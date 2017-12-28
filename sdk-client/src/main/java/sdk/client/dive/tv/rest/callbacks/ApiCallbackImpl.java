package sdk.client.dive.tv.rest.callbacks;

import java.util.List;
import java.util.Map;

import com.touchvie.sdk.ApiCallback;
import com.touchvie.sdk.ApiException;

import sdk.client.dive.tv.rest.enums.RestAPIError;


public class ApiCallbackImpl<T> implements ApiCallback<T> {

    private String serviceName;
    private sdk.client.dive.tv.rest.callbacks.ClientCallback<T> callback;

    public ApiCallbackImpl(String serviceName, sdk.client.dive.tv.rest.callbacks.ClientCallback<T> callback) {
        this.serviceName = serviceName;
        this.callback = callback;
    }

    public sdk.client.dive.tv.rest.callbacks.ClientCallback<T> getCallback() {
        return this.callback;
    }

    @Override
    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
        e.printStackTrace();
        this.callback.onFailure(RestAPIError.getEnum(serviceName, statusCode));
    }

    @Override
    public void onSuccess(T result, int statusCode, Map<String, List<String>> responseHeaders) {
        this.callback.onSuccess(result);
    }

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
        // TO IGNORE
    }

    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        // TO IGNORE
    }
}
