package com.example.ghibliapp.Controleur;

import java.util.List;

public class RestGhibliResponse {
    private Integer count;
    private String next;
    private List<Ghibli> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Ghibli> getResults() {
        return results;
    }
}

