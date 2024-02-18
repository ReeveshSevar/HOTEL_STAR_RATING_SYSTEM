package com.user.UserService.repository;

import com.user.UserService.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String>
{
}
