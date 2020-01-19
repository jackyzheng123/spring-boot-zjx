package com.zjx.cache.service;

import com.zjx.cache.dao.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 基础接口定义
 *
 * @param <T>
 * @param <ID>
 * @author leo
 * @time 2018-12-25
 */
public interface BaseService<T, ID extends Serializable, R extends BaseRepository<T, ID>> {

    /**
     * 保存对象
     *
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 批量保存对象
     *
     * @param list
     * @return
     */
    List<T> saveAll(List<T> list);

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 更新部分字段
     *
     * @param id
     * @param t
     * @param properties
     * @return
     */
    T update(ID id, T t, String... properties);

    /**
     * 根据ID查找对象(Optional)
     *
     * @param id
     * @return
     */
    Optional<T> get(ID id);

    /**
     * 根据ID查询对象
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 删除对象
     *
     * @param t
     */
    void delete(T t);

    /**
     * 根据ID判断对象是否存在
     *
     * @param id
     * @return
     */
    boolean exists(ID id);

    /**
     * 统计数量
     *
     * @return
     */
    long count();

    /**
     * 查找所有对象列表
     *
     * @return
     */
    List<T> findList();

    /**
     * 排序查询所有对象列表
     *
     * @param sort
     * @return
     */
    List<T> findList(Sort sort);

    /**
     * 根据IDS查找对象列表
     *
     * @param ids
     * @return
     */
    List<T> findList(List<ID> ids);

    /**
     * 动态列表查询
     *
     * @param spec
     * @return
     */
    List<T> findList(Specification<T> spec);

    /**
     * 分页查询对象列表
     *
     * @param pageable
     * @return
     */
    Page<T> findPage(Pageable pageable);


    /**
     * 动态分页查询
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findPage(Specification<T> spec, Pageable pageable);

}
