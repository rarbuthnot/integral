Feature: Following

  Scenario Outline: Charlie can follow Alice and Bob, and he views an aggregated list of all timelines.
    Given Alice has published "I love the weather today."
    And Bob has published "Darn! We lost!"
    And Bob has published "Good game though."
    And Charlie has published "I'm in New York today! Anyone wants to have a coffee?"
    When Charlie follows Alice
    And Charlie follows Bob
    And Charlie views his timeline
    Then Charlie sees: "<messages>"

    Examples:
      | messages                                                                         |
      | Charlie - I'm in New York today! Anyone wants to have a coffee? |
      | Bob - Good game though.                                           |
      | Bob - Darn! We lost!                                             |
      | Alice - I love the weather today.                                 |

