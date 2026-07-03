package de.htw_berlin.mytodolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private final FootworkSessionRepository repository;
    private final SessionHistoryRepository historyRepository;

    public Controller(FootworkSessionRepository repository, SessionHistoryRepository historyRepository) {
        this.repository = repository;
        this.historyRepository = historyRepository;
    }

    @GetMapping("/sessions")
    public List<FootworkSession> getAllSessions() {
        return repository.findAll();
    }

    @PostMapping("/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public FootworkSession createSession(@RequestBody FootworkSession session) {
        return repository.save(session);
    }

    @PutMapping("/sessions/{id}")
    public FootworkSession updateSession(@PathVariable Long id, @RequestBody FootworkSession updated) {
        FootworkSession session = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        session.setName(updated.getName());
        session.setIntervals(updated.getIntervals());
        return repository.save(session);
    }

    @DeleteMapping("/sessions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    @GetMapping("/api/history")
    public List<SessionHistory> getHistory(@RequestParam String email) {
        return historyRepository.findByOwnerEmailOrderByIdDesc(email);
    }

    @PostMapping("/api/history")
    @ResponseStatus(HttpStatus.CREATED)
    public SessionHistory saveHistory(@RequestBody SessionHistory entry) {
        return historyRepository.save(entry);
    }
}