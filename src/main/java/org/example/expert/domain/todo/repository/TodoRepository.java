package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(""" 
            SELECT t FROM Todo t WHERE (:weather is null or t.weather = :weather)
                        and (:startTime is null or t.modifiedAt >= :startTime) 
                        and (:endTime is null or t.modifiedAt <= :endTime)
                         ORDER BY t.modifiedAt DESC""")
    Page<Todo> findAllByOrderByModifiedAtDesc(
            @Param("weather")String weather,
            @Param("startTime")String startTime,
            @Param("endTime")String endTime,
            Pageable pageable);


    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
