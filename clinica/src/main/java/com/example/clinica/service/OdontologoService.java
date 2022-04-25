package com.example.clinica.service;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import com.example.clinica.repository.OdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {
    @Autowired
    OdontologoRepository repository;
    Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    public List<Odontologo> buscarOdontologos(){
        return repository.findAll();
    }

    public Optional<Odontologo> buscar(Long id){
        return repository.findById(id);
    }

    public void borrar(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=buscar(id);
        if (odontologoBuscado.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el odontologo con el id= "+id+". Ingresar un id correcto");
    }

    public Odontologo guardar(Odontologo odontologo){

        LOGGER.info("se agrego un nuevo odontologo");
        return repository.save(odontologo);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        if(buscar(odontologo.getId()).isPresent())
            return repository.save(odontologo);
        else
            return null;
    }
}
