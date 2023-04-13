package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {


//        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();
        System.out.println();
        userDao.saveUser("Name", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println();
        userDao.getAllUsers();
        System.out.println();
        userDao.removeUserById(1);
        System.out.println();
        userDao.getAllUsers();
        System.out.println();
        userDao.cleanUsersTable();
        System.out.println();
        userDao.dropUsersTable();
    }
}
