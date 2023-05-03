package logistic.management.services.user;

import logistic.management.model.entity.user.AppUser;

public interface UserServiceTest {

    public AppUser getUserById(int anyInt);

    public AppUser saveUser(AppUser user);

}