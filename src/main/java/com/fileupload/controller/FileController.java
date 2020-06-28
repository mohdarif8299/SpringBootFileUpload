package com.fileupload.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.entities.FileModel;
import com.fileupload.repository.FileUploadRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class FileController {

	@Autowired
	FileUploadRepository fileUploadRepository;

	Resource resource = new ClassPathResource("/public/");

	@PostMapping("/upload")
	public String uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Path filepath = Paths.get(resource.getFile().getPath(), file.getOriginalFilename());
		file.transferTo(filepath);
		String imageURL = "http://localhost:8080/" + file.getOriginalFilename();
		System.out.println(imageURL);
		FileModel fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), imageURL);
		fileUploadRepository.save(fileModel);
		return "success";
	}

	@GetMapping(path = { "/get/{imageName}" })
	public ResponseEntity<String> getImage(@PathVariable("imageName") String imageName) throws IOException {
		final Optional<FileModel> retrievedImage = fileUploadRepository.findByName(imageName);
		FileModel fm = retrievedImage.get();
		return new ResponseEntity<String>(fm.getFile(), HttpStatus.OK);
	}
}
