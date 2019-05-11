package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;

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

import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class EditActivitiesScreen {

    private EditProjectScreen parentScreen;
    private PlanningApp planningApp;
    private JPanel panelEditActivities;
	private JPanel panelSuccessMessage;
    private JPanel panelEditActivitySearch;    
    private JPanel panelEditActivityDetails;
    private JPanel panelAddEmployee; 
    private JPanel panelCheckLeader; 
	private JList<Activity> listSearchResult;
	private DefaultListModel<Activity> searchResults;
	private JList<Employee> listEmployees;
	private DefaultListModel<Employee> searchEmployees;
	private JLabel lblSearchResultDetail;
	private JLabel lblSearchResultEmployees; 
    private JTextField activityNameField;
    private JTextField employeeNameField;
    private JTextField amountOfHours;
	private JButton btnBack;
    private JButton btnAdd;
    private JButton btnEdit; 
    private JButton btnRemove;
    private JButton btnPrevious;
    private JButton btnSave;
    private JButton btnYes; 
    private JButton btnNo; 
    private JButton btnOkay; 
    private JLabel lblSuccessMessage;
    private JLabel activityReminderField; 
    private JLabel lblPhase; 
    private JLabel lblProjectName;
    private JLabel lblLeader;
    private JLabel lblCheckLeader;
    private JTextField startDayField;
    private JTextField startMonthField;
    private JTextField startYearField;
    private JTextField endDayField;
    private JTextField endMonthField;
    private JTextField endYearField;
    private JTextField searchField; 
    private Activity activity;
    private int projectNumber;
    private Project project;
	private JTextField startWeekField;
	private JTextField endWeekField;
	private int firstYear = 2000;
	private int lastYear = 2040;
	private JComboBox<Integer> startYearComboBox;
	private JComboBox<Integer> endYearComboBox;
	
    

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
    	
     // Check Project Leader 
        panelCheckLeader = new JPanel();
        panelEditActivities.add(panelCheckLeader);
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
				panelEditActivitySearch.setVisible(true);
			}
		});
        panelCheckLeader.add(btnYes);
        
        btnNo = new JButton("No");
        btnNo.setBounds(40, 150, 90, 40);
        btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                clear();
                parentScreen.setVisible(true);
			}
		});
        panelCheckLeader.add(btnNo);
        
        btnOkay = new JButton("Okay");
        btnOkay.setBounds(90, 150, 90, 40);
        btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                clear();
                parentScreen.setVisible(true);
			}
		});
        panelCheckLeader.add(btnOkay);
        btnOkay.setVisible(false);
        // End of panel check project leader 
        
        
        
        
        
        //Panel showing all activities for a project 
        panelEditActivitySearch = new JPanel();
        panelEditActivitySearch.setBounds(0, 28, 350, 500);
		panelEditActivities.add(panelEditActivitySearch);
		panelEditActivitySearch.setLayout(null);
		panelEditActivitySearch.setVisible(false);
        
		
		lblPhase = new JLabel();
		lblPhase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhase.setVerticalAlignment(SwingConstants.TOP);
		lblPhase.setBounds(160, 0, 150, 90);
		StringBuffer b = new StringBuffer();
		b.append("<html><h1>&nbsp;Step 1</h1>Select Activity</html>");
		lblPhase.setText(b.toString());
		panelEditActivitySearch.add(lblPhase);
		
		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setBounds(50, 100, 100, 30);
		panelEditActivitySearch.add(lblActivity);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProject(project); 
				searchActivities(project.getProjectNumber());
			}
		});
		searchField.setBounds(100, 100, 130, 30);
		panelEditActivitySearch.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActivities(project.getProjectNumber());
			}
		});
		btnSearch.setBounds(235, 100, 105, 30);
		panelEditActivitySearch.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		searchResults = new DefaultListModel<>(); 
		listSearchResult = new JList<Activity>(searchResults);
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
		listSearchResult.setVisibleRowCount(4);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(50, 140, 295, 80);
		panelEditActivitySearch.add(listScrollPane);
		
		// Edit activity button and add activity button 
		btnEdit = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Edit</h2></html>");
		btnEdit.setText(b.toString());
		btnEdit.setBounds(245, 290, 90, 35);
		panelEditActivitySearch.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSearchResult.getSelectedIndex() == -1) {
					System.out.println("You need to select an activity");
				} else {
					activity = listSearchResult.getSelectedValue();
					projectNumber = project.getProjectNumber();
					setActivity();
					panelEditActivitySearch.setVisible(false);
					panelCheckLeader.setVisible(false);
					panelEditActivityDetails.setVisible(true);
				}
			}
		});
		
		btnAdd = new JButton();
		b = new StringBuffer(); b.append("<html><h2>Add-activity</h2></html>");
		btnAdd.setText(b.toString());
		btnAdd.setBounds(50, 290, 140, 35);
		panelEditActivitySearch.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Something to redirect to create activity screen
				
				panelEditActivitySearch.setVisible(false);
				panelEditActivityDetails.setVisible(false);
				panelCheckLeader.setVisible(false);
				searchResults.clear();
			}
		});
		
		
        
        // Show all employees for that activity 
		panelEditActivityDetails = new JPanel();
        panelEditActivities.add(panelEditActivityDetails);
        panelEditActivityDetails.setLayout(null);
        panelEditActivityDetails.setBounds(60, 60, 330, 390);
        panelEditActivityDetails.setVisible(false);
     		
     		
      searchEmployees = new DefaultListModel<>();
     	listEmployees = new JList<Employee>(searchEmployees);
     	listEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     	listEmployees.setSelectedIndex(-1);
     	listEmployees.setVisibleRowCount(7);
        listScrollPane = new JScrollPane(listEmployees);

        listScrollPane.setBounds(80, 120, 250, 150);
     	panelEditActivityDetails.add(listScrollPane);
     		
//     	btnAdd = new JButton();
//     	StringBuffer b = new StringBuffer(); 
//     	b.append("<html><h2>Add</h2></html>");
//     	btnAdd.setText(b.toString());
//     	btnAdd.setBounds(245, 290, 90, 40);
//     	panelActivityDetails.add(btnAdd);
//     	btnAdd.addActionListener(new ActionListener() {
//	     	public void actionPerformed(ActionEvent e) {
//	     		if (listEmployees.getSelectedIndex() == -1) {
//	     			panelAddEmployee.setVisible(true);
//	     			
//	     		} else {
//	     			System.out.println("");
//	     		}
//	     	}
//     	});
     		
//     	btnRemove = new JButton(); 
//     	b = new StringBuffer(); b.append("<html><h2>Remove</h2></html>");
//    	btnRemove.setText(b.toString());
//    	btnRemove.setBounds(80, 290, 120, 40);
//     	panelActivityDetails.add(btnRemove);
//     	btnPrevious.addActionListener(new ActionListener() {
//     		public void actionPerformed(ActionEvent e) {
//     			panelAddEmployee.setVisible(true);
//     			panelActivityDetails.setVisible(false);
//     			// Activities.clear(); ? 
//     		}
//     	});
//     	
//     	btnPrevious = new JButton();
//     	b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
//     	btnPrevious.setText(b.toString());
//     	btnPrevious.setBounds(80, 290, 120, 40);
//     	panelActivityDetail.add(btnPrevious);
//     	btnPrevious.addActionListener(new ActionListener() {
//     		public void actionPerformed(ActionEvent e) {
//     			panelSelectEmployee.setVisible(true);
//     			panelSelectActivity.setVisible(false);
//     			panelGiveTime.setVisible(false);
//     			relevantActivities.clear();
//     		}
//     	});
        
        
		
		
        
        // Add employee
        
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
    
    public void searchActivities(int projectNumber) {
    	// Reminder to select an activity 	
    	StringBuffer b = new StringBuffer();
		b.append("<html><b>Reminder:</b> Select the<br>activity</html>");
		activityReminderField.setText(b.toString());
		
		searchResults.clear();	
		try {
			planningApp.searchForActivitiesByName(projectNumber, searchField.getText())
			.forEach((m) -> {searchResults.addElement(m);});
		} catch (OperationNotAllowedException e) {
			e.printStackTrace();
		} 
    	
    }
    
    public void setActivity() {
    	
    }
    
    public void editActivity() {
    	// Update name there is no way to update a name
    	
    	// Update start week 
    	if (!(startDayField.getText().equals("") || 
				startMonthField.getText().equals("") || 
				startYearField.getText().equals(""))) { // All fields need to be filled out
			try {
				int day = Integer.parseInt(startDayField.getText());
				int month = Integer.parseInt(startMonthField.getText());
				int year = Integer.parseInt(startYearField.getText());
				GregorianCalendar startDate = new GregorianCalendar(year, month - 1, day);
				planningApp.editStartDateOfActivity(startDate, project.getProjectNumber(), activity.getName(), null);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
    	
    	// Update end week
    	// Update expected amount of hours 
    }

	public void setVisible(boolean b) {
		panelEditActivities.setVisible(b);
	}
	
	public void clear() {
		employeeNameField.setText("");
        activityNameField.setText("");
        amountOfHours.setText("");
        lblLeader.setText("Project Leader:");
        lblProjectName.setText("Project Name:");
        startWeekField.setText("");
        endWeekField.setText("");
        startYearComboBox.setSelectedItem(2019);
        endYearComboBox.setSelectedItem(2019); 
		searchField.setText("");
		searchResults.clear();
    }
//
//	protected void searchEmployees() {
//		// Show a reminder to select the employee
//		StringBuffer b = new StringBuffer();
//		b.append("<html><b>Reminder:</b> Select the<br>employee</html>");
//		employeeReminderField.setText(b.toString());
//		
//		searchResults.clear();
//		try {
//			planningApp.searchForEmployeesByName(searchField.getText())
//			.forEach((m) -> {searchResults.addElement(m);});
//		} catch (OperationNotAllowedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}

}
