package com.moringaschool.student.hero;

public class Hero {

    private int id;
    private String name;
    private int age;
    private String power;
    private String weakness;

    public Hero() {
    }

    public Hero(int id, String name, int age, String power, String weakness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
}
