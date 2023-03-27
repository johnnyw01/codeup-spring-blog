package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    // Dependency injection start
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }
    // Dependency injection end



    @GetMapping("/posts")
    public String index(Model model) {
        // create an ArrayList of two post objects
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post("First post title", "This is the first post"));
//        posts.add(new Post("Second post title", "This is the second post"));

        // add the posts to the model
//        model.addAttribute("posts", postDao.findAll());

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String view(@PathVariable String id, Model model) {
        // create a new post object
        // convert the id parameter to a long value
        long postId = Long.parseLong(id);
//        Post post = new Post(postId, "Post title", "This is post ");
        Post post = postDao.getPostById(postId);
        // add the post to the model
        model.addAttribute("post", post);

        // return the view
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createBlogForm() {
        System.out.println("This is working");
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createBlog(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        // validate the title and body parameters
        if (title == null || title.isEmpty() || body == null || body.isEmpty()) {
            return "redirect:/posts/create";
        }
        System.out.println("This is working");

            User user = userDao.findUserByUsername("test_user");
            Post post = new Post();

            post.setTitle(title);
            post.setBody(body);
            post.setUser(user);
            postDao.save(post);
            return "redirect:/posts";

    }
}
