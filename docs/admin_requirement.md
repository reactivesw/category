# Admin-Web Requirement on Category
This document describes the requirement of admin-web on category service.

## 1. Introduction

Category service is used to manage category. The functions category service
provides to admin-web as below:
+ create category
+ delete category
+ update category
+ get individual category 
+ get all categories

## 2. Requirement

### 2.1 Basic requirement about category

1. name, description, meta title, meta description, meta keywords of category
   should be multiple language.
2. slug of category should be alphabetic, numeric, underscore(_) and hyphen(-)
   characters.Maximize size of slug is 256, and minimum size is 2.
3. name and slug of category are required, others is optional.
4. category could be multilevel.

### 2.2. Creating category

1. name and slug of category should be unique.
2. parent of a category could be added while creating a new category.
3. orderHint will be generated automatically by server.

### 2.3. Deleting category
1. if a category is deleted, all sub category of this category will be deleted too.
2. the association between product and category will be deleted.

### 2.4 Updating category
1. all attribute of category could be updated

### 2.5. Getting individual category

### 2.6. Getting all categories

## 3. How to provide
Category service provides RESTful API to admin-web, here
is
[api documentation](https://github.com/reactivesw/category/blob/master/docs/api.md)
