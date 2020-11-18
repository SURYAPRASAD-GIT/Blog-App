package com.sblog.model;

import java.time.Instant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table
@Data 
public class Post {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column
	private String title;
	@Lob
    @Column
    @NotEmpty
	private String content;
	@Column
	private Instant createdOn;
	@Column
    private Instant updatedOn;
	@Column
	@NotBlank
    private String username;
}
