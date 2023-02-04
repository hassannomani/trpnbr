package com.nbr.trp.representative.service;

import com.nbr.trp.representative.entity.Representative;
import com.nbr.trp.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RepresentativeService {
    public Representative saveRepresentative(Representative representative);

    public List<Representative> getAllRepresentatives();

    public Optional<Representative> getUserByUsername(String username);

    public Optional<Representative> getUserByUuid(String uuid);

}
