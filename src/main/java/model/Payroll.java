-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package model;

public class Payroll {

    private int payrollId;
    private int empId;
    private String month;
    private int presentDays;
    private int absentDays;
    private double totalSalary;

    public int getPayrollId() {

        return payrollId;
    }

    public void setPayrollId(int payrollId) {

        this.payrollId = payrollId;
    }

    public int getEmpId() {

        return empId;
    }

    public void setEmpId(int empId) {

        this.empId = empId;
    }

    public String getMonth() {

        return month;
    }

    public void setMonth(String month) {

        this.month = month;
    }

    public int getPresentDays() {

        return presentDays;
    }

    public void setPresentDays(int presentDays) {

        this.presentDays = presentDays;
    }

    public int getAbsentDays() {

        return absentDays;
    }

    public void setAbsentDays(int absentDays) {

        this.absentDays = absentDays;
    }

    public double getTotalSalary() {

        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {

        this.totalSalary = totalSalary;
    }
}
