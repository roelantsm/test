package be.ehb.javaenterprise.lab3.exercise2.controller;

import be.ehb.javaenterprise.lab3.exercise2.model.User;
import be.ehb.javaenterprise.lab3.exercise2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/db")
public class DBController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/init")
    public Iterable<User> initDB () {
        // cleanup
        userRepository.deleteAll();

        // create users
        List<String> names = Arrays.asList("ann", "rita", "jules", "mahou", "fran", "frans", "rene", "roger", "lisa", "verde", "merel");
        List<String> emailProviders = Arrays.asList("gmail.com", "hotmail.com", "yahoo.com", "mail.com");
        names.stream()
                .forEach(name -> {
                    User u = new User();
                    u.setEmail( name
                            + "."
                            + names.get(new Random().nextInt(names.size()))
                            + "@"
                            + emailProviders.get(new Random().nextInt(emailProviders.size()))
                    );
                    userRepository.save(u);
                });
        // list users
        return userRepository.findAll();
    }
}
