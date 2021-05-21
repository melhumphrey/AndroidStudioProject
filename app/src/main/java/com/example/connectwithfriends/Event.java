package com.example.connectwithfriends;

public class Event {
    public String event_name;
    public String day;
    public String month;
    public String year;
    EventTime start_time;
    EventTime end_time;

    public Event(String name, String day, String month, String year, EventTime start_time, EventTime end_time) {
        this.event_name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Event(){
        start_time = new EventTime();
        end_time = new EventTime();
        this.event_name = "event";
        this.day = "1";
        this.month = "Jan";
        this.year = "2021";
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStartTime(EventTime time) { this.start_time = time; }

    public void setEndTime(EventTime time) { this.end_time = time; }

    public EventTime getStart_time() {
        return start_time;
    }

    public EventTime getEnd_time() {
        return end_time;
    }
}
