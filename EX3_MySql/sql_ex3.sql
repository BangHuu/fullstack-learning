create database sapo;
use sapo;

create table Category(
	id int primary key auto_increment,
    name nvarchar(20) not null,
    createDate timestamp null,
    modifiedDate timestamp null,
    description nvarchar(255)
);
create table Product(
	id int primary key auto_increment,
    name nvarchar(20) not null,
    image nvarchar(255) ,
    price nvarchar(30),
    quantity int,
    description nvarchar(255),
    category_id int
);
ALTER TABLE Product  ADD CONSTRAINT fk_category_product FOREIGN KEY (category_id) REFERENCES Category(id);
select * from Product
select * from Category
select  Product.id,Product.name,image,price,Product.description from Product inner join Category on Product.category_id =  Category.id 
where Category.name LIKE N'%Apple%' and Product.name LIKE N'%Điện thoại%'

select Category.id,count(Product.id) as total from Product inner join Category on Category.id = Product.category_id 
group by Product.category_id order by total desc;

select  Product.*from Product order by quantity  desc limit 0,3
