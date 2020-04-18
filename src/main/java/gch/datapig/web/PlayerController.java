package gch.datapig.web;

import java.util.List;

import gch.datapig.model.Player;
import gch.datapig.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gch.datapig.exception.RecordNotFoundException;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @RequestMapping(path = {"/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        Player entity = service.getEmployeeById(id);
        model.addAttribute("player", entity);
        return "player/add-edit-player";
    }


    @RequestMapping(path = {"/edit"})
    public String newEmployee(Model model) throws RecordNotFoundException {
        model.addAttribute("player", new Player());
        return "player/add-edit-player";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Player employee) {
        service.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}
