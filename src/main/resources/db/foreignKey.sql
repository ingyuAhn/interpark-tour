alter table tour
    add constraint FKpy38sq4e84fouj3ixh3t6pkhs
        foreign key (city_id)
            references city (city_id)