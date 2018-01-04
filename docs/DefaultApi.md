# DefaultApi

All URIs are relative to *https://rest.dive.tv/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteLikes**](DefaultApi.md#deleteLikes) | **DELETE** /likes/{card_id} | Delete card like
[**getCard**](DefaultApi.md#getCard) | **GET** /cards/{card_id} | Full card detail
[**getCardVersion**](DefaultApi.md#getCardVersion) | **GET** /cards/{card_id}/versions/{version} | Full card detail with version
[**getCatalogMovie**](DefaultApi.md#getCatalogMovie) | **GET** /movies/{client_movie_id} | Movie catalog info
[**getChannelGrid**](DefaultApi.md#getChannelGrid) | **GET** /channels/{channel_id}/grid | Channel events grid
[**getChannelMovie**](DefaultApi.md#getChannelMovie) | **GET** /channels/{channel_id} | Channel movie catalog info
[**getLikes**](DefaultApi.md#getLikes) | **GET** /likes | Get card likes
[**getReadyChannels**](DefaultApi.md#getReadyChannels) | **GET** /channels/{channel_id_list}/ready | Channel sync availability
[**getReadyMovies**](DefaultApi.md#getReadyMovies) | **GET** /movies/{client_movie_id_list}/ready | Movie sync availability
[**getStaticChannelScene**](DefaultApi.md#getStaticChannelScene) | **GET** /channels/{channel_id}/static | Static channel scene
[**getStaticMovieScene**](DefaultApi.md#getStaticMovieScene) | **GET** /movies/{client_movie_id}/static/{timestamp} | Static VOD scene
[**postLikes**](DefaultApi.md#postLikes) | **POST** /likes/{card_id} | Add card like
[**postToken**](DefaultApi.md#postToken) | **POST** /token | Token endpoint


<a name="deleteLikes"></a>
# **deleteLikes**
> deleteLikes(authorization, cardId, acceptLanguage)

Delete card like

Removes a card from current user&#39;s likes list

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String cardId = "cardId_example"; // String | Requested card ID
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
try {
    apiInstance.deleteLikes(authorization, cardId, acceptLanguage);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteLikes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **cardId** | **String**| Requested card ID |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getCard"></a>
# **getCard**
> Card getCard(authorization, cardId, acceptLanguage, products, userData, imageSize)

Full card detail

Retrieves a full card detail, with no relations or context

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String cardId = "cardId_example"; // String | Unique identifier for this card
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
Boolean products = true; // Boolean | Indicates if cards should contain commercial products when available (true) or not (false)
Boolean userData = true; // Boolean | Indicates if cards should contain user data when available (true) or not (false)
String imageSize = "m"; // String | Size of the images returned in the response
try {
    Card result = apiInstance.getCard(authorization, cardId, acceptLanguage, products, userData, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getCard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **cardId** | **String**| Unique identifier for this card |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **products** | **Boolean**| Indicates if cards should contain commercial products when available (true) or not (false) | [optional] [default to true]
 **userData** | **Boolean**| Indicates if cards should contain user data when available (true) or not (false) | [optional] [default to true]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**Card**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCardVersion"></a>
# **getCardVersion**
> Card getCardVersion(authorization, cardId, version, acceptLanguage, products, userData, relations, imageSize)

Full card detail with version

Retrieves a full card detail, and its relations to other cards in a given context (card version)

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String cardId = "cardId_example"; // String | Unique identifier for this card
String version = "version_example"; // String | Version identifier, indicates the context where the card is being requested
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
Boolean products = true; // Boolean | Indicates if cards should contain commercial products when available (true) or not (false)
Boolean userData = true; // Boolean | Indicates if cards should contain user data when available (true) or not (false)
Boolean relations = true; // Boolean | Indicates if cards should contain the scene hierarchy (true) or not (false)
String imageSize = "m"; // String | Size of the images returned in the response
try {
    Card result = apiInstance.getCardVersion(authorization, cardId, version, acceptLanguage, products, userData, relations, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getCardVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **cardId** | **String**| Unique identifier for this card |
 **version** | **String**| Version identifier, indicates the context where the card is being requested |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **products** | **Boolean**| Indicates if cards should contain commercial products when available (true) or not (false) | [optional] [default to true]
 **userData** | **Boolean**| Indicates if cards should contain user data when available (true) or not (false) | [optional] [default to true]
 **relations** | **Boolean**| Indicates if cards should contain the scene hierarchy (true) or not (false) | [optional] [default to true]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**Card**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCatalogMovie"></a>
# **getCatalogMovie**
> Card getCatalogMovie(authorization, clientMovieId, acceptLanguage, imageSize)

Movie catalog info

Retrieves a movie&#39;s full card by its client ID, including catalog and cast information

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String clientMovieId = "clientMovieId_example"; // String | Client movie ID being played
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
String imageSize = "m"; // String | Size of the images returned in the response
try {
    Card result = apiInstance.getCatalogMovie(authorization, clientMovieId, acceptLanguage, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getCatalogMovie");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **clientMovieId** | **String**| Client movie ID being played |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**Card**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getChannelGrid"></a>
# **getChannelGrid**
> TvGrid getChannelGrid(authorization, channelId, acceptLanguage, imageSize)

Channel events grid

Returns the current and upcoming grid of TV events for the given channel

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String channelId = "channelId_example"; // String | Client channel ID
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
String imageSize = "m"; // String | Size of the images returned in the response
try {
    TvGrid result = apiInstance.getChannelGrid(authorization, channelId, acceptLanguage, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getChannelGrid");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **channelId** | **String**| Client channel ID |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**TvGrid**](TvGrid.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getChannelMovie"></a>
# **getChannelMovie**
> Card getChannelMovie(authorization, channelId, acceptLanguage, imageSize)

Channel movie catalog info

Retrieves full card detail, including catalog and cast information, for the content currently being broadcasted on the channel

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String channelId = "channelId_example"; // String | Client channel ID
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
String imageSize = "m"; // String | Size of the images returned in the response
try {
    Card result = apiInstance.getChannelMovie(authorization, channelId, acceptLanguage, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getChannelMovie");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **channelId** | **String**| Client channel ID |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**Card**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getLikes"></a>
# **getLikes**
> List&lt;Card&gt; getLikes(authorization, acceptLanguage, imageSize, paginateKey, size)

Get card likes

Returns a paginated list of cards liked by current user

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
String imageSize = "m"; // String | Size of the images returned in the response
String paginateKey = "paginateKey_example"; // String | Paginate key
String size = "20"; // String | Number of desired results
try {
    List<Card> result = apiInstance.getLikes(authorization, acceptLanguage, imageSize, paginateKey, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getLikes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]
 **paginateKey** | **String**| Paginate key | [optional]
 **size** | **String**| Number of desired results | [optional] [default to 20]

### Return type

[**List&lt;Card&gt;**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getReadyChannels"></a>
# **getReadyChannels**
> List&lt;ChannelStatus&gt; getReadyChannels(authorization, channelIdList, acceptLanguage)

Channel sync availability

Checks if a list of client channel identifiers are currently broadcasting synchronizable content

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
List<String> channelIdList = Arrays.asList("channelIdList_example"); // List<String> | List of client channel IDs as a comma separated list
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
try {
    List<ChannelStatus> result = apiInstance.getReadyChannels(authorization, channelIdList, acceptLanguage);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getReadyChannels");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **channelIdList** | [**List&lt;String&gt;**](String.md)| List of client channel IDs as a comma separated list |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]

### Return type

[**List&lt;ChannelStatus&gt;**](ChannelStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getReadyMovies"></a>
# **getReadyMovies**
> List&lt;MovieStatus&gt; getReadyMovies(authorization, clientMovieIdList, acceptLanguage)

Movie sync availability

Checks whether a list of client movie identifiers (Video On Demand) are available to be synchronized using the Dive API

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
List<String> clientMovieIdList = Arrays.asList("clientMovieIdList_example"); // List<String> | List of client movie IDs, provided as a comma separated list
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
try {
    List<MovieStatus> result = apiInstance.getReadyMovies(authorization, clientMovieIdList, acceptLanguage);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getReadyMovies");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **clientMovieIdList** | [**List&lt;String&gt;**](String.md)| List of client movie IDs, provided as a comma separated list |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]

### Return type

[**List&lt;MovieStatus&gt;**](MovieStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getStaticChannelScene"></a>
# **getStaticChannelScene**
> List&lt;Card&gt; getStaticChannelScene(authorization, channelId, acceptLanguage, relations, imageSize)

Static channel scene

Retrieves the list of cards related to the content currently being broadcasted in the provided channel

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String channelId = "channelId_example"; // String | Client channel ID
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
Boolean relations = true; // Boolean | Indicates if cards should contain the scene hierarchy (true) or not (false)
String imageSize = "m"; // String | Size of the images returned in the response
try {
    List<Card> result = apiInstance.getStaticChannelScene(authorization, channelId, acceptLanguage, relations, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getStaticChannelScene");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **channelId** | **String**| Client channel ID |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **relations** | **Boolean**| Indicates if cards should contain the scene hierarchy (true) or not (false) | [optional] [default to true]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**List&lt;Card&gt;**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getStaticMovieScene"></a>
# **getStaticMovieScene**
> List&lt;Card&gt; getStaticMovieScene(authorization, clientMovieId, timestamp, acceptLanguage, relations, imageSize)

Static VOD scene

Retrieves the current list of cards related to the given movie scene

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String clientMovieId = "clientMovieId_example"; // String | Client movie ID being played
Float timestamp = 3.4F; // Float | Current movie timestamp in seconds
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
Boolean relations = true; // Boolean | Indicates if cards should contain the scene hierarchy (true) or not (false)
String imageSize = "m"; // String | Size of the images returned in the response
try {
    List<Card> result = apiInstance.getStaticMovieScene(authorization, clientMovieId, timestamp, acceptLanguage, relations, imageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getStaticMovieScene");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **clientMovieId** | **String**| Client movie ID being played |
 **timestamp** | **Float**| Current movie timestamp in seconds |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **relations** | **Boolean**| Indicates if cards should contain the scene hierarchy (true) or not (false) | [optional] [default to true]
 **imageSize** | **String**| Size of the images returned in the response | [optional] [default to m] [enum: s, m, l]

### Return type

[**List&lt;Card&gt;**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="postLikes"></a>
# **postLikes**
> postLikes(authorization, cardId, acceptLanguage)

Add card like

Stores a card under current user&#39;s likes list

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Authorization token ('Bearer <token>')
String cardId = "cardId_example"; // String | Requested card ID
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
try {
    apiInstance.postLikes(authorization, cardId, acceptLanguage);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#postLikes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Authorization token (&#39;Bearer &lt;token&gt;&#39;) |
 **cardId** | **String**| Requested card ID |
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="postToken"></a>
# **postToken**
> AccessToken postToken(authorization, grantType, acceptLanguage, deviceId, refreshToken)

Token endpoint

The token endpoint is used to obtain access tokens which allow clients to make API requests

### Example
```java
// Import classes:
//import com.touchvie.sdk.ApiException;
//import com.touchvie.sdk.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String authorization = "authorization_example"; // String | Basic authorization token ('Basic <client_key>')
String grantType = "grantType_example"; // String | Grant type used to obtain the token.
String acceptLanguage = "acceptLanguage_example"; // String | Client locale, as language-country
String deviceId = "deviceId_example"; // String | Device identifier, must uniquely identify the user or device accessing the API. Required only for \"device_credentials\" grant type
String refreshToken = "refreshToken_example"; // String | Refresh token, used to issue a new token without resending client credentials. Required only for \"refresh_token\" grant type
try {
    AccessToken result = apiInstance.postToken(authorization, grantType, acceptLanguage, deviceId, refreshToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#postToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Basic authorization token (&#39;Basic &lt;client_key&gt;&#39;) |
 **grantType** | **String**| Grant type used to obtain the token. | [enum: device_credentials, refresh_token]
 **acceptLanguage** | **String**| Client locale, as language-country | [optional]
 **deviceId** | **String**| Device identifier, must uniquely identify the user or device accessing the API. Required only for \&quot;device_credentials\&quot; grant type | [optional]
 **refreshToken** | **String**| Refresh token, used to issue a new token without resending client credentials. Required only for \&quot;refresh_token\&quot; grant type | [optional]

### Return type

[**AccessToken**](AccessToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

