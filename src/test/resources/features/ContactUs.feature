Feature: WebDriverUniversity.com - Contact us page

  Scenario: Valid Contact us form submission
    Given I navigate to webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    And I type a last name
    And I enter email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  Scenario: Invalid Contact us form submission
    Given I navigate to webdriveruniversity homepage
    When I click on the contact us button
    And I type a first name
    And I type a last name
    #And I enter email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a unsuccessful contact us submission message






