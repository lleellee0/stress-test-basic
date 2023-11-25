package com.example.stresstestbasic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScenarioController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        sleep(20);

        return "Login Success";
    }

    @RequestMapping(value = "/some-function-one", method = RequestMethod.GET)
    public String someFunctionOne() {
        sleep(30);

        return "Result One";
    }

    @RequestMapping(value = "/some-function-two", method = RequestMethod.GET)
    public String someFunctionTwo() {
        sleep(15);

        return "Result Two";
    }

    private void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
