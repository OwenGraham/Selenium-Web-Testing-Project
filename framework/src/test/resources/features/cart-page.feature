@cart @functional @positive
Feature: Cart Page Functionality

  @cart @functional @positive
  Scenario: Removing a product from the cart as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    And the user has added a product to the cart
    When the user navigates to the "CART" page
    And the user clicks the Remove button for the 0 th product
    Then the product should be removed from the cart
    And the number displayed next to the cart icon should decrement by one

  @cart @functional @positive
  Scenario: Viewing products in the cart as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then all 3 selected products should be added to the cart
    And each product should display its name, quantity, and price

  @cart @functional @negative
  Scenario: Removing a product from the cart as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    And the user has added a product to the cart
    When the user navigates to the "CART" page
    And the user clicks the Remove button for the 0 th product
    Then the product should be removed from the cart
    And the number displayed next to the cart icon should decrement by one

  @cart @functional @negative
  Scenario: Viewing products in the cart as error_user
    Given the user is logged in as "error_user"
    And the user is on the "INVENTORY" page
    When the user adds 3 products to the cart
    Then all 3 selected products should be added to the cart
    And each product should display its name, quantity, and price