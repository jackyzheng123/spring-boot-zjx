package com.zjx.cache.service.impl;

import com.zjx.cache.dao.BaseRepository;
import com.zjx.cache.service.BaseService;
import com.zjx.cache.util.BeanUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T, ID, R> {

    @Autowired
    protected R repository;

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> saveAll(List<T> list) {
        return repository.saveAll(list);
    }

    @Override
    public T update(T t) {
        return repository.saveAndFlush(t);
    }

    @Override
    public T update(ID id, T t, String... properties) {
        Assert.isTrue(repository.existsById(id), String.format("The entity with id %s is not exits", id));
        T entity = repository.getOne(id);
        BeanUtils.copyProperties(t, entity, ArrayUtils.addAll(properties, BeanUtil.getNullPropertyNames(t)));
        return repository.saveAndFlush(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.getOne(id);
    }

    @Override
    public Optional<T> get(ID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<T> findList() {
        return repository.findAll();
    }

    @Override
    public List<T> findList(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<T> findList(Specification<T> spec) {
        return repository.findAll(spec);
    }

    @Override
    public Page<T> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findList(List<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Page<T> findPage(Specification<T> spec, Pageable pageable) {
        return this.repository.findAll(spec, pageable);
    }
}
