package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface LoginRepository extends JpaRepository<LoginCredentials,Integer> {

    @Query(value="SELECT l FROM LoginCredentials l WHERE l.email=:email AND l.password=:password")
    public LoginCredentials q(String email, String password);
}
