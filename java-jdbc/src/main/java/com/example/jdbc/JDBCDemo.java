package com.example.jdbc;
import java.sql.*; //подключение к библиотекам java.sql;
import java.sql.SQLException;

public class JDBCDemo {
    /*
     * Подключение к драйверу jdbc.
     * затем идёт подключение к самой базе данных.
     * для начала выбираем jdbc,затем конкретный файл с которым мы будем работать, потом хост бд и уже нашу бд
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL="jdbc:mysql://localhost/employee";

    /*
     * Нужен пароль и логин для аутентификации данных.
     */
    static final String USER="root";
    static final String PASSWORD="";
    /*
     * Главный блок программы описывающий основные операции, подключение к бд и методы обработок исключений
     */
    public static void main(String[]args)
                  throws ClassNotFoundException, SQLException {

        Connection connection = null;//устанавливаем связь
           Statement statement = null;//устанавливаем выражение для sql.

           //
           System.out.println("Registration of JDBC driver");
           Class.forName("com.mysql.cj.jdbc.Driver");

           System.out.println("Creating database connection...");
           connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
           System.out.println("Executing...");
           statement=connection.createStatement();

           String sql;
           sql = "SELECT * FROM employee";

           ResultSet resultSet = statement.executeQuery(sql);
           System.out.println("\nEmployee:");
           while(resultSet.next())
           {
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String surname = resultSet.getString("surname");
               int salary=resultSet.getInt("salary");

               System.out.println("\n=====================");

               System.out.println("id:\t" + id);
               System.out.println("name:\t" + name);
               System.out.println("surname:\t" + surname);
               System.out.println("Salary:\t" + salary+".$");
           }

           System.out.println("Closing connection");
           resultSet.close();
           statement.close();
           connection.close();

    }
}
