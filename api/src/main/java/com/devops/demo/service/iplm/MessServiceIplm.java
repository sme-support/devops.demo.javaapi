package com.devops.demo.service.iplm;

import com.devops.demo.entity.Mess;
import com.devops.demo.service.MessService;
import com.devops.demo.repository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DatVM2
 * Date: 30/01/2019
 * Time: 13:28
 */
@Service
public class MessServiceIplm implements MessService {
    @Autowired
    private MessRepository messRepository;

    @Override
    public Mess findByIdMess(Mess mess) {
        if( messRepository.findById(mess.getId()).isPresent()){
            return mess;
        }
        return null;
    }

    @Override
    public void update(Mess mess) {
        messRepository.save(mess);
    }
}
