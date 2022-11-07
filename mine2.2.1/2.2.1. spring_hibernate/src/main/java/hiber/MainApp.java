package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Alex", "One", "one@gmail.com");
      User user2 = new User("Dima", "Two", "two@gmail.com");
      User user3 = new User("Mike", "Three", "three@gmail.com");
      User user4 = new User("Olya", "Four", "four@gmail.com");
      User user5 = new User("Mikhail", "Five", "five@gmail.com");

      Car bmw = new Car("Bmw", 5);
      Car volvo = new Car("Volvo", 31);
      Car nissan = new Car("Nissan", 4);
      Car porshe = new Car("Porshe", 3);
      Car fiat = new Car("Fiat", 1);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.add(user1.setCar(bmw).setUser(user1));
      userService.add(user2.setCar(volvo).setUser(user2));
      userService.add(user3.setCar(nissan).setUser(user3));
      userService.add(user4.setCar(porshe).setUser(user4));
      userService.add(user5.setCar(fiat).setUser(user5));

      System.out.println(userService.getUserByCar("Volvo", 31));
      context.close();
   }
}
