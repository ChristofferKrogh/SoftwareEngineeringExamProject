Feature: Return Book
	Descriptions: The user returns a book
	Actors: User

Scenario: A user returns a borrowed book
	Given that the user has borrowed a book
	When the user returns the book
	Then the book is not borrowed by the user

Scenario: A user returns a book he hasn't borrowed
	Given that the user has not borrowed the book
	When the user returns the book
	Then the book is not borrowed by the user
	And I get the error message "book is not borrowed by the user"		