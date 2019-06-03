create table if not exists users
(
  id          SERIAL       NOT NULL,
  first_name  varchar(250) NOT NULL,
  last_name   varchar(250) NOT NULL,
  middle_name varchar(250) NOT NULL,
  login       varchar(250) NOT NULL,
  password    varchar(250) NOT NULL,
  email       varchar(250) NOT NULL UNIQUE,
  phone       varchar(20)  NOT NULL,
  role        varchar(45)  NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists rooms
(
  id           SERIAL      NOT NULL,
  place_number int         NOT NULL,
  class        varchar(25) NOT NULL,
  room_number  int         NOT NULL,
  price        int         NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists request_rooms
(
  id             SERIAL      NOT NULL,
  place_number   int         NOT NULL,
  class          varchar(25) NOT NULL,
  status         varchar(25) NOT NULL,
  arrival_date   timestamp   NOT NULL,
  departure_date timestamp   NOT NULL,
  user_id        int         NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT request_rooms_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES users (id)
);

create table if not exists invoices
(
  id               SERIAL      NOT NULL,
  room_id          int         NOT NULL,
  request_rooms_id int         NOT NULL,
  user_id          int         NOT NULL,
  arrival_date     timestamp   NOT NULL,
  departure_date   timestamp   NOT NULL,
  total_price      int         NOT NULL,
  status           varchar(25) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT invoices_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES users (id),
  CONSTRAINT invoices_room_id_fkey FOREIGN KEY (room_id)
    REFERENCES rooms (id),
  CONSTRAINT invoices_request_rooms_id_fkey FOREIGN KEY (request_rooms_id)
    REFERENCES request_rooms (id)
);


