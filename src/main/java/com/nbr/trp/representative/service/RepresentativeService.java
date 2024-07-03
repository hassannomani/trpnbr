package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.entity.RepresentativeAgentView;
import com.nbr.trp.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RepresentativeService {
    public Representative saveRepresentative(Representative representative);

    public List<Representative> getAllRepresentatives();

    public Optional<Representative> getUserByTin(String tin);

    public Optional<Representative> getUserById(String uuid);

    public List<Representative> getAllRepresentativesOfAnAgent(String id);

    public Representative assignAgent(String tin, String agent);

    public RepresentativeAgentView getAgentInfo(String tin);

    public Representative getSingleRepresentativesOfAnAgent(String agent, String trp);


}
