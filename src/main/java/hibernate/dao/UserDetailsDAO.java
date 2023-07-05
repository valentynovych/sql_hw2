package hibernate.dao;

import hibernate.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    void addUsersDetails(UserDetails usersDetails);

    UserDetails getUsersDetailById(UserDetails usersDetails);

    List<UserDetails> getAllUsersDetails();

    void updateUsersDetailsById(UserDetails usersDetails);

    void deleteUserDetailsById(UserDetails usersDetails);
}
