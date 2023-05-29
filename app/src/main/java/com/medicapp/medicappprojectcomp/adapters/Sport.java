package com.medicapp.medicappprojectcomp.adapters;

public class Sport {

    private String Name;
    private String NativeName;
    private String Sport;
    private String SportPng;

    public Sport(String name, String nativeName, String sport, String sportPng) {
        this.Name = name;
        this.NativeName = nativeName;
        this.Sport = sport;
        this.SportPng = sportPng;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNativeName(String nativeName) {
        NativeName = nativeName;
    }


    public void setSport(String sport) {
        Sport = sport;
    }

    public void setSportPng(String sportPng) {
        SportPng = sportPng;
    }

    public String getName() {
        return Name;
    }

    public String getNativeName() {
        return NativeName;
    }

    public String getSport() {
        return Sport;
    }

    public String getSportPng() {
        return SportPng;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "Name='" + Name + '\'' +
                ", NativeName='" + NativeName + '\'' +
                ", Sport='" + Sport + '\'' +
                ", SportPng='" + SportPng + '\'' +
                '}';
    }
}
