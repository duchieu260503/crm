-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: crm
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `postcode` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `region` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `street` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Hanoi','Vietnam','100000','Red River Delta','12 Pho Hue'),(2,'Ho Chi Minh City','Vietnam','700000','B','123 Le Loi'),(3,'Da Nang','Vietnam','550000','C','456 Tran Phu'),(4,'Can Tho','Vietnam','900000','D','789 Nguyen Trai');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `approach_time` date DEFAULT NULL,
  `category` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `chance` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created` date DEFAULT NULL,
  `field` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `project_budget` decimal(19,2) DEFAULT NULL,
  `project_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `source` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk1fi84oi1yyuswr40h38kjy1s` (`user_id`),
  CONSTRAINT `FKk1fi84oi1yyuswr40h38kjy1s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'VTCorp','2025-06-01','Doanh nghiệp','Tiềm năng','2025-06-01','Viễn thông','Viettel Corporation',500000000.00,'Chăm sóc khách hàng','Marketing',1,'Mới'),(2,'FPTSoft','2025-05-20','Nội bộ','Chắc chắn','2025-05-20','Phần mềm','FPT Software',350000000.00,'Triển khai IPCC','Giới thiệu',4,'Đang xúc tiến'),(3,'EVNNPC','2025-05-10','Chính phủ','Rủi ro','2025-07-25','Năng lượng','EVN Miền Bắc',800000000.00,'Tổng đài hỗ trợ','Tự nhiên',1,'Đã hợp tác'),(4,'Mobifone','2025-04-25','Ngoài','Đạt mục tiêu','2025-04-25','Viễn thông','Mobifone',620000000.00,'Nâng cấp hệ thống','Giới thiệu',2,'Mới'),(5,'BKAV','2025-04-15','Doanh nghiệp','Tiềm năng','2025-04-15','An ninh mạng','BKAV Corporation',150000000.00,'Bảo mật nội bộ','Marketing',9,'Đang xúc tiến'),(6,'CMC','2025-04-25','Doanh nghiệp','Đạt mục tiêu','2025-04-25','CNTT','CMC Corp',650000000.00,'Data Warehouse','Giới thiệu',1,'Mới'),(7,'MISA','2025-04-28','Nội bộ','Tiềm năng','2025-04-28','Kế toán','MISA',450000000.00,'Phần mềm kế toán','Marketing',2,'Đang xúc tiến'),(8,'EVN','2025-04-30','Chính phủ','Rủi ro','2025-04-30','Điện lực','EVN',780000000.00,'Giám sát lưới điện','Tự nhiên',4,'Mới'),(9,'VNA','2025-05-01','Doanh nghiệp','Chắc chắn','2025-05-01','Hàng không','Vietnam Airlines',1200000000.00,'Customer Analytics','Giới thiệu',9,'Đã hợp tác'),(10,'VCX','2025-04-01','Doanh nghiệp','Tiềm năng','2025-04-01','Viễn thông','Viettel',700000000.00,'Hệ thống quản lý CSKH','Marketing',1,'Mới'),(11,'GHTK','2025-04-05','Chính phủ','Đạt mục tiêu','2025-04-05','Logistics','Giao Hàng Tiết Kiệm',600000000.00,'Hệ thống phân phối','Tự nhiên',2,'Đang xúc tiến'),(12,'FPT','2025-04-10','Doanh nghiệp','Chắc chắn','2025-04-10','CNTT','FPT Software',850000000.00,'Chuyển đổi số','Giới thiệu',4,'Đã hợp tác'),(13,'Mobifone','2025-04-15','Ngoài','Rủi ro','2025-04-15','Viễn thông','Mobifone',500000000.00,'CRM Platform','Tự nhiên',1,'Mới'),(14,'BKAV','2025-04-20','Doanh nghiệp','Tiềm năng','2025-04-20','An ninh mạng','BKAV',900000000.00,'Hệ thống báo cáo','Marketing',2,'Đang xúc tiến'),(15,'TPB','2025-04-22','Ngân hàng','Chắc chắn','2025-04-22','Tài chính','TPBank',1000000000.00,'eKYC Solution','Tự nhiên',4,'Đã hợp tác'),(110,'Cường Thịnh','2025-07-23','Doanh nghiệp','Tiềm năng','2025-07-25','Xây dựng','Công ty TNHH Cường Thịnh',50000000.00,'Quản lý khách hàng','Sự kiện',1,'Mới');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_person`
--

DROP TABLE IF EXISTS `contact_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_person`
--

LOCK TABLES `contact_person` WRITE;
/*!40000 ALTER TABLE `contact_person` DISABLE KEYS */;
INSERT INTO `contact_person` VALUES (1,'hieu@gmail.com','Hieu','123445676','https://viettelcx.com.vn/'),(2,'bop@gmail.com','Pham Minh Hung','0904121298','www.google.com'),(3,'pmh@gmail.com','Pham Manh Cuong','0123456789','www.facebook.com'),(4,'giahuy@gmail.com','Pham Gia Huy','0904123432','www.vnexpress.com'),(5,'linh.le@example.com','Linh Le','0981234567','https://zingnews.vn'),(6,'duong.tran@example.com','Tran Van Duong','0912345678','https://dantri.com.vn'),(7,'ha.nguyen@example.com','Nguyen Thi Ha','0977654321','https://vnreview.vn'),(8,'hung.pham@example.com','Pham Quang Hung','0933123456','https://cafebiz.vn'),(9,'hoa.do@example.com','Do Thi Hoa','0902123456','https://genk.vn'),(10,'tuan.vo@example.com','Vo Minh Tuan','0945671234','https://tuoitre.vn'),(11,'my.bui@example.com','Bui Thi My','0923344556','https://cafef.vn'),(12,'son.nguyen@example.com','Nguyen Duc Son','0967891234','https://baomoi.com'),(13,'trang.pham@example.com','Pham Ngoc Trang','0987654321','https://soha.vn'),(14,'khoa.le@example.com','Le Van Khoa','0911223344','https://nhandan.vn'),(15,'quyetvu@gmail.com','Vu Xuan Quyet','0913552552','www.ctg.com'),(16,'hong080775@gmail.com','Nguyen Thi Hong','0904698775','www.cuongthinh.com'),(19,'minhhung@gmail.com','Phạm Minh Hưng','0338765046','www.vincompany.com');
/*!40000 ALTER TABLE `contact_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `am_lead` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `category` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `contract_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `contract_value_no_vat` bigint DEFAULT NULL,
  `created` date DEFAULT NULL,
  `detailed_progress` longtext COLLATE utf8mb4_general_ci,
  `difficulties` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `duration_in_months` int DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `expected_launch` date DEFAULT NULL,
  `expected_revenue_per_month` bigint DEFAULT NULL,
  `expected_seats_per_month` int DEFAULT NULL,
  `expected_value` bigint DEFAULT NULL,
  `general_progress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `implementing_unit` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `last_client` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `next_plan` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pakd_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pakd_content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `revenue_status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `selling_channel` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `service_platform` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `signed_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `total_value` bigint DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `unit_in_charge` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `vat` bigint DEFAULT NULL,
  `vcx_implementing_unit` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `vcx_ratio` double DEFAULT NULL,
  `accepted_by_id` bigint DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `client_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvek4edk3rdcoy0pdrynsrwr0` (`accepted_by_id`),
  KEY `FKpuvc4r7k7n6wkr7myq3ve10fg` (`author_id`),
  KEY `FKlhq3p3xl25vvnfvyfc51ica0j` (`client_id`),
  CONSTRAINT `FKhvek4edk3rdcoy0pdrynsrwr0` FOREIGN KEY (`accepted_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlhq3p3xl25vvnfvyfc51ica0j` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKpuvc4r7k7n6wkr7myq3ve10fg` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'Nguyen Van A','Gói cơ bản',NULL,NULL,'2025-01-01','Đang lên kế hoạch và liên hệ khách hàng.','Thiếu thông tin đầu vào',NULL,NULL,'2025-09-01',5000000,20,100000000,'50%','Phòng CSKH 1',NULL,'Gặp khách tuần sau','Ưu tiên khách hàng lớn',NULL,NULL,'DV CSKH',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội kinh doanh 1',NULL,'Đơn vị A',0.1,2,1,1),(2,'Tran Thi B','Dự án chiến lược',NULL,NULL,'2025-02-01','Gửi demo sản phẩm.','Khách yêu cầu thêm tính năng',NULL,NULL,'2025-10-01',8000000,10,120000000,'30%','Phòng CSKH 2',NULL,'Hẹn gặp lại lần 2','',NULL,NULL,'Reputa',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội kinh doanh 2',NULL,'Đơn vị B',0.08,1,2,2),(3,'Le Van C','Thử nghiệm',NULL,NULL,'2025-03-01','Đã gặp mặt, đợi feedback.','Khách bận công tác',NULL,NULL,'2025-11-01',6000000,15,110000000,'60%','Phòng Kinh doanh',NULL,'Gửi tài liệu tham khảo','',NULL,NULL,'DV hệ thống IPCC',NULL,NULL,NULL,NULL,NULL,'Đang ký',NULL,'IN_PROGRESS','Đội kinh doanh 3',NULL,'Đơn vị C',0.12,1,1,3),(4,'Pham Thi D','Phát triển',NULL,NULL,'2025-04-01','Đang triển khai thử nội bộ.','Chưa chốt ngân sách',NULL,NULL,'2025-08-01',4500000,12,95000000,'40%','Phòng kỹ thuật',NULL,'Thảo luận lại giá','',NULL,NULL,'DV CSKH trọn gói',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội dự án',NULL,'Đơn vị D',0.15,2,2,4),(5,'Nguyen Duy E','Tiềm năng',NULL,NULL,'2025-05-01','Khách đang xem xét nội dung hợp đồng.','Quy trình phức tạp',NULL,NULL,'2025-07-15',7000000,18,130000000,'25%','Phòng kinh doanh',NULL,'Lên lịch thảo luận','',NULL,NULL,'Reputa',NULL,NULL,NULL,NULL,NULL,'Đang ký',NULL,'IN_PROGRESS','Đội sales',NULL,'Đơn vị E',0.09,2,1,5),(6,'Tran Van F','Gói cao cấp','CT-2025-001',120000000,'2025-01-01','Hợp đồng đã ký ngày 01/06/2025, triển khai đúng tiến độ.',NULL,12,'2026-01-01',NULL,NULL,NULL,NULL,'100%','Phòng dự án A','Viettel',NULL,'Hợp đồng quan trọng','PAKD-01','Hợp tác mở rộng kênh CSKH','DV CSKH trọn gói','Đang thu','Kênh phân phối A','Cloud IPCC','2025-01-15','2025-02-01','Đã ký',132000000,'DONE_DEAL','Đội A',12000000,'Đơn vị F',0.1,1,1,6),(7,'Nguyen Thi G','Gói cơ bản','CT-2025-002',90000000,'2025-02-01','Đã hoàn thành triển khai đợt 1, chuẩn bị nghiệm thu.',NULL,6,'2025-08-01',NULL,NULL,NULL,NULL,'100%','Phòng vận hành','VNPT',NULL,'','PAKD-02','Kênh hỗ trợ khách hàng VNPT','DV CSKH','Chưa thu','Kênh trực tiếp','On-Premise','2025-02-15','2025-03-01','Đã ký',99000000,'DONE_DEAL','Đội B',9000000,'Đơn vị G',0.12,1,1,7),(8,'Pham Van H','Chiến lược','CT-2025-003',150000000,'2025-03-01','Sản phẩm vận hành ổn định, không có phản hồi tiêu cực từ khách.',NULL,24,'2027-03-01',NULL,NULL,NULL,NULL,'100%','Phòng dự án B','FPT',NULL,'','PAKD-03','Triển khai toàn quốc','DV hệ thống IPCC','Thu đủ','Kênh đại lý','Hybrid','2025-03-10','2025-04-01','Đã ký',165000000,'DONE_DEAL','Đội C',15000000,'Đơn vị H',0.08,1,1,8),(9,'Le Thi I','Gói hỗ trợ','CT-2025-004',60000000,'2025-04-01','Đã hoàn thành triển khai đợt 2, chuẩn bị nghiệm thu.',NULL,3,'2025-07-01',NULL,NULL,NULL,NULL,'100%','Phòng hỗ trợ','Mobifone',NULL,'','PAKD-04','Hỗ trợ khách hàng Mobifone','Reputa','Thu đủ','Giới thiệu','SaaS','2025-04-10','2025-05-01','Đã ký',66000000,'DONE_DEAL','Đội D',6000000,'Đơn vị I',0.1,1,2,9),(10,'Truong Van K','Gói tiêu chuẩn','CT-2025-005',80000000,'2025-05-01','Hợp đồng đã ký ngày 01/09/2025, triển khai đúng tiến độ.',NULL,9,'2026-02-01',NULL,NULL,NULL,NULL,'100%','Phòng kinh doanh','VinaPhone',NULL,'','PAKD-05','Gói chuẩn cho Vina','DV CSKH','Đang thu','Telesales','VoIP','2025-05-15','2025-06-01','Đã ký',88000000,'DONE_DEAL','Đội E',8000000,'Đơn vị K',0.11,1,1,10),(11,'Nguyen Thi M','Chiến lược',NULL,NULL,'2025-06-01','Chờ phản hồi từ khách về bản thảo hợp đồng.','Khách yêu cầu thay đổi điều khoản',NULL,NULL,'2025-10-15',7000000,16,105000000,'20%','Phòng kinh doanh',NULL,'Gửi lại hợp đồng chỉnh sửa',NULL,NULL,NULL,'DV hệ thống IPCC',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội kinh doanh 4',NULL,'Đơn vị L',0.07,1,2,4),(12,'Le Van N','Tiềm năng',NULL,NULL,'2025-07-01','Đang phân tích yêu cầu khách hàng.','Thiếu tài nguyên nhân sự',NULL,NULL,'2025-11-01',6000000,12,85000000,'10%','Phòng kỹ thuật',NULL,'Chuẩn bị báo giá',NULL,NULL,NULL,'Reputa',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội kỹ thuật',NULL,'Đơn vị M',0.09,2,1,6),(13,'Pham Thi O','Gói hỗ trợ',NULL,NULL,'2025-08-01','Lên kế hoạch demo hệ thống.','Chưa thống nhất KPI',NULL,NULL,'2025-12-01',5500000,14,95000000,'35%','Phòng hỗ trợ',NULL,'Hẹn demo trực tiếp','Khách ưu tiên hỗ trợ kỹ thuật',NULL,NULL,'DV CSKH',NULL,NULL,NULL,NULL,NULL,'Đang ký',NULL,'IN_PROGRESS','Đội CSKH',NULL,'Đơn vị N',0.11,2,2,8),(14,'Tran Van P','Phát triển','CT-2025-006',100000000,'2025-06-15','Hợp đồng triển khai ổn định, khách hài lòng.',NULL,6,'2025-12-15',NULL,NULL,NULL,NULL,'100%','Phòng phát triển','CMC',NULL,'Khen ngợi đội ngũ triển khai','PAKD-06','Triển khai phần mềm kế toán','DV phần mềm kế toán','Đang thu','Giới thiệu','SaaS','2025-06-20','2025-07-01','Đã ký',110000000,'DONE_DEAL','Đội D',10000000,'Đơn vị O',0.09,1,1,1),(15,'Bui Thi Q','Thử nghiệm','CT-2025-007',85000000,'2025-07-10','Đã hoàn thành setup hệ thống thử nghiệm.',NULL,3,'2025-10-10',NULL,NULL,NULL,NULL,'100%','Phòng thử nghiệm','EVN',NULL,'Hệ thống hoạt động ổn định','PAKD-07','Thử nghiệm chatbot CSKH','Chatbot CSKH','Thu đủ','Telesales','Hybrid','2025-07-15','2025-08-01','Đã ký',93500000,'DONE_DEAL','Đội E',8500000,'Đơn vị P',0.08,1,1,9),(16,'Do Van R','Gói cao cấp',NULL,NULL,'2025-09-01','Chờ PAKD phản hồi kế hoạch triển khai.','PAKD chưa duyệt ngân sách',NULL,NULL,'2026-01-10',9000000,22,150000000,'15%','Phòng PAKD',NULL,'Thảo luận thêm','Cần sớm chốt ngân sách',NULL,NULL,'DV hệ thống IPCC',NULL,NULL,NULL,NULL,NULL,'Đang ký',NULL,'IN_PROGRESS','Đội triển khai A',NULL,'Đơn vị Q',0.1,2,2,10),(17,'Nguyen Thi S','Gói cơ bản','CT-2025-008',70000000,'2025-08-01','Triển khai xong giai đoạn 1.',NULL,6,'2026-02-01',NULL,NULL,NULL,NULL,'100%','Phòng CSKH','CMC',NULL,NULL,'PAKD-08','Gói DV chuẩn cho CMC','DV CSKH','Đang thu','Kênh đại lý','On-Premise','2025-08-15','2025-09-01','Đã ký',77000000,'DONE_DEAL','Đội A',7000000,'Đơn vị R',0.12,1,1,5),(18,'Hoang Van T','Phát triển','CT-2025-009',135000000,'2025-08-15','Khách đề nghị bổ sung tính năng sau nghiệm thu đợt 1.',NULL,12,'2026-08-15',NULL,NULL,NULL,NULL,'100%','Phòng kỹ thuật','VNPT',NULL,NULL,'PAKD-09','Tích hợp CRM nâng cao','CRM tích hợp','Thu đủ','Kênh trực tiếp','Cloud IPCC','2025-08-20','2025-09-01','Đã ký',148500000,'DONE_DEAL','Đội B',13500000,'Đơn vị S',0.1,1,2,2),(19,'Tran Thi U','Gói hỗ trợ',NULL,NULL,'2025-09-01','Khách yêu cầu tư vấn triển khai qua Zoom.','Chờ xác nhận khách',NULL,NULL,'2026-01-01',4800000,10,72000000,'10%','Phòng hỗ trợ',NULL,'Lên lịch tư vấn Zoom',NULL,NULL,NULL,'Reputa',NULL,NULL,NULL,NULL,NULL,'Chưa ký',NULL,'IN_PROGRESS','Đội CSKH',NULL,'Đơn vị T',0.08,2,1,3),(20,'Phan Van V','Dự án chiến lược','CT-2025-010',155000000,'2025-10-01','Đã nghiệm thu thành công, triển khai toàn quốc.',NULL,18,'2027-04-01',NULL,NULL,NULL,NULL,'100%','Phòng dự án C','FPT',NULL,NULL,'PAKD-10','Dự án tích hợp toàn quốc','DV tổng hợp','Thu đủ','Kênh phân phối B','SaaS','2025-10-10','2025-11-01','Đã ký',170500000,'DONE_DEAL','Đội D',15500000,'Đơn vị U',0.09,1,2,7);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `office` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKak81m3gkj8xq5t48xuflbj0kn` (`address_id`),
  CONSTRAINT `FKak81m3gkj8xq5t48xuflbj0kn` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `supervisor_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKrvnycmphjyvdxat1lycui3hpk` (`supervisor_id`),
  KEY `fk_user_role` (`role_id`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  CONSTRAINT `fk_user_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKrvnycmphjyvdxat1lycui3hpk` FOREIGN KEY (`supervisor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','ca@gmail.com','Ca','Pham','$2a$10$r7tT2MFkWdbrkbrA.laYcuGwpLImdWbbFgDNDhAootlnAbpThJ/D6','0587767149',1,1,1),(2,_binary '','hieu@gmail.com','Đức Hiếu','Phạm','$2a$10$SsLYhDu/JkkZ2G04z544behYp7H1DL.8fTnuAWajOkyvag1l5.LUe','0904121298',1,1,2),(4,_binary '','duchieu260503@gmail.com','Duc Hieu','Pham','$2a$10$IC/PbJVlT0Czfa3Z0..7/ed7d1vh56bYNs5p1o5G5PC2m8bga/j8m','0904123432',1,2,3),(9,_binary '','nhanvien@gmail.com','Viên','Nhân','$2a$10$PQqJZkpsdsqhTsJc.lnIOeH8FYndydWQvokdfMrbtgkOevd.4Oj5G','0904698775',1,2,4),(10,_binary '','quanly@gmail.com','Lý','Quản','$2a$10$6rOQQvoqNW7AbXjdEif26eOLnZ.ifDb66nYdBVxdxXYhwuHrcK6AO','0929723656',1,1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-28 10:10:28
