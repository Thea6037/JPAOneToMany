package com.example.jpaonetomany.service;

import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.repositories.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner
{
    private final RestTemplate restTemplate;
    public ApiServiceGetKommunerImpl(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Autowired
    KommuneRepository KommuneRepository;

    private void saveKommuner(List<Kommune> kommuner)
    {
        kommuner.forEach(reg -> KommuneRepository.save(reg));
    }

    @Override
    public List<Kommune> getKommuner(){
        ResponseEntity<List<Kommune>> kommuneResponse = restTemplate.exchange(kommuneUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>()
        {});
        //ResponseEntity wrapper et http response og body
        List<Kommune> Kommuner = kommuneResponse.getBody();
        saveKommuner(Kommuner);
        return Kommuner;
    }
}
