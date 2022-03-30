package com.youngadessi.demo.post.api.post;

import com.youngadessi.demo.post.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query( value = "SELECT * FROM post_schema.tbl_post WHERE created_date_time > cast(now() as DATE) - 50  OFFSET :1 ROWS FETCH NEXT :pageSize ROWS ONLY", nativeQuery = true)
    public List<Post> findLastFiveDays(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

}
