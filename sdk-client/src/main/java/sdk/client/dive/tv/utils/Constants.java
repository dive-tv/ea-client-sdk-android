package sdk.client.dive.tv.utils;


public class Constants {

    public static final String DEVICE_GRANT_TYPE = "device_credentials";
    public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    public static final String BASIC_AUTH_PREFIX = "Basic ";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String EMPTY_STRING = "";

    public static final String DEFAULT_ENVIRONMENT = "pro";

    // SHARED PREFERENCES
    public static final String SHARED_PREFERENCES = "dive.tv.preferences";
    public static final String ACCESS_TOKEN_VALUE = "dive.tv.auth.access.token.value";
    public static final String ACCESS_TOKEN_EXPIRATION = "dive.tv.auth.access.token.expiration";
    public static final String REFRESH_TOKEN_VALUE = "dive.tv.auth.refresh.token.value";
    public static final String REFRESH_TOKEN_EXPIRATION = "dive.tv.auth.refresh.token.expiration";
    public static final String API_KEY_VALUE = "dive.tv.auth.client.api.key";
    public static final String DEVICE_ID_VALUE = "dive.tv.auth.client.device.id";
    public static final long EXPIRATION_MARGIN = 10000; // 10 seconds
    public static final long MONTH_TO_MILLISECONDS = 2629746000L;

    //STREAM API
    public static final String TV_CHANNEL_ENDPOINT = "/channels";
    public static final String VOD_ENDPOINT = "/movies";
    public static final String MOVIE_ID_QUERY_PARAM = "movie_id=";
    public static final String TIMESTAMP_QUERY_PARAM = "&timestamp=";
    public static final String CHANNEL_ID_QUERY_PARAM = "channel_id=";
    public static final String DEVELOPMENT = "dev";
    public static final String DEVELOPMENT_STREAM_SERVER_HOST = "https://dev-stream.dive.tv";
    public static final String PRE_PRODUCTION = "pre";
    public static final String PRE_PRODUCTION_STREAM_SERVER_HOST = "https://pre-stream.dive.tv";
    public static final String PRODUCTION = "pro";
    public static final String STREAM_SERVER_HOST = "https://stream.dive.tv";

    //STREAM ERROR
    public static final int ERROR_CODE = 500;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final String CONNECT_ERROR_TYPE = "Error connecting to socket";
    public static final String OPEN_SOCKET_ERROR_TYPE = "Error opening a socket";
    public static final String EMIT_MESSAGE_ERROR_TYPE = "Error emitting message";
    public static final String AUTHENTICATION_ERROR_TYPE = "Authentication error";
    public static final String UNAUTHORIZED_ERROR_TYPE = "Unauthorized";

    // SDK CLIENT
    public static final String GET_READY_CHANNELS_SERVICE_NAME = "ready-channels";
    public static final String GET_READY_MOVIES_SERVICE_NAME = "ready-movies";
    public static final String GET_CARD_SERVICE_NAME = "card";
    public static final String GET_CARD_VERSION_SERVICE_NAME = "card-version";
    public static final String GET_TOKEN_SERVICE_NAME = "token";
    public static final String GET_LIKES_SERVICE_NAME = "get-likes";
    public static final String ADD_LIKE_SERVICE_NAME = "add-like";
    public static final String DELETE_LIKE_SERVICE_NAME = "delete-like";
    public static final String GET_CATALOG_MOVIE_SERVICE_NAME = "catalog-movie";
    public static final String GET_STATIC_MOVIE_SCENE_SERVICE_NAME = "static-movie";
    public static final String GET_CHANNEL_GRID_SERVICE_NAME = "channel-grid";
    public static final String GET_CHANNEL_MOVIE_SERVICE_NAME = "channel-movie";
    public static final String GET_STATIC_CHANNEL_SCENE_SERVICE_NAME = "static-channel";

    // SOCKET MANAGER
    public static final String SOCKET_PATH = "/v1/stream";
    public static final String[] SOCKET_TRANSPORT_PROTOCOL = new String[]{"websocket"};
    public static final boolean SOCKET_SECURE_FLAG = true;
    public static final boolean SOCKET_MULTIPLEX_FLAG = false;
    public static final boolean SOCKET_RECONNECTION_FLAG = true;
    public static final String SOCKET_HANDSHAKE_KEY = "token";
    public static final String JSON_KEY_CARDS = "cards";
    public static final String JSON_KEY_MOVIE_ID = "movie_id";
    public static final String EMIT_ARGUMENT_KEY_TIMESTAMP = "timestamp";
    public static final String EVENT_CONNECT = "connect";
    public static final String EVENT_AUTHENTICATED = "authenticated";
    public static final String EVENT_UNAUTHORIZED = "unauthorized";
    public static final String EVENT_MOVIE_START = "movie_start";
    public static final String EVENT_MOVIE_END = "movie_end";
    public static final String EVENT_SCENE_START = "scene_start";
    public static final String EVENT_SCENE_UPDATE = "scene_update";
    public static final String EVENT_SCENE_END = "scene_end";
    public static final String EVENT_PAUSE_START = "pause_start";
    public static final String EVENT_PAUSE_END = "pause_end";
    public static final String EVENT_ERROR = "error";
}
