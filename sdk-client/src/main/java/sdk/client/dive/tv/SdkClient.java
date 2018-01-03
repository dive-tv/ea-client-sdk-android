package sdk.client.dive.tv;

import android.content.Context;

import com.touchvie.sdk.ApiClient;
import com.touchvie.sdk.ApiException;
import com.touchvie.sdk.api.DefaultApi;
import com.touchvie.sdk.api.InterfaceApi;
import com.touchvie.sdk.model.Card;
import com.touchvie.sdk.model.ChannelStatus;
import com.touchvie.sdk.model.MovieStatus;

import java.util.List;

import sdk.client.dive.tv.rest.DefaultApiWrapper;
import sdk.client.dive.tv.rest.callbacks.ApiCallbackImpl;
import sdk.client.dive.tv.rest.callbacks.ClientCallback;
import sdk.client.dive.tv.rest.enums.RestAPIError;
import sdk.client.dive.tv.socket.SocketListener;
import sdk.client.dive.tv.socket.StreamApi;
import sdk.client.dive.tv.socket.model.StreamMessage;
import sdk.client.dive.tv.utils.Constants;
import sdk.client.dive.tv.utils.SharedPreferencesHelper;
import com.touchvie.sdk.model.TvGrid;


public class SdkClient {

    private static volatile SdkClient instance;

    private SharedPreferencesHelper sharedPreferences;
    private InterfaceApi restAPI;
    private StreamApi streamAPI;

    /**
     * @param context Instance of application context.
     * @param environment API environment. Allowed values: dev | pre | pro
     * @param apiKey Basic authorization key provided by dive.tv
     * @param deviceId Unique identifier of device.
     * @return Instance of SDK client object
     */
    public static synchronized SdkClient getOrCreateInstance(Context context, String environment, String apiKey, String deviceId) {
        if (instance == null)
            synchronized (SdkClient.class) {
                if (instance == null)
                    instance = new SdkClient(context, environment, apiKey, deviceId);
            }
        return instance;
    }

    /**
     * @param context Instance of application context.
     * @param apiKey Basic authorization key provided by dive.tv
     * @param deviceId Unique identifier of device.
     * @return Instance of SDK client object
     */
    public static synchronized SdkClient getOrCreateInstance(Context context, String apiKey, String deviceId) {
        if (instance == null)
            synchronized (SdkClient.class) {
                if (instance == null)
                    instance = new SdkClient(context, Constants.DEFAULT_ENVIRONMENT, apiKey, deviceId);
            }
        return instance;
    }

    public static synchronized SdkClient getInstance() {
        return instance;
    }

    private SdkClient(Context context, String environment, String apiKey, String deviceId) {
        this.sharedPreferences = new SharedPreferencesHelper(context);
        this.sharedPreferences.storeApiKey(apiKey);
        this.sharedPreferences.storeDeviceId(deviceId);
        ApiClient apiClient = new ApiClient(environment);
        DefaultApi restAPI = new DefaultApi(apiClient);
        this.restAPI = DefaultApiWrapper.wrap(restAPI, this.sharedPreferences);
        this.streamAPI = new StreamApi(environment, this.sharedPreferences, restAPI, apiClient.getJSON().getGson());
    }

    /**
     * Checks whether a list of client movie identifiers (Video On Demand) are available to be synchronized using the Dive API
     *
     * @param clientMovieIds List of client movie IDs
     * @param callback Instance of client callback. "onSuccess" method expects to receive a list of movie status
     */
    public void getReadyMovies(List<String> clientMovieIds, ClientCallback<List<MovieStatus>> callback) {
        try {
            ApiCallbackImpl<List<MovieStatus>> apiCallback = new ApiCallbackImpl(Constants.GET_READY_MOVIES_SERVICE_NAME, callback);
            this.restAPI.getReadyMoviesAsync(sharedPreferences.getAccessTokenWithPrefix(), clientMovieIds, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Connection method for the card stream of the requested VOD contextual information, starting from the indicated timestamp
     *
     * @param movieId Movie ID for the requested VOD
     * @param timestamp Current playback timestamp in seconds
     * @param listener Instance of socket listener.
     */
    public void vodStreamConnect(String movieId, int timestamp, SocketListener listener) {
        StreamMessage message = new StreamMessage(movieId, timestamp);
        this.streamAPI.connect(message, listener);
    }

    /**
     * Client message, sets a new playback timestamp for the current VOD contextual stream.
     *
     * @param timestamp New playback timestamp, in seconds
     */
    public void vodStreamSetMessage(int timestamp) {
        StreamMessage message = new StreamMessage(timestamp, StreamMessage.Type.vod_set);
        this.streamAPI.sendMessage(message);
    }

    /**
     * Client message, signals a VOD playback resume after a pause.
     *
     * @param timestamp New playback timestamp, in seconds
     */
    public void vodStreamContinueMessage(int timestamp) {
        StreamMessage message = new StreamMessage(timestamp, StreamMessage.Type.vod_continue);
        this.streamAPI.sendMessage(message);
    }

    /**
     * Client message, signals a pause in current VOD playback.
     *
     * @param timestamp New playback timestamp, in seconds
     */
    public void vodStreamPauseMessage(int timestamp) {
        StreamMessage message = new StreamMessage(timestamp, StreamMessage.Type.vod_pause);
        this.streamAPI.sendMessage(message);
    }

    /**
     * Client message, indicates the end of current VOD playback and closes the contextual stream.
     */
    public void vodStreamEndMessage() {
        StreamMessage message = new StreamMessage(StreamMessage.Type.vod_end);
        this.streamAPI.sendMessage(message);
    }

    /**
     * Checks if a list of client channel identifiers are currently broadcasting synchronizable content
     *
     * @param channelId List of client channel IDs
     * @param callback Instance of client callback. "onSuccess" method expects to receive a list of channel status
     */
    public void getReadyChannels(List<String> channelId, final ClientCallback<List<ChannelStatus>> callback){
        try {
            ApiCallbackImpl<List<ChannelStatus>> apiCallback = new ApiCallbackImpl(Constants.GET_READY_CHANNELS_SERVICE_NAME,callback);
            this.restAPI.getReadyChannelsAsync(sharedPreferences.getAccessToken(), channelId, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Connection method for the card stream of the requested channel contextual information.
     *
     * @param channelId Channel ID for the requested linear TV channel
     * @param listener Instance of socket listener.
     */
    public void tvChannelStreamConnect(String channelId, SocketListener listener) {
        StreamMessage message = new StreamMessage(channelId);
        this.streamAPI.connect(message, listener);
    }

    /**
     * Disconnect method that closes the open socket with stream API server
     */
    public void streamDisconnect() {
        this.streamAPI.disconnect();
    }

    /**
     * Retrieves a full card detail, without relations or context
     *
     * @param cardId Unique identifier of card
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a card
     */
    public void getCard(String cardId, String acceptLanguage, final ClientCallback<Card> callback) {
        try {
            ApiCallbackImpl<Card> apiCallback = new ApiCallbackImpl(Constants.GET_CARD_SERVICE_NAME, callback);
            this.restAPI.getCardAsync(sharedPreferences.getAccessTokenWithPrefix(), cardId, acceptLanguage, null,null, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Retrieves a full card detail, and its relations to other cards in a given context (card version)
     *
     * @param cardId Unique identifier of card
     * @param version Version identifier, indicates the context where the card is being requested
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a card
     */
    public void getCardVersion(String cardId, String version, String acceptLanguage, ClientCallback<Card> callback) {
        try {
            ApiCallbackImpl<Card> apiCallback = new ApiCallbackImpl(Constants.GET_CARD_VERSION_SERVICE_NAME, callback);
            this.restAPI.getCardVersionAsync(sharedPreferences.getAccessTokenWithPrefix(), cardId, version, acceptLanguage, null, null, null, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Returns a paginated list of cards liked by current user
     *
     * @param acceptLanguage Client locale, as language-country
     * @param paginateKey Paginate key
     * @param size Number of desired results
     * @param callback Instance of client callback. "onSuccess" method expects to receive a list of cards
     */
    public void getlikes(String acceptLanguage, String paginateKey, String size, ClientCallback<List<Card>> callback) {
        try {
            ApiCallbackImpl<List<Card>> apiCallback = new ApiCallbackImpl(Constants.GET_LIKES_SERVICE_NAME, callback);
            this.restAPI.getLikesAsync(sharedPreferences.getAccessTokenWithPrefix(), acceptLanguage, null, paginateKey,
                    size, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Stores a card under current user’s likes list
     *
     * @param cardId Requested card ID
     * @param callback Instance of client callback. "onSuccess" method expects to receive a "java.lang.Void" object
     */
    public void postLikes(String cardId, ClientCallback<Void> callback) {
        try {
            ApiCallbackImpl<Void> apiCallback = new ApiCallbackImpl(Constants.ADD_LIKE_SERVICE_NAME, callback);
            this.restAPI.postLikesAsync(sharedPreferences.getAccessTokenWithPrefix(), cardId, null,
            apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Removes a card from current user’s likes list
     *
     * @param cardId Requested card ID
     * @param callback Instance of client callback. "onSuccess" method expects to receive a "java.lang.Void" object
     */
    public void deleteLikes(String cardId, ClientCallback<Void> callback) {
        try {
            ApiCallbackImpl<Void> apiCallback = new ApiCallbackImpl(Constants.DELETE_LIKE_SERVICE_NAME, callback);
            this.restAPI.deleteLikesAsync(sharedPreferences.getAccessTokenWithPrefix(), cardId, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Retrieves a movie’s full card by its client ID, including catalog and cast information
     *
     * @param clientMovieId Client movie ID being played
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a card
     */
    public void getCatalogMovie(String clientMovieId, String acceptLanguage, ClientCallback<Card> callback) {
        try {
            ApiCallbackImpl<Card> apiCallback = new ApiCallbackImpl(Constants.GET_CATALOG_MOVIE_SERVICE_NAME, callback);
            this.restAPI.getCatalogMovieAsync(sharedPreferences.getAccessTokenWithPrefix(), clientMovieId, acceptLanguage,
                    null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Retrieves the current list of cards related to the given movie scene
     *
     * @param clientMovieId Client movie ID being played
     * @param timestamp Current movie timestamp in seconds
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a list of cards
     */
    public void getStaticMovieScene(String clientMovieId, Float timestamp, String acceptLanguage, ClientCallback<List<Card>> callback) {
        try {
            ApiCallbackImpl<List<Card>> apiCallback = new ApiCallbackImpl(Constants.GET_STATIC_MOVIE_SCENE_SERVICE_NAME, callback);
            this.restAPI.getStaticMovieSceneAsync(sharedPreferences.getAccessTokenWithPrefix(), clientMovieId, timestamp,
                    acceptLanguage, null, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Returns the current and upcoming grid of TV events for the given channel
     *
     * @param clientChannelId Client channel ID
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a tv grid
     */
    public void getChannelGrid(String clientChannelId, String acceptLanguage, ClientCallback<TvGrid> callback) {
        try {
            ApiCallbackImpl<TvGrid> apiCallback = new ApiCallbackImpl(Constants.GET_CHANNEL_GRID_SERVICE_NAME, callback);
            this.restAPI.getChannelGridAsync(sharedPreferences.getAccessTokenWithPrefix(), clientChannelId, acceptLanguage, null,
                    apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Retrieves full card detail, including catalog and cast information, for the content currently being broadcasted on the channel
     *
     * @param clientChannelId Client channel ID
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a card
     */
    public void getChannelMovie(String clientChannelId, String acceptLanguage, ClientCallback<Card> callback) {
        try {
            ApiCallbackImpl<Card> apiCallback = new ApiCallbackImpl(Constants.GET_CHANNEL_MOVIE_SERVICE_NAME, callback);
            this.restAPI.getChannelMovieAsync(sharedPreferences.getAccessTokenWithPrefix(), clientChannelId, acceptLanguage, null,
            apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }

    /**
     * Retrieves the list of cards related to the content currently being broadcasted in the provided channel
     *
     * @param clientChannelId Client channel ID
     * @param acceptLanguage Client locale, as language-country
     * @param callback Instance of client callback. "onSuccess" method expects to receive a list of cards
     */
    public void getStaticChannelScene(String clientChannelId, String acceptLanguage, ClientCallback<List<Card>> callback) {
        try {
            ApiCallbackImpl<List<Card>> apiCallback = new ApiCallbackImpl(Constants.GET_STATIC_CHANNEL_SCENE_SERVICE_NAME, callback);
            this.restAPI.getStaticChannelSceneAsync(sharedPreferences.getAccessTokenWithPrefix(), clientChannelId, acceptLanguage,
                    null, null, apiCallback);
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
    }
}
