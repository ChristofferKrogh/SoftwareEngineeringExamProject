package dtu.planning.app;

public class Report {

    Project project;

    public Report(Project project) {
        this.project = project;
    }

    // This is a bad way to have dependencies. Its a circular dependency and should be changed for
    // "static" data upon generation instead of report just being a "wrapper" of project functions.
    // Also it adds both days and hours with no regard to whether or not its hours or not
    public double getReportedTimeForActivity(String activityName) throws ActivityNotFoundException {
        double reportedTime = 0;
        for (TimeRegistration t : this.project.getActivityByName(activityName).getTimeRegistrations()) {
            reportedTime += t.getAmountOfTime();
        }
        return reportedTime;
    }

    public double getEstimatedTimeForActivity(String activityName) throws ActivityNotFoundException {
        return this.project.getActivityByName(activityName).getExpectedAmountOfHours();
    }

}
