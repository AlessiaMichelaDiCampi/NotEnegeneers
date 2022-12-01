package com.example.notengeneer;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String,Object> users = new HashMap<>();
    public String email;
    public String name;
    public String surname;
    public String state;
    public String CAP;
    public String address;
    public String address_number;

    public String date_birth;

    public User(String email,String name,String surname,String CAP,String address,String address_number,String dateBirth,String state){
        this.email=email;
        this.name=name;
        this.surname=surname;
        this.CAP=CAP;
        this.address=address;
        this.date_birth=dateBirth;
        this.state= "Italia";
        this.address_number=address_number;
        users.put("email",email);
        users.put("name",name);
        users.put("surname",surname);
        users.put("state",state);
        users.put("CAP",CAP);
        users.put("address",address);
        users.put("address_number",address_number);
        users.put("date_birth",dateBirth);
    }
    public User(User u) {
        this.email=u.email;
        this.name=u.name;
        this.surname=u.surname;
        this.CAP=u.CAP;
        this.address=u.address;
        this.date_birth=u.date_birth;
        this.state= "Italia";

    }
    public User() {

    }
    public void update(String email,String name,String surname,String CAP,String address,String address_number,String dateBirth,String state){
        this.email=email;
        this.name=name;
        this.surname=surname;
        this.CAP=CAP;
        this.address=address;
        this.date_birth=dateBirth;
        this.state= "Italia";
        this.address_number=address_number;
        users.put("email",email);
        users.put("name",name);
        users.put("surname",surname);
        users.put("state",state);
        users.put("CAP",CAP);
        users.put("address",address);
        users.put("address_number",address_number);
        users.put("date_birth",dateBirth);
    }

        public String getEmail(){
       return this.email;
    }
    public Map<String,Object> getMap(){
        saveMap();
        return this.users;
    }
    private void saveMap(){
        users.put("email",email);
        users.put("name",name);
        users.put("surname",surname);
        users.put("state",state);
        users.put("CAP",CAP);
        users.put("address",address);
        users.put("address_number",address_number);
        users.put("date_birth",date_birth);
    }
    public String getNamel(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getCAP(){
        return this.CAP;
    }
    public String getAddres(){
        return this.address;
    }
    public String getState(){
        return this.state;
    }
    public String getDateBirth(){
        return this.date_birth;
    }
    public int getMonth(){
        String mm=date_birth.substring(3,5);
        return Integer.parseInt(mm);
    }
    public int getYear(){

        String yyyy=date_birth.substring(6);
        return Integer.parseInt(yyyy);
    }
    public int getDay(){
        String dd=date_birth.substring(0,2);
        return  Integer.parseInt(dd);
    }


}
