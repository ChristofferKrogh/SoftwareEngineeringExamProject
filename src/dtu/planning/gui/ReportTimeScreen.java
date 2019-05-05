package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dtu.planning.app.PlanningApp;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;

public class ReportTimeScreen {
	// flow idea: 
	// Select which employee to report time for
	// Show all the activities that the employee is assigned and which project they belong to
	// Select the activity to report time on
	// Give amount of time with units and date (the date could be set to today as standard)
	// Finally, the time can be reported. Afterwards, a success message will be shown
	
	// Implementation:
	// There will be one master panel that contains three other panels. Only one of these panels will be shown at a time
	// The first panel will say "Step 1" and here you will be able to search for and select an employee.
	// At the bottom of this panel there will be a button to take you to the next panel. You must select an employee to move forward
	// The second panel will say "Step 2" and all the activities that the employee is assigned to will be shown.
	// At the bottom of the second panel there will be a previous and next button. You must select an Activity to move forward.
	// In the third panel it will be possible to give the amount of time in units and date
	// At the bottom of the third panel there will be a previous button and a report time button
	
	private MainScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelReportTime;
	private JPanel panelSelectEmployee;
	private JPanel panelSelectActivity;
	private JPanel panelGiveTime;
	private JTextField searchField;
	private JTextField projectNameField;
	private JLabel lblPhase;
	private JLabel employeeReminderField;
	private JLabel lblSuccessMessage;
	private JTextField startDayField;
	private JTextField startMonthField;
	private JTextField startYearField;
	private JTextField endDayField;
	private JTextField endMonthField;
	private JTextField endYearField;
	private JComboBox<String> internalOrExternalComboBox;
	private JList<Employee> listSearchResult;
	private JList<Activity> listRelevantActivities;
	private DefaultListModel<Employee> searchResults;
	private DefaultListModel<Activity> relevantActivities;
	private Employee employee;
	private Activity activity;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnPrevious;
	
	public ReportTimeScreen(PlanningApp planningApp, MainScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}

	private void initialize() {
		panelReportTime = new JPanel();
		parentWindow.addPanel(panelReportTime);
		panelReportTime.setLayout(null);
		panelReportTime.setBorder(BorderFactory.createTitledBorder(
                "Report Time"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelReportTime.add(btnBack);
		
		// ------------- Step 1 -------------------
		panelSelectEmployee = new JPanel();
		panelSelectEmployee.setBounds(0, 28, 350, 500);
		panelReportTime.add(panelSelectEmployee);
		panelSelectEmployee.setLayout(null);
		panelSelectEmployee.setVisible(true);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		StringBuffer b = new StringBuffer();
		b.append("<html><h1>&nbsp;Step 1</h1>Select Employee</html>");
		lblPhase.setText(b.toString());
		panelSelectEmployee.add(lblPhase);
		
		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(80, 100, 150, 30);
		panelSelectEmployee.add(lblEmployee);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		searchField.setBounds(190, 100, 140, 30);
		panelSelectEmployee.add(searchField);
		
		employeeReminderField = new JLabel();
		employeeReminderField.setBounds(5, 170, 87, 45);
		panelSelectEmployee.add(employeeReminderField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(160, 135, 100, 30);
		panelSelectEmployee.add(btnSearch);
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

        listScrollPane.setBounds(80, 170, 250, 100);
		panelSelectEmployee.add(listScrollPane);
		
		btnNext = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Next</h2></html>");
		btnNext.setText(b.toString());
		btnNext.setBounds(245, 290, 90, 40);
		panelSelectEmployee.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSearchResult.getSelectedIndex() == -1) {
					System.out.println("You need to select an employee");
				} else {
					employee = listSearchResult.getSelectedValue();
					panelSelectEmployee.setVisible(false);
					panelSelectActivity.setVisible(true);
				}
			}
		});
		// ----------------------------------------
		
		// ------------- Step 2 -------------------
		panelSelectActivity = new JPanel();
		panelSelectActivity.setBounds(0, 28, 350, 500);
		panelReportTime.add(panelSelectActivity);
		panelSelectActivity.setLayout(null);
		panelSelectActivity.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h1>&nbsp;Step 2</h1>Select Activity</html>");
		lblPhase.setText(b.toString());
		panelSelectActivity.add(lblPhase);
		
		JLabel lblActivity = new JLabel();
		b = new StringBuffer();
		b.append("<html>All activities for this employee can be found in the list below</html>");
		lblActivity.setText(b.toString());
		lblActivity.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivity.setVerticalAlignment(SwingConstants.TOP);
		lblActivity.setBounds(80, 80, 250, 40);
		panelSelectActivity.add(lblActivity);
	
		relevantActivities = new DefaultListModel<>();
		listRelevantActivities = new JList<Activity>(relevantActivities);
		listRelevantActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listRelevantActivities.setSelectedIndex(0);
		// TODO: implement details about activities
//		listRelevantActivities.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				if (listRelevantActivities.getSelectedIndex() == -1) {
//				} else {
//				}
//				
//			}
//		});
		listRelevantActivities.setVisibleRowCount(7);
        listScrollPane = new JScrollPane(listRelevantActivities);

        listScrollPane.setBounds(80, 120, 250, 150);
		panelSelectActivity.add(listScrollPane);
		
		btnNext = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Next</h2></html>");
		btnNext.setText(b.toString());
		btnNext.setBounds(245, 290, 90, 40);
		panelSelectActivity.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listRelevantActivities.getSelectedIndex() == -1) {
					System.out.println("You need to select an activity");
				} else {
					activity = listRelevantActivities.getSelectedValue();
					panelSelectEmployee.setVisible(false);
					panelSelectActivity.setVisible(false);
					panelGiveTime.setVisible(true);
				}
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelSelectActivity.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(true);
				panelSelectActivity.setVisible(false);
				panelGiveTime.setVisible(false);
			}
		});
		// ----------------------------------------
		
		// ------------- Step 3 -------------------
		panelGiveTime = new JPanel();
		panelGiveTime.setBounds(0, 28, 350, 500);
		panelReportTime.add(panelGiveTime);
		panelGiveTime.setLayout(null);
		panelGiveTime.setVisible(false);
		// ----------------------------------------

	}

	protected void searchEmployees() {
		// Show a reminder to select the employee
		StringBuffer b = new StringBuffer();
		b.append("<html><b>Reminder:</b> Select the<br>employee</html>");
		employeeReminderField.setText(b.toString());
		
		searchResults.clear();
		planningApp.searchForEmployeesByName(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});		
	}

	protected void clear() {
		panelSelectEmployee.setVisible(true);
		panelSelectActivity.setVisible(false);
		panelGiveTime.setVisible(false);
		employeeReminderField.setText("");
		searchField.setText("");
		searchResults.clear();
		
	}

	public void setVisible(boolean aFlag) {
		panelReportTime.setVisible(aFlag);
		
	}

}
