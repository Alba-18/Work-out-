package com.example.work_out_.model;

public class Activities {
    private String description,name;
    private int[] sets = new int[]{2,3,4,5,2,3};

    public Activities(String description, String name, User user) {
        String level = user.getLevelOfExercise();
        int difficulty;
        switch (level){
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

    public void setSets(int[] sets) {
        this.sets = sets;
    }
}
