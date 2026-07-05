package de.htw_berlin.mytodolist;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SessionHistoryRepository extends JpaRepository<SessionHistory, Long> {
    List<SessionHistory> findByOwnerEmailOrderByIdDesc(String ownerEmail);
}
