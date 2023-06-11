package com.nbr.trp.commission.service;

import com.nbr.trp.commission.entity.Commission;
import com.nbr.trp.commission.entity.Items;
import com.nbr.trp.commission.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService{

    @Autowired
    ItemsRepository itemsRepository;

    public Items saveItems(Items items){
        return itemsRepository.save(items);
    }

    public Items getItemsByCode(String code){
        return itemsRepository.findByItemCode(code);
    }

    public Items getItemsByNature(String nature){
        return itemsRepository.findByItemNature(nature);
    }



}
