package com.groupproj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.List;

public class Payment {

    public static void show(List<Book> cart, BookstoreApp app) {
        if (cart.isEmpty()) {
            Utilities.showAlert("Cart Empty", "Your cart is empty!");
            return;
        }

        double total = cart.stream().mapToDouble(Book::getPrice).sum();

        Stage paymentStage = new Stage();
        paymentStage.setTitle("Payment");

        VBox paymentBox = new VBox(20);
        paymentBox.setPadding(new Insets(30));
        paymentBox.setStyle("-fx-background-color: #1a1a1a;");

        Label titleLabel = new Label("Payment Information");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");

        Label totalLabel = new Label(String.format("Total Amount: Php%.2f", total));
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalLabel.setStyle("-fx-text-fill: #ff1f8f;");

        VBox formBox = new VBox(15);

        TextField cardNumField = createStyledTextField("Card Number (1234 5678 9012 3456)");
        TextField cardNameField = createStyledTextField("Cardholder Name");
        TextField expiryField = createStyledTextField("Expiry (MM/YY)");
        TextField cvvField = createStyledTextField("CVV");

        formBox.getChildren().addAll(cardNumField, cardNameField, expiryField, cvvField);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button payBtn = new Button("Process Payment");
        payBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-padding: 12 30; -fx-cursor: hand; " +
                "-fx-background-radius: 20; -fx-font-weight: bold;");
        payBtn.setOnAction(e -> {
            if (cardNumField.getText().isEmpty() || cardNameField.getText().isEmpty()) {
                Utilities.showAlert("Error", "Please fill in all fields!");
                return;
            }

            Utilities.showAlert("Success", "Payment Processed Successfully!\n\nThank you for your purchase!");
            cart.clear();
            app.updateCartCount();
            paymentStage.close();
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-padding: 12 30; -fx-cursor: hand; " +
                "-fx-background-radius: 20;");
        cancelBtn.setOnAction(e -> paymentStage.close());

        buttonBox.getChildren().addAll(payBtn, cancelBtn);

        paymentBox.getChildren().addAll(titleLabel, totalLabel, formBox, buttonBox);

        Scene scene = new Scene(paymentBox, 500, 500);
        paymentStage.setScene(scene);
        paymentStage.show();
    }

    private static TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; " +
                "-fx-prompt-text-fill: #666; -fx-padding: 12; " +
                "-fx-background-radius: 8; -fx-font-size: 13px;");
        return field;
    }
}