package sv.edu.udb.investigacion2.controller;

import sv.edu.udb.investigacion2.models.Usuario;
import sv.edu.udb.investigacion2.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Lista todos los usuarios")
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Obtiene un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un nuevo usuario")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @Operation(summary = "Actualiza un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioActualizado.setId(id);
                    Usuario usuarioGuardado = usuarioRepository.save(usuarioActualizado);
                    return ResponseEntity.ok(usuarioGuardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un usuario por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualiza parcialmente un usuario")
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioParcial(@PathVariable Long id, @RequestBody Usuario usuarioParcial) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    if (usuarioParcial.getNombre() != null) {
                        usuario.setNombre(usuarioParcial.getNombre());
                    }
                    if (usuarioParcial.getEmail() != null) {
                        usuario.setEmail(usuarioParcial.getEmail());
                    }
                    Usuario usuarioGuardado = usuarioRepository.save(usuario);
                    return ResponseEntity.ok(usuarioGuardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
