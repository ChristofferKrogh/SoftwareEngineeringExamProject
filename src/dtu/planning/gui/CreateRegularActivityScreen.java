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
import dtu.planning.app.Activity;
import dtu.planning.app.OperationNotAllowedException;

public class CreateRegularActivityScreen {
	
	private RegularActivitiesScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelCreateRegActivity;
	private JPanel panelCreateRegActivitySuccess;
	private JTextField searchField;
	private JTextField regActivityNameField;
	private JLabel employeeReminderField;
	private JLabel lblSuccessMessage;
	private JTextField startWeekField;
	private JTextField endWeekField;
	private JComboBox<Integer> startYearComboBox;
	private JComboBox<Integer> endYearComboBox;
	private JList<Employee> listSearchResult;
	private DefaultListModel<Employee> searchResults;
	private JButton btnBack;
	private Activity regActivity;
	private int firstYear;
	private int lastYear;
	
	public CreateRegularActivityScreen(PlanningApp planningApp, RegularActivitiesScreen parentWindow, int firstYear, int lastYear) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		this.firstYear = firstYear;
		this.lastYear = lastYear;
		initialize();
	}

	private void initialize() {
		panelCreateRegActivity = new JPanel();
		parentWindow.addPanel(panelCreateRegActivity);
		panelCreateRegActivity.setLayout(null);
		panelCreateRegActivity.setBorder(BorderFactory.createTitledBorder(
                "Create Regular Activity"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelCreateRegActivitySuccess.setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateRegActivity.add(btnBack);
		
		JButton btnCreateRegActivity = new JButton("Create Regular Activity");
		btnCreateRegActivity.setBounds(225, 385, 160, 50);
		panelCreateRegActivity.add(btnCreateRegActivity);
		btnCreateRegActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createRegActivity();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				if (regActivity != null) {
					setVisible(false);
					setSuccessMessage();
					panelCreateRegActivitySuccess.setVisible(true);
				}
			}
		});
		
		JLabel lblProjectName = new JLabel("Regular Activity Name: ");
		lblProjectName.setBounds(90, 48, 150, 30);
		panelCreateRegActivity.add(lblProjectName);
		
		regActivityNameField = new JTextField();
		regActivityNameField.setBounds(242, 48, 145, 30);
		panelCreateRegActivity.add(regActivityNameField);
		
		// ------------ Start and end dates ---------------
		startWeekField = new JTextField();
		startWeekField.setBounds(242, 108, 35, 30);
		panelCreateRegActivity.add(startWeekField);
		
		endWeekField = new JTextField();
		endWeekField.setBounds(242, 140, 35, 30);
		panelCreateRegActivity.add(endWeekField);
		
		Integer[] comboBoxItems = new Integer[lastYear-firstYear];
		for (int i = 0; i < lastYear - firstYear; i++) {
			comboBoxItems[i] = firstYear + i;
		}
		startYearComboBox = new JComboBox<>(comboBoxItems);
		startYearComboBox.setBounds(302, 108, 85, 30);
		startYearComboBox.setSelectedItem(2019);
		panelCreateRegActivity.add(startYearComboBox);
		endYearComboBox = new JComboBox<>(comboBoxItems);
		endYearComboBox.setBounds(302, 140, 85, 30);
		endYearComboBox.setSelectedItem(2019);
		panelCreateRegActivity.add(endYearComboBox);
		
		JLabel lblEditDates = new JLabel();
		lblEditDates.setVerticalAlignment(SwingConstants.TOP);
		lblEditDates.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditDates.setBounds(90, 115, 318, 75);
		StringBuffer editDatesString = new StringBuffer();
		editDatesString.append("<html><b>Expected Start:</b>&nbsp;&nbsp;&nbsp; week &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of <br><br>"); //&nbsp; is a space
		editDatesString.append("<b>Expected End:</b>&nbsp;&nbsp;&nbsp;&nbsp; week &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of </html>");
		lblEditDates.setText(editDatesString.toString());
		panelCreateRegActivity.add(lblEditDates);
		// -------------------------------------------------
		
		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(100, 200, 150, 30);
		panelCreateRegActivity.add(lblEmployee);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		searchField.setBounds(180, 200, 160, 30);
		panelCreateRegActivity.add(searchField);
		
		employeeReminderField = new JLabel();
		employeeReminderField.setBounds(5, 270, 87, 45);
		panelCreateRegActivity.add(employeeReminderField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(165, 235, 100, 30);
		panelCreateRegActivity.add(btnSearch);
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
        listScrollPane.setBounds(100, 270, 250, 100);
		panelCreateRegActivity.add(listScrollPane);
		
		// ------------ Success Message --------------------
		panelCreateRegActivitySuccess = new JPanel();
		panelCreateRegActivitySuccess.setVisible(false);
		parentWindow.addPanel(panelCreateRegActivitySuccess);
		panelCreateRegActivitySuccess.setLayout(null);
		panelCreateRegActivitySuccess.setBorder(BorderFactory.createTitledBorder(
                "Success Message"));
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelCreateRegActivitySuccess.setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateRegActivitySuccess.add(btnBack);
		
		lblSuccessMessage = new JLabel();
		lblSuccessMessage.setVerticalAlignment(SwingConstants.TOP);
		lblSuccessMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblSuccessMessage.setBounds(80, 80, 300, 200);
		panelCreateRegActivitySuccess.add(lblSuccessMessage);
		
		JButton btnOK = new JButton("OK"); // Same as btnBack
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelCreateRegActivitySuccess.setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnOK.setBounds(160, 260, 100, 40);
		panelCreateRegActivitySuccess.add(btnOK);
		// -------------------------------------------------
	}
	
	protected void searchEmployees() {
		// Show a reminder to select the employee
		StringBuffer b = new StringBuffer();
		b.append("<html><b>Reminder:</b> Select the<br>project leader</html>");
		employeeReminderField.setText(b.toString());
				
		searchResults.clear();
		planningApp.searchForEmployeesByName(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});		
	}

	protected void setSuccessMessage() {
		StringBuffer b = new StringBuffer();
		b.append("<html><h2>The regular activity called</h2><h1>\"");
		b.append(regActivity.getName());
		b.append("\"</h1><h2>was created!</h1></html>");	
		lblSuccessMessage.setText(b.toString());
	}

	protected void createRegActivity() throws Exception {
		String name = regActivityNameField.getText();
		if (name.equals("")) {
			throw new OperationNotAllowedException("The regular activity needs a name");
		}
		GregorianCalendar start = new GregorianCalendar();
		int startWeek;
		try {
			startWeek = Integer.parseInt(startWeekField.getText());
		} catch (Exception e) {
			throw new OperationNotAllowedException("The start week must be an integer");
		}
		int startYear = Integer.parseInt(startYearComboBox.getSelectedItem().toString());
		start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
		GregorianCalendar end = new GregorianCalendar();
		int endWeek;
		try {
			endWeek = Integer.parseInt(endWeekField.getText());
		} catch (Exception e) {
			throw new OperationNotAllowedException("The end week must be an integer");
		}
		int endYear = Integer.parseInt(endYearComboBox.getSelectedItem().toString());
		end.setWeekDate(endYear, endWeek, GregorianCalendar.SATURDAY);
		if (end.before(start)) {
			throw new OperationNotAllowedException("The expected end must be after the expected start");
		} else if (end.after(new GregorianCalendar(lastYear - 1, 11, 31))) {
			throw new OperationNotAllowedException("The activity must end before " + lastYear);
		} else if (listSearchResult.getSelectedIndex() == -1) {
			throw new OperationNotAllowedException("You need to select an employee");
		} else {
			regActivity = planningApp.addRegularActivity(new Activity(regActivityNameField.getText(), start, end), listSearchResult.getSelectedValue().getInitials());
		}
	}

	protected void setVisible(boolean aFlag) {
		panelCreateRegActivity.setVisible(aFlag);
	}

	private void clear() {
		searchField.setText("");
		searchResults.clear();
		regActivityNameField.setText("");
		startWeekField.setText("");
		endWeekField.setText("");
		startYearComboBox.setSelectedItem(2019);
		endYearComboBox.setSelectedItem(2019);
		employeeReminderField.setText("");
	}
}
