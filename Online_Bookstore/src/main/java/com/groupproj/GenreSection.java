package com.groupproj;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.*;
import java.util.List;

public class GenreSection {

    public static VBox create(String genre, Connection conn, List<Book> cart, BookstoreApp app) {
        VBox section = new VBox(20);

        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(genre);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: white;");

        header.getChildren().add(title);

        HBox scrollContainer = new HBox();
        scrollContainer.setAlignment(Pos.CENTER_LEFT);

        Button leftArrow = new Button("‹");
        leftArrow.setStyle("-fx-background-color: rgba(255, 31, 143, 0.8); " +
                "-fx-text-fill: white; -fx-font-size: 24px; " +
                "-fx-font-weight: bold; -fx-padding: 15 10; " +
                "-fx-cursor: hand; -fx-background-radius: 5;");
        leftArrow.setPrefHeight(280);

        HBox booksGrid = new HBox(20);
        booksGrid.setAlignment(Pos.CENTER_LEFT);

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT id, title, author, price, " +
                            "COALESCE(image_url, '') as image_url " +
                            "FROM books WHERE genre = ? LIMIT 12"
            );
            pstmt.setString(1, genre);
            ResultSet rs = pstmt.executeQuery();

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
            Utilities.showAlert("Error", "Failed to load books for genre: " + e.getMessage());
            e.printStackTrace();
        }

        ScrollPane scrollPane = new ScrollPane(booksGrid);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefHeight(360);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        Button rightArrow = new Button("›");
        rightArrow.setStyle("-fx-background-color: rgba(255, 31, 143, 0.8); " +
                "-fx-text-fill: white; -fx-font-size: 24px; " +
                "-fx-font-weight: bold; -fx-padding: 15 10; " +
                "-fx-cursor: hand; -fx-background-radius: 5;");
        rightArrow.setPrefHeight(280);

        leftArrow.setOnAction(e -> {
            double currentValue = scrollPane.getHvalue();
            scrollPane.setHvalue(Math.max(0, currentValue - 0.3));
        });

        rightArrow.setOnAction(e -> {
            double currentValue = scrollPane.getHvalue();
            scrollPane.setHvalue(Math.min(1, currentValue + 0.3));
        });

        leftArrow.setOnMouseEntered(e -> leftArrow.setStyle(
                "-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                        "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 15 10; " +
                        "-fx-cursor: hand; -fx-background-radius: 5;"));
        leftArrow.setOnMouseExited(e -> leftArrow.setStyle(
                "-fx-background-color: rgba(255, 31, 143, 0.8); -fx-text-fill: white; " +
                        "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 15 10; " +
                        "-fx-cursor: hand; -fx-background-radius: 5;"));

        rightArrow.setOnMouseEntered(e -> rightArrow.setStyle(
                "-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                        "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 15 10; " +
                        "-fx-cursor: hand; -fx-background-radius: 5;"));
        rightArrow.setOnMouseExited(e -> rightArrow.setStyle(
                "-fx-background-color: rgba(255, 31, 143, 0.8); -fx-text-fill: white; " +
                        "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 15 10; " +
                        "-fx-cursor: hand; -fx-background-radius: 5;"));

        scrollContainer.getChildren().addAll(leftArrow, scrollPane, rightArrow);

        section.getChildren().addAll(header, scrollContainer);
        return section;
    }
}