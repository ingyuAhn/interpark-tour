create table tour (
                      tour_id bigint not null auto_increment,
                      end_date date not null,
                      start_data date not null,
                      city_id bigint,
                      primary key (tour_id)
) engine=InnoDB
