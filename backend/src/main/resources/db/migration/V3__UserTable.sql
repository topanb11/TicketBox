CREATE TABLE ru (
    id UUID NOT NULL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    creditCardNumber VARCHAR(255) NOT NULL,
    creditCardExpirationDate TIMESTAMP NOT NULL,
    ccv VARCHAR(255) NOT NULL,
    validUntil TIMESTAMP NOT NULL
);
