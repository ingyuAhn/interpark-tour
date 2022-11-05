create table city (
                      city_id bigint not null auto_increment,
                      create_date datetime,
                      update_date datetime,
                      city_name varchar(100) not null,
                      last_click_date date,
                      population integer not null,
                      primary key (city_id)
) engine=InnoDB