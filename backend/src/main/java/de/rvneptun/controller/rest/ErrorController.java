package de.rvneptun.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

public class ErrorController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/error")
    public String listAvailable(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Ein Fehler ist aufgetreten";
    }
}
