package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
//import javax.swing.SwingConstants;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.xml.stream.events.StartDocument;
import javax.swing.event.ListSelectionListener;

import dtu.planning.app.PlanningApp;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;
import dtu.planning.app.OperationNotAllowedException;


public class CreateProjectScreen {

	private ProjectsScreen parentWindow;
	private CreateActivitiesScreen createActivitiesScreen;
	private PlanningApp planningApp;
	private JPanel panelCreateProject;
	private JPanel panelCreateProjectSuccess;
	private JTextField searchField;
	private JTextField projectNameField;
	private JLabel leaderReminderField;
	private JLabel lblSuccessMessage;
	private JTextField startDayField;
	private JTextField startMonthField;
	private JTextField startYearField;
	private JTextField endDayField;
	private JTextField endMonthField;
	private JTextField endYearField;
	private JComboBox<String> internalOrExternalComboBox;
	private JList<Employee> listSearchResult;
	private DefaultListModel<Employee> searchResults;
	private Project project;
	private JButton btnBack;
	
	public CreateProjectScreen(PlanningApp planningApp, ProjectsScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelCreateProject = new JPanel();
		parentWindow.addPanel(panelCreateProject);
		panelCreateProject.setLayout(null);
		panelCreateProject.setBorder(BorderFactory.createTitledBorder(
                "Create Project"));
		
		// ------------ Success Message --------------------
		panelCreateProjectSuccess = new JPanel();
		parentWindow.addPanel(panelCreateProjectSuccess);
		panelCreateProjectSuccess.setLayout(null);
		panelCreateProjectSuccess.setBorder(BorderFactory.createTitledBorder(
                "Success Message"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelCreateProjectSuccess.setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateProjectSuccess.add(btnBack);
		
		JPanel panelSuccessMessage = new JPanel();
		panelSuccessMessage.setBounds(50, 65, 330, 310);
		panelCreateProjectSuccess.add(panelSuccessMessage);
		panelSuccessMessage.setLayout(null);
		
		lblSuccessMessage = new JLabel("");
		lblSuccessMessage.setVerticalAlignment(SwingConstants.TOP);
		lblSuccessMessage.setBounds(0, 0, 330, 310);
		panelSuccessMessage.add(lblSuccessMessage);
		
		JButton btnCreateProjectSuccess = new JButton("Create Project");
		btnCreateProjectSuccess.setBounds(225, 385, 150, 50);
		panelCreateProjectSuccess.add(btnCreateProjectSuccess);
		btnCreateProjectSuccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCreateProjectSuccess.setVisible(false);
				clear();
				panelCreateProject.setVisible(true);
			}
		});
		
		JButton btnCreateActivity = new JButton("Create Activity");
		btnCreateActivity.setBounds(25, 385, 150, 50);
		panelCreateProjectSuccess.add(btnCreateActivity);
		btnCreateActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createActivitiesScreen.setProject(project);
				setVisible(false);
				panelCreateProjectSuccess.setVisible(false);
				clear();
				createActivitiesScreen.setVisible(true);
			}
				
		});
		// -------------------------------------------------
		
		JButton btnCreateProject = new JButton("Create Project");
		btnCreateProject.setBounds(225, 385, 150, 50);
		panelCreateProject.add(btnCreateProject);
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProject();
				if (project != null) {
					setVisible(false);
					setSuccessMessage();
					panelCreateProjectSuccess.setVisible(true);
				}
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateProject.add(btnBack);
		
		JLabel lblProjectName = new JLabel("Project Name: ");
		lblProjectName.setBounds(100, 28, 100, 30);
		panelCreateProject.add(lblProjectName);
		
		projectNameField = new JTextField();
		projectNameField.setBounds(210, 28, 140, 30);
		panelCreateProject.add(projectNameField);
		
		String[] comboBoxItems = {"Internal", "External"};
		internalOrExternalComboBox = new JComboBox<>(comboBoxItems);
		internalOrExternalComboBox.setBounds(90, 60, 150, 50);
		panelCreateProject.add(internalOrExternalComboBox);
		
		// ------------- Start and end dates ---------------------
		
		JLabel lblDateFormat = new JLabel("Day / Month / Year");
		lblDateFormat.setBounds(215, 110, 200, 30);
		panelCreateProject.add(lblDateFormat);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(100, 135, 100, 30);
		panelCreateProject.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(100, 165, 100, 30);
		panelCreateProject.add(lblEndDate);
		
		startDayField = new JTextField();
		startDayField.setBounds(210, 135, 30, 30);
		panelCreateProject.add(startDayField);
		
		startMonthField = new JTextField();
		startMonthField.setBounds(250, 135, 30, 30);
		panelCreateProject.add(startMonthField);
		
		startYearField = new JTextField();
		startYearField.setBounds(290, 135, 60, 30);
		panelCreateProject.add(startYearField);
		
		endDayField = new JTextField();
		endDayField.setBounds(210, 165, 30, 30);
		panelCreateProject.add(endDayField);
		
		endMonthField = new JTextField();
		endMonthField.setBounds(250, 165, 30, 30);
		panelCreateProject.add(endMonthField);
		
		endYearField = new JTextField();
		endYearField.setBounds(290, 165, 60, 30);
		panelCreateProject.add(endYearField);
		
		JLabel separatorOne = new JLabel("/");
		separatorOne.setBounds(240, 135, 30, 30);
		panelCreateProject.add(separatorOne);
		
		JLabel separatorTwo = new JLabel("/");
		separatorTwo.setBounds(280, 135, 30, 30);
		panelCreateProject.add(separatorTwo);
		
		JLabel separatorThree = new JLabel("/");
		separatorThree.setBounds(240, 165, 30, 30);
		panelCreateProject.add(separatorThree);
		
		JLabel separatorFour = new JLabel("/");
		separatorFour.setBounds(280, 165, 30, 30);
		panelCreateProject.add(separatorFour);
		
		// --------------------------------------------------------
		
		JLabel lblLeader = new JLabel("Project Leader:");
		lblLeader.setBounds(100, 210, 150, 30);
		panelCreateProject.add(lblLeader);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		searchField.setBounds(210, 210, 140, 30);
		panelCreateProject.add(searchField);
		
		leaderReminderField = new JLabel();
		leaderReminderField.setBounds(5, 280, 87, 45);
		panelCreateProject.add(leaderReminderField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(165, 245, 100, 30);
		panelCreateProject.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		searchResults = new DefaultListModel<>();
		listSearchResult = new JList<Employee>(searchResults);
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listSearchResult.getSelectedIndex() == -1) {
					searchField.setText("");
				} else {
					searchField.setText(listSearchResult.getSelectedValue().getName());
				}
				
			}
		});
		listSearchResult.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(100, 280, 250, 100);
		panelCreateProject.add(listScrollPane);
		
		createActivitiesScreen = new CreateActivitiesScreen(planningApp, parentWindow);
	}

	public void setVisible(boolean aFlag) {
		panelCreateProject.setVisible(aFlag);
	}
	
	public void addPanel(JPanel panel) {
		parentWindow.addPanel(panel);
	}
	
	protected void searchEmployees() {
		// Show a reminder to select the employee
		StringBuffer b = new StringBuffer();
		b.append("<html><b>Reminder:</b> Select the<br>project leader</html>");
		leaderReminderField.setText(b.toString());
		
		searchResults.clear();
		try {
			planningApp.searchForEmployeesByName(searchField.getText())
			.forEach((m) -> {searchResults.addElement(m);});
		} catch (OperationNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setSuccessMessage() {
		StringBuffer b = new StringBuffer();
		b.append("<html><h1>The project \"");
		b.append(project.getName());
		b.append("\" was created!</h1><br>");
		b.append("<p>You can now create an activity for this project or you can choose to create a new project.</p></html>");		
		lblSuccessMessage.setText(b.toString());
	}
	
	public void clear() {
		searchField.setText("");
		searchResults.clear();
		projectNameField.setText("");
		leaderReminderField.setText("");
		startDayField.setText("");
		startMonthField.setText("");
		startYearField.setText("");
		endDayField.setText("");
		endMonthField.setText("");
		endYearField.setText("");
		lblSuccessMessage.setText("");
		internalOrExternalComboBox.setSelectedIndex(0);
		project = null;
	}
	
	public void createProject() {
		String name = projectNameField.getText();
		boolean isProjectInternal = internalOrExternalComboBox.getSelectedItem().toString().matches("Internal");
		if (name.equals("")) {
			setConsoleMessage("The project needs a name");
		} else {
			StringBuffer b = new StringBuffer();
        	b.append("<html>");
			// Create the project
			Project project = planningApp.createProject(name, isProjectInternal);
			
			// Add a project leader to the project if possible
			if (listSearchResult.getSelectedIndex() == -1) {
				b.append("The project was created without a project leader<br>");
			} else {
				try {
					planningApp.setProjectLeader(project.getProjectNumber(), listSearchResult.getSelectedValue().getInitials());
				} catch (OperationNotAllowedException e) {
					b.append(e.getMessage() + "<br>");
				}
			}
			
			// Add a start date to the project if possible
			if (startDayField.getText().equals("") || 
					startMonthField.getText().equals("") || 
					startYearField.getText().equals("")) { // All fields need to be filled out
				b.append("The project was created without a start date<br>");
			} else {
				try {
					int day = Integer.parseInt(startDayField.getText());
					int month = Integer.parseInt(startMonthField.getText());
					int year = Integer.parseInt(startYearField.getText());
					GregorianCalendar startDate = new GregorianCalendar(year, month - 1, day);
					planningApp.editStartDateOfProject(startDate, project.getProjectNumber());
				} catch (Exception e) {
					b.append("The project was created without a start date:<br>Something Went Wrong ");			
					b.append(e.getMessage() + "<br>");
				}
			}
			
			// Add an end date to the project if possible
			if (endDayField.getText().equals("") || 
					endMonthField.getText().equals("") || 
					endYearField.getText().equals("")) { // All fields need to be filled out
				b.append("The project was created without an end date<br>");
			} else {
				try {
					int day = Integer.parseInt(endDayField.getText());
					int month = Integer.parseInt(endMonthField.getText());
					int year = Integer.parseInt(endYearField.getText());
					GregorianCalendar endDate = new GregorianCalendar(year, month - 1, day);
					planningApp.editEndDateOfProject(endDate, project.getProjectNumber());
				} catch (Exception e) {
					b.append("The project was created without an end date:<br>Something went wrong ");
					b.append(e.getMessage() + "<br>");
				}
			}
			b.append("</html>");
			setConsoleMessage(b.toString());
			
			this.project = project;
		}
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}
}
