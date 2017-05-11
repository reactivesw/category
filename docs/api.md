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
| slug | String | required, NotNull, Allowed are alphabetic, numeric, underscore (_) and hyphen (-) characters. Maximum size is 256, Minimum size is 2 |
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
| ancestors | List\<Reference\> |  Contains the parent path towards the root category. From the root category to the parent path by order. |
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
| previousOrderHint | String | required, NotNull |
| nextOrderHint | String | if category is changed to be the last one, this paremeter should be empty, otherwise, required |

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
| offset | Integer | For now, this field is null. |
| count | Integer | Result count. |
| total | Integer | For now, this field is null. |
| results | List\<T\> | All view model in this field. |
| facets | Object | For now, this field is null. |

### Reference

| field name | field type | comments |
|-|-|-|
| typeId | String |  The typeId designates the type of the referenced resource (see the list of reference types below). |
| id | String | required. The unique ID of the referenced resource. |

example:

```json
{
  "typeId": "category",
  "id": "d1229e6f-2b79-441e-b419-180311e52754"
}
```

### Reference Types

* `cart` - Identifies a reference to a Cart.
* `category` - Identifies a reference to a Category.
* `payment` - Identifies a reference to a Payment.
* `product` - Identifies a reference to a Product.
* `product-type` - Identifies a reference to a ProductType.
* `order` - Identifies a reference to an Order.

## API

### create category

* Url: {category service url}/
* method: POST
* request body: category - CategoryDraft - required
* response:
  
  | http code | body | comment |
  |---|---|---|
  | 200 | CategoryView | create success and return created category |
  | 400 | null | category is null, can not create this category |
  | 409 | null | can not create category when the name is already exist |

* payload sample:

```json
{
  "name": {
    "en": "testcategory"
  },
  "description": {
    "en": "category named as clothes"
  },
  "slug": "testcategory"
}
```

ps: name and slug should be unique.

### delete category

* Url: {category service url}/{categoryId}
* method: DELETE
* request param: version - Integer - required
* response:

  | http code | body | comment |
  |---|---|---|
  | 200 | null | find and delete this category success |
  | 404 | null | can not find this category |

### update category

* Url: {category service url}/{categoryId}
* method: PUT
* request body: updateRequest - UpdateRequest - required
* response:

  | http code | body | comment |
  |---|---|---|
  | 200 | CategoryView | update category success |
  | 404 | null | can not find this category |
  | 409 | null | version not match |

* payload sample:

```json
{
  "version": 1,
  "actions": [
    {
      "action": "setName",
      "name": {
        "en": "Pro T-Shirt"
      }
    },
    {
      "action": "setSlug",
      "slug": "f9779820-b282-4f62-8489-0d5ff1415a98"
    }
  ]
}
```

### get category by id

* Url: {category service url}/{categoryId}
* method: GET
* response:

  | http code | body | comment |
  |---|---|---|
  | 200 | CategoryView | find category success |
  | 404 | null | can not find this category |

### query category

* Url: {category service url}/
* method: GET
* request: query - QueryConditions - not required
* response: PagedQueryResult\<CategoryView\>

#### Special Note

1. if category is null, will return like this:

```json
{
    "offset": null,
    "count": 0,
    "total": null,
    "results": [],
    "facets": null
}
```

2. category order in result

Because there is an orderHint in category, the result will sort by the orderHint, smaller is prioritized.
For example:
 
```json
{
  "offset": null,
  "count": 3,
  "total": null,
  "results": [
    {
      "name": {
        "en": "testcategory1"
      },
      "orderHint": 0.01
    },
    {
      "name": {
        "en": "testcategory2"
      },
      "orderHint": 0.02
    },
    {
      "name": {
        "en": "testcategory3"
      },
      "orderHint": 0.03
    }
  ],
  "facets": null
}
```

ps: For now, we have not completed paged query, so calling this api will return all category in result.
