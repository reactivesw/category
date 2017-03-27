# Category API Design

## Introduction

TODO

## View Model

### CategoryDraft

| field name | field type | comments |
|-|-|-|
| name | LocalizedString | required, NotNull |
| description | LocalizedString | |
| parent | Reference | |
| slug | String | required, NotNull, pattern is "[-a-zA-Z0-9_]{2,256}" |
| orderHint | String | |
| externalId | String | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | |
| metaKeywords | LocalizedString | |

### CategoryView

| field name | field type | comments |
|-|-|-|
| id | String | |
| version | Integer | |
| createdAt | ZonedDateTime | |
| lastModifiedAt | ZonedDateTime | |
| name | LocalizedString | |
| description | LocalizedString | |
| slug | String | |
| ancestors | List\<Reference\> | |
| parent | Reference | |
| orderHint | String | |
| externalId | String | |
| metaTitle | LocalizedString | |
| metaDescription | LocalizedString | |
| metaKeywords | LocalizedString | |

### UpdateRequest

| field name | field type | comments |
|-|-|-|
| version | Integer | required, NotNull, min is 0 |
| actions | List\<UpdateAction\> | required, NotNull |

### Update Action

#### SetName

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setName` |
| name | LocalizedString | required, NotNull |

#### SetDescription

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setDescription` |
| description | LocalizedString | If not defined, the description is unset |

#### SetExternalID

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setExternalId` |
| externalId | String | If not defined, the external ID is unset |

#### SetMetaDescription

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setMetaDescription` |
| metaDescription | LocalizedString | If not defined, the metaDescription is unset |

#### SetMetaKeywords

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setMetaKeywords` |
| metaKeywords | LocalizedString | If not defined, the metaKeywords is unset |

#### SetMetaTitle

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setMetaTitle` |
| metaTitle | LocalizedString | If not defined, the metaTitle is unset |

#### SetOrderHint

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setOrderHint` |
| orderHint | String | required, NotNull |

#### SetParent

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setParent` |
| parent | Reference to `Category` | |

#### SetSlug

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setSlug` |
| slug | String | required, NotNull, pattern is "[-a-zA-Z0-9_]{2,256}" |

### PagedQueryResult

| field name | field type | comments |
|-|-|-|
| offset | Integer | |
| count | Integer | |
| total | Integer | |
| results | List\<T\> | |
| facets | Object | |

## API

### create category

* Url : {category service url}/
* method : POST
* request body : category - CategoryDraft - required
* response : CategoryView

### delete category

* Url : {category service url}/{categoryId}
* method : DELETE
* request param : version - Integer - required
* response : void

### update category

* Url : {category service url}/{categoryId}
* method : PUT
* request body : updateRequest - UpdateRequest - required
* response : CategoryView

### get category by id

* Url : {category service url}/{categoryId}
* method : GET
* response : CategoryView

### query category

* Url : {category service url}/
* method : GET
* request : query - QueryConditions - not required
* response : PagedQueryResult\<CategoryView\>