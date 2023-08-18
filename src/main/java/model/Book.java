package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Book {
    private SimpleIntegerProperty isbn = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleIntegerProperty year = new SimpleIntegerProperty();

    public Book (SimpleIntegerProperty isbn) {
        this.isbn = isbn;
    }

    public Book (SimpleIntegerProperty isbn, SimpleStringProperty title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Book (SimpleIntegerProperty isbn, SimpleStringProperty title, SimpleIntegerProperty year) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
    }





    @Override
    public String toString () {
        return "Book{" +
                "isbn=" + isbn.toString() +
                ", title='" + title.toString() + '\'' +
                ", year=" + year.toString() +
                '}';
    }


    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getIsbn() == book.getIsbn();
    }

    @Override
    public int hashCode () {
        return Objects.hash(getIsbn());
    }

    public SimpleIntegerProperty getIsbn() {
        return this.isbn;
    }
}
