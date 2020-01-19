package com.zjx.cache.dao;

import com.zjx.cache.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2019/1/24 16:07
 * @Version V1.0
 **/
@Repository
public interface PersonRepository extends BaseRepository<Person, Integer> {

}
