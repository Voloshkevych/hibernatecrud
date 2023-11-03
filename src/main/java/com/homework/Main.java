package com.homework;

import com.homework.entity.Author;
import com.homework.entity.Book;
import com.homework.service.AuthorService;
import com.homework.service.BookService;

public class Main {

  public static void main(String[] args) {
    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();

    Author newAuthor = new Author();
    newAuthor.setName("Nazar Struk");
    newAuthor.setNationality("Ukrainian");
    Author savedAuthor = authorService.createAuthor(newAuthor);

    Book newBook = new Book();
    newBook.setTitle("Hibernate Intro");
    newBook.setIsbn("123456789");
    newBook.setAuthor(savedAuthor);

    Book savedBook = bookService.createBook(newBook);
    System.out.println("Book saved: " + savedBook);

    Book retrievedBook = bookService.getBook(savedBook.getId());
    System.out.println("Book retrieved: " + retrievedBook);

    retrievedBook.setTitle("Hibernate Intro Updated");
    Book updatedBook = bookService.updateBook(retrievedBook);
    System.out.println("Book updated: " + updatedBook);

    System.out.println("Listing all books:");
    bookService.getAllBooks().forEach(book -> System.out.println(book));

    bookService.deleteBook(updatedBook);
    System.out.println("Book deleted.");

    bookService.shutdown();
    authorService.shutdown();
  }
}