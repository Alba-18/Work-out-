package com.example.work_out_.model;

public class Activities {
    private String description,name, userDificulty;
    private int[] sets = new int[]{2,3,4,5,2,1};

    public Activities(String description, String name, String userDificulty) {
        int difficulty;
        switch (userDificulty){
            case "Beginner":
                difficulty = 1;
                break;
            case "Intermediate":
                difficulty = 2;
                break;
            case "Advanced":
                difficulty = 3;
                break;
            default:
                difficulty = 1;
                break;
        }
        for (int i= 0; i < sets.length; i++){
            sets[i] = difficulty * sets[i];
        }
        this.description = description;
        this.name = name;
        this.userDificulty = userDificulty;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getSets() {
        return sets;
    }

    public String getUserDificulty() {
        return userDificulty;
    }

    public void setUserDificulty(String userDificulty) {
        this.userDificulty = userDificulty;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }
}
