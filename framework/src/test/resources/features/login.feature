Feature: Login Functionality

  As a user of the commerce website,
  I want to be able to log in with valid credentials,
  So that I can access my account and make purchases.

  @functional @happy_path
  Scenario: Successful login with valid credentials
    Given the user is on the "LOGIN" page
    When the user enters the username "standard_user" and the password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the home page

  @functional @invalid_login
  Scenario: Unsuccessful login with incorrect credentials
    Given the user is on the "LOGIN" page
    When the user enters the username "standard_user" and the password "wrong_password"
    And the user clicks the login button
    Then the user should see an error message indicating incorrect credentials

  @functional @invalid_login
  Scenario: Unsuccessful login with empty username
    Given the user is on the "LOGIN" page
    When the user leaves the username field empty and enters the password "secret_sauce"
    And the user clicks the login button
    Then the user should see an error message indicating that the username is required

  @functional @invalid_login
  Scenario: Unsuccessful login with empty password
    Given the user is on the "LOGIN" page
    When the user enters the username "standard_user" and leaves the password field empty
    And the user clicks the login button
    Then the user should see an error message indicating that the password is required

  @functional @locked_out_user
  Scenario: Unsuccessful login with locked-out user
    Given the user is on the "LOGIN" page
    When the user enters the username "locked_out_user" and the password "secret_sauce"
    And the user clicks the login button
    Then the user should see an error message indicating that the account is locked

#  @performance
#  Scenario: Performance of login system under load
#    Given the system is under peak load conditions
#    When the user enters the username "standard_user" and the password "secret_sauce"
#    And the user clicks the login button
#    Then the login process should complete within acceptable time limits