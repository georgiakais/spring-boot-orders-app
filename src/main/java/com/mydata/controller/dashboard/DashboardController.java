package com.mydata.controller.dashboard;

import com.mydata.dto.DashboardData;
import com.mydata.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;


    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String getDashboard(Principal principal, Model model) {
        String username = principal.getName(); // Get the logged-in username

        DashboardData dashboardData = dashboardService.getDashboardData(username); // service

        // model
        model.addAttribute("username", username);
        model.addAttribute("dashboardData", dashboardData);
        return "dashboard"; // view
    }
}