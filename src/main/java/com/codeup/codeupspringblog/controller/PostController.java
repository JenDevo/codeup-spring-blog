package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.dao.PostRepository;
import com.codeup.codeupspringblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {


//    (Line 19 & 21-23) Dependency Injection from Posts Interface
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String indexPage(Model model){

//        Created a new array list.
        List<Post> allPosts = postDao.findAll();

//        Two post objects are added to the array list called allPosts
        allPosts.add(new Post("This is a new post!!!", "I am saying lots of things relating to my post."));
        allPosts.add(new Post("This is the second post", "Here is where I am saying a lot of important information regarding my post."));

        // Passing the arraylist to the view
        // (AKA ability to call > "th:each='post : ${allPosts}'"
        // in index.html);
        model.addAttribute("allPosts", allPosts);
         return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable Long id, Model model){
        Post post = postDao.getReferenceById(id);


        model.addAttribute("post", post);
        model.addAttribute("individualPost", new Post("I am the Title of this Post!", "I am the body of the post, I will be the description or whatever the creator wants to be here!"));
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String viewPostForm(){
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createForm(@RequestParam String title, @RequestParam String body){

        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }
}
