package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String index(Model model) {
        // create an ArrayList of two post objects
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("First post title", "This is the first post"));
        posts.add(new Post("Second post title", "This is the second post"));

        // add the posts to the model
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String view(@PathVariable int id, Model model) {
        // create a new post object
        Post post = new Post(1, "Post title", "This is post ");

        // add the post to the model
        model.addAttribute("post", post);

        // return the view
        return "posts/show";
//        return "This is post " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm() {
        return "This is the form for creating a new post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "A new post has been created";
    }
}


//In this code, we define a class called PostController that listens for HTTP GET and POST requests to four different routes. The @GetMapping and @PostMapping annotations specify the URL path and HTTP method for each route.
//
//For each route, we've defined a method that returns a string describing what the route does. We've used the @ResponseBody annotation on each method to indicate that the return value should be sent as the response to the client, rather than being interpreted as a view name by Spring Boot.
//
//We've also used the @PathVariable annotation on the view method to extract the value of the id variable from the URL path. This allows us to dynamically generate the response based on the requested post ID.
//
//Note that we've used the @Controller annotation on the class to indicate that this is a standard MVC controller, rather than a REST controller. This tells Spring Boot to use view templates to render the responses for each route, but since we're just returning strings in this example, we've used @ResponseBody to bypass the view rendering and send the strings directly to the client.