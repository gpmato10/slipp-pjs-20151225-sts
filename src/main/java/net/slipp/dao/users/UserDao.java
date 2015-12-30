package net.slipp.dao.users;

public interface UserDao {

	User findById(String userId);

	void create(User user);

	void update(User user);

}