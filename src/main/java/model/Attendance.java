-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package model;

public class Attendance {

    private int attendanceId;
    private int empId;
    private String attendanceDate;
    private String status;

    public int getAttendanceId() {

        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {

        this.attendanceId = attendanceId;
    }

    public int getEmpId() {

        return empId;
    }

    public void setEmpId(int empId) {

        this.empId = empId;
    }

    public String getAttendanceDate() {

        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {

        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
