package fcmb.com.good.repo.others;

import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.model.entity.others.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select st from  Hotel st where st.uuid=:recordId")
    Optional<Hotel> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from  Hotel st where st.uuid=:recordId")
    Optional< Hotel> deleteByUuid(@Param("recordId")UUID uuid);

}
