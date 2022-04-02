@dummyFeatures
Feature: Add to cart

  Rule: Add form Store

    @bvt @regression
   Scenario Outline: Add one quantity to the cart
      Given I'm on the Store page
      When I add a "<productName>" to the cart
      Then I see <quantity> "<productName>" in the cart

     Examples:
       | productName | quantity |
       | Blues Shoes | 1        |

      @regression
    Scenario: Add two quantity to the cart
      Given I'm on the Store page
      When I add a "Green Shoes" to the cart
      Then I see 2 "<productName>" in the cart