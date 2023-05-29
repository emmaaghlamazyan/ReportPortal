Feature: As I user I want to update a filter

  Background:
    Given User is logged in Report portal

  @smoke
  Scenario Outline: All required fields should be filled in
    Given Filter is created in Report Portal
    When User updates filter
    Then Check response message
    And Check <status> code
    Examples:
      | status |
      | 200  |

