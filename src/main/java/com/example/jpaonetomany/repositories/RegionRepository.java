package com.example.jpaonetomany.repositories;

import com.example.jpaonetomany.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String>
{
}
