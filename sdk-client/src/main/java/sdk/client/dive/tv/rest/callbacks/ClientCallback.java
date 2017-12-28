package sdk.client.dive.tv.rest.callbacks;

import sdk.client.dive.tv.rest.enums.RestAPIError;

/**
 * ClientCallback for asynchronous call.
 *
 * @param <T> The return type
 */
public interface ClientCallback<T> {

    /**
     * This is called when the API call fails.
     *
     * @param message The message to send on failure
     */
    void onFailure(RestAPIError message);

    /**
     * This is called when the API call succeeded.
     *
     * @param result The result deserialized from response
     */
    void onSuccess(T result);
}
