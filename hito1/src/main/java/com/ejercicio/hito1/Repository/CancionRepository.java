package com.ejercicio.hito1.Repository;

import com.ejercicio.hito1.Model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Long> {
    List<Cancion> findByNombreContainingIgnoreCase(String nombre);
    List<Cancion> findByLetraContainingIgnoreCase(String letra);
}
