package transport.management.repo.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import transport.management.model.entity.services.Delivery;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select st from Delivery st where st.uuid=:recordId")
    Optional<Delivery> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Delivery st where st.uuid=:recordId")
    Optional<Delivery> deleteByUuid(@Param("recordId")UUID uuid);

}


