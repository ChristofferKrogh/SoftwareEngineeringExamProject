package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
//import javax.swing.JFrame;
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
import dtu.planning.app.Project;


public class ProjectsScreen {
	
	private MainScreen parentWindow;
	private PlanningApp planningApp;
	private CreateProjectScreen createProjectScreen;
	private JPanel panelProjects;
	private JTextField searchField;
	private JList<Project> listSearchResult;
	private DefaultListModel<Project> searchResults;
	private JLabel lblSearchResultDetail;
	private JButton btnBack;
	
	public ProjectsScreen(PlanningApp planningApp, MainScreen parentWindow) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelProjects = new JPanel();
		parentWindow.addPanel(panelProjects);
		panelProjects.setLayout(null);
		panelProjects.setBorder(BorderFactory.createTitledBorder(
                "Projects"));
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProjects();
			}
		});
		searchField.setBounds(138, 28, 130, 26);
		panelProjects.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProjects();
			}
		});
		btnSearch.setBounds(148, 68, 117, 29);
		panelProjects.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		JButton btnCreateProject = new JButton("Create Project");
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				createProjectScreen.setVisible(true);
			}
		});
		btnCreateProject.setBounds(225, 385, 150, 50);
		panelProjects.add(btnCreateProject);
		
		JButton btnEditProject = new JButton("Edit Project");
		btnEditProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnEditProject.setBounds(25, 385, 150, 50);
		panelProjects.add(btnEditProject);
		
		searchResults = new DefaultListModel<>();
		listSearchResult = new JList<Project>(searchResults);
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting() == false) {

		            if (listSearchResult.getSelectedIndex() == -1) {
		            	lblSearchResultDetail.setText("");

		            } else {
		            	lblSearchResultDetail.setText(new ProjectPrinter(listSearchResult.getSelectedValue()).printDetail());
		            }
		        }
			}
		});
		listSearchResult.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(21, 109, 361, 100);
		panelProjects.add(listScrollPane);
		
		JPanel panelSearchResult = new JPanel();
		panelSearchResult.setBounds(21, 230, 361, 135);
		panelProjects.add(panelSearchResult);
		panelSearchResult.setBorder(BorderFactory.createTitledBorder(
                "Detail"));
		panelSearchResult.setLayout(null);
		
		lblSearchResultDetail = new JLabel("");
		lblSearchResultDetail.setVerticalAlignment(SwingConstants.TOP);
		lblSearchResultDetail.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchResultDetail.setBounds(23, 19, 318, 120);
		panelSearchResult.add(lblSearchResultDetail);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelProjects.add(btnBack);
		
		createProjectScreen = new CreateProjectScreen(planningApp, this);

	}
	protected void searchProjects() {
		searchResults.clear();
		planningApp.searchForProjectsByName(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});
	}

	public void setVisible(boolean aFlag) {
		panelProjects.setVisible(aFlag);
	}

	public void clear() {
		searchField.setText("");
		searchResults.clear();
	}
	
	public void addPanel(JPanel panel) {
		parentWindow.addPanel(panel);
	}
}
