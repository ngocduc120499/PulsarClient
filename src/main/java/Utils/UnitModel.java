package Utils;

import Config.AppConfig;
import Model.Business;
import Model.Ticket;
import Model.User;
import org.apache.pulsar.shade.org.eclipse.util.DateCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static List<Ticket> loadTicketData(){
        List<Ticket> listTickets = new ArrayList<>();
        Ticket ticket1 = new Ticket(1,"Ticket 1","This is a ticket 1",true,Date.valueOf("2020-04-12"),29);
        Ticket ticket2 = new Ticket(2,"Ticket 2","This is a ticket 2",true,Date.valueOf("2020-04-12"),26);
        Ticket ticket3 = new Ticket(3,"Ticket 3","This is a ticket 3",true,Date.valueOf("2020-04-12"),23);
        Ticket ticket4 = new Ticket(4,"Ticket 4","This is a ticket 4",true,Date.valueOf("2020-04-12"),22);
        Ticket ticket5 = new Ticket(5,"Ticket 5","This is a ticket 5",true,Date.valueOf("2020-04-12"),27);
        Ticket ticket6 = new Ticket(6,"Ticket 6","This is a ticket 6",true,Date.valueOf("2020-04-12"),27);
        listTickets.add(ticket1);
        listTickets.add(ticket2);
        listTickets.add(ticket3);
        listTickets.add(ticket4);
        listTickets.add(ticket5);
        listTickets.add(ticket6);
        return listTickets;
    }
//    public static List<Business> loadBusinessData() throws IOException {
//        return readData(System.getProperty("user.dir") + AppConfig.INPUT_FILE_PATH)
//                .map(UnitModel::strToBusiness)
//                .collect(Collectors.toList());
//    }
//
//    public static Stream<String> readData(String inputPath) throws IOException {
//        Path path = Paths.get(inputPath);
//        return Files.lines(path);
//    }
//
//    public static Business strToBusiness(String str) {
//        String[] tokens = str.split(",");
//        String description     = tokens[0];
//        String industry        = tokens[1];
//        Integer level          = Integer.parseInt(tokens[2]);
//        String line_code       = tokens[3];
//        Long value             = Long.parseLong(tokens[4]);
//        return new Business(description, industry, level, line_code,value );
//    }



}
