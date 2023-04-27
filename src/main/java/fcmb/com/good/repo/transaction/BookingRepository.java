package fcmb.com.good.repo.transaction;

import fcmb.com.good.model.entity.transaction.Booking;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select st from Booking st where st.uuid=:recordId")
    Optional<Booking> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Booking st where st.uuid=:recordId")
    Optional<Booking> deleteByUuid(@Param("recordId")UUID uuid);

}
