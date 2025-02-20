USE RealEstate ;
CREATE TABLE Lessor (
    Lessor_Id INT PRIMARY KEY AUTO_INCREMENT,
    Lessor_Name VARCHAR(255),
    Lessor_Email VARCHAR(255) NOT NULL UNIQUE,
    CHECK (Lessor_Email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
    Lessor_Address VARCHAR(255) NOT NULL,
    Lessor_Password VARCHAR(255) NOT NULL,
    Revenue DECIMAL(10,2), 
    Lessor_Status VARCHAR(255)
);
CREATE TABLE Product (
    Product_Id INT PRIMARY KEY AUTO_INCREMENT,
    Product_Name VARCHAR(255) NOT NULL,
    Product_Type VARCHAR(255) NOT NULL,
    Price DECIMAL(10,2) NOT NULL, 
    Product_Status VARCHAR(255) NOT NULL,
    Address VARCHAR(255) NOT NULL, 
    Acreage FLOAT NOT NULL, 
    Describes VARCHAR(255) NOT NULL
);
ALTER TABLE Product 
ADD COLUMN Lessor_Id INT,
ADD CONSTRAINT FK_Product_Lessor FOREIGN KEY (Lessor_Id) REFERENCES Lessor(Lessor_Id);
SELECT * from Product;
SELECT * FROM product WHERE Product_Id = 1;
ALTER TABLE Product 
CHANGE COLUMN Price Price_Short DECIMAL(10,2) NOT NULL;
ALTER TABLE Product 
ADD COLUMN Price_Long DECIMAL(10,2) NOT NULL;
INSERT INTO Product (Product_Name, Product_Type, Price_Short, Price_Long, Product_Status, Address, Acreage, Describes)
VALUES 
('Nhà phố trung tâm', 'Nhà phố', 12000000, 15000000, 'Còn trống', 'Quận 1, TP.HCM', 80, 'Nhà phố đẹp, gần trung tâm, tiện ích đầy đủ.'),
('Căn hộ cao cấp', 'Căn hộ', 10000000, 13000000, 'Đã thuê', 'Quận 2, TP.HCM', 60, 'Căn hộ hiện đại, nội thất sang trọng, an ninh tốt.'),
('Nhà nguyên căn', 'Nhà riêng', 9000000, 11000000, 'Còn trống', 'Quận 7, TP.HCM', 100, 'Nhà rộng rãi, thích hợp gia đình, khu dân cư yên tĩnh.'),
('Văn phòng hạng A', 'Văn phòng', 30000000, 35000000, 'Còn trống', 'Quận 1, TP.HCM', 120, 'Văn phòng cao cấp, tiện nghi đầy đủ, vị trí đắc địa.'),
('Căn hộ view sông', 'Căn hộ', 14000000, 17000000, 'Còn trống', 'Quận 4, TP.HCM', 70, 'Căn hộ có view đẹp, không gian thoáng mát, gần sông.'),
('Nhà biệt thự sân vườn', 'Biệt thự', 25000000, 30000000, 'Còn trống', 'Quận 9, TP.HCM', 200, 'Biệt thự có sân vườn rộng, không gian xanh mát.'),
('Mặt bằng kinh doanh', 'Mặt bằng', 50000000, 60000000, 'Đã thuê', 'Quận 3, TP.HCM', 150, 'Mặt bằng đẹp, thuận tiện kinh doanh, khu sầm uất.'),
('Phòng trọ giá rẻ', 'Phòng trọ', 3000000, 3500000, 'Còn trống', 'Gò Vấp, TP.HCM', 25, 'Phòng trọ sạch sẽ, an ninh tốt, gần trường học.'),
('Shophouse thương mại', 'Shophouse', 20000000, 25000000, 'Đã thuê', 'Thủ Đức, TP.HCM', 90, 'Shophouse tiện kinh doanh, mặt tiền đẹp, sẵn khách.'),
('Nhà cấp 4', 'Nhà riêng', 7000000, 8500000, 'Còn trống', 'Bình Tân, TP.HCM', 70, 'Nhà cấp 4 đầy đủ tiện nghi, khu dân cư yên tĩnh.'),
('Căn hộ mini', 'Căn hộ', 5000000, 6000000, 'Còn trống', 'Tân Bình, TP.HCM', 35, 'Căn hộ nhỏ, đầy đủ tiện nghi, phù hợp người đi làm.'),
('Văn phòng hạng B', 'Văn phòng', 20000000, 25000000, 'Còn trống', 'Quận 10, TP.HCM', 90, 'Văn phòng rộng, giá hợp lý, tiện nghi đầy đủ.'),
('Kho xưởng', 'Kho bãi', 40000000, 45000000, 'Còn trống', 'Bình Chánh, TP.HCM', 500, 'Kho rộng, xe container vào được, thuận tiện vận chuyển.'),
('Căn hộ studio', 'Căn hộ', 8000000, 9500000, 'Còn trống', 'Phú Nhuận, TP.HCM', 45, 'Căn hộ studio đầy đủ nội thất, khu trung tâm.'),
('Nhà liền kề', 'Nhà phố', 15000000, 18000000, 'Còn trống', 'Quận 12, TP.HCM', 90, 'Nhà liền kề, thiết kế đẹp, khu an ninh.'),
('Shophouse cao cấp', 'Shophouse', 25000000, 30000000, 'Còn trống', 'Bình Thạnh, TP.HCM', 100, 'Shophouse cao cấp, tiện kinh doanh, ngay mặt tiền.'),
('Phòng trọ sinh viên', 'Phòng trọ', 2000000, 2500000, 'Còn trống', 'Thủ Đức, TP.HCM', 20, 'Phòng trọ giá rẻ, gần đại học, thuận tiện đi lại.'),
('Văn phòng giá rẻ', 'Văn phòng', 12000000, 15000000, 'Còn trống', 'Quận 5, TP.HCM', 80, 'Văn phòng rộng rãi, giá mềm, gần trung tâm.'),
('Kho chứa hàng', 'Kho bãi', 35000000, 40000000, 'Còn trống', 'Nhà Bè, TP.HCM', 400, 'Kho chứa hàng rộng, có bảo vệ 24/7.'),
('Biệt thự nghỉ dưỡng', 'Biệt thự', 45000000, 50000000, 'Còn trống', 'Cần Giờ, TP.HCM', 300, 'Biệt thự sát biển, không gian đẹp, tiện nghi cao cấp.');

SELECT * FROM Lessor;


