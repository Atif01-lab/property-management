package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  //to make the class a singleton
//instead of @service, we can also use @component, @repository, @configuration
public class PropertyServiceImpl implements PropertyService {

    //injected Repository layer in Service layer.
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        return propertyDTO ;
    }

    // getAllProperties returns a list of all properties
    public List<PropertyDTO> getAllProperties(){
        //list containing all properties as an entity
       List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        //creating a new list that will contain all properties as DTO
        List<PropertyDTO> propList = new ArrayList<>();

       //converting entity to DTO
       for (PropertyEntity pe : listOfProps) {
           PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
           propList.add(dto);
       }

        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        //findAllById returns Optional, which does a null check.
        Optional<PropertyEntity> optEn = propertyRepository.findAllById(propertyId);

        PropertyDTO dto = null;
        if(optEn.isPresent()){  //if that data is present in db
            PropertyEntity pe = optEn.get();//get data from the DB
            //override the data and convert dto to entity
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwner(propertyDTO.getOwner());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());

            propertyRepository.save(pe);//save the updated entity
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findAllById(propertyId);

        PropertyDTO dto = null;
        if(optEn.isPresent()){  //if that data is present in db
            PropertyEntity pe = optEn.get();//get data from the DB
            //override the data and convert dto to entity
            pe.setDescription(propertyDTO.getDescription());

            propertyRepository.save(pe);//save the updated entity
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findAllById(propertyId);

        PropertyDTO dto = null;
        if(optEn.isPresent()){  //if that data is present in db
            PropertyEntity pe = optEn.get();//get data from the DB
            //override the data and convert dto to entity
            pe.setPrice(propertyDTO.getPrice());
            propertyRepository.save(pe);//save the updated entity
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);

    }
}

