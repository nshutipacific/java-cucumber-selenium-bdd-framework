Feature: Price Range Filter
  Scenario: Filter Products within price Range
    Given a user is on the product page
    When a user filter withing price range using range input
    And user click on filter button
    Then user should see products within the price range