CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(200) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(500),
    genre VARCHAR(100)
);

INSERT INTO books (title, author, price, image_url, genre) VALUES
('The Lord of the Rings (Paperback)', 'J.R.R. Tolkien', 1320 , 'https://fullybookedonline.com/media/catalog/product/cache/1aee7d54deb90b08d8543ae5166ad121/9/7/9780261103252.jpg', 'Fantasy'),
('A Game of Thrones: A Song of Ice and Fire, Book 1 (Mass Market)', 'George R.R. Martin', 499, '<https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780553573404_1.jpg', 'Fantasy'),
('The Hobbit', 'J.R.R. Tolkien', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780547953830_3.jpg', 'Fantasy'),
('The Final Empire: Mistborn, Book 1', 'Brandon Sanderson', 1299, 'https://fullybookedonline.com/media/catalog/product/cache/1aee7d54deb90b08d8543ae5166ad121/9/7/97812508682821.jpg', 'Fantasy'),
('The Name of the Wind: Kingkiller Chronicles, Book 1', 'Patrick Rothfuss', 1960, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780756404079.jpg', 'Fantasy'),
('The Way of Kings: Stormlight Archive, Book 1', 'Brandon Sanderson', 1455, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780765376671_1.jpg', 'Fantasy'),
('The Last Wish', 'Andrzej Sapkowski', 2999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781949480450.jpg', 'Fantasy'),
('Harry Potter and the Sorcerer`s Stone', 'J.K. Rowling', 779, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781338878929_43638_1_.jpg', 'Fantasy'),
('American Gods', 'Neil Gaiman', 1119, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780063081918.jpg', 'Fantasy'),
('The Lies of Locke Lamora: Gentleman Bastards', 'Scott Lynch', 559, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780553588941_1d567_1.jpg', 'Fantasy');

INSERT INTO books (title, author, price, image_url, genre) VALUES
('Dune: Dune Chronicles', 'Frank Herbert', 615, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780441172719-1_1.jpg', 'Science Fiction'),
('Neuromancer: The Sprawl Trilogy', 'William Gibson', 559, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780441569595.jpg', 'Science Fiction'),
('Ender`s Game: The Ender Saga', 'Orson Scott Card', 1119, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780765394866.jpg', 'Science Fiction'),
('Red Rising', 'Pierce Brown', 699, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780345539809.jpg', 'Science Fiction'),
('Foundation', 'Isaac Asimov', 560, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/i/m/im.jpg', 'Science Fiction'),
('The Martian', 'Andy Weir', 952, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780553418026.1.jpg', 'Science Fiction'),
('1984', 'George Orwell', 489, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780702306129_b6be0_1_.jpg', 'Science Fiction'),
('Brave New World', 'Aldous Huxley', 1063, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780060850524_5.jpg', 'Science Fiction'),
('The Left Hand of Darkness', 'Ursula K. Le Guin', 1064, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/8/1/81a8iba0fel_opt.jpg', 'Science Fiction'),
('Ready Player One', 'Ernest Cline', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/w/e/webp.net-resizeimage_-_2020-10-30t133413.964.jpg', 'Science Fiction');

INSERT INTO books (title, author, price, image_url, genre) VALUES
('The Girl with the Dragon Tattoo', 'Stieg Larsson', 559, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780307949486_3.jpg', 'Mystery'),
('Gone Girl', 'Gillian Flynn', 559, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780385347778-1.jpg', 'Mystery'),
('The Da Vinci Code', 'Dan Brown', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780307277671-1.jpg', 'Mystery'),
('How to Solve Your Own Murder', 'Kristen Perrin', 728, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780593474020.jpg', 'Mystery'),
('The Hound of the Baskervilles', 'Arthur Conan Doyle', 179, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780007368570.jpg', 'Mystery'),
('The Only One Left', 'Riley Sager', 699, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780593183243_dce88_2_.jpg', 'Mystery'),
('Big Little Lies', 'Liane Moriarty', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780425274866.jpg', 'Mystery'),
('The Woman in White', 'Wilkie Collins', 199, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/i/m/imageedit_71_6717266960.jpg', 'Mystery'),
('The Girl on the Train', 'Paula Hawkins', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781594634024.jpg', 'Mystery'),
('And Then There Were None', 'Agatha Christie', 1063, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780062073471_1.jpg', 'Mystery');

INSERT INTO books (title, author, price, image_url, genre) VALUES
('Pride and Prejudice', 'Jane Austen', 951, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/7/1/718cs-jdgfl._sl1500__1_.jpg', 'Romance'),
('Me Before You', 'Jojo Moyes', 699, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780143124542.1.jpg', 'Romance'),
('Outlander', 'Diana Gabaldon', 999, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780385319959.jpg', 'Romance'),
('The Notebook', 'Nicholas Sparks', 499, 'https://nicholassparks.com/wp-content/uploads/2022/08/TheNotebook.jpg', 'Romance'),
('It Ends with Us', 'Colleen Hoover', 799, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781471156267-1.jpg', 'Romance'),
('The Fault in Our Stars', 'John Green', 839, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780142424179-1.jpg', 'Romance'),
('Jane Eyre', 'Charlotte Bronte', 395, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781847493736_1.jpg', 'Romance'),
('To All the Boys I`ve Loved Before', 'Jenny Han', 650, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781442426719-1.jpg', 'Romance'),
('Twilight', 'Stephenie Meyer', 1119, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780316592987.jpg', 'Romance'),
('The Time Traveler`s Wife', 'Audrey Niffenegger', 450, 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1733519901i/18619684.jpg', 'Romance');

INSERT INTO books (title, author, price, image_url, genre) VALUES
('The Shining', 'Stephen King', 1120, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780345806789.jpg', 'Horror'),
('Dracula', 'Bram Stoker', 616, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780307743305.jpg', 'Horror'),
('Frankenstein', 'Mary Shelley', 489, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9781847493507_e4c36_1_.jpg', 'Horror'),
('Bird Box', 'Josh Malerman', 672, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780063413023_db360.jpg', 'Horror'),
('The Exorcist', 'William Peter Blatty', 672, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780063412934.jpg', 'Horror'),
('House of Leaves', 'Mark Z. Danielewski', 1599, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780375703768-ons.jpg', 'Horror'),
('IT', 'Stephen King', 1199, 'https://imgv2-1-f.scribdassets.com/img/word_document/293485159/original/8479b16054/1?v=1', 'Horror'),
('The Haunting of Hill House', 'Shirley Jackson', 952, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9780143039983_5de4a_1_.jpg', 'Horror'),
('The Silence of the Lambs', 'Thomas Harris', 499, 'https://upload.wikimedia.org/wikipedia/en/thumb/6/62/Silence3.png/250px-Silence3.png', 'Horror'),
('The Stand', 'Stephen King', 671, 'https://fullybookedonline.com/media/catalog/product/cache/c47981ce8877a2d4680934f7b4835fb3/9/7/9798217007738.jpg', 'Horror');