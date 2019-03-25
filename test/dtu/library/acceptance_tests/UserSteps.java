package dtu.library.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Address;
import dtu.library.app.LibraryApp;
import dtu.library.app.User;

public class UserSteps {
	
	private LibraryApp libraryApp;
	private User user;
	private Address address;
	private ErrorMessageHolder errorMessage;
	public UserHelper helper;
	private int fine;
	
	public UserSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessage, UserHelper helper) {
		this.libraryApp = libraryApp;
		this.errorMessage = errorMessage;
		this.helper = helper;
	}
	
	@Given("^there is a user with CPR \"([^\"]*)\", name \"([^\"]*)\", e-mail \"([^\"]*)\"$")
	public void thereIsAUserWithCPRNameEMail(String cpr, String name, String email) throws Exception {
		user = new User(cpr,name,email);
		assertThat(user.getCpr(),is(equalTo(cpr)));
		assertThat(user.getName(),is(equalTo(name)));
		assertThat(user.getEmail(),is(equalTo(email)));
	}
	
	@Given("^the user has address street \"([^\"]*)\", post code (\\d+), and city \"([^\"]*)\"$")
	public void theUserHasAddressWithStreetPostCodeAndCity(String street, int postCode, String city) throws Exception {
		address = new Address(street,postCode,city);
		assertThat(address.getStreet(),is(equalTo(street)));
		assertThat(address.getPostCode(),is(equalTo(postCode)));
		assertThat(address.getCity(),is(equalTo(city)));
		user.setAddress(address);
		assertThat(user.getAddress(),is(sameInstance(address)));
	}
	
	@When("^the administrator registers the user(?: again|)$")
	public void theAdministratorRegistersTheUser() throws Exception {
		try {
			libraryApp.registerUser(user);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("^the user is a registered user of the library$")
	public void theUserIsARegisteredUserOfTheLibrary() throws Exception {
	    List<User> users = libraryApp.getUsers();
	    assertThat(users.size(),is(1));
	    User u = users.get(0);
	    assertThat(u.getName(), is(user.getName()));
	    assertThat(u.getEmail(), is(user.getEmail()));
	}
	
	@Given("^a user is registered with the library$")
	public void aUserIsRegisteredWithTheLibrary() throws Exception {
	    user = helper.getUser();
	    libraryApp.adminLogin("adminadmin");
	    libraryApp.registerUser(user);
	    libraryApp.adminLogout();
	}
	
	@Then("^the user has overdue books$")
	public void theUserHasOverdueBooks() throws Exception {
		assertThat(libraryApp.userHasOverdueBooks(helper.getUser()),is(true));
	}

	@Then("^the user has no overdue books$")
	public void theUserHasNoOverdueBooks() throws Exception {
		assertThat(libraryApp.userHasOverdueBooks(helper.getUser()),is(false));
	}

	@Then("^the user has to pay a fine of (\\d+) DKK$")
	public void theUserHasToPayAFineOfDKK(double fine) throws Exception {
		assertThat(libraryApp.getFineForUser(helper.getUser()),is(equalTo(fine)));
	}

	@Then("^the user has to pay no fine$")
	public void theUserHasToPayNoFine() throws Exception {
		theUserHasToPayAFineOfDKK(0);
	}
	
	@Given("^the fine for one overdue book is (\\d+) DKK$")
	public void theFineForOneOverdueBookIsDKK(int fine) throws Exception {
		this.fine = fine;
	}
	
}

