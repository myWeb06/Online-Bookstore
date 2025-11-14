package com.groupproj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;

public class BookCard {

    public static VBox create(Book book, List<Book> cart, BookstoreApp app) {
        VBox card = new VBox(10);
        card.setPrefWidth(200);
        card.setStyle("-fx-cursor: hand;");

        StackPane coverPane = new StackPane();
        coverPane.setPrefSize(200, 280);

        ImageView imageView = new ImageView();
        if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
            try {
                imageView = new ImageView(new Image(book.getImageUrl(), true));
                imageView.setFitWidth(200);
                imageView.setFitHeight(280);
                imageView.setPreserveRatio(false);

                Rectangle clip = new Rectangle(200, 280);
                clip.setArcWidth(10);
                clip.setArcHeight(10);
                imageView.setClip(clip);

                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.rgb(0, 0, 0, 0.5));
                shadow.setRadius(15);
                imageView.setEffect(shadow);

                coverPane.getChildren().add(imageView);
            } catch (Exception e) {

                Rectangle placeholder = new Rectangle(200, 280);
                placeholder.setFill(Color.web("#2a2a2a"));
                placeholder.setArcWidth(10);
                placeholder.setArcHeight(10);

                Label noImageLabel = new Label("No Image");
                noImageLabel.setStyle("-fx-text-fill: #666; -fx-font-size: 14px;");

                coverPane.getChildren().addAll(placeholder, noImageLabel);
            }
        }

        Button addBtn = new Button("Add to Cart");
        addBtn.setStyle("-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                "-fx-font-size: 12px; -fx-padding: 8 20; -fx-cursor: hand; " +
                "-fx-background-radius: 20; -fx-opacity: 0;");
        addBtn.setOnAction(e -> {
            cart.add(book);
            app.updateCartCount();
            Utilities.showAlert("Success", "Added to cart!");
        });
        StackPane.setAlignment(addBtn, Pos.CENTER);

        coverPane.getChildren().add(addBtn);

        card.setOnMouseEntered(e -> {
            addBtn.setStyle("-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                    "-fx-font-size: 12px; -fx-padding: 8 20; -fx-cursor: hand; " +
                    "-fx-background-radius: 20; -fx-opacity: 1;");
        });
        card.setOnMouseExited(e -> {
            addBtn.setStyle("-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                    "-fx-font-size: 12px; -fx-padding: 8 20; -fx-cursor: hand; " +
                    "-fx-background-radius: 20; -fx-opacity: 0;");
        });

        Label bookTitle = new Label(book.getTitle());
        bookTitle.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-font-weight: bold;");
        bookTitle.setWrapText(true);
        bookTitle.setMaxWidth(200);

        Label bookAuthor = new Label(book.getAuthor());
        bookAuthor.setStyle("-fx-text-fill: #888; -fx-font-size: 11px;");

        Label bookPrice = new Label("Php" + String.format("%.2f", book.getPrice()));
        bookPrice.setStyle("-fx-text-fill: #ff1f8f; -fx-font-weight: bold; -fx-font-size: 14px;");

        card.getChildren().addAll(coverPane, bookTitle, bookAuthor, bookPrice);
        return card;
    }
}