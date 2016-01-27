Feature: Demo Dropwizard Application

  Scenario: HelloWorld
    When I request GET hello
    Then I should get response status as 200