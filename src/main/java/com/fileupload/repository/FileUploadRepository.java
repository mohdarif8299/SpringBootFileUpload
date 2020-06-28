package com.fileupload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.entities.FileModel;

public interface FileUploadRepository extends JpaRepository<FileModel, Long> {
	Optional<FileModel> findByName(String name);
}
