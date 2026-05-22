package de.htw_berlin.mytodolist;

import jakarta.persistence.Embeddable;

@Embeddable
public class Interval {
    private String type; // "WORK" or "REST"
    private int durationMinutes;

    public Interval() {}

    public Interval(String type, int durationMinutes) {
        this.type = type;
        this.durationMinutes = durationMinutes;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
}
