package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//abstraction
//interface is used to achieve abstraction, its methods does not have any bodies/implementation
public interface PropertyService {
    public PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();
    //Updating a property
    //updateProperty is a method that takes propertyDTO object and propertyId to return the updates PropertyDTO
     PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

     PropertyDTO updatePropertyDescription(@RequestBody PropertyDTO propertyDTO,Long propertyId);
     PropertyDTO updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, Long propertyId);

     void deleteProperty (Long propertyId);//for deleting we don't need anything to return, and we need only propertyId
}
