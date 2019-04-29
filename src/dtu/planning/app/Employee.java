package dtu.planning.app;

public class Employee {

	private String name; 
	private String initials;
	
	public Employee(String name, String initials) {
		this.name = name; 
		this.initials = initials;
	}
	
	public String getName() {
		return name; 
	}
	
	public String getInitials() {
		return initials; 
	}
	
	public boolean match(String searchText) {
		searchText = searchText.toLowerCase();
		String name = this.name.toLowerCase();
		String initials = this.initials.toLowerCase();
		return name.contains(searchText) ||
				initials.contains(searchText);
	}
	
	public String toString() {
		return this.name + "  (" + this.initials + ")";
	}
	
}
