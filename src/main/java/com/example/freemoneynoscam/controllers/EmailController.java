package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.model.Email;
import com.example.freemoneynoscam.repositories.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class EmailController {
    private EmailRepository er = new EmailRepository();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/test")
    public String test(WebRequest dataFromForm) {
        boolean result = er.insertEmailIntoDB(dataFromForm.getParameter("email"));
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

    @GetMapping("/email")
    public String getEmail(Model m) {
        String email = er.fetchSingleEmail();
        m.addAttribute("email", email);
        return "singleEmail";
    }

    @GetMapping("/all-emails")
    public String getAllEmails(Model model) {
        List<Email> emails = er.fetchAllEmails();
        model.addAttribute("emails", emails);
        return "allEmails";
    }
}
