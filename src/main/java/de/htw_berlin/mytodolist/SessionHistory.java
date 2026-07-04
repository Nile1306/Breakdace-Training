package de.htw_berlin.mytodolist;

import jakarta.persistence.*;

@Entity
public class SessionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerEmail;
    private String type;
    private String sessionName;
    private Integer intervalCount;
    private String date;
    private String time;
    private boolean completed;

    public SessionHistory() {}

    public Long getId() { return id; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    public String getType() {  return type; }

    public void setType(String type) { this.type = type; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public Integer getIntervalCount() { return intervalCount; }
    public void setIntervalCount(Integer intervalCount) { this.intervalCount = intervalCount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
