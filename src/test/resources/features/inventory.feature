Feature: Inventory Page Functionality

  As a user of the commerce website,
  I want to view, sort, and add products to my cart from the inventory page,
  So that I can choose products to purchase.

  @functional @positive
  Scenario: Viewing the inventory page with available products as standard_user
    Given the user is logged in as "standard_user"
    When the user is on the "INVENTORY" page
    Then the user should see a list of available products
    And each product should display its name, description, and price

  @functional @positive
    Scenario: Sorting products by price (low to high) as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user selects the "PRICE_LOW_TO_HIGH" option from the sort dropdown
    Then the products should be displayed in ascending order of price

  @functional @positive
  Scenario: Sorting products by price (high to low) as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user selects the "PRICE_HIGH_TO_LOW" option from the sort dropdown
    Then the products should be displayed in descending order of price

  @functional @positive @cart
  Scenario: Adding a product to the cart and checking cart number as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user clicks the Add to cart button for a product
    Then the cart icon should display the number 1

  @functional @positive @cart
  Scenario: Adding a product to the cart and checking cart as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user clicks the Add to cart button for a product
    Then the product should be added to the cart

  @functional @positive @cart
  Scenario: Removing a product from the cart as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    And the user has added a product to the cart
    When the user clicks the Remove button for that product
    Then the product should be removed from the cart
    And the number displayed next to the cart icon should decrement by one

  @functional @positive @cart
  Scenario: Adding multiple products to the cart and checking cart as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then all 3 selected products should be added to the cart

  @functional @positive @cart
  Scenario: Adding multiple products to the cart and checking cart number as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then the cart icon should display the number 3

  @functional @positive
  Scenario: Sorting products by name (A to Z) as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user selects the "NAME_A_TO_Z" option from the sort dropdown
    Then the products should be displayed in alphabetical order (A to Z)

  @functional @positive
  Scenario: Sorting products by name (Z to A) as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user selects the "NAME_Z_TO_A" option from the sort dropdown
    Then the products should be displayed in reverse alphabetical order (Z to A)

  @functional @positive
  Scenario: Viewing product details from the inventory page as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user clicks on a product's name
    Then the user should be redirected to the product's detail page
    And the user should see detailed information about the product

  @functional @positive
  Scenario: Viewing the inventory page with available products as error_user
    Given the user is logged in as "error_user"
    When the user is on the "INVENTORY" page
    Then the user should see a list of available products
    And each product should display its name, description, and price

  @functional @positive
    Scenario: Sorting products by price (low to high) as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user selects the "PRICE_LOW_TO_HIGH" option from the sort dropdown
    Then the products should be displayed in ascending order of price

  @functional @positive
  Scenario: Sorting products by price (high to low) as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user selects the "PRICE_HIGH_TO_LOW" option from the sort dropdown
    Then the products should be displayed in descending order of price

  @functional @positive @cart
  Scenario: Adding a product to the cart and checking cart number as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user clicks the Add to cart button for a product
    Then the cart icon should display the number 1

  @functional @positive @cart
  Scenario: Adding a product to the cart and checking cart as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user clicks the Add to cart button for a product
    Then the product should be added to the cart

  @functional @positive @cart
  Scenario: Removing a product from the cart as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    And the user has added a product to the cart
    When the user clicks the Remove button for that product
    Then the product should be removed from the cart
    And the number displayed next to the cart icon should decrement by one

  @functional @positive @cart
  Scenario: Adding multiple products to the cart and checking cart as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then all 3 selected products should be added to the cart

  @functional @positive @cart
  Scenario: Adding multiple products to the cart and checking cart number as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then the cart icon should display the number 3

  @functional @positive
  Scenario: Sorting products by name (A to Z) as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user selects the "NAME_A_TO_Z" option from the sort dropdown
    Then the products should be displayed in alphabetical order (A to Z)

  @functional @positive
  Scenario: Sorting products by name (Z to A) as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user selects the "NAME_Z_TO_A" option from the sort dropdown
    Then the products should be displayed in reverse alphabetical order (Z to A)

  @functional @positive
  Scenario: Viewing product details from the inventory page as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user clicks on a product's name
    Then the user should be redirected to the product's detail page
    And the user should see detailed information about the product