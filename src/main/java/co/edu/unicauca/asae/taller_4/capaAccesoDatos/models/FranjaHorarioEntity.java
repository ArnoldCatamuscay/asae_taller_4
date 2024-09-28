package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FranjasHorarias")
public class FranjaHorarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 20)
    String dia;
    LocalTime horaInicio;
    LocalTime horaFin;

    //* Relaciones */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCurso", nullable = false)
	private CursoEntity objCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEspacioFisico", nullable = false)
	private EspacioFisicoEntity objEspacioFisico;

}
