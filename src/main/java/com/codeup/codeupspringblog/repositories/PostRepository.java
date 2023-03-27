package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);

    Post findPostByTitle(String title);

    Post getPostById(long id);

    Post findPostByUserId(long id);

}
