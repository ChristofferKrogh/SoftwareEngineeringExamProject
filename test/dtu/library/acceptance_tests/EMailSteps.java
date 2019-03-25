package dtu.library.acceptance_tests;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;

import static org.mockito.Mockito.*;

public class EMailSteps {
	
	LibraryApp libraryApp;
	UserHelper helper;
	MockEmailServerHolder emailServerHolder;
	private ErrorMessageHolder errorMessageHolder;
	
	public EMailSteps(LibraryApp libraryApp, UserHelper helper, MockEmailServerHolder emailServerHolder, ErrorMessageHolder errorMessageHolder) {
		this.libraryApp = libraryApp;
		this.helper = helper;
		this.emailServerHolder = emailServerHolder;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("^the administrator sends a reminder e-mail$")
	public void theAdministratorSendsAReminderEMail() throws Exception {
		try {
			libraryApp.sendReminder();
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("^then the user receives an email with subject \"([^\"]*)\" and text \"([^\"]*)\"$")
	public void thenTheUserReceivesAnEmailWithSubjectAndText(String subject, String text) throws Exception {
	    verify(emailServerHolder.getMockEmailServer()).sendEmail(helper.getUser().getEmail(), subject, text);
	}
}

