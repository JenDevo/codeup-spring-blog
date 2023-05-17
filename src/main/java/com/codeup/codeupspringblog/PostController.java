package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String indexPage(){
        return "Posts index page";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id){
        return "Viewing an individual post";
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostForm(){
        return "Viewing the form to create a post";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String createForm(){
        return "New Post Created";
    }
}
