package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
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
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.OperationNotAllowedException;

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
	private JLabel lblPhase;
	private JLabel employeeReminderField;
	private JList<Employee> listSearchResult;
	private JList<Activity> listRelevantActivities;
	private JScrollPane listScrollPane;
	private DefaultListModel<Employee> searchResults;
	private DefaultListModel<Activity> relevantActivities;
	private List<Integer> relevantProjectNumbers;
	private Employee employee;
	private Activity activity;
	private int projectNumber;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnPrevious;
	private JComboBox<Integer> hoursComboBox;
	private JComboBox<Integer> minutesComboBox;
	private JLabel lblReportingDetails;
	private JComboBox<String> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<String> yearComboBox;
	private JPanel panelSuccess;
	private	JLabel lblSuccess;
	private float amountOfTime;
	
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
        listScrollPane = new JScrollPane(listSearchResult);

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
					findRelevantActivities();
					
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
		listRelevantActivities.setSelectedIndex(-1);
		listRelevantActivities.setVisibleRowCount(7);
        listScrollPane = new JScrollPane(listRelevantActivities);

        listScrollPane.setBounds(80, 120, 250, 150);
		panelSelectActivity.add(listScrollPane);
//		 TODO: implement details about activities
//		listRelevantActivities.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				if (listRelevantActivities.getSelectedIndex() == -1) {
//				} else {
//				}
//				
//			}
//		});
		
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
					projectNumber = relevantProjectNumbers.get(relevantActivities.indexOf(activity));
					setReportingDetails();
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
				relevantActivities.clear();
			}
		});
		// ----------------------------------------
		
		// ------------- Step 3 -------------------
		panelGiveTime = new JPanel();
		panelGiveTime.setBounds(0, 28, 380, 500);
		panelReportTime.add(panelGiveTime);
		panelGiveTime.setLayout(null);
		panelGiveTime.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h1>&nbsp;Step 3</h1>&nbsp;&nbsp;Report Time</html>");
		lblPhase.setText(b.toString());
		panelGiveTime.add(lblPhase);
		
		JLabel lblTime = new JLabel();
		b = new StringBuffer();
		b.append("<html>Select how much time you spent on the activity</html>");
		lblTime.setText(b.toString());
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setVerticalAlignment(SwingConstants.TOP);
		lblTime.setBounds(80, 80, 250, 40);
		panelGiveTime.add(lblTime);
		
		JLabel lblHourMins = new JLabel();
		b = new StringBuffer();
		b.append("<html>Hours:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "Minutes:</html>");
		lblHourMins.setText(b.toString());
		lblHourMins.setHorizontalAlignment(SwingConstants.LEFT);
		lblHourMins.setVerticalAlignment(SwingConstants.TOP);
		lblHourMins.setBounds(80, 135, 250, 40);
		panelGiveTime.add(lblHourMins);
		
		Integer[] comboBoxItemsMinutes = new Integer[60];
		for (int i = 0; i < 60; i++) {
			comboBoxItemsMinutes[i] = i;
		}
		Integer[] comboBoxItemsHours = new Integer[24];
		for (int i = 0; i < 24; i++) {
			comboBoxItemsHours[i] = i;
		}
		hoursComboBox = new JComboBox<>(comboBoxItemsHours);
		hoursComboBox.setBounds(125, 130, 70, 30);
		panelGiveTime.add(hoursComboBox);
		hoursComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float time = Float.parseFloat(minutesComboBox.getSelectedItem().toString()) / 60 + Float.parseFloat(hoursComboBox.getSelectedItem().toString());
				amountOfTime = BigDecimal.valueOf(time).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				setReportingDetails();
			}
		});
		minutesComboBox = new JComboBox<>(comboBoxItemsMinutes);
		minutesComboBox.setBounds(265, 130, 70, 30);
		panelGiveTime.add(minutesComboBox);
		minutesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float time = Float.parseFloat(minutesComboBox.getSelectedItem().toString()) / 60 + Float.parseFloat(hoursComboBox.getSelectedItem().toString());
				amountOfTime = BigDecimal.valueOf(time).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				setReportingDetails();
			}
		});
		
		JLabel lblDate = new JLabel("Date:                     "
				+ "-                      -");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setBounds(80, 170, 300, 30);
		panelGiveTime.add(lblDate);
		
		String[] comboBoxDates = new String[32];
		comboBoxDates[0] = "Day";
		for (int i = 1; i < 32; i++) {
			comboBoxDates[i] = "" + i;
		}
		dayComboBox = new JComboBox<>(comboBoxDates);
		dayComboBox.setBounds(125, 170, 75, 30);
		panelGiveTime.add(dayComboBox);
		
		String[] comboBoxMonths = new String[13];
		comboBoxMonths[0] = "Month";
		for (int i = 1; i < 13; i++) {
			comboBoxMonths[i] = "" + i;
		}
		monthComboBox = new JComboBox<>(comboBoxMonths);
		monthComboBox.setBounds(205, 170, 90, 30);
		panelGiveTime.add(monthComboBox);
		
		// TODO: use first and last year
		String[] comboBoxYears = new String[32];
		comboBoxYears[0] = "Year";
		for (int i = 1; i < 32; i++) {
			comboBoxYears[i] = i + 2000 + "";
		}
		yearComboBox = new JComboBox<>(comboBoxYears);
		yearComboBox.setBounds(300, 170, 85, 30);
		panelGiveTime.add(yearComboBox);
		
		lblReportingDetails = new JLabel();
		lblReportingDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblReportingDetails.setVerticalAlignment(SwingConstants.TOP);
		lblReportingDetails.setBounds(80, 215, 265, 90);
		panelGiveTime.add(lblReportingDetails);
		
		
		JButton btnTime = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Report Time</h2></html>");
		btnTime.setText(b.toString());
		btnTime.setBounds(210, 290, 157, 40);
		panelGiveTime.add(btnTime);
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dayComboBox.getSelectedIndex() == 0 ||
						monthComboBox.getSelectedIndex() == 0 ||
						yearComboBox.getSelectedIndex() == 0) {
					System.out.println("You need to select a date");
				} else {
					reportTime();
					setSuccessMessage();
					panelGiveTime.setVisible(false);
					panelSuccess.setVisible(true);
				}
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelGiveTime.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(false);
				panelSelectActivity.setVisible(true);
				panelGiveTime.setVisible(false);
				hoursComboBox.setSelectedItem(0);
				minutesComboBox.setSelectedItem(0);
				setReportingDetails();
				dayComboBox.setSelectedIndex(0);
				monthComboBox.setSelectedIndex(0);
				yearComboBox.setSelectedIndex(0);
			}
		});
		// ----------------------------------------
		// ------------- Success -------------------
		panelSuccess = new JPanel();
		panelSuccess.setBounds(0, 28, 380, 500);
		panelReportTime.add(panelSuccess);
		panelSuccess.setLayout(null);
		panelSuccess.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h1>&nbsp;Success</h1></html>");
		lblPhase.setText(b.toString());
		panelSuccess.add(lblPhase);
		
		lblSuccess = new JLabel();
		lblSuccess.setHorizontalAlignment(SwingConstants.LEFT);
		lblSuccess.setVerticalAlignment(SwingConstants.TOP);
		lblSuccess.setBounds(110, 80, 250, 100);
		panelSuccess.add(lblSuccess);
		
		JButton btnNewReport = new JButton();
		b = new StringBuffer(); b.append("<html><h2>New Time Report</h2></html>");
		btnNewReport.setText(b.toString());
		btnNewReport.setBounds(110, 300, 210, 40);
		panelSuccess.add(btnNewReport);
		btnNewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(true);
				panelSelectActivity.setVisible(false);
				panelGiveTime.setVisible(false);
				panelSuccess.setVisible(false);
				clear();
//				hoursComboBox.setSelectedItem(0);
//				minutesComboBox.setSelectedItem(0);
//				setReportingDetails();
//				dayComboBox.setSelectedIndex(0);
//				monthComboBox.setSelectedIndex(0);
//				yearComboBox.setSelectedIndex(0);
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Great!</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(160, 250, 100, 40);
		panelSuccess.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		// ----------------------------------------
		
		

	}
	
	private void setSuccessMessage() {
		StringBuffer b = new StringBuffer();
		b.append("<html>A time report of <b>" + amountOfTime + " hours</b> on<h2>\"");
		b.append(activity.getName() + "\"</h2><p> was created on ");
		b.append(dayComboBox.getSelectedItem().toString() + "/" + monthComboBox.getSelectedItem().toString() + "/" + yearComboBox.getSelectedItem().toString());
		b.append(" for<br>" + employee.getName() + "</p></html>");
		lblSuccess.setText(b.toString());
	}

	private void setReportingDetails() {
		StringBuffer b = new StringBuffer();
		b.append("<html>You are currently reporting <b>" + amountOfTime + "</b> hours on <b>\"");
		b.append(activity.getName());
		b.append("\"</b> for " + employee.getName() + "</html>");
		lblReportingDetails.setText(b.toString());
	}

	private void findRelevantActivities() {
		relevantActivities.clear();
		SimpleEntry<List<Activity>, List<Integer>> foundActivities = planningApp.getAllRelevantActivitiesForEmployee(employee);
		foundActivities.getKey().forEach((a) -> {relevantActivities.addElement(a);});
		relevantProjectNumbers = foundActivities.getValue();
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
		panelSuccess.setVisible(false);
		employeeReminderField.setText("");
		searchField.setText("");
		searchResults.clear();
		hoursComboBox.setSelectedItem(0);
		minutesComboBox.setSelectedItem(0);
		dayComboBox.setSelectedIndex(0);
		monthComboBox.setSelectedIndex(0);
		yearComboBox.setSelectedIndex(0);
	}

	public void setVisible(boolean aFlag) {
		panelReportTime.setVisible(aFlag);
		
	}
	
	private void reportTime() {
		GregorianCalendar date = new GregorianCalendar(Integer.parseInt(yearComboBox.getSelectedItem().toString()), Integer.parseInt(monthComboBox.getSelectedItem().toString()) - 1, Integer.parseInt(dayComboBox.getSelectedItem().toString()));
		TimeRegistration timeRegistration = null;
		try {
			timeRegistration = new TimeRegistration(employee, date, amountOfTime);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		try {
			planningApp.registerTime(projectNumber, activity.getName(), timeRegistration);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		} catch (ActivityNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
