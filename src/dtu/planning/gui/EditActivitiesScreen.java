package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import dtu.planning.app.PlanningApp;

public class EditActivitiesScreen {

    private EditProjectScreen parentScreen;
    private PlanningApp planningApp;
    private JPanel panelEditActivities;
	private JPanel panelSuccessMessage;
	private JButton btnBack;
    


    public EditActivitiesScreen(PlanningApp planningApp, EditProjectScreen parentScreen) {
        this.planningApp = planningApp;
        this.parentScreen = parentScreen;
        initialize();
    }

    private void initialize() {
    	panelEditActivities = new JPanel();
		parentScreen.addPanel(panelEditActivities);
		panelEditActivities.setLayout(null);
		panelEditActivities.setBorder(BorderFactory.createTitledBorder(
                "Edit Activities"));
		
		// Back button
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clear();
                parentScreen.setVisible(true);
            }
        });
        btnBack.setBounds(21, 28, 59, 29);
        panelEditActivities.add(btnBack);
    	
        
        
        
        
    }
    
    public void setProject() {
    	
    }
    
    public void setActivity() {
    	
    }
    
    public void editActivity() {
    	
    }

	public void setVisible(boolean b) {
		panelEditActivities.setVisible(b);
	}
	
	public void clear() {
		/*
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
        */
    }


}
