package com.cg.spblaguna.service.image;

import com.cg.spblaguna.model.Image;
import com.cg.spblaguna.model.Room;
import com.cg.spblaguna.model.dto.req.ImageReqDTO;
import com.cg.spblaguna.model.dto.res.ImageResDTO;
import com.cg.spblaguna.model.enumeration.EImageType;
import com.cg.spblaguna.repository.IImageRepository;
import com.cg.spblaguna.util.UploadUtil;
import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ImageService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    private IImageRepository iImageRepository;


    public ImageResDTO saveImage(MultipartFile fileImage) throws IOException {
        var file = new Image();
        iImageRepository.save(file);

        var uploadResult = cloudinary.uploader().upload(fileImage.getBytes(), uploadUtil.buildImageUpLoadParams(file));
        String fileUrl = (String) uploadResult.get("secure_url");
        String fileFormat = (String) uploadResult.get("format");
        file.setFileType(fileFormat);
        file.setFileName(file.getId() + "." + fileFormat);
        file.setFileUrl(fileUrl);
        file.setFileFolder(UploadUtil.IMAGE_UPLOAD_FOLDER);
        file.setCloudId(file.getFileFolder() + "/" + file.getId());
        iImageRepository.save(file);


        return file.toImageResDTO();

    }

    public void deleteImage(String imageID) {
        iImageRepository.deleteById(imageID);
    }
}
