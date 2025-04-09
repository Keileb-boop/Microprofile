package sv.edu.udb.investigacion2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.investigacion2.models.Usuario;
import sv.edu.udb.investigacion2.repository.UsuarioRepository;
import sv.edu.udb.investigacion2.controller.UsuarioController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsuarioRepositoryIntegrationTest {

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
}
