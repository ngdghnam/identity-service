package com.devteria.identity_service.repos;

import com.devteria.identity_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//User - Model/ Entity, String - Kiểu dữ liệu của ID
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
