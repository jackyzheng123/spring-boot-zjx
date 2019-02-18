package com.zjx.cache.service;

import com.zjx.cache.dao.PersonRepository;
import com.zjx.cache.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/1/24 16:05
 * @Version V1.0
 **/
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @CachePut(value = "people", key = "#person.id")
    public void save(Person person) {
        personRepository.save(person);
        System.out.println("为id、key为:" + person.getId() + "数据做了缓存");
    }

    @Cacheable(value = "people", key = "#id")
    public Person getPerson(Integer id) {
        System.out.println("为id、key为:" + id + "数据做了缓存");
        return personRepository.getOne(id);
    }

    @CacheEvict(value = "people")
    public void delete(Integer id) {
        //personRepository.deleteById(id);
        System.out.println("删除了id、key为" + id + "的数据缓存");
    }

}
