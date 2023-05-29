package com.nbr.trp.action.service;

import com.nbr.trp.action.entity.Action;
import org.springframework.stereotype.Service;

@Service
public interface ActionService {

    public Action save(Action action);

    public Action getAction(String id);

    public Action getActionAgent(String tin);

    public Action getActionTrp(String tin);

    public Action getActionAgentWithTime(String tin);

    public Action getActionTrpWithTime(String tin);


}
