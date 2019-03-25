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
	
//	private int generateNumber() {
//		// todo: include dato
//	}

}
