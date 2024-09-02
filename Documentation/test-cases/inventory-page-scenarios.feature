## Feature: Inventory Page Functionality

As a user of the commerce website,
I want to view, sort, and add products to my cart from the inventory page,
So that I can choose products to purchase.

### Scenario: Viewing the inventory page with available products
@functional @positive
Given the user is logged in as "standard_user"
When the user navigates to the inventory page
Then the user should see a list of available products
And each product should display its name, description, and price

### Scenario: Sorting products by price (low to high)
@functional @positive
Given the user is on the inventory page
When the user selects the "Price (low to high)" option from the sort dropdown
Then the products should be displayed in ascending order of price

### Scenario: Sorting products by price (high to low)
@functional @positive
Given the user is on the inventory page
When the user selects the "Price (high to low)" option from the sort dropdown
Then the products should be displayed in descending order of price

### Scenario: Adding a product to the cart
@functional @positive @cart
Given the user is on the inventory page
When the user clicks the "Add to cart" button for a product
Then the product should be added to the cart
And the number displayed next to the cart icon should increment by one

### Scenario: Removing a product from the cart
@functional @positive @cart
Given the user has added a product to the cart
And the user is on the inventory page
When the user clicks the "Remove" button for that product
Then the product should be removed from the cart
And the number displayed next to the cart icon should decrement by one

### Scenario: Adding multiple products to the cart
@functional @positive @cart
Given the user is on the inventory page
When the user adds several products to the cart
Then all selected products should be added to the cart
And the cart icon should reflect the total number of items added

### Scenario: Sorting products by name (A to Z)
@functional @positive
Given the user is on the inventory page
When the user selects the "Name (A to Z)" option from the sort dropdown
Then the products should be displayed in alphabetical order (A to Z)

### Scenario: Sorting products by name (Z to A)
@functional @positive
Given the user is on the inventory page
When the user selects the "Name (Z to A)" option from the sort dropdown
Then the products should be displayed in reverse alphabetical order (Z to A)

### Scenario: Viewing product details from the inventory page
@functional @positive
Given the user is on the inventory page
When the user clicks on a product's name or image
Then the user should be redirected to the product's detail page
And the user should see detailed information about the product
