import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class EmployeeDAO {

    // Create

    public void addEmployee(Employee emp) {

        String sql = "INSERT INTO employee (name, phone, salary, role, dob, branch, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();

             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());

            ps.setString(2, emp.getPhone());

            ps.setInt(3, emp.getSalary());

            ps.setString(4, emp.getRole());

            ps.setString(5, emp.getDob());

            ps.setString(6, emp.getBranch());

            ps.setString(7, emp.getStatus());

            ps.executeUpdate();

            System.out.println("✅ Employee added.");

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    // Read

    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM employee";

        try (Connection conn = DBConnection.getConnection();

             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Employee emp = new Employee(

                        rs.getString("name"),

                        rs.getString("phone"),

                        rs.getInt("salary"),

                        rs.getString("role"),

                        rs.getString("dob"),

                        rs.getString("branch"),

                        rs.getString("status")

                );

                list.add(emp);
                System.out.println(
                        "Name: " + emp.getName() +
                                ", Phone: " + emp.getPhone() +
                                ", Salary: " + emp.getSalary() +
                                ", Role: " + emp.getRole() +
                                ", DOB: " + emp.getDob() +
                                ", Branch: " + emp.getBranch() +
                                ", Status: " + emp.getStatus()
                );


            }
            System.out.println("✅ selected");

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);

        }

        return list;

    }

    // Update


    //updateEmployeeByName
    public void updateEmployeeByName(Employee emp) {
        StringBuilder sql = new StringBuilder("UPDATE employee SET ");
        List<Object> params = new ArrayList<>();



        // Add non-null/non-empty fields to query and params
        if (emp.getPhone() != null && !emp.getPhone().isEmpty()) {
            sql.append("phone=?, ");
            params.add(emp.getPhone());
        }
        if (emp.getSalary() != 0) {
            sql.append("salary=?, ");
            params.add(emp.getSalary());
        }
        if (emp.getRole() != null && !emp.getRole().isEmpty()) {
            sql.append("role=?, ");
            params.add(emp.getRole());
        }
        if (emp.getDob() != null && !emp.getDob().isEmpty()) {
            sql.append("dob=?, ");
            params.add(emp.getDob());
        }
        if (emp.getBranch() != null && !emp.getBranch().isEmpty()) {
            sql.append("branch=?, ");
            params.add(emp.getBranch());
        }
        if (emp.getStatus() != null && !emp.getStatus().isEmpty()) {
            sql.append("status=?, ");
            params.add(emp.getStatus());
        }



        // Trim trailing comma
        if (params.isEmpty()) {
            System.out.println("No fields to update.");
            return;
        }
        sql.setLength(sql.length() - 2); // remove last comma



        // WHERE clause (assuming name is unique identifier)
        sql.append(" WHERE name=?;");
        params.add(emp.getName());



        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {



            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }



            int rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);



        } catch (SQLException e) {
            e.printStackTrace();
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete

    public void deleteEmployeeByName(String name) {

        String sql = "DELETE FROM employee WHERE name=?";

        try (Connection conn = DBConnection.getConnection();

             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            int rows = ps.executeUpdate();

            System.out.println("✅ Rows deleted: " + rows);

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/empl", "prati_empl", "Abcd1234!");
    }
        public void importEmployeesFromCSV(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath));
                 Connection conn = getConnection()) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length != 7) continue;

                    String sql = "INSERT INTO employee (name, phone, salary, role, dob, branch, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, parts[0]);
                        ps.setString(2, parts[1]);
                        ps.setDouble(3, Double.parseDouble(parts[2]));
                        ps.setString(4, parts[3]);
                        ps.setString(5, parts[4]);
                        ps.setString(6, parts[5]);
                        ps.setString(7, parts[6]);
                        ps.executeUpdate();
                    }
                }
                System.out.println("Employees imported successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void viewAllEmployees() {
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("name") +
                            ", Phone: " + rs.getString("phone") +
                            ", Salary: " + rs.getDouble("salary") +
                            ", Role: " + rs.getString("role") +
                            ", DOB: " + rs.getString("dob") +
                            ", Branch: " + rs.getString("branch") +
                            ", Status: " + rs.getString("status"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void exportEmployeesToCSV(String outputPath) {
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

                while (rs.next()) {
                    String row = rs.getString("name") + "," +
                            rs.getString("phone") + "," +
                            rs.getDouble("salary") + "," +
                            rs.getString("role") + "," +
                            rs.getString("dob") + "," +
                            rs.getString("branch") + "," +
                            rs.getString("status");
                    writer.write(row);
                    writer.newLine();
                }

                System.out.println("Exported successfully to " + outputPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }







