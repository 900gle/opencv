package com.etoos.opencv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String main(){
        return "index";
    }

    @GetMapping("buttons")
    public String buttos(){
        return "buttons";
    }

    @GetMapping("cards")
    public String cards(){
        return "cards";
    }

    @GetMapping("tables")
    public String tables(){
        return "tables";
    }

    @GetMapping("charts")
    public String charts(){
        return "charts";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("utilities-color")
    public String utilitiesColor(){
        return "utilities-color";
    }

    @GetMapping("utilities-border")
    public String utilitiesBorder(){
        return "utilities-border";
    }

    @GetMapping("utilities-animation")
    public String utilitiesAnimation(){
        return "utilities-animation";
    }

    @GetMapping("utilities-other")
    public String utilitiesOther(){
        return "utilities-other";
    }




}
