@cart @functional @positive
Feature: Cart Page Functionality

  @cart @functional @positive
  Scenario: Removing a product from the cart
    Given the user has added a product to the cart
    When the user navigates to the cart page
    And the user clicks the "Remove" button for the product
    Then the product should be removed from the cart
    And the cart count should be updated accordingly

  @cart @functional @positive
  Scenario: Viewing products in the cart
    Given the user has added multiple products to the cart
    When the user navigates to the cart page
    Then the user should see the list of added products
    And each product should display its name, quantity, and price

  @cart @functional @negative
  Scenario: Viewing the cart page with no items
    Given the user is logged in as "standard_user"
    When the user navigates to the cart page
    Then the user should see a message indicating the cart is empty
