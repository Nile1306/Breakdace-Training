package de.htw_berlin.mytodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

@GetMapping("/session")
    public FootworkSession getSession(){
    return new FootworkSession (40, 20, 3);
}

}
