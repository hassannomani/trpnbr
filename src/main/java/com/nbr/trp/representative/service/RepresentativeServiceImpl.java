package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.entity.RepresentativeAgentView;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import com.nbr.trp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServiceImpl implements RepresentativeService{
    @Autowired
    RepresentativeRepository  representativeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Representative saveRepresentative(Representative representative) {
        Integer trpno = userRepository.findNoOfTRP();
        representative.setTrpId("TRP"+(trpno+1));
        Representative representative1 = representativeRepository.save(representative);
        return  representative1;
    }

    @Override
    public List<Representative> getAllRepresentatives() {
        return representativeRepository.findAll();
    }

    @Override
    public Optional<Representative> getUserByTin(String tin) {
        return Optional.ofNullable(representativeRepository.findByTinNo(tin).orElse(null));
    }

    @Override
    public Optional<Representative> getUserById(String id) {
        return Optional.ofNullable(representativeRepository.findByUserid(id).orElse(null));
    }

    @Override
    public List<Representative> getAllRepresentativesOfAnAgent(String id){
        return representativeRepository.findByAgentId(id);
    }

    @Override
    public Representative assignAgent(String tin, String agent){
        Representative rep = representativeRepository.findByTin(tin);
        rep.setAgentId(agent);
        return representativeRepository.save(rep);
    }
    @Override
    public RepresentativeAgentView getAgentInfo(String tin){
        return representativeRepository.findAgentInfoByTin(tin);
    }


}
