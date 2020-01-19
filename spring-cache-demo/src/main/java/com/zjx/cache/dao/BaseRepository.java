package com.zjx.cache.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2020/1/17 15:51
 * @Version V1.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T,ID>, JpaRepository<T, ID>, CrudRepository<T, ID>, JpaSpecificationExecutor<T> {

}
