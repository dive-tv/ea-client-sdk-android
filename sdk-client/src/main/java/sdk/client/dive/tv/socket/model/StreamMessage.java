package sdk.client.dive.tv.socket.model;

public class StreamMessage {

    private Type type;
    private String movieId;
    private String channelId;
    private Integer timestamp;

    public StreamMessage(String channelId) {
        this.type = Type.connect_tv_channel;
        this.channelId = channelId;
    }

    public StreamMessage(String movieId, Integer timestamp) {
        this.type = Type.connect_vod;
        this.movieId = movieId;
        this.timestamp = timestamp;
    }

    public StreamMessage(Integer timestamp, Type type) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public StreamMessage(Type messageType) {
        this.type = messageType;
    }

    public Type getType() {
        return type;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getChannelId() {
        return channelId;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public enum Type {
        authenticate,
        connect_vod,
        connect_tv_channel,
        vod_set,
        vod_pause,
        vod_continue,
        vod_end;
    }
}
