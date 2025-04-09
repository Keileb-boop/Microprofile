package sv.edu.udb.investigacion2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() throws SQLException {
        // Verifica que el DataSource esté configurado
        assertNotNull(dataSource);

        // Intenta obtener una conexión de la base de datos
        try (Connection connection = dataSource.getConnection()) {
            // Verifica que la conexión se haya obtenido correctamente
            assertNotNull(connection);
            // Verifica que la conexión sea válida
            assertTrue(connection.isValid(1));
        }
    }

    @Test
    public void testJdbcTemplate() {
        // Verifica que el JdbcTemplate esté configurado
        assertNotNull(jdbcTemplate);

        // Intenta ejecutar una consulta simple
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

        // Verifica que la consulta se haya ejecutado correctamente
        assertNotNull(result);
        assertEquals(1, result);
    }
}