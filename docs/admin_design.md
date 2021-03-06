# Category Design for Admin-Web

This document describes how to achieve the admin-web [requirement](./admin-requirement.md).

## 1. Basic Functions

Category service provides following functions:

+ create category
+ delete category
+ update category
+ get individual category
+ get all categories

## 2. Model Design

For category, there is 3 special requirements:

* order

  a new category will be the last one in category list. order of category can be changed.

* multilevel

    a category can have one parent category. each root category and its descendents form a tree structure.

* multi-language

    name, description, meta title, meta description, meta keywords of category should be multiple language.

Following is how to achieve those requirements.

### 2.1 OrderHint Design

In category model, we store a string named `orderHint` and use it to sort
category in one level. An `orderHint` is a string, the context is the value of 0
to 1 float data.

When create a category, will create an `orderHint` to it by system time, here is
the rule:

1. get system current time in milliseconds, like: 14919906819555.

2. convert step 1 value to float data, 14919906819555 -> 0.14919906819555.

3. store step 2 value to `orderHint` in string.

When update category order, what we need to do is update one category
`orderHint` and others don't need to change, here is the rule:

1. get the previous category `orderHint` and behind one category `orderHint`, calculate the median of two `orderHint`, example:

   ```
   the first one orderHint: 0.149199068195555
   the after one orderHint: 0.149199068195556

   the result: (0.149199068195555 + 0.149199068195556) / 2 = 0.1491990681955555
   ```
2. if change the category to the first one, the new `orderHint` will be the median of the original first one `orderHint` and `0`, example:

    ```
    the orginal first one orderHint: 0.149199068195555

    the result: (0.149199068195555 + 0) / 2 = 0.074599534097775
    ```

3. if change the category to the last one, the new `orderHint` will be
   regenerated by server, so just the orderHint of last category will be sent to
   server which determines the category is changed to the last one. For example:

   ```
   the original last one orderHint: 0.1492428478697
   the result: 0.149266079805(recreate by server)
   ```

### 2.2 Multilevel Design

A category could at most have one parent category. If a category has no parent
category, this category is a root category. When creating a new category, its
parent could be added in the meanwhile, here is the rule:

1. get parent id from categoryDraft object 
   
2. set parent and ancestor for this new category. clarifing something about
   ancestor that ancestor is a list of string, store the path from root category
   to parent category. If its parent id is null, it set its parent as
   empty(thus, it is a root category), otherwise, just setting parent for this
   new category.Be careful, if the parent of this new category is not a root
   category, the new category will have grandparent category, its parent and
   grandparent should be set as ancestor. For example: if you want to create new
   category named `cpu`, then its parent category is `computer`, and `computer`
   has a parent category named `electronic product`

	``` electronic product -> computer -> cpu ```
	
	so `electronic product` is a root category, [`electronic product`
    `computer`] are ancestor of `cpu` category
	
3. if a category change its parent category, it should also change its ancestors
   and its sub category's ancestors.For example, if `computer` category changes
   it parent from `electronic product` to `necessity`:

   Before: ``` electronic product -> computer -> cpu ```
   
   After: ``` necessity -> computer -> cpu ```

   Then, the ancestors of `computer` changes from [`electronic product`] to
   [`necessity`], and the ancestors of `cpu` changes from [`electronic product`
   `computer`] to [`necessity` `computer`]


### 2.3 Multi-Language Design

In our system, multi-language is basic function, here is
design
[document](https://github.com/reactivesw/ecommerce-cloud/blob/master/docs/multilanguange-design.md).

## 3. Workflow

### 3.1. Create Category

1. get categoryDraft object.
2. get parent id from categoryDraft object and get all categories derived from a
   same parent.
3. check whether new category's name is unique on the same level.
4. set parent and ancestor for this new category. Ancestor is a list of string,
   store the path from root category to parent category. If its parent id is
   null, it set its parent as empty(thus, it is a root category), otherwise,
   just setting parent for this new category.
5. convert categoryDraft object to category entity object.
6. call save category function.
7. convert entity object to view object, and return result.

### 3.2 Delete Category

1. get category id and version.
2. get category entity object by id.
3. check whether id correspond with correct version or not.
4. get all sub categories of this category.
5. delete this category and its all sub categories.
6. produce event about deleting category.

### 3.3 Update Category

1. get category id and update action list. 
2. get category entity object by id.
3. check whether id correspond with correct version or not.
4. update category by the attribute defined in update action list.
5. convert entity object to view object, and return result.

### 3.4 Get Individual Category

1. get category id.
2. get category entity object by id.
3. convert entity to view and return result.

### 3.5 Get All Categories

1. receive get request without extra parameters.
2. get all categories from database.
3. convert category entity object to view object.
4. put all view object into a `PageQueryObject` and count the size of view object.
5. return `PageQueryObject`.

## 4. Event Design

### 4.1 Delete Category Event

#### 4.1.1 Model Design

* event entity

| field name | field style | comment |
|--|--|--|
| id | String | UUID, created by database, not null |
| createdTime | Long | create time, not null |
| expire | Long | expire time |
| version | Integer | version of the event, not null |
| data | String | the real data of the event |
| topic | String | the topic name |

* event data

| field name | field style | comment |
|--|--|--|
| categoryIds | List\<String\> | category id in a list |

#### 4.1.2 Topic Design

Topic name: `reactivesw-category-deleted`.
Use gcloud command to create this topic:

```shell
gcloud beta pubsub topics create reactivesw-category-deleted
```
