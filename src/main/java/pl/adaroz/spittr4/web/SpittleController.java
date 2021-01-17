package pl.adaroz.spittr4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.adaroz.spittr4.data.SpittleRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private final SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = GET)
    public String spittles(
            @RequestParam(defaultValue = "1000") long max,
            @RequestParam(defaultValue = "5") int count,
            Model model) {
        model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
        return "/spittleList";
    }

    @RequestMapping(value = "/{spittleId}", method = GET)
    public String spittle(@PathVariable long spittleId, Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "/spittle";
    }

}
