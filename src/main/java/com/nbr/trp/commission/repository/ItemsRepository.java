package com.nbr.trp.commission.repository;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ItemsRepository extends JpaRepository<Items, String> {

    public Items save(Items items);

    public Items findByItemCode(String code);

    public Items findByItemNature(String nature);

}
