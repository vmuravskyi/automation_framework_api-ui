#@dummyFeatures
#Feature: Dummy feature
#
#  As a customer of asd app
#  I want to add a product to the cart
#  So that I can purchase a product
#
#  Rule: Withdraw from account
#
#    Background: Common given steps
#      Given I'm logged in as Volodymyr
#
#    @dummyScenario
#    Scenario Outline: scenario 1
#
#      Given my account balance is $<opening balance>
#      When I withdraw $<withdrawal amount>
#      Then the account balance should be $<closing balance>
#
#      Examples:
#        | opening balance | withdrawal amount | closing balance |
#        | 100             | 50                | 50              |
#        | 50              | 50                | 0               |
#
#
#  # And, But, *
#
#  Rule: rule 2
#
#    Background: Common given steps
#
#    Scenario: scenario 2
#
#      Given I'm dummy
#      When I do dummy things
#      And I do some more dummy things
#      Then dummy things happen
#      And some more dummy things happen
#
#
#    # feature -> user story