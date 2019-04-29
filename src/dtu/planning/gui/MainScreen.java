package dtu.planning.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
//import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.Project;

public class MainScreen {
	
	PlanningApp planningApp;
	ProjectsScreen projectsScreen;
	CreateProjectScreen createProjectScreen;
	EditProjectScreen editProjectScreen;
	RegularActivitiesScreen regularActivitiesScreen;
	CreateRegularActivityScreen createRegularActivityScreen;

	private JFrame frame;
	private JPanel panelMenu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen screen = new MainScreen();
					screen.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainScreen() throws Exception {
		planningApp = new PlanningApp();
		createExampleData();
		initialize();
	}

	private void createExampleData() throws Exception {
		planningApp.addEmployee(new Employee("Lars Larsen", "LL"));
		planningApp.addEmployee(new Employee("John Doe", "JD"));
		planningApp.addEmployee(new Employee("Jane Doe", "JaD"));
		planningApp.addEmployee(new Employee("Christian Knudsen", "CK"));
		
		planningApp.createProject("Test Project", true);
		planningApp.createProject("Test Project external", false);
		Project p1 = planningApp.createProject("Mow the lawn", true);
		planningApp.setProjectLeader(p1.getProjectNumber(), "LL");
		Project p2 = planningApp.createProject("Take out the trash", true);
		planningApp.setProjectLeader(p2.getProjectNumber(), "JD");
		Project p3 = planningApp.createProject("Do the dishes", true);
		planningApp.setProjectLeader(p3.getProjectNumber(), "JaD");
		// FIX
		GregorianCalendar startWeek = new GregorianCalendar();
		startWeek.setWeekDate(2019, 2, GregorianCalendar.SUNDAY);
		GregorianCalendar endWeek = new GregorianCalendar();
		endWeek.setWeekDate(2020, 4, GregorianCalendar.SATURDAY);
		planningApp.addRegularActivity(new Activity("Sickness", startWeek, endWeek), "CK");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_160236068959176");
		panelMenu.setLayout(null);
		panelMenu.setBorder(BorderFactory.createTitledBorder(
                "Main Menu"));
		
		JButton btnProjects = new JButton("Projects");
		btnProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				projectsScreen.setVisible(true);
				
			}

		});
		btnProjects.setBounds(104, 52, 193, 29);
		panelMenu.add(btnProjects);
		
		JButton btnRegularActivities = new JButton("Regular Activities");
		btnRegularActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				regularActivitiesScreen.setVisible(true);
			}

		});
		btnRegularActivities.setBounds(104, 90, 193, 29);
		panelMenu.add(btnRegularActivities);
		
		JButton btnActivities = new JButton("Activities");
		btnActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
//				ActivitiesScreen.setVisible(true);
			}

		});
		btnActivities.setBounds(104, 128, 193, 29);
		panelMenu.add(btnActivities);
		
		projectsScreen = new ProjectsScreen(planningApp, this);
		createProjectScreen = new CreateProjectScreen(planningApp, projectsScreen);
		editProjectScreen = new EditProjectScreen(planningApp, projectsScreen);
		regularActivitiesScreen = new RegularActivitiesScreen(planningApp, this);
		createRegularActivityScreen = new CreateRegularActivityScreen(planningApp, regularActivitiesScreen);
	}
	
	public void setVisible(boolean aFlag) {
		panelMenu.setVisible(aFlag);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void addPanel(JPanel panel) {
		frame.getContentPane().add(panel);
	}


}
