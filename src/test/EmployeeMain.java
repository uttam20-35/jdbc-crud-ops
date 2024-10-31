package test;

import impl.EmployeeImpl;

import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeImpl employee = new EmployeeImpl();

        while (true) {
            System.out.println("1. List of the Employees");
            System.out.println("2. Add new Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Update Employee");
            System.out.println("0. For Exit");
            System.out.println("Select above Numbers for the operations :");
            int number = sc.nextInt();

            switch (number) {
                case 1:
                    employee.getAllEmployees();
                    break;
                case 2:
                    employee.addEmployee();
                    break;
                case 3:
                    employee.getEmployeeById();
                    break;
                case 4:
                    employee.deleteEmployee();
                    break;
                case 5:
                    employee.preloadData();
                    employee.updateEmployeeById();
                    break;
                case 0:
                    System.out.println("Exiting");
                    sc.close();
                    return;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
}
