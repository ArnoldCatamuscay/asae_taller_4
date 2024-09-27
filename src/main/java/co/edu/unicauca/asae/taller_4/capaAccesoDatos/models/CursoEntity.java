package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cursos")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 255)
    String nombre;

    //* Relaciones */
    @ManyToOne
    @JoinColumn(name = "idAsignatura", nullable = false)
	private AsignaturaEntity objAsignatura;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objCurso")
	private List<FranjaHorarioEntity> franjasHorario;
}
