package spring.app.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.app.crud.dao.PersonDAO;

@Controller
@RequestMapping("/test-batch-update")
public class Batchcontroller {
    private PersonDAO personDAO;

    @Autowired
    public Batchcontroller(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String withoutBatch() {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatch() {
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
}
