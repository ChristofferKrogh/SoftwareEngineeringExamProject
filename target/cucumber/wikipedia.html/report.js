$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("use_cases/provided/add_book.feature");
formatter.feature({
  "name": "Add book",
  "description": "\tDescription: A book is added to the library\n\tActors: Administrator",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add a book successfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a book with title \"Extreme Programming\", author \"Kent Beck\", and signature \"Beck99\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iHaveABookWithTitleAuthorAndSignature(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iAddTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book with title \"Extreme Programming\", author \"Kent Beck\", and signature \"Beck99\" is added to the library",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookWithTitleAuthorAndSignatureIsAddedToTheLibrary(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Add a book when the adminstrator is not logged in",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a book with title \"Extreme Programming\", author \"Kent Beck\", and signature \"Beck99\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iHaveABookWithTitleAuthorAndSignature(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iAddTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Administrator login required\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/admin_login.feature");
formatter.feature({
  "name": "Admin login",
  "description": "\tDescription: The administrator logs into the library system and also logs out\n\tActor: Administrator",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Administrator can login",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the password is \"adminadmin\"",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.thePasswordIs(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator login succeeds",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLoginSucceeds()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the adminstrator is logged in",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdminstratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Administrator has the wrong password",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the password is \"wrong password\"",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.thePasswordIs(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator login fails",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLoginFails()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator is not logged in",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/admin_logout.feature");
formatter.feature({
  "name": "Admin logout",
  "description": "\tDescription: The administrator logs out from the library system\n\tActor: Administrator",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Administrator logs out",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "When "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator is not logged in",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/borrow_book.feature");
formatter.feature({
  "name": "Borrow Book",
  "description": "\tDescription: The user borrows a book\n\tActors: User",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "User borrows book",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a book with signature \"Beck99\" is in the library",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a user is registered with the library",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.aUserIsRegisteredWithTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book is borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookIsBorrowedByTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "User borrows book more than 10 books",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user has borrowed 10 books",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.theUserHasBorrowedBooks(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a user is registered with the library",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.aUserIsRegisteredWithTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a book with signature \"Beck99\" is in the library",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book is not borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookIsNotBorrowedByTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Can\u0027t borrow more than 10 books\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "User cannot borrow books if he has overdue books",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a book with signature \"Beck99\" is in the library",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a book with signature \"Rose11\" is in the library",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a user is registered with the library",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.aUserIsRegisteredWithTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book with signature \"Beck99\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "29 days have passed",
  "keyword": "And "
});
formatter.match({
  "location": "TimeSteps.daysHavePassed(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book with signature \"Rose11\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book with signature \"Rose11\" is not borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookWithSignatureIsNotBorrowedByTheUser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Can\u0027t borrow book if user has overdue books\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "User cannot borrow books if he has outstanding fines",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a book with signature \"Beck99\" is in the library",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a book with signature \"Rose11\" is in the library",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.aBookWithSignatureIsInTheLibrary(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a user is registered with the library",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.aUserIsRegisteredWithTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book with signature \"Beck99\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "29 days have passed",
  "keyword": "And "
});
formatter.match({
  "location": "TimeSteps.daysHavePassed(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book with signature \"Beck99\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user borrows the book with signature \"Rose11\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserBorrowsTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book with signature \"Rose11\" is not borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookWithSignatureIsNotBorrowedByTheUser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Can\u0027t borrow book if user has outstanding fines\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/overdue_book.feature");
formatter.feature({
  "name": "Overdue book",
  "description": "\tDescription: Contains the business rules when a book is overdue\n\t\tThis is more a feature describing a business rule then a \n\t\tuse case, but Cucumber scenarios can also be used here as\n\t\tthese business rules are given and must be read by\n\t\tdomain experts",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Overdue book after 28 days",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user has borrowed a book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.thatTheUserHasBorrowedABook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay no fine",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayNoFine()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "29 days have passed",
  "keyword": "Given "
});
formatter.match({
  "location": "TimeSteps.daysHavePassed(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the fine for one overdue book is 100 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theFineForOneOverdueBookIsDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has overdue books",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasOverdueBooks()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Not overdue book if books was borrowed less than or equal 28 days",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "the user has borrowed a book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.thatTheUserHasBorrowedABook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "28 days have passed",
  "keyword": "And "
});
formatter.match({
  "location": "TimeSteps.daysHavePassed(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has no overdue books",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasNoOverdueBooks()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay no fine",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayNoFine()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/pay_fine.feature");
formatter.feature({
  "name": "Pay fine",
  "description": "\tDescription: The user pays his fines\n\tActors: User",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Pay fine for overdue books",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user has an overdue book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aUserHasAnOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user pays 100 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theUserPaysDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user can borrow books again",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theUserCanBorrowBooksAgain()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay no fine",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayNoFine()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Pay fine and then the user has another book which is overdue",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user has an overdue book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aUserHasAnOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user pays 100 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theUserPaysDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user can borrow books again",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theUserCanBorrowBooksAgain()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has another overdue book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.theUserHasAnotherOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user pays 100 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theUserPaysDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user can borrow books again",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theUserCanBorrowBooksAgain()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay no fine",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayNoFine()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Pay partial fine for overdue books",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user has an overdue book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.aUserHasAnOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 100 DKK",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user pays 50 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theUserPaysDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user cannot borrow books",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theUserCannotBorrowBooks()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay a fine of 50 DKK",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayAFineOfDKK(double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user pays 50 DKK",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserPaysDKK(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user can borrow books again",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theUserCanBorrowBooksAgain()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has to pay no fine",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasToPayNoFine()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/register_user.feature");
formatter.feature({
  "name": "Register user",
  "description": "\tDescription: The administrator registers a user of the library\n\tActors: Administrator",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Register a user",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "there is a user with CPR \"020563-4886\", name \"Helena M. Bertelsen\", e-mail \"HelenaMBertelsen@rhyta.com \"",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.thereIsAUserWithCPRNameEMail(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has address street \"Slåenhaven 50\", post code 4560, and city \"Vig\"",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasAddressWithStreetPostCodeAndCity(String,int,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator registers the user",
  "keyword": "When "
});
formatter.match({
  "location": "UserSteps.theAdministratorRegistersTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user is a registered user of the library",
  "keyword": "Then "
});
formatter.match({
  "location": "UserSteps.theUserIsARegisteredUserOfTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Register a user when not the administrator",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "there is a user with CPR \"020563-4886\", name \"Helena M. Bertelsen\", e-mail \"HelenaMBertelsen@rhyta.com \"",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.thereIsAUserWithCPRNameEMail(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user has address street \"Slåenhaven 50\", post code 4560, and city \"Vig\"",
  "keyword": "And "
});
formatter.match({
  "location": "UserSteps.theUserHasAddressWithStreetPostCodeAndCity(String,int,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator registers the user",
  "keyword": "When "
});
formatter.match({
  "location": "UserSteps.theAdministratorRegistersTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Administrator login required\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Register a user that is already registered",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "a user is registered with the library",
  "keyword": "Given "
});
formatter.match({
  "location": "UserSteps.aUserIsRegisteredWithTheLibrary()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator registers the user again",
  "keyword": "When "
});
formatter.match({
  "location": "UserSteps.theAdministratorRegistersTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"User is already registered\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/return_book.feature");
formatter.feature({
  "name": "Return Book",
  "description": "\tDescriptions: The user returns a book\n\tActors: User",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "A user returns a borrowed book",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the user has borrowed a book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.thatTheUserHasBorrowedABook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book is not borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookIsNotBorrowedByTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "A user returns a book he hasn\u0027t borrowed",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the user has not borrowed the book",
  "keyword": "Given "
});
formatter.match({
  "location": "BookSteps.thatTheUserHasNotBorrowedTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user returns the book",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.theUserReturnsTheBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the book is not borrowed by the user",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.theBookIsNotBorrowedByTheUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"book is not borrowed by the user\"",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/search_book.feature");
formatter.feature({
  "name": "Search books",
  "description": "\tDescription: A user (either an ordinary user or the administrator) searches for books by \n\t\t\t\t providing a substring of either the title, author, or signature field\n\tActors: user",
  "keyword": "Feature"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Searching for a substring of the signature",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for the text \"99\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I find the book with signature \"Beck99\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iFindTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Searching for a substring of the title",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for the text \"Extreme\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I find the book with signature \"Beck99\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iFindTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Searching for a substring of the author",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is not logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsNotLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for the text \"Seb\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I find the book with signature \"Rose11\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iFindTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Searching also works when the administrator is logged in",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for the text \"Seb\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I find the book with signature \"Rose11\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iFindTheBookWithSignature(String)"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "No books match the criteria",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I search for the text \"Ian\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I don\u0027t find any book",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iDonTFindAnyBook()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "The library has a set of books",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "these books are contained in the library",
  "rows": [
    {
      "cells": [
        "Extreme Programming",
        "Kent Beck",
        "Beck99"
      ]
    },
    {
      "cells": [
        "Test Driven Development",
        "Kent Beck",
        "Beck02"
      ]
    },
    {
      "cells": [
        "Lean Software Development",
        "Mary Poppendieck and Tom Poppendieck",
        "Pop07"
      ]
    },
    {
      "cells": [
        "Cucumber for Java",
        "Seb Rose",
        "Rose11"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.theseBooksAreContainedInTheLibrary(String\u003e\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Find more than one book",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I search for the text \"Beck\"",
  "keyword": "When "
});
formatter.match({
  "location": "BookSteps.iSearchForTheText(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I find a book with signatures \"Beck99\" and \"Beck02\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iFindABookWithSignaturesAnd(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases/provided/send_reminder.feature");
formatter.feature({
  "name": "Send reminder",
  "description": "\tDescription: Send an e-mail to all users with overdue books\n\tActors: Administrator",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Send reminder e-mail",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "there is a user with one overdue book",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.thereIsAUserWithOneOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator sends a reminder e-mail",
  "keyword": "When "
});
formatter.match({
  "location": "EMailSteps.theAdministratorSendsAReminderEMail()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "then the user receives an email with subject \"Overdue book(s)\" and text \"You have 1 overdue book(s)\"",
  "keyword": "Then "
});
formatter.match({
  "location": "EMailSteps.thenTheUserReceivesAnEmailWithSubjectAndText(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "The administrator is not logged in",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "that the administrator is logged in",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginLogoutSteps.thatTheAdministratorIsLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "there is a user with one overdue book",
  "keyword": "And "
});
formatter.match({
  "location": "BookSteps.thereIsAUserWithOneOverdueBook()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator logs out",
  "keyword": "And "
});
formatter.match({
  "location": "LoginLogoutSteps.theAdministratorLogsOut()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the administrator sends a reminder e-mail",
  "keyword": "When "
});
formatter.match({
  "location": "EMailSteps.theAdministratorSendsAReminderEMail()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get the error message \"Administrator login required\"",
  "keyword": "Then "
});
formatter.match({
  "location": "BookSteps.iGetTheErrorMessage(String)"
});
formatter.result({
  "status": "passed"
});
});