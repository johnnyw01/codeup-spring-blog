package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {
    @GetMapping("/rolldice")
    public String diceGame() {
        return "rolldice";
    }

    @PostMapping("/rolldice")
    public String number(@RequestParam(name = "pickNumber") int pickNumber, Model model) {

        int randNum = (int) (Math.random() * 5) + 1;


        model.addAttribute("dieRoll", randNum);
        model.addAttribute("userGuess", pickNumber);

        return "rolldice";

        }


    }
