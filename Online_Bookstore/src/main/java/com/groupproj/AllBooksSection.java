package com.groupproj;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.*;
import java.util.List;

public class AllBooksSection {

    public static VBox create(Connection conn, List<Book> cart, BookstoreApp app) {
        VBox section = new VBox(20);

        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("All Books");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setStyle("-fx-text-fill: white;");

        header.getChildren().add(title);

        FlowPane booksGrid = new FlowPane(20, 20);
        booksGrid.setPrefWrapLength(1200);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id, title, author, price, " +
                            "COALESCE(image_url, '') as image_url " +
                            "FROM books"
            );

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                );
                booksGrid.getChildren().add(BookCard.create(book, cart, app));
            }
        } catch (Exception e) {
            Utilities.showAlert("Error", "Failed to load books: " + e.getMessage());
            e.printStackTrace();
        }

        section.getChildren().addAll(header, booksGrid);
        return section;
    }
}