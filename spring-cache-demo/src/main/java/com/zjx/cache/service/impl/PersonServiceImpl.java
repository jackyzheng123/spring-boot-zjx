package com.zjx.cache.service.impl;

import com.zjx.cache.dao.PersonRepository;
import com.zjx.cache.dto.PageQueryDTO;
import com.zjx.cache.dto.PersonQueryDTO;
import com.zjx.cache.entity.Person;
import com.zjx.cache.service.PersonService;
import com.zjx.cache.specification.PersonSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/1/24 16:05
 * @Version V1.0
 **/
@Service
@Slf4j
public class PersonServiceImpl extends BaseServiceImpl<Person, Integer, PersonRepository> implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @CachePut(value = "person", keyGenerator = "iKeyGenerator")
    public void add(Person person) {
        System.out.println("为id、key为:" + person.getId() + "数据做了缓存");
        personRepository.save(person);
    }

    @Override
    @CacheEvict(value = "person", keyGenerator = "iKeyGenerator", allEntries = true)
    public void modify(Person person) {
        System.out.println("为id、key为:" + person.getId() + "数据做了缓存");
        personRepository.save(person);
    }

    @Override
    @Cacheable(value = "person", keyGenerator = "iKeyGenerator")
    public Person getPerson(Integer id) {
        System.out.println("为id、key为:" + id + "数据做了缓存");
        return personRepository.getOne(id);
    }

    @Override
    @CacheEvict(value = "person", keyGenerator = "iKeyGenerator", allEntries = true)
    public void remove(Integer id) {
        System.out.println("删除了id、key为" + id + "的数据缓存");
        personRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "person", keyGenerator = "iKeyGenerator")
    public Page<Person> getPersonPage(PersonQueryDTO dto) {
        PageQueryDTO.Order order = dto.getOrder();
        Sort sort = new Sort(order.getIsDesc() ? Sort.Direction.DESC : Direction.ASC, order.getOrderBy());
        Pageable pageable = new PageRequest(dto.getPageNum(), dto.getPageSize(), sort);
        return personRepository.findAll(PersonSpecification.findPersonPage(dto), pageable);
    }
}
