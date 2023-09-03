package com.example.jpaonetomany.controller;

import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.RegionRepository;
import com.example.jpaonetomany.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionRESTController
{
    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;


    @GetMapping("/getregioner")
    public List<Region> getRegioner()
    {
        List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
        return lstRegioner;
    }

    @GetMapping("/getregionerdata")
    public List<Region> getRegionerFraData()
    {
        List<Region> regionList = regionRepository.findAll();
        return regionList;
    }

    @GetMapping("/kommunenavne/{id}")
    public List<String> getKommuneNavne(@PathVariable String id) throws Exception
    {
        Optional<Region> region = regionRepository.findById(id);
        if(region.isEmpty())
        {
            System.out.println("No regions found");
            throw new Exception();
        }
        List<String> kommuneNavne = region.get().getKommuneNavne();

        return kommuneNavne;
    }

    @DeleteMapping("/region/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable String id)
    {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isPresent())
        {
            regionRepository.deleteById(id);
            return ResponseEntity.ok("Region deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
    }
}
