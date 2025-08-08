
public class Employee {

    private String name;

    private String phone;

    private int salary;

    private String role;

    private String dob;

    private String branch;

    private String status;

    // Constructors

    public Employee() {}

    public Employee(String name, String phone, int salary, String role, String dob, String branch, String status) {

        this.name = name;

        this.phone = phone;

        this.salary = salary;

        this.role = role;

        this.dob = dob;

        this.branch = branch;

        this.status = status;

    }

    // Getters and Setters

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getBranch() { return branch; }

    public void setBranch(String branch) { this.branch = branch; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", role='" + role + '\'' +
                ", dob='" + dob + '\'' +
                ", branch='" + branch + '\'' +
                //", status='" + status + '\'' +
                '}';
    }
}