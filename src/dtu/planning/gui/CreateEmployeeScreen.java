package dtu.planning.gui;

import dtu.planning.app.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEmployeeScreen {
	
	private MainScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelCreateEmployee;
	private JPanel panelEmployee;
	private JPanel panelSuccessMessage;
	private JLabel lblSuccessMessage;
	private JTextField employeeNameField;
	private JTextField employeeInitialsField;
	private Employee employee;
	private JButton btnBack;
	
	public CreateEmployeeScreen(PlanningApp planningApp, MainScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelCreateEmployee = new JPanel();
		parentWindow.addPanel(panelCreateEmployee);
		panelCreateEmployee.setLayout(null);
		panelCreateEmployee.setBorder(BorderFactory.createTitledBorder(
                "Add Employee"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateEmployee.add(btnBack);
		
		// --------- Create Employee ---------
		panelEmployee = new JPanel();
		panelCreateEmployee.add(panelEmployee);
        panelEmployee.setLayout(null);
        panelEmployee.setBounds(60, 60, 300, 390);
        panelEmployee.setVisible(true);
        JLabel lblName = new JLabel("Name:");
        panelEmployee.add(lblName);
        lblName.setBounds(0, 20, 60, 30);
        JLabel lblInitials = new JLabel("Initials:");
        panelEmployee.add(lblInitials);
        lblInitials.setBounds(0, 60, 100, 30);
        
        employeeNameField = new JTextField();
        panelEmployee.add(employeeNameField);
        employeeNameField.setBounds(90, 20, 170, 30);
        
        employeeInitialsField = new JTextField();
        panelEmployee.add(employeeInitialsField);
        employeeInitialsField.setBounds(90, 60, 170, 30);
        
        JButton btnAdd = new JButton("Add Employee");
        panelEmployee.add(btnAdd);
        btnAdd.setBounds(90, 180, 110, 40);
        btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean employeeWasAdded = false;
				if (employeeNameField.getText().equals("")) {
					setConsoleMessage("You need to specify the employee's name");
				} else if (employeeInitialsField.getText().equals("")) {
					setConsoleMessage("You need to specify the employee's initials");
				} else {
					employeeWasAdded = addEmployee();
				}
				
				if (employeeWasAdded) {
					panelEmployee.setVisible(false);
					setSuccessMessage();
					panelSuccessMessage.setVisible(true);
				}
			}
		});
		// -----------------------------------
		
		// ---------- Success Message --------
		panelSuccessMessage = new JPanel();
		panelCreateEmployee.add(panelSuccessMessage);
        panelSuccessMessage.setLayout(null);
        panelSuccessMessage.setBounds(10, 60, 384, 390);
        panelSuccessMessage.setVisible(false);
        
        lblSuccessMessage = new JLabel();
        panelSuccessMessage.add(lblSuccessMessage);
        lblSuccessMessage.setVerticalAlignment(SwingConstants.TOP);
        lblSuccessMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccessMessage.setBounds(0, 0, 384, 300);
        
        JButton btnAddAnother = new JButton("Add Another Employee");
        panelSuccessMessage.add(btnAddAnother);
        btnAddAnother.setBounds(100, 180, 200, 40);
        btnAddAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSuccessMessage.setVisible(false);
				clear();
				panelEmployee.setVisible(true);
				
			}
		});
		// -----------------------------------
	}
	
	private boolean addEmployee() {
		Employee employee = new Employee(employeeNameField.getText(), employeeInitialsField.getText());
		try {
			planningApp.addEmployee(employee);
			this.employee = employee;
			return true;
		} catch (OperationNotAllowedException e) {
			setConsoleMessage(e.getMessage());
			return false;
		}
	}
	
	private void setSuccessMessage() {
		StringBuffer b = new StringBuffer();
		b.append("<html><h2>The employee called</h2> <h1>" + employee + "</h1> <h2>was added to the system</h2></html>");
		lblSuccessMessage.setText(b.toString());
	}

	public void setVisible(boolean aFlag) {
		panelCreateEmployee.setVisible(aFlag);
	}
	
	public void clear() {
		employeeNameField.setText("");
		employeeInitialsField.setText("");
		panelEmployee.setVisible(true);
		panelSuccessMessage.setVisible(false);
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}

}
