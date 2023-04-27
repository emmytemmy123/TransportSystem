package fcmb.com.good.repo.transaction;

import fcmb.com.good.model.entity.transaction.BookingReminder;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingReminderRepository extends JpaRepository<BookingReminder, Long> {

    @Query("select st from BookingReminder st where st.uuid=:recordId")
    Optional<BookingReminder> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from BookingReminder st where st.uuid=:recordId")
    Optional<BookingReminder> deleteByUuid(@Param("recordId")UUID uuid);

}
