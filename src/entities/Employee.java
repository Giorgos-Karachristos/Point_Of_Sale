package entities;

public class Employee {
    private String employeeID;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String role;
    private String ssn;
    private String id;
    private String hireDate;
    private String username;
    private String password;

    public Employee(String employeeID, String name, String surname, String phone, String email, String role, String ssn, String id, String hireDate, String username) {
        this.employeeID = employeeID;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.id = id;
        this.hireDate = hireDate;
        this.username = username;
    }

    public Employee(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getSsn() {
        return ssn;
    }

    public String getId() {
        return id;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
