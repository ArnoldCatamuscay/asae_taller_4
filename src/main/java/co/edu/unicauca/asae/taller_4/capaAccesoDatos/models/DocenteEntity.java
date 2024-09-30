package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
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

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idOficina")
    private OficinaEntity objOficina;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_docente", 
        joinColumns = @JoinColumn(name = "idDocente"), 
        inverseJoinColumns = @JoinColumn(name = "idCurso")
    )
    private Set<CursoEntity> cursos;

    public DocenteEntity(int idPersona, String nombre, String apellido, String correo) {
        super(idPersona, nombre, apellido, correo);
        this.cursos = new HashSet<CursoEntity>();
    }

    public void addCurso(CursoEntity curso) {
        this.cursos.add(curso);
        curso.getDocentes().add(this);
    }

    public void removeCurso(CursoEntity curso) {
        this.cursos.remove(curso);
        curso.getDocentes().remove(this);
    }
}
