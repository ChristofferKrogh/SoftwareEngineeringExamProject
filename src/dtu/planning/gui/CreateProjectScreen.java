package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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


public class CreateProjectScreen {

	private ProjectsScreen parentWindow;
	private PlanningApp planningApp;
	private JPanel panelCreateProject;
	private JTextField searchField;
	private JList<Employee> listSearchResult;
	private DefaultListModel<Employee> searchResults;
	private JLabel lblSearchResultDetail;
	private JButton btnBack;
	
	public CreateProjectScreen(PlanningApp planningApp, ProjectsScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelCreateProject = new JPanel();
		parentWindow.addPanel(panelCreateProject);
		panelCreateProject.setLayout(null);
		panelCreateProject.setBorder(BorderFactory.createTitledBorder(
                "Create Project"));
		
		JButton btnCreateProject = new JButton("Create Project");
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnCreateProject.setBounds(225, 385, 150, 50);
		panelCreateProject.add(btnCreateProject);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelCreateProject.add(btnBack);
		
		JLabel lblProjectName = new JLabel("Project Name: ");
		lblProjectName.setBounds(100, 28, 100, 30);
		panelCreateProject.add(lblProjectName);
		
		JTextField projectNameField = new JTextField();
		projectNameField.setBounds(210, 28, 140, 30);
		panelCreateProject.add(projectNameField);
		
		String[] comboBoxItems = {"Internal", "External"};
		JComboBox<String> internalOrExternalComboBox = new JComboBox<>(comboBoxItems);
		internalOrExternalComboBox.setBounds(90, 60, 150, 50);
		panelCreateProject.add(internalOrExternalComboBox);
		
		// ------------- Start and end dates ---------------------
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(100, 110, 100, 30);
		panelCreateProject.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(100, 140, 100, 30);
		panelCreateProject.add(lblEndDate);
		
		JTextField startDayField = new JTextField();
		startDayField.setBounds(210, 110, 30, 30);
		panelCreateProject.add(startDayField);
		
		JTextField startMonthField = new JTextField();
		startMonthField.setBounds(250, 110, 30, 30);
		panelCreateProject.add(startMonthField);
		
		JTextField startYearField = new JTextField();
		startYearField.setBounds(290, 110, 60, 30);
		panelCreateProject.add(startYearField);
		
		JTextField endDayField = new JTextField();
		endDayField.setBounds(210, 140, 30, 30);
		panelCreateProject.add(endDayField);
		
		JTextField endMonthField = new JTextField();
		endMonthField.setBounds(250, 140, 30, 30);
		panelCreateProject.add(endMonthField);
		
		JTextField endYearField = new JTextField();
		endYearField.setBounds(290, 140, 60, 30);
		panelCreateProject.add(endYearField);
		
		JLabel separatorOne = new JLabel("/");
		separatorOne.setBounds(240, 110, 30, 30);
		panelCreateProject.add(separatorOne);
		
		JLabel separatorTwo = new JLabel("/");
		separatorTwo.setBounds(280, 110, 30, 30);
		panelCreateProject.add(separatorTwo);
		
		JLabel separatorThree = new JLabel("/");
		separatorThree.setBounds(240, 140, 30, 30);
		panelCreateProject.add(separatorThree);
		
		JLabel separatorFour = new JLabel("/");
		separatorFour.setBounds(280, 140, 30, 30);
		panelCreateProject.add(separatorFour);
		
		// --------------------------------------------------------
		
		JLabel lblLeader = new JLabel("Project Leader:");
		lblLeader.setBounds(100, 180, 150, 30);
		panelCreateProject.add(lblLeader);
		
		searchField = new JTextField();
		searchField.setBounds(210, 180, 140, 30);
		panelCreateProject.add(searchField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployees();
			}
		});
		btnSearch.setBounds(165, 215, 100, 30);
		panelCreateProject.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		searchResults = new DefaultListModel<>();
		listSearchResult = new JList<Employee>(searchResults);
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResult.setSelectedIndex(0);
//		listSearchResult.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//		        if (e.getValueIsAdjusting() == false) {
//
//		            if (listSearchResult.getSelectedIndex() == -1) {
//		            	lblSearchResultDetail.setText("");
//
//		            } else {
//		            	lblSearchResultDetail.setText(new ProjectPrinter(listSearchResult.getSelectedValue()).printDetail());
//		            }
//		        }
//			}
//		});
		listSearchResult.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(100, 250, 250, 100);
		panelCreateProject.add(listScrollPane);

	}

	public void setVisible(boolean aFlag) {
		panelCreateProject.setVisible(aFlag);
	}
	
	protected void searchEmployees() {
		searchResults.clear();
		planningApp.searchForEmployeesByName(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});
	}
	
	public void clear() {
		searchField.setText("");
		searchResults.clear();
	}
}
