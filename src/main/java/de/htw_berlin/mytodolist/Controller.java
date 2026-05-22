package de.htw_berlin.mytodolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private final FootworkSessionRepository repository;

    public Controller(FootworkSessionRepository repository) {
        this.repository = repository;
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
}
