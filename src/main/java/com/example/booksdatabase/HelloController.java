package com.example.booksdatabase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Book;

import java.util.Arrays;
import java.util.HashSet;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button connectToDBButton;

    @FXML
    private Button saveToFileButton;
    @FXML
    private TableView booksTableView;

    private HashSet<Book> books = new HashSet<Book>();

    ObservableList<Book> observableBooks = FXCollections.observableArrayList();



    @FXML
    protected void onHelloButtonClick () {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    @FXML
    protected void onConnectToDBButtonClick () {
        Connection conn =  connectToDB();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT isbn, title, year FROM book ORDER BY title;");
            int c = 0;
            while (rs.next()) {
                c++;
                System.out.println(c+"\t"+rs.getString("isbn")+"\t"+rs.getString("title")+"\t"+rs.getString("year"));
                //Book b = new Book();
                SimpleIntegerProperty isbn = new SimpleIntegerProperty();
                isbn.set(Integer.parseInt(rs.getString(1)));
                SimpleStringProperty title = new SimpleStringProperty();
                title.set(rs.getString(2));
                SimpleIntegerProperty year = new SimpleIntegerProperty();
                year.set(Integer.parseInt(rs.getString(3)));

                books.add(new Book(isbn,title,year));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("не удалось подключиться к базе. "+e.getMessage());
        }

        initTable();
    }

    public void initTable() {
        booksTableView.getColumns().clear();
        TableColumn<Book, Integer> columnISBN = new TableColumn<>("isbn");
        columnISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        columnISBN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Book, String> columnTitle = new TableColumn<>("title");
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, Integer> columnYear = new TableColumn<>("year");
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        booksTableView.getColumns().add(columnISBN);
        booksTableView.getColumns().add(columnTitle);
        booksTableView.getColumns().add(columnYear);


        observableBooks.addAll(books.stream().toList());
        booksTableView.setItems(observableBooks);
        booksTableView.setEditable(true);

    }

    private static Connection connectToDB() {
        String url = "jdbc:postgresql://10.10.104.166:5432/Lib?user=postgres&password=123";//&ssl=true
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("подключено");
            return conn;
        } catch (Exception e) {
            System.out.println("не удалось подключиться к базе. "+e.getMessage());
            return null;
        }
    }




}