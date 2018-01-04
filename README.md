# ea-client-sdk-android
Dive Experience Amplifier client SDK for Android

## Introduction

Dive provides a series of SDK clients for the most common client programming languages which can be added as libraries on the client side.

The client SDK library manages communications with the REST API services and handle authentication and serialization / deserialization of data into native objects.


NOTE: this document is being updated on a regular base and contents are subject to change.

## Integration Methods

The following sections describe the different functions that SDK contains to integrate a client SW using Dive Front SDK.

- Authentication details are provided in the library initialization
- API calls are performed calling library methods
- Response statuses and objects are mapped to native objects of the library implementation language.

### Delete card like
````java
deleteLikes(cardId, callback)
````
Removes a card from current user's likes list

#### Parameters:

Class | Method | HTTP request 
------------ | ------------- | ------------- 
*cardId* | *String* | *Requested card ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String cardId = "cardId_example"; // String | Requested card ID
ClientCallback<List<Card>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    SdkClient.newInstance().deleteLikes(cardId, callback);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#deleteLikes");
    e.printStackTrace();
}

````

### Full card detail
````java
Card getCard(cardId, accepLanguage, callback)
````
Retrieves a full card detail, with no relations or context

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*cardId* | *String* | *Requested card ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
[Card](docs/Card.md)

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String cardId = "cardId_example"; // String | Unique identifier for this card
ClientCallback<Card> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};
try {
    Card result = SdkClient.getInstance().getCard(cardId, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getCard");
    e.printStackTrace();
}
````

### Full card detail with versions
````java
Card getCardVersion(cardId, version, callback)
````
Retrieves a full card detail, and its relations to other cards in a given context (card version)

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*cardId* | *String* | *Requested card ID* 
*version* | *String* | *Version identifier, indicates the context where the card is being requested*
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
[Card](docs/Card.md)

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String cardId = "cardId_example"; // String | Unique identifier for this card
String version = "version_example"; // String | Version identifier, indicates the context where the card is being requested
ClientCallback<Card> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};
try {
    Card result = SdkClient.getInstance().getCardVersion(cardId, version, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getCardVersion");
    e.printStackTrace();
}
````

### Movie catalog info
````java
Card getCatalogMovie(clientMovieId, callback)
````
Retrieves a movie's full card by its client ID, including catalog and cast information

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*clientMovieId* | *String* | *Client movie ID being played* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
[Card](docs/Card.md)

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String clientMovieId = "clientMovieId_example"; // String | Client movie ID being played
ClientCallback<ListCard> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    Card result = SdkClient.getInstance().getCatalogMovie(clientMovieId, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getCatalogMovie");
    e.printStackTrace();
}
````

### Channel events grid
````java
TvGrid getChannelGrid(channelId, callback)
````
Returns the current and upcoming grid of TV events for the given channel

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*channelId* | *String* | *Client channel ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
[TvGrid](docs/TvGrid.md)

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String channelId = "channelId_example"; // String | Client channel ID
Float timestamp = 3.4F; // Float | Current movie timestamp in seconds
ClientCallback<TvGrid> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    TvGrid result = SdkClient.getInstance().getChannelGrid(channelId, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaulSdkClienttApi#getChannelGrid");
    e.printStackTrace();
}
````

### Channel movie catalog info
````java
Card getChannelMovie(channelId, callback)
````
Retrieves full card detail, including catalog and cast information, for the content currently being broadcasted on the channel

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*channelId* | *String* | *Client channel ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
[Card](docs/Card.md)

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String channelId = "channelId_example"; // String | Client channel ID
ClientCallback<Card> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    Card result = SdkClient.getInstance().getChannelMovie(channelId, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getChannelMovie");
    e.printStackTrace();
}
````

### Get card likes
````java
List<Card> getLikes(imageSize, paginateKey, size, callback)
````
Returns a paginated list of cards liked by current user

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- | -------------  
*imageSize* | *String* | *Size of the images returned in the response* | *(optional) (default to true)[enum: s, m, l]*
*paginateKey* | *String* | *Paginate key* | *[optional]*
*size* | *String* | *Number of desired results* | *(optional) (default to 20)*
*callback* | *ClientCallBack* | *Callback for receive response* | 

#### Return:
Liat<[Card](docs/Card.md)>

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String imageSize = "m"; // String | Size of the images returned in the response
String paginateKey = "paginateKey_example"; // String | Paginate key
String size = "20"; // String | Number of desired results
ClientCallback<List<Card>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    List<Card> result = SdkClient.getInstance().getLikes(imageSize, paginateKey, size, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getLikes");
    e.printStackTrace();
}
````

### Channel sync availability
````java
List<ChannelStatus> getReadyChannels(channelIdList, callback)
````
Checks if a list of client channel identifiers are currently broadcasting synchronizable content

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*channelIdList* | *List<String>* | *List of client channel IDs as a comma separated list* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
List<[ChannelStatus](docs/ChannelStatus.md)>

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
List<String> channelIdList = Arrays.asList("channelIdList_example"); // List<String> | List of client channel IDs as a comma separated list
ClientCallback<List<ChannelStatus>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    List<ChannelStatus> result = apiInstance.getReadyChannels(channelIdList, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getReadyChannels");
    e.printStackTrace();
}
````

### Movie sync availability
````java
List<MovieStatus> getReadyMovies(clientMovieIdList, callback)
````
Checks whether a list of client movie identifiers (Video On Demand) are available to be synchronized using the Dive API

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*clientMovieIdList* | *List<String>* | *List of client movie IDs, provided as a comma separated list* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
List<[MovieStatus](docs/MovieStatus.md)>

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
List<String> clientMovieIdList = Arrays.asList("clientMovieIdList_example"); // List<String> | List of client movie IDs, provided as a comma separated list
ClientCallback<List<MovieStatus>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    List<MovieStatus> result = sdkClient.getInstance().getReadyMovies(clientMovieIdList, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getReadyMovies");
    e.printStackTrace();
}
````

### Static channel scene
````java
List<Card> getStaticChannelScene(channelId, callback)
````
Retrieves the list of cards related to the content currently being broadcasted in the provided channel

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*channelId* | *String* | *Client channel ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
List<[Card](docs/Card.md)>

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String channelId = "channelId_example"; // String | Client channel ID
ClientCallback<List<Card>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    List<Card> result = SdkClient.getInstance().getStaticChannelScene(channelId, callback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#getStaticChannelScene");
    e.printStackTrace();
}
````

### Stream Connect
````java
vodStreamConnect( clientMovieId, timestamp, listener)
````
Connect a Socket for receive carousel

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*clientMovieId* | *String* | *Requested movie ID* 
*timestamp* | *Float* | *Current movie timestamp in seconds* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String movieId = "movieId_example"; // String | Requested card ID
int movieTime = movieTime;
ClientCallback<List<Card>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    SdkClient.getInstance().vodStreamConnect(movieId, movieTime, callback);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#vodStreamConnect");
    e.printStackTrace();
}
````

### Stream Play
````java
vodStreamSetMessage( timestamp)
````
Send play event to VOD Socket

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*timestamp* | *Float* | *time of movie start* 

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
int movieTime = movieTime;

try {
    SdkClient.getInstance().vodStreamSetMessage(movieTime);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#vodStreamSetMessage");
    e.printStackTrace();
}
````

### Stream Pause
````java
vodStreamPauseMessage( timestamp)
````
Send pause event to VOD Socket

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*timestamp* | *Float* | *time of movie Pause* 

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
int movieTime = movieTime;

try {
    SdkClient.getInstance().vodStreamPauseMessage(movieTime);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#vodStreamPauseMessage");
    e.printStackTrace();
}
````


### Stream Continue
````java
vodStreamContinueMessage( timestamp)
````
Send continue event to VOD Socket

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*timestamp* | *Float* | *time of movie Continue* 

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
int movieTime = movieTime;

try {
    SdkClient.getInstance().vodStreamContinueMessage(movieTime);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#vodStreamContinueMessage");
    e.printStackTrace();
}
````


### Stream End
````java
vodStreamEndMessage( timestamp)
````
Send end event to VOD Socket

#### Parameters:

N/A

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);

try {
    SdkClient.getInstance().vodStreamEndMessage();
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#vodStreamEndMessage");
    e.printStackTrace();
}
````

### Add card like
````java
postLikes(cardId, callback)
````
Stores a card under current user's likes list

#### Parameters:

Name | Type | Description 
------------ | ------------- | ------------- 
*cardId* | *String* | *Requested card ID* 
*callback* | *ClientCallBack* | *Callback for receive response*

#### Return:
N/A

#### Example:
````java
String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), apiKey ,deviceId);
String cardId = "cardId_example"; // String | Requested card ID
ClientCallback<List<Card>> callback = = new ClientCallback() {
   @Override
   public void onFailure(ApiError apiError) {

   }

   @Override
   public void onSuccess(Object o) {
              }
   }
};

try {
    SdkClient.getInstance().postLikes(cardId, callback);
} catch (ApiException e) {
    System.err.println("Exception when calling SdkClient#postLikes");
    e.printStackTrace();
}
````

## Documentation for Models
 - [AuthError](docs/AuthError.md)
 - [AwardData](docs/AwardData.md)
 - [AwardsData](docs/AwardsData.md)
 - [Card](docs/Card.md)
 - [CardContainer](docs/CardContainer.md)
 - [CardUser](docs/CardUser.md)
 - [CatalogData](docs/CatalogData.md)
 - [CatalogSync](docs/CatalogSync.md)
 - [ChannelStatus](docs/ChannelStatus.md)
 - [DupleData](docs/DupleData.md)
 - [ImageData](docs/ImageData.md)
 - [LinkData](docs/LinkData.md)
 - [ListingData](docs/ListingData.md)
 - [MapData](docs/MapData.md)
 - [MovieStatus](docs/MovieStatus.md)
 - [Product](docs/Product.md)
 - [RatingData](docs/RatingData.md)
 - [RelationModule](docs/RelationModule.md)
 - [SeasonsChapters](docs/SeasonsChapters.md)
 - [SeasonsData](docs/SeasonsData.md)
 - [SourceData](docs/SourceData.md)
 - [TextData](docs/TextData.md)
 - [TvEvent](docs/TvEvent.md)
 - [TvGrid](docs/TvGrid.md)
 - [Awards](docs/Awards.md)
 - [Catalog](docs/Catalog.md)
 - [Duple](docs/Duple.md)
 - [Image](docs/Image.md)
 - [Link](docs/Link.md)
 - [Listing](docs/Listing.md)
 - [Map](docs/Map.md)
 - [Rating](docs/Rating.md)
 - [Seasons](docs/Seasons.md)
 - [Single](docs/Single.md)
 - [Text](docs/Text.md)


## How to use
- Include SDK Client  and SDK Front to a project

    - Add dependency into your app build.gradle file

        ```java
        
        dependencies {
            ...
           compile 'com.github.dive-tv:ea-client-sdk-android:1.0.6'
           compile 'com.github.dive-tv:sdk-client-java:1.0.6'
        
        }
        ```
    - Add repository into your app build.gradle file
        ```java
        repositories {
            ...
            jcenter()
            maven { url "https://jitpack.io" } 
        }
        ```
    - Add repository into your root build.gradle file
        ````java
        dependencies {
            classpath 'com.github.dcendents:android-maven-gradle-plugin:2.0' 
        }
        ````

- Code example:
````java
import sdk.client.dive.tv.SdkClient;

public class MainActivity extends Activity {
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ...
String deviceId = XXXX; Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
SdkClient.getOrCreateInstance(getApplicationContext(), YOUR_API_KEY,deviceId);
   }
}

````
- Modify YOUR_API_KEY by provided value
- deviceId value should be your unique client identifier


## Author



