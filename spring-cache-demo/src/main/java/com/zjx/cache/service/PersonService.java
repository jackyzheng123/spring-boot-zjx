package com.zjx.cache.service;

import com.zjx.cache.dao.PersonRepository;
import com.zjx.cache.dto.PageQueryDTO;
import com.zjx.cache.dto.PersonQueryDTO;
import com.zjx.cache.entity.Person;
import org.springframework.data.domain.Page;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2020/1/17 15:40
 * @Version V1.0
 **/
public interface PersonService extends BaseService<Person, Integer, PersonRepository> {

    void add(Person person);

    void modify(Person person);

    Person getPerson(Integer id);

    void remove(Integer id);

    Page<Person> getPersonPage(PersonQueryDTO dto);
}
