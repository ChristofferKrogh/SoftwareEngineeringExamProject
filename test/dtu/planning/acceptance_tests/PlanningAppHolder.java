package dtu.planning.acceptance_tests;
import dtu.planning.app.PlanningApp;

public class PlanningAppHolder {
	
	private PlanningApp planningApp;
	
	public PlanningApp getPlanningApp() {
		// Create planning if if none is defined
		if (this.planningApp == null) {
			this.planningApp = new PlanningApp();
		}
		return planningApp;
	}

	public void setPlanningApp(PlanningApp planningApp) {
		this.planningApp = planningApp;
	}
}
