package com.literAlura.LiterAlura.model.entity.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByFechaFallecimientoGreaterThan(Integer año);

    List<Persona> findByNombre(String nombre);
}
