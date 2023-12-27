# Spring Boot Exercise
A REST API for manipulating artworks such as paintings, sculptures, etc.

## Requirements
* Java 17
* Maven
* Spring Boot dependencies

## Endpoints
### Public
|HTTP method|Endpoint|Description|
|:---|:---|:---|
|`GET`|/api/v0/artworks|Gets all artworks. With a query string you can: 1) filter by any `Artwork` field 2) sort by `institution`, `author`, `date`, `materials` 3) set page number, page size. An exceptions is thrown if the specified sort property is not an `Artwork` field.|
|`GET`|/api/v0/artworks/`id`|Gets an artwork object or throws an exception if it is not found.|
|`GET`|/api/v0/artworks/`institution`/`invNum`|Alternative for getting an artwork by id, but this time it is found by combining two parameters unique for every artwork.|

### Admin
|HTTP method|Endpoint|Description|
|:---|:---|:---|
|`POST`|/api/admin/v0/artworks|Creates an artwork or throws an exception if the artwork is created without the following parameters: `institution`, `invNum`.|
|`PUT`|/api/admin/v0/artworks/`id`|Updates an artwork or, if it does not exist, creates a new one.|
|`DELETE`|/api/admin/v0/artworks/`id`|Deletes an artwork.|
