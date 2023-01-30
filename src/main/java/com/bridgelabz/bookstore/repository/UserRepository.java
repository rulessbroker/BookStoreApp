package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	@Query(value = "select * from user_details where email =:email", nativeQuery = true)
	public Optional<UserModel> findByEmail(String email);

	@Query(value = "delete from user_details where email =:email", nativeQuery = true)
	void deleteByEmail(String email);
}
