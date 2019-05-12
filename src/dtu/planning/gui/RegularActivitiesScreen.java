package dtu.planning.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import dtu.planning.app.Activity;
import dtu.planning.app.OperationNotAllowedException;

public class RegularActivitiesScreen {
	
	private MainScreen parentWindow;
	private CreateRegularActivityScreen createRegularActivityScreen;
	private PlanningApp planningApp;
	private JPanel panelRegular;
	private JTextField searchField;
	private JList<Activity> listSearchResult;
	private DefaultListModel<Activity> searchResults;
	private JLabel lblSearchResultDetail;
	private JButton btnBack;
	private JComboBox<Integer> startYearComboBox;
	private JComboBox<Integer> endYearComboBox;
	private JTextField startWeekField;
	private JTextField endWeekField;
	private JLabel lblEditDates;
	private boolean editDatesMode = false;
	private Activity regularActivity;
	private JPanel panelEditDates;
	private AbstractButton btnEditRegActivity;
	private int firstYear;
	private int lastYear;

	
	public RegularActivitiesScreen(PlanningApp planningApp, MainScreen parentWindow, int firstYear, int lastYear) {
		this.planningApp = planningApp;
		this.parentWindow = parentWindow;
		this.firstYear = firstYear;
		this.lastYear = lastYear;
		initialize();
	}

	private void initialize() {
		panelRegular = new JPanel();
		parentWindow.addPanel(panelRegular);
		panelRegular.setLayout(null);
		panelRegular.setBorder(BorderFactory.createTitledBorder(
                "Regular Activities"));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelRegular.add(btnBack);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchRegActivities();
			}
		});
		searchField.setBounds(138, 28, 130, 26);
		panelRegular.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchRegActivities();
			}
		});
		btnSearch.setBounds(148, 68, 117, 29);
		panelRegular.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		searchResults = new DefaultListModel<>();
		listSearchResult = new JList<Activity>(searchResults);
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting() == false) {

		            if (listSearchResult.getSelectedIndex() == -1) {
		            	lblSearchResultDetail.setText("");

		            } else {
		            	Activity selectedRegular = listSearchResult.getSelectedValue();
		            	StringBuffer b = new StringBuffer();
		        		b.append("<html>");
		        		b.append(String.format("<b>Name:</b>     %s<br>", selectedRegular.getName()));
		        		b.append("<b>Expected Start:</b> " + selectedRegular.getStartWeekString() + "<br>");
		        		b.append("<b>Expected End:</b>&nbsp; " + selectedRegular.getEndWeekString() + "<br>");
		        		b.append("<b>Assigned Employee:</b> " + selectedRegular.getAssignedEmployees().get(0).toString() + "<br></html>");
		        		
		            	lblSearchResultDetail.setText(b.toString());
		            }
		        }
			}
		});
		listSearchResult.setVisibleRowCount(4);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(21, 109, 361, 80);
		panelRegular.add(listScrollPane);
		
		JPanel panelSearchResult = new JPanel();
		panelSearchResult.setBounds(21, 200, 361, 90);
		panelRegular.add(panelSearchResult);
		panelSearchResult.setBorder(BorderFactory.createTitledBorder(
                "Detail"));
		panelSearchResult.setLayout(null);
		
		lblSearchResultDetail = new JLabel("");
		lblSearchResultDetail.setVerticalAlignment(SwingConstants.TOP);
		lblSearchResultDetail.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchResultDetail.setBounds(23, 19, 318, 85);
		panelSearchResult.add(lblSearchResultDetail);
		
		
		// --------------------- Edit Dates Panel --------------------
		panelEditDates = new JPanel();
		panelEditDates.setBounds(21, 295, 361, 80);
		panelRegular.add(panelEditDates);
		panelEditDates.setBorder(BorderFactory.createTitledBorder(
                "Edit Epected Start and End"));
		panelEditDates.setLayout(null);
		
		startWeekField = new JTextField();
		startWeekField.setBounds(165, 13, 35, 30);
		panelEditDates.add(startWeekField);
		
		endWeekField = new JTextField();
		endWeekField.setBounds(165, 45, 35, 30);
		panelEditDates.add(endWeekField);
		
		Integer[] comboBoxItems = new Integer[lastYear-firstYear];
		for (int i = 0; i < lastYear - firstYear; i++) {
			comboBoxItems[i] = firstYear + i;
		}
		startYearComboBox = new JComboBox<>(comboBoxItems);
		startYearComboBox.setBounds(225, 13, 85, 30);
		panelEditDates.add(startYearComboBox);
		endYearComboBox = new JComboBox<>(comboBoxItems);
		endYearComboBox.setBounds(225, 45, 85, 30);
		panelEditDates.add(endYearComboBox);
		
		lblEditDates = new JLabel();
		lblEditDates.setVerticalAlignment(SwingConstants.TOP);
		lblEditDates.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditDates.setBounds(23, 20, 318, 75);
		panelEditDates.add(lblEditDates);
		panelEditDates.setVisible(false);
		// -----------------------------------------------------------
		
		
		JButton btnCreateRegActivity = new JButton("Create Regular Activity");
		btnCreateRegActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				createRegularActivityScreen.setVisible(true);
			}
		});
		btnCreateRegActivity.setBounds(225, 385, 160, 50);
		panelRegular.add(btnCreateRegActivity);
		
		btnEditRegActivity = new JButton("Edit Expected Start and End");
		btnEditRegActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editDatesMode) {
					try {
						editDates();
						editDatesMode = false;
						panelEditDates.setVisible(false);
						clear();
						btnEditRegActivity.setText("Edit Expected Start and End");
					} catch (Exception e2) {
						setConsoleMessage(e2.getMessage());
					}
				} else {
					if (listSearchResult.getSelectedIndex() == -1) {
						setConsoleMessage("You need to select a regular activity");
					} else {
						regularActivity = listSearchResult.getSelectedValue();
						editDatesMode = true;
						setEditDatesPanel(listSearchResult.getSelectedValue());
						btnEditRegActivity.setText("Save Changes");
						panelEditDates.setVisible(true);
					}
				}
			}
		});
		btnEditRegActivity.setBounds(20, 385, 190, 50);
		panelRegular.add(btnEditRegActivity);
		
		createRegularActivityScreen = new CreateRegularActivityScreen(planningApp, this, firstYear, lastYear);
		
	}
	
	protected void searchRegActivities() {
		searchResults.clear();
		try {
			planningApp.searchForRegActivitiesByName(searchField.getText())
			.forEach((m) -> {searchResults.addElement(m);});
		} catch (OperationNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setVisible(boolean aFlag) {
		panelRegular.setVisible(aFlag);
	}
	
	public void clear() {
		searchField.setText("");
		searchResults.clear();
		panelEditDates.setVisible(false);
		editDatesMode = false;
		btnEditRegActivity.setText("Edit Expected Start and End");
	}
	
	private void setEditDatesPanel(Activity regularActivity) {
		StringBuffer editDates = new StringBuffer();
		editDates.append("<html><b>Expected Start:</b> week &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of <br><br>"); //&nbsp; is a space
		editDates.append("<b>Expected End:</b>&nbsp; week &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; of </html>");
		lblEditDates.setText(editDates.toString());
		
		startWeekField.setText(Integer.toString(regularActivity.getStartWeek().get(GregorianCalendar.WEEK_OF_YEAR)));
		startYearComboBox.setSelectedItem(regularActivity.getStartWeek().get(GregorianCalendar.YEAR));
		endWeekField.setText(Integer.toString(regularActivity.getEndWeek().get(GregorianCalendar.WEEK_OF_YEAR)));
		endYearComboBox.setSelectedItem(regularActivity.getEndWeek().get(GregorianCalendar.YEAR));
	}
	
	private void editDates() throws OperationNotAllowedException {
		int startWeek = Integer.parseInt(startWeekField.getText());
		int endWeek = Integer.parseInt(endWeekField.getText());
		int startYear = Integer.parseInt(startYearComboBox.getSelectedItem().toString());
		int endYear = Integer.parseInt(endYearComboBox.getSelectedItem().toString());
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
		end.setWeekDate(endYear, endWeek, GregorianCalendar.SATURDAY);
		if (end.before(start)) {
			throw new OperationNotAllowedException("End week must be after start week");
		} else if (end.after(new GregorianCalendar(lastYear - 1, 11, 31))) {
			throw new OperationNotAllowedException("The regular activity must end before " + lastYear);
		}
		planningApp.editStartWeekOfRegular(start, regularActivity.getName());
		planningApp.editEndWeekOfRegular(end, regularActivity.getName());
		
	}
	
	public void addPanel(JPanel panel) {
		parentWindow.addPanel(panel);
	}
	
	public void setConsoleMessage(String message) {
		parentWindow.setConsoleMessage(message);
	}

}
