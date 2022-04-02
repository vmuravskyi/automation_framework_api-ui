package com.cucumber.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepDefs {

    @Given("I'm dummy")
    public void iMDummy() {

    }

    @When("I do dummy things")
    public void iDoDummyThings() {

    }

    @Then("dummy things happen")
    public void dummyThingsHappen() {

    }

    @And("some more dummy things happen")
    public void someMoreDummyThingsHappen() {

    }

    @Given("my account balance is ${int}")
    public void myAccountBalanceIs$OpeningBalance(int arg0) {

    }

    @When("I withdraw ${int}")
    public void iWithdraw$WithdrawalAmount(int arg0) {

    }

    @Then("the account balance should be ${int}")
    public void theAccountBalanceShouldBe$ClosingBalance(int arg0) {

    }

    @And("I navigate to the Store page by pressing {string} menu option")
    public void iNavigateToTheStorePageByPressingMenuOption(String arg0) {

    }

    @Given("I'm on the Home page")
    public void iMOnTheHomePage() {


    }
}
