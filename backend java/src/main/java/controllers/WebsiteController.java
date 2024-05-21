package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.WebsiteService;
import models.RequestHistory;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @PostMapping("/website/summarize")
    public String summarizeWebsite(@RequestBody String url) {
        return websiteService.summarizeWebsite(url);
    }

    @GetMapping("/history")
    public List<RequestHistory> getHistory() {
        return websiteService.getHistory();
    }
}
