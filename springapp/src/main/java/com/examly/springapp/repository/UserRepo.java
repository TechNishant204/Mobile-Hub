package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


        @Query(value="from User where username=?1")
        Optional<User> loadUserDetailsByUserName(String username);

        //public boolean existsByuserName(String username);

        public boolean existsByEmail(String email);

        public boolean existsByMobileNumber(String mobileNumber);


}
