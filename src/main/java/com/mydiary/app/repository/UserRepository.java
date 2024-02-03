package com.mydiary.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mydiary.app.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query(value = "select * from users where username=:username LIMIT 1",nativeQuery = true)
	public User findByUserName(String username);

}
