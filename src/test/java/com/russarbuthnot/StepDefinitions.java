package com.russarbuthnot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StepDefinitions {
    HashMap<String, ArrayList<String>> posts = new HashMap<>();
    HashMap<String, ArrayList<String>> follows = new HashMap<>();
    List<String> timeline = new ArrayList<>();

    @Given("{word} has published {string}")
    public void user_has_published(String user, String message) {
        String messageWithUser = String.format("%s - %s", user, message);
        if (null != posts.get(user)) {
            ArrayList<String> messages = posts.get(user);
            messages.add(messageWithUser);
            posts.put(user, messages);
        } else {
            posts.put(user, new ArrayList<>(Collections.singleton(messageWithUser)));
        }
    }

    @When("{word} views {word} timeline")
    public void user_views_timeline(String viewer, String user) {
        if (user.equals("her") || user.equals("his")) {
            timeline = posts.get(viewer);
            if (null != follows.get(viewer)) {
                timeline = posts.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
            }
        } else {
            String[] splitUser = user.split("'");
            timeline = posts.get(splitUser[0]);
        }
    }

    @When("{word} follows {word}")
    public void follows(String user1, String user2) {
        if (null != follows.get(user1)) {
            ArrayList<String> following = follows.get(user1);
            following.add(user2);
            follows.put(user1, following);
        } else {
            follows.put(user1, new ArrayList<>(Collections.singleton(user2)));
        }
    }

    @Then("{word} sees: {string}")
    public void user_sees(String user, String expectedAnswer) {
        assertTrue(timeline.contains(expectedAnswer));
    }
}
