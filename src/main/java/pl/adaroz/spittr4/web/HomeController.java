package pl.adaroz.spittr4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adaroz.spittr4.Spitter;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

    {
        System.out.println("From static");
    }

    @RequestMapping(method = GET)
    public String home() {
        return "/home";
    }

    @RequestMapping(value = "/check", method = GET)
    public String check() {
        return "/check";
    }

    @RequestMapping(value = "/json", method = GET)
    public Spitter json() {
        return new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
    }

}
