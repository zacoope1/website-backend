package com.webservice.user;

import com.webservice.user.model.User;
import com.webservice.common.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE email = :email", nativeQuery = true)
    public User getUserInfo(@Param("email") String email);

}
