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
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;

public class MainScreen {
	
	PlanningApp planningApp;
	ProjectsScreen projectsScreen;
	CreateProjectScreen createProjectScreen;
	CreateActivitiesScreen createActivitiesScreen; 
	EditProjectScreen editProjectScreen;
	RegularActivitiesScreen regularActivitiesScreen;
	CreateRegularActivityScreen createRegularActivityScreen;
	ReportTimeScreen reportTimeScreen;
	private int firstYear = 2000;
	private int lastYear = 2040;

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
		GregorianCalendar startWeek = new GregorianCalendar();
		startWeek.setWeekDate(2019, 2, GregorianCalendar.SUNDAY);
		GregorianCalendar endWeek = new GregorianCalendar();
		endWeek.setWeekDate(2020, 4, GregorianCalendar.SATURDAY);
		planningApp.addRegularActivity(new Activity("Sickness", startWeek, endWeek), "CK");
		planningApp.addRegularActivity(new Activity("Vacation", startWeek, endWeek), "CK");
		planningApp.addRegularActivity(new Activity("Child Sickness", startWeek, endWeek), "CK");
		planningApp.addActivity(p1.getProjectNumber(), "Buy lawnmower", startWeek, endWeek, 4, p1.getProjectLeader().getInitials());
		planningApp.assignEmployee(p1.getProjectNumber(), "Buy lawnmower", p1.getProjectLeader().getInitials(), "JaD");
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
		
		JButton btnReportTime = new JButton("Report Time");
		btnReportTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				reportTimeScreen.setVisible(true);
			}

		});
		btnReportTime.setBounds(104, 190, 193, 29);
		panelMenu.add(btnReportTime);
		
		JButton btnCorrectTime = new JButton("Correct Reported Time");
		btnReportTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
//				regularActivitiesScreen.setVisible(true);
			}

		});
		btnCorrectTime.setBounds(104, 238, 193, 29);
		panelMenu.add(btnCorrectTime);
		
		JButton btnGetTime = new JButton("Get Daily Reported Time");
		btnGetTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Project p : planningApp.getProjects()) {
					for (Activity a : p.getActivities()) {
						for (TimeRegistration t : a.getTimeRegistrations()) {
							System.out.println(t);
						}
					}
				}
				
				
			}

		});
		btnGetTime.setBounds(104, 286, 193, 29);
		panelMenu.add(btnGetTime);
		
		projectsScreen = new ProjectsScreen(planningApp, this);
		createProjectScreen = new CreateProjectScreen(planningApp, projectsScreen);
		createActivitiesScreen = new CreateActivitiesScreen(planningApp, projectsScreen); 
		editProjectScreen = new EditProjectScreen(planningApp, projectsScreen);
		regularActivitiesScreen = new RegularActivitiesScreen(planningApp, this, firstYear, lastYear);
		createRegularActivityScreen = new CreateRegularActivityScreen(planningApp, regularActivitiesScreen, firstYear, lastYear);
		reportTimeScreen = new ReportTimeScreen(planningApp, this);
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
