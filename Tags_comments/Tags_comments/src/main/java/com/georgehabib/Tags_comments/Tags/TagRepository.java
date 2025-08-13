package com.georgehabib.Tags_comments.Tags;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tags, Integer> {

    Optional<Tags> findByName(String name);

    boolean existsByName(String name);

}
