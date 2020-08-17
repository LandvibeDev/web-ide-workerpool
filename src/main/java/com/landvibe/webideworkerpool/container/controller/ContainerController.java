package com.landvibe.webideworkerpool.container.controller;

import com.landvibe.webideworkerpool.container.model.Container;
import com.landvibe.webideworkerpool.container.service.ContainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    private ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Container> getContainers(@RequestParam String userId, @RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "0") int skip) {
        return containerService.getContainers(userId, limit, skip);
    }

    @GetMapping(value = "/{cid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Container> getContainer(@PathVariable String cid) {
        return containerService.getContainer(cid);
    }

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createContainer(@RequestParam String name, @RequestParam String description, @RequestParam String type, @RequestParam String userId) {
        String time = Instant.now().toString();
        String cid = UUID.randomUUID().toString();
        //TODO: type validation
        Container container = new Container(cid, name, description, "created", "type", time, time, userId);
        containerService.createContainer(container);
    }

    @DeleteMapping(value = "/{cid}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContainer(@PathVariable String cid) {
        containerService.deleteContainer(cid);
    }
}
