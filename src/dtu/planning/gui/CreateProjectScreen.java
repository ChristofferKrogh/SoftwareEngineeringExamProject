package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.stream.events.StartDocument;

import dtu.planning.app.PlanningApp;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;
import dtu.planning.app.OperationNotAllowedException;


public class CreateProjectScreen {

	private ProjectsScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelCreateProject;
	private JTextField searchField;
	private JTextField projectNameField;
	private JLabel leaderReminderField;
	private JTextField startDayField;
	private JTextField startMonthField;
	private JTextField startYearField;
	private JTextField endDayField;
	private JTextField endMonthField;
	private JTextField endYearField;
	private JComboBox<String> internalOrExternalComboBox;
	private JList<Employee> listSearchResult;
	private DefaultListModel<Employee> searchResults;
//	private JLabel lblSearchResultDetail;
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
		
		JButton btnCreateProject = new JButton("Create Project");
		btnCreateProject.setBounds(225, 385, 150, 50);
		panelCreateProject.add(btnCreateProject);
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProject();
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
//		listSearchResult.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//		        if (e.getValueIsAdjusting() == false) {
//
//		            if (listSearchResult.getSelectedIndex() == -1) {
//		            	lblSearchResultDetail.setText("");
//
//		            } else {
//		            	lblSearchResultDetail.setText(new ProjectPrinter(listSearchResult.getSelectedValue()).printDetail());
//		            }
//		        }
//			}
//		});
		listSearchResult.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(100, 280, 250, 100);
		panelCreateProject.add(listScrollPane);

	}

	public void setVisible(boolean aFlag) {
		panelCreateProject.setVisible(aFlag);
	}
	
	protected void searchEmployees() {
		// Show a reminder to select the employee
		StringBuffer b = new StringBuffer();
		b.append("<html><b>Reminder:</b> Select the<br>project leader</html>");
		leaderReminderField.setText(b.toString());
		
		searchResults.clear();
		planningApp.searchForEmployeesByName(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});
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
		internalOrExternalComboBox.setSelectedIndex(0);
	}
	
	public void createProject() {
		String name = projectNameField.getText();
		boolean isProjectInternal = internalOrExternalComboBox.getSelectedItem().toString().matches("Internal");
		if (name.equals("")) {
			System.out.println("The project needs a name");
		} else {
			// Create the project
			Project project = planningApp.createProject(name, isProjectInternal);
			
			// Add a project leader to the project if possible
			if (listSearchResult.getSelectedIndex() == -1) {
				System.out.println("The project was created without a project leader");
			} else {
				try {
					planningApp.setProjectLeader(project.getProjectNumber(), listSearchResult.getSelectedValue().getEmployeeId());
				} catch (OperationNotAllowedException e) {
					// TODO Auto-generated catch block
					System.out.println("Something went wrong when assigning a project leader to the project");
					e.printStackTrace();
				}
			}
			
			// Add a start date to the project if possible
			if (startDayField.getText().equals("") || 
					startMonthField.getText().equals("") || 
					startYearField.getText().equals("")) { // All fields need to be filled out
				System.out.println("The project was created without a start date");
			} else {
				try {
					int day = Integer.parseInt(startDayField.getText());
					int month = Integer.parseInt(startMonthField.getText());
					int year = Integer.parseInt(startYearField.getText());
					GregorianCalendar startDate = new GregorianCalendar(year, month, day);
					planningApp.editStartDateOfProject(startDate, project.getProjectNumber());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Something went wrong when changing the start date");
				}
			}
			
			// Add an end date to the project if possible
			if (endDayField.getText().equals("") || 
					endMonthField.getText().equals("") || 
					endYearField.getText().equals("")) { // All fields need to be filled out
				System.out.println("The project was created without a end date");
			} else {
				try {
					int day = Integer.parseInt(endDayField.getText());
					int month = Integer.parseInt(endMonthField.getText());
					int year = Integer.parseInt(endYearField.getText());
					GregorianCalendar endDate = new GregorianCalendar(year, month, day);
					planningApp.editEndDateOfProject(endDate, project.getProjectNumber());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Something went wrong when changing the end date");
				}
			}
			
			clear();
		}
		
	}
}
