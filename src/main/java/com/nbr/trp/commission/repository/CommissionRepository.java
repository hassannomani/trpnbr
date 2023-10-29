package com.nbr.trp.commission.repository;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.CommissionBillView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommissionRepository extends JpaRepository<Commission, String> {


    public Commission findByCreationNo(String id);

    public Commission save(Commission commission);

    public List<Commission> findByDebitCode(String id);

    public List<Commission> findByCreditCode(String id);

//    @Query(value = "select first_name, last_name,payee,payee_type,ledger_id,creationNo,creation_date, taxpayer_id," +
//            "taxpayer_name,agent_commission,representative_commission, c.status from users u join commission c" +
//            " on u.username=c.payee join ledger l on c.ledger_id=l.lid where c.status=0",nativeQuery = true)
//    public List<CommissionBillView> findPendingBillAdmin();


    @Query(value = "select first_name, last_name,payee,payee_type,ledger_id,creationNo,creation_date, taxpayer_id," +
            "taxpayer_name,agent_commission,representative_commission, c.status from users u join commission c" +
            " on u.username=c.payee join ledger l on c.ledger_id=l.lid where c.status=:status and u.username=:tin order by created_date desc",nativeQuery = true)
    public List<CommissionBillView> findBillUser(@Param("tin") String tin, @Param("status") String status);


    @Query(value = "select count(creationNo) from commission where ledger_id=:ledger_id and payee_type=:role and status='1' ",nativeQuery = true)
    public Integer findDuplicatePayment(@Param("ledger_id") String ledger_id, @Param("role") String role );


    @Query(value = "select first_name, last_name,payee,payee_type,ledger_id,creationNo,creation_date, taxpayer_id," +
            "taxpayer_name,agent_commission,representative_commission, c.status from users u join commission c" +
            " on u.username=c.payee join ledger l on c.ledger_id=l.lid where c.status=:status order by creation_date desc",nativeQuery = true)
    public List<CommissionBillView> findBillAdmin(@Param("status") String status);

    @Query(value = "select first_name, last_name,payee,payee_type,ledger_id,creationNo,creation_date, taxpayer_id," +
            "taxpayer_name,agent_commission,representative_commission, c.status from users u join commission c" +
            " on u.username=c.payee join ledger l on c.ledger_id=l.lid where c.status=1 order by creation_date desc",nativeQuery = true)
    public List<CommissionBillView> findapprovedBillAdmin();


    @Query(value = "select distinct first_name, last_name, payee from commission c join users u on u.username=c.payee where c.status='0' ",nativeQuery = true)
    public Object[] get_applicants();
}