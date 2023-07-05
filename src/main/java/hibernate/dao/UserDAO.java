package hibernate.dao;


import hibernate.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    void updateUserById(User user);

    void deleteUserById(User user);
}
