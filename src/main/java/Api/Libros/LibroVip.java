package Api.Libros;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "libro_vip")
public class LibroVip extends Libro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro_vip")
    private int id;
    @NotNull
    private String nombre;

}
