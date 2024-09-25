Feature: Checkout Process

  As a user of the commerce website,
  I want to complete my checkout process with valid information,
  So that I can finalize my purchase.

  @functional @positive @checkout
  Scenario: Initiating checkout process as standard_user
    Given the user is logged in as "standard_user"
    And the user has products in their cart
    When the user clicks the Checkout button on the CART page
    Then the user should be redirected to the Checkout Information page

  @functional @positive @checkout
  Scenario: Filling out checkout information with valid data as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "CHECKOUT_ONE" page
    When the user enters "John" as first name
    And the user enters "Doe" as last name
    And the user enters "CH7 4QW" as post code
    And the user clicks the Continue button
    Then the user should be redirected to the Order Summary page

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing first name as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Graham" as last name
    And the user enters "XXX XXX" as post code
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a first name is required

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing last name as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Owen" as first name
    And the user enters "XXX XXX" as post code
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a last name is required

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing postcode as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Owen" as first name
    And the user enters "Graham" as last name
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a postcode is required

  @functional @positive @checkout
  Scenario: Viewing the order summary with item, tax, and total price as standard_user
    Given the user is logged in as "standard_user"
    And the user has products in their cart
    When the user navigates to the "CHECKOUT_TWO" page
    Then the item total, tax, and total price should be displayed correctly

  @functional @positive @checkout
  Scenario: Completing the checkout process and viewing thank you message as standard_user
    Given the user is logged in as "standard_user"
    And the user is on the "CHECKOUT_TWO" page
    When the user clicks the Finish button
    Then the user should be redirected to the Thank You page
    And the user should see a thank you message

  @functional @positive @checkout
  Scenario: Initiating checkout process as error_user
    Given the user is logged in as "error_user"
    And the user has products in their cart
    When the user clicks the Checkout button on the CART page
    Then the user should be redirected to the Checkout Information page

  @functional @positive @checkout
  Scenario: Filling out checkout information with valid data as error_user
    Given the user is logged in as "error_user"
    And the user is on the "CHECKOUT_ONE" page
    When the user enters "John" as first name
    And the user enters "Doe" as last name
    And the user enters "CH7 4QW" as post code
    And the user clicks the Continue button
    Then the user should be redirected to the Order Summary page

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing first name as error_user
    Given the user is logged in as "error_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Graham" as last name
    And the user enters "XXX XXX" as post code
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a first name is required

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing last name as error_user
    Given the user is logged in as "error_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Owen" as first name
    And the user enters "XXX XXX" as post code
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a last name is required

  @functional @negative @checkout
  Scenario: Submitting checkout information with missing postcode as error_user
    Given the user is logged in as "error_user"
    And the user is on the "CHECKOUT_ONE" page
    And the user enters "Owen" as first name
    And the user enters "Graham" as last name
    When the user clicks the Continue button
    Then an error message should be displayed indicating that a postcode is required

  @functional @positive @checkout
  Scenario: Viewing the order summary with item, tax, and total price as error_user
    Given the user is logged in as "error_user"
    And the user has products in their cart
    When the user navigates to the "CHECKOUT_TWO" page
    Then the item total, tax, and total price should be displayed correctly

  @functional @positive @checkout
  Scenario: Completing the checkout process and viewing thank you message as error_user
    Given the user is logged in as "error_user"
    And the user is on the "CHECKOUT_TWO" page
    When the user clicks the Finish button
    Then the user should be redirected to the Thank You page
    And the user should see a thank you message
