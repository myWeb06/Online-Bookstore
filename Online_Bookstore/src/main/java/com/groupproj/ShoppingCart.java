package com.groupproj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.List;

public class ShoppingCart {

    public static void show(List<Book> cart, Label cartCountLabel) {
        if (cart.isEmpty()) {
            Utilities.showAlert("Cart Empty", "Your cart is empty!");
            return;
        }

        Stage cartStage = new Stage();
        cartStage.setTitle("Shopping Cart");

        VBox cartBox = new VBox(15);
        cartBox.setPadding(new Insets(30));
        cartBox.setStyle("-fx-background-color: #1a1a1a;");

        Label titleLabel = new Label();
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");

        VBox itemsBox = new VBox(10);

        Label totalLabel = new Label();
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        totalLabel.setStyle("-fx-text-fill: white;");

        final Runnable[] refreshItemsWrapper = new Runnable[1];

        Runnable updateTotal = () -> {
            double total = cart.stream().mapToDouble(Book::getPrice).sum();
            totalLabel.setText(String.format("Total: Php%.2f", total));
        };

        Runnable updateCartCount = () -> {
            if (cartCountLabel != null) {
                cartCountLabel.setText("(" + cart.size() + ")");
            }
        };

        Runnable refreshItems = () -> {
            itemsBox.getChildren().clear();

            titleLabel.setText(String.format("Your Shopping Cart (%d)", cart.size()));

            if (cart.isEmpty()) {
                Label emptyLabel = new Label("Cart is now empty");
                emptyLabel.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
                itemsBox.getChildren().add(emptyLabel);

                cartStage.close();
            } else {
                int index = 1;
                for (Book book : cart) {
                    HBox itemRow = new HBox();
                    itemRow.setStyle("-fx-background-color: #2a2a2a; -fx-padding: 10; " +
                            "-fx-background-radius: 8;");
                    itemRow.setAlignment(Pos.CENTER_LEFT);
                    itemRow.setSpacing(10);

                    Label numberLabel = new Label(String.valueOf(index) + ".");
                    numberLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
                    numberLabel.setPrefWidth(30);
                    numberLabel.setMaxWidth(30);
                    numberLabel.setMinWidth(30);

                    Label bookLabel = new Label(book.getTitle());
                    bookLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
                    bookLabel.setPrefWidth(270);
                    bookLabel.setMaxWidth(270);
                    bookLabel.setMinWidth(270);
                    bookLabel.setWrapText(true);

                    Label priceLabel = new Label(String.format("Php%.2f", book.getPrice()));
                    priceLabel.setStyle("-fx-text-fill: #ff1f8f; -fx-font-weight: bold; -fx-font-size: 16px;");
                    priceLabel.setPrefWidth(120);
                    priceLabel.setMaxWidth(120);
                    priceLabel.setMinWidth(120);
                    priceLabel.setAlignment(Pos.CENTER);

                    Button removeBtn = new Button("Remove");
                    removeBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; " +
                            "-fx-font-size: 12px; -fx-padding: 8 20; -fx-background-radius: 15; " +
                            "-fx-cursor: hand;");
                    removeBtn.setPrefWidth(120);
                    removeBtn.setMaxWidth(120);
                    removeBtn.setMinWidth(120);

                    removeBtn.setOnMouseEntered(e ->
                            removeBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                                    "-fx-font-size: 12px; -fx-padding: 8 20; -fx-background-radius: 15; " +
                                    "-fx-cursor: hand;")
                    );
                    removeBtn.setOnMouseExited(e ->
                            removeBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; " +
                                    "-fx-font-size: 12px; -fx-padding: 8 20; -fx-background-radius: 15; " +
                                    "-fx-cursor: hand;")
                    );

                    removeBtn.setOnAction(e -> {
                        cart.remove(book);
                        refreshItemsWrapper[0].run();
                        updateTotal.run();
                        updateCartCount.run();
                    });

                    itemRow.getChildren().addAll(numberLabel, bookLabel, priceLabel, removeBtn);
                    itemsBox.getChildren().add(itemRow);
                    index++;
                }
            }
            updateTotal.run();
        };

        refreshItemsWrapper[0] = refreshItems;

        refreshItems.run();

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #444;");

        Button closeBtn = new Button("Close");
        closeBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-padding: 10 30; -fx-background-radius: 20;");
        closeBtn.setOnAction(e -> cartStage.close());

        cartBox.getChildren().addAll(titleLabel, new Separator(), itemsBox, separator, totalLabel, closeBtn);

        Scene scene = new Scene(cartBox, 600, 450);
        cartStage.setScene(scene);
        cartStage.show();
    }
}