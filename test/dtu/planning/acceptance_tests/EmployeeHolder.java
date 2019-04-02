package dtu.planning.acceptance_tests;

import dtu.planning.app.Employee;

public class EmployeeHolder {
	
	public class ProjectHolder {
		
		private Employee employee;
		
		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
	}

}
