package org.example.contact.dao;

import org.example.contact.dto.ContactDTO;
import org.example.contact.util.ConnectionUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.engine.descriptor.TestInstanceLifecycleUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBServiceImplTest {
    private Connection conn;
    private DBServiceImpl dbService;

    @BeforeAll
    void setup() {

        try {
            Properties prop = new Properties();
            String propFileName = "db.properties";

            InputStream file = ConnectionUtil.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(file);
            file.close();

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            conn = DriverManager.getConnection(url, user, password);
            dbService = new DBServiceImpl();


            String createTableQuery = """
                    CREATE TABLE IF NOT EXISTS contactbook (
                        id SERIAL PRIMARY KEY,
                        cname VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        dob DATE,
                        mobile VARCHAR(20) NOT NULL
                    );
                    """;
            conn.createStatement().execute(createTableQuery);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            }
    }

    @AfterAll
    void tearDown() throws SQLException {

        String dropTableQuery = "DROP TABLE IF EXISTS contactbook";
        conn.createStatement().execute(dropTableQuery);
        conn.close();
    }

    @BeforeEach
    void cleanUp() throws SQLException {

        String deleteAll = "DELETE FROM contactbook";
        conn.createStatement().execute(deleteAll);
    }

    @Test
    void testCreate() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Alex");
        contactDTO.setEmail("alex@example.com");
        contactDTO.setDob("1990-01-01");
        contactDTO.setMobile("1234567890");

        boolean result = dbService.create(conn, contactDTO);
        assertTrue(result);

    }

    @Test
    void testGetAllContacts() {
        ContactDTO contactDTO1 = new ContactDTO();
        contactDTO1.setName("John Doe");
        contactDTO1.setEmail("john.doe@example.com");
        contactDTO1.setDob("1990-01-01");
        contactDTO1.setMobile("1234567890");
        dbService.create(conn, contactDTO1);

        ContactDTO contactDTO2 = new ContactDTO();
        contactDTO2.setName("Jane Smith");
        contactDTO2.setEmail("jane.smith@example.com");
        contactDTO2.setDob("1992-02-02");
        contactDTO2.setMobile("9876543210");
        dbService.create(conn, contactDTO2);

        List<ContactDTO> contacts = dbService.getAllContacts(conn);
        assertEquals(2, contacts.size());
    }

    @Test
    void testUpdate() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Chirag");
        contactDTO.setEmail("chirag@example.com");
        contactDTO.setDob("1992-02-02");
        contactDTO.setMobile("9876543210");

        dbService.create(conn, contactDTO);

        // Update contact
        ContactDTO updatedContactDTO = new ContactDTO();
        updatedContactDTO.setName("Vijay");
        updatedContactDTO.setEmail("vijay@example.com");
        updatedContactDTO.setDob("1992-03-03");
        updatedContactDTO.setMobile("1231231234");

        boolean result = dbService.update(conn, updatedContactDTO, 1);
        assertTrue(result);
//
//        // Verify update
//        ContactDTO updatedContact = dbService.getById(conn, 1);
//        assertEquals("Vijay", updatedContact.getName());
//        assertEquals("vijay@example.com", updatedContact.getEmail());
//        assertEquals("1992-03-03", updatedContact.getDob());
//        assertEquals("1231231234", updatedContact.getMobile());
    }

    @Test
    void testSearch() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Alex");
        contactDTO.setEmail("alex@example.com");
        contactDTO.setDob("1995-03-03");
        contactDTO.setMobile("5555555555");
        dbService.create(conn, contactDTO);

        ContactDTO result = dbService.search(conn, "Alex");
        assertNotNull(result);
        assertEquals("Alex", result.getName());
    }

    @Test
    void testDelete() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("John Doe");
        contactDTO.setEmail("john.doe@example.com");
        contactDTO.setDob("1990-01-01");
        contactDTO.setMobile("1234567890");
        dbService.create(conn, contactDTO);

        boolean result = dbService.delete(conn, 1);
        assertTrue(result);

        // Verify deletion
        ContactDTO deletedContact = dbService.getById(conn, 1);
        assertNull(deletedContact.getName());
    }

    @Test
    void testGetById() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Alice Smith");
        contactDTO.setEmail("alice.smith@example.com");
        contactDTO.setDob("1995-03-03");
        contactDTO.setMobile("5555555555");
        dbService.create(conn, contactDTO);

        ContactDTO retrievedContact = dbService.getById(conn, 1);
        assertEquals("Alice Smith", retrievedContact.getName());
    }
}