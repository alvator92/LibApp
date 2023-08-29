package ru.test.libapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surName", required = false) String surname,
                            Model model) {
//        System.out.println("Hello : " + name + ", " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "operation", required = false) String operation,
                             @RequestParam(value = "first", required = false) int first,
                             @RequestParam(value = "second", required = false) int second,
                             Model model) {
        String result = String.valueOf(first + second);
        if (operation.equals("sum")) {
            model.addAttribute("message", "result of " + operation + " = " + result);

        }
        return "/first/calculator";
    }
}
