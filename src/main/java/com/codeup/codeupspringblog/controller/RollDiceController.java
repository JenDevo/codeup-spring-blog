package com.codeup.codeupspringblog.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String userOptions(){
        return "roll-dice";
    }


    @GetMapping("/roll-dice/{n}")
    public String guess(@PathVariable int n, Model model) {

        int randomNum = (int)Math.floor(Math.random() * (6 -1 + 1) + 1);
        String comparison = "";

        if(randomNum == n){
            comparison = "Yay! It's a match!";
        }else {
            comparison = "Whoops! Not a match, please try again.";
        }

        model.addAttribute("userGuess", "You picked the number: " + n);
        model.addAttribute("roll", "The dice roll is: " + randomNum);
        model.addAttribute("comparison", comparison);
            return "roll-dice";
        }
    }
