package com.tnt.onlinestore.validators;

import com.tnt.onlinestore.entities.UserEntity;
import com.tnt.onlinestore.exceptions.responseEntityExceptions.InvalidEntryException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateUserName(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() || userEntity.getName().getBytes().length < 2) {
            throw new InvalidEntryException("A user requires a name of at least 2 characters");
        }
    }

    public void validateUserEmail(UserEntity userEntity) {
        if (userEntity.getEmail().isEmpty() || userEntity.getEmail().getBytes().length < 6) {
            throw new InvalidEntryException("Invalid email");
        }
    }

    public void validateUserPassword(UserEntity userEntity) {
        if (userEntity.getPassword().isEmpty() || userEntity.getPassword().getBytes().length < 3) {
            throw new InvalidEntryException("Password must be at least 3 chars in length");
        }
    }

    public void validateUserAddress(UserEntity userEntity) {
        if (userEntity.getAddress().isEmpty() || userEntity.getAddress().getBytes().length < 4) {
            throw new InvalidEntryException("Address must be at least 4 characters (don't ask why we chose that...)");
        }
    }

    public void validateNameString(String name) {
        if (name == null || name.getBytes().length < 2) {
            throw new InvalidEntryException("Name must be at least 2 characters long");
        }
    }

    public void validatePasswordString(String password) {
        if (password == null || password.getBytes().length < 3) {
            throw new InvalidEntryException("Password must be at least 3 characters long");
        }
    }

    public void validateAddressString(String address) {
        if (address == null || address.getBytes().length < 4) {
            throw new InvalidEntryException("Address must be at least 4 characters long");
        }
    }

}
