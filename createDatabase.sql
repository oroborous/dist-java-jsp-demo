create table species (
    species_nm varchar(75) primary key
);

insert into species (species_nm) values ('dog');
insert into species (species_nm) values ('cat');
insert into species (species_nm) values ('bird');
insert into species (species_nm) values ('hamster');

create table pet (
                     pet_id integer primary key generated always as identity (start with 1, increment by 1),
                     nm varchar(75) not null,
                     age integer not null,
                     species_nm varchar(75),
                     foreign key (species_nm) references species (species_nm)
);

insert into pet (nm, age, species_nm) values ('Jayne', 14, 'dog');
insert into pet (nm, age, species_nm) values ('Zuul', 12, 'dog');
insert into pet (nm, age, species_nm) values ('Bean', 3, 'dog');
insert into pet (nm, age, species_nm) values ('Charlie', 8, 'cat');
insert into pet (nm, age, species_nm) values ('Preston', 2, 'cat');

create table food (
                      food_id integer primary key generated always as identity (start with 1, increment by 1),
                      nm varchar(150) not null,
                      price float
);

insert into food (nm, price) values ('Nutro Ultra Senior Dry Dog Food', 59.49);
insert into food (nm, price) values ('Nutro Chicken & Brown Rice Dry Cat Food', 19.47);
insert into food (nm, price) values ('I and Love and You Chicken Essentials', 34.62);
insert into food (nm, price) values ('Alpo Snaps', 4.92);

create table pet_food (
                          pet_id integer,
                          food_id integer,
                          primary key (pet_id, food_id),
                          foreign key (pet_id) references pet (pet_id),
                          foreign key (food_id) references food (food_id)
);

insert into pet_food (pet_id, food_id) values (1, 1);
insert into pet_food (pet_id, food_id) values (2, 1);
insert into pet_food (pet_id, food_id) values (1, 4);
insert into pet_food (pet_id, food_id) values (2, 4);
insert into pet_food (pet_id, food_id) values (3, 3);
insert into pet_food (pet_id, food_id) values (3, 4);
insert into pet_food (pet_id, food_id) values (4, 2);
insert into pet_food (pet_id, food_id) values (5, 2);