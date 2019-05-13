package dtu.planning.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Calendar;

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
//import javax.swing.SwingConstants;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.xml.stream.events.StartDocument;

import dtu.planning.app.PlanningApp;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;
import dtu.planning.app.OperationNotAllowedException;


public class EditProjectScreen {

	private ProjectsScreen parentWindow;
	private PlanningApp planningApp;
	private EditActivitiesScreen editActivitiesScreen; 
	private JPanel panelEditProject;
	private JPanel panelSuccessMessage;
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
	private Project project;
	
	public EditProjectScreen(PlanningApp planningApp, ProjectsScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelEditProject = new JPanel();
		parentWindow.addPanel(panelEditProject);
		panelEditProject.setLayout(null);
		panelEditProject.setBorder(BorderFactory.createTitledBorder(
                "Edit Project"));
		
		JButton btnCreateProject = new JButton("Save Changes");
		btnCreateProject.setBounds(225, 385, 150, 50);
		panelEditProject.add(btnCreateProject);
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editProject();
				panelSuccessMessage.setVisible(true);
			}
		});
		
		JButton btnEditActivities = new JButton("Edit Activities");
		btnEditActivities.setBounds(25, 385, 150, 50);
		panelEditProject.add(btnEditActivities);
		btnEditActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Jeg forestiller mig, at det bliver noget i stil med:
				editActivitiesScreen.setProject(project);
				setVisible(false);
				editActivitiesScreen.setVisible(true);
				//–––––––––––––––––––––––––––––-
				// project.getActivities().forEach((a)-> {System.out.println(a + " (" + a.getExpectedAmountOfHours() + " hours)" + " - " + a.getAssignedEmployees());});
			}
		});
		
		// -------Success Message-----------
		panelSuccessMessage = new JPanel();
		panelEditProject.add(panelSuccessMessage);
		panelSuccessMessage.setBounds(50, 110, 300, 200);
		panelSuccessMessage.setLayout(null);
		panelSuccessMessage.setBorder(BorderFactory.createLineBorder(Color.green, 5));
		panelSuccessMessage.setBackground(Color.white);
		panelSuccessMessage.setVisible(false);
		JLabel lblSuccessMessage = new JLabel("The changes were saved");
		lblSuccessMessage.setBounds(75, 50, 200, 30);
		panelSuccessMessage.add(lblSuccessMessage);
		
		JButton btnSuccessMessage = new JButton("Great!");
		btnSuccessMessage.setBounds(75, 100, 150, 50);
		panelSuccessMessage.add(btnSuccessMessage);
		btnSuccessMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSuccessMessage.setVisible(false);
			}
		});
		// --------------------------------
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelSuccessMessage.setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelEditProject.add(btnBack);
		
		JLabel lblProjectName = new JLabel("Project Name: ");
		lblProjectName.setBounds(100, 28, 100, 30);
		panelEditProject.add(lblProjectName);
		
		projectNameField = new JTextField();
		projectNameField.setBounds(210, 28, 140, 30);
		panelEditProject.add(projectNameField);
		
		String[] comboBoxItems = {"Internal", "External"};
		internalOrExternalComboBox = new JComboBox<>(comboBoxItems);
		internalOrExternalComboBox.setBounds(90, 60, 150, 50);
		panelEditProject.add(internalOrExternalComboBox);
		
		// ------------- Start and end dates ---------------------
		
		JLabel lblDateFormat = new JLabel("Day / Month / Year");
		lblDateFormat.setBounds(215, 110, 200, 30);
		panelEditProject.add(lblDateFormat);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(100, 135, 100, 30);
		panelEditProject.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(100, 165, 100, 30);
		panelEditProject.add(lblEndDate);
		
		startDayField = new JTextField();
		startDayField.setBounds(210, 135, 30, 30);
		panelEditProject.add(startDayField);
		
		startMonthField = new JTextField();
		startMonthField.setBounds(250, 135, 30, 30);
		panelEditProject.add(startMonthField);
		
		startYearField = new JTextField();
		startYearField.setBounds(290, 135, 60, 30);
		panelEditProject.add(startYearField);
		
		endDayField = new JTextField();
		endDayField.setBounds(210, 165, 30, 30);
		panelEditProject.add(endDayField);
		
		endMonthField = new JTextField();
		endMonthField.setBounds(250, 165, 30, 30);
		panelEditProject.add(endMonthField);
		
		endYearField = new JTextField();
		endYearField.setBounds(290, 165, 60, 30);
		panelEditProject.add(endYearField);
		
		JLabel separatorOne = new JLabel("/");
		separatorOne.setBounds(240, 135, 30, 30);
		panelEditProject.add(separatorOne);
		
		JLabel separatorTwo = new JLabel("/");
		separatorTwo.setBounds(280, 135, 30, 30);
		panelEditProject.add(separatorTwo);
		
		JLabel separatorThree = new JLabel("/");
		separatorThree.setBounds(240, 165, 30, 30);
		panelEditProject.add(separatorThree);
		
		JLabel separatorFour = new JLabel("/");
		separatorFour.setBounds(280, 165, 30, 30);
		panelEditProject.add(separatorFour);
		
		// --------------------------------------------------------
		
		JLabel lblLeader = new JLabel("Project Leader:");
		lblLeader.setBounds(100, 210, 150, 30);
		panelEditProject.add(lblLeader);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		searchField.setBounds(210, 210, 140, 30);
		panelEditProject.add(searchField);
		
		leaderReminderField = new JLabel();
		leaderReminderField.setBounds(5, 280, 87, 45);
		panelEditProject.add(leaderReminderField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(165, 245, 100, 30);
		panelEditProject.add(btnSearch);
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
		panelEditProject.add(listScrollPane);
		
		editActivitiesScreen = new EditActivitiesScreen(planningApp, this);

	}

	public void setVisible(boolean aFlag) {
		panelEditProject.setVisible(aFlag);
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
	
	public void setProject(Project project) {
		this.project = project;
		projectNameField.setText(project.getName());
		if (project.isProjectInternal()) {
			internalOrExternalComboBox.setSelectedIndex(0);
		} else {
			internalOrExternalComboBox.setSelectedIndex(1);
		}
		
		if (project.hasProjectLeader()) {
			searchField.setText(project.getProjectLeader().getName());
		}
		
		startDayField.setText(Integer.toString(project.getStartDate().get(Calendar.DATE)));
		startMonthField.setText(Integer.toString(project.getStartDate().get(Calendar.MONTH) + 1));
		startYearField.setText(Integer.toString(project.getStartDate().get(Calendar.YEAR)));
		endDayField.setText(Integer.toString(project.getEndDate().get(Calendar.DATE)));
		endMonthField.setText(Integer.toString(project.getEndDate().get(Calendar.MONTH) + 1));
		endYearField.setText(Integer.toString(project.getEndDate().get(Calendar.YEAR)));
		
		// TODO: include activities
	}
	
	public void editProject() {
		StringBuffer b = new StringBuffer();
		b.append("<html>");
		// Update project name
		if (!projectNameField.getText().equals("")) {
			try {
				planningApp.setNameOfProject(projectNameField.getText(), project.getProjectNumber());
			} catch (OperationNotAllowedException e) {
				b.append(e.getMessage() + "<br>");
			}
		}
		
		// Set internal/external status
		boolean isProjectInternal = internalOrExternalComboBox.getSelectedItem().toString().toLowerCase().equals("internal");
		try {
			planningApp.setProjectInternal(isProjectInternal, project.getProjectNumber());
		} catch (OperationNotAllowedException e) {
			b.append(e.getMessage() + "<br>");
		}
		
		// Set project leader if possible
		if (listSearchResult.getSelectedIndex() != -1) {
			try {
				planningApp.setProjectLeader(project.getProjectNumber(), listSearchResult.getSelectedValue().getInitials());
			} catch (OperationNotAllowedException e) {
				b.append(e.getMessage() + "<br>");
			}
		}
		
		// set start date if possible
		if (!(startDayField.getText().equals("") || 
				startMonthField.getText().equals("") || 
				startYearField.getText().equals(""))) { // All fields need to be filled out
			try {
				int day = Integer.parseInt(startDayField.getText());
				int month = Integer.parseInt(startMonthField.getText());
				int year = Integer.parseInt(startYearField.getText());
				GregorianCalendar startDate = new GregorianCalendar(year, month - 1, day);
				planningApp.editStartDateOfProject(startDate, project.getProjectNumber());
			} catch (Exception e) {
				b.append(e.getMessage() + "<br>");
			}
		}
		
		// set end date if possible
		if (!(endDayField.getText().equals("") || 
				endMonthField.getText().equals("") || 
				endYearField.getText().equals(""))) { // All fields need to be filled out
			try {
				int day = Integer.parseInt(endDayField.getText());
				int month = Integer.parseInt(endMonthField.getText());
				int year = Integer.parseInt(endYearField.getText());
				GregorianCalendar endDate = new GregorianCalendar(year, month - 1, day);
				planningApp.editEndDateOfProject(endDate, project.getProjectNumber());
			} catch (Exception e) {
				b.append(e.getMessage() + "<br>");
			}
		}
		b.append("</html>");
		setConsoleMessage(b.toString());
		
	}
	
	public void addPanel(JPanel panel) {
		parentWindow.addPanel(panel);
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}
	
}
