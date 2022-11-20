package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import com.mycompany.propertymanagement.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel(); //create an obj of ErrorModel
            errorModel.setCode("INVALID_REGISTER");
            errorModel.setMessage("User already registered");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }else {
            UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
            Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(userEntity.getOwnerEmail(),userEntity.getPassword());
            userEntity = userRepository.save(userEntity);
            userDTO = userConverter.convertEntityToDTO(userEntity);
        }
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if(optionalUserEntity.isPresent()){
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }else{
            //create a list of error model
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel(); //create an obj of ErrorModel
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("User not found");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);

        }
        return userDTO;

    }
}
