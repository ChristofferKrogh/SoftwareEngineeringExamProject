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
    private JPanel panelSuccessMessage;
    private JTextField projectNameField;
    private JTextField activityNameField;
    private JTextField employeeNameField;
    private JTextField amountOfHours;
    private JButton btnBack;
    private JLabel lblSuccessMessage;
    private JTextField leaderReminderField;
    private JTextField startDayField;
    private JTextField startMonthField;
    private JTextField startYearField;
    private JTextField endDayField;
    private JTextField endMonthField;
    private JTextField endYearField;
    private Activity activity;
    private Project project;

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

        // Success Message
        panelCreateActivitySuccess = new JPanel();
        parentWindow.addPanel(panelCreateActivitySuccess);
        panelCreateActivitySuccess.setLayout(null);
        panelCreateActivitySuccess.setBorder(BorderFactory.createTitledBorder(
                "Success Message"));


        // Back botton
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
        panelCreateActivity.add(btnBack);

        // Project name field
        JLabel lblProjectName = new JLabel("Project Name: ");
        lblProjectName.setBounds(100, 28, 100, 30);
        panelCreateActivity.add(lblProjectName);

        projectNameField = new JTextField();
        projectNameField.setBounds(210, 28, 140, 30);
        panelCreateActivity.add(projectNameField);

        // Project leader for the project <<<<< TODO
        JLabel lblLeader = new JLabel("Project Leader:");
        lblLeader.setBounds(100, 68, 150, 30);
        panelCreateActivity.add(lblLeader);

        leaderReminderField = new JTextField();
        leaderReminderField.setBounds(210, 68, 140, 30);
        panelCreateActivity.add(leaderReminderField);


        // Activity name field
        JLabel lblActivityName = new JLabel("Activity Name: ");
        lblActivityName.setBounds(100, 108, 100, 30);
        panelCreateActivity.add(lblActivityName);

        activityNameField = new JTextField();
        activityNameField.setBounds(210, 108, 140, 30);
        panelCreateActivity.add(activityNameField);



        // Set start date, end date and amount of hours
        JLabel lblDateFormat = new JLabel("Day / Month / Year");
        lblDateFormat.setBounds(215, 145, 200, 30);
        panelCreateActivity.add(lblDateFormat);

        JLabel lblStartDate = new JLabel("Start Date:");
        lblStartDate.setBounds(100, 170, 100, 30);
        panelCreateActivity.add(lblStartDate);

        JLabel lblEndDate = new JLabel("End Date:");
        lblEndDate.setBounds(100, 200, 100, 30);
        panelCreateActivity.add(lblEndDate);

        startDayField = new JTextField();
        startDayField.setBounds(210, 170, 30, 30);
        panelCreateActivity.add(startDayField);

        startMonthField = new JTextField();
        startMonthField.setBounds(250, 170, 30, 30);
        panelCreateActivity.add(startMonthField);

        startYearField = new JTextField();
        startYearField.setBounds(290, 170, 60, 30);
        panelCreateActivity.add(startYearField);

        endDayField = new JTextField();
        endDayField.setBounds(210, 200, 30, 30);
        panelCreateActivity.add(endDayField);

        endMonthField = new JTextField();
        endMonthField.setBounds(250, 200, 30, 30);
        panelCreateActivity.add(endMonthField);

        endYearField = new JTextField();
        endYearField.setBounds(290, 200, 60, 30);
        panelCreateActivity.add(endYearField);

        JLabel separatorOne = new JLabel("/");
        separatorOne.setBounds(242, 170, 30, 30);
        panelCreateActivity.add(separatorOne);

        JLabel separatorTwo = new JLabel("/");
        separatorTwo.setBounds(282, 170, 30, 30);
        panelCreateActivity.add(separatorTwo);

        JLabel separatorThree = new JLabel("/");
        separatorThree.setBounds(242, 200, 30, 30);
        panelCreateActivity.add(separatorThree);

        JLabel separatorFour = new JLabel("/");
        separatorFour.setBounds(282, 200, 30, 30);
        panelCreateActivity.add(separatorFour);

        // Amount of hours
        JLabel lblAmountOfHours = new JLabel("Expected hours: ");
        lblAmountOfHours.setBounds(100, 240, 100, 30);
        panelCreateActivity.add(lblAmountOfHours);

        amountOfHours = new JTextField();
        amountOfHours.setBounds(210, 240, 140, 30);
        panelCreateActivity.add(amountOfHours);

        // Add employee button
        JLabel lblEmployee = new JLabel("Employee:");
        lblEmployee.setBounds(100, 270, 150, 30);
        panelCreateActivity.add(lblEmployee);

        employeeNameField = new JTextField();
        employeeNameField.setBounds(210, 270, 140, 30);
        panelCreateActivity.add(employeeNameField);

        // Set success message
        JPanel panelSuccessMessage = new JPanel();
        panelSuccessMessage.setBounds(50, 65, 330, 310);
        panelCreateActivitySuccess.add(panelSuccessMessage);
        panelSuccessMessage.setLayout(null);

        lblSuccessMessage = new JLabel("");
        lblSuccessMessage.setVerticalAlignment(SwingConstants.TOP);
        lblSuccessMessage.setBounds(0, 0, 330, 310);
        panelSuccessMessage.add(lblSuccessMessage);

        //  Add activity button
        JButton btnCreateActivity = new JButton("Add Activity");
        btnCreateActivity.setBounds(225, 385, 150, 50);
        panelCreateActivity.add(btnCreateActivity);
        btnCreateActivity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createActivity();
                if (activity != null) {
                    setVisible(false);
                    setSuccessMessage();
                    panelCreateActivitySuccess.setVisible(true);
                }
            }
        });



    }


    public void setProject(Project project) {
        this.project = project;
        projectNameField.setText(project.getName());
        if(project.getProjectLeader() != null) {
            leaderReminderField.setText(project.getProjectLeader().getName());
        }
    }

    public void setVisible(boolean aFlag) {panelCreateActivity.setVisible(aFlag); }


    public void clear() {
        projectNameField.setText("");
        leaderReminderField.setText("");
        employeeNameField.setText("");
        startDayField.setText("");
        startMonthField.setText("");
        startYearField.setText("");
        endDayField.setText("");
        endMonthField.setText("");
        endYearField.setText("");
    }


    private void setSuccessMessage() {
        StringBuffer b = new StringBuffer();
        b.append("<html><h1>The activity \"");
        b.append(activity.getName());
        b.append("\" was created!</h1><br>");
        b.append("<p>You can now add another activity for this project.</p></html>");
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
                activity = planningApp.addActivity(projectNumber, name, null, null, 0);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }

            // Add a start date to the activity
            if (startDayField.getText().equals("") ||
                    startMonthField.getText().equals("") ||
                    startYearField.getText().equals("")) { // All fields need to be filled out
                System.out.println("The activity was created without a start date");
            } else {
                try {
                    int day = Integer.parseInt(startDayField.getText());
                    int month = Integer.parseInt(startMonthField.getText());
                    int year = Integer.parseInt(startYearField.getText());
                    GregorianCalendar startDate = new GregorianCalendar(year, month, day);
                    planningApp.editStartDateOfActivity(startDate, project.getProjectNumber(),name);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Add an end date to the activity
            if (endDayField.getText().equals("") ||
                    endMonthField.getText().equals("") ||
                    endYearField.getText().equals("")) { // All fields need to be filled out
                System.out.println("The Activity was created without an end date");
            } else {
                try {
                    int day = Integer.parseInt(endDayField.getText());
                    int month = Integer.parseInt(endMonthField.getText());
                    int year = Integer.parseInt(endYearField.getText());
                    GregorianCalendar endDate = new GregorianCalendar(year, month, day);
                    planningApp.editEndDateOfActivity(endDate, project.getProjectNumber(),name);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Add the expected amount of hours
            try{
                int expectedAmountOfHours = Integer.parseInt(amountOfHours.getText());
                planningApp.editExpectedAmountOfHoursForActivity(expectedAmountOfHours,project.getProjectNumber(), name);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Add employee to the activity by initials
            Employee employee =null;
            try{
                employee = planningApp.searchForEmployee(employeeNameField.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            activity.assignEmployee(employee);

            this.activity = activity;


        }

    }
}
