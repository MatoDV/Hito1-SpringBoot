package com.ejercicio.hito1.Controller;

import com.ejercicio.hito1.Model.Cancion;
import com.ejercicio.hito1.Repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {
    @Autowired
    private CancionRepository cancionRepository;
    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@RequestBody Cancion cancion){
        Cancion nuevaCancion = cancionRepository.save(cancion);
        return ResponseEntity.ok(nuevaCancion);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> modificarCancion(@PathVariable Long id,@RequestBody Cancion cancion){
        Optional<Cancion> optionalCancion = cancionRepository.findById(id);
        if(optionalCancion.isPresent()){
            cancion.setId(id);
            cancionRepository.save(cancion);
            return ResponseEntity.ok(cancion);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>>buscarPorNombre(@RequestParam(required = false)String nombre,@RequestParam(required = false)String letra){
        List<Cancion> canciones;
        if(nombre!=null){
            canciones = cancionRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (letra!=null){
            canciones= cancionRepository.findByLetraContainingIgnoreCase(letra);
        }else{
            canciones = cancionRepository.findAll();
        }
        return ResponseEntity.ok(canciones);
    }
}
