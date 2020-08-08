package com.landvibe.webideworkerpool.command;

import com.landvibe.webideworkerpool.command.model.Command;
import com.landvibe.webideworkerpool.command.model.CommandResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/command")
public class CommandController {

    public CommandController() {
    }

    @PostMapping(value = "/projects/{pid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CommandResult runCommand(@PathVariable int pid, @RequestBody Command command) {
        return new CommandResult("hello");
    }

}
