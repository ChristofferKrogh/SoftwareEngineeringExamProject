package dtu.library.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Book;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.TooManyBooksException;
import dtu.library.app.User;

public class BookSteps {

	private LibraryApp libraryApp;

	private Book book;
	private ErrorMessageHolder errorMessage;
	private List<Book> books;
	private Map<String, Book> bookBySignature = new HashMap<>();
	
	private UserHelper helper;
	private MockDateHolder dateHolder;

	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * BookSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in the "Cucumber for Java" book available online from the DTU Library.
	 */
	public BookSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessage, UserHelper helper, MockDateHolder dateHolder) {
		this.libraryApp = libraryApp;
		this.errorMessage = errorMessage;
		this.helper = helper;
		this.dateHolder = dateHolder;
	}

	@Given("^I have a book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\"$")
	public void iHaveABookWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
		book = new Book(title,author,signature);
	}

	@Given("^a book with signature \"([^\"]*)\" is in the library$")
	public void aBookWithSignatureIsInTheLibrary(String signature) throws Exception {
		if (signature.equals("Beck99")) {
			book = new Book("Extreme Programming", "Kent Beck", signature);
		} else {
			book = new Book("The Cucumber Book for Java", "Seb Rose", signature);
		}
		bookBySignature.put(signature,book);
		addBooksToLibrary(Arrays.asList(book));
	}

	@Given("^these books are contained in the library$")
	public void theseBooksAreContainedInTheLibrary(List<List<String>> books) throws Exception {
		for (List<String> bookInfo : books) {
			libraryApp.addBook(new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2)));
		}
	}
	
	@When("^I add the book$")
	public void iAddTheBook() throws Exception {
		try {
			libraryApp.addBook(book);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("^the book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\" is added to the library$")
	public void theBookWithTitleAuthorAndSignatureIsAddedToTheLibrary(String title, String author, String signature)
			throws Exception {
		assertThat(book.getTitle(),is(equalTo(title)));
		assertThat(book.getAuthor(),is(equalTo(author)));
		assertThat(book.getSignature(),is(equalTo(signature)));
		assertThat(libraryApp.getBooks(),hasItem(book));
	}

	@Then("^I get the error message \"([^\"]*)\"$")
	public void iGetTheErrorMessage(String errorMessage) throws Exception {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}

	@Given("^the library has a book with title \"([^\"]*)\", author \"([^\"]*)\", and signature \"([^\"]*)\"$")
	public void theLibraryHasABookWithTitleAuthorAndSignature(String title, String author, String signature)
			throws Exception {
		Book book = new Book(title, author, signature);
		assertThat(book.getTitle(),is(equalTo(title)));
		assertThat(book.getAuthor(),is(equalTo(author)));
		assertThat(book.getSignature(),is(equalTo(signature)));
		libraryApp.addBook(book);
	}

	@When("^I search for the text \"([^\"]*)\"$")
	public void iSearchForTheText(String searchText) throws Exception {
		books = libraryApp.search(searchText);
	}

	@Then("^I find the book with signature \"([^\"]*)\"$")
	public void iFindTheBookWithSignature(String signature) throws Exception {
		assertEquals(1, books.size());
		assertEquals(signature, books.get(0).getSignature());
	}

	@Then("^I don't find any book$")
	public void iDonTFindAnyBook() throws Exception {
		assertTrue(books.isEmpty());
	}

	@Then("^I find a book with signatures \"([^\"]*)\" and \"([^\"]*)\"$")
	public void iFindABookWithSignaturesAnd(String signature1, String signature2) throws Exception {
		assertEquals(2, books.size());
		Book book1 = books.get(0);
		Book book2 = books.get(1);
		assertTrue((book1.getSignature().equals(signature1) && book2.getSignature().equals(signature2))
				|| (book1.getSignature().equals(signature2) && book2.getSignature().equals(signature1)));
	}
	
	@Given("^book \"([^\"]*)\" by \"([^\"]*)\" with signature \"([^\"]*)\" is in the library$")
	public void bookByWithSignatureIsInTheLibrary(String title, String author, String signature) throws Exception {
		book = new Book(title,author,signature);
	}
	
	@Given("^the user has borrowed (\\d+) books$")
	public void theUserHasBorrowedBooks(int arg1) throws Exception {
		List<Book> exampleBooks = getExampleBooks(10);
		addBooksToLibrary(exampleBooks);
		for (Book b : exampleBooks) {
			libraryApp.borrowBook(b,helper.getUser());
		}
	}

	private void addBooksToLibrary(List<Book> exampleBooks) throws OperationNotAllowedException {
		boolean adminLoggedIn = libraryApp.adminLoggedIn();
		if (!adminLoggedIn) {
			libraryApp.adminLogin("adminadmin");
		}
		for (Book b : exampleBooks) {
			libraryApp.addBook(b);
		}
		if (!adminLoggedIn) {
			libraryApp.adminLogout();
		}
	}

	private List<Book> getExampleBooks(int n) {
		List<Book> books = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			books.add(new Book("title"+i,"author"+i,"signature"+i));
		}
		return books;
	}

	@When("^the user borrows the book$")
	public void theUserBorrowsTheBook() throws Exception {
		try {
			libraryApp.borrowBook(book,helper.getUser());
		} catch (TooManyBooksException e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("^the user borrows the book with signature \"([^\"]*)\"$")
	public void theUserBorrowsTheBookWithSignature(String signature) throws Exception {
		try {
			libraryApp.borrowBook(bookBySignature.get(signature),helper.getUser());
		} catch (Exception e ) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("^the book is borrowed by the user$")
	public void theBookIsBorrowedByTheUser() throws Exception {
		assertThat(helper.getUser().getBorrowedBooks(),hasItem(book));
	}
	
	@Then("^the book is not borrowed by the user$")
	public void theBookIsNotBorrowedByTheUser() throws Exception {
		assertThat(helper.getUser().getBorrowedBooks(),not(hasItem(book)));
	}
	
	@Then("^the book with signature \"([^\"]*)\" is not borrowed by the user$")
	public void theBookWithSignatureIsNotBorrowedByTheUser(String signature) throws Exception {
		Book book = bookBySignature.get(signature);
		assertThat(helper.getUser().getBorrowedBooks(),not(hasItem(book)));
	}
	
	@Given("^(?:that |)the user has borrowed a book$")
	public void thatTheUserHasBorrowedABook() throws Exception {
		book = new Book("title","author","signature");
		addBooksToLibrary(Arrays.asList(book));
		libraryApp.borrowBook(book,helper.getUser());
	}
	
	@Given("^that the user has not borrowed the book$")
	public void thatTheUserHasNotBorrowedTheBook() throws Exception {
		book = new Book("title","author","signature");
		addBooksToLibrary(Arrays.asList(book));
	}

	@When("^the user returns the book$")
	public void theUserReturnsTheBook() throws Exception {
		try {
			helper.getUser().returnBook(book);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("^the user returns the book with signature \"([^\"]*)\"$")
	public void theUserReturnsTheBookWithSignature(String signature) throws Exception {
		try {
			helper.getUser().returnBook(bookBySignature.get(signature));
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("^there is a user with one overdue book$")
	public void thereIsAUserWithOneOverdueBook() throws Exception {
		libraryApp.registerUser(helper.getUser());
		thatTheUserHasBorrowedABook();
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueBooks(helper.getUser()),is(true));		
	}
	
	@Given("^a user has an overdue book$")
	public void aUserHasAnOverdueBook() throws Exception {
		User user = helper.getUser();
		libraryApp.adminLogin("adminadmin");
		libraryApp.registerUser(user);
		libraryApp.adminLogout();
		List<Book> books = getExampleBooks(1);
		addBooksToLibrary(books);
		book = books.get(0);
		libraryApp.borrowBook(book, user);
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueBooks(user),is(true));
	}

	@Given("^the user has another overdue book$")
	public void theUserHasAnotherOverdueBook() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(new Book("title","author","signature"));
		addBooksToLibrary(books);
		book = books.get(0);
		libraryApp.borrowBook(book, helper.getUser());
		dateHolder.advanceDateByDays(29);
		assertThat(libraryApp.userHasOverdueBooks(helper.getUser()),is(true));
	}

	@When("^the user pays (\\d+) DKK$")
	public void theUserPaysDKK(int money) throws Exception {
	    libraryApp.payFine(helper.getUser(),money);
	}

	@Then("^the user can borrow books again$")
	public void theUserCanBorrowBooksAgain() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(true));
	}
	
	@Then("^the user cannot borrow books$")
	public void theUserCannotBorrowBooks() throws Exception {
		assertThat(libraryApp.canBorrow(helper.getUser()),is(false));
	}


}
