import java.util.List;

public class Employee {
    private String employeeId;
    private String name;
    private String department;
    private double basicSalary;
    private int joiningYear;
    private double rating;
    private List<String> skills;

    public Employee() {}

    public Employee(String employeeId, String name, String department, 
                    double basicSalary, int joiningYear, double rating, 
                    List<String> skills) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
        this.joiningYear = joiningYear;
        this.rating = rating;
        this.skills = skills;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public int getJoiningYear() { return joiningYear; }
    public void setJoiningYear(int joiningYear) { this.joiningYear = joiningYear; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    @Override
    public String toString() {
        return "Employee {" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", basicSalary=" + basicSalary +
                ", joiningYear=" + joiningYear +
                ", rating=" + rating +
                ", skills=" + skills +
                '}';
    }
}

