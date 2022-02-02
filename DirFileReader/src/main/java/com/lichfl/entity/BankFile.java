package com.lichfl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "axis_dep_files")
public class BankFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Column(name = "file_name")
	String fileName;

	@Column(name = "file_size")
	Long fileSize;

	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="sent_msg")
	private Character sentMsg;

}
