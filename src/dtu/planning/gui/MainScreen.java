package dtu.planning.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
//import dtu.planning.app.OperationNotAllowedException;

public class MainScreen {
	
	PlanningApp planningApp;
	ProjectsScreen projectsScreen;
	CreateProjectScreen createProjectScreen;

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
		planningApp.createProject("Test Project", true);
		planningApp.createProject("Test Project external", false);
		planningApp.addEmployee(new Employee("John Doe", "JD"));
		planningApp.addEmployee(new Employee("Jane Doe", "JD"));
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
		
		projectsScreen = new ProjectsScreen(planningApp, this);
		createProjectScreen = new CreateProjectScreen(planningApp, projectsScreen);
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
