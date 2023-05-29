package com.nbr.trp.action.repository;

import com.nbr.trp.action.entity.Action;
import com.nbr.trp.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ActionRepository extends JpaRepository<Action, String> {

    Action save(Action action);

    Action findByActionId(String id);

    Action findBySender(String tin);

    Action findByReceiver(String tin);

    Action findByActionType(String type);

}
