package de.htw_berlin.mytodolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {

    private FootworkSessionRepository repository;
    private SessionHistoryRepository historyRepository;
    private TodoRepository todoRepository;
    private Controller controller;

    @BeforeEach
    void setUp() {
        repository = mock(FootworkSessionRepository.class);
        historyRepository = mock(SessionHistoryRepository.class);
        todoRepository = mock(TodoRepository.class);
        controller = new Controller(repository, historyRepository, todoRepository);
    }

    @Test
    void getAllSessionsReturnsWhatRepoGives() {
        FootworkSession session = new FootworkSession();
        session.setName("Tuesday Grind");
        when(repository.findAll()).thenReturn(List.of(session));

        List<FootworkSession> result = controller.getAllSessions();

        assertEquals(1, result.size());
        assertEquals("Tuesday Grind", result.get(0).getName());
    }

    @Test
    void createSessionJustSavesIt() {
        FootworkSession session = new FootworkSession();
        session.setName("New Session");
        when(repository.save(session)).thenReturn(session);

        FootworkSession saved = controller.createSession(session);

        assertEquals("New Session", saved.getName());
        verify(repository).save(session);
    }

    @Test
    void updateSessionThrows404WhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        FootworkSession updated = new FootworkSession();
        updated.setName("doesn't matter");

        assertThrows(ResponseStatusException.class, () -> controller.updateSession(99L, updated));
    }

    @Test
    void deleteSessionRemovesExistingOne() {
        when(repository.existsById(5L)).thenReturn(true);

        controller.deleteSession(5L);

        verify(repository).deleteById(5L);
    }

    @Test
    void deleteSessionThrowsWhenIdMissing() {
        when(repository.existsById(123L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> controller.deleteSession(123L));
        // just making sure we don't call delete when there's nothing to delete
        verify(repository, never()).deleteById(any());
    }
}
