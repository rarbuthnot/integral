Feature: Publishing
  Scenario: Alice publishes messages to her personal timeline.
    Given Alice has published "I love the weather today."
    When Alice views her timeline
    Then Alice sees: "Alice - I love the weather today."