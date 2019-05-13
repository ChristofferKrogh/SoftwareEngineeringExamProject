package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

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
import dtu.planning.app.Employee;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.OperationNotAllowedException;

public class CorrectReportedTimeScreen {
	private MainScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelCorrectTime;
	private JPanel panelSelectEmployee;
	private JPanel panelSelectDate;
	private JPanel panelSelectTimeReg;
	private JPanel panelEditTime;
	private JTextField searchField;
	private JLabel lblPhase;
	private JList<Employee> listSearchResult;
	private JList<TimeRegistration> listTimeRegistrations;
	private JScrollPane listScrollPane;
	private DefaultListModel<Employee> searchResults;
	private DefaultListModel<TimeRegistration> timeRegistrations;
	private Employee employee;
	private GregorianCalendar date;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnPrevious;
	private JComboBox<Integer> hoursComboBox;
	private JComboBox<Integer> minutesComboBox;
	private JLabel lblReportingDetails;
	private JComboBox<String> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<String> yearComboBox;
	private float amountOfTime;
	protected TimeRegistration timeRegistration;
  	
	public CorrectReportedTimeScreen(PlanningApp planningApp, MainScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}

	private void initialize() {
		panelCorrectTime = new JPanel();
		panelCorrectTime.setVisible(false);
		parentWindow.addPanel(panelCorrectTime);
		panelCorrectTime.setLayout(null);
		panelCorrectTime.setBorder(BorderFactory.createTitledBorder(
                "Correct Reported Time"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCorrectTime.add(btnBack);
		
		// ––––––––––– Step 1: Select Employee ––––––––––-
		panelSelectEmployee = new JPanel();
		panelSelectEmployee.setBounds(0, 28, 350, 500);
		panelCorrectTime.add(panelSelectEmployee);
		panelSelectEmployee.setLayout(null);
		panelSelectEmployee.setVisible(true);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		StringBuffer b = new StringBuffer();
		b.append("<html><h2>&nbsp;Step 1</h2>Select Employee</html>");
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
					setConsoleMessage("You need to select an employee");
				} else {
					employee = listSearchResult.getSelectedValue();
					panelSelectEmployee.setVisible(false);
					panelSelectDate.setVisible(true);
				}
			}
		});
		// –––––––––––––––––––––––––––––––––––––––––––––––
		
		// ––––––––––– Step 2: Select Date ––––––––––––––-
		panelSelectDate = new JPanel();
		panelSelectDate.setBounds(0, 28, 350, 500);
		panelCorrectTime.add(panelSelectDate);
		panelSelectDate.setLayout(null);
		panelSelectDate.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h2>&nbsp;Step 2</h2>Select Date</html>");
		lblPhase.setText(b.toString());
		panelSelectDate.add(lblPhase);
		
		String[] comboBoxDates = new String[32];
		comboBoxDates[0] = "Day";
		for (int i = 1; i < 32; i++) {
			comboBoxDates[i] = "" + i;
		}
		dayComboBox = new JComboBox<>(comboBoxDates);
		dayComboBox.setBounds(80, 170, 75, 30);
		panelSelectDate.add(dayComboBox);
		
		String[] comboBoxMonths = new String[13];
		comboBoxMonths[0] = "Month";
		for (int i = 1; i < 13; i++) {
			comboBoxMonths[i] = "" + i;
		}
		monthComboBox = new JComboBox<>(comboBoxMonths);
		monthComboBox.setBounds(155, 170, 90, 30);
		panelSelectDate.add(monthComboBox);
		
		String[] comboBoxYears = new String[32];
		comboBoxYears[0] = "Year";
		for (int i = 1; i < 32; i++) {
			comboBoxYears[i] = i + 2000 + "";
		}
		yearComboBox = new JComboBox<>(comboBoxYears);
		yearComboBox.setBounds(245, 170, 85, 30);
		panelSelectDate.add(yearComboBox);
		
		btnNext = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Next</h2></html>");
		btnNext.setText(b.toString());
		btnNext.setBounds(245, 290, 90, 40);
		panelSelectDate.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dayComboBox.getSelectedIndex() == 0 ||
						monthComboBox.getSelectedIndex() == 0 ||
						yearComboBox.getSelectedIndex() == 0) {
					setConsoleMessage("You need to select a date");
				} else {
					date = new GregorianCalendar(Integer.parseInt(yearComboBox.getSelectedItem().toString()), Integer.parseInt(monthComboBox.getSelectedItem().toString()) - 1, Integer.parseInt(dayComboBox.getSelectedItem().toString()));
					searchTimeRegistrations();
					panelSelectEmployee.setVisible(false);
					panelSelectDate.setVisible(false);
					panelSelectTimeReg.setVisible(true);
				}
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelSelectDate.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(true);
				panelSelectDate.setVisible(false);
				panelSelectTimeReg.setVisible(false);
			}
		});
		// –––––––––––––––––––––––––––––––––––––––––––––––
		
		// –––––––– Step 3: Select TimeRegistration –––––-
		panelSelectTimeReg = new JPanel();
		panelSelectTimeReg.setBounds(0, 28, 350, 500);
		panelCorrectTime.add(panelSelectTimeReg);
		panelSelectTimeReg.setLayout(null);
		panelSelectTimeReg.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h2>&nbsp;Step 3</h2>Select Time Registration</html>");
		lblPhase.setText(b.toString());
		panelSelectTimeReg.add(lblPhase);
		
		timeRegistrations = new DefaultListModel<>();
		listTimeRegistrations = new JList<TimeRegistration>(timeRegistrations);
		listTimeRegistrations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTimeRegistrations.setSelectedIndex(0);
		listTimeRegistrations.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(listTimeRegistrations);

        listScrollPane.setBounds(80, 120, 250, 150);
		panelSelectTimeReg.add(listScrollPane);
		
		JButton btnEdit = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Edit</h2></html>");
		btnEdit.setText(b.toString());
		btnEdit.setBounds(245, 290, 90, 40);
		panelSelectTimeReg.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listTimeRegistrations.getSelectedIndex() == -1) {
					setConsoleMessage("You need to select a time registration");
				} else {
					timeRegistration = listTimeRegistrations.getSelectedValue();
					setAmountOfTimeComboBoxes();
					panelSelectTimeReg.setVisible(false);
					panelEditTime.setVisible(true);
				}
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelSelectTimeReg.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(false);
				panelSelectDate.setVisible(true);
				panelSelectTimeReg.setVisible(false);
			}
		});
		// –––––––––––––––––––––––––––––––––––––––––––––––
		
		// –––––––– Step 4: Edit  TimeRegistration ––––––-
		panelEditTime = new JPanel();
		panelEditTime.setBounds(0, 28, 370, 500);
		panelCorrectTime.add(panelEditTime);
		panelEditTime.setLayout(null);
		panelEditTime.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(140, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 4</h2>Submit Amount of Time</html>");
		lblPhase.setText(b.toString());
		panelEditTime.add(lblPhase);
		
		JLabel timeDetails = new JLabel("hours:                    minutes:");
		timeDetails.setBounds(80, 140, 200, 30);
		panelEditTime.add(timeDetails);
		
		Integer[] comboBoxHours = new Integer[24];
		for (int i = 0; i < 24; i++) {
			comboBoxHours[i] = i;
		}
		hoursComboBox = new JComboBox<>(comboBoxHours);
		hoursComboBox.setBounds(120, 140, 70, 30);
		panelEditTime.add(hoursComboBox);
		hoursComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float time = Float.parseFloat(minutesComboBox.getSelectedItem().toString()) / 60 + Float.parseFloat(hoursComboBox.getSelectedItem().toString());
				amountOfTime = BigDecimal.valueOf(time).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				setSelectedTimeLbl();
			}
		});
		
		Integer[] comboBoxMinutes = new Integer[60];
		for (int i = 0; i < 60; i++) {
			comboBoxMinutes[i] = i;
		}
		minutesComboBox = new JComboBox<>(comboBoxMinutes);
		minutesComboBox.setBounds(255, 140, 70, 30);
		panelEditTime.add(minutesComboBox);
		minutesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float time = Float.parseFloat(minutesComboBox.getSelectedItem().toString()) / 60 + Float.parseFloat(hoursComboBox.getSelectedItem().toString());
				amountOfTime = BigDecimal.valueOf(time).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				setSelectedTimeLbl();
			}
		});
		
		lblReportingDetails = new JLabel();
		lblReportingDetails.setBounds(80, 210, 300, 30);
		panelEditTime.add(lblReportingDetails);
		
		JButton btnSave = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Save Change</h2></html>");
		btnSave.setText(b.toString());
		btnSave.setBounds(205, 290, 165, 40);
		panelEditTime.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				correctTime();
				searchTimeRegistrations();
				panelSelectEmployee.setVisible(false);
				panelSelectDate.setVisible(false);
				panelSelectTimeReg.setVisible(true);
				panelEditTime.setVisible(false);
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelEditTime.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(false);
				panelSelectDate.setVisible(false);
				panelSelectTimeReg.setVisible(true);
				panelEditTime.setVisible(false);
			}
		});
		// ––––––––––––––––––––––––––––––––––––––––––––––––
	}
	
	private void correctTime() {
		timeRegistration.correctTime(amountOfTime);
	}

	private void setSelectedTimeLbl() {
		StringBuffer b = new StringBuffer();
		b.append("<html>You are currently reporting <b>" + amountOfTime + " hour(s)</b></html>");
		lblReportingDetails.setText(b.toString());
	}

	private void setAmountOfTimeComboBoxes() {
		hoursComboBox.setSelectedItem((int) timeRegistration.getAmountOfTime());
		int minutes = (int) Math.round(timeRegistration.getAmountOfTime() * 100 % 100 * 0.6);
		minutesComboBox.setSelectedItem(minutes);
	}

	public void setVisible(boolean aFlag) {
		panelCorrectTime.setVisible(aFlag);
	}
	
	public void clear() {
		panelSelectEmployee.setVisible(true);
		panelSelectDate.setVisible(false);
		panelSelectTimeReg.setVisible(false);
		panelEditTime.setVisible(false);
		searchField.setText("");
		searchResults.clear();
		hoursComboBox.setSelectedItem(0);
		minutesComboBox.setSelectedItem(0);
		dayComboBox.setSelectedIndex(0);
		monthComboBox.setSelectedIndex(0);
		yearComboBox.setSelectedIndex(0);
	}
	
	private void searchTimeRegistrations() {
		timeRegistrations.clear();
		planningApp.getAllTimeRegistrationsForEmployeeOnDate(employee, date)
		.forEach(t -> {timeRegistrations.addElement(t);});
	}
	
	private void searchEmployees() {
		searchResults.clear();
		try {
			planningApp.searchForEmployeesByName(searchField.getText())
			.forEach((m) -> {searchResults.addElement(m);});
		} catch (OperationNotAllowedException e) {
			setConsoleMessage(e.getMessage());
		}		
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}
}
