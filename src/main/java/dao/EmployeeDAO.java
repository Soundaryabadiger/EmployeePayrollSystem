package dao;

import java.sql.ResultSet;

import model.Attendance;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Payroll;
import model.Leave;
import model.Employee;
import util.DBConnection;
import java.util.Map;
import java.util.HashMap;

public class EmployeeDAO {

    public boolean addEmployee(Employee emp) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            System.out.println("Connection Success");

            String query = "INSERT INTO employees(name,email,department,salary,joining_date,photo)\r\n"
            		+ "VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());
            ps.setDate(5, java.sql.Date.valueOf(emp.getJoiningDate()));
            ps.setString(6, emp.getPhoto());

            System.out.println("Query Executing...");
            int rows = ps.executeUpdate();

            System.out.println("Rows Inserted: " + rows);

            if(rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            System.out.println("ERROR IS:");
            e.printStackTrace();
        }

        return status;
    }
    
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM employees";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Employee emp = new Employee();

                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setJoiningDate(rs.getString("joining_date"));
                emp.setPhoto(rs.getString("photo"));

                list.add(emp);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    public boolean deleteEmployee(int empId) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM employees WHERE emp_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, empId);

            int rows = ps.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    
    public Employee getEmployeeById(int empId) {

        Employee emp = null;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT * FROM employees WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                emp = new Employee();

                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setJoiningDate(rs.getString("joining_date"));
                emp.setPhoto(rs.getString("photo"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return emp;
    }
    
    public boolean updateEmployee(Employee emp) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "UPDATE employees SET name=?, email=?, department=?, salary=?, joining_date=? WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());

            ps.setDate(5,
            java.sql.Date.valueOf(emp.getJoiningDate()));

            ps.setInt(6, emp.getEmpId());

            int rows = ps.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public List<Employee> searchEmployee(String keyword) {

        List<Employee> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT * FROM employees WHERE name LIKE ? OR department LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Employee emp = new Employee();

                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setJoiningDate(rs.getString("joining_date"));

                list.add(emp);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    public List<Attendance> getAttendanceByEmpId(int empId) {

        List<Attendance> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT * FROM attendance WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Attendance att = new Attendance();

                att.setAttendanceId(
                        rs.getInt("attendance_id"));

                att.setEmpId(
                        rs.getInt("emp_id"));

                att.setAttendanceDate(
                        rs.getString("attendance_date"));

                att.setStatus(
                        rs.getString("status"));

                list.add(att);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    public boolean markAttendance(Attendance attendance) {

        boolean status = false;

        try {

            Connection con =
            DBConnection.getConnection();

            /* CHECK IF ALREADY MARKED */

            String checkQuery =
            "SELECT * FROM attendance WHERE emp_id=? AND attendance_date=CURDATE()";

            PreparedStatement checkPs =
            con.prepareStatement(checkQuery);

            checkPs.setInt(1,
            attendance.getEmpId());

            ResultSet rs =
            checkPs.executeQuery();

            /* IF ALREADY EXISTS */

            if(rs.next()) {

                return false;
            }

            /* INSERT ATTENDANCE */

            String query =
            "INSERT INTO attendance(emp_id, attendance_date, status) VALUES (?, CURDATE(), ?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setInt(1,
            attendance.getEmpId());

            ps.setString(2,
            attendance.getStatus());

            int rows =
            ps.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public List<Payroll> getPayrollByEmpId(int empId) {

        List<Payroll> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT * FROM payroll WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(
                        rs.getInt("payroll_id"));

                payroll.setEmpId(
                        rs.getInt("emp_id"));

                payroll.setMonth(
                        rs.getString("month"));

                payroll.setPresentDays(
                        rs.getInt("present_days"));

                payroll.setAbsentDays(
                        rs.getInt("absent_days"));

                payroll.setTotalSalary(
                        rs.getDouble("total_salary"));

                list.add(payroll);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    public int getTotalEmployees() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT COUNT(*) FROM employees";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                count = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }
    
    public int getPresentToday() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT COUNT(*) FROM attendance WHERE attendance_date=CURDATE() AND status='Present'";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                count = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }
    
    public int getAbsentToday() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT COUNT(*) FROM attendance WHERE attendance_date=CURDATE() AND status='Absent'";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                count = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }
    
    public double getTotalPayroll() {

        double total = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "SELECT SUM(total_salary) FROM payroll";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                total = rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }
    
    public boolean generatePayroll(int empId) {

        boolean status = false;

        try {

            Connection con =
            DBConnection.getConnection();

            /* CHECK IF PAYROLL ALREADY GENERATED */

            String checkQuery =
            "SELECT * FROM payroll WHERE emp_id=? AND month=MONTHNAME(CURDATE())";

            PreparedStatement checkPs =
            con.prepareStatement(checkQuery);

            checkPs.setInt(1, empId);

            ResultSet checkRs =
            checkPs.executeQuery();

            if(checkRs.next()) {

                return false;
            }

            /* GET EMPLOYEE SALARY */

            String empQuery =
            "SELECT salary FROM employees WHERE emp_id=?";

            PreparedStatement empPs =
            con.prepareStatement(empQuery);

            empPs.setInt(1, empId);

            ResultSet empRs =
            empPs.executeQuery();

            double salary = 0;

            if(empRs.next()) {

                salary =
                empRs.getDouble("salary");
            }

            /* COUNT PRESENT DAYS */

            String presentQuery =
            "SELECT COUNT(*) FROM attendance WHERE emp_id=? AND status='Present'";

            PreparedStatement presentPs =
            con.prepareStatement(presentQuery);

            presentPs.setInt(1, empId);

            ResultSet presentRs =
            presentPs.executeQuery();

            int presentDays = 0;

            if(presentRs.next()) {

                presentDays =
                presentRs.getInt(1);
            }

            /* COUNT ABSENT DAYS */

            String absentQuery =
            "SELECT COUNT(*) FROM attendance WHERE emp_id=? AND status='Absent'";

            PreparedStatement absentPs =
            con.prepareStatement(absentQuery);

            absentPs.setInt(1, empId);

            ResultSet absentRs =
            absentPs.executeQuery();

            int absentDays = 0;

            if(absentRs.next()) {

                absentDays =
                absentRs.getInt(1);
            }

            /* CALCULATE SALARY */

            double perDaySalary =
            salary / 30;

            double totalSalary =
            perDaySalary * presentDays;

            /* INSERT PAYROLL */

            String payrollQuery =
            "INSERT INTO payroll(emp_id, month, present_days, absent_days, total_salary) VALUES (?, MONTHNAME(CURDATE()), ?, ?, ?)";

            PreparedStatement payrollPs =
            con.prepareStatement(payrollQuery);

            payrollPs.setInt(1, empId);

            payrollPs.setInt(2, presentDays);

            payrollPs.setInt(3, absentDays);

            payrollPs.setDouble(4, totalSalary);

            int rows =
            payrollPs.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean applyLeave(int empId,
    		String leaveDate,
    		String reason) {

    		    boolean status = false;

    		    try {

    		        Connection con =
    		        DBConnection.getConnection();

    		        String query =
    		        "INSERT INTO leave_requests(emp_id, leave_date, reason) VALUES(?,?,?)";

    		        PreparedStatement ps =
    		        con.prepareStatement(query);

    		        ps.setInt(1, empId);

    		        ps.setDate(2,
    		        java.sql.Date.valueOf(leaveDate));

    		        ps.setString(3, reason);

    		        int rows =
    		        ps.executeUpdate();

    		        if(rows > 0) {

    		            status = true;
    		        }

    		    } catch(Exception e) {

    		        e.printStackTrace();
    		    }

    		    return status;
    		}
    
    public List<Leave> getLeaveHistory(int empId) {

        List<Leave> list =
        new ArrayList<>();

        try {

            Connection con =
            DBConnection.getConnection();

            String query =
            "SELECT * FROM leave_requests WHERE emp_id=?";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setInt(1, empId);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next()) {

                Leave leave =
                new Leave();

                leave.setLeaveId(
                rs.getInt("leave_id"));

                leave.setEmpId(
                rs.getInt("emp_id"));

                leave.setLeaveDate(
                rs.getString("leave_date"));

                leave.setReason(
                rs.getString("reason"));

                leave.setStatus(
                rs.getString("status"));

                list.add(leave);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    public Map<String, Integer>
    getDepartmentCounts() {

        Map<String, Integer> map =
        new HashMap<>();

        try {

            Connection con =
            DBConnection.getConnection();

            String query =
            "SELECT department, COUNT(*) AS total FROM employees GROUP BY department";

            PreparedStatement ps =
            con.prepareStatement(query);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next()) {

                String dept =
                rs.getString("department");

                int count =
                rs.getInt("total");

                map.put(dept, count);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return map;
    }
}