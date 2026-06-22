-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package model;

public class Leave {

    private int leaveId;

    private int empId;

    private String leaveDate;

    private String reason;

    private String status;

    public int getLeaveId() {

        return leaveId;
    }

    public void setLeaveId(int leaveId) {

        this.leaveId = leaveId;
    }

    public int getEmpId() {

        return empId;
    }

    public void setEmpId(int empId) {

        this.empId = empId;
    }

    public String getLeaveDate() {

        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {

        this.leaveDate = leaveDate;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
