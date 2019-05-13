package dtu.planning.gui;

import java.awt.Color;
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
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class EditActivitiesScreen {

    private EditProjectScreen parentScreen;
    private CreateActivitiesScreenCopy createActivitiesScreenCopy;
	private PlanningApp planningApp;
    private JPanel panelEditActivities;
	private JPanel panelEditActivitySuccess;
    private JPanel panelEditActivitySearch;    
    private JPanel panelEditActivityDetails;
    private JPanel panelAddEmployee; 
    private JPanel panelCheckLeader; 
	private JList<Activity> listSearchResult;
	private JList<Employee> listSearchResultEmployee;
	private JList<Employee> listSearchResultAddEmployee; 
	private DefaultListModel<Activity> searchResults;
	private DefaultListModel<Employee> searchResultEmployee; 
	private DefaultListModel<Employee> searchResultsAddEmployee; 
    private JTextField activityNameField;
    private JTextField amountOfHours;
	private JButton btnBack;
	private JButton btnAddEmployee; 
    private JButton btnAdd;
    private JButton btnEdit; 
    private JButton btnRemove;
    private JButton btnPrevious;
    private JButton btnSave;
    private JButton btnYes; 
    private JButton btnNo; 
    private JButton btnOkay; 
    private JButton btnAddNewEmployee; 
    private JButton btnEditDate; 
    private JLabel lblSuccessMessage;
    private JLabel activityReminderField; 
    private JLabel lblPhase; 
    private JLabel lblPhaseEmployee; 
    private JLabel lblProjectName;
    private JLabel lblLeader;
    private JLabel lblCheckLeader;
    private JLabel lblPhaseChooseEmployee; 
    private JLabel lblActivity; 
    private JLabel lblAddEmployee; 
    private JLabel lblPhaseAddEmployee; 
    private JTextField searchField; 
    private JTextField searchFieldAddEmployee; 
    private Activity activity;
    private Project project;
	private JTextField startWeekField;
	private JTextField endWeekField;
	private int firstYear = 2000;
	private int lastYear = 2040;
	private JComboBox<Integer> startYearComboBox;
	private JComboBox<Integer> endYearComboBox; 
	
    

    public EditActivitiesScreen(PlanningApp planningApp, EditProjectScreen parentScreen) {
    	// TODO Remove planningApp from constructor, not used here
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
				panelEditActivities.setVisible(true);
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
        
        // TODO not used only initialization 
        lblProjectName = new JLabel("Project Name: ");
        lblLeader = new JLabel("Project Leader:");
        
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
		
		lblActivity = new JLabel("Activity:");
		lblActivity.setBounds(50, 100, 100, 30);
		panelEditActivitySearch.add(lblActivity);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActivities();
			}
		});
		searchField.setBounds(100, 100, 130, 30);
		panelEditActivitySearch.add(searchField);
		searchField.setColumns(10);
		
//		activityReminderField = new JLabel(); // Chosen to not show this activity reminder field 
//		activityReminderField.setBounds(5, 280, 87, 45);
//		panelEditActivitySearch.add(activityReminderField);
		
		
		
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
					setActivity(activity);
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
				createActivitiesScreenCopy.setProject(project);
				searchResults.clear();  
				setVisible(false);
				createActivitiesScreenCopy.setVisible(true);
			}
		});
		
		
        
        // Show all employees for that activity 
		panelEditActivityDetails = new JPanel();
        panelEditActivities.add(panelEditActivityDetails);
        panelEditActivityDetails.setLayout(null);
        panelEditActivityDetails.setBounds(0, 28, 350, 500);
        panelEditActivityDetails.setVisible(false);
     	
        // Setting headline for step 2 
        lblPhaseEmployee = new JLabel();
        lblPhaseEmployee.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhaseEmployee.setVerticalAlignment(SwingConstants.TOP);
        lblPhaseEmployee.setBounds(160, 0, 150, 90);
		StringBuffer R = new StringBuffer();
		R.append("<html><h1>&nbsp;Step 2</h1>Edit the selected activity</html>");
		lblPhaseEmployee.setText(R.toString());
		panelEditActivityDetails.add(lblPhaseEmployee);
        
		// Scroll panel with the employees assigned to the chosen activity
		lblPhaseChooseEmployee = new JLabel();
		lblPhaseChooseEmployee.setBounds(50, 70, 150, 30);
		StringBuffer b2 = new StringBuffer();
		b2.append("<html>All assigned employees:</html>");
		lblPhaseChooseEmployee.setText(b2.toString());
		panelEditActivityDetails.add(lblPhaseChooseEmployee);
		
        searchResultEmployee = new DefaultListModel<>();
		listSearchResultEmployee = new JList<Employee>(searchResultEmployee);
		listSearchResultEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResultEmployee.setSelectedIndex(0);
		listSearchResultEmployee.setVisibleRowCount(4);
        JScrollPane listScrollPaneEmployee = new JScrollPane(listSearchResultEmployee);
        listScrollPaneEmployee.setBounds(50, 100, 295, 80);
		panelEditActivityDetails.add(listScrollPaneEmployee);
     	
     		
     	btnAddEmployee = new JButton();
     	StringBuffer b3 = new StringBuffer(); 
     	b3.append("<html><h2>Add</h2></html>");
     	btnAddEmployee.setText(b3.toString());
     	btnAddEmployee.setBounds(255, 180, 90, 30);
     	panelEditActivityDetails.add(btnAddEmployee);
     	btnAddEmployee.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		if (listSearchResultEmployee.getSelectedIndex() == -1) {
	     			panelAddEmployee.setVisible(true);
	     			panelEditActivityDetails.setVisible(false);
	     			searchAllEmployees();	     			
	     		} else {
	     			System.out.println("");
	     		}
	     	}
     	});
     		
     	btnRemove = new JButton(); 
     	b = new StringBuffer(); b.append("<html><h2>Remove</h2></html>");
    	btnRemove.setText(b.toString());
    	btnRemove.setBounds(50, 180, 120, 30);
     	panelEditActivityDetails.add(btnRemove);
     	btnRemove.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			if (listSearchResultEmployee.getSelectedIndex() == -1) {
					System.out.println("You need to choose an employee to remove");
				} else {
					removeEmployee();
					searchResultEmployee.clear(); 
					setActivity(activity);  
				}	
     		}
     	});
     	
        // Activity name field
        JLabel lblActivityName = new JLabel("Activity Name:");
        lblActivityName.setBounds(50, 220, 100, 30);
        panelEditActivityDetails.add(lblActivityName);

        activityNameField = new JTextField();
        activityNameField.setBounds(150, 220, 140, 30);
        panelEditActivityDetails.add(activityNameField);



        // Set start week and end week
        startWeekField = new JTextField();
		startWeekField.setBounds(150, 250, 35, 30);
		panelEditActivityDetails.add(startWeekField);
		
		endWeekField = new JTextField();
		endWeekField.setBounds(150, 280, 35, 30);
		panelEditActivityDetails.add(endWeekField);
		
		Integer[] comboBoxItems = new Integer[lastYear-firstYear];
		for (int i = 0; i < lastYear - firstYear; i++) {
			comboBoxItems[i] = firstYear + i;
		}
		startYearComboBox = new JComboBox<>(comboBoxItems);
		startYearComboBox.setBounds(185, 250, 85, 30);
		startYearComboBox.setSelectedItem(2019);
		panelEditActivityDetails.add(startYearComboBox);
		endYearComboBox = new JComboBox<>(comboBoxItems);
		endYearComboBox.setBounds(185, 280, 85, 30);
		endYearComboBox.setSelectedItem(2019);
		panelEditActivityDetails.add(endYearComboBox);
		
		JLabel lblDates = new JLabel();
		lblDates.setVerticalAlignment(SwingConstants.TOP);
		lblDates.setHorizontalAlignment(SwingConstants.LEFT);
		lblDates.setBounds(50, 250, 260, 75);
		StringBuffer b4 = new StringBuffer();
		b4.append("<html><b>Start:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;week&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of<br><br>"
					 + "<b>End:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;week&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of</html>");
		lblDates.setText(b4.toString());
		panelEditActivityDetails.add(lblDates);

        // Amount of hours
        JLabel lblAmountOfHours = new JLabel("Expected hours:");
        lblAmountOfHours.setBounds(50, 310, 105, 30);
        panelEditActivityDetails.add(lblAmountOfHours);

        amountOfHours = new JTextField();
        amountOfHours.setBounds(150, 310, 140, 30);
        panelEditActivityDetails.add(amountOfHours);
     	
        
     	btnPrevious = new JButton();
     	b = new StringBuffer(); b.append("<html><h2>Previous</h2></html>");
     	btnPrevious.setText(b.toString());
     	btnPrevious.setBounds(50, 350, 100, 30);
     	panelEditActivityDetails.add(btnPrevious);
     	btnPrevious.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			panelEditActivityDetails.setVisible(false);
     			panelEditActivitySearch.setVisible(true);
     			searchResultEmployee.clear();
     		}
     	});
        
     	btnSave = new JButton(); 
     	b = new StringBuffer(); 
     	b.append("<html><h2>Save</h2></html>");
        btnSave.setText(b.toString());
        btnSave.setBounds(255, 350, 90, 30);
        panelEditActivityDetails.add(btnSave);
        btnSave.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			editActivity(); 
                panelEditActivityDetails.setVisible(false);
//                clear();
                panelEditActivitySuccess.setVisible(true);
     		}
        });
     	
        
        // Add employee panel
        panelAddEmployee = new JPanel();
        panelEditActivities.add(panelAddEmployee);
        panelAddEmployee.setLayout(null);
        panelAddEmployee.setBounds(0, 28, 350, 500);
        panelAddEmployee.setVisible(false);
        
        lblPhaseAddEmployee = new JLabel();
		lblPhaseAddEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhaseAddEmployee.setVerticalAlignment(SwingConstants.TOP);
		lblPhaseAddEmployee.setBounds(160, 0, 150, 90);
		b = new StringBuffer();
		b.append("<html><h1>&nbsp;Step 3</h1>Add another employee to the activty</html>");
		lblPhaseAddEmployee.setText(b.toString());
		panelAddEmployee.add(lblPhaseAddEmployee);
		
		lblAddEmployee = new JLabel("Employee:");
		lblAddEmployee.setBounds(50, 100, 100, 30);
		panelAddEmployee.add(lblAddEmployee);
		
		searchFieldAddEmployee = new JTextField();
		searchFieldAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAllEmployees();
			}
		});
		searchFieldAddEmployee.setBounds(100, 100, 130, 30);
		panelAddEmployee.add(searchFieldAddEmployee);
		searchFieldAddEmployee.setColumns(10);
		
		
		// Search after new employee to add to activity 
		searchResultsAddEmployee = new DefaultListModel<>();
		listSearchResultAddEmployee = new JList<Employee>(searchResultsAddEmployee);
		listSearchResultAddEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResultAddEmployee.setSelectedIndex(0);
		listSearchResultAddEmployee.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listSearchResultAddEmployee.getSelectedIndex() == -1) {
					searchFieldAddEmployee.setText("");
				} else {
					searchFieldAddEmployee.setText(listSearchResultAddEmployee.getSelectedValue().getInitials());
				}
			}
		});
		listSearchResultAddEmployee.setVisibleRowCount(4);
        JScrollPane listScrollPaneAddEmployee = new JScrollPane(listSearchResultAddEmployee);

        listScrollPaneAddEmployee.setBounds(50, 140, 295, 80);
		panelAddEmployee.add(listScrollPaneAddEmployee);
		
		btnAddNewEmployee = new JButton();
     	b = new StringBuffer(); 
     	b.append("<html><h2>Add</h2></html>");
     	btnAddNewEmployee.setText(b.toString());
     	btnAddNewEmployee.setBounds(255, 280, 90, 30);
     	panelAddEmployee.add(btnAddNewEmployee);
     	btnAddNewEmployee.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		if (listSearchResultAddEmployee.getSelectedIndex() == -1) {
	     			System.out.println("");	     			
	     		} else {
	     			panelAddEmployee.setVisible(false);
	     			panelEditActivityDetails.setVisible(true);
	     			addEmployee(); 
	     			searchResultEmployee.clear(); 
	     			setActivity(activity); 
	     		}
	     	}
     	});
		
		
		createActivitiesScreenCopy = new CreateActivitiesScreenCopy(planningApp, this); 
		
		// Success message 
		panelEditActivitySuccess = new JPanel();
	    panelEditActivities.add(panelEditActivitySuccess);
	    panelEditActivitySuccess.setBounds(50, 110, 300, 200);
	    panelEditActivitySuccess.setLayout(null);
	    panelEditActivitySuccess.setBorder(BorderFactory.createLineBorder(Color.green, 5));
	    panelEditActivitySuccess.setBackground(Color.white);
	    panelEditActivitySuccess.setVisible(false);
		JLabel lblSuccessMessage = new JLabel("The changes were saved");
		lblSuccessMessage.setBounds(75, 50, 200, 30);
		panelEditActivitySuccess.add(lblSuccessMessage);
	        
	    JButton btnEditNewActivity = new JButton("Edit another activity");
	    btnEditNewActivity.setBounds(45, 100, 190, 50);
	    panelEditActivitySuccess.add(btnEditNewActivity);
	    btnEditNewActivity.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				clear();
				setProject(project);
				panelEditActivitySuccess.setVisible(false);
				panelCheckLeader.setVisible(false);
				panelEditActivitySearch.setVisible(true);
			}
		});
        
    }
    
    private void initializeSearchResults() { 
		project.getActivities().forEach(a->{searchResults.addElement(a);});	
	}
    
    private void initializeSearchResultsEmployee() {
    	try {
        	activity.getAssignedEmployees().forEach((m) -> {searchResultEmployee.addElement(m);});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    private void initializeSearchResultsAllEmployees() { 
    	planningApp.getEmployees().forEach((m) -> {searchResultsAddEmployee.addElement(m);});	
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
         initializeSearchResults();
    }
    
    public void searchActivities() {

		searchResults.clear();	
		try {
			planningApp.searchForActivitiesByName(project.getProjectNumber(), searchField.getText())
			.forEach((m) -> {searchResults.addElement(m);});
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage()); // TODO correct new solution
		} 
    	
    }
    
    public void setActivity(Activity activity) {
    	this.activity = activity; 
    	
    	// Display given settings for activity 
    	StringBuffer b = new StringBuffer();
        b.append(activity.getName());
        activityNameField.setText(b.toString());
        
        b = new StringBuffer(); 
        b.append(activity.getExpectedAmountOfHours());
    	amountOfHours.setText(b.toString());
        
    	initializeSearchResultsEmployee(); 
    }
    
    public void editActivity() {
    	// There is no way to update a name
    	String name = activity.getName(); 
    	
    	// Update start week  to the activity
        if (!startWeekField.getText().equals("")) { // Only change the start week if there is new information
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

        // Update the end week of the activity
        if (!endWeekField.getText().equals("")) { // Only change if there is new information
            
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
    	    	
    	// Update expected amount of hours 
        if(!amountOfHours.getText().equals("")) {
        	float hours = Float.parseFloat(amountOfHours.getText()); 
        	try {
				planningApp.editExpectedAmountOfHoursForActivity(hours, project.getProjectNumber(), name, project.getProjectLeader().getInitials());
			} catch (ActivityNotFoundException | OperationNotAllowedException | NotProjectLeaderException e) {
				System.out.println(e.getMessage());
			}
        	
        }
        
    }

	public void setVisible(boolean b) {
		panelEditActivities.setVisible(b);
	}
	
	public void clear() {
		lblProjectName.setText("Projectname: ");
        lblLeader.setText("Project Leader: "); 
		
		btnYes.setVisible(true);
        btnNo.setVisible(true);
        btnOkay.setVisible(false);
    	panelEditActivitySuccess.setVisible(false);
        panelEditActivitySearch.setVisible(false);    
        panelEditActivityDetails.setVisible(false);
        panelAddEmployee.setVisible(false); 
        panelCheckLeader.setVisible(true);
        
        startWeekField.setText("");
        endWeekField.setText("");
        startYearComboBox.setSelectedItem(2019);
        endYearComboBox.setSelectedItem(2019); 
		searchField.setText("");
		searchResults.clear();
		searchResultEmployee.clear();
    }

	protected void removeEmployee() {
		String employeeInitials = listSearchResultEmployee.getSelectedValue().getInitials();  
		System.out.println(employeeInitials);
		try {
			planningApp.removeEmployeeFromActivity(project.getProjectNumber(), activity.getName(), employeeInitials, project.getProjectLeader().getInitials());
		} catch (OperationNotAllowedException | NotProjectLeaderException | ActivityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public void addPanel(JPanel panel) {
		parentScreen.addPanel(panel);	
	}
	
	public void searchEmployees() {
		searchResultEmployee.clear();
		activity.getAssignedEmployees().forEach((m) -> {searchResultEmployee.addElement(m);});
			
	}
	
	public void searchAllEmployees() {		
		searchResultsAddEmployee.clear();
		try {
			planningApp.searchForEmployeesByName(searchFieldAddEmployee.getText())
			.forEach((m) -> {searchResultsAddEmployee.addElement(m);});
		} catch (OperationNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void addEmployee() {
		String initials = searchFieldAddEmployee.getText(); 
        try{
            planningApp.assignEmployee(project.getProjectNumber(), activity.getName(), project.getProjectLeader().getInitials(), initials);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
    private void setSuccessMessage() {
        StringBuffer b = new StringBuffer();
        b.append("<html><h1>The activity \"");
        b.append(activity.getName());
        b.append("\" was changed!</h1><br>");
        b.append("<p>You can now add or edit another activity to this project.</p></html>");
        lblSuccessMessage.setText(b.toString());
    }

}
