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
    public Optional<Representative> getUserByTin(String tin) {
        return Optional.ofNullable(representativeRepository.findByTinNo(tin).orElse(null));
    }

    @Override
    public Optional<Representative> getUserById(String id) {
        return Optional.ofNullable(representativeRepository.findByUserid(id).orElse(null));
    }
}
