package be.ehb.javaenterprise.lab3.exercise2.repository;


import be.ehb.javaenterprise.lab3.exercise2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findUsersByEmailContaining(String keyword);

}
