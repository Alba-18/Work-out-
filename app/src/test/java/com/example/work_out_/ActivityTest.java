package com.example.work_out_;

import static org.junit.Assert.assertEquals;

import com.example.work_out_.model.Activities;

import org.junit.Test;

public class ActivityTest {

    @Test
    public void create_activity(){

        Activities activity = new Activities("Entrena pito", "PitoTrainer", "Intermediate");
        assertEquals(activity.getName(), "PitoTrainer");
        assertEquals(activity.getDescription(), "Entrena pito");
        assertEquals(activity.getUserDificulty(), "Intermediate");


    }

}
