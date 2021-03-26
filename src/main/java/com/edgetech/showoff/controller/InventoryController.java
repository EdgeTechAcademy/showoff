package com.edgetech.showoff.controller;

import com.edgetech.showoff.model.Inventory;
import com.edgetech.showoff.model.User;
import com.edgetech.showoff.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/inv")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    //  RequestMethod.GET is the default. It is optional
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("inventory", inventoryService.listAllInventorys());
        return "home";
    }
    //  RequestMethod.GET is the default. It is optional
    @RequestMapping(value = "/carList", method = RequestMethod.GET)
    public String car(Model model) {
        model.addAttribute("inventory", inventoryService.listAllInventorys());
        return "carList";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("inventory", inventoryService.listAllInventorys());
        model.addAttribute("name", "Edge Tech Academy");
        model.addAttribute("address1", "2241 S. Watson Rd.");
        model.addAttribute("address2", "Suite 181");
        model.addAttribute("city", "Arlington");
        model.addAttribute("state", "TX");
        model.addAttribute("zip", "76060");
        return "contact";
    }


    @RequestMapping(value = { "/show" }, method = RequestMethod.GET)
    public ModelAndView contactUs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("inventory", inventoryService.listAllInventorys());
        modelAndView.setViewName("carList"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        //@RequestParam String thing,
        Inventory car = inventoryService.getInventoryById(id);
        if (car != null) {
            model.addAttribute("inventory", car);
            return "carDetails";
        } else {
            model.addAttribute("message", "The Car Id: " + id + " was not found in the database");
            return "404";           // show the not found page
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("inventory", inventoryService.getInventoryById(id));
        return "carEdit";
    }

    @RequestMapping("/new")
    public String newInventory(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "carEdit";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return "redirect:/inv/carList";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return "redirect:/inv/carList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String carMake, @RequestParam String carModel,
                         @RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice,
                         @RequestParam Integer startYear,  @RequestParam Integer endYear, Model model) {

        List<Inventory> cars = (List<Inventory>) inventoryService.listAllInventorys();
        List<Inventory> finalList = cars;

        if (carMake.length() > 0) {
            finalList.removeIf(car -> ( ! car.getMake().equals(carMake)));
        }
        if (carModel.length() > 0) {
            finalList.removeIf(car -> ( ! car.getModel().equals(carModel)));
        }

        if (startYear != null) {
            finalList.removeIf(car -> ( car.getYear() < startYear));
        }

        if (endYear != null && endYear > startYear) {
            finalList.removeIf(car -> ( car.getYear() > endYear));
        }


        if (minPrice != null) {
            finalList.removeIf(car -> ( car.getPrice().compareTo(minPrice) == -1));
        }

        if (maxPrice != null && (minPrice == null || maxPrice.compareTo(minPrice)== 1)) {
            finalList.removeIf(car -> ( car.getPrice().compareTo(maxPrice) ==1 ));
        }

        model.addAttribute("inventory", finalList);
        return "carList";
    }
}