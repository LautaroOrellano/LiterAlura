package com.literAlura.LiterAlura.model.entity.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaFallecimientoGreaterThan(Integer año);

    List<Autor> findByNombre(String nombre);
}
