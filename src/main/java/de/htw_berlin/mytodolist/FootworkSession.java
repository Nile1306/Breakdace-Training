package de.htw_berlin.mytodolist;

public class FootworkSession {
    private int workTime;
    private int restTime;
    private int round;


    public FootworkSession (int workTime, int restTime, int  round){
    this.workTime = workTime;
    this.restTime = restTime;
    this.round = round;
}

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public int getWorkTime() {
        return workTime;
    }
    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

}
