package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "curso_docente", joinColumns = @JoinColumn(name = "idDocente"), inverseJoinColumns = @JoinColumn(name = "idCurso"))
    private List<CursoEntity> cursos;

    public DocenteEntity(int idPersona, String nombre, String apellido, String correo) {
        super(idPersona, nombre, apellido, correo);
    }
}
