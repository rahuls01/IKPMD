package com.example.djsni.studievolg_1;

public class vak {

    String name, ec, description;
    int id;

    public vak() {

    }

    public vak(String name, String ec, String description, int id) {
        this.name = this.name;
        this.ec = ec;
        this.description = this.description;
    };

    public String getName() {
        return this.name;
    }

    public int getid() {
        return this.id;
    }

    public String getec() {
        return this.ec;
    }

    public String getdescription() {
        return this.description;
    }

    public String toString() {
        return "Name : " + this.name + "\nDescription : " + this.description + "\n EC : " + this.ec + "\n ID : " + this.id;
    }



}