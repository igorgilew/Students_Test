create sequence if not exists student_seq start with 1 increment by 1;

create table if not exists students(
                                       id bigint not null primary key default(nextval('student_seq')),
                                       name varchar(255) not null,
                                       passport varchar(255) not null
);

insert into students (name, passport)
VALUES ('Tom', '2342-2342');
