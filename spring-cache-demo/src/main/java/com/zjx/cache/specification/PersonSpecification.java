package com.zjx.cache.specification;

import com.zjx.cache.dto.PersonQueryDTO;
import com.zjx.cache.entity.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Carson Cheng
 * @Date 2020/1/17 15:53
 * @Version V1.0
 **/
public class PersonSpecification {

    @SuppressWarnings("serial")
    public static Specification<Person> findPersonPage(PersonQueryDTO dto) {
        return new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                String name = dto.getName();
                if( StringUtils.isNotEmpty(name)){
                    predicates.add(criteriaBuilder.like(root.<String>get("name"), "%" + name + "%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
