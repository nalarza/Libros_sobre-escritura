package Api.Libros;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="biblioteca")
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biblioteca")
    private int id;
    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "biblioteca",cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public Biblioteca() {
    }

    public Biblioteca(int id, String nombre, Set<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.libros = libros;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for (Libro libro : libros){
            libro.setBiblioteca(this);
        }
    }

}
