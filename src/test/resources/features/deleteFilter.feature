Feature: Delete Filter Feature

  Background:
    Given User is logged in to the Report Portal

  @smoke @delete_filter
  Scenario: User successfully deletes a filter
    Given A filter exists in the Report Portal
    When The User deletes the filter
    Then The filter should no longer appear in the Report Portal and the status code should be 200