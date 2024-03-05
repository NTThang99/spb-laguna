package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Image;
import com.cg.spblaguna.model.dto.req.ImageReqDTO;
import com.cg.spblaguna.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageAPI {
    @Autowired
    private ImageService imageService;
    @PostMapping
    public Image upload(ImageReqDTO imageReqDTO) throws IOException {
    return imageService.saveImage(imageReqDTO);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        imageService.deleteImage(id);
    }
}
