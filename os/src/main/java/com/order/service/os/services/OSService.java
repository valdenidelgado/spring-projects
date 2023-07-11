package com.order.service.os.services;

import com.order.service.os.repositories.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OSService {

    @Autowired
    private OSRepository repository;
}
