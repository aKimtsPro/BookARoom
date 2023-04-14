insert into equipment(equipment_name) values ('projecteur');
insert into equipment(equipment_name) values ('machine a cafe');

insert into room(room_capacity, room_number) values (20, 1);
insert into room(room_capacity, room_number) values (20, 2);

insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (1, 1);
insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (2, 1);
insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (2, 2);

insert into "user"(user_username, user_password, user_enabled) values ('user', '$2a$12$qZMhHUMBuru4UeCzFwsvBeuTopbWOrOcocOIocEgKUYm5B5F5yOye', true);
insert into "user"(user_username, user_password, user_enabled) values ('admin', '$2a$12$IRD.PfN5.a4wq0j8fzP9b.UWQkrrr/sZzBX4A6HBjec7lCHARlbbW', true);

insert into user_roles(owner_id, "role") values (1, 'USER');
insert into user_roles(owner_id, "role") values (2, 'USER');
insert into user_roles(owner_id, "role") values (2, 'ADMIN');