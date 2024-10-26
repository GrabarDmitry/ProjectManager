package com.example.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.UserService.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query(
        value = "SELECT * FROM USER_ENTITY", 
        nativeQuery = true)
    List<User> findAll();

    @Query(
        value = "SELECT * FROM USER_ENTITY WHERE ID=?1 ", 
        nativeQuery = true)
    User findByStringId(String id);

    @Query(
        value = "SELECT * FROM USER_ENTITY WHERE ID IN :userIds", 
        nativeQuery = true)
    List<User> findByStringIds(@Param("userIds") List<String> userIds);

}
