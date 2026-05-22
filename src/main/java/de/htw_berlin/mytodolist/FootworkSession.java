package de.htw_berlin.mytodolist;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FootworkSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "footwork_session_intervals", joinColumns = @JoinColumn(name = "session_id"))
    @OrderColumn(name = "interval_order")
    private List<Interval> intervals = new ArrayList<>();

    public FootworkSession() {}

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Interval> getIntervals() { return intervals; }
    public void setIntervals(List<Interval> intervals) { this.intervals = intervals; }
}
