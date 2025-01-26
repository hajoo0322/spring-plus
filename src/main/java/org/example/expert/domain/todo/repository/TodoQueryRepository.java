package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.expert.domain.todo.entity.QTodo.todo;

@Component
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Todo findByIdWithUser(long id) {
        return jpaQueryFactory.selectFrom(todo)
                .join(todo.user)
                .where(todo.id.eq(id))
                .fetchOne();
    }
}
