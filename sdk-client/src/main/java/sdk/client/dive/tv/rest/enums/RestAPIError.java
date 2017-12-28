package sdk.client.dive.tv.rest.enums;

public enum RestAPIError {
    UNEXPECTED_ERROR(null, 0, "Unexpected Error"),
    GRANT_TYPE_MISSING(null, 400, "Grant type missing"),
    UNAUTHORIZED(null, 401, "Unauthorized"),
    FORBIDDEN(null, 403, "Forbidden"),
    NOT_FOUND(null, 404, "Not found"),
    INTERNAL_ERROR(null, 500, "Internal Error"),
    SERVICE_TEMPORARY_UNAVAILABLE(null, 503, "Service temporary unavailable");


    private final String serviceName;
    private final int statusCode;
    private final String message;

    public String getMessage() {
        return this.message;
    }

    RestAPIError(String serviceName, int statusCode, String message) {
        this.serviceName = serviceName;
        this.statusCode = statusCode;
        this.message = message;
    }

    public static RestAPIError getEnum(String serviceNameStr, int statusCodeInt) {
        for (RestAPIError failureMessage : RestAPIError.values())
            if (failureMessage.statusCode == statusCodeInt)
                if (failureMessage.serviceName == null || (failureMessage.serviceName != null && failureMessage.serviceName.equals(serviceNameStr)))
                    return failureMessage;

        return UNEXPECTED_ERROR;
    }
}
