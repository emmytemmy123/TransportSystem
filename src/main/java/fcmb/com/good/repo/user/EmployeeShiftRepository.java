package fcmb.com.good.repo.user;

import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.entity.user.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {

    @Query("select st from EmployeeShift st where st.uuid=:recordId")
    Optional<EmployeeShift> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from EmployeeShift st where st.uuid=:recordId")
    Optional<EmployeeShift> deleteByUuid(@Param("recordId")UUID uuid);

}
