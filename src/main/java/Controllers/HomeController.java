package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import Model.LocationStats;
import Services.CovidDataPuller;

@Controller
public class HomeController {
	
	@Autowired
	CovidDataPuller covidDataPuller;
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats>stats=covidDataPuller.getStats();
		int latestReports = stats.stream().mapToInt(statts -> statts.getLatestReports()).sum();
		model.addAttribute("locationStat",stats);
		model.addAttribute("latestReports",latestReports);
		return "home";
		
	}
}
