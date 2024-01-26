package mk.finki.ukim.mk.lab.web.controler;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final AuthService authService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, AuthService authService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.authService = authService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Integer numDelBalloons = balloonService.getNumDeletedBalloons();
        model.addAttribute("numDeletedBalloons", numDelBalloons);
        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";

    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String desc,
                              @RequestParam Long manufacturer,
                              @RequestParam Long id) {
        try {
            this.balloonService.save(id, name, desc, manufacturer);
        } catch (ManufacturerNotFoundException e) {
            return "redirect:/balloons?error=" + e.getMessage();
        }
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        try {
            this.balloonService.deleteById(id);
        } catch (BalloonNotFoundException exception) {
            return "redirect:/balloons?error=" + exception.getMessage();
        }
        balloonService.updateNumDeletedBalloons();
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        try {
            Balloon balloon = this.balloonService.findById(id);
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
        } catch (BalloonNotFoundException e) {
            return "redirect:/balloons?error=" + e.getMessage();
        }
        return "add-balloon";

    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }

    @GetMapping("/orders")
    public String getUserOrders() {
        return "userOrders";
    }

    @PostMapping
    public String searchBalloons(@RequestParam String keyword, Model model) {
        Integer numDelBalloons = balloonService.getNumDeletedBalloons();
        model.addAttribute("numDeletedBalloons", numDelBalloons);
        List<Balloon> balloons = this.balloonService.searchByNameOrDescription(keyword);
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

}

