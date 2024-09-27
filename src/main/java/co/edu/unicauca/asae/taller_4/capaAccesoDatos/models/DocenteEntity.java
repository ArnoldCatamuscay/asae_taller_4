package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Docentes")
@PrimaryKeyJoinColumn(name = "idPersona")
@Getter
@Setter
@NoArgsConstructor
public class DocenteEntity extends PersonaEntity {

    @OneToOne
    @JoinColumn(name = "idOficina")
    private OficinaEntity objOficina;

    public DocenteEntity(int idPersona, String nombre, String apellido, String correo) {
        super(idPersona, nombre, apellido, correo);
    }
}
