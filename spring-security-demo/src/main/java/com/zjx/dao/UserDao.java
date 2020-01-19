package com.zjx.dao;

import com.zjx.domain.SysRole;
import com.zjx.domain.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    SysUser findByUserName(String username);

    List<SysRole> findUserRole(Integer id);
}
