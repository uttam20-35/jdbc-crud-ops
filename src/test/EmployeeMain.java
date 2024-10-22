package test;

import impl.EmployeeImpl;

public class EmployeeMain {
    public static void main(String[] args) {

        EmployeeImpl employee =new EmployeeImpl();

        employee.getAllEmployees();
        employee.addEmployee();
        employee.getEmployeeById();
        employee.deleteEmployee();
        //both methods required for update in table
        employee.preloadData();
        employee.updateEmployeeById();
    }
}
