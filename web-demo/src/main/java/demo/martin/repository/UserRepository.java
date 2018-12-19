package demo.martin.repository;

import org.springframework.data.repository.CrudRepository; 

import demo.martin.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
}
