package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.PositionDTO;

import com.ptit.managerbank.model.Position;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper extends  EntityMapper<PositionDTO, Position> {
}
