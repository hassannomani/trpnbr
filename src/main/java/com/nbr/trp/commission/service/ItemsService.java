package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Items;
import org.springframework.stereotype.Service;

@Service
public interface ItemsService {

    public Items saveItems(Items items);

    public Items getItemsByCode(String code);


    public Items getItemsByNature(String nature);
}
