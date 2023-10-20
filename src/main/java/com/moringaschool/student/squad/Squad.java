package com.moringaschool.student.squad;

public class Squad {
    private int id;
    private String name;
    private int size;
    private String cause;


    public Squad() {
    }

    public Squad(int id, String name, int size, String cause) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
