package dtu.library.acceptance_tests;

import static org.mockito.Mockito.mock;

import dtu.library.app.EmailServer;
import dtu.library.app.LibraryApp;

public class MockEmailServerHolder {
	private EmailServer mockEmailServer = mock(EmailServer.class);
	
	public MockEmailServerHolder(LibraryApp libraryApp) {
		libraryApp.setEmailServer(mockEmailServer);
	}

	public EmailServer getMockEmailServer() {
		return mockEmailServer;
	}

	public void setMockEmailServer(EmailServer mockEmailServer) {
		this.mockEmailServer = mockEmailServer;
	}
}
