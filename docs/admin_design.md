# Category Design for Admin-Web

This document describes how to achieve the admin-web [requirement](./admin-requirement.md).

## 1. Basic Functions

For admin-web, category service provides following functions:

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

* multi-languang

    name, description, meta title, meta description, meta keywords of category should be multiple language.

Following is how to achieve those requirement.

### 2.1 OrderHint Design

In category model, we store a string named `orderHint` and use it to sort category in one level. An `orderHint` is a string, the context is the value of 0 to 1 float data.

When create a category, will create an `orderHint` to it by system time, here is the rule:

1. get system current time in milliseconds, like: 14919906819555.

2. convert step 1 value to float data, 14919906819555 -> 0.14919906819555.

3. store setp 2 value to `orderHint` in string.

When update category order, what we need to do is update one category `orderHint` and others don't need to change, here is the rule:

1. get the first one category `orderHint`  and after one category `orderHint`, calculate the median of two `orderHint`.
   example:
   ```
   the first one orderHint: 0.149199068195555
   the after one orderHint: 0.149199068195556

   the result: (0.149199068195555 + 0.149199068195556) / 2 = 0.1491990681955555
   ```
2. if change the category to the first one, the new `orderHint` will be the median of the original first one `orderHint` and `0`.
    example:
    ```
    the orginal first one orderHint: 0.149199068195555

    the result: (0.149199068195555 + 0) / 2 = 0.074599534097775
    ```

3. if change the category to the last one, the new  `orderHint` will be the median of the original last one `orderHint` and `1`.
    exmaple:
    ```
    the orginal last one orderHint: 0.149199068195555

    the result: (0.149199068195555 + 1) / 2 = 0.574599534097775
    ```


### 2.2 Multilevel Design

### 2.3 Multi-Language Design

In our system, multi-language is basic function, here is design [document](https://github.com/reactivesw/ecommerce-cloud/blob/master/docs/multilanguange-design.md).

## 3. Workflow

### 6.1. Create Category

### 6.2 Delete Category

### 6.3 Update Category

### 6.4 Get Individual Category

### 6.5 Get All Categories