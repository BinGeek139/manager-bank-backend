package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;


}
