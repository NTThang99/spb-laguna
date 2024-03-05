package com.cg.spblaguna.model.dto.req;

import com.cg.spblaguna.model.enumeration.EImageType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageReqDTO {
    private EImageType eImageType;
    private MultipartFile image;
}