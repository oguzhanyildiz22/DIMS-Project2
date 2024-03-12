package com.sau.dims.repository;

import com.sau.dims.model.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser,Integer> {
}
