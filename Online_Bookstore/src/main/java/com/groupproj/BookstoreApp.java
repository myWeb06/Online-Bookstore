package com.groupproj;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.sql.PreparedStatement;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookstoreApp extends Application {

    private Connection conn;
    private List<Book> cart = new ArrayList<>();
    private Label cartCountLabel;
    private VBox mainContent;
    private ScrollPane mainScrollPane;

    @Override
    public void start(Stage primaryStage) {
        cartCountLabel = new Label("(0)");

        HBox bottomBar = CartBar.create(this, cartCountLabel);
        connectDatabase();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #0a0a0a;");

        HBox topBar = NavigationBar.create(this);
        root.setTop(topBar);

        mainScrollPane = new ScrollPane();
        mainContent = new VBox(30);
        mainContent.setPadding(new Insets(30, 40, 100, 40));
        mainContent.setStyle("-fx-background-color: #0a0a0a;");

        showHomeView();

        mainScrollPane.setContent(mainContent);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setStyle("-fx-background: #0a0a0a; -fx-background-color: #0a0a0a;");
        root.setCenter(mainScrollPane);

        cartCountLabel = new Label("(0)");

        bottomBar = CartBar.create(this, cartCountLabel);
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 1400, 800);
        primaryStage.setTitle("Online Bookstore");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showHomeView() {
        mainContent.getChildren().clear();
        VBox recommendedSection = RecommendedSection.create(conn, cart, this);
        VBox allBooksSection = AllBooksSection.create(conn, cart, this);
        mainContent.getChildren().addAll(recommendedSection, allBooksSection);
        mainScrollPane.setVvalue(0);
    }

    public void showGenresView() {
        mainContent.getChildren().clear();

        Label pageTitle = new Label("Browse by Genre");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        pageTitle.setStyle("-fx-text-fill: white;");

        mainContent.getChildren().add(pageTitle);

        try {
            Statement stmt = conn.createStatement();
            ResultSet genresRs = stmt.executeQuery(
                    "SELECT DISTINCT genre FROM books WHERE genre IS NOT NULL AND genre != '' ORDER BY genre"
            );

            while (genresRs.next()) {
                String genre = genresRs.getString("genre");
                VBox genreSection = GenreSection.create(genre, conn, cart, this);
                mainContent.getChildren().add(genreSection);
            }

        } catch (Exception e) {
            Utilities.showAlert("Error", "Failed to load genres: " + e.getMessage());
        }

        mainScrollPane.setVvalue(0);
    }

    public void searchBooks(String query) {
        VBox searchResults = new VBox(20);
        searchResults.setPadding(new Insets(30, 40, 30, 40));
        searchResults.setStyle("-fx-background-color: #0a0a0a;");

        Label titleLabel = new Label("Search Results for: \"" + query + "\"");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setStyle("-fx-text-fill: white;");

        HBox booksGrid = new HBox(20);
        booksGrid.setAlignment(Pos.CENTER_LEFT);
        booksGrid.setStyle("-fx-padding: 20 0;");

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT id, title, author, price, " +
                            "COALESCE(image_url, '') as image_url " +
                            "FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? LIMIT 20"
            );
            String searchPattern = "%" + query.toLowerCase() + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            ResultSet rs = pstmt.executeQuery();

            int resultCount = 0;
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                );
                booksGrid.getChildren().add(BookCard.create(book, cart, this));
                resultCount++;
            }

            if (resultCount == 0) {
                Label noResults = new Label("No books found matching your search.");
                noResults.setStyle("-fx-text-fill: #888; -fx-font-size: 16px;");
                searchResults.getChildren().addAll(titleLabel, noResults);
            } else {
                ScrollPane scrollPane = new ScrollPane(booksGrid);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                scrollPane.setFitToHeight(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                searchResults.getChildren().addAll(titleLabel, scrollPane);
            }

        } catch (Exception e) {
            Utilities.showAlert("Error", "Failed to search books: " + e.getMessage());
            e.printStackTrace();
        }

        mainContent.getChildren().clear();
        mainContent.getChildren().add(searchResults);
        mainScrollPane.setVvalue(0);
    }

    public void viewCart() {
        ShoppingCart.show(cart, cartCountLabel); // Pass the cartCountLabel here
    }

    public void addToCart(Book book) {
        cart.add(book);
        cartCountLabel.setText("(" + cart.size() + ")"); // Update count when adding
    }

    public void checkout() {
        Payment.show(cart, this);
    }

    public void updateCartCount() {
        cartCountLabel.setText("(" + cart.size() + ")");
    }

    public List<Book> getCart() {
        return cart;
    }

    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookstore", "root", "root");
            System.out.println("Database connected!");
        } catch (Exception e) {
            Utilities.showAlert("Database Error", "Failed to connect: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}