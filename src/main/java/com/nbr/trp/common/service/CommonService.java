package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.District;
import com.nbr.trp.common.entity.Division;
import com.nbr.trp.common.entity.Thana;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    List<Division> getAllDivision();

    List<District> getAllDistrict();

    List<Thana> getAllThana();


}
