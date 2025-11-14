package com.groupproj;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class CartBar {

    public static HBox create(BookstoreApp app, Label cartCountLabel) {
        HBox bottomBar = new HBox(20);
        bottomBar.setStyle("-fx-background-color: rgba(20, 20, 20, 0.95); " +
                "-fx-padding: 20 40; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 20, 0, 0, -5);");
        bottomBar.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button viewCartBtn = new Button("View Cart");
        cartCountLabel.setStyle("-fx-text-fill: #ff1f8f; -fx-font-weight: bold; -fx-font-size: 16px;");

        Button checkoutBtn = new Button("Checkout");

        String btnStyle = "-fx-background-color: #ff1f8f; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-padding: 12 30; -fx-cursor: hand; " +
                "-fx-background-radius: 25; -fx-font-weight: bold;";

        viewCartBtn.setStyle(btnStyle);
        checkoutBtn.setStyle(btnStyle);

        viewCartBtn.setOnAction(e -> app.viewCart());
        checkoutBtn.setOnAction(e -> app.checkout());

        HBox cartBox = new HBox(10, viewCartBtn, cartCountLabel);
        cartBox.setAlignment(Pos.CENTER);

        bottomBar.getChildren().addAll(spacer, cartBox, checkoutBtn);
        return bottomBar;
    }
}