package com.example.work_out_;

import static org.junit.Assert.assertEquals;

import com.example.work_out_.model.User;

import org.junit.Test;

public class UserTest {


    @Test
    public void create_user(){

        User user = new User("Pito", "pitosucio@gmail.com","21", "69", "169", "Begginer", "Low", "Yes", "7 days");
        assertEquals(user.getName(), "Pito");
        assertEquals(user.getEmail(), "pitosucio@gmail.com");
        assertEquals(user.getAge(), "21");
        assertEquals(user.getWeight(), "69");
        assertEquals(user.getHeight(), "169");
        assertEquals(user.getLevelOfExercise(), "Begginer");
        assertEquals(user.getExerciseImpact(), "Low");
        assertEquals(user.getCardio(), "Yes");
        assertEquals(user.getObjetive(), "7 days");

    }

}
