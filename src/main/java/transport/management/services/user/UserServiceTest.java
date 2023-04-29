package transport.management.services.user;

import transport.management.model.entity.user.AppUser;

public interface UserServiceTest {

    public AppUser getUserById(int anyInt);

    public AppUser saveUser(AppUser user);

}