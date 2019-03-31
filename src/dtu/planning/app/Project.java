package dtu.planning.app;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private String name;
	private boolean isProjectInternal;
	private Employee projectLeader;
	private List<Activity> activities;
	private int number;
	// start and end dates are missing

	public Project(String name, boolean isProjectInternal, int projectCount) {
		this.name = name;
		this.isProjectInternal = isProjectInternal;
		this.number = projectCount;
//		this.number = generateNumber();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isProjectInternal() {
		return this.isProjectInternal;
	}
	
	public Employee getProjectLeader() {
		return projectLeader;
	}
	
	public void setProjectLeader(Employee e) {
		this.projectLeader = e;
	}
	
	public int getProjectNumber() {
		return number;
	}
	
//	private int generateNumber() {
//		// todo: include dato
//	}

}
