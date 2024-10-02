package com.bcb.core.domain;

import com.bcb.core.exception.BCBException;
import com.bcb.core.persistence.model.UserEntity;
import com.bcb.core.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    public String attemptLogin() {
        UserEntity user = repository.findByUsernameAndPassword("fulano@mail.com", "senha123");

        if (user == null) {
            throw new BCBException("Invalid username or password.", HttpStatus.FORBIDDEN);
        }

        return user.getCustomer().getPhone();
    }
}
