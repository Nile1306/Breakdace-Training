package de.htw_berlin.mytodolist;

import jakarta.persistence.*;

@Entity
public class SessionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerEmail;
    private String sessionName;
    private int intervalCount;
    private String date;
    private String time;
    private boolean completed;

    public SessionHistory() {}

    public Long getId() { return id; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public int getIntervalCount() { return intervalCount; }
    public void setIntervalCount(int intervalCount) { this.intervalCount = intervalCount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
