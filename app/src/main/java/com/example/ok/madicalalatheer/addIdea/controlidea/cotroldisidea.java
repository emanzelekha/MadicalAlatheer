package com.example.ok.madicalalatheer.addIdea.controlidea;

import org.json.JSONObject;

/**
 * Created by ahmed on 11/7/2016.
 */

public class cotroldisidea  {
    private JSONObject Data;
    private String Ideaid;
    private String serial1;
    private String goal;
    private String suggest;
    private String job;
    private boolean active;
    private boolean buttons;


    public cotroldisidea(JSONObject Data,String Ideaid,String serial1, String goal, String suggest,String job, boolean active, boolean buttons) {
        this.Data=Data;
        this.Ideaid = Ideaid;
        this.serial1 = serial1;
        this.goal = goal;
        this.suggest = suggest;
        this.job = job;
        this.active = active;
        this.buttons = buttons;
    }
    public JSONObject getData(){return Data;}
    public void setData(JSONObject Data){
        this.Data=Data;
    }
    public String getIdeaid() {
        return Ideaid;
    }

    public void setIdeaid(String goalid) {
        this.Ideaid = goalid;
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

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public void setButtons(boolean buttons) {
        this.buttons = buttons;
    }
}
