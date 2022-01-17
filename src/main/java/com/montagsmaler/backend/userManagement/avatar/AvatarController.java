package com.montagsmaler.backend.userManagement.avatar;

import com.montagsmaler.backend.userManagement.avatar.AvatarService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class AvatarController {
    static final String AVATAR_ROOT_MAPPING = "/avatar/";
    static final String PATH_TO_IMAGE_FOLDER = "image/";

    @Resource
    private AvatarService avatarService;

    @GetMapping(value = "/avatar")
    public Map<String,String> getAllAvatars(){
        return avatarService.getAvatarToImageMap(AVATAR_ROOT_MAPPING + PATH_TO_IMAGE_FOLDER);
    }

    @RequestMapping(value = AVATAR_ROOT_MAPPING +PATH_TO_IMAGE_FOLDER+"{imageName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageName)  {
        try {
            var imgFile = new ClassPathResource(PATH_TO_IMAGE_FOLDER + imageName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }
}
