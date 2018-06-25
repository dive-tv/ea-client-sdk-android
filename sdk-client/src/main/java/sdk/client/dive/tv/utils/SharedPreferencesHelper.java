package sdk.client.dive.tv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.touchvie.sdk.model.AccessToken;

public class SharedPreferencesHelper {

    private Context appContext;

    public SharedPreferencesHelper(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Retrieves the access token stored on shared preferences. If the stored token has expired, returns an empty string
     *
     * @return the access token
     */
    public String getAccessToken() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String accessToken = sharedPref.getString(Constants.ACCESS_TOKEN_VALUE, null);
        if (accessToken == null || accessToken.length() == 0)
            return null;
        long expiration = sharedPref.getLong(Constants.ACCESS_TOKEN_EXPIRATION, 0);
        if(expiration > (System.currentTimeMillis() + Constants.EXPIRATION_MARGIN))
            return accessToken;
        else
            return Constants.EMPTY_STRING;
    }

    /**
     * Retrieves the access token stored on shared preferences with "Bearer " prefix.
     *
     * @return the access token with "Bearer " prefix
     */
    public String getAccessTokenWithPrefix() {
        String accessToken = getAccessToken();
        if (accessToken == null || accessToken.length() == 0)
            return accessToken;
        return Constants.TOKEN_PREFIX + accessToken;
    }

    /**
     * Retrieves the refresh token stored on shared preferences. If the stored token has expired, returns an empty string
     *
     * @return the refresh token
     */
    public String getRefreshToken() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String refreshToken = sharedPref.getString(Constants.REFRESH_TOKEN_VALUE, null);
        if (refreshToken == null || refreshToken.length() == 0)
            return null;
        long expiration = sharedPref.getLong(Constants.REFRESH_TOKEN_EXPIRATION, 0);
        if(expiration > System.currentTimeMillis())
            return refreshToken;
        else
            return Constants.EMPTY_STRING;
    }

    /**
     * Store access and refresh token values on shared preferences.
     *
     * @param accessToken the token object with access and refresh token values
     */
    public void storeToken(AccessToken accessToken) {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        long accessTokenExpiration = System.currentTimeMillis() + ((long) (accessToken.getExpiresIn() * 1000));
        editor.putLong(Constants.ACCESS_TOKEN_EXPIRATION, accessTokenExpiration);
        editor.putString(Constants.ACCESS_TOKEN_VALUE, accessToken.getAccessToken());
        long refreshTokenExpiration = accessTokenExpiration + Constants.MONTH_TO_MILLISECONDS;
        editor.putLong(Constants.REFRESH_TOKEN_EXPIRATION, refreshTokenExpiration);
        editor.putString(Constants.REFRESH_TOKEN_VALUE, accessToken.getRefreshToken());
        editor.apply();
    }

    /**
     * Retrieves the basic auth token ("Basic " prefix + API_KEY) stored on shared preferences.
     *
     * @return the basic auth token
     */
    public String getBasicAuthToken() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String apiKey = sharedPref.getString(Constants.API_KEY_VALUE, null);
        return (apiKey != null) ? Constants.BASIC_AUTH_PREFIX + apiKey : apiKey;
    }

    /**
     * Store API key value on shared preferences.
     *
     * @param apiKey the API key value
     */
    public void storeApiKey(String apiKey) {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.API_KEY_VALUE, apiKey);
        editor.apply();
    }

    /**
     * Retrieves the device ID value stored on shared preferences.
     *
     * @return the device ID value
     */
    public String getDeviceId() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.DEVICE_ID_VALUE, null);
    }

    /**
     * Store device ID value on shared preferences.
     *
     * @param deviceId the device ID value
     */
    public void storeDeviceId(String deviceId) {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.DEVICE_ID_VALUE, deviceId);
        editor.apply();
    }

    /**
     * Retrieves the categories value stored on shared preferences.
     *
     * @return the categories
     */
    public String getCategories() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.CATEGORIES_VALUE, null);
    }

    /**
     * Store device ID value on shared preferences.
     *
     * @param categories the categories
     */
    public void storeCategories(String categories) {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.CATEGORIES_VALUE, categories);
        editor.apply();
    }

    /**
     * Retrieves the categories value stored on shared preferences.
     *
     * @return the categories
     */
    public boolean getCategoriesVisible() {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(Constants.CATEGORIES_VALUE, true);
    }

    /**
     * Store device ID value on shared preferences.
     *
     * @param categories the categories
     */
    public void storeCategoriesVisible(boolean categories) {
        SharedPreferences sharedPref = appContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Constants.CATEGORIES_VISIBLE_VALUE, categories);
        editor.apply();
    }
}
