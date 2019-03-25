package dtu.library.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the business logic associated with the library
 * application.
 * 
 * @author Hubert
 *
 */
public class LibraryApp {

	private boolean loggedIn = false;
	private List<Book> books = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private DateServer dateServer = new DateServer();
	private EmailServer emailServer;

	/**
	 * @return true if the administrator is logged in, false otherwise.
	 */
	public boolean adminLoggedIn() {
		return loggedIn;
	}

	/**
	 * Logs in the administrator provided that the correct password is supplied.
	 * 
	 * @param password
	 * @return true if the administrator could be logged in, false otherwise.
	 */
	public boolean adminLogin(String password) {
		loggedIn = password.equals("adminadmin");
		return loggedIn;
	}

	/**
	 * Adds a book to the library, so that it can be searched and borrowed. Only the
	 * administrator can do this. Thus, the administrator has to be logged in.
	 * 
	 * @param book,
	 *            the book to be added
	 * @throws OperationNotAllowedException
	 *             if the administrator is not logged in
	 */
	public void addBook(Book book) throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		books.add(book);
	}

	/**
	 * @return the list of books currently known by the library.
	 */
	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	/**
	 * Search for a set of books by text. The text can be any substring of either
	 * the title, the author, and the signature.
	 * 
	 * @param searchText
	 * @return a list of books containing the searchText in either title, author,
	 *         and signature
	 */
	public List<Book> search(String searchText) {
		// /* Simple version */
		// List<Book> found = new ArrayList<>();
		// for (Book book : books) {
		// if (book.match(searchText)) {
		// found.add(book);
		// }
		// }
		// return found;
		/* Using Java 8 streams to search */
		return books.stream().filter(b -> b.match(searchText)).collect(Collectors.toList());
	}

	/**
	 * Logs out the administrator.
	 */
	public void adminLogout() {
		loggedIn = false;
	}

	public void registerUser(User user) throws Exception {
		checkAdministratorLoggedIn();
		if (users.contains(user)) {
			throw new Exception("User is already registered");
		}
		users.add(user);
	}

	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public void borrowBook(Book book, User user) throws Exception {
		user.borrowBook(book, dateServer.getDate());
	}

	public double getFineForUser(User user) {
		return user.getFine(dateServer.getDate());
	}

	public boolean userHasOverdueBooks(User user) {
		return user.hasOverdueBooks(dateServer.getDate());
	}

	public void sendReminder() throws OperationNotAllowedException {
		checkAdministratorLoggedIn();
		Calendar currentDate = dateServer.getDate();
		users.stream()
			.filter(u -> u.hasOverdueBooks(currentDate))
			.forEach(u -> {u.sendEmailReminder(emailServer,currentDate);});
	}

	private void checkAdministratorLoggedIn() throws OperationNotAllowedException {
		if (!adminLoggedIn()) {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}

	public void payFine(User user, int money) {
		user.payFine(money);
	}

	public boolean canBorrow(User user) {
		try {
			user.canBorrow(dateServer.getDate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
