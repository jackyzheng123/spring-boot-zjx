package com.zjx.cache.dao;

import com.zjx.cache.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/1/24 16:07
 * @Version V1.0
 **/
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
