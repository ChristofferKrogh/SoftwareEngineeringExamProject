package dtu.planning.app;

public class Employee {

	private String name; 
	private String initials; 
	private int employeeId; // We need a better way than initials to identify employees
	
	public Employee(String name, String initials) {
		this.name = name; 
		this.initials = initials;
		this.employeeId = -1; // magic number
	}
	
	// TODO: there are no tests for this constructor
	public Employee(String name, int employeeCount) {
		this.name = name;
		name = " " + name;
		this.initials = "";
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == ' ' &&  i+1 < name.length() && name.charAt(i+1) != ' ') {
				this.initials += name.charAt(i+1);
			}
		}
		this.employeeId = employeeCount;
	}
	
	public String getName() {
		return name; 
	}
	
	public String getInitials() {
		return initials; 
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public boolean match(String searchText) {
		return this.name.contains(searchText) || this.initials.contains(searchText);
	}
	
	public String toString() {
		return this.name + "  (" + this.initials + ") - " + this.employeeId;
	}
	
}
