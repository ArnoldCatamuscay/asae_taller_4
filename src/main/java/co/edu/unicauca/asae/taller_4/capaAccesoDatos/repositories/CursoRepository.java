package co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.CursoEntity;

public interface CursoRepository extends CrudRepository<CursoEntity, Integer> {
    
}
