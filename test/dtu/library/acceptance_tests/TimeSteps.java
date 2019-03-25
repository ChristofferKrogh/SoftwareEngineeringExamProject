package dtu.library.acceptance_tests;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class TimeSteps {
	
	MockDateHolder dateHolder;
	
	public TimeSteps(MockDateHolder dateHolder) {
		this.dateHolder = dateHolder;
	}
	
	@Given("^(\\d+) days have passed$")
	public void daysHavePassed(int days) throws Exception {
	    dateHolder.advanceDateByDays(days);
	}
}

