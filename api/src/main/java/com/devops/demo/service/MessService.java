package com.devops.demo.service;

import com.devops.demo.entity.Mess;

/**
 * Created by DatVM2
 * Date: 29/01/2019
 * Time: 15:21
 */
public interface MessService {
    Mess findByIdMess(Mess mess);
    void update(Mess mess);
}
