package com.example.notengeneer;

public class Event {

    private String Name, Description, Type, Position, StartDate, FinishDate, Manager, Participants;

    public String getName(){
        return this.Name;
    }

    public String getDescription(){
        return this.Description;
    }

    public String getType(){
        return this.Type;
    }

    public String getPosition() {
        return Position;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getFinishDate() {
        return FinishDate;
    }

    public String getManager() {
        return Manager;
    }

    public String getParticipants() {
        return Participants;
    }


    public void setName(String name) {
        this.Name = name;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setFinishDate(String finishDate) {
        FinishDate = finishDate;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public void setParticipants(String participants) {
        Participants = participants;
    }
}
