package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Domicilio;
import com.example.clinica.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;

    @Test
        public void a_testCrearPaciente(){
        Paciente paciente = new Paciente();
        paciente.setNombre("rodrigo");
        paciente.setApellido("cabrera");
        paciente.setEmail("rodri@gmail.com");
        paciente.setDni(45613);

        Domicilio domicilio=new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setNumero(12);
        domicilio.setLocalidad("paris");
        domicilio.setProvincia("mendoza");
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);
        Optional<Paciente> paciente1 = pacienteService.buscar(1L);

        assertTrue(paciente1 != null);
        }
    @Test
    public void b_buscarPacienteTest(){
        Assertions.assertNotNull(pacienteService.buscar(1L));
    }
    @Test
    public void c_listarPacienteTest() {
        Assertions.assertNotNull(pacienteService.listarPacientes());
    }
    @Test
    public void d_eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
        Assertions.assertFalse(pacienteService.buscar(1L).isPresent());
    }
}