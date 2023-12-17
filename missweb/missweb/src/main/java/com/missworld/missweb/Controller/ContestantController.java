package com.missworld.missweb.Controller;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Services.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
public class ContestantController {

    private ContestantService contestantService;

    @Autowired
    public ContestantController(ContestantService contestantService) {
        this.contestantService = contestantService;
    }

    @GetMapping("/dashBoard/apply")
    public String userApply(@RequestParam("missid") long missid,Model model){
        Contestants person = new Contestants();
        model.addAttribute("person",person);
        model.addAttribute("missid",missid);
        return "U_application";
    }

    @PostMapping("/dashBoard/apply")
    public String saveApplication(@RequestParam("missid") long missid,Contestants person){
        contestantService.addContestant(person);
        return "redirect:/dashBoard?missid="+missid;
    }

    @GetMapping("/dashBoard")
    public String userDashBoard(@RequestParam("missid") long missid,Model model){
        List<Contestants> persons = contestantService.findAllContestants();
        String results;
        Boolean applied;
        Users user = new Users();
        user.setUserId(missid);
        results = contestantService.findIfVoted(user);
        applied = contestantService.hasApplied(user);
        model.addAttribute("applied",applied);
        model.addAttribute("results",results);
        model.addAttribute("missid",missid);
        model.addAttribute("persons",persons);
        return "U_dashboard";
    }

//    @GetMapping("/adminDashboard")
//    public String adminDashboard(@RequestParam("missid") long missid,Model model){
//        List<Contestants> persons = contestantService.findAllContestants();
//        model.addAttribute("missid",missid);
//        model.addAttribute("persons",persons);
//        return "Dashboard";
//    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(@RequestParam("missid") long missid, @RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(page - 1, pageSize); // adjust the page number

        Page<Contestants> contestantsPage = contestantService.findAllContestants(pageable); // get a page of contestants

        List<Contestants> contestantsList = contestantsPage.getContent(); // get the list of contestants from the page
        int totalPages = contestantsPage.getTotalPages(); // get the total number of pages

        model.addAttribute("missid", missid);
        model.addAttribute("persons", contestantsList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "Dashboard";
    }


    @GetMapping("/adminDashboard/{contestant_id}/delete")
    public String eliminateContestant(@RequestParam(value="missid") long missid,@PathVariable("contestant_id") long contestant_id){
        contestantService.deleteById(contestant_id);
        return "redirect:/adminDashboard?missid="+missid;
    }

    @GetMapping("/dashboard/search")
    public String searchCont(@RequestParam(value ="country") String country,@RequestParam(value="missid") long missid, Model model){
        List<Contestants> persons = contestantService.searchCont(country);
        model.addAttribute("persons",persons);
        model.addAttribute("missid",missid);
        return"U_dashboard";
    }

//    @GetMapping("/adminDashboard/search")
//    public String adminSearchCont(@RequestParam(value ="country") String country, Model model){
//        List<Contestants> persons = contestantService.searchCont(country);
//        model.addAttribute("persons",persons);
//        return "Dashboard";
//    }

    @GetMapping("/adminDashboard/search")
    public String adminSearchCont(@RequestParam(value ="country") String country,
                                  @RequestParam("missid") long missid,
                                  @RequestParam(defaultValue = "1") int page,
                                  Model model) {
        int pageSize = 5; // set the number of items to display per page

        Pageable pageable = PageRequest.of(page - 1, pageSize); // adjust the page number to 0-based index

        Page<Contestants> contestantsPage = contestantService.searchContWithPagination(country, pageable); // perform search with pagination

        List<Contestants> contestantsList = contestantsPage.getContent(); // get the list of contestants from the page
        int totalPages = contestantsPage.getTotalPages(); // get the total number of pages

        model.addAttribute("missid", missid);
        model.addAttribute("persons", contestantsList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "Dashboard";
    }

}
