package be.ehb.javaenterprise.lab3.exercise2.controller;

import be.ehb.javaenterprise.lab3.exercise2.error.PostNotFoundException;
import be.ehb.javaenterprise.lab3.exercise2.model.Post;
import be.ehb.javaenterprise.lab3.exercise2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping({"", "/"})
    public Iterable<Post> findAll () {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @GetMapping("/search/email/{keyword}")
    public Iterable<Post> findByUserEmail(@PathVariable String keyword) {
        return postRepository.findPostsByUserEmailContaining(keyword);
    }

    @PostMapping("")
    public Post create(@RequestBody Post post) {
        // We don't want to overwrite an existing post.
        post.setId(null);
        return postRepository.save(post);
    }

    @PutMapping("")
    public Post update(@RequestBody Post post) {
        if (! postRepository.existsById(post.getId()) )
            throw new PostNotFoundException(post.getId());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (! postRepository.existsById(id) )
            throw new PostNotFoundException(id);
        postRepository.deleteById(id);
    }

}
