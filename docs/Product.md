
# Product

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**productId** | **String** | Product identifier | 
**category** | [**CategoryEnum**](#CategoryEnum) | Merchant category | 
**source** | [**SourceData**](SourceData.md) |  |  [optional]
**title** | **String** | Product title | 
**image** | **String** | Product image URL | 
**url** | **String** | Merchant store URL | 
**price** | **Double** | Product price |  [optional]
**currency** | **String** | Product price currency |  [optional]
**isUpToDate** | **Boolean** | Indicates if product in up to date on Dive database |  [optional]
**isExact** | **Boolean** | Indicates if product is an exact match |  [optional]
**country** | **String** | Country information. Only for travel products |  [optional]
**region** | **String** | Region information. Only for travel products |  [optional]
**city** | **String** | City information. Only for travel products |  [optional]
**address** | **String** | Address information. Only for travel products |  [optional]
**postalCode** | **String** | Postal code information. Only for travel products |  [optional]
**rating** | **Double** | Rating information. Only for travel products |  [optional]


<a name="CategoryEnum"></a>
## Enum: CategoryEnum
Name | Value
---- | -----
FASHION | &quot;fashion&quot;
ELECTRO | &quot;electro&quot;
MERCH | &quot;merch&quot;
TRAVEL | &quot;travel&quot;



