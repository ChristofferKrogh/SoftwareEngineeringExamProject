package dtu.planning.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class MainScreen {

    PlanningApp planningApp;
    ProjectsScreen projectsScreen;
    CreateProjectScreen createProjectScreen;
    CreateActivitiesScreen createActivitiesScreen;
    CreateEmployeeScreen createEmployeeScreen;
    EditProjectScreen editProjectScreen;
    EditActivitiesScreen editActivitiesScreen;
    RegularActivitiesScreen regularActivitiesScreen;
    CreateRegularActivityScreen createRegularActivityScreen;
    ReportTimeScreen reportTimeScreen;
    CorrectReportedTimeScreen correctReportedTimeScreen;
    GetDailyScreen getDailyScreen;
    GenerateReportScreen generateReportScreen;
    CreateActivitiesScreenCopy createActivitiesScreenCopy;
    private int firstYear = 2000;
    private int lastYear = 2040;

    private JFrame frame;
    private JFrame consoleFrame;
    private JPanel panelMenu;
    private JLabel lblConsoleMessage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainScreen screen = new MainScreen();
                    screen.frame.setVisible(true);
                    screen.consoleFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * 
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
        planningApp.addActivity(p1.getProjectNumber(), "Buy lawnmower", startWeek, endWeek, 4,
                p1.getProjectLeader().getInitials());
        planningApp.addActivity(p1.getProjectNumber(), "Cut grass", startWeek, endWeek, 4,
                p1.getProjectLeader().getInitials());
        planningApp.assignEmployee(p1.getProjectNumber(), "Buy lawnmower", p1.getProjectLeader().getInitials(), "JaD");
        planningApp.assignEmployee(p1.getProjectNumber(), "Buy lawnmower", p1.getProjectLeader().getInitials(), "CK");
        planningApp.assignEmployee(p1.getProjectNumber(), "Cut grass", p1.getProjectLeader().getInitials(), "JD");
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // ---------- Console ------------
        consoleFrame = new JFrame();
        consoleFrame.setBounds(0, 510, 404, 200);
        consoleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consoleFrame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel panelConsole = new JPanel();
        consoleFrame.getContentPane().add(panelConsole, "name_160236068959177"); // -6 til -7
        panelConsole.setLayout(null);
        panelConsole.setBorder(BorderFactory.createTitledBorder("Status"));
        lblConsoleMessage = new JLabel("Important messages will appear in this window");
        lblConsoleMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsoleMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblConsoleMessage.setBounds(5, 5, 395, 170);
        panelConsole.add(lblConsoleMessage);
        // -------------------------------

        frame = new JFrame();
        frame.setBounds(0, 0, 404, 486);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        panelMenu = new JPanel();
        frame.getContentPane().add(panelMenu, "name_160236068959176");
        panelMenu.setLayout(null);
        panelMenu.setBorder(BorderFactory.createTitledBorder("Main Menu"));

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

        JLabel lblTime = new JLabel("<html><b>Time</b></html>");
        lblTime.setBounds(185, 155, 100, 30);
        panelMenu.add(lblTime);

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
        btnCorrectTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                correctReportedTimeScreen.setVisible(true);
            }

        });
        btnCorrectTime.setBounds(104, 238, 193, 29);
        panelMenu.add(btnCorrectTime);

        JButton btnGetTime = new JButton("Get Daily Reported Time");
        btnGetTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                getDailyScreen.setVisible(true);
            }
        });
        btnGetTime.setBounds(104, 286, 193, 29);
        panelMenu.add(btnGetTime);

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                createEmployeeScreen.setVisible(true);
            }
        });
        btnAddEmployee.setBounds(104, 380, 193, 29);
        panelMenu.add(btnAddEmployee);

        projectsScreen = new ProjectsScreen(planningApp, this);
        createProjectScreen = new CreateProjectScreen(planningApp, projectsScreen);
        createActivitiesScreen = new CreateActivitiesScreen(planningApp, projectsScreen);
        editProjectScreen = new EditProjectScreen(planningApp, projectsScreen);
        editActivitiesScreen = new EditActivitiesScreen(planningApp, editProjectScreen);
        regularActivitiesScreen = new RegularActivitiesScreen(planningApp, this, firstYear, lastYear);
        createRegularActivityScreen = new CreateRegularActivityScreen(planningApp, regularActivitiesScreen, firstYear,
                lastYear);
        reportTimeScreen = new ReportTimeScreen(planningApp, this);
        correctReportedTimeScreen = new CorrectReportedTimeScreen(planningApp, this);
        getDailyScreen = new GetDailyScreen(planningApp, this);
        generateReportScreen = new GenerateReportScreen(planningApp, projectsScreen);
        createActivitiesScreenCopy = new CreateActivitiesScreenCopy(planningApp, editActivitiesScreen);

        createEmployeeScreen = new CreateEmployeeScreen(planningApp, this);
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

    public void setConsoleMessage(String message) {
        lblConsoleMessage.setText(message);
    }

}
