package com.mycompany.propertymanagement.service;


import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;
import org.springframework.stereotype.Component;

public interface UserService {
    //anything in interface is public by default

   UserDTO register (UserDTO userDTO);
   UserDTO login (String email, String password);
}
