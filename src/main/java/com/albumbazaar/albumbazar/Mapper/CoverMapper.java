package com.albumbazaar.albumbazar.Mapper;

import com.albumbazaar.albumbazar.controller.FileUploadController;
import com.albumbazaar.albumbazar.dto.CoverDTO;
import com.albumbazaar.albumbazar.model.Cover;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Mapper(componentModel = "spring")
public interface CoverMapper {

    Cover coverDTOToCover(CoverDTO coverDTO);

    CoverDTO coverTCoverDTO(Cover cover);

}
