package dtu.planning.app;

public class Employee {

    private String name;
    private String initials;

    public Employee(String name, String initials) {
        this.name = name;
        this.initials = initials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    public boolean match(String searchText) {
        searchText = searchText.toLowerCase();
        String name;
        // In case that no name has been set
        if (this.name != null) {
            name = this.name.toLowerCase();
        } else {
            name = "";
        }
        String initials = this.initials.toLowerCase();
        return name.contains(searchText) || initials.contains(searchText);
    }

    public String toString() {
        return this.name + " (" + this.initials + ")";
    }

    public boolean equals(Object obj) {
        Employee other = (Employee) obj;

        return this.initials.equals(other.initials);
    }

}
