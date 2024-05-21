package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import models.RequestHistory;
import repositories.RequestHistoryRepository;

import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public String summarizeWebsite(String url) {
        // Call Python service for summarization
        String summary = restTemplate.postForObject("http://localhost:8000/summarize", url, String.class);
        saveRequestHistory(url);
        return summary;
    }

    public List<RequestHistory> getHistory() {
        return requestHistoryRepository.findAll();
    }

    private void saveRequestHistory(String url) {
        RequestHistory history = new RequestHistory();
        history.setUrl(url);
        requestHistoryRepository.save(history);
    }
}
