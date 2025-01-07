package com.metaverse.workflow.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

    /*@Query("FROM user WHERE mobile_no=:mobileNo")
    List<UserEntity> findAllByMobileNo(@Param("mobileNo") Long mobileNo);*/

}
