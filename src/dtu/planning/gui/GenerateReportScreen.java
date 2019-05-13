package dtu.planning.gui;

import dtu.planning.app.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class GenerateReportScreen {
	
	private ProjectsScreen parentWindow;
    private PlanningApp planningApp;
    private JPanel panelCreateActivity;
    private JPanel panelCheckLeader;
    private JPanel panelActivityDetails;
    private JEditorPane activityNamePane;
    private JEditorPane reportedTimePane;
    private JEditorPane estimatedTimePane;
    private JButton btnBack;
    private JButton btnYes;
    private JButton btnNo;
    private JButton btnOkay;
    private JLabel lblProjectName;
    private JLabel lblCheckLeader;
    private Project project;
	
	public GenerateReportScreen(PlanningApp planningApp, ProjectsScreen parentWindow) {
		this.planningApp = planningApp;
        this.parentWindow = parentWindow;
        initialize();
	}
	
	private void initialize() {
        panelCreateActivity = new JPanel();
        parentWindow.addPanel(panelCreateActivity);
        panelCreateActivity.setLayout(null);
        panelCreateActivity.setBorder(BorderFactory.createTitledBorder(
                "Generate Report"));
        
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
				generateReport();
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
        
        // ––––––––––––––––––– Report Details –––––––––––––––––––––––––––
        panelActivityDetails = new JPanel();
        panelCreateActivity.add(panelActivityDetails);
        panelActivityDetails.setLayout(null);
        panelActivityDetails.setBounds(0, 30, 375, 390);
        panelActivityDetails.setVisible(false);
        
        // Project name field
        lblProjectName = new JLabel("Project Name: ");
        lblProjectName.setBounds(100, 0, 260, 30);
        panelActivityDetails.add(lblProjectName);
        
        // Report
        activityNamePane = new JEditorPane();
        activityNamePane.setBounds(20, 50, 140, 300);
        activityNamePane.setContentType("text/html");
        panelActivityDetails.add(activityNamePane);
        
        reportedTimePane = new JEditorPane();
        reportedTimePane.setBounds(165, 50, 100, 300);
        reportedTimePane.setContentType("text/html");
        panelActivityDetails.add(reportedTimePane);
        
        estimatedTimePane = new JEditorPane();
        estimatedTimePane.setBounds(270, 50, 150, 300);
        estimatedTimePane.setContentType("text/html");
        panelActivityDetails.add(estimatedTimePane);
        
        // ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
	}
	
	private void generateReport() {
		Report report = null;
		try {
			report = planningApp.generateReport(project.getProjectNumber(), project.getProjectLeader());
		} catch (NotProjectLeaderException | OperationNotAllowedException e) {
			setConsoleMessage(e.getMessage());
			return;
		}
		
		// Set report editor pane
		StringBuffer b1 = new StringBuffer();
		StringBuffer b2 = new StringBuffer();
		StringBuffer b3 = new StringBuffer();
		b1.append("<html><h3>Activity</h3>");
		b2.append("<html><h3>Reported Time</h3>");
		b3.append("<html><h3>Estimated Time</h3>");
		for (Activity a : project.getActivities()) {
			b1.append(a.getName() + "<br>");
			try {
				double reportedTime = report.getReportedTimeForActivity(a.getName());
				reportedTime = BigDecimal.valueOf(reportedTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				double estimatedTime = report.getEstimatedTimeForActivity(a.getName());
				estimatedTime = BigDecimal.valueOf(estimatedTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				b2.append(reportedTime + " hours<br>");
				b3.append(estimatedTime + " hours<br>");
			} catch (ActivityNotFoundException e) {
				setConsoleMessage(e.getMessage());
			}
		}
		b1.append("</html>");
		b2.append("</html>");
		b3.append("</html>");
        activityNamePane.setText(b1.toString());
        reportedTimePane.setText(b2.toString());
        estimatedTimePane.setText(b3.toString());
	}

	public void setProject(Project project) {
        this.project = project;
        // set project name label
        StringBuffer b = new StringBuffer();
        b.append("<html>Project Name: <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
        		+ project.getName() + "</b></html>");
        lblProjectName.setText(b.toString());
        
        // Set check leader panel
        if(project.getProjectLeader() != null) {
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
        lblProjectName.setText("Project Name:");
        panelCheckLeader.setVisible(true);
        panelActivityDetails.setVisible(false);
//        panelCreateActivitySuccess.setVisible(false);
        btnYes.setVisible(true);
        btnNo.setVisible(true);
        btnOkay.setVisible(false);
    }
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}

}
