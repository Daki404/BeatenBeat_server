package daki.BeatenBeat.service;

import daki.BeatenBeat.domain.user.Image;
import daki.BeatenBeat.domain.user.User;
import daki.BeatenBeat.dto.UploadResponseDTO;
import daki.BeatenBeat.dto.UserResponseDTO;
import daki.BeatenBeat.external.aws.s3.S3Service;
import daki.BeatenBeat.repository.ImageRepository;
import daki.BeatenBeat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Autowired
    private S3Service s3Service;

    @Transactional(readOnly = true)
    public UserResponseDTO getUserInfo(User user) {
        Optional<Image> userImage =  imageRepository.findByUser(user);
        if (userImage.isPresent()) {
            return UserResponseDTO.toDTO(user, userImage.get().getUrl());
        }
        return UserResponseDTO.toDTO(user, null);
    }

    @Transactional
    public UploadResponseDTO uploadProfileImage(MultipartFile file, User user) {
        try {
            String imageUrl = s3Service.saveImage(file, user);

            Optional<Image> existingImage = imageRepository.findByUser(user);
            if (existingImage.isPresent()) {
                Image image = existingImage.get();
                image.setImageUrl(imageUrl);
                imageRepository.save(image);
            } else {
                // 이미지가 없는 경우 새로운 이미지를 추가합니다.
                Image newImage = new Image(user, imageUrl);
                imageRepository.save(newImage);
            }

            return UploadResponseDTO.toDto(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return UploadResponseDTO.toDto(false);
    }
}