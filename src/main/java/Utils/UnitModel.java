package Utils;

import Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitModel {
    public static List<User> loadUserData(){
        List<User> listUsers = new ArrayList<>();
        User user = new User("Rose",12);
        User user1 = new User("Lisa",15);
        User user2 = new User("Jennie",16);
        User user3 = new User("John",12);
        User user4 = new User("Van A",15);
        User user5 = new User("Name 1",16);
        User user6 = new User("Name 2",13);
        User user7 = new User("Name 3",18);
        listUsers.add(user);
        listUsers.add(user1);
        listUsers.add(user2);
        listUsers.add(user3);
        listUsers.add(user4);
        listUsers.add(user5);
        listUsers.add(user6);
        listUsers.add(user7);
        return listUsers;
    }
}
