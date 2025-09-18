@regression @login
Feature: WebDriverUniversity.com - Login page

  Scenario Outline: Login to the WebDriverUniversity sucessfully
    Given I navigate to webdriveruniversity homepage
    When I click on the login portal button
    And I give login id '<userId>' and password '<password>'
    And I click on the login button
    Then I should see a validation message '<message>'
    Examples:
      | userId    | password     | message              |
      | webdriver | webdriver123 | validation succeeded |
      | webdriver | webdriver124 | validation failed    |
    @smoke
    Examples:
      | userId    | password     | message              |
      | webdriver | webdriver123 | validation succeeded |

