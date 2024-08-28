## Feature: Login Functionality

As a user of the commerce website,
I want to be able to log in with valid credentials,
So that I can access my account and make purchases.

### Scenario: Successful login with valid credentials
@functional @happy_path
Given the user is on the login page
When the user enters the username "standard_user" and the password "secret_sauce"
And the user clicks the login button
Then the user should be redirected to the home page
And the user should see the welcome message

### Scenario: Unsuccessful login with incorrect credentials
@functional @invalid_login
Given the user is on the login page
When the user enters the username "standard_user" and the password "wrong_password"
And the user clicks the login button
Then the user should see an error message indicating incorrect credentials

### Scenario: Unsuccessful login with empty username
@functional @invalid_login
Given the user is on the login page
When the user leaves the username field empty and enters the password "secret_sauce"
And the user clicks the login button
Then the user should see an error message indicating that the username is required

### Scenario: Unsuccessful login with empty password
@functional @invalid_login
Given the user is on the login page
When the user enters the username "standard_user" and leaves the password field empty
And the user clicks the login button
Then the user should see an error message indicating that the password is required

### Scenario: Unsuccessful login with locked-out user
@functional @locked_out_user
Given the user is on the login page
When the user enters the username "locked_out_user" and the password "secret_sauce"
And the user clicks the login button
Then the user should see an error message indicating that the account is locked

### Scenario: Performance of login system under load
@performance
Given the system is under peak load conditions
When the user enters the username "standard_user" and the password "secret_sauce"
And the user clicks the login button
Then the login process should complete within acceptable time limits
