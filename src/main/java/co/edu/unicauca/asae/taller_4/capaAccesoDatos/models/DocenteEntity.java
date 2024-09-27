package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Docentes")
@PrimaryKeyJoinColumn(name = "IdPersona")
@Getter @Setter @NoArgsConstructor
public class DocenteEntity extends PersonaEntity {
    
    public DocenteEntity(int idPersona, String nombre, String apellido, String correo){
        super(idPersona, nombre, apellido, correo);
    }
}
