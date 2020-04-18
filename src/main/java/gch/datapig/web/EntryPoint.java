package gch.datapig.web;


import gch.datapig.model.Player;
import gch.datapig.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class EntryPoint {

    private PlayerService service;

    @Autowired
    public EntryPoint(PlayerService service) {

        this.service = service;
    }

    @RequestMapping
    public String getAllEmployees(Model model) {
        List<Player> list = service.getAllEmployees();
        model.addAttribute("players", list);
        return "index";
    }
}
