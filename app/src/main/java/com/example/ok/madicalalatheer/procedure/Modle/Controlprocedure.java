package com.example.ok.madicalalatheer.procedure.Modle;

/**
 * Created by ok on 06/11/2016.
 */

public class Controlprocedure {

    private String serial1;
    private String goal;

    public Controlprocedure(String serial1, String goal) {
        this.serial1 = serial1;
        this.goal = goal;

    }

    public String getSerial1() {
        return serial1;
    }

    public void setSerial1(String serial1) {
        this.serial1 = serial1;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

}