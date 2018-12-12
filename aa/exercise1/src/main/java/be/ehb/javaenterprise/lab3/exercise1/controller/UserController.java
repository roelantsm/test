package be.ehb.javaenterprise.lab3.exercise1.controller;

import be.ehb.javaenterprise.lab3.exercise1.error.UserNotFoundException;
import be.ehb.javaenterprise.lab3.exercise1.model.User;
import be.ehb.javaenterprise.lab3.exercise1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping({"", "/"})
    public Iterable<User> findAll () {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/search/email/{keyword}")
    public Iterable<User> findByEmail(@PathVariable String keyword) {
        return userRepository.findUsersByEmailContaining(keyword);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        // We don't want to overwrite an existing user.
        user.setId(null);
        return userRepository.save(user);
    }

    @PutMapping("")
    public User update(@RequestBody User user) {
        if (! userRepository.existsById(user.getId()) )
            throw new UserNotFoundException(user.getId());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (! userRepository.existsById(id) )
            throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }

}
