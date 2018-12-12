package be.ehb.javaenterprise.lab3.exercise2.repository;


import be.ehb.javaenterprise.lab3.exercise2.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Iterable<Post> findPostsByUserEmailContaining(String keyword);

}
