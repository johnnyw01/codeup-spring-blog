package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    // Dependency injection start
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService ) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;

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
//         convert the id parameter to a long value
        long postId = Long.parseLong(id);
//        Post post = new Post(postId, "Post title", "This is post ");

        Post post = postDao.getPostById(postId);
        // add the post to the model
        model.addAttribute("post", post);

        // return the view
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createBlogForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createBlog(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @ModelAttribute Post newPost) {
        // validate the title and body parameters
        if (title == null || title.isEmpty() || body == null || body.isEmpty()) {
            return "redirect:/posts/create";
        }

        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(authenticatedUser == null){
            return "redirect:/users/login";
        }

        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setUser(authenticatedUser);

        Post post = postDao.save(newPost);

        emailService.prepareAndSend(post, newPost.getTitle(), newPost.getBody());
        return "redirect:/posts";

    }
        @GetMapping("/posts/{id}/edit")
        public String editBlogForm ( @PathVariable long id, Model model){
            Post editPost = postDao.getPostById(id);
//            model.addAttribute("id", id);
            model.addAttribute("editPost", editPost);
            return "posts/edit";
        }

        @PostMapping("/posts/{id}/edit")
        public String editPost ( @PathVariable long id, @RequestParam(name = "title") String editTitle, @RequestParam(name = "body") String editBody) {
            Post post = postDao.getPostById(id);
            post.setTitle(editTitle);
            post.setBody(editBody);
            postDao.save(post);

            return "redirect:/posts";
        }


    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.delete(postDao.getById(id));

        return "redirect:/posts";
    }

}
