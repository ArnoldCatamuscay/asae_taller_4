package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "Cursos")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 255, nullable = false)
    String nombre;

    //* Relaciones */
    @ManyToOne
    @JoinColumn(name = "idAsignatura", nullable = false)
	private AsignaturaEntity objAsignatura;

    @OneToMany(cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER, mappedBy = "objCurso")
	private List<FranjaHorarioEntity> franjasHorario;

    @ManyToMany(mappedBy = "cursos")
    private Set<DocenteEntity> docentes;

    public CursoEntity() {
        this.franjasHorario = new ArrayList<FranjaHorarioEntity>();
        this.docentes = new HashSet<DocenteEntity>();
    }
}
