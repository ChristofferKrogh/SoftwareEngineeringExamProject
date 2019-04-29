package dtu.planning.gui;

import dtu.planning.app.Project;

public class ProjectPrinter {
	
	private Project project;

	public ProjectPrinter(Project project) {
		this.project = project;
	}

	public String printDetail() {
		StringBuffer b = new StringBuffer();
		b.append("<html>");
		b.append(String.format("<b>Name:</b>     %s<br>", project.getName()));
		b.append(String.format("<b>Project number:</b>    %s<br>", Integer.toString(project.getProjectNumber())));
		try {
			b.append(String.format("<b>Project leader:</b>    %s<br>", project.getProjectLeader().getName()));
		} catch (Exception e) {
			b.append(String.format("<b>Project leader:</b>    No project leader<br>"));
		}
		b.append(String.format("<b>Internal or external project:</b>    %s<br>", project.isProjectInternal()? "Internal" : "External"));
		b.append(String.format("<b>Start date:</b> %s<br>", project.getStartDateString()));
		b.append(String.format("<b>End date:</b> %s<br></html>", project.getEndDateString()));
		return b.toString();
	}
	
	public String print() {
			return project.getName();
	}
}
