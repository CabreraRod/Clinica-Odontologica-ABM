package com.example.clinica.service;

import com.example.clinica.exceptions.BadRequestException;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;
import com.example.clinica.repository.OdontologoRepository;
import com.example.clinica.repository.PacienteRepository;
import com.example.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TurnoService {
    @Autowired
    TurnoRepository repository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    public Turno guardar(Turno turno) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());

        if(paciente.isPresent() && odontologo.isPresent()){
            //entonces, podemos agregar el turno
            return repository.save(turno);
        }else{
            throw new BadRequestException("No se puede registrar el turno porque el paciente o el odontólogo no existen.");
        }
    }

    public List<Turno> listarTurnos(){
        return repository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado= buscar(id);
        if (turnoBuscado.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el turno con el id= "+id+". Ingresar un id correcto");
    }

    public Optional<Turno> buscar(Long id){
        return repository.findById(id);
    }

    public Turno actualizar(Turno turno) {
        if(buscar(turno.getId()).isPresent())
            return repository.save(turno);
        else
            return null;
    }
}
