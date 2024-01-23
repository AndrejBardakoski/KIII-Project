package mk.finki.ukim.mk.lab.web.controler;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/balloons/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("/add")
    public String saveManufacturer(@RequestParam String name,
                                   @RequestParam String country,
                                   @RequestParam String address) {
        try {
            this.manufacturerService.save(name, country, address);
        } catch (Exception e) {
            return "redirect:/balloons?error=" + e.getMessage();
        }
        return "redirect:/balloons/add-form";
    }

    @GetMapping("/add-form")
    public String getAddManufacturerPage(Model model) {
        return "add-manufacturer";
    }
}

