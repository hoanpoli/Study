package com.example.test.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.test.model.Users;

public interface UserDao extends CrudRepository<Users, Integer> {
	@Query("FROM Users a WHERE a.id = :id")
	public Users getBy(@Param("id") int id);

	@Query("FROM Users a WHERE a.userName = :userName AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public Users getBy(@Param("userName") String userName);

	@Query("FROM Users a WHERE (a.userName = :userName OR a.email = :email) AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public Users getBy(@Param("userName") String userName, @Param("email") String email);

	@Query(value = "SELECT r.userName FROM Users r WHERE r.id = :id")
	public List<String> getRoleByUserId(@Param("id") int id);

	@Query("FROM Users")
	public List<Users> search();
}