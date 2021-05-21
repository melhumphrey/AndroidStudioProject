package com.example.connectwithfriends;

public class EventTime {

    public boolean pm;
    public String hour;
    public String minute;

    EventTime(){
        pm = true;
        hour = "1";
        minute = "00";
    }

    public void setPm(boolean pm) {
        this.pm = pm;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public boolean isPm() {
        return pm;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }
}
