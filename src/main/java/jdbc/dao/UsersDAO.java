package jdbc.dao;

import jdbc.entity.Users;

import java.util.List;

public interface UsersDAO {
    void addUser(Users users);

    Users getUsersById(Long userId);

    List<Users> getAllUsers();

    void updateUserById(Long userId, Users users);

    void deleteUserById(Long userId);
}
