CREATE TABLE if NOT EXISTS god_types (
    god_type_name VARCHAR(14) NOT NULL,

    PRIMARY KEY (god_type_name)
);

CREATE TABLE if NOT EXISTS item_types (
    item_type_name VARCHAR(14) NOT NULL,

    PRIMARY KEY (item_type_name)
);

CREATE TABLE if NOT EXISTS god (
    god_name VARCHAR(100) NOT NULL,
    god_type VARCHAR(8) NOT NULL,
    god_image VARCHAR(255),

    PRIMARY KEY (god_name),

    FOREIGN KEY (god_type) REFERENCES god_types(god_type_name)
);

CREATE TABLE if NOT EXISTS item (
    item_name VARCHAR(100) NOT NULL,
    item_type VARCHAR(14) NOT NULL,
    item_image VARCHAR(255),

    PRIMARY KEY (item_name),

    FOREIGN KEY (item_type) REFERENCES item_types(item_type_name)
);

CREATE TABLE if NOT EXISTS relic (
    relic_name VARCHAR(100) NOT NULL,
    relic_image VARCHAR(255),

    PRIMARY KEY (relic_name)
);