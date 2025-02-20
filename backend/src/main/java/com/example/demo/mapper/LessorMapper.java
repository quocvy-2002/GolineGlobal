package com.example.demo.mapper;

import com.example.demo.dto.request.CreateLessorRequest;
import com.example.demo.dto.request.UpdateLessorRequest;
import com.example.demo.dto.response.LessorResponse;
import com.example.demo.entity.Lessor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LessorMapper {
    Lessor toLessor(CreateLessorRequest lessor);
    LessorResponse toLessorResponse(Lessor lessor);
    void updateLessor(@MappingTarget Lessor lessor, UpdateLessorRequest request);

}
