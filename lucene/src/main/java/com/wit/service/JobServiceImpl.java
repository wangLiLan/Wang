package com.wit.service;

import com.wit.mapper.JobMapper;
import com.wit.pojo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl {

    @Autowired(required = false)
    private JobMapper jobMapper;
    public List<Job> findAll(){
        List<Job> list = jobMapper.selectAll();

        return list;
    }
}
