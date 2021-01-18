@search
Feature: User can search for hospitals

  Scenario Outline: Search hospitals with their first names displays relevant details
    Given I am on the search page
    When I search for "<keywords>"
    Then I shall see the hospitals list"<searchResults>"
    And I close the search

    Examples:
      | keywords | searchResults                                                                                                                                                                                  |
      | Hospital | Hospital Of St Cross, Hospital For Tropical Diseases, Hospital Of St John & St Elizabeth                                                                                                       |
      | Mental   | Mental Health Services (Adult Mental Health) The Fermoy Unit, Mental Health Service (Lister Hospital), Mental health services at The Friarage Hospital, Northallerton, Mental Health Unit QEII |

  Scenario Outline:  Search hospitals with middle or last names does not bring any results
    Given I am on the search page
    When I search for "<keywords>"
    Then I shall see no results
    And I close the search
    Examples:
      | keywords  |
      | Community |
      | Care      |

  Scenario Outline: Search with random characters should display relevant error messages
    Given I am on the search page
    When I search for "<keywords>"
    Then I shall see no results
    And I close the search
    Examples:
      | keywords |
      | %*^+_=   |
      | hjkliop  |
      | b789j00  |
