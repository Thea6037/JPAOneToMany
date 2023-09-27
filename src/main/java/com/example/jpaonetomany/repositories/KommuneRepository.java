package com.example.jpaonetomany.repositories;

import com.example.jpaonetomany.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KommuneRepository extends JpaRepository<Kommune, String>
{
    Optional<Kommune> findKommuneByNavn(String navn);
}
