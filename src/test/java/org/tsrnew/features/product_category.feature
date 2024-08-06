Feature: Category Filter

  Scenario Outline: Filter products by category
    Given a user is on the product page
    When the user selects "<Category>" from the dropdown
    Then the product listing should display only products from the "<Category>" category

    Examples:
      | Category            |
      | Accessories         |
      | Men                 |
      | Men's Shoes         |
      | Men's Shirts        |
      | Men's Jeans         |
      | Purses and Handbags |
      | Women               |
      | Women's Jeans       |
      | Women's Shirts      |
      | Women's Shoes       |