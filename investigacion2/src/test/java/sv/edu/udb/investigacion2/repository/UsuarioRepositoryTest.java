package sv.edu.udb.investigacion2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.investigacion2.models.Usuario;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("Test Usuario");
        usuario.setEmail("test@example.com");

        // Act
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Assert
        assertNotNull(usuarioGuardado.getId());
        assertEquals("Test Usuario", usuarioGuardado.getNombre());
        assertEquals("test@example.com", usuarioGuardado.getEmail());
    }

    @Test
    public void testObtenerUsuarioPorId() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("Test Usuario");
        usuario.setEmail("test@example.com");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Act
        Usuario usuarioObtenido = usuarioRepository.findById(usuarioGuardado.getId()).orElse(null);

        // Assert
        assertNotNull(usuarioObtenido);
        assertEquals("Test usuario", usuarioObtenido.getNombre());
    }
}