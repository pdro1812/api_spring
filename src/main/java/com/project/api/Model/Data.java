package com.project.api.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private String name;
    private String country;
    private int idade;
    private int score;
    private boolean active;
    private Team team;

    @JsonProperty("nome")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pais")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @JsonProperty("ativo")
    public boolean isActive() {
        return active;
    }
    public void isActive(boolean active) {
        this.active = active;
    }  

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
}

