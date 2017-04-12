# Admin-Web Requirement on Category
This document describes the requirement of admin-web on category service

## 1. Introduction

Category service is used to manage category. The functions category service
provides to admin-web as below:
+ create category 
+ delete category
+ update category 
+ get all category
+ get individual category 
+ get category with condition

## 2. Requirement

### 2.1 About category

1. name, description, meta title, meta description, meta keywords of category
   should be multiple language
2. slug of category should be alphabetic, numeric, underscore(_) and hyphen(-)
   characters.Maximize size of slug is 256, and minimum size is 2
3. name and slug of category are required, others is optional
4. category could be multilevel

### 2.1. About creating category

1. satisfing the requirement about category
2. the category administrator want to create should not exist

### 2.2. About deleting category
1. the category administrator want to delete should exist
2. the category administrator want to delete should correspond with correct
   version

### 2.3 About updating category

1. satisfing the requirement about category
2. the category administrator want to update should exist

### 2.4. About getting individual category

1. individual category you want to get should exist

### 2.5. About getting all category

1. satisfing the requirement about category

### 2.6. About getting category with condition

## 3. How to provide
Category service provides RESTful API to admin-web, here
is
[api documentation](https://github.com/reactivesw/category/blob/master/docs/api.md)
