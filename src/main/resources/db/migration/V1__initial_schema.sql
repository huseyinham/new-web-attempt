CREATE TABLE if NOT EXISTS type (
    type_name VARCHAR(14) NOT NULL,

    PRIMARY KEY (type_name)
);

CREATE TABLE if NOT EXISTS god (
    god_name VARCHAR(100) NOT NULL,
    god_type VARCHAR(8) NOT NULL,

    PRIMARY KEY (god_name),

    FOREIGN KEY (god_type) REFERENCES type(type_name)
);

CREATE TABLE if NOT EXISTS item (
    item_name VARCHAR(100) NOT NULL,
    item_type VARCHAR(14) NOT NULL,

    PRIMARY KEY (item_name),

    FOREIGN KEY (item_type) REFERENCES type(type_name)
);

CREATE TABLE if NOT EXISTS relic (
    relic_name VARCHAR(100) NOT NULL,

    PRIMARY KEY (relic_name)
);