package com.logindemo.login.repository;

import com.logindemo.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByMail(String username);

    boolean existsByMobile(String username);

    User findByMail(String username);

    User findByMobile(String username);
}
