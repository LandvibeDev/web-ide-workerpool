package com.landvibe.webideworkerpool.container;

import com.landvibe.webideworkerpool.container.model.Container;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Container> getContainers(@RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "0") int skip) {
        LocalDateTime time = LocalDateTime.parse("2020-04-10T20:09:31", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        List<Container> containers = new ArrayList<>();
        containers.add(new Container(0, "container0", "description", "running", "nodejs:12", time, time));
        containers.add(new Container(1, "container1", "description", "running", "nodejs:12", time, time));
        containers.add(new Container(2, "container2", "description", "running", "nodejs:12", time, time));
        return containers;
    }

    @GetMapping(value = "/{pid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Container getContainer(@PathVariable int pid) {
        LocalDateTime time = LocalDateTime.parse("2020-04-10T20:09:31", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Container(0, "container0", "description", "running", "nodejs:12", time, time);
    }
}
