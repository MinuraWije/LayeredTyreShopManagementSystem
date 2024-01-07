create database MoratuwaTyreCenter;

use MoratuwaTyreCenter;

create table customer(
                         customerId varchar(35) primary Key,
                         name varchar(35) not null,
                         address text not null,
                         telNum int(20) not null,
                         email text not null
);

create table orders(
                       orderId varchar(35) primary Key,
                       customerId varchar(35),
                       orderDate date not null,
                       foreign key(customerId) references customer(customerId) on update cascade on delete cascade
);


create table item(
                     itemId varchar(35) primary key,
                     brand varchar(35),
                     model varchar(35),
                     unitPrice double,
                     qtyOnHand int(5)
);


create table orderDetail(
                            orderId varchar(35),
                            itemId varchar(35),
                            quantity int(5) not null,
                            unitPrice double,
                            foreign key (itemId) references item(itemId) on update cascade on delete cascade,
                            foreign key (orderId) references orders(orderId) on update cascade on delete cascade
);


create table supplier(
                         supplierId varchar(35) primary key,
                         name varchar(35) not null,
                         address text not null,
                         telNum int(20) not null,
                         email text not null
);

create table itemDetail(
                           itemId varchar(35),
                           supplierId varchar(35),
                           foreign key (itemId) references item(itemId) on update cascade on delete cascade,
                           foreign key (supplierId) references supplier(supplierId) on update cascade on delete cascade
);

create table employee(
                         employeeId varchar(35) primary key,
                         name varchar(35) not null,
                         address text not null,
                         telNum int(20) not null,
                         email text not null,
                         role varchar(35)
);

create table payment(
                        paymentId varchar(35) primary key,
                        orderId varchar(35),
                        amount double,
                        paymentDate date,
                        description varchar(35),
                        foreign key (orderId) references orders(orderId) on update cascade on delete cascade
);