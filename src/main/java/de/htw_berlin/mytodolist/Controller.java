package de.htw_berlin.mytodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

@GetMapping
public String test() {
    return "Hello World";
}

}
