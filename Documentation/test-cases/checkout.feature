Feature: Checkout Process

  As a user of the commerce website,
  I want to complete my checkout process with valid information,
  So that I can finalize my purchase.

  @functional @positive @checkout
  Scenario: Initiating checkout process as standard_user
    Given the user is logged in as "standard_user"
    And the user has products in their cart
    When the user clicks the "Checkout" button on the "CART" page
    Then the user should be redirected to the "Checkout Information" page

  @functional @positive @checkout
  Scenario: Filling out checkout information with valid data as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "Checkout Information" page
    When the user enters "John" as first name
    And the user enters "Doe" as last name
    And the user enters "john.doe@example.com" as email
    And the user clicks the "Continue" button
    Then the user should be redirected to the "Order Summary" page

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing data as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "Checkout Information" page
    When the user leaves the first name, last name, or email field blank
    And the user clicks the "Continue" button
    Then an error message should be displayed indicating that all fields are required

  @functional @positive @checkout
  Scenario: Viewing the order summary with item, tax, and total price as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "Order Summary" page
    Then the item total, tax, and total price should be displayed correctly

  @functional @positive @checkout
  Scenario: Completing the checkout process and viewing thank you message as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "Order Summary" page
    When the user clicks the "Finish" button
    Then the user should be redirected to the "Thank You" page
    And the user should see a thank you message
