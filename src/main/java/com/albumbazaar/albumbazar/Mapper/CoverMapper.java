package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.model.Cover;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoverMapper {

    Cover coverDTOToCover(CoverDTO coverDTO);

    CoverDTO coverTCoverDTO(Cover cover);

}
