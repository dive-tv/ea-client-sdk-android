
# Card

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cardId** | **String** | Card unique identifier | 
**version** | **String** | Card version for a given movie and scene |  [optional]
**type** | [**TypeEnum**](#TypeEnum) | Card type | 
**locale** | **String** | Card content locale | 
**title** | **String** | Card title | 
**subtitle** | **String** | Card subtitle (might be empty) |  [optional]
**image** | [**ImageData**](ImageData.md) |  |  [optional]
**hasContent** | **Boolean** | Indicates if the card has additional browsable content |  [optional]
**info** | [**List&lt;CardContainer&gt;**](CardContainer.md) |  |  [optional]
**products** | [**List&lt;Product&gt;**](Product.md) | Commercial products associated to the card |  [optional]
**relations** | [**List&lt;RelationModule&gt;**](RelationModule.md) |  |  [optional]
**user** | [**CardUser**](CardUser.md) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
MOVIE | &quot;movie&quot;
SERIE | &quot;serie&quot;
PERSON | &quot;person&quot;
CHARACTER | &quot;character&quot;
VEHICLE | &quot;vehicle&quot;
FASHION | &quot;fashion&quot;
LOCATION | &quot;location&quot;
HISTORIC | &quot;historic&quot;
TRIVIA | &quot;trivia&quot;
QUOTE | &quot;quote&quot;
OST | &quot;ost&quot;
HOME | &quot;home&quot;
TECHNOLOGY | &quot;technology&quot;
ART | &quot;art&quot;
SONG | &quot;song&quot;
LOOK | &quot;look&quot;
WEAPON | &quot;weapon&quot;
LEISURE_SPORT | &quot;leisure_sport&quot;
HEALTH_BEAUTY | &quot;health_beauty&quot;
FOOD_DRINK | &quot;food_drink&quot;
FAUNA_FLORA | &quot;fauna_flora&quot;
BUSINESS | &quot;business&quot;
REFERENCE | &quot;reference&quot;
CHAPTER | &quot;chapter&quot;



