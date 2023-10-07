package com.munsal.java21demo.mapper;

import com.munsal.java21demo.domain.entity.BookEntity;
import com.munsal.java21demo.domain.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto toBookDto(BookEntity entity);

    BookEntity toBookEntity(BookDto dto);
}