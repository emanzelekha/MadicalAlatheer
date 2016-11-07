package com.example.ok.madicalalatheer.AddGoal.control;

/**
 * Created by ok on 06/11/2016.
 */

public class ControlAddGoal {

    private String serial1;
    private String goal;
    private String to;
    private boolean active;
    private boolean buttons;

    public ControlAddGoal(String serial1, String goal, String to, boolean active, boolean buttons) {
        this.serial1 = serial1;
        this.goal = goal;
        this.to = to;
        this.active = active;
        this.buttons = buttons;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isButtons() {
        return buttons;
    }

    public void setButtons(boolean active) {
        this.buttons = buttons;
    }
}
