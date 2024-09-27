package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Administrativos")
@PrimaryKeyJoinColumn(name = "idPersona")
@Getter
@Setter
@NoArgsConstructor
public class AdministrativoEntity extends PersonaEntity {
    @Column(nullable = false, length = 255)
    private String rol;

    public AdministrativoEntity(int idPersona, String nombre, String apellido, String correo, String rol) {
        super(idPersona, nombre, apellido, correo);
        this.rol = rol;
    }
}
