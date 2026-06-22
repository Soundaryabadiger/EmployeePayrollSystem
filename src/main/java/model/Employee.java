-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package model;

public class Employee {

    private int empId;
    private String name;
    private String email;
    private String department;
    private double salary;
    private String joiningDate;
    private String photo;
    
    public Employee() {

    }
    
    public Employee(int empId, String name, String email,
            String department, double salary, String joiningDate) {

        this.empId = empId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Getter and Setter Methods

    public void setEmpId(int empId) {
        this.empId = empId;
    } 
    
    public int getEmpId() {
        return empId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getDepartment() {
        return department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
    
    public String getJoiningDate() {
        return joiningDate;
    }
    
    public String getPhoto() {

        return photo;
    }

    public void setPhoto(String photo) {

        this.photo = photo;
    }

}
