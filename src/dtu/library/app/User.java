package dtu.library.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class User {

	private static final int MAX_NUMBER_OF_BOOKS = 10;
	private String cpr;
	private String name;
	private String email;
	private Address address;
	private List<Book> borrowedBooks = new ArrayList<>();
	private Optional<Double> fine = Optional.empty();

	public User(String cpr, String name, String email) {
		this.cpr = cpr;
		this.name = name;
		this.email = email;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Book> getBorrowedBooks() {
		return Collections.unmodifiableList(borrowedBooks);
	}

	public void returnBook(Book book) throws Exception {
		if (!borrowedBooks.contains(book)) {
			throw new Exception("book is not borrowed by the user");
		}
		borrowedBooks.remove(book);
	}

	public String getCpr() {
		return cpr;
	}

	public Address getAddress() {
		return address;
	}

	void borrowBook(Book book, Calendar borrowDate) throws Exception {
		canBorrow(borrowDate);
		book.setDueDateFromBorrowDate(borrowDate);
		borrowedBooks.add(book);
	}

	public void canBorrow(Calendar borrowDate) throws TooManyBooksException, OverdueException, Exception {
		if (borrowedBooks.size() >= MAX_NUMBER_OF_BOOKS) {
			throw new TooManyBooksException(String.format("Can't borrow more than %d books",MAX_NUMBER_OF_BOOKS));
		}
		if (hasOverdueBooks(borrowDate)) {
			throw new OverdueException("Can't borrow book if user has overdue books");
		}
		if (hasFine(borrowDate)) {
			throw new Exception("Can't borrow book if user has outstanding fines");
		}
	}

	double getFine(Calendar currentDate) {
		// Use Optional to remember if the fine has been computed or not
		// If fine.isPresent(), then the fine has already been computed
		// and the value of fine is used.
		// If not fine.isPresent(), then we have to compute the value of fine.
		// However, if the fine is 0, then we need to compute the fine next time again.
		if (!fine.isPresent()) {
			double fineValue = borrowedBooks.stream()
					.filter(b -> b.isOverdue(currentDate))
					.mapToDouble(b -> b.getFine())
					.sum();
			if (fineValue == 0) {
				fine = Optional.empty();
			} else {
				fine = Optional.of(fineValue);
			}
		}
		return fine.isPresent() ? fine.get() : 0;
	}
	
	boolean hasFine(Calendar currentDate) {
		return fine.isPresent() && fine.get() != 0;
	}

	boolean hasOverdueBooks(Calendar date) {
		return borrowedBooks.stream()
				.anyMatch(b -> b.isOverdue(date));
	}

	void sendEmailReminder(EmailServer emailServer, Calendar currentDate) {
		long numberOfOverdueBooks = borrowedBooks.stream()
				.filter(b -> b.isOverdue(currentDate))
				.count();
		emailServer.sendEmail(email, 
				"Overdue book(s)", 
				String.format("You have %s overdue book(s)", numberOfOverdueBooks));
	}

	public void payFine(int money) {
		if (fine.get() == money) {
			fine = Optional.empty();
		} else {
			fine = Optional.of(fine.get() - money);
		}
	}

}
