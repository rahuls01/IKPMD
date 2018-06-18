package com.example.djsni.studievolg_1;

public class vak {

    String Module, Studiepunten, Omschrijving;
    int id;

    public vak() {

    }

    public vak(String Module, String Studiepunten, String Omschrijving , int id) {
        this.Module = Module;
        this.Studiepunten = Studiepunten;

        this.Omschrijving = Omschrijving;
        this.id = id ;

    };

    public String getName() {
        return this.Module;
    }

    public int getid() {
        return this.id;
    }

    public String getec() {
        return this.Studiepunten;
    }

    public String getdescription() {
        return this.Omschrijving;
}


    public String toString() {
        return "Module: " + this.Module + "\nOmschrijving : " + this.Omschrijving + "\nStudiepunten : " + this.Studiepunten  ;
    }



}