package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AssignmentBuildingDTO convertToDto(AssignmentBuildingEntity entity) {
        AssignmentBuildingDTO result = modelMapper.map(entity, AssignmentBuildingDTO.class);
        return result;
    }
    public AssignmentBuildingEntity convertToEntity(AssignmentBuildingDTO dto) {
        AssignmentBuildingEntity result = modelMapper.map(dto, AssignmentBuildingEntity.class);
        return result;
    }
}
