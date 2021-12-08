package com.example.work_out_.model;

public class User {

    private String name,email,age, weight, height, levelOfExercise, cardio, exerciseImpact, objetive;

    public User() {
    }

    public User(String name, String email, String age, String weight, String height, String levelOfExercise,String exerciseImpact, String cardio,  String objetive) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.levelOfExercise = levelOfExercise;
        this.cardio = cardio;
        this.exerciseImpact = exerciseImpact;
        this.objetive = objetive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLevelOfExercise() {
        return levelOfExercise;
    }

    public void setLevelOfExercise(String levelOfExercise) {
        this.levelOfExercise = levelOfExercise;
    }

    public String getCardio() {
        return cardio;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    public String getExerciseImpact() {
        return exerciseImpact;
    }

    public void setExerciseImpact(String exerciseImpact) {
        this.exerciseImpact = exerciseImpact;
    }

    public String getObjetive() {
        return objetive;
    }

    public void setObjetive(String objetive) {
        this.objetive = objetive;
    }
}
