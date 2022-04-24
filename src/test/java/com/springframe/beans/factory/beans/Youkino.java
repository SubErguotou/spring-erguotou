package com.springframe.beans.factory.beans;

public class Youkino {
    private String name;
    private int age;

    public void getInfo(){
        System.out.println("Youkino的getInfo方法");
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
}
