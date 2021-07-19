package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "/results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        System.out.println(searchType);
        System.out.println(searchTerm);
        ArrayList<Job> jobs = new ArrayList<>();
        if(searchType.equals("all")) {
            for(Job job : JobData.findAll()) {
                if(job.getName().contains(searchTerm)) {
                    jobs.add(job);
                }
                else if(job.getEmployer().getValue().contains(searchTerm)) {
                    jobs.add(job);
                }
                else if(job.getLocation().getValue().contains(searchTerm)) {
                    jobs.add(job);
                }
                else if(job.getPositionType().getValue().contains(searchTerm)) {
                    jobs.add(job);
                }
                else if(job.getCoreCompetency().getValue().contains(searchTerm)) {
                    jobs.add(job);
                }
            }
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }

}
