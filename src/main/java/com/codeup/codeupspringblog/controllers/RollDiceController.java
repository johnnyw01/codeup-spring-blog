package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class RollDiceController {

//    @GetMapping("/rolldice")
//    public String diceGame() {
//        return "rolldice";
//    }
//
//    @PostMapping("/rolldice/{n}")
//    public String number(@RequestParam(name = "pickNumber") int pickNumber, Model model) {
//
//        int randNum = (int) (Math.random() * 5) + 1;
//
//
//        model.addAttribute("dieRoll", randNum);
//        model.addAttribute("userGuess", pickNumber);
//
//        return "rolldice";
//
//        }

    @GetMapping("/rolldice")
    public String diceGame() {
        return "rolldice";
    }

    @GetMapping("/rolldice/{n}")
    public String number (@PathVariable int n, Model model){
        int roll = ThreadLocalRandom.current().nextInt(1,6+1);
        model.addAttribute("guess", n);
        model.addAttribute("roll", roll);
        return "roll-guess";
    }


    }
