package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.representative.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServiceImpl implements RepresentativeService{
    @Autowired
    RepresentativeRepository  representativeRepository;

    @Override
    public Representative saveRepresentative(Representative representative) {
        Representative representative1 = representativeRepository.save(representative);
        return  representative1;
    }

    @Override
    public List<Representative> getAllRepresentatives() {
        return representativeRepository.findAll();
    }

    @Override
    public Optional<Representative> getUserByUsername(String username) {
        return Optional.ofNullable(representativeRepository.findByUsername(username).orElse(null));
    }

    @Override
    public Optional<Representative> getUserByUuid(String uuid) {
        return Optional.ofNullable(representativeRepository.findByUuid(uuid).orElse(null));
    }
}
