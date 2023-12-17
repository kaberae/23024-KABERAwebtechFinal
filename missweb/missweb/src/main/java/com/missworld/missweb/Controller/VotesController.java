package com.missworld.missweb.Controller;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Services.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VotesController {
    private VotesService votesService;

    @Autowired
    public VotesController(VotesService votesService) {
        this.votesService = votesService;
    }

    @GetMapping("/dashBoard/{contestant_id}/vote")
    public String onVoting(@PathVariable("contestant_id") long contestant_id, @RequestParam("missid") long missid, RedirectAttributes redirectAttributes){
        Users user = new Users();
        user.setUserId(missid);
        Contestants cont = new Contestants();
        cont.setContestant_id(contestant_id);
        String  results = votesService.saveVotes(cont,user);
        redirectAttributes.addAttribute("results",results);
        return"redirect:/dashBoard?missid="+missid;
    }


}
