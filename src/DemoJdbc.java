import java.sql.*;
import java.util.Scanner;

public class DemoJdbc  {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/demo";
        String username ="postgres";
        String password ="root";
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Employee Id :");
        int eid = sc.nextInt();
        System.out.println("Enter Employee Name :");
        String ename =sc.next();
        System.out.println("Enter Employee Age :");
        int age =sc.nextInt();
        /*int eid=104;
        String ename="Pranay";
        int age= 15;*/
//        String sql= "insert into employee values (103,'sahil',18)";
//        String sql = "insert into employee values ("+eid+",'"+ename+"',"+age+")";
        String insert_sql= "insert into employee values (?,?,?)";
//        String update_sql= "update employee set ename=? where eid=?";
//        String sql= "select *from employee";
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established");
//            PreparedStatement preparedStatement= connection.prepareStatement(update_sql);
//            preparedStatement.setString(1, ename);
//            preparedStatement.setInt(2,eid);
            PreparedStatement preparedStatement= connection.prepareStatement(insert_sql);
            preparedStatement.setInt(1,eid);
            preparedStatement.setString(2,ename);
            preparedStatement.setInt(3,age);
            preparedStatement.execute();
//            For Select Query only :
            /*Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("eid") + "-"
                        + resultSet.getString("ename")
                        + "-" + resultSet.getInt("age"));
            }*/
            connection.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}