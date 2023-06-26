package jdbc.dao;

import jdbc.entity.Users;

import java.util.List;

public interface UsersDAO {
    void addUser(Users users);

    Users getUserById(Long userId);

    List<Users> getAllUsers();

    void updateUserById(Users users);

    void deleteUserById(Long userId);
}
