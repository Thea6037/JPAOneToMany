package com.example.jpaonetomany.controller;

import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.KommuneRepository;
import com.example.jpaonetomany.repositories.RegionRepository;
import com.example.jpaonetomany.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//PostMapping = man indsætter ny
//PutMapping = man ændrer en allerede eksisterende


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
