package com.example.clinica.service;

import com.example.clinica.exceptions.BadRequestException;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Test
    public void a_testCrearTurno() throws BadRequestException {

        Turno turno = new Turno();

        Paciente paciente = new Paciente();
        paciente.setNombre("rodrigo");
        paciente.setApellido("cabrera");
        paciente.setEmail("rodri@gmail.com");
        paciente.setDni(45613);
        pacienteService.guardar(paciente);

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("odon1");
        odontologo.setApellido("apeOdon1");
        odontologo.setMatricula("12A");
        odontologoService.guardar(odontologo);

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.parse("2022-02-02"));

        turnoService.guardar(turno);
        Optional<Turno> turno1 = turnoService.buscar(1L);

        assertTrue(turno1 != null);
    }

    @Test
    public void c_eliminarTurnoTest() throws ResourceNotFoundException{
        turnoService.eliminar(1L);
        Assertions.assertFalse(turnoService.buscar(1L).isPresent());
    }
    @Test
    public void b_buscarTurnoTest(){
        Assertions.assertNotNull(turnoService.buscar(1L));
    }
    @Test
    public void d_listarTurnoTest() {
        Assertions.assertNotNull(turnoService.listarTurnos());
    }


}