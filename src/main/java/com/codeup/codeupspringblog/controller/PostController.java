package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    //  (Line 19 & 21-23) Dependency Injection from Posts Interface
    private final PostRepository postDao;

    //  Inject UserRepository into PostController
    private final UserRepository userDao;

    //  Dependency Injection - EmailService into Post Controller
    private final EmailService emailService;


    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String indexPage(Model model) {

//        Created a new array list.
        List<Post> allPosts = postDao.findAll();
        // Passing the arraylist to the view
        // (AKA ability to call > "th:each='post : ${allPosts}'"
        // in index.html);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable Long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String viewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createForm(@ModelAttribute Post post) {
//        User user = userDao.findById(2L).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostTest(@PathVariable Long id, Model model){
        Post post = postDao.findById(id).get();
        model.addAttribute("posts", post);
        return "redirect:/posts";
    }

    @PostMapping("posts/{id}/edit")
    public String updateEdit(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts/{id}";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam Long id) {
        postDao.deleteById(id);

        return "redirect:/posts";
    }

    @GetMapping("/posts/email/{id}")
    public String sendEmailAboutPost(@PathVariable Long id) {
        Post post = postDao.getReferenceById(id);

        emailService.prepareAndSend(post, "Here's the information about the post you created", post.getTitle() + " : " + post.getBody() + "Posted by: " + post.getUser().getUsername());

        return "redirect:/posts";
    }
}
