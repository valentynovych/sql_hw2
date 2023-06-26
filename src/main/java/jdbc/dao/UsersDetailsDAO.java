package jdbc.dao;

import jdbc.entity.UsersDetails;

import java.util.List;

public interface UsersDetailsDAO {

    void addUsersDetails(UsersDetails usersDetails);

    UsersDetails getUsersDetailById(Long detailsId);

    List<UsersDetails> getAllUsersDetails();

    void updateUsersDetailsById(UsersDetails usersDetails);

    void deleteUserDetailsById(Long detailsId);
}
