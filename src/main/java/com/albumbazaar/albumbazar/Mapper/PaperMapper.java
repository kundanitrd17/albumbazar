package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.PaperDTO;
import com.albumbazaar.albumbazar.model.Paper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaperMapper {

    Paper paperDTOToPaperEntity(PaperDTO paperDTO);

    PaperDTO paperEntityToPaperDTO(Paper paper);

}
