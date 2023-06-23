package jdbc.dao;

import jdbc.entity.UsersDetails;

import java.util.List;

public interface UsersDetailsDAO {

    void addUsersDetails(UsersDetails usersDetails);

    UsersDetails getUsersDetailById(Long detailsId);

    List<UsersDetails> getAllUsersDetails();

    void updateUsersDetailsById(Long detailsId);

    void deleteUserDetailsById(Long detailsId);
}
