package com.project_user.controller;

import com.project_user.entity.ApplicationUser;
import com.project_user.repository.ApplicationUserRepository;
import com.project_user.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> findAll() throws Exception {
        return this.applicationUserService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> findById(@PathVariable("id") Long id) throws Exception {
        return this.applicationUserService.findById(id);
    }


    @PostMapping
    public ResponseEntity<ApplicationUser> createUser(@RequestBody ApplicationUser user) throws Exception{
        return this.applicationUserService.save(user);
    }

    @PutMapping
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser user) throws Exception{
        return this.applicationUserService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApplicationUser> deleteUser(@PathVariable("id") Long id) throws Exception{
        return this.applicationUserService.delete(id);
    }

    @PostMapping("/{id}/file")
    public ResponseEntity<ApplicationUser> uploadImgaeToDB(@RequestParam("image") MultipartFile image, @PathVariable("id") Long id) throws IOException {
        byte[] imageArr = image.getBytes();

        String imageAsString= Base64.encodeBase64String(imageArr);

        return this.applicationUserService.addFile(imageAsString, id);

    }

}
