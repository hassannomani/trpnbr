package com.nbr.trp.user.repository;

import com.nbr.trp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByUuid(String uuid);


    @Query(value = "select * from users where status=0 order by added_date desc",
            nativeQuery = true)
    List<User> findAllPending();

    @Query(value = "select * from users where username=?1",
            nativeQuery = true)
    User getByTin(String username);

    @Query(value = "select * from users where status=1 order by added_date desc",
            nativeQuery = true)
    List<User> findAllByOrderByAddedDateDesc();

    List<User> findByStatus(String status);

//    @Query(value = "update users set status = '1' where uuid = ?1",
//            nativeQuery = true)
//    User approveUser(String uuid);
}

