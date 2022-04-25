package com.example.clinica.security;

import com.example.clinica.model.AppUser;
import com.example.clinica.model.AppUsuariosRoles;
import com.example.clinica.repository.UserRepository;
import com.example.clinica.service.OdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password1 = passwordEncoder.encode("password1");

        userRepository.save(new AppUser("rodri", "rodri", "rodri@gmail.com",password, AppUsuariosRoles.ROLE_ADMIN));
        logger.info("Usuario admin creado");
        userRepository.save(new AppUser("juan", "juan", "juan@gmail.com",password1, AppUsuariosRoles.ROLE_USER));
        logger.info("Usuario user creado");
    }
}
