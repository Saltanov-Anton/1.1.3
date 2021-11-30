package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        User u = new User();
        //user.createUsersTable();
        //user.saveUser("Anton", "Saltanov", (byte) 31);
        //user.saveUser("Ivan", "Grozniy", (byte) 55);
        //user.saveUser("Keanu", "Reeves", (byte) 57);
        //user.saveUser("Bruce", "Lee", (byte) 32);
        //user.removeUserById(2);
        //System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
