package com.zjx.cache.controller;

import com.zjx.cache.dto.PageQueryDTO;
import com.zjx.cache.dto.PersonQueryDTO;
import com.zjx.cache.entity.Person;
import com.zjx.cache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        personService.add(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Integer id){
        return personService.getPerson(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        personService.remove(id);
    }

    @PutMapping
    public void update(@RequestBody Person person){
        personService.modify(person);
    }

    @GetMapping
    public Page<Person> getPersonPage(@RequestBody PersonQueryDTO dto){
        return personService.getPersonPage(dto);
    }
}
