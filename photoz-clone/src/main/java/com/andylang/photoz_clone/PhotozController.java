
package com.andylang.photoz_clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }


    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photozService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("DELETE request received for id: " + id);
        System.out.println("Current DB content: " + photozService);

        Photo photo = photozService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        System.out.println("Photo removed: " + photo);
        System.out.println("Current DB content: " + photozService);

    }

    @PostMapping("/photoz")
    @CrossOrigin(origins = "http://localhost:63342")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photozService.save(file.getOriginalFilename(), file.getBytes());
        return photo;
    }
}
