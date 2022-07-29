package Api.Libros;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private int id;
    @NotNull
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_biblioteca")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biblioteca biblioteca;

    public Libro() {
    }

    public double Detalles(double alquiler ){
        double porcentaje = alquiler*0.03;
        double costo = alquiler-porcentaje;
        return costo;
    }

    public double Porcentaje(double porcentaje){
        double alquiler = 0;
        porcentaje = alquiler*0.03;
        return porcentaje;
    }

    public  String Detalles(String nombreCompleto,String direccion){
        return "Quien Alquila: "+ nombreCompleto+ ", Direccion: " + direccion ;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
