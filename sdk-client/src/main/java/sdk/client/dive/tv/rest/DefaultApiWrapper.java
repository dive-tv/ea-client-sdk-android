package sdk.client.dive.tv.rest;

import sdk.client.dive.tv.rest.callbacks.ApiCallbackImpl;

import java.lang.reflect.Proxy;

import com.touchvie.sdk.ApiCallback;
import com.touchvie.sdk.ApiException;
import com.touchvie.sdk.api.DefaultApi;
import com.touchvie.sdk.model.AccessToken;
import com.touchvie.sdk.api.InterfaceApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import sdk.client.dive.tv.rest.callbacks.ClientCallback;
import sdk.client.dive.tv.rest.enums.RestAPIError;
import sdk.client.dive.tv.utils.Constants;
import sdk.client.dive.tv.utils.SharedPreferencesHelper;

public class DefaultApiWrapper extends DefaultApi implements InterfaceApi , InvocationHandler {

    private final DefaultApi delegate;
    private final SharedPreferencesHelper sharedPreferences;

    private final String basicAuthToken;
    private final String deviceId;

    public DefaultApiWrapper(DefaultApi delegate, SharedPreferencesHelper sharedPreferences) {
        this.delegate = delegate;
        this.sharedPreferences = sharedPreferences;
        this.basicAuthToken = this.sharedPreferences.getBasicAuthToken();
        this.deviceId = this.sharedPreferences.getDeviceId();
    }

    public static InterfaceApi wrap(DefaultApi wrapped, SharedPreferencesHelper settings) {
        return (InterfaceApi) Proxy.newProxyInstance(DefaultApi.class.getClassLoader(), new Class[] { InterfaceApi.class }, new DefaultApiWrapper(wrapped, settings));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String accessToken = getAccessToken(args);
        if(accessToken == null || accessToken.length() == 0) {
            if(accessToken == null)
                return tokenCall(TokenCallType.TOKEN, method, args);
            else
                return tokenCall(TokenCallType.REFRESH_TOKEN, method, args);
        } else {
            Method m = findMethod(delegate.getClass(), method);
            if (m != null)
                return m.invoke(delegate, args);
        }
        return null;
    }

    /**
     * Retrieves the value of authorization argument (access token)
     *
     * @param args Array of API method arguments
     * @return the first argument (authorization)
     */
    private String getAccessToken(Object[] args) {
        return (String) args[0]; // return the first argument (authorization)
    }

    /**
     * @param clazz DefaultAPI class
     * @param method the API method definition to invoke
     * @return the API method to invoke
     * @throws Throwable
     */
    private Method findMethod(Class<?> clazz, Method method) throws Throwable {
        try {
            return clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param callType Type of token call. Allowed values: TOKEN | REFRESH_TOKEN
     * @param method the API method definition to invoke
     * @param args Array of API method arguments
     * @return Call object of async call.
     */
    private Object tokenCall(TokenCallType callType, Method method, Object[] args) {
        ClientCallback callback = getCallback(args);
        try {
            if(callType == TokenCallType.TOKEN) {
                return delegate.postTokenAsync(basicAuthToken, Constants.DEVICE_GRANT_TYPE, null, deviceId, null, tokenCallback(callback, method, args));
            } else if (callType == TokenCallType.REFRESH_TOKEN) {
                String refreshToken = sharedPreferences.getRefreshToken();
                if(refreshToken == null || refreshToken.length() == 0)
                    return delegate.postTokenAsync(basicAuthToken, Constants.DEVICE_GRANT_TYPE, null, deviceId, null, tokenCallback(callback, method, args));
                else
                    return delegate.postTokenAsync(basicAuthToken, Constants.REFRESH_TOKEN_GRANT_TYPE, null, null, refreshToken, tokenCallback(callback, method, args));
            }
        } catch (ApiException e) {
            e.printStackTrace();
            callback.onFailure(RestAPIError.INTERNAL_ERROR);
        }
        return null;
    }

    /**
     * Retrieves the value of callback argument (ApiCallbackImpl object)
     *
     * @param args Array of API method arguments
     * @return the last argument (ApiCallbackImpl object)
     */
    private ClientCallback getCallback(Object[] args) {
        ApiCallbackImpl apiCallback = (ApiCallbackImpl) args[args.length-1];
        return apiCallback.getCallback();
    }

    /**
     * ApiCallback implementation to retrieve the response to postToken API call. In the "onSuccess" method, the AccessToken retrieved is stored on shared preferences and then the API call is made
     *
     * @param callback the Client callback implementation
     * @param method the API method definition to invoke
     * @param args Array of API method arguments
     * @return the ApiCallback implementation
     */
    private ApiCallback<AccessToken> tokenCallback(final ClientCallback callback, final Method method, final Object[] args) {
        return new ApiCallback<AccessToken>() {
            @Override
            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> headers) {
                e.printStackTrace();
                callback.onFailure(RestAPIError.getEnum(Constants.GET_TOKEN_SERVICE_NAME, statusCode));
            }
            @Override
            public void onSuccess(AccessToken accessToken, int statusCode, Map<String, List<String>> headers) {
                sharedPreferences.storeToken(accessToken);
                args[0] = Constants.TOKEN_PREFIX + accessToken.getAccessToken(); // replace the first argument (authorization)
                try {
                    Method m = findMethod(delegate.getClass(), method);
                    if (m != null)
                        m.invoke(delegate, args);
                } catch (Throwable e) {
                    e.printStackTrace();
                    callback.onFailure(RestAPIError.UNEXPECTED_ERROR);
                }
            }
            @Override
            // TO IGNORE
            public void onUploadProgress(long l, long l1, boolean b) {}
            @Override
            // TO IGNORE
            public void onDownloadProgress(long l, long l1, boolean b) {}
        };
    }

    private enum TokenCallType {
        TOKEN,
        REFRESH_TOKEN
    }
}