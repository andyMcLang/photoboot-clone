
package com.andylang.photoz_clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotozController {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "1.jpg"));
        put("2", new Photo("2", "2.jpg"));
        put("3", new Photo("3", "3.jpg"));
    }};

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("DELETE request received for id: " + id);
        System.out.println("Current DB content: " + db);

        Photo photo = db.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        System.out.println("Photo deleted: " + photo.getFileName());
        System.out.println("Remaining photos: " + db.size());
        if (db.isEmpty()) System.out.println("No more photos");
        else System.out.println("Remaining photos: " + db.keySet());
        System.out.println();

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        db.put(photo.getId(), photo);
        return photo;
    }
}
