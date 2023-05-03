package logistic.management.repo.user;

import logistic.management.model.entity.user.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Sellers, Long> {

    Sellers findByUsername(String username);

    @Query("select st from Sellers st where st.uuid=:recordId")
    Optional<Sellers> findByUuid(@Param("recordId") UUID uuid);

    @Query("select e from Sellers e where e.email=:email")
    Optional<Sellers> findByEmailId(@Param("email")String email);

    Sellers findByEmail(String email);

}
