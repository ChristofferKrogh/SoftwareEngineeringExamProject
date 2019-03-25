package dtu.library.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class represents a book with title, author, and signature, where signature
 * is a unique key used by the librarian to identify the book. Very often it is 
 * composed of the first letters of the authors plus the year the book was published.
 * @author Hubert
 *
 */
public class Book {
	private static final int OVERDUE_FINE = 100;
	private String title;
	private String author;
	private String signature;
	private Calendar dueDate;
	private static final int MAX_NUMBER_OF_DAYS = 28;

	public Book(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSignature() {
		return signature;
	}

	/**
	 * 
	 * @param searchText
	 * @return returns true if the searchText appears as substring in either title, author, or signature field.
	 */
	public boolean match(String searchText) {
		return signature.contains(searchText) || title.contains(searchText) || author.contains(searchText);
	}

	void setDueDateFromBorrowDate(Calendar borrowDate) {
		dueDate = new GregorianCalendar();
		dueDate.setTime(borrowDate.getTime());
		dueDate.add(Calendar.DAY_OF_YEAR, MAX_NUMBER_OF_DAYS);
	}

	double getFine() {
		return OVERDUE_FINE;
	}

	boolean isOverdue(Calendar currentDate) {
		// Precondition: isOverdue is only called for books that 
		// are borrowed and thus have their dueDate set
		// The assert statement will not have full condition coverage, as the
		// precondition should never fail. If the precondition fails, a client has made
		// a mistake, i.e., the program has a bug.
		assert dueDate != null;  
		return currentDate.after(dueDate);
	}

}
