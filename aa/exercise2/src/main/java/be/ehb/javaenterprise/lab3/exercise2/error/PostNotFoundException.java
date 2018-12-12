package be.ehb.javaenterprise.lab3.exercise2.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("The post with id " + id + " is not found.");
    }

}
