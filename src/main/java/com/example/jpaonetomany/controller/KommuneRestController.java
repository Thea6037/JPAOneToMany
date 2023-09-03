package com.example.jpaonetomany.controller;

import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.KommuneRepository;
import com.example.jpaonetomany.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KommuneRestController
{
    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getkommuner")
    public List<Kommune> getKommuner()
    {
        List<Kommune> lstKommuner = apiServiceGetKommuner.getKommuner();
        return lstKommuner;
    }

    @GetMapping("/getkommunerdata")
    public List<Kommune> getRegionerFraData()
    {
        List<Kommune> kommuneList = kommuneRepository.findAll();
        return kommuneList;
    }
}
