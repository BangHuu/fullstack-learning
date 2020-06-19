create database sapo;
drop database sapo
use sapo
create table category(
	id int primary key auto_increment,
    name nvarchar(20) not null,
    createDate timestamp null,
    modifiedDate timestamp null,
    description nvarchar(255)
);
create table product(
	id int primary key auto_increment,
    name nvarchar(20) not null,
    image nvarchar(255) ,
    price decimal(18,4),-- 18 là tổng số lượng các số , 4 là tổng số sau dấu phẩy 
    quantity int,
    description nvarchar(255),
    category_id int
);
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_per` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
LOCK TABLES `permission` WRITE;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
INSERT INTO `permission` VALUES (1,'ROLE_Full'),(2,'ROLE_Admin'),(3,'ROLE_Read_Only'),(4,'ROLE_Edit'),(5,'ROLE_Create');
CREATE TABLE `user_per` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_per` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `licensed` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_per` (`id_per`),
  CONSTRAINT `user_per_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `user_per_ibfk_2` FOREIGN KEY (`id_per`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
INSERT INTO `user_per` VALUES (1,1,1,1),(2,3,3,1),(4,1,7,1),(5,3,9,1),(6,1,10,1),(7,1,11,1),(8,1,12,1),(9,3,13,1),(10,1,14,1),(11,1,15,1);
ALTER TABLE product  ADD CONSTRAINT fk_category_product FOREIGN KEY (category_id) REFERENCES category(id);
select * from product
select * from category
categoryproductproductcategoryproductcategoryselect  product.id,product.name,image,price,product.description from product inner join category on product.category_id =  category.id 
where category.name Like N'Apple' and product.name = N'Điện thoại'

select category.id,count(product.id) as total from product right join category on category.id = product.category_id 
group by product.category_id order by total desc;

select  product.*from product order by quantity  desc limit 0,3
