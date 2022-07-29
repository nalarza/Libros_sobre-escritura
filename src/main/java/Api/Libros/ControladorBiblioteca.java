package Api.Libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Biblioteca")
public class ControladorBiblioteca {

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @PostMapping(path = "/AgregarBiblioteca")
    public ResponseEntity<Biblioteca> guardarBiblioteca(@Valid @RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaGuardada = bibliotecaRepository.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
    }

    @GetMapping(path = "/MostrarTodasLasBibliotecas")
    public ResponseEntity<List<Biblioteca>> mostrarBibliotecas(){
        return ResponseEntity.ok(bibliotecaRepository.findAll());
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);
        bibliotecaRepository.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();
    }



    @Autowired
    LibroRepository libroRepository;
    @PostMapping(path = "/AgregarLibro")
    public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro){
       try{
           Libro libroGuardado = libroRepository.save(libro);
           URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                   .buildAndExpand(libroGuardado.getId()).toUri();
           return ResponseEntity.created(ubicacion).body(libroGuardado);
        }catch (Exception e){
            return new ResponseEntity("LA BIBLIOTECA NO SE ENCUENTRA : "+e, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/MostrarLibros")
    public ResponseEntity<List<Libro>> mostarLibros(){
        return ResponseEntity.ok(libroRepository.findAll());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Libro> traerLibroId (@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepository.findById(id);
        return ResponseEntity.ok(libroOptional.get());
    }

    @GetMapping(path = "/librosVip")
    public List<Object>  librosVip(@RequestParam double costoAlquiler, @RequestParam int idLibro,
                                   @RequestParam int idPersona, @RequestParam int idBiblioteca) {
        LibroVip vip = new LibroVip();
        double valor;
        String vips;
        double porcentaje;
        Optional<Libro> libroOptional = libroRepository.findById(idLibro);
        Optional<Persona> personaOptional = personaRepository.findById(idPersona);
        List<Persona> persona = personaRepository.findAll();
        List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
        double costo = costoAlquiler;
        valor = vip.Detalles(costoAlquiler);
        try {
            if (persona.size() >= 0) {
                vips = vip.Detalles(persona.get(idPersona - 1).getNombre(), persona.get(idPersona - 1).getDireccion());
                porcentaje = vip.Porcentaje(costoAlquiler);
                if (bibliotecas.size() >= 0) {
                    String biblioteca = bibliotecas.get(idBiblioteca - 1).getNombre();
                    return Arrays.asList(biblioteca, libroOptional, personaOptional, vips, "Valor Del Alquiler: " + costo, "Valor A La Biblioteca: " + valor,
                            "Porcentaje De La Biblioteca: " + porcentaje);
                }
            }
        } catch (Exception e) {
            return Arrays.asList("La Biblioteca, La Persona O El lIbro No Existen "+e);
        }

        return Arrays.asList();
    }

    @Autowired
    PersonaRepository personaRepository;

    @PostMapping(path = "/Agreagar/personas")
    public ResponseEntity<Persona> guardarPersonas(@Valid @RequestBody Persona persona){
        Persona PersonaGuardada = personaRepository.save(persona);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(PersonaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(PersonaGuardada);
    }
}
