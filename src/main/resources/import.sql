insert into equipment(equipment_name) values ('projecteur');
insert into equipment(equipment_name) values ('machine a cafe');

insert into room(room_capacity, room_number) values (20, 1);
insert into room(room_capacity, room_number) values (20, 2);

insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (1, 1);
insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (2, 1);
insert into room_equipment(room_equipment_room_id, room_equipment_equipment_id) values (2, 2);

insert into "user"(user_username, user_password, user_role) values ('user', 'pass', 'USER');
insert into "user"(user_username, user_password, user_role) values ('admin', 'pass', 'ADMIN');