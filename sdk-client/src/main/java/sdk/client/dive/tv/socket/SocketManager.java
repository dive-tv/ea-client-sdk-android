package sdk.client.dive.tv.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.touchvie.sdk.ApiCallback;
import com.touchvie.sdk.ApiException;
import com.touchvie.sdk.api.DefaultApi;
import com.touchvie.sdk.model.AccessToken;
import com.touchvie.sdk.model.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import sdk.client.dive.tv.socket.model.StreamError;
import sdk.client.dive.tv.socket.model.StreamMessage;
import sdk.client.dive.tv.utils.Constants;
import sdk.client.dive.tv.utils.SharedPreferencesHelper;

public class SocketManager {

    private SharedPreferencesHelper settings;
    private DefaultApi restAPI;
    private Gson gson;
    private HostnameVerifier hostnameVerifier;

    private Socket socket;

    private SocketListener listener;

    public SocketManager(SharedPreferencesHelper settings, DefaultApi restAPI, Gson gson) {
        this.settings = settings;
        this.restAPI = restAPI;
        this.gson = gson;
        this.hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    public void openSocket(String queryParams, String url, SocketListener listener) {
        try {
            if (isConnected())
                closeSocket();
            this.listener = listener;
            IO.Options socketOptions = getSocketOptions(queryParams);
            socketConnection(url, socketOptions);
        } catch (Exception e) {
            e.printStackTrace();
            this.listener.onErrorReceived(StreamError.openSocketError(e.getMessage()));
            closeSocket();
        }
    }

    public void closeSocket() {
        if (this.socket != null && this.socket.connected())
            this.socket.close();
        this.listener = null;
    }

    public void emitMessage(StreamMessage message) {
        if (isConnected()) {
            try {
                String event = message.getType().name();
                JSONObject args = null;
                if (message.getTimestamp() != null) {
                    args = new JSONObject();
                    args.put(Constants.EMIT_ARGUMENT_KEY_TIMESTAMP, message.getTimestamp());
                }

                if (args == null)
                    this.socket.emit(event);
                else
                    this.socket.emit(event, args.toString());
            } catch (Exception e) {
                e.printStackTrace();
                this.listener.onErrorReceived(StreamError.emitMessageError(e.getMessage()));
                closeSocket();
            }
        }
    }

    private boolean isConnected() {
        return this.socket != null && this.listener != null && this.socket.connected();
    }

    private IO.Options getSocketOptions(String queryParams) throws NoSuchAlgorithmException {
        IO.Options options = new IO.Options();
        IO.setDefaultSSLContext(SSLContext.getDefault());
        IO.setDefaultHostnameVerifier(this.hostnameVerifier);
        options.sslContext = SSLContext.getDefault();
        options.hostnameVerifier = this.hostnameVerifier;
        options.secure = Constants.SOCKET_SECURE_FLAG;
        options.path = Constants.SOCKET_PATH;
        options.multiplex = Constants.SOCKET_MULTIPLEX_FLAG;
        options.reconnection = Constants.SOCKET_RECONNECTION_FLAG;
        options.transports = Constants.SOCKET_TRANSPORT_PROTOCOL;
        options.query = queryParams;
        return options;
    }

    private void socketConnection(String url, IO.Options socketOptions) throws URISyntaxException {
        this.socket = IO.socket(new URI(url).toString(), socketOptions);

        this.socket.on(Constants.EVENT_CONNECT, onConnect);
        this.socket.on(Constants.EVENT_AUTHENTICATED, onAuthenticated);
        this.socket.on(Constants.EVENT_UNAUTHORIZED, onUnauthorized);
        this.socket.on(Constants.EVENT_MOVIE_START, onMovieStart);
        this.socket.on(Constants.EVENT_MOVIE_END, onMovieEnd);
        this.socket.on(Constants.EVENT_SCENE_START, onSceneStart);
        this.socket.on(Constants.EVENT_SCENE_UPDATE, onSceneUpdate);
        this.socket.on(Constants.EVENT_SCENE_END, onSceneEnd);
        this.socket.on(Constants.EVENT_PAUSE_START, onPauseStart);
        this.socket.on(Constants.EVENT_PAUSE_END, onPauseEnd);
        this.socket.on(Constants.EVENT_ERROR, onError);

        this.socket.connect();
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            try {
                ApiCallback<AccessToken> callback = new ApiCallback<AccessToken>() {
                    @Override
                    public void onFailure(ApiException e, int status, Map<String, List<String>> headers) {
                        e.printStackTrace();
                        listener.onErrorReceived(StreamError.authenticationError(status, e.getMessage()));
                    }

                    @Override
                    public void onSuccess(AccessToken accessToken, int i, Map<String, List<String>> map) {
                        try {
                            settings.storeToken(accessToken);
                            JSONObject tokenObj = new JSONObject();
                            tokenObj.put(Constants.SOCKET_HANDSHAKE_KEY, accessToken.getAccessToken());
                            socket.emit(StreamMessage.Type.authenticate.name(), tokenObj);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onErrorReceived(StreamError.authenticationError(e.getMessage()));
                        }
                    }
                    @Override
                    //TO IGNORE
                    public void onUploadProgress(long l, long l1, boolean b) {}
                    @Override
                    //TO IGNORE
                    public void onDownloadProgress(long l, long l1, boolean b) {}
                };
                String accessToken = settings.getAccessToken();
                if (accessToken == null || accessToken.length() == 0) {
                    String basicAuthToken = settings.getBasicAuthToken();
                    String deviceId = settings.getDeviceId();
                    if (accessToken == null) {
                        restAPI.postTokenAsync(basicAuthToken, Constants.DEVICE_GRANT_TYPE, null, deviceId, null, callback);
                    } else {
                        String refreshToken = settings.getRefreshToken();
                        if (refreshToken == null || refreshToken.length() == 0)
                            restAPI.postTokenAsync(basicAuthToken, Constants.DEVICE_GRANT_TYPE, null, deviceId, null, callback);
                        else
                            restAPI.postTokenAsync(basicAuthToken, Constants.REFRESH_TOKEN_GRANT_TYPE, null, null, refreshToken, callback);
                    }
                } else {
                    JSONObject tokenObj = new JSONObject();
                    tokenObj.put(Constants.SOCKET_HANDSHAKE_KEY, accessToken);
                    socket.emit(StreamMessage.Type.authenticate.name(), tokenObj);
                }
            } catch (Exception e) {
                e.printStackTrace();
                listener.onErrorReceived(StreamError.authenticationError(e.getMessage()));
            }
        }
    };

    private Emitter.Listener onAuthenticated = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
        }
    };

    private Emitter.Listener onUnauthorized = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject response = (JSONObject) args[0];
            listener.onErrorReceived(StreamError.unauthorizedError(response.toString()));
        }
    };

    private Emitter.Listener onMovieStart = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args.length == 1) {
                try {
                    JSONObject response = (JSONObject) args[0];
                    String movieId = response.getString(Constants.JSON_KEY_MOVIE_ID);
                    if(movieId != null)
                        listener.onMovieStartEventReceived(movieId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Emitter.Listener onMovieEnd = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            listener.onMovieEndEventReceived();
        }
    };

    private Emitter.Listener onSceneStart = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args.length > 0) {
                try {
                    JSONObject response = (JSONObject) args[0];
                    Type listCard = new TypeToken<List<Card>>() {
                    }.getType();
                    String jsonCards = response.getString(Constants.JSON_KEY_CARDS);
                    if(jsonCards != null) {
                        List<Card> cards = gson.fromJson(jsonCards, listCard);
                        listener.onSceneStartEventReceived(cards);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Emitter.Listener onSceneUpdate = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args.length > 0) {
                try {
                    JSONObject response = (JSONObject) args[0];
                    Type listCard = new TypeToken<List<Card>>() {
                    }.getType();
                    String jsonCards = response.getString(Constants.JSON_KEY_CARDS);
                    if(jsonCards != null) {
                        List<Card> cards = gson.fromJson(jsonCards, listCard);
                        listener.onSceneUpdateEventReceived(cards);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Emitter.Listener onSceneEnd = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            listener.onSceneEndEventReceived();
        }
    };

    private Emitter.Listener onPauseStart = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            listener.onPausedStartEventReceived();
        }
    };

    private Emitter.Listener onPauseEnd = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            listener.onPausedEndEventReceived();
        }
    };

    private Emitter.Listener onError = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args.length > 0) {
                try {
                    JSONObject response = (JSONObject) args[0];
                    StreamError error = gson.fromJson(response.toString(), StreamError.class);
                    listener.onErrorReceived(error);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
