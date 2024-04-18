package ru.antonovmikhail.book.service;

import ru.antonovmikhail.book.model.Book;
import ru.antonovmikhail.book.model.dto.BookDtoIn;
import ru.antonovmikhail.book.model.dto.BookDtoOut;
import ru.antonovmikhail.book.model.dto.NewBookDto;

import java.util.List;
import java.util.UUID;

public interface BookService {


    BookDtoOut getBookById(UUID id);

    BookDtoOut saveNewBook(NewBookDto dto);

    BookDtoOut update(BookDtoIn dtoIn);

    List<BookDtoOut> findAll(Integer from, Integer size, String sortBy);

    BookDtoOut delete(UUID id);
}
