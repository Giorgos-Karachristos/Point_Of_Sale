package entities;

public class Role {
    private String name;
    private String posAccess;
    private String countEmployees;

    public Role(String name, String posAccess, String countEmployees) {
        this.name = name;
        this.posAccess = posAccess;
        this.countEmployees = countEmployees;
    }

    public String getPosAccess() {
        return posAccess;
    }

    public String getCountEmployees() {
        return countEmployees;
    }

    public String getName() {
        return name;
    }
}
