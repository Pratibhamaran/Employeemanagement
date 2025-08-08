import java.util.*;
public class EMS {
    public static void main(String[] args) {
        allInOne();
    }
        public static void allInOne() {
            EmployeeDAO dao = new EmployeeDAO();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. Insert ");
                System.out.println("2. Select");
                System.out.println("3. Update ");
                System.out.println("4. Delete");
                System.out.println("5. add employee from csv");
                System.out.println("6. view all employee by csv");
                System.out.println("7. export employees to csv");
                System.out.println("8. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name1 = sc.next();
                        System.out.print("Enter Phone: ");
                        String phone1 = sc.next();
                        System.out.print("Enter Salary: ");
                        int salary1 = sc.nextInt();
                        System.out.print("Enter Role: ");
                        String role1 = sc.next();
                        System.out.print("Enter DOB (yyyy-mm-dd): ");
                        String dob1 = sc.next();
                        System.out.print("Enter Branch: ");
                        String branch1 = sc.next();
                        System.out.print("Enter Status: ");
                        String status1 = sc.next();
                        Employee emp1 = new Employee(name1, phone1, salary1, role1, dob1, branch1, status1);
                        dao.addEmployee(emp1);
                        break;
                    case 2:
                        dao.getAllEmployees();
                        break;
                    case 3:

                        System.out.print("Enter Name of Employee to Update: ");
                        String updateName = sc.next();

                        // Create temporary values for update (can be skipped)
                        System.out.println("Enter new values (type '-' to skip):");

                        System.out.print("New Phone: ");
                        String newPhone = sc.next();
                        newPhone = newPhone.equals("-") ? "" : newPhone;

                        System.out.print("New Salary: ");
                        String salaryInput = sc.next();
                        int newSalary = salaryInput.equals("-") ? 0 : Integer.parseInt(salaryInput);

                        System.out.print("New Role: ");
                        String newRole = sc.next();
                        newRole = newRole.equals("-") ? "" : newRole;

                        System.out.print("New DOB (yyyy-mm-dd): ");
                        String newDob = sc.next();
                        newDob = newDob.equals("-") ? "" : newDob;

                        System.out.print("New Branch: ");
                        String newBranch = sc.next();
                        newBranch = newBranch.equals("-") ? "" : newBranch;

                        System.out.print("New Status: ");
                        String newStatus = sc.next();
                        newStatus = newStatus.equals("-") ? "" : newStatus;

                        // Create new Employee object with updated fields
                        Employee updatedEmp = new Employee(updateName, newPhone, newSalary, newRole, newDob, newBranch, newStatus);

                        // Call the update method
                        dao.updateEmployeeByName(updatedEmp);
                        break;


                    case 4:
                        System.out.print("Enter name to delete: ");
                        String nameToDelete = sc.nextLine();
                        dao.deleteEmployeeByName(nameToDelete);
                        break;
                    case 5:
                        dao.importEmployeesFromCSV("src/employee.csv");
                        break;
                    case 6:
                        dao.viewAllEmployees();
                        break;
                    case 7:
                        dao.exportEmployeesToCSV("exported_employees.csv");
                        break;
                    case 8:
                        System.out.println("BYEBYE..");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }




        // Create
        //Employee emp1 = new Employee("Rajesh", "9876543210", 50000, "Developer", "1990-05-26", "Chennai", "Active");
        //dao.addEmployee(emp1);

        // Read
        /*for (Employee e : dao.getAllEmployees()) {
            System.out.println(e.getName() + " - " + e.getPhone() + " - " + e.getDob()+" - "+e.getRole()+" _ "+e.getSalary()+" - "+e.getStatus());
        }*/

        //Update
        //dao.updateEmployeeCity("Trichy", "Tiruchirappalli");
        /*elhi");
        dao.updateEmployeeByName(emp1);
        // Delete
        //dao.deleteEmployeeByName("Rajesh");

    }
}


Object = Row of Table

Employee ramesh = new Employee();
ramesh.name
ramesh.age
ramesh.salary


Table in Database
Employee
Name        age     salary
Ramesh      27      250000
Rajesh      20      100000000000

*/