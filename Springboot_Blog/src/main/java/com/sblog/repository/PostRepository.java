package com.sblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	

}
