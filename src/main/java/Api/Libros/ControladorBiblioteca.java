package Api.Libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Biblioteca")
public class ControladorBiblioteca {

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @PostMapping(path = "/Agregar")
    public ResponseEntity<Biblioteca> guardarBiblioteca(@Valid @RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaGuardada = bibliotecaRepository.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
    }

    @GetMapping(path = "/MostrarTodas")
    public ResponseEntity<List<Biblioteca>> mostrarBibliotecas(){
        return ResponseEntity.ok(bibliotecaRepository.findAll());
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);

        if (bibliotecaOptional.isPresent()){
            System.out.printf("No se encuentra esta Biblioteca");
            return ResponseEntity.unprocessableEntity().build();
        }
        bibliotecaRepository.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();
    }

}
