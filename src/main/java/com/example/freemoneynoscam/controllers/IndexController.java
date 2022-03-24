package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.EmailDBManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
public class IndexController {

    private EmailDBManager edb = new EmailDBManager();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/test")
    public String test(WebRequest dataFromForm) {
        boolean result = edb.insertEmailIntoDB(dataFromForm.getParameter("email"));
        System.out.println(dataFromForm.getParameter("email"));

        if (result) {
            return "redirect:/succes";
        } else {
            return "redirect:/failure";
        }
    }

    @GetMapping("/succes")
    public String succes() {
        return "succes";
    }

    @GetMapping("/failure")
    public String failure() {
        return "failure";
    }
}
