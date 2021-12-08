package com.example.work_out_.model;

public class Activities {
    private String description,name;
    private int[] sets = new int[]{2,3,4,5,2,3};

    public Activities(String description, String name, int[] sets, User user) {
        /*
        String level = user.getLevel();
        int difficulty;
        switch (level){
            case "beginner":
                difficulty = 1;
                break;
            case "intermediate":
                difficulty = 2;
                break;
            case "advanced":
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
        this.sets = sets;

         */
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
