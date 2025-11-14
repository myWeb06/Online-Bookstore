package com.groupproj;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NavigationBar {

    public static HBox create(BookstoreApp app) {
        HBox topBar = new HBox(30);
        topBar.setStyle("-fx-background-color: #141414; -fx-padding: 20 40;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("📚 BOOKSTORE");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logo.setStyle("-fx-text-fill: #ff1f8f;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search books...");
        searchField.setPrefWidth(300);
        searchField.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; " +
                "-fx-prompt-text-fill: #666; -fx-padding: 10 15; " +
                "-fx-background-radius: 20; -fx-font-size: 13px;");

        Button searchBtn = new Button("🔍");
        searchBtn.setStyle("-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                "-fx-font-size: 16px; -fx-padding: 8 15; -fx-cursor: hand; " +
                "-fx-background-radius: 20; -fx-font-weight: bold;");

        searchBtn.setOnAction(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                app.searchBooks(query);
            }
        });

        searchField.setOnAction(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                app.searchBooks(query);
            }
        });

        HBox searchBox = new HBox(10, searchField, searchBtn);
        searchBox.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button homeBtn = createNavButton("Home");
        Button genresBtn = createNavButton("Genres");

        homeBtn.setOnAction(e -> app.showHomeView());
        genresBtn.setOnAction(e -> app.showGenresView());

        topBar.getChildren().addAll(logo, searchBox, spacer, homeBtn, genresBtn);
        return topBar;
    }

    private static Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #888; " +
                "-fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 5 15;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 5 15;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: #888; -fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 5 15;"));
        return btn;
    }
}