package co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.AsignaturaEntity;

public interface AsignaturaRepository extends CrudRepository<AsignaturaEntity, Integer> {
    
}
