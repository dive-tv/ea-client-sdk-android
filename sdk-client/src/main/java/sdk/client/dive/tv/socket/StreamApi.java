package sdk.client.dive.tv.socket;

import com.google.gson.Gson;
import com.touchvie.sdk.api.DefaultApi;

import java.util.HashMap;

import sdk.client.dive.tv.socket.model.StreamMessage;
import sdk.client.dive.tv.utils.Constants;
import sdk.client.dive.tv.utils.SharedPreferencesHelper;
import sdk.client.dive.tv.socket.model.StreamError;


/**
 * Class to manage all the StreamApi operations.
 */
public class StreamApi {

    private static HashMap<String, String> hosts;
    static {
        hosts = new HashMap<>();
        hosts.put(Constants.DEVELOPMENT, Constants.DEVELOPMENT_STREAM_SERVER_HOST);
        hosts.put(Constants.PRE_PRODUCTION, Constants.PRE_PRODUCTION_STREAM_SERVER_HOST);
        hosts.put(Constants.PRODUCTION, Constants.STREAM_SERVER_HOST);
    }

    private String host;
    private SocketManager socketManager;

    public StreamApi(String environment, SharedPreferencesHelper settings, DefaultApi restAPI, Gson gson) {
        this.host = hosts.get(environment);
        this.socketManager = new SocketManager(settings, restAPI, gson);
    }

    public void connect(StreamMessage message, SocketListener listener) {
        try {
            String queryParams;
            String url;
            if(message.getType() == StreamMessage.Type.connect_vod) {
                queryParams = Constants.MOVIE_ID_QUERY_PARAM + message.getMovieId() + Constants.TIMESTAMP_QUERY_PARAM + message.getTimestamp();
                url = this.host + Constants.VOD_ENDPOINT;
            } else {
                queryParams = Constants.CHANNEL_ID_QUERY_PARAM + message.getChannelId();
                url = this.host + Constants.TV_CHANNEL_ENDPOINT;
            }
            this.socketManager.openSocket(queryParams, url, listener);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onErrorReceived(StreamError.connectSocketError(e.getMessage()));
        }
    }

    public void sendMessage(StreamMessage message){
        this.socketManager.emitMessage(message);
    }

    public void disconnect() {
        this.socketManager.closeSocket();
    }

}