package sdk.client.dive.tv.socket.model;

import sdk.client.dive.tv.utils.Constants;

public class StreamError {

    private int code;
    private String type;
    private String error;

    public StreamError(){}

    public StreamError(int code, String type, String error) {
        this.code = code;
        this.type = type;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static StreamError connectSocketError(String exceptionMessage){
        return new StreamError(Constants.ERROR_CODE, Constants.CONNECT_ERROR_TYPE, exceptionMessage);
    }

    public static StreamError openSocketError(String exceptionMessage){
        return new StreamError(Constants.ERROR_CODE, Constants.OPEN_SOCKET_ERROR_TYPE, exceptionMessage);
    }

    public static StreamError emitMessageError(String exceptionMessage){
        return new StreamError(Constants.ERROR_CODE, Constants.EMIT_MESSAGE_ERROR_TYPE, exceptionMessage);
    }

    public static StreamError authenticationError(int status, String exceptionMessage){
        return new StreamError(status, Constants.AUTHENTICATION_ERROR_TYPE, exceptionMessage);
    }

    public static StreamError authenticationError(String exceptionMessage){
        return new StreamError(Constants.ERROR_CODE, Constants.AUTHENTICATION_ERROR_TYPE, exceptionMessage);
    }

    public static StreamError unauthorizedError(String exceptionMessage){
        return new StreamError(Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_ERROR_TYPE, exceptionMessage);
    }
}
