package com.cg.spblaguna.model;

import com.cg.spblaguna.model.dto.res.ImageResDTO;
import com.cg.spblaguna.model.enumeration.EImageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String fileType;
    private String cloudId;

    @Column(name = "image_type")
    @Enumerated(EnumType.STRING)
    private EImageType imageType;

    @OneToOne
    @JoinColumn(name = "receptionist_avatar", unique = false)
    private Receptionist receptionistAvatar;

    @ManyToOne
    @JoinColumn(name = "room_image", unique = false)
    private Room roomImage;

    public ImageResDTO toImageResDTO(){
        return new ImageResDTO(this.id, this.fileUrl);
    }
}