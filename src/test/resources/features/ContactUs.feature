@regression @contact-us
Feature: WebDriverUniversity.com - Contact us page

  #Background will run once for all the scenarios before running each scenario
  Background: pre conditions
    Given I navigate to webdriveruniversity homepage
    When I click on the contact us button

  Scenario: Valid Contact us form submission
    And I type a first name
    And I type a last name
    And I enter email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  Scenario: Invalid Contact us form submission
    And I type a first name
    And I type a last name
    #And I enter email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a unsuccessful contact us submission message

  Scenario: Valid form submission using specific data
    And I type a specific first name "Akshay"
    And I type a specific last name "Ghadi"
    And I enter a specific email address "akshayghadi@mail.com"
    And I type a specific comment "this is a specific comment"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @randomData
  Scenario: Valid Contact us form submission
    And I type a random first name
    And I type a random last name
    And I enter an random email address
    #And I type a comment
    And I add a random comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @smoke #@ignore
  Scenario Outline: Validate contact us page
    And I type a first name <firstName> and a last name <lastName>
    And I type a email address '<emailAddress>' and I comment '<comment>'
    And I click on the submit button
    Then I should be presented with a header text '<message>'
    Examples:
      | firstName | lastName | emailAddress           | comment               | message                      |
      | John      | Jones    | john_jones@example.com | hey, how are you?     | Thank You for your Message!  |
      | Hari      | Gawas    | hari_gawas@example.com | hey, how are u 2?     | Thank You for your Message!  |
      | Mia       | Kate     | mia_kate@              | DO you create website | Error: Invalid email address |






