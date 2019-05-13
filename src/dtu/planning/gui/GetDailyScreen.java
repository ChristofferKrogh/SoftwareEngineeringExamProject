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

public class GetDailyScreen {
	private MainScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelGetDaily;
	private JPanel panelSelectEmployee;
	private JPanel panelEditTime;
	private JTextField searchField;
	private JLabel lblPhase;
	private JList<Employee> listSearchResult;
	private JList<TimeRegistration> listTimeRegistrations;
	private JScrollPane listScrollPaneEmployees;
	private DefaultListModel<Employee> searchResults;
	private DefaultListModel<TimeRegistration> timeRegistrations;
	private Employee employee;
	private GregorianCalendar date;
	private JButton btnBack;
	private JButton btnEdit;
	private JButton btnPrevious;
	private JComboBox<Integer> hoursComboBox;
	private JComboBox<Integer> minutesComboBox;
	private JLabel lblReportingDetails;
	private float amountOfTime;
	private TimeRegistration timeRegistration;
	private JScrollPane listScrollPaneTimeReg;
	private JLabel lblTimeReg;
	
	public GetDailyScreen(PlanningApp planningApp, MainScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	private void initialize() {
		panelGetDaily = new JPanel();
		panelGetDaily.setVisible(false);
		parentWindow.addPanel(panelGetDaily);
		panelGetDaily.setLayout(null);
		panelGetDaily.setBorder(BorderFactory.createTitledBorder(
                "Get Daily Reported Time"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelGetDaily.add(btnBack);
		
		// ––––––––––– Step 1: Select Employee ––––––––––-
		date = new GregorianCalendar(); // Initialize to today's date
		panelSelectEmployee = new JPanel();
		panelSelectEmployee.setBounds(0, 28, 350, 500);
		panelGetDaily.add(panelSelectEmployee);
		panelSelectEmployee.setLayout(null);
		panelSelectEmployee.setVisible(true);
		
		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(80, 10, 150, 30);
		panelSelectEmployee.add(lblEmployee);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
				lblTimeReg.setVisible(false);
				listScrollPaneTimeReg.setVisible(false);
				btnEdit.setVisible(false);
			}
		});
		searchField.setBounds(170, 10, 160, 30);
		panelSelectEmployee.add(searchField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
				lblTimeReg.setVisible(false);
				listScrollPaneTimeReg.setVisible(false);
				btnEdit.setVisible(false);
			}
		});
		btnSearch.setBounds(160, 45, 100, 30);
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
					employee = listSearchResult.getSelectedValue();
					searchTimeRegistrations();
					listScrollPaneTimeReg.setVisible(true);
					lblTimeReg.setVisible(true);
					btnEdit.setVisible(false);
				}
				
			}
		});
		listSearchResult.setVisibleRowCount(5);
        listScrollPaneEmployees = new JScrollPane(listSearchResult);

        listScrollPaneEmployees.setBounds(80, 80, 250, 100);
		panelSelectEmployee.add(listScrollPaneEmployees);
		
		lblTimeReg = new JLabel("All time registrations for the employee:");
		lblTimeReg.setBounds(80, 200, 250, 25);
		lblTimeReg.setVisible(false);
		panelSelectEmployee.add(lblTimeReg);
		timeRegistrations = new DefaultListModel<>();
		listTimeRegistrations = new JList<TimeRegistration>(timeRegistrations);
		listTimeRegistrations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTimeRegistrations.setSelectedIndex(0);
		listTimeRegistrations.setVisibleRowCount(5);
		listTimeRegistrations.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listTimeRegistrations.getSelectedIndex() == -1) {
					
				} else {
					timeRegistration = listTimeRegistrations.getSelectedValue();
					btnEdit.setVisible(true);
				}
				
			}
		});
        listScrollPaneTimeReg = new JScrollPane(listTimeRegistrations);
        listScrollPaneTimeReg.setVisible(false);
        listScrollPaneTimeReg.setBounds(80, 235, 250, 100);
		panelSelectEmployee.add(listScrollPaneTimeReg);
		
		btnEdit = new JButton();
		StringBuffer b = new StringBuffer(); b.append("<html><h2>Edit</h2></html>");
		btnEdit.setText(b.toString());
		btnEdit.setBounds(245, 340, 90, 40);
		btnEdit.setVisible(false);
		panelSelectEmployee.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(false);
				panelEditTime.setVisible(true);
				setAmountOfTimeComboBoxes();
			}
		});
		// –––––––––––––––––––––––––––––––––––––––––––––––
		
		// –––––––– Step 3: Edit  TimeRegistration ––––––-
		panelEditTime = new JPanel();
		panelEditTime.setBounds(0, 28, 370, 500);
		panelGetDaily.add(panelEditTime);
		panelEditTime.setLayout(null);
		panelEditTime.setVisible(false);
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(80, 20, 250, 90);
		b = new StringBuffer();
		b.append("<html><h2>Submit Amount of Time</h2></html>");
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
				clear();
			}
		});
		
		btnPrevious = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
		btnPrevious.setText(b.toString());
		btnPrevious.setBounds(80, 290, 120, 40);
		panelEditTime.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectEmployee.setVisible(true);
				panelEditTime.setVisible(false);
			}
		});
		// ––––––––––––––––––––––––––––––––––––––––––––––––
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
	
	private void searchTimeRegistrations() {
		timeRegistrations.clear();
		planningApp.getAllTimeRegistrationsForEmployeeOnDate(employee, date)
		.forEach(t -> {timeRegistrations.addElement(t);});
	}
	
	private void setSelectedTimeLbl() {
		StringBuffer b = new StringBuffer();
		b.append("<html>You are currently reporting <b>" + amountOfTime + " hour(s)</b></html>");
		lblReportingDetails.setText(b.toString());
	}
	
	private void correctTime() {
		timeRegistration.correctTime(amountOfTime);
	}
	
	private void setAmountOfTimeComboBoxes() {
		hoursComboBox.setSelectedItem((int) timeRegistration.getAmountOfTime());
		int minutes = (int) Math.round(timeRegistration.getAmountOfTime() * 100 % 100 * 0.6);
		minutesComboBox.setSelectedItem(minutes);
	}
	
	public void setVisible(boolean aFlag) {
		panelGetDaily.setVisible(aFlag);
	}
	
	public void clear() {
		panelSelectEmployee.setVisible(true);
		panelEditTime.setVisible(false);
		lblTimeReg.setVisible(false);
		listScrollPaneTimeReg.setVisible(false);
		btnEdit.setVisible(false);
		searchField.setText("");
		searchResults.clear();
		timeRegistrations.clear();
		hoursComboBox.setSelectedItem(0);
		minutesComboBox.setSelectedItem(0);
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}

}
