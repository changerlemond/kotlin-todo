package server.project.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import server.project.domain.QTodo.todo
import server.project.domain.Todo

class TodoRepositoryCustomImpl(private val queryFactory: JPAQueryFactory): TodoRepositoryCustom {

    override fun findByUserId(userId: Long, pageable: Pageable): Page<Todo> {
        val content = queryFactory.selectFrom(todo)
            .where(todo.user.id.eq(userId))
            .orderBy(todo.createdDateTime.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = queryFactory.selectFrom(todo)
            .where(todo.user.id.eq(userId))

        return PageableExecutionUtils.getPage(content, pageable) { total.fetch().size.toLong() }
    }

    override fun findLatestByUserId(userId: Long): Todo? {
        return queryFactory.select(todo)
            .from(todo)
            .where(todo.user.id.eq(userId))
            .orderBy(todo.createdDateTime.desc())
            .limit(1)
            .fetchOne()
    }

}