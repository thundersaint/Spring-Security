CREATE TABLE users (username VARCHAR(250) PRIMARY KEY, password VARCHAR(250), enabled boolean) ;  
CREATE TABLE authorities (username  VARCHAR(250), authority VARCHAR(250), CONSTRAINT fk_user_name FOREIGN KEY (username) REFERENCES users(username)) ;


