package com.dice.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TwitterAPI {

    @Autowired
    private Twitter twitter;
    @GetMapping("/find/{user}")
    public TwitterProfile findUserByUserId(@PathVariable String userId) {
        return twitter.userOperations().getUserProfile(userId);
    }

    @GetMapping("/tweets/{usrId}")
    public List<String> getTweetsOfUser(@PathVariable String userId) {
        List<String> tweets = new ArrayList<>();
        twitter.timelineOperations().getUserTimeline(userId).forEach(tweet -> {
            tweets.add(tweet.getText());
        });
        return tweets;
    }

}
