package dtu.planning.gui;

import dtu.planning.app.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class CreateActivitiesScreen {

    private ProjectsScreen parentWindow;
    private PlanningApp planningApp;
    private JPanel panelCreateActivity;
    private JPanel panelCreateActivitySuccess;
    private JPanel panelCheckLeader;
    private JPanel panelActivityDetails;
    private JTextField activityNameField;
    private JTextField employeeNameField;
    private JTextField amountOfHours;
    private JButton btnBack;
    private JButton btnYes;
    private JButton btnNo;
    private JButton btnOkay;
    private JLabel lblSuccessMessage;
    private JLabel lblProjectName;
    private JLabel lblLeader;
    private JLabel lblCheckLeader;
    private JTextField startDayField;
    private JTextField startMonthField;
    private JTextField startYearField;
    private JTextField endDayField;
    private JTextField endMonthField;
    private JTextField endYearField;
    private Activity activity;
    private Project project;
	private JTextField startWeekField;
	private JTextField endWeekField;
	private int firstYear = 2000;
	private int lastYear = 2040;
	private JComboBox<Integer> startYearComboBox;
	private JComboBox<Integer> endYearComboBox;

    public CreateActivitiesScreen(PlanningApp planningApp, ProjectsScreen parentWindow) {
        this.planningApp = planningApp;
        this.parentWindow = parentWindow;
        initialize();
    }

    private void initialize() {
        panelCreateActivity = new JPanel();
        parentWindow.addPanel(panelCreateActivity);
        panelCreateActivity.setLayout(null);
        panelCreateActivity.setBorder(BorderFactory.createTitledBorder(
                "Create Activity"));
        
        // Back button
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clear();
                parentWindow.setVisible(true);
            }
        });
        btnBack.setBounds(21, 28, 59, 29);
        panelCreateActivity.add(btnBack);
        
        
        // –––––––––––––––––– Check Leader ––––––––––––––––––––––––––––– 
        panelCheckLeader = new JPanel();
        panelCreateActivity.add(panelCheckLeader);
        panelCheckLeader.setLayout(null);
        panelCheckLeader.setBounds(60, 60, 300, 390);
        panelCheckLeader.setVisible(true);
        
        lblCheckLeader = new JLabel();
        lblCheckLeader.setBounds(0, 0, 300, 300);
        lblCheckLeader.setVerticalAlignment(SwingConstants.TOP);
        lblCheckLeader.setHorizontalAlignment(SwingConstants.CENTER);
        panelCheckLeader.add(lblCheckLeader);
        
        btnYes = new JButton("Yes");
        btnYes.setBounds(160, 150, 90, 40);
        btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckLeader.setVisible(false);
				panelActivityDetails.setVisible(true);
			}
		});
        panelCheckLeader.add(btnYes);
        
        btnNo = new JButton("No");
        btnNo.setBounds(40, 150, 90, 40);
        btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                clear();
                parentWindow.setVisible(true);
			}
		});
        panelCheckLeader.add(btnNo);
        
        btnOkay = new JButton("Okay");
        btnOkay.setBounds(90, 150, 90, 40);
        btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                clear();
                parentWindow.setVisible(true);
			}
		});
        panelCheckLeader.add(btnOkay);
        btnOkay.setVisible(false);
        // ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––

        // ––––––––––––––––––– Activity Details –––––––––––––––––––––––––
        panelActivityDetails = new JPanel();
        panelCreateActivity.add(panelActivityDetails);
        panelActivityDetails.setLayout(null);
        panelActivityDetails.setBounds(60, 60, 330, 390);
        panelActivityDetails.setVisible(false);
        
        // Project name field
        lblProjectName = new JLabel("Project Name: ");
        lblProjectName.setBounds(40, 0, 260, 30);
        panelActivityDetails.add(lblProjectName);

        // Project leader for the project <<<<< TODO
        lblLeader = new JLabel("Project Leader:");
        lblLeader.setBounds(40, 35, 260, 30);
        panelActivityDetails.add(lblLeader);

        // Activity name field
        JLabel lblActivityName = new JLabel("Activity Name:");
        lblActivityName.setBounds(40, 80, 100, 30);
        panelActivityDetails.add(lblActivityName);

        activityNameField = new JTextField();
        activityNameField.setBounds(150, 80, 140, 30);
        panelActivityDetails.add(activityNameField);



        // Set start week and end week
        startWeekField = new JTextField();
		startWeekField.setBounds(150, 125, 35, 30);
		panelActivityDetails.add(startWeekField);
		
		endWeekField = new JTextField();
		endWeekField.setBounds(150, 155, 35, 30);
		panelActivityDetails.add(endWeekField);
		
		Integer[] comboBoxItems = new Integer[lastYear-firstYear];
		for (int i = 0; i < lastYear - firstYear; i++) {
			comboBoxItems[i] = firstYear + i;
		}
		startYearComboBox = new JComboBox<>(comboBoxItems);
		startYearComboBox.setBounds(205, 125, 85, 30);
		startYearComboBox.setSelectedItem(2019);
		panelActivityDetails.add(startYearComboBox);
		endYearComboBox = new JComboBox<>(comboBoxItems);
		endYearComboBox.setBounds(205, 155, 85, 30);
		endYearComboBox.setSelectedItem(2019);
		panelActivityDetails.add(endYearComboBox);
		
		JLabel lblDates = new JLabel();
		lblDates.setVerticalAlignment(SwingConstants.TOP);
		lblDates.setHorizontalAlignment(SwingConstants.LEFT);
		lblDates.setBounds(40, 130, 260, 75);
		StringBuffer b = new StringBuffer();
		b.append("<html><b>Start:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;week&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of<br><br>"
					 + "<b>End:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;week&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of</html>");
		lblDates.setText(b.toString());
		panelActivityDetails.add(lblDates);

        // Amount of hours
        JLabel lblAmountOfHours = new JLabel("Expected hours:");
        lblAmountOfHours.setBounds(40, 200, 105, 30);
        panelActivityDetails.add(lblAmountOfHours);

        amountOfHours = new JTextField();
        amountOfHours.setBounds(150, 200, 140, 30);
        panelActivityDetails.add(amountOfHours);

        // Add employee button
        JLabel lblEmployee = new JLabel("Employee:");
        lblEmployee.setBounds(40, 240, 150, 30);
        panelActivityDetails.add(lblEmployee);

        employeeNameField = new JTextField();
        employeeNameField.setBounds(150, 240, 140, 30);
        panelActivityDetails.add(employeeNameField);

        //  Add activity button
        JButton btnCreateActivity = new JButton("Add Activity");
        btnCreateActivity.setBounds(170, 340, 145, 50);
        panelActivityDetails.add(btnCreateActivity);
        btnCreateActivity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createActivity();
                if (activity != null) {
                    panelActivityDetails.setVisible(false);
                    setSuccessMessage();
                    panelCreateActivitySuccess.setVisible(true);
                }
            }
        });
        // ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
        
        // ––––––––––––––––– Success Message ––––––––––––––––––––––––––––
        panelCreateActivitySuccess = new JPanel();
        panelCreateActivity.add(panelCreateActivitySuccess);
        panelCreateActivitySuccess.setLayout(null);
        panelCreateActivitySuccess.setBounds(60, 60, 300, 500);
        panelCreateActivitySuccess.setVisible(false);
        
        lblSuccessMessage = new JLabel("");
        lblSuccessMessage.setVerticalAlignment(SwingConstants.TOP);
        lblSuccessMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccessMessage.setBounds(0, 0, 300, 310);
        panelCreateActivitySuccess.add(lblSuccessMessage);
        
        JButton btnCreateNewActivity = new JButton("Add Another Activity");
        btnCreateNewActivity.setBounds(60, 250, 190, 50);
        panelCreateActivitySuccess.add(btnCreateNewActivity);
        btnCreateNewActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				setProject(project);
				panelCreateActivitySuccess.setVisible(false);
				panelCheckLeader.setVisible(false);
				panelActivityDetails.setVisible(true);
			}
		});
        // ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
    }


    public void setProject(Project project) {
        this.project = project;
        StringBuffer b = new StringBuffer();
        b.append("<html>Project Name: <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
        		+ project.getName() + "</b></html>");
        lblProjectName.setText(b.toString());
        if(project.getProjectLeader() != null) {
        	b = new StringBuffer();
        	b.append("<html>Project Leader: <b>&nbsp;&nbsp;&nbsp;&nbsp;"
        			+ project.getProjectLeader().getName() + "</b></html>");
        	lblLeader.setText(b.toString());
        	
        	b = new StringBuffer();
        	b.append("<html><h2>Are You</h2><h1>" + project.getProjectLeader().getName() + "?</h1></html>");
            lblCheckLeader.setText(b.toString());
        } else {
        	b = new StringBuffer();
        	b.append("<html><h2>There has not been selected a project leader for</h2><h1>" + project.getName() + "</h1></html>");
            lblCheckLeader.setText(b.toString());
            btnYes.setVisible(false);
            btnNo.setVisible(false);
            btnOkay.setVisible(true);
        }
    }

    public void setVisible(boolean aFlag) {panelCreateActivity.setVisible(aFlag); }


    public void clear() {
        employeeNameField.setText("");
        activityNameField.setText("");
        amountOfHours.setText("");
        lblLeader.setText("Project Leader:");
        lblProjectName.setText("Project Name:");
        panelCheckLeader.setVisible(true);
        panelActivityDetails.setVisible(false);
        panelCreateActivitySuccess.setVisible(false);
        startWeekField.setText("");
        endWeekField.setText("");
        startYearComboBox.setSelectedItem(2019);
        endYearComboBox.setSelectedItem(2019);
        btnYes.setVisible(true);
        btnNo.setVisible(true);
        btnOkay.setVisible(false);
    }


    private void setSuccessMessage() {
        StringBuffer b = new StringBuffer();
        b.append("<html><h1>The activity \"");
        b.append(activity.getName());
        b.append("\" was created!</h1><br>");
        b.append("<p>You can now add another activity to this project.</p></html>");
        lblSuccessMessage.setText(b.toString());
    }

    public void createActivity(){
        // Set activity name
        String name = activityNameField.getText();
        int projectNumber = project.getProjectNumber();
        Activity activity = null;

        if(name.equals("")){
            System.out.println("The activity needs a name");
        } else {
            // Create the activity
            try {
                activity = planningApp.addActivity(projectNumber, name, null, null, 0,project.getProjectLeader().getInitials());
            } catch (OperationNotAllowedException | NotProjectLeaderException e) {
            	System.out.println(e.getMessage());
            }

            // Add a start date to the activity
            if (startWeekField.getText().equals("")) { // All fields need to be filled out
                System.out.println("The activity was created without a start date");
            } else {
                try {
                    int week = Integer.parseInt(startWeekField.getText());
                    int year = Integer.parseInt(startYearComboBox.getSelectedItem().toString());
                    GregorianCalendar startDate = new GregorianCalendar(); 
                    startDate.setWeekDate(year, week, GregorianCalendar.SUNDAY);
                    planningApp.editStartDateOfActivity(startDate, project.getProjectNumber(),name, project.getProjectLeader().getInitials());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Add an end date to the activity
            if (endWeekField.getText().equals("")) { // All fields need to be filled out
                System.out.println("The activity was created without an end date");
            } else {
                try {
                    int week = Integer.parseInt(endWeekField.getText());
                    int year = Integer.parseInt(endYearComboBox.getSelectedItem().toString());
                    GregorianCalendar endDate = new GregorianCalendar();
                    endDate.setWeekDate(year, week, GregorianCalendar.SATURDAY);
                    planningApp.editEndDateOfActivity(endDate, project.getProjectNumber(),name, project.getProjectLeader().getInitials());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Add the expected amount of hours
            if (!amountOfHours.getText().equals("")) {
            	try{
                    float expectedAmountOfHours = Float.parseFloat(amountOfHours.getText());
                    planningApp.editExpectedAmountOfHoursForActivity(expectedAmountOfHours,project.getProjectNumber(), name, project.getProjectLeader().getInitials());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
            // Add employee to the activity by initials
            Employee employee = null;
            try{
                employee = planningApp.searchForEmployee(employeeNameField.getText());
                activity.assignEmployee(employee);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            

            this.activity = activity;
        }
    }
}
