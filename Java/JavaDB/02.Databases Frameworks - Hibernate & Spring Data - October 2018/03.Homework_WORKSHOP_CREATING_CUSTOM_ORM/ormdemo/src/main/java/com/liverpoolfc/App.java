package com.liverpoolfc;

import com.liverpoolfc.db.EntityDbContext;
import com.liverpoolfc.db.base.DbContext;
import com.liverpoolfc.entities.Department;
import com.liverpoolfc.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni_simple";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection = getConnection();

        DbContext<User> usersDbContext =
                getDbContext(connection, User.class);

        DbContext<Department> departmentDbContext =
                getDbContext(connection, Department.class);

        User user = new User("Sadio", "Mane");
        user.setAge(25);
        user.setUcn("10101010101010");

        User Liverpool = new User("Steven", "Gerrard");
        Liverpool.setAge(38);
        Liverpool.setUcn("8888888888");

        usersDbContext.persist(user);
        usersDbContext.persist(Liverpool);

//       usersDbContext.delete("first_name LIKE ('S%')");

//        User rush = usersDbContext.findById(1);
//        rush.setLastName("Ian");
//
//        usersDbContext.persist(rush);
//
//        usersDbContext.find()
//                .forEach(System.out::println);
//        User user = usersDbContext.findFirst();
//        System.out.println(user);
//        user.setLastName("Only Levski");S
//
//        usersDbContext.persist(user);
//                DbContext<Department> departmentDbContext
//                = getDbContext(connection, Department.class);
//        departmentDbContext.find()
//                .forEach(System.out::println);

        connection.close();
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) throws SQLException {
        return new EntityDbContext<>(connection, klass);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONNECTION_STRING,
                "root",
                "8101"
        );
    }
}
