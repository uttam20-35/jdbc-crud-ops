package impl;

import dao.EmployeeDao;
import pojo.Employee;
import util.JDBCConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeImpl implements EmployeeDao {

    Employee employee =new Employee();
    JDBCConnection jdbcConnection =new JDBCConnection();
    Scanner scanner=new Scanner(System.in);
/*_______________________________________________________________________________________________________________*/

    //    Using collection MAP for updateEmployee for "preload/ first search then update" purpose
    Map<Long, Employee> employeeMap =new HashMap<>();

   public void preloadData(){
        String searchSql= "select *from employee";

        try {
            Statement statement =jdbcConnection.dbConnect().createStatement();
            ResultSet resultSet= statement.executeQuery(searchSql);
            if (resultSet.next()){
                do {
                    long eid =resultSet.getLong(1);
                    String ename=resultSet.getString(2);
                    int age= resultSet.getInt(3);
                    employeeMap.put(eid, new Employee(eid,ename,age));
                    }
                while (resultSet.next());
                jdbcConnection.dbConnect().close();
            }else System.out.println("Empty Database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/*_______________________________________________________________________________________________________________*/

    @Override
    public void addEmployee() {
        System.out.println("Enter eid :");
        employee.setEid(scanner.nextLong());

        System.out.println("Enter ename :");
        employee.setEname(scanner.next());

        System.out.println("Enter age :");
        employee.setAge(scanner.nextInt());

        String insertSql ="insert into employee values(?,?,?)";

        try {
            PreparedStatement preparedStatement = jdbcConnection.dbConnect().prepareStatement(insertSql);
            preparedStatement.setLong(1,employee.getEid());
            preparedStatement.setString(2,employee.getEname());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.execute();
            System.out.println("Values are inserted");
            jdbcConnection.dbConnect().close();
        } catch (SQLException e) {
            System.out.println("Error while inserting");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee() {

        System.out.println("Enter Employee ID for delete :");
        employee.setEid(scanner.nextLong());

//        String searchSql= "select *from employee where eid=?";
        String deleteSql= "delete from employee where eid =?";

//        try {
//            PreparedStatement preparedStatement =jdbcConnection.dbConnect().prepareStatement(searchSql);
//            preparedStatement.setInt(1,employee.getEid());
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()){
        try {
            PreparedStatement statement = jdbcConnection.dbConnect().prepareStatement(deleteSql);
            statement.setLong(1, employee.getEid());
            int affectedRows = statement.executeUpdate();

            if (affectedRows>0){
                System.out.println("Record deleted successfully");
            }
            else System.out.println("Id not found");
            jdbcConnection.dbConnect().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//            }
//            else System.out.println("Id is not present");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void getAllEmployees() {
        String selectSql="select *from employee";
        try {
            Statement statement=jdbcConnection.dbConnect().createStatement();
            ResultSet resultSet= statement.executeQuery(selectSql);

            if (resultSet.next()){
                do {

                    System.out.println(resultSet.getInt(1)+"-"
                        +resultSet.getString("ename")+"-"+
                        resultSet.getInt("age"));
                }
                while (resultSet.next());
                jdbcConnection.dbConnect().close();
            }
            else System.out.println("No Records are present in Emp Table");

        } catch (SQLException e) {
            System.out.println("Error while fetching records");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getEmployeeById() {
        System.out.println("Enter the id for finding employee :");
        employee.setEid(scanner.nextLong());

        String searchSql ="select *from employee where eid =?";

        try {
            PreparedStatement preparedStatement= jdbcConnection.dbConnect().prepareStatement(searchSql);
            preparedStatement.setLong(1,employee.getEid());

            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("Query fired with given ID");

                if (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "-"
                            + resultSet.getString(2) + "-"
                            + resultSet.getInt(3));
                }
                else System.out.println("No record found");
                jdbcConnection.dbConnect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateEmployeeById() {

        System.out.println("Enter id which want to update: ");
        Long id= scanner.nextLong();

        if (employeeMap.containsKey(id)){

            String updateSql= "update employee set ename =?, age=? where eid =?";

            System.out.println("Enter updated name: ");
            employee.setEname(scanner.next());

            System.out.println("Enter updated age: ");
            employee.setAge(scanner.nextInt());

            employee.setEid(id);

            try{
                PreparedStatement preparedStatement =jdbcConnection.dbConnect().prepareStatement(updateSql);

                preparedStatement.setString(1,employee.getEname());
                preparedStatement.setInt(2,employee.getAge());
                preparedStatement.setLong(3,employee.getEid());

                int affectedRows= preparedStatement.executeUpdate();
                if (affectedRows>0)
                    {
                        System.out.println("One record updated successfully");
                    }
                else System.out.println("Error while updating record");
                jdbcConnection.dbConnect().close();
                }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else System.out.println("Given id not present in Database");
    }
}
