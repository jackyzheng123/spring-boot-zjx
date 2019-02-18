package com.zjx.cache.controller;

import com.zjx.cache.entity.Person;
import com.zjx.cache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/1/24 16:03
 * @Version V1.0
 **/

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public void save(@RequestBody Person person){
        personService.save(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Integer id){
        return personService.getPerson(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        personService.delete(id);
    }
}
