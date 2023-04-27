package fcmb.com.good.repo.services;

import fcmb.com.good.model.entity.services.Services;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query("select st from Services st where st.uuid=:recordId")
    Optional<Services> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Services st where st.uuid=:recordId")
    Optional<Services> deleteByUuid(@Param("recordId")UUID uuid);

}


