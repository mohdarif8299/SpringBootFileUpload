package com.fileupload.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "file_type")
	private String type;
	@Column(name = "file")
	private String file;

	public FileModel() {
		super();
	}

	public FileModel(String fileName, String type, String file) {
		super();
		this.name = fileName;
		this.type = type;
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setId(String fileName) {
		this.name = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
