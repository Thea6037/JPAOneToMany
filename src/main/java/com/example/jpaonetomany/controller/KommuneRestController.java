package com.example.jpaonetomany.controller;

import com.example.jpaonetomany.exception.ResourceNotFoundException;
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
import java.util.Optional;

//PostMapping = man indsætter ny
//PutMapping = man ændrer en allerede eksisterende


@RestController
@CrossOrigin
public class KommuneRestController
{
    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/kommunenavn/{navn}")
    public ResponseEntity<Kommune> getKommuneByName(@PathVariable String navn)
    {
        Kommune kommune = kommuneRepository.findKommuneByNavn(navn).orElseThrow(() -> new ResourceNotFoundException("Kommune ikke fundet med navn" + navn));
        return new ResponseEntity<>(kommune, HttpStatus.OK);
    }

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

    @PostMapping("/kommune")
    public ResponseEntity<Kommune> postRegion(@RequestBody Kommune kommune)
    {
        System.out.println("Indsætter ny kommune");
        System.out.println(kommune);

        Kommune savedKommune = kommuneRepository.save(kommune);
        if(savedKommune == null)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedKommune, HttpStatus.CREATED);
        }
    }

    @PutMapping("/kommune/{kode}")
    public ResponseEntity<Kommune> putKommune(@PathVariable String kode, @RequestBody Kommune kommune)
    {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if(orgKommune.isPresent())
        {
            kommune.setKode(kode); //hvis man ændrer i "kode", så overskriver den det eksisterende objekt og ikke laver et nyt objekt
            kommuneRepository.save(kommune);
            return new ResponseEntity<>(kommune, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Kommune(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/kommune/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode)
    {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if(orgKommune.isPresent())
        {
            kommuneRepository.deleteById(kode);
            return ResponseEntity.ok("Kommune deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found");
        }
    }
}
