package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    public void a_testCrearOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("odon1");
        odontologo.setApellido("apeOdon1");
        odontologo.setMatricula("12A");

        odontologoService.guardar(odontologo);
        Optional<Odontologo> odontologo1 = odontologoService.buscar(1L);

        assertTrue(odontologo1 != null);
    }
    @Test
    public void b_buscarOdontologoTest(){
        Assertions.assertNotNull(odontologoService.buscar(1L));
    }
    @Test
    public void c_listarOdontologosTest() {
        Assertions.assertNotNull(odontologoService.buscarOdontologos());
    }
    @Test
    public void c_eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.borrar(1L);
        Assertions.assertFalse(odontologoService.buscar(1L).isPresent());
    }
}