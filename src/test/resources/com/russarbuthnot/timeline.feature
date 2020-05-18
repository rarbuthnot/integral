Feature: Timeline

  Scenario Outline: Alice views Bob's timeline.
    Given Bob has published "Darn! We lost!"
    And Bob has published "Good game though."
    When Alice views Bob's timeline
    Then Alice sees: "<messages>"

    Examples:
      | messages            |
      | Bob - Good game though. |
      | Bob - Darn! We lost!    |