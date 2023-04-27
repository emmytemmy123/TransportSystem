package fcmb.com.good.repo.user;

import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    //List<Role> findRolesByUsersId(Long userId);

    Optional<Role> findByDepartment(String department);

    @Query("select st from Role st where st.uuid=:recordId")
    Optional<Role> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Role st where st.uuid=:recordId")
    Optional<Role> deleteByUuid(@Param("recordId")UUID uuid);



}
