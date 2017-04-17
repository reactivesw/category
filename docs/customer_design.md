# Category Design for Customer-Web

This document describes how to achieve the customer-web [requirement](/docs/customer_requirement.md).

## 1. Functions

For customer-web, category service provide following functions:

1. get all categories in order

## 2. Workflow

### 2.1 Get all categories in order

1. get all categories from database by `JPA` method `findAll`.
2. if categories is null, return empty list to customer-web immediately.
3. sort categories by orderHint, smaller orderHint is priority.
4. convert categories to view model that returns to customer-web.
5. return result to customer-web.