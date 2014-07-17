-- --------------------------------------------------------
-- Host:                         173.194.249.69
-- Server version:               5.5.38 - (Google)
-- Server OS:                    Linux
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table inmindd.cognitive_one_info
CREATE TABLE IF NOT EXISTS `cognitive_one_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `formal_education_years` double DEFAULT NULL,
  `non_formal_education_years` double DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `manager_simul_years` int(11) DEFAULT NULL,
  `professional` int(11) DEFAULT NULL,
  `professional_simul_years` int(11) DEFAULT NULL,
  `technician` int(11) DEFAULT NULL,
  `technician_simul_years` int(11) DEFAULT NULL,
  `clerical` int(11) DEFAULT NULL,
  `clerical_simul_years` int(11) DEFAULT NULL,
  `service` int(11) DEFAULT NULL,
  `service_simul_years` int(11) DEFAULT NULL,
  `agriculture` int(11) DEFAULT NULL,
  `agriculture_simul_years` int(11) DEFAULT NULL,
  `craft` int(11) DEFAULT NULL,
  `craft_simul_years` int(11) DEFAULT NULL,
  `plant` int(11) DEFAULT NULL,
  `plant_simul_years` int(11) DEFAULT NULL,
  `elementary` int(11) DEFAULT NULL,
  `elementary_simul_years` int(11) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.cognitive_one_info: ~62 rows (approximately)
/*!40000 ALTER TABLE `cognitive_one_info` DISABLE KEYS */;
INSERT INTO `cognitive_one_info` (`patient_id`, `timestamp`, `formal_education_years`, `non_formal_education_years`, `manager`, `manager_simul_years`, `professional`, `professional_simul_years`, `technician`, `technician_simul_years`, `clerical`, `clerical_simul_years`, `service`, `service_simul_years`, `agriculture`, `agriculture_simul_years`, `craft`, `craft_simul_years`, `plant`, `plant_simul_years`, `elementary`, `elementary_simul_years`) VALUES
	('1101002', '2014-06-12 13:57:24', 12, 13, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-06-13 14:13:11', 1, 2, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0),
	('1101002', '2014-06-16 14:53:20', 1, 2, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 0, 0, 0, 12, 0),
	('1101002', '2014-06-16 14:54:13', 1, 2, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 12:50:25', 12, 0, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:02:17', 0, 0, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:08:15', 12, 0.5, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:11:32', 12.5, 0.5, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:11:57', 12, 0.5, 12, 0, 13, 0, 0, 0, 14, 21, 0, 0, 24, 0, 0, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:37:02', 12, 0.5, 0, 0, 0, 0, 5, 0, 10, 21, 0, 0, 0, 0, 35, 21, 0, 0, 12, 0),
	('1101002', '2014-06-23 13:38:14', 12, 0.5, 0, 0, 0, 0, 5, 0, 10, 21, 0, 0, 0, 0, 35, 21, 0, 0, 0, 0),
	('1101002', '2014-07-14 10:35:22', 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-14 10:35:56', 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-14 10:36:16', 12, 0.6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-14 10:36:38', 12, 0.6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-24 15:41:36', 24, 1, 0, 0, 13, 0, 6, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-25 09:04:08', 24, 1, 0, 0, 13, 0, 5, 0, 1, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-25 10:10:41', 15, 1, 0, 0, 10, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 09:42:26', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 09:42:49', 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 15:47:55', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 15:48:06', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 15:48:16', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 15:49:40', 15, 1, 0, 0, 6, 0, 6, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 10:49:17', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 10:59:11', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:08:31', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:11:14', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:13:41', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:16:27', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:20:44', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:27:53', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:30:02', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:33:03', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:35:47', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:37:47', 10, 1, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 11:41:09', 7, 1, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 15:44:07', 7, 1, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 15:46:03', 7, 1, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-02 08:54:38', 7, 1, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-09 11:53:45', 15, 5, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('11123', '2014-07-14 09:28:02', 7, 5, 4, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1112345', '2014-07-14 09:55:27', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('11124', '2014-07-14 10:22:49', 15, 2, 0, 0, 3, 0, 0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1124681', '2014-07-11 16:27:43', 20, 0, 0, 0, 9, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1175432', '2014-07-11 16:41:39', 17, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-06-24 14:54:28', 18, 3, 0, 0, 14, 0, 0, 0, 4, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-06-25 09:19:26', 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 35, 0),
	('1234', '2014-06-30 14:02:45', 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0),
	('1234', '2014-06-30 16:21:45', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-06-30 16:22:27', 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-01 11:23:18', 18, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-01 11:36:02', 12, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-01 15:43:09', 12, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-01 15:45:21', 12, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-02 08:46:35', 12, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-10 15:42:53', 16, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-11 14:25:42', 16, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('2201002', '2014-06-16 14:55:59', 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('2202007', '2014-07-11 14:45:42', 11, 2, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('3355555', '2014-07-11 15:45:06', 19, 3, 12, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('52', '2014-07-09 13:58:25', 12, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
/*!40000 ALTER TABLE `cognitive_one_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.cognitive_two_info
CREATE TABLE IF NOT EXISTS `cognitive_two_info` (
  `patient_id` varchar(7) NOT NULL,
  `reading_years` int(11) DEFAULT NULL,
  `household_years` int(11) DEFAULT NULL,
  `driving_years` int(11) DEFAULT NULL,
  `leisure_years` int(11) DEFAULT NULL,
  `technology_years` int(11) DEFAULT NULL,
  `social_years` int(11) DEFAULT NULL,
  `cinema_years` int(11) DEFAULT NULL,
  `gardening_years` int(11) DEFAULT NULL,
  `children_years` int(11) DEFAULT NULL,
  `volunteering_years` int(11) DEFAULT NULL,
  `artistic_years` int(11) DEFAULT NULL,
  `exhibitions_years` int(11) DEFAULT NULL,
  `holidays_years` int(11) DEFAULT NULL,
  `books_years` int(11) DEFAULT NULL,
  `number_children` int(11) DEFAULT NULL,
  `pets_years` int(11) DEFAULT NULL,
  `bank_account_years` int(11) DEFAULT NULL,
  `reading` varchar(45) DEFAULT NULL,
  `household` varchar(45) DEFAULT NULL,
  `driving` varchar(45) DEFAULT NULL,
  `leisure` varchar(45) DEFAULT NULL,
  `technology` varchar(45) DEFAULT NULL,
  `social` varchar(45) DEFAULT NULL,
  `cinema` varchar(45) DEFAULT NULL,
  `gardening` varchar(45) DEFAULT NULL,
  `children` varchar(45) DEFAULT NULL,
  `volunteering` varchar(45) DEFAULT NULL,
  `artistic` varchar(45) DEFAULT NULL,
  `exhibitions` varchar(45) DEFAULT NULL,
  `holidays` varchar(45) DEFAULT NULL,
  `books` varchar(45) DEFAULT NULL,
  `raised_children` varchar(45) DEFAULT NULL,
  `pets` varchar(45) DEFAULT NULL,
  `bank_account` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.cognitive_two_info: ~51 rows (approximately)
/*!40000 ALTER TABLE `cognitive_two_info` DISABLE KEYS */;
INSERT INTO `cognitive_two_info` (`patient_id`, `reading_years`, `household_years`, `driving_years`, `leisure_years`, `technology_years`, `social_years`, `cinema_years`, `gardening_years`, `children_years`, `volunteering_years`, `artistic_years`, `exhibitions_years`, `holidays_years`, `books_years`, `number_children`, `pets_years`, `bank_account_years`, `reading`, `household`, `driving`, `leisure`, `technology`, `social`, `cinema`, `gardening`, `children`, `volunteering`, `artistic`, `exhibitions`, `holidays`, `books`, `raised_children`, `pets`, `bank_account`, `timestamp`) VALUES
	('101', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'yes', 'never', 'never', '2014-07-08 11:29:12'),
	('1101002', 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 23, 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'yes', 'never', 'often', '2014-06-12 14:58:16'),
	('1101002', 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 0, 0, 0, 2, 0, 0, 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'never', 'never', 'yes', 'never', 'never', '2014-06-13 15:42:27'),
	('1101002', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 2, 0, 0, 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'never', 'yes', 'never', 'never', '2014-06-17 12:18:14'),
	('1101002', 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 55, 0, 0, 0, 2, 0, 0, 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'never', 'yes', 'never', 'never', '2014-06-17 13:50:50'),
	('1101002', 60, 60, 10, 0, 0, 0, 0, 20, 10, 5, 5, 35, 0, 15, 3, 20, 0, 'often', 'often', 'often', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'yes', 'often', 'never', '2014-06-23 15:02:34'),
	('1101002', 60, 600, 10, 0, 0, 0, 0, 20, 10, 5, 5, 35, 0, 15, 3, 20, 0, 'often', 'often', 'often', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'yes', 'often', 'never', '2014-06-23 15:06:00'),
	('1101002', 60, 60, 10, 0, 0, 0, 0, 20, 10, 5, 5, 35, 0, 15, 3, 20, 0, 'often', 'often', 'often', 'never', 'never', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'yes', 'often', 'never', '2014-06-23 15:08:46'),
	('1101002', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'yes', 'never', 'never', '2014-07-14 11:53:07'),
	('1102007', 40, 40, 13, 10, 30, 30, 30, 20, 10, 5, 10, 30, 30, 40, 2, 0, 40, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'yes', 'never', 'often', '2014-06-24 15:45:33'),
	('1102007', 30, 30, 12, 10, 20, 20, 20, 20, 10, 5, 2, 20, 20, 20, 2, 0, 30, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'yes', 'never', 'often', '2014-06-25 09:07:33'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'yes', 'never', 'often', '2014-06-25 10:16:31'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 60, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'yes', 'often', 'often', '2014-06-30 15:52:32'),
	('1102007', 26, 26, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 10:55:26'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 10:59:18'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:08:40'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:11:23'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:13:48'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:16:32'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:20:50'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:27:59'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:30:07'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:33:09'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:35:55'),
	('1102007', 26, 0, 20, 26, 26, 26, 26, 26, 20, 26, 26, 0, 24, 26, 2, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'yes', 'never', 'often', '2014-07-01 11:37:53'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'never', '2014-07-01 11:41:44'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'never', '2014-07-01 15:44:12'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'never', '2014-07-01 15:46:11'),
	('1102007', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'never', '2014-07-02 08:54:43'),
	('1102007', 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 2, 26, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'yes', 'often', 'often', '2014-07-09 11:54:59'),
	('11123', 26, 26, 26, 26, 20, 26, 26, 26, 20, 0, 26, 26, 26, 26, 0, 0, 26, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'often', 'often', 'no', 'never', 'often', '2014-07-14 09:32:36'),
	('1112345', 25, 25, 0, 0, 15, 25, 25, 0, 15, 0, 0, 15, 15, 25, 3, 15, 25, 'often', 'often', 'never', 'never', 'often', 'often', 'often', 'never', 'often', 'never', 'never', 'often', 'often', 'often', 'yes', 'often', 'often', '2014-07-11 15:04:33'),
	('1112345', 25, 0, 0, 0, 15, 25, 25, 0, 15, 0, 0, 15, 15, 25, 3, 15, 25, 'often', 'often', 'never', 'never', 'often', 'often', 'often', 'never', 'often', 'never', 'never', 'often', 'often', 'often', 'yes', 'often', 'often', '2014-07-14 09:56:44'),
	('11124', 0, 28, 21, 6, 24, 33, 0, 25, 23, 0, 0, 0, 33, 33, 3, 17, 33, 'never', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'never', 'never', 'never', 'often', 'often', 'yes', 'often', 'often', '2014-07-14 10:27:48'),
	('1120192', 0, 10, 0, 0, 10, 10, 10, 10, 10, 10, 10, 0, 0, 10, 0, 0, 10, 'often', 'often', 'never', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'no', 'never', 'often', '2014-07-11 16:07:58'),
	('1120192', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'never', '2014-07-11 16:18:20'),
	('1124681', 0, 22, 22, 22, 22, 22, 0, 0, 22, 0, 3, 5, 22, 22, 2, 0, 22, 'never', 'often', 'often', 'often', 'often', 'often', 'never', 'never', 'often', 'never', 'often', 'often', 'often', 'often', 'yes', 'never', 'often', '2014-07-11 16:32:01'),
	('1175432', 10, 20, 16, 0, 0, 0, 5, 0, 5, 0, 0, 10, 10, 20, 0, 0, 21, 'often', 'often', 'often', 'never', 'never', 'never', 'often', 'never', 'often', 'never', 'never', 'often', 'often', 'often', 'no', 'never', 'often', '2014-07-11 16:46:10'),
	('1234', 26, 26, 0, 10, 16, 26, 26, 10, 10, 0, 6, 26, 15, 26, 0, 10, 26, 'often', 'often', 'never', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'often', 'often', 'often', 'no', 'often', 'often', '2014-06-24 14:58:20'),
	('1234', 25, 30, 25, 0, 0, 30, 0, 0, 35, 0, 0, 0, 20, 0, 3, 20, 25, 'often', 'often', 'often', 'never', 'never', 'often', 'never', 'never', 'often', 'never', 'never', 'never', 'often', 'never', 'yes', 'often', 'often', '2014-06-25 09:21:31'),
	('1234', 25, 0, 30, 0, 0, 25, 0, 0, 25, 0, 0, 0, 15, 0, 3, 20, 25, 'often', 'never', 'often', 'never', 'never', 'often', 'never', 'never', 'often', 'never', 'never', 'never', 'often', 'never', 'yes', 'often', 'often', '2014-06-30 14:04:49'),
	('1234', 30, 30, 25, 20, 15, 20, 30, 0, 30, 0, 0, 15, 20, 30, 4, 20, 30, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'never', 'never', 'often', 'often', 'often', 'yes', 'often', 'often', '2014-07-01 11:25:10'),
	('1234', 0, 0, 20, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 'never', 'never', 'often', 'never', 'often', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'often', '2014-07-01 11:36:58'),
	('1234', 0, 0, 20, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 'never', 'never', 'often', 'never', 'often', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'often', '2014-07-01 15:43:17'),
	('1234', 0, 0, 20, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 'never', 'never', 'often', 'never', 'often', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'often', '2014-07-01 15:45:38'),
	('1234', 0, 0, 20, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 'never', 'never', 'often', 'never', 'often', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'no', 'never', 'often', '2014-07-02 08:46:47'),
	('1234', 25, 20, 0, 0, 15, 20, 20, 0, 15, 0, 0, 20, 25, 25, 0, 15, 25, 'often', 'often', 'never', 'never', 'often', 'often', 'often', 'never', 'often', 'never', 'never', 'often', 'often', 'often', 'no', 'often', 'often', '2014-07-10 15:59:13'),
	('2202007', 35, 35, 18, 10, 35, 25, 25, 25, 25, 25, 25, 25, 0, 25, 2, 0, 35, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'never', 'often', 'yes', 'never', 'often', '2014-07-11 14:48:31'),
	('3355555', 20, 20, 10, 10, 10, 10, 10, 8, 5, 3, 3, 10, 20, 20, 0, 0, 20, 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'often', 'no', 'never', 'often', '2014-07-11 15:46:50'),
	('52', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 10, 12, 'never', 'never', 'never', 'never', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'yes', 'often', 'often', '2014-07-09 14:03:08'),
	('52', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 10, 12, 'never', 'never', 'never', 'never', 'often', 'never', 'never', 'never', 'never', 'never', 'never', 'never', 'often', 'often', 'yes', 'often', 'often', '2014-07-09 14:03:39');
/*!40000 ALTER TABLE `cognitive_two_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.diet_info
CREATE TABLE IF NOT EXISTS `diet_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `culinary_fat` int(11) DEFAULT NULL,
  `oil_consume` int(11) DEFAULT NULL,
  `vegetable_servings` int(11) DEFAULT NULL,
  `fruit_units` int(11) DEFAULT NULL,
  `red_meat` int(11) DEFAULT NULL,
  `butter` int(11) DEFAULT NULL,
  `carbonated_beverages` int(11) DEFAULT NULL,
  `wine_week` int(11) DEFAULT NULL,
  `legumes_week` int(11) DEFAULT NULL,
  `fish_week` int(11) DEFAULT NULL,
  `sweets_week` int(11) DEFAULT NULL,
  `nuts_week` int(11) DEFAULT NULL,
  `prefer_chicken` int(11) DEFAULT NULL,
  `sauce_week` int(11) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.diet_info: ~78 rows (approximately)
/*!40000 ALTER TABLE `diet_info` DISABLE KEYS */;
INSERT INTO `diet_info` (`patient_id`, `timestamp`, `culinary_fat`, `oil_consume`, `vegetable_servings`, `fruit_units`, `red_meat`, `butter`, `carbonated_beverages`, `wine_week`, `legumes_week`, `fish_week`, `sweets_week`, `nuts_week`, `prefer_chicken`, `sauce_week`) VALUES
	('1101002', '2014-06-12 14:58:57', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-06-17 14:37:29', 0, 2, 3, 3, 1, 1, 1, 7, 0, 0, 0, 3, 1, 2),
	('1101002', '2014-07-06 13:55:23', 0, 2, 3, 3, 1, 1, 1, 7, 0, 0, 0, 3, 1, 2),
	('1101002', '2014-07-06 16:07:57', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-06 16:08:18', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-07 09:12:37', 1, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-07 09:14:25', 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-07 09:15:19', 1, 4, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-07 09:22:22', 0, 2, 3, 2, 1, 0, 1, 7, 0, 0, 0, 0, 0, 0),
	('1101002', '2014-07-09 11:51:20', 1, 2, 0, 0, 1, 1, 0, 0, 0, 3, 0, 0, 1, 0),
	('1101002', '2014-07-09 12:03:44', 1, 2, 0, 0, 1, 1, 0, 0, 0, 3, 3, 0, 1, 0),
	('1101002', '2014-07-09 12:03:59', 1, 0, 0, 0, 1, 1, 0, 0, 0, 3, 3, 0, 1, 0),
	('1101002', '2014-07-11 14:27:42', 1, 2, 3, 0, 1, 1, 0, 0, 0, 3, 3, 0, 1, 0),
	('1101002', '2014-07-14 14:34:21', 1, 2, 3, 0, 1, 1, 0, 0, 0, 3, 3, 0, 1, 0),
	('1101002', '2014-07-14 16:22:38', 1, 2, 3, 0, 1, 1, 0, 0, 0, 3, 3, 0, 1, 0),
	('1101003', '2014-07-06 13:58:55', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101003', '2014-07-06 16:09:08', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101003', '2014-07-06 16:16:05', 1, 4, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1101003', '2014-07-06 16:16:35', 1, 4, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-24 15:47:27', 1, 0, 3, 1, 0, 1, 0, 0, 0, 0, 3, 0, 1, 0),
	('1102007', '2014-06-25 09:09:40', 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 3, 0, 1, 0),
	('1102007', '2014-06-25 10:19:18', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-27 15:04:58', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 16:00:33', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-06-30 16:00:39', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
	('1102007', '2014-07-01 10:56:17', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 0, 3, 1, 2),
	('1102007', '2014-07-01 10:59:31', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:08:57', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:11:35', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:14:00', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:16:44', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:21:01', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:28:11', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:30:53', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:31:06', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-01 11:34:01', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0, 1, 0),
	('1102007', '2014-07-01 11:36:06', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-01 11:38:03', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-01 11:42:00', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-01 15:44:24', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-01 15:46:24', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-02 08:54:55', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-09 10:33:35', 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1102007', '2014-07-09 10:41:13', 1, 4, 5, 2, 0, 0, 0, 7, 3, 3, 0, 0, 0, 0),
	('1102007', '2014-07-09 10:43:27', 1, 4, 5, 3, 0, 0, 0, 7, 3, 3, 0, 3, 1, 2),
	('1102007', '2014-07-09 10:45:05', 0, 4, 5, 3, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-09 11:48:59', 0, 4, 5, 3, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-09 11:55:08', 0, 4, 5, 3, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('1102007', '2014-07-11 14:17:57', 0, 4, 5, 3, 0, 0, 0, 7, 3, 3, 3, 3, 1, 2),
	('11123', '2014-07-14 09:36:40', 1, 0, 5, 3, 1, 0, 0, 0, 3, 3, 0, 3, 1, 2),
	('1112345', '2014-07-11 15:07:23', 1, 2, 3, 2, 0, 0, 0, 0, 3, 0, 0, 3, 1, 2),
	('1112345', '2014-07-14 09:57:18', 1, 2, 3, 2, 0, 0, 0, 0, 3, 0, 0, 3, 1, 2),
	('11124', '2014-07-14 10:31:32', 1, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2),
	('1120192', '2014-07-11 16:10:05', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1120192', '2014-07-11 16:10:07', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1120192', '2014-07-11 16:19:07', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1120192', '2014-07-11 16:19:13', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
	('1124681', '2014-07-11 16:35:26', 1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2),
	('1124681', '2014-07-11 16:35:31', 1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2),
	('1175432', '2014-07-11 16:50:26', 1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2),
	('1175432', '2014-07-11 16:50:38', 1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2),
	('1175432', '2014-07-11 16:50:39', 1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2),
	('1175432', '2014-07-11 16:50:58', 1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2),
	('1234', '2014-06-24 15:00:23', 1, 2, 3, 3, 0, 0, 0, 0, 3, 3, 0, 0, 1, 2),
	('1234', '2014-06-25 09:22:37', 0, 0, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-06-30 14:06:06', 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 3, 0, 0, 0),
	('1234', '2014-07-01 11:26:05', 1, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2),
	('1234', '2014-07-01 11:29:03', 1, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2),
	('1234', '2014-07-01 11:37:46', 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 3, 0, 0, 0),
	('1234', '2014-07-01 15:43:30', 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
	('1234', '2014-07-01 15:46:28', 1, 2, 3, 2, 0, 0, 0, 0, 3, 3, 0, 3, 1, 2),
	('1234', '2014-07-02 08:47:05', 1, 2, 3, 2, 0, 0, 0, 0, 3, 3, 3, 3, 1, 2),
	('1234', '2014-07-10 15:59:48', 1, 2, 3, 2, 0, 0, 0, 0, 3, 3, 3, 3, 1, 2),
	('1234', '2014-07-11 14:23:51', 1, 2, 3, 2, 0, 0, 0, 0, 3, 3, 3, 3, 1, 2),
	('1234', '2014-07-11 14:26:08', 1, 2, 3, 2, 0, 0, 0, 0, 3, 3, 3, 3, 1, 2),
	('3355555', '2014-07-11 15:48:21', 1, 0, 3, 1, 0, 0, 0, 0, 3, 0, 3, 0, 1, 0),
	('52', '2014-07-09 14:07:07', 1, 2, 0, 0, 1, 1, 1, 7, 3, 3, 3, 3, 0, 2),
	('52', '2014-07-09 14:07:21', 1, 2, 0, 0, 1, 1, 1, 7, 3, 3, 3, 3, 0, 2);
/*!40000 ALTER TABLE `diet_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.family_history_info
CREATE TABLE IF NOT EXISTS `family_history_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mother_dementia` varchar(10) DEFAULT NULL,
  `mother_cvd` varchar(10) DEFAULT NULL,
  `mother_diabetes` varchar(10) DEFAULT NULL,
  `father_dementia` varchar(10) DEFAULT NULL,
  `father_cvd` varchar(10) DEFAULT NULL,
  `father_diabetes` varchar(10) DEFAULT NULL,
  `sibling_dementia` varchar(10) DEFAULT NULL,
  `sibling_cvd` varchar(10) DEFAULT NULL,
  `sibling_diabetes` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.family_history_info: ~40 rows (approximately)
/*!40000 ALTER TABLE `family_history_info` DISABLE KEYS */;
INSERT INTO `family_history_info` (`patient_id`, `timestamp`, `mother_dementia`, `mother_cvd`, `mother_diabetes`, `father_dementia`, `father_cvd`, `father_diabetes`, `sibling_dementia`, `sibling_cvd`, `sibling_diabetes`) VALUES
	('1101002', '2014-06-12 14:56:38', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1101002', '2014-06-13 11:54:49', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes'),
	('1101002', '2014-06-13 12:22:09', 'yes', 'no', 'dont', 'yes', 'no', 'dont', 'dont', 'no', 'yes'),
	('1101002', '2014-07-14 11:03:50', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes', 'yes'),
	('1102007', '2014-06-24 15:36:26', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-06-25 08:55:47', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 10:47:46', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 10:58:42', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:08:19', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:10:51', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:13:20', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:15:56', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:20:28', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:27:38', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:29:49', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:32:48', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:35:15', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:37:29', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:40:35', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 15:43:53', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 15:45:41', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-02 08:54:11', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('11123', '2014-07-14 09:22:26', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'yes', 'no'),
	('1112345', '2014-07-11 15:00:50', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1112345', '2014-07-14 09:55:13', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('11124', '2014-07-14 10:16:46', 'no', 'no', 'yes', 'no', 'yes', 'no', 'no', 'no', 'no'),
	('1120192', '2014-07-11 15:54:22', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1120192', '2014-07-11 16:16:20', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1124681', '2014-07-11 16:24:23', 'yes', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1175432', '2014-07-11 16:38:08', 'dont', 'dont', 'dont', 'dont', 'dont', 'dont', 'dont', 'dont', 'dont'),
	('1234', '2014-06-24 14:50:58', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-06-25 09:17:48', 'yes', 'no', 'no', 'no', 'yes', 'no', 'no', 'no', 'yes'),
	('1234', '2014-06-30 14:00:43', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 11:21:31', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 15:42:39', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 15:44:53', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-02 08:46:04', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-10 15:41:54', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-11 14:25:22', 'no', 'yes', 'no', 'no', 'no', 'no', 'no', 'no', 'no'),
	('52', '2014-07-09 13:51:03', 'yes', 'yes', 'no', 'no', 'no', 'no', 'no', 'dont', 'dont');
/*!40000 ALTER TABLE `family_history_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.feelings_info
CREATE TABLE IF NOT EXISTS `feelings_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` varchar(45) NOT NULL,
  `ces1` varchar(45) DEFAULT NULL,
  `ces2` varchar(45) DEFAULT NULL,
  `ces3` varchar(45) DEFAULT NULL,
  `ces4` varchar(45) DEFAULT NULL,
  `ces5` varchar(45) DEFAULT NULL,
  `ces6` varchar(45) DEFAULT NULL,
  `ces7` varchar(45) DEFAULT NULL,
  `ces8` varchar(45) DEFAULT NULL,
  `ces9` varchar(45) DEFAULT NULL,
  `ces10` varchar(45) DEFAULT NULL,
  `ces11` varchar(45) DEFAULT NULL,
  `ces12` varchar(45) DEFAULT NULL,
  `ces13` varchar(45) DEFAULT NULL,
  `ces14` varchar(45) DEFAULT NULL,
  `ces15` varchar(45) DEFAULT NULL,
  `ces16` varchar(45) DEFAULT NULL,
  `ces17` varchar(45) DEFAULT NULL,
  `ces18` varchar(45) DEFAULT NULL,
  `ces19` varchar(45) DEFAULT NULL,
  `ces20` varchar(45) DEFAULT NULL,
  `depression` varchar(45) DEFAULT NULL,
  `treated` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.feelings_info: ~51 rows (approximately)
/*!40000 ALTER TABLE `feelings_info` DISABLE KEYS */;
INSERT INTO `feelings_info` (`patient_id`, `timestamp`, `ces1`, `ces2`, `ces3`, `ces4`, `ces5`, `ces6`, `ces7`, `ces8`, `ces9`, `ces10`, `ces11`, `ces12`, `ces13`, `ces14`, `ces15`, `ces16`, `ces17`, `ces18`, `ces19`, `ces20`, `depression`, `treated`) VALUES
	('1101002', '2014-06-12 08:49:18', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1101002', '2014-06-12 08:49:58', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1101002', '2014-06-12 14:54:19', 'most', 'most', 'most', 'occasional', 'occasional', 'some', 'rarely', 'rarely', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'most', 'yes', 'yes'),
	('1101002', '2014-06-13 10:21:44', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'yes', 'no'),
	('1101002', '2014-06-13 10:37:05', 'rarely', 'rarely', 'rarely', 'most', 'most', 'most', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'occasional', 'most', 'yes', 'yes'),
	('1101002', '2014-07-14 09:49:26', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'yes', 'yes'),
	('1101002', '2014-07-14 09:56:08', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'yes', 'yes'),
	('1101002', '2014-07-14 09:57:11', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'yes', 'yes'),
	('1101002', '2014-07-14 09:58:25', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'yes', 'yes'),
	('1101002', '2014-07-14 10:02:17', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'yes', 'yes'),
	('1101003', '2014-06-13 10:39:19', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'yes', 'yes'),
	('1102007', '2014-06-24 15:34:28', 'rarely', 'rarely', 'rarely', 'most', 'some', 'rarely', 'rarely', 'occasional', 'rarely', 'some', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'some', 'rarely', 'some', 'no', 'no'),
	('1102007', '2014-06-25 08:49:04', 'rarely', 'rarely', 'rarely', 'most', 'some', 'some', 'some', 'most', 'rarely', 'some', 'rarely', 'occasional', 'rarely', 'some', 'some', 'most', 'rarely', 'some', 'rarely', 'some', 'no', 'no'),
	('1102007', '2014-07-01 10:39:37', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 10:46:42', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 10:58:34', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:10:05', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:12:35', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:15:23', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:19:17', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:26:25', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:29:24', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:32:25', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:34:59', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:37:02', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 11:40:16', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 15:43:44', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-01 15:45:19', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-02 08:53:50', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1102007', '2014-07-09 10:38:39', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'occasional', 'some', 'some', 'some', 'no', 'no'),
	('1102008', '2014-06-30 13:34:40', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('11123', '2014-07-14 09:17:41', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'some', 'rarely', 'rarely', 'no', 'no'),
	('11123', '2014-07-14 09:18:40', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'some', 'rarely', 'rarely', 'no', 'no'),
	('1112345', '2014-07-11 14:59:54', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'occasional', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1112345', '2014-07-14 09:54:39', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'occasional', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('11124', '2014-07-14 10:14:31', 'some', 'rarely', 'rarely', 'most', 'some', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'some', 'most', 'rarely', 'rarely', 'rarely', 'most', 'rarely', 'rarely', 'rarely', 'rarely', 'yes', 'yes'),
	('11124', '2014-07-14 13:38:00', 'rarely', 'some', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'occasional', 'yes', 'yes'),
	('1120192', '2014-07-11 16:15:15', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1124681', '2014-07-11 16:22:11', 'rarely', 'occasional', 'some', 'occasional', 'occasional', 'some', 'some', 'occasional', 'rarely', 'some', 'occasional', 'occasional', 'rarely', 'occasional', 'rarely', 'occasional', 'some', 'some', 'rarely', 'rarely', 'no', 'no'),
	('1175432', '2014-07-11 16:34:17', 'occasional', 'rarely', 'most', 'occasional', 'rarely', 'occasional', 'some', 'rarely', 'some', 'occasional', 'some', 'rarely', 'occasional', 'most', 'some', 'rarely', 'some', 'some', 'occasional', 'most', 'no', 'yes'),
	('1234', '2014-06-25 09:16:15', 'some', 'rarely', 'occasional', 'some', 'some', 'occasional', 'some', 'some', 'some', 'some', 'occasional', 'some', 'some', 'occasional', 'some', 'some', 'occasional', 'occasional', 'some', 'some', 'no', 'no'),
	('1234', '2014-06-30 13:56:59', 'some', 'some', 'some', 'most', 'some', 'rarely', 'some', 'occasional', 'rarely', 'rarely', 'some', 'some', 'some', 'some', 'some', 'some', 'rarely', 'some', 'rarely', 'some', 'no', 'no'),
	('1234', '2014-07-01 11:20:23', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1234', '2014-07-01 11:33:13', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'no', 'no'),
	('1234', '2014-07-01 15:42:16', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'no', 'no'),
	('1234', '2014-07-02 08:45:47', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'some', 'no', 'no'),
	('1234', '2014-07-10 15:41:03', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('1234', '2014-07-11 14:25:00', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'occasional', 'rarely', 'rarely', 'rarely', 'rarely', 'no', 'no'),
	('2202007', '2014-07-11 14:41:07', 'occasional', 'rarely', 'some', 'most', 'occasional', 'occasional', 'some', 'occasional', 'rarely', 'some', 'some', 'occasional', 'rarely', 'rarely', 'some', 'some', 'rarely', 'some', 'rarely', 'some', 'no', 'no'),
	('52', '2014-07-09 13:41:48', 'occasional', 'occasional', 'some', 'some', 'occasional', 'some', 'occasional', 'occasional', 'some', 'some', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'some', 'some', 'occasional', 'yes', 'no'),
	('52', '2014-07-09 13:41:55', 'occasional', 'occasional', 'some', 'some', 'occasional', 'some', 'occasional', 'occasional', 'some', 'some', 'some', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'rarely', 'some', 'some', 'occasional', 'yes', 'no');
/*!40000 ALTER TABLE `feelings_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.medical_info
CREATE TABLE IF NOT EXISTS `medical_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cent` double DEFAULT NULL,
  `feet` double DEFAULT NULL,
  `inches` double DEFAULT NULL,
  `kilo` double DEFAULT NULL,
  `stone` double DEFAULT NULL,
  `lbs` double DEFAULT NULL,
  `mmol` double DEFAULT NULL,
  `systolic` double DEFAULT NULL,
  `diastolic` double DEFAULT NULL,
  `chol` varchar(45) DEFAULT NULL,
  `highChol` varchar(45) DEFAULT NULL,
  `lifestylechange` varchar(45) DEFAULT NULL,
  `medchol` varchar(45) DEFAULT NULL,
  `cvd` varchar(45) DEFAULT NULL,
  `hyper` varchar(45) DEFAULT NULL,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `medblood` varchar(45) DEFAULT NULL,
  `mellitus` varchar(45) DEFAULT NULL,
  `diabetestreatment` varchar(45) DEFAULT NULL,
  `sugar` varchar(45) DEFAULT NULL,
  `kidney` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.medical_info: ~47 rows (approximately)
/*!40000 ALTER TABLE `medical_info` DISABLE KEYS */;
INSERT INTO `medical_info` (`patient_id`, `timestamp`, `cent`, `feet`, `inches`, `kilo`, `stone`, `lbs`, `mmol`, `systolic`, `diastolic`, `chol`, `highChol`, `lifestylechange`, `medchol`, `cvd`, `hyper`, `bloodpressure`, `medblood`, `mellitus`, `diabetestreatment`, `sugar`, `kidney`) VALUES
	('1101002', '2014-06-12 14:56:22', 0, 5, 8, 0, 15, 5, 5.7, 0, 0, 'yes', 'yes', 'no', 'past', 'no', 'no', 'no', 'past', 'no', 'no', 'yes', 'no'),
	('1101002', '2014-06-13 10:56:19', 0, 5, 7, 60, 0, 0, -1, 0, 0, 'dont', 'no', 'no', 'currently', 'yes', 'no', 'no', 'currently', 'yes', 'yes', 'yes', 'yes'),
	('1101002', '2014-06-13 11:36:01', 0, 5, 6, 0, 12, 6, 5, 0, 0, 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'currently', 'yes', 'yes', 'no', 'dont'),
	('1101002', '2014-06-13 11:38:25', 0, 5, 6, 0, 12, 6, 5, 0, 0, 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'currently', 'yes', 'yes', 'no', 'dont'),
	('1101002', '2014-06-13 11:53:16', 0, 5, 6, 0, 12, 6, 5, 0, 0, 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'currently', 'yes', 'yes', 'no', 'dont'),
	('1101002', '2014-07-14 10:52:06', 0, 6, 0, 0, 12, 0, 5, 80, 90, 'yes', 'yes', 'yes', 'currently', 'yes', 'yes', 'yes', 'currently', 'yes', 'yes', 'yes', 'yes'),
	('1102007', '2014-06-24 15:36:05', 0, 5, 4, 0, 9, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-06-25 08:54:25', 0, 5, 4, 0, 9, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 10:47:34', 0, 5, 4, 0, 11, 4, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 10:58:38', 0, 5, 4, 0, 11, 4, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:08:13', 0, 5, 4, 0, 14, 2, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:13:15', 0, 5, 4, 0, 14, 2, 8, 0, 0, 'yes', 'yes', 'no', 'past', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:15:51', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'no', 'yes', 'yes', 'past', 'no', 'no', 'no', 'no'),
	('1102007', '2014-07-01 11:20:22', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'no', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:26:49', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:27:34', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:29:44', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:32:43', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:35:12', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:37:18', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 11:40:31', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-01 15:45:37', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-02 08:54:07', 0, 5, 4, 0, 14, 2, 8, 190, 90, 'yes', 'yes', 'no', 'past', 'yes', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102007', '2014-07-07 14:43:39', 0, 5, 4, 0, 14, 2, 8, 120, 80, 'yes', 'no', 'no', 'never', 'no', 'no', 'yes', 'past', 'yes', 'yes', 'no', 'yes'),
	('1102008', '2014-06-30 14:00:02', 0, 5, 4, 0, 9, 2, -1, 90, 60, 'dont', 'no', 'no', 'never', 'no', 'no', 'yes', 'never', 'no', 'no', 'no', 'no'),
	('1102008', '2014-06-30 14:01:14', 0, 5, 4, 0, 9, 2, -1, 90, 60, 'dont', 'no', 'no', 'never', 'no', 'no', 'yes', 'never', 'no', 'no', 'no', 'no'),
	('11123', '2014-07-14 09:21:12', 0, 5, 4, 72, 0, 0, -1, 0, 0, 'dont', 'yes', 'yes', 'past', 'yes', 'yes', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1112345', '2014-07-11 15:00:36', 0, 5, 2, 0, 8, 7, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1112345', '2014-07-14 09:54:52', 0, 5, 2, 0, 8, 7, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('11124', '2014-07-14 10:16:01', 164, 0, 0, 0, 15, 1, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1120192', '2014-07-11 15:52:50', 155, 0, 0, 0, 8, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1120192', '2014-07-11 15:53:04', 155, 0, 0, 0, 8, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1120192', '2014-07-11 16:16:07', 155, 0, 0, 0, 8, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1124681', '2014-07-11 16:23:52', 165, 0, 0, 51, 0, 0, -1, 120, 60, 'dont', 'yes', 'no', 'never', 'no', 'no', 'yes', 'never', 'no', 'no', 'no', 'no'),
	('1124681', '2014-07-11 16:23:54', 165, 0, 0, 51, 0, 0, -1, 120, 60, 'dont', 'yes', 'no', 'never', 'no', 'no', 'yes', 'never', 'no', 'no', 'no', 'no'),
	('1175432', '2014-07-11 16:36:36', 0, 5, 5, 0, 14, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'yes', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-06-24 14:50:16', 0, 5, 2, 0, 8, 8, 4, 0, 0, 'yes', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-06-25 09:17:20', 0, 5, 3, 0, 10, 8, -1, 0, 0, 'dont', 'dont', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'yes', 'dont', 'no'),
	('1234', '2014-06-30 13:58:18', 0, 5, 9, 0, 12, 0, 6, 0, 0, 'yes', 'yes', 'yes', 'never', 'no', 'yes', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 11:21:11', 0, 5, 2, 0, 8, 9, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 11:34:01', 0, 5, 10, 0, 11, 10, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 15:42:31', 0, 5, 10, 0, 11, 10, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-01 15:44:46', 0, 5, 10, 0, 11, 10, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-02 08:45:55', 0, 5, 10, 0, 11, 10, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('1234', '2014-07-11 14:25:13', 0, 5, 10, 0, 11, 10, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no'),
	('2202007', '2014-07-11 14:43:25', 0, 5, 11, 0, 11, 2, 4, 0, 0, 'yes', 'no', 'no', 'never', 'no', 'yes', 'no', 'never', 'no', 'no', 'no', 'no'),
	('3355555', '2014-07-11 15:42:13', 0, 5, 4, 0, 9, 0, -1, 0, 0, 'dont', 'no', 'no', 'never', 'no', 'no', 'no', 'never', 'no', 'no', 'no', 'no');
/*!40000 ALTER TABLE `medical_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.patient_info
CREATE TABLE IF NOT EXISTS `patient_info` (
  `patient_id` varchar(7) NOT NULL DEFAULT '1',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `country_of_birth` varchar(64) DEFAULT NULL,
  `marital_status` varchar(45) DEFAULT NULL,
  `living_arrangements` varchar(100) DEFAULT NULL,
  `occupational_group` varchar(45) DEFAULT NULL,
  `employment_status` varchar(45) DEFAULT NULL,
  `education_level` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`),
  UNIQUE KEY `timestamp_UNIQUE` (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.patient_info: ~173 rows (approximately)
/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;
INSERT INTO `patient_info` (`patient_id`, `timestamp`, `age`, `gender`, `country_of_birth`, `marital_status`, `living_arrangements`, `occupational_group`, `employment_status`, `education_level`) VALUES
	('1101001', '2014-06-11 12:11:56', 56, 'male', 'Afghanistan', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-11 13:22:32', 46, 'female', 'Bahrain', 'In a civil partnership)', 'Living alone', 'Technician', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-12 14:53:11', 56, 'male', 'Ireland', 'Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Technician', 'Paid employment', 'Technical or Vocational'),
	('1101002', '2014-06-12 16:45:26', 56, 'female', 'Afghanistan', 'In a civil partnership)', 'Living alone', 'Manager', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-12 17:02:25', 56, 'female', 'Åland Islands', 'Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-12 17:08:17', 56, 'female', 'Afghanistan', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'Primary Education'),
	('1101002', '2014-06-12 17:13:06', 44, 'female', 'Afghanistan', 'Single (Never married)', 'Living alone', 'Manager', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-12 17:16:11', 55, 'female', 'Afghanistan', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-13 08:51:23', 55, 'female', 'Åland Islands', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-13 09:02:08', 55, 'male', 'Åland Islands', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-13 09:11:36', 55, 'male', 'Åland Islands', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-13 09:30:27', 55, 'male', 'Åland Islands', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-13 09:59:22', 55, 'male', 'Antigua and Barbuda', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-17 13:52:18', 55, 'male', 'Antigua and Barbuda', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'Lower Secondary'),
	('1101002', '2014-06-24 13:26:25', 55, 'male', 'Ascension Island', 'Ongehuwd (nooit getrouwd geweest)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-30 11:46:08', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Living alone', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-30 11:46:50', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Living alone', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-06-30 11:54:21', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Living alone', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 11:17:07', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Other, Please specifiy :', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 11:24:27', 55, 'male', 'Ascension Island', 'Single (Never married)', 'living with cats aND DOGS', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 11:26:02', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 11:32:27', 55, 'male', 'Ascension Island', 'Single (Never married)', 'living with cats and dogs', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 11:37:03', 55, 'male', 'Ascension Island', 'Single (Never married)', 'living with cats and dogs', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 12:03:39', 55, 'male', 'Ascension Island', 'Single (Never married)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 12:04:28', 55, 'male', 'Ascension Island', 'Single (Never married)', 'andudgee with the b', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 12:05:06', 55, 'male', 'Ascension Island', 'Single (Never married)', 'with the cats and dogs', 'Clerical', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 13:15:25', 55, 'male', 'Ascension Island', 'Single (Never married)', '', 'Clerical', 'Other, Please specify', 'No formal education or training'),
	('1101002', '2014-07-04 13:24:10', 45, 'male', 'Åland Islands', 'In a civil partnership', 'living on the park', 'Manager', 'dd', 'No formal education or training'),
	('1101002', '2014-07-04 13:26:02', 45, 'male', 'Åland Islands', 'In a civil partnership', '', 'Manager', 'on the dole', 'No formal education or training'),
	('1101002', '2014-07-04 13:29:56', 45, 'male', 'Åland Islands', 'In a civil partnership', 'Living alone', 'Manager', 'Looking for first regular job', 'No formal education or training'),
	('1101002', '2014-07-04 13:32:23', 45, 'male', 'Åland Islands', 'In a civil partnership', 'Living with unrelated people only, apart from spouse/partner', 'Manager', 'Looking for first regular job', 'No formal education or training'),
	('1101002', '2014-07-04 13:33:40', 45, 'male', 'Åland Islands', 'In a civil partnership', '', 'Manager', 'hhhe', 'No formal education or training'),
	('1101002', '2014-07-04 13:39:14', 56, 'male', 'Antarctica', 'Married (First marriage)', 'test', 'Services', 'Looking for first regular job', 'Primary Education'),
	('1101002', '2014-07-04 13:39:41', 56, 'male', 'Antarctica', 'Married (First marriage)', '', 'Services', 'Looking for first regular job', 'Primary Education'),
	('1101002', '2014-07-04 13:49:07', 45, 'female', 'Angola', 'Divorced', 'cats and dog', 'Services', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 13:50:03', 45, 'female', 'Angola', 'Divorced', 'cats and dog', 'Services', 'Paid employment', 'No formal education or training'),
	('1101002', '2014-07-04 14:18:25', 45, 'female', 'Angola', 'Divorced', 'mmmmmmmcats and dog', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:22:46', 45, 'female', 'Angola', 'Divorced', 'cats and dogs', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:24:58', 45, 'female', 'Angola', 'Divorced', 'in the trees', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:27:23', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:27:37', 45, 'female', 'Angola', 'Divorced', 'in the trees', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:27:59', 45, 'female', 'Angola', 'Divorced', 'in the tree house', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:34:12', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-04 14:35:28', 45, 'female', 'Angola', 'Divorced', 'in a home', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-07 11:50:50', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-07 11:52:08', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-07 12:00:40', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', NULL),
	('1101002', '2014-07-07 12:05:08', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', NULL),
	('1101002', '2014-07-07 12:11:07', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', NULL),
	('1101002', '2014-07-07 12:12:25', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'O Grade, Standard Grade [Includes School Leaving Certificate, GCSE, GCE o Level, CSE, NQ Access 3 Cluster. Intermediate 1, Intermediate 2, Senior Certiciate or equivalent]'),
	('1101002', '2014-07-07 12:14:58', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'GNVQ/GSVQ Advanced, SVQ Level 3,ONC, OND, SCOTVEC National Diploma, City and Guilds Advanced Craft, RSA Advanced or equivalent'),
	('1101002', '2014-07-07 12:19:10', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'GNVQ/GSVQ Advanced, SVQ Level 3,ONC, OND, SCOTVEC National Diploma, City and Guilds Advanced Craft, RSA Advanced or equivalent'),
	('1101002', '2014-07-07 12:21:33', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Other post- but pre-Higher Education qualifications not already mentioned'),
	('1101002', '2014-07-07 13:36:17', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', NULL),
	('1101002', '2014-07-07 13:43:23', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Post school pre-higher education: HNC,HND,SVQ Level 4, RSA Higher Diploma or equivalent'),
	('1101002', '2014-07-07 13:58:12', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Post school pre-higher education: HNC,HND,SVQ Level 4, RSA Higher Diploma or equivalent'),
	('1101002', '2014-07-07 14:39:05', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-07 14:40:07', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Please select one'),
	('1101002', '2014-07-07 14:49:27', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', NULL),
	('1101002', '2014-07-07 14:53:13', 45, 'female', 'Angola', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Please select one'),
	('1101002', '2014-07-07 14:53:33', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Please select one'),
	('1101002', '2014-07-07 14:58:09', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Primary Education'),
	('1101002', '2014-07-07 15:08:32', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Technical or Vocational'),
	('1101002', '2014-07-09 12:14:00', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Technical or Vocational'),
	('1101002', '2014-07-14 14:07:39', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Technical or Vocational'),
	('1101002', '2014-07-14 14:32:32', 45, 'female', 'Åland Islands', 'Divorced', 'Living alone', 'Services', 'Paid employment', 'Technical or Vocational'),
	('1101003', '2014-06-13 08:56:29', 57, 'male', 'Afghanistan', 'Single (Never married)', 'Living alone', 'Manager', 'Paid employment', 'No formal education or training'),
	('1101003', '2014-06-13 09:12:57', 56, 'female', 'Antarctica', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101003', '2014-06-13 09:17:03', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101003', '2014-06-13 10:38:14', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Professional', 'Paid employment', 'No formal education or training'),
	('1101003', '2014-07-04 14:36:46', 55, 'female', 'Albania', 'Single (Never married)', 'cats', 'Technician', 'Paid employment', 'No formal education or training'),
	('1101003', '2014-07-04 14:53:17', 55, 'female', 'Albania', 'Single (Never married)', 'cats', 'Technician', 'on the dole', 'No formal education or training'),
	('1101003', '2014-07-07 12:20:41', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Technician', 'Retired from employment', 'Post school pre-higher education: HNC,HND,SVQ Level 4, RSA Higher Diploma or equivalent'),
	('1101003', '2014-07-07 13:48:52', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Technician', 'Retired from employment', NULL),
	('1101003', '2014-07-07 13:50:26', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Technician', 'Retired from employment', 'Professional qualifications e.g teaching, accountancy'),
	('1101003', '2014-07-07 13:52:46', 55, 'female', 'Albania', 'Single (Never married)', 'Living alone', 'Technician', 'Retired from employment', 'Professional qualifications e.g teaching, accountancy'),
	('1102007', '2014-06-24 15:31:13', 48, 'female', 'Ireland', 'Seperated', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Professional', 'Paid employment', 'Doctorate (PhD) or higher'),
	('1102007', '2014-06-25 08:43:28', 48, 'female', 'Ireland', 'Seperated', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Professional', 'Paid employment', 'Doctorate (PhD) or higher'),
	('1102007', '2014-06-25 08:47:15', 48, 'female', 'Ireland', 'Seperated', 'Other, Please specifiy :', 'Professional', 'Paid employment', 'Doctorate (PhD) or higher'),
	('1102007', '2014-06-25 10:05:12', 48, 'female', 'Ireland', 'Seperated', 'Other, Please specifiy :', 'Professional', 'Paid employment', 'Doctorate (PhD) or higher'),
	('1102007', '2014-06-30 09:01:39', 40, 'male', 'Argentina', 'Single (Never married)', 'Living alone', 'Professional;', 'Paid employment', 'Doctorate (PhD) or higher'),
	('1102007', '2014-06-30 09:18:36', 40, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Craft', 'Paid employment', 'Technical or Vocational'),
	('1102007', '2014-06-30 09:35:28', 40, 'male', 'Ireland', 'Single (Never married)', 'Living alone', 'Craft', 'Paid employment', 'Technical or Vocational'),
	('1102007', '2014-07-01 10:46:01', 40, 'male', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Craft', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 10:48:54', 40, 'male', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 10:53:01', 44, 'male', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 10:58:30', 44, 'male', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:07:31', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:10:00', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:12:28', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:15:15', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:19:11', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:26:18', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:29:20', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:32:20', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:34:54', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:36:57', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 11:40:10', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 15:43:38', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-01 15:45:14', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-02 08:53:45', 44, 'female', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-10 10:29:36', 54, 'male', 'Ireland', 'In a civil partnership', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Looking after home & family', 'Technical or Vocational'),
	('1102007', '2014-07-10 10:30:35', 45, 'male', 'Åland Islands', '"Married (First marriage)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Services', 'Retired from employment', 'Upper Secondary'),
	('1102008', '2014-06-30 12:52:51', 40, 'female', 'Algeria', '"Married (First marriage)', 'Living alone', 'Professional;', 'Other, Please specify', 'Lower Secondary'),
	('11123', '2014-07-14 09:12:03', 54, 'female', 'Ireland', 'Cohabiting with a partner', 'Living with unrelated people only, apart from spouse/partner', 'Professional;', 'Paid employment', 'Postgraduate diploma or degree'),
	('11123', '2014-07-14 09:12:13', 54, 'female', 'Ireland', 'Cohabiting with a partner', 'Living with unrelated people only, apart from spouse/partner', 'Professional;', 'Paid employment', 'Postgraduate diploma or degree'),
	('1112345', '2014-07-11 14:58:30', 45, 'female', 'Ireland', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1112345', '2014-07-11 16:44:40', 45, 'female', 'Ireland', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1112345', '2014-07-14 09:54:21', 45, 'female', 'Ireland', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('11124', '2014-07-14 10:10:39', 51, 'female', 'Ireland', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Higher Certificate'),
	('11124', '2014-07-14 10:10:48', 51, 'female', 'Ireland', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Looking after home & family', 'Higher Certificate'),
	('1120192', '2014-07-11 15:37:50', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:37:53', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:37:58', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:38:06', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:38:22', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:31', 40, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:37', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:39', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:40', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:41', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:39:42', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 15:46:08', 50, 'female', 'Ireland', 'Single (Never married)', 'Living with another relative (other than spouse/partner or children/grandchildren)', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 16:14:26', 40, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1120192', '2014-07-11 16:14:32', 40, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1124681', '2014-07-11 16:17:50', 40, 'female', 'United Kingdom', 'Separated', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Paid employment', 'Other higher education qualifications not already mentioned'),
	('1124681', '2014-07-11 16:18:16', 40, 'female', 'United Kingdom', 'Separated', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Paid employment', 'Other higher education qualifications not already mentioned'),
	('1124681', '2014-07-11 16:58:17', 40, 'female', 'United Kingdom', 'Separated', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Paid employment', 'Other higher education qualifications not already mentioned'),
	('1124681', '2014-07-11 16:58:21', 40, 'female', 'United Kingdom', 'Separated', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Paid employment', 'Other higher education qualifications not already mentioned'),
	('1124681', '2014-07-11 16:58:23', 40, 'female', 'United Kingdom', 'Separated', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Paid employment', 'Other higher education qualifications not already mentioned'),
	('1175432', '2014-07-11 16:31:45', 40, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Clerical', 'Paid employment', 'Postgraduate diploma or degree'),
	('1234', '2014-06-25 09:14:54', 55, 'female', 'Ireland', '"Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Elementary', 'Paid employment', 'Lower Secondary'),
	('1234', '2014-06-30 13:55:03', 50, 'male', 'Ireland', '"Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Craft', 'Paid employment', 'Higher Certificate'),
	('1234', '2014-07-01 11:19:37', 50, 'male', 'Ireland', '"Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Craft', 'Paid employment', 'Higher Certificate'),
	('1234', '2014-07-01 11:32:13', 55, 'male', 'Ireland', 'Single (Never married)', 'Living alone', 'Craft', 'Paid employment', 'Upper Secondary'),
	('1234', '2014-07-01 11:34:38', 55, 'male', 'Ireland', 'Single (Never married)', 'Living alone', 'Clerical', 'Paid employment', 'Upper Secondary'),
	('1234', '2014-07-01 15:41:49', 55, 'male', 'Ireland', '"Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'Higher Certificate'),
	('1234', '2014-07-01 15:44:30', 55, 'male', 'Ireland', '"Married (First marriage)', 'Living with family members(i.e. spouse/partner and/or children/grandchildren)', 'Clerical', 'Paid employment', 'Higher Certificate'),
	('1234', '2014-07-10 15:39:35', 44, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Professional;', 'Paid employment', 'Honour Bachelor degree/professional qualification or both'),
	('1234', '2014-07-11 14:24:44', 44, 'female', 'Ireland', 'Single (Never married)', 'Living alone', 'Professional;', 'Paid employment', NULL),
	('2201002', '2014-07-07 15:10:39', 56, 'female', 'Ascension Island', 'In a civil partnership', 'Living alone', 'Manager', 'Retired from employment', 'Please select one'),
	('2201002', '2014-07-07 15:15:40', 56, 'female', 'Ascension Island', 'In a civil partnership', 'Living alone', 'Manager', 'Retired from employment', 'Please select one'),
	('2201002', '2014-07-07 15:21:40', 56, 'female', 'Ascension Island', 'In a civil partnership', 'Living alone', 'Manager', 'Retired from employment', 'Please select one'),
	('2201002', '2014-07-07 15:41:50', 56, 'female', 'Ascension Island', 'In a civil partnership', 'Living alone', 'Manager', 'Retired from employment', 'O Grade, Standard Grade [Includes School Leaving Certificate, GCSE, GCE o Level, CSE, NQ Access 3 Cluster. Intermediate 1, Intermediate 2, Senior Certiciate or equivalent]'),
	('2201002', '2014-07-07 15:51:59', 56, 'female', 'Ascension Island', 'In a civil partnership', 'Living alone', 'Manager', 'Retired from employment', 'Higher Grade, Advanced Higher, CSYS, A Level, AS Level,Advanced Senior Certificate or equivalent'),
	('2202007', '2014-07-11 14:39:03', 53, 'female', 'Ireland', 'Married (First marriage)', 'Living with unrelated people only, apart from spouse/partner', 'Professional;', 'Student', 'First Degree, Higher degree, SVQ level 5 or equivalent'),
	('3301002', '2014-07-07 15:54:39', 57, 'male', 'Australia', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Retired from employment', 'Basisschool'),
	('3301002', '2014-07-07 16:04:24', 57, 'male', 'Australia', 'Married (First marriage)', 'Living with family members (i.e. spouse/partner and/or children/grandchildren)', 'Professional;', 'Retired from employment', 'Geen formel onderwijs of opleiding');
/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.physical_activities_info
CREATE TABLE IF NOT EXISTS `physical_activities_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `diy_hours` int(11) DEFAULT NULL,
  `summer_walking_hours` int(11) DEFAULT NULL,
  `winter_walking_hours` int(11) DEFAULT NULL,
  `summer_cycling_hours` int(11) DEFAULT NULL,
  `winter_cycling_hours` int(11) DEFAULT NULL,
  `summer_garden_hours` int(11) DEFAULT NULL,
  `winter_garden_hours` int(11) DEFAULT NULL,
  `summer_physical_hours` int(11) DEFAULT NULL,
  `winter_physical_hours` int(11) DEFAULT NULL,
  `summer_housework_hours` int(11) DEFAULT NULL,
  `winter_housework_hours` int(11) DEFAULT NULL,
  `flight_stairs` int(11) DEFAULT NULL,
  `vigorous_hours` int(11) DEFAULT NULL,
  `physical_activity_work` varchar(45) DEFAULT NULL,
  `vigorous` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.physical_activities_info: ~68 rows (approximately)
/*!40000 ALTER TABLE `physical_activities_info` DISABLE KEYS */;
INSERT INTO `physical_activities_info` (`patient_id`, `timestamp`, `diy_hours`, `summer_walking_hours`, `winter_walking_hours`, `summer_cycling_hours`, `winter_cycling_hours`, `summer_garden_hours`, `winter_garden_hours`, `summer_physical_hours`, `winter_physical_hours`, `summer_housework_hours`, `winter_housework_hours`, `flight_stairs`, `vigorous_hours`, `physical_activity_work`, `vigorous`) VALUES
	('101', '2014-07-08 11:28:30', 0, 5, 2, 0, 0, 0, 0, 4, 4, 2, 2, 20, NULL, 'sedentary', 'yes'),
	('1101002', '2014-06-12 09:51:21', 7, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 0, 'standing', 'yes'),
	('1101002', '2014-06-12 11:42:18', 7, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 0, 'na', 'yes'),
	('1101002', '2014-06-12 11:42:39', 7, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 0, 'manual', 'yes'),
	('1101002', '2014-06-12 11:42:59', 7, 2, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 0, 'manual', 'yes'),
	('1101002', '2014-06-12 11:43:52', 7, 2, 2, 3, 4, 5, 6, 8, 9, 10, 11, 13, 0, 'manual', 'yes'),
	('1101002', '2014-06-12 14:56:56', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'standing', NULL),
	('1101002', '2014-06-13 13:52:29', 7, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 0, 'standing', 'yes'),
	('1101002', '2014-06-23 16:27:54', 7, 1, 2, 3, 4, 5, 6, 0, 0, 10, 11, 12, 0, 'standing', 'yes'),
	('1101002', '2014-06-23 16:29:27', 7, 1, 2, 3, 3, 5, 6, 0, 0, 10, 11, 12, 0, 'standing', 'yes'),
	('1101002', '2014-06-23 16:54:51', 7, 1, 2, 0, 0, 5, 6, 0, 0, 10, 11, 12, 0, 'standing', 'yes'),
	('1101002', '2014-06-24 11:42:54', 7, 1, 2, 1, 1, 5, 6, 0, 0, 10, 11, 12, 0, 'sedentary', 'yes'),
	('1101002', '2014-06-30 16:49:44', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 'heavy', 'yes'),
	('1101002', '2014-07-01 16:27:28', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1101003', '2014-06-13 14:28:06', 8, 1, 3, 4, 5, 6, 7, 9, 10, 11, 12, 1, 0, 'sedentary', 'yes'),
	('1102007', '2014-06-24 15:38:03', 1, 1, 1, 0, 0, 2, 1, 1, 2, 12, 12, 10, 0, 'sedentary', 'no'),
	('1102007', '2014-06-25 08:58:10', 2, 2, 1, 0, 0, 3, 1, 2, 3, 12, 12, 10, 0, 'sedentary', 'no'),
	('1102007', '2014-06-30 09:40:28', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:40:29', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:40:30', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:40:31', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:40:32', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:40:33', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 09:41:09', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sedentary', NULL),
	('1102007', '2014-06-30 15:44:24', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 'sedentary', NULL),
	('1102007', '2014-06-30 15:45:36', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 'sedentary', NULL),
	('1102007', '2014-06-30 15:45:55', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 10:48:19', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'na', 'no'),
	('1102007', '2014-07-01 10:49:02', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'na', 'no'),
	('1102007', '2014-07-01 10:59:02', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:11:06', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:13:32', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:16:16', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:20:38', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:27:46', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:29:56', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:32:56', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 11:35:41', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, NULL, 'sedentary', 'no'),
	('1102007', '2014-07-01 11:37:42', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 15:44:01', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-01 15:45:56', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, NULL, 'sedentary', NULL),
	('1102007', '2014-07-02 08:54:30', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 'standing', 'no'),
	('11123', '2014-07-14 09:25:25', 0, 15, 8, 0, 0, 0, 0, 0, 0, 7, 2, 14, 5, 'standing', 'yes'),
	('1112345', '2014-07-11 15:01:37', 1, 2, 1, 0, 0, 1, 0, 1, 1, 3, 3, 15, 0, 'sedentary', 'no'),
	('1112345', '2014-07-14 09:55:21', 1, 2, 1, 0, 0, 1, 0, 1, 1, 3, 3, 15, 0, 'sedentary', 'no'),
	('11124', '2014-07-14 10:19:56', 1, 2, 1, 0, 0, 0, 0, 0, 0, 50, 40, 5, 1, 'sedentary', 'yes'),
	('1120192', '2014-07-11 15:56:05', 0, 10, 10, 5, 5, 0, 0, 0, 0, 10, 10, 1, 5, 'sedentary', 'yes'),
	('1120192', '2014-07-11 16:17:08', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 'sedentary', 'yes'),
	('1124681', '2014-07-11 16:26:05', 0, 3, 2, 0, 0, 0, 0, 2, 2, 6, 6, 10, 2, 'sedentary', 'yes'),
	('1175432', '2014-07-11 16:38:49', 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 1, 0, 'sedentary', 'no'),
	('1234', '2014-06-24 14:52:33', 0, 8, 8, 0, 0, 2, 0, 3, 3, 6, 6, 50, 0, 'sedentary', 'yes'),
	('1234', '2014-06-25 09:18:44', 2, 2, 1, 0, 0, 2, 1, 0, 0, 5, 5, 15, 0, 'manual', 'no'),
	('1234', '2014-06-30 14:01:54', 2, 3, 3, 0, 0, 1, 0, 1, 0, 1, 0, 15, 0, 'manual', 'yes'),
	('1234', '2014-07-01 11:22:30', 0, 2, 2, 0, 0, 1, 1, 1, 1, 3, 3, 10, NULL, 'sedentary', 'no'),
	('1234', '2014-07-01 11:28:01', 0, 2, 2, 0, 0, 1, 1, 1, 1, 3, 3, 10, NULL, 'sedentary', NULL),
	('1234', '2014-07-01 11:28:43', 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 10, NULL, 'sedentary', NULL),
	('1234', '2014-07-01 11:35:28', 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 5, NULL, 'sedentary', 'no'),
	('1234', '2014-07-01 15:43:00', 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, NULL, 'sedentary', 'no'),
	('1234', '2014-07-01 15:45:13', 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, NULL, 'sedentary', NULL),
	('1234', '2014-07-11 14:25:31', 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 0, 'sedentary', NULL),
	('2202007', '2014-07-11 14:44:41', 1, 4, 4, 0, 0, 2, 0, 0, 0, 7, 7, 7, 0, 'sedentary', 'no'),
	('3355555', '2014-07-11 15:44:13', 0, 5, 5, 3, 3, 8, 2, 8, 8, 10, 10, 6, 2, 'sedentary', 'yes'),
	('52', '2014-07-09 13:56:00', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes'),
	('52', '2014-07-09 13:56:21', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes'),
	('52', '2014-07-09 13:56:22', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes'),
	('52', '2014-07-09 13:56:23', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes'),
	('52', '2014-07-09 13:57:14', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes'),
	('52', '2014-07-09 13:57:55', 9, 3, 2, 3, 1, 2, 1, 8, 7, 30, 35, 3, NULL, 'sedentary', 'yes');
/*!40000 ALTER TABLE `physical_activities_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.smoking_alcohol_info
CREATE TABLE IF NOT EXISTS `smoking_alcohol_info` (
  `patient_id` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `smoker_type` varchar(45) DEFAULT NULL,
  `current_year_start` int(11) DEFAULT NULL,
  `current_num_smoke` int(11) DEFAULT NULL,
  `former_year_start` int(11) DEFAULT NULL,
  `former_year_stop` int(11) DEFAULT NULL,
  `former_num_smoke` int(11) DEFAULT NULL,
  `drinks_freq` varchar(50) DEFAULT NULL,
  `num_drinks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.smoking_alcohol_info: ~96 rows (approximately)
/*!40000 ALTER TABLE `smoking_alcohol_info` DISABLE KEYS */;
INSERT INTO `smoking_alcohol_info` (`patient_id`, `timestamp`, `smoker_type`, `current_year_start`, `current_num_smoke`, `former_year_start`, `former_year_stop`, `former_num_smoke`, `drinks_freq`, `num_drinks`) VALUES
	('1101002', '2014-06-12 14:58:48', 'current', 1990, 10, 0, 0, 0, '2-3 times per week', '8 - 11'),
	('1101002', '2014-06-13 16:07:42', 'current', 1970, 40, 0, 0, 0, 'Monthly or less', '7 or less'),
	('1101002', '2014-06-15 14:32:51', 'current', 1970, 44, 0, 0, 0, 'Never', '12 - 17'),
	('1101002', '2014-06-15 14:35:09', 'former', 0, 0, 1960, 1980, 55, 'Never', '12 - 17'),
	('1101002', '2014-06-15 14:46:10', 'current', 1970, 12, 0, 0, 0, 'Never', '7 or less'),
	('1101002', '2014-06-15 14:47:10', 'former', 0, 0, 44, 55, 0, 'Never', '7 or less'),
	('1101002', '2014-06-15 14:56:11', 'current', 1970, 77, 0, 0, 0, 'Never', '7 or less'),
	('1101002', '2014-06-15 15:00:03', 'current', 1970, 0, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-06-15 15:03:00', 'current', 1970, 0, 0, 0, 0, 'Never', '8 - 11'),
	('1101002', '2014-06-16 09:15:59', 'current', 1970, 30, 0, 0, 0, 'Monthly or less', '18+'),
	('1101002', '2014-06-16 09:16:46', 'current', 1970, 50, 0, 0, 0, 'Monthly or less', '18+'),
	('1101002', '2014-06-16 09:17:31', 'current', 1970, 60, 0, 0, 0, 'Monthly or less', '18+'),
	('1101002', '2014-06-19 13:43:49', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-08 14:13:34', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-08 14:14:28', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-08 14:19:20', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-08 14:26:56', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-08 14:28:14', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-08 14:28:57', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '18+'),
	('1101002', '2014-07-08 14:29:28', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '21+'),
	('1101002', '2014-07-09 11:01:55', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-09 11:07:29', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '8 - 11'),
	('1101002', '2014-07-09 11:13:27', 'current', 1970, 20, 0, 0, 0, 'Monthly or less', '7 or less'),
	('1101002', '2014-07-10 14:08:23', 'current', 1970, 0, 0, 0, 0, 'Never', '8 - 11'),
	('1101002', '2014-07-10 14:17:00', 'current', 1970, 0, 0, 0, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-10 14:17:54', 'former', 0, 0, 1965, 2015, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-11 14:29:12', 'former', 0, 0, 1965, 2015, 20, 'Monthly or less', '7 or less'),
	('1101002', '2014-07-14 12:04:58', 'current', 1970, 0, 0, 0, 0, 'Never', '8 - 11'),
	('1101002', '2014-07-14 12:05:47', 'current', 1970, 0, 0, 0, 0, 'Never', '8 - 11'),
	('1101002', '2014-07-14 12:07:37', 'current', 1970, 0, 0, 0, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-14 12:08:26', 'current', 1970, 0, 0, 0, 0, 'Monthly or less', '12 - 17'),
	('1101002', '2014-07-14 12:13:49', 'current', 1970, 0, 0, 0, 0, 'Monthly or less', '14 - 20'),
	('1101002', '2014-07-14 12:14:08', 'current', 1970, 5, 0, 0, 0, 'Monthly or less', '14 - 20'),
	('1101002', '2014-07-14 12:17:08', 'former', 0, 0, 1970, 2010, 0, 'Monthly or less', '8 - 14'),
	('1101002', '2014-07-14 12:23:58', 'former', 0, 0, 1970, 1980, 20, 'Never', '8 - 11'),
	('1101002', '2014-07-14 12:29:15', 'former', 0, 0, 1970, 1980, 20, 'Never', '14 - 20'),
	('1101002', '2014-07-14 12:29:33', 'former', 0, 0, 1970, 1980, 20, 'Never', '14 - 20'),
	('1102007', '2014-06-24 15:46:31', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '7 or less'),
	('1102007', '2014-06-25 09:08:38', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '7 or less'),
	('1102007', '2014-06-30 15:56:28', 'current', 1963, 20, 0, 0, 0, '4 or more times per week', '18+'),
	('1102007', '2014-06-30 15:58:45', 'current', 1963, 0, 0, 0, 0, '4 or more times per week', '18+'),
	('1102007', '2014-06-30 15:59:26', 'current', 1963, 0, 0, 0, 0, '4 or more times per week', '18+'),
	('1102007', '2014-07-01 10:55:38', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 10:59:25', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:08:51', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:11:30', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:13:54', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:16:38', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:20:55', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:28:06', 'never', 0, 0, 0, 0, 0, 'Never', '7 or less'),
	('1102007', '2014-07-01 11:30:47', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 11:33:16', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 11:36:01', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 11:37:57', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 11:41:53', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 15:44:19', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-01 15:46:17', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-02 08:54:49', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-04 15:08:01', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-09 21:23:46', 'current', 26, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-10 08:56:18', 'current', 60, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-10 08:57:20', 'current', 60, 20, 0, 0, 0, '2-3 times per week', '18+'),
	('1102007', '2014-07-11 14:17:48', 'current', 1965, 20, 0, 0, 0, '2-3 times per week', '8 - 11'),
	('11123', '2014-07-14 09:34:23', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1112345', '2014-07-11 15:06:41', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1112345', '2014-07-14 09:57:04', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('11124', '2014-07-14 10:28:56', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '7 or less'),
	('1120192', '2014-07-11 16:08:43', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '8 - 11'),
	('1120192', '2014-07-11 16:18:44', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '8 - 11'),
	('1124681', '2014-07-11 16:33:48', 'former', 0, 0, 1997, 2010, 1, '2-3 times per week', '7 or less'),
	('1124681', '2014-07-11 16:33:51', 'former', 0, 0, 1997, 2010, 1, '2-3 times per week', '7 or less'),
	('1175432', '2014-07-11 16:49:13', 'former', 0, 0, 1985, 2000, 5, 'Monthly or less', '7 or less'),
	('1234', '2014-06-24 14:59:21', 'current', 1996, 5, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-06-25 09:21:54', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '8 - 11'),
	('1234', '2014-06-30 14:05:19', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '8 - 11'),
	('1234', '2014-07-01 11:25:26', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '7 or less'),
	('1234', '2014-07-01 11:37:17', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-01 15:43:24', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-01 15:45:44', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-02 08:46:56', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-10 15:59:23', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-10 15:59:37', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-10 15:59:38', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('1234', '2014-07-11 14:26:00', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('2201002', '2014-06-16 09:42:30', 'current', 1978, 30, 0, 0, 0, 'Monthly or less', '21+'),
	('2201002', '2014-06-16 11:48:32', 'former', 0, 0, 1960, 1980, 22, 'Monthly or less', '7 or less'),
	('2201005', '2014-06-16 09:50:31', 'current', 1980, 50, 0, 0, 0, 'Never', '21+'),
	('2201006', '2014-06-16 10:01:40', 'current', 1980, 50, 0, 0, 0, 'Monthly or less', '14 - 20'),
	('2201010', '2014-06-16 10:06:16', 'current', 1989, 40, 0, 0, 0, 'Never', '14 - 20'),
	('2202007', '2014-07-11 14:49:20', 'never', 0, 0, 0, 0, 0, '2-3 times per week', '14 - 20'),
	('3301002', '2014-07-08 14:45:09', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '14 - 20'),
	('3301002', '2014-07-09 11:14:35', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '8 - 14'),
	('3301002', '2014-07-09 11:15:18', 'never', 0, 0, 0, 0, 0, 'Monthly or less', '21+'),
	('3355555', '2014-07-11 15:47:21', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('52', '2014-07-09 14:05:40', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less'),
	('52', '2014-07-09 14:06:24', 'never', 0, 0, 0, 0, 0, '2-4 times per month', '7 or less');
/*!40000 ALTER TABLE `smoking_alcohol_info` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_apps
CREATE TABLE IF NOT EXISTS `support_apps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(10) NOT NULL DEFAULT 'en',
  `name` varchar(255) NOT NULL,
  `logo_url` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`,`lang`),
  KEY `category` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_apps: ~2 rows (approximately)
/*!40000 ALTER TABLE `support_apps` DISABLE KEYS */;
INSERT INTO `support_apps` (`id`, `lang`, `name`, `logo_url`, `category`, `description`) VALUES
	(1, 'en', 'NHS Couch to 5K', 'http://a4.mzstatic.com/us/r30/Features/d6/30/93/dj.flwjxavg.170x170-75.jpg', 'Diet and Fitness Apps', '<p>Couch to 5K podcast to download to your mobile</p>\r\n\r\n<p>This is a podcast that can be downloaded to your computer, phone or I-Pod, it is designed to get someone off the couch and running up to 5K within 9 weeks.</p>\r\n\r\n<p><a href="https://itunes.apple.com/gb/podcast/nhs-couch-to-5k/id394384987?mt=2"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a></p>'),
	(2, 'en', 'Change4Life Smart Recipes', 'https://lh3.ggpht.com/RpUzme3NNZjUzyGHC0JYf0bJ6iDn2xcNplqevYjbojFWaVMoM_rZhQosIGEJHa_Cw7k=w300', 'Diet and Fitness Apps', '<p>This app offers healthy eating recipes, calorie tracker and advice on making healthier food choices</p>\r\n\r\n<p><a href="https://play.google.com/store/apps/details?id=uk.nhs.befoodsmart&amp;hl=en"><img alt="" src="http://developer.android.com/images/brand/en_generic_rgb_wo_45.png" style="height:45px; width:129px" /></a></p>'),
	(3, 'en', 'My Fitness Pal', 'https://lh3.ggpht.com/KOU_OYo92224XWhglvriYmBtUb35knxkHoLzFex-oFBg9Zx90PFXmTNCI7WmYal6dg=w300', 'Diet and Fitness Apps', '<p><strong>My Fitness Pal</strong> offer is an app that helps users to monitor calorie intake with a calorie counter, sets our fitness goals and ways to attain them through an activity tracker.</p>\r\n\r\n<p><a href="http://www.myfitnesspal.com">www.myfitnesspal.com</a></p>\r\n\r\n<p><a href="https://itunes.apple.com/us/app/calorie-counter-diet-tracker/id341232718"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="https://play.google.com/store/apps/details?id=com.myfitnesspal.android&amp;rdid=com.myfitnesspal.android"><img alt="" src="http://developer.android.com/images/brand/en_generic_rgb_wo_45.png" style="height:45px; width:129px" /></a></p>\r\n'),
	(4, 'en', 'Pedometer App', 'https://lh4.ggpht.com/CvLvDq3VngjZGa-6MVKP4Mt18N5_2-w3Qf_j2hkU6K5u9vvZkApRcslmsqc0Dg2ZZ_4=w300', 'Diet and Fitness Apps', '<p><strong>Pedometer App</strong></p>\r\n\r\n<p>This simple pedometer will help you track your steps during the day or while exercising. Is available for android and apple phones.</p>\r\n\r\n<p><a href="https://www.runtastic.com/en/apps/pedometer">https://www.runtastic.com/en/apps/pedometer</a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><a href="https://itunes.apple.com/app/id442894329"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="https://play.google.com/store/apps/details?id=com.runtastic.android.pedometer.lite"><img alt="" src="http://developer.android.com/images/brand/en_generic_rgb_wo_45.png" style="height:45px; width:129px" /></a></p>\r\n'),
	(5, 'en', 'Finding Optimism App', 'http://a1.mzstatic.com/us/r30/Purple2/v4/f6/67/34/f667343c-ac67-98d3-e6b4-51e19b9ea80d/mzl.zunxegqc.175x175-75.jpg', 'Mental Health Apps', '<p>This app helps the uses develop plans and strategies that promote positive mental health</p>\r\n\r\n<p><a href="http://www.findingoptimism.com/">http://www.findingoptimism.com/</a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><a href="https://itunes.apple.com/us/app/optimism/id352262677?mt=8"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a></p>\r\n'),
	(6, 'en', 'Mood Gym', 'https://moodgym.anu.edu.au/en/moodgym_graphic.img', 'Mental Health Apps', '<p>Mood Gym is a web based program aimed at those with suffering from low mood, depression, anxiety and other issues with mental health. It offers a training program that can help prevent depression</p>\r\n\r\n<p><a href="https://moodgym.anu.edu.au/welcome">https://moodgym.anu.edu.au/welcome</a></p>'),
	(7, 'en', 'Fitbrain', 'http://a3.mzstatic.com/us/r30/Purple/v4/af/85/68/af8568b2-a892-3275-083d-43e89b58beb2/mzl.yeifuczq.175x175-75.jpg', 'Brain Training Apps', '<p><strong>Fitbrain</strong> is a brain training program that helps your memory, focus and brain speed</p>\r\n\r\n<p><a href="https://itunes.apple.com/ie/app/fit-brains-trainer/id565200595?mt=8"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a></p>\r\n'),
	(8, 'en', 'Lumosity App', 'http://a3.mzstatic.com/us/r30/Purple2/v4/22/d8/a7/22d8a7cb-93a7-5228-da76-0da035eea44b/mzl.ztdenkve.175x175-75.jpg', 'Brain Training Apps', '<p>Brain training app with plenty of games to aid memory, speed, perception and general brain health</p>\r\n\r\n<p><a href="http://www.luminosity.com">www.luminosity.com</a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><a href="https://itunes.apple.com/ie/app/lumosity/id664306853?mt=8"><img alt="" src="https://devimages.apple.com.edgekey.net/app-store/marketing/guidelines/images/badge-download-on-the-app-store.svg" style="height:40px; width:135px" /></a></p>');
/*!40000 ALTER TABLE `support_apps` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_experts
CREATE TABLE IF NOT EXISTS `support_experts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(10) NOT NULL DEFAULT 'en',
  `country` varchar(255) NOT NULL,
  `image_url` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`,`lang`),
  KEY `country` (`country`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_experts: ~13 rows (approximately)
/*!40000 ALTER TABLE `support_experts` DISABLE KEYS */;
INSERT INTO `support_experts` (`id`, `lang`, `country`, `image_url`, `description`) VALUES
	(1, 'en', 'Ireland', 'images/staff/kate.png', '<strong>Dr Kate Irving</strong> is a senior lecturer in the School of Nursing and Human Sciences, Dublin City University. Kate has been involved in clinical practice, teaching and research in the field of dementia for over 15 years. Kate is the Principal Investigator on the In-MINDD (innovative midlife intervention for dementia deterrence) project. She is a partner on a further two European consortia, Dem@care and Actif-Care (Joint Programme on Neuro Degeneration). Kate is the developer and co-ordinator of the joint HSE/DCU Dementia Champion Programme, the Dublin Northside Alzheimer Café and the Memory Works screening/remediation Clinic in DCU.'),
	(2, 'en', 'Ireland', 'images/staff/muriel.png', '<strong>Muriel Redmond</strong> is a researcher at the School of Nursing and Human Sciences, Dublin City University, engaged on the In-MINND project. Muriel has a degree in Sociology and has worked for over 10 years as a researcher. Her experience covers many areas, including mental health, drug use among vulnerable populations and evaluation of services for young adults. She has extensive experience in qualitative research methods and has also worked on many quantitative research projects, including a government funded project on ICT needs of the voluntary sector in Ireland.'),
	(3, 'en', 'Ireland', 'images/staff/maria.png', '<strong>Dr Maria Pierce</strong> is a postdoctoral researcher on the In-MINDD (Innovative Midlife Intervention for Dementia Deterrence) project. She joined the School of Nursing and Human Sciences, Dublin City University in October 2013 from the Living with Dementia programme based in the School of Social Work and Social Policy, Trinity College Dublin. Her background is in social policy and she has a particular interest in ageing and dementia. She completed a social science degree at the National University of Ireland, Dublin, and holds a PhD in social policy and ageing from Trinity College Dublin.'),
	(4, 'en', 'Scotland', 'images/staff/susan.png', '<strong>Dr Susan Browne</strong> is a health services researcher. She joined General Practice and Primary Care at the University of Glasgow in April 2003 as a researcher looking at the impact of public cancer awareness campaigns on primary care medical services. She has worked on a number of research projects since then mainly focusing on the patient experience of illness, particularly colorectal cancer and heart failure. Susan’s PhD explored the experiences of colorectal cancer patients from a longitudinal perspective.'),
	(5, 'en', 'Scotland', 'images/staff/kateod.png', '<strong>Professor Kate O\'Donnell</strong> joined General Practice and Primary Care at the University of Glasgow as a lecturer in 1996 after working as an immunologist and then as co-ordinator of the West of Scotland Health Services Research Network in the then Department of Public Health, University of Glasgow. She is involved in both research and postgraduate education. Her research is concerned with the implementation and delivery of primary care services, particularly for underserved populations, and the evaluation of primary care policy into practice. She is also interested in the way that health care access affects how people think about their health and use health services.'),
	(6, 'en', 'France', '', 'Philippe Robert'),
	(7, 'en', 'France', '', 'Anne-Marie Cauvin'),
	(8, 'en', 'France', '', 'Jeremy Bourgeouis'),
	(9, 'en', 'France', '', 'Valeria Manera'),
	(10, 'en', 'The Netherlands', 'images/staff/frans.png', 'Frans Verhey is appointed as a professor of Old Age Psychiatry and Neuroropsychiatry at the University of Maastricht, the Netherlands. He is the director of the Alzheimer Center Limburg. He actively participates in several international networks, such as the European AD Consortium, and INTERDEM.  The topics of his current research projects include: predictors of conversion of mild cognitively impaired elderly into Alzheimer’s Disease; the course and risk factors of neuropsychiatric disturbances in dementia; psychosocial interventions; predictors of vascular dementia. He is the (co)author of more than 325 (inter)national articles. He has supervised more than 30 PhD’s students, his current H-factor is 48 (July 2014).'),
	(10, 'nl', 'The Netherlands', 'images/staff/frans.png', 'Frans Verhey is hoogleraar Ouderenpsychiatrie en Neuropsychiatrie aan de Universiteit van Maastricht. Hij is het hoofd van het Alzheimer Centrum Limburg aldaar. Hij is bestuurslid van diverse international samenwerkingen, zoals het European Alzheimer’s Disease Consortium en InterDem. Zijn onderzoek richt zich op predictoren van dementia, mn de ziekte van Alzheimer; beloop en risicofactoren van gedragsproblemen bij mensen met dementie; predictoren van Vasculaire dementie; psychosociale interventies. Hij is (co)auteur van meer dan 325 (inter)nationale artikelen; zijn huidige H-factor (juli 2014) is 48.'),
	(11, 'en', 'The Netherlands', '', 'Martin van Boxtel'),
	(12, 'en', 'The Netherlands', '', 'Sebastian Kohler'),
	(13, 'en', 'The Netherlands', 'images/staff/kay.png', 'Kay Deckers is a PhD-student at the School for Mental Health and Neuroscience, Maastricht University. As one of the researchers of the In-MINDD project, he is mainly involved in epidemiological studies into the association between risk factors and cognitive ageing or dementia. In 2012 he received his research master’s degree in Neuropsychology from Maastricht University. His PhD project, which is planned to be completed in 2017, will focus on the risk factors of cognitive impairment and dementia.'),
	(13, 'nl', 'The Netherlands', 'images/staff/kay.png', 'Kay Deckers is werkzaam als promovendus bij de School for Mental Health and Neuroscience, Universiteit Maastricht. Als onderzoeker op het In-MINDD project is hij voornamelijk betrokken bij epidemiologische studies naar de relatie tussen risicofactoren en cognitieve veroudering of dementie. Hij is in 2012 afgestudeerd als neuropsycholoog aan de Universiteit Maastricht. Zijn promotieonderzoek dat tot 2017 loopt, richt zicht op de risicofactoren van cognitieve veroudering en dementie.'),
	(14, 'en', 'The Netherlands', 'images/staff/olga.png', 'Olga Schiepers is a postdoctoral researcher at the School for Mental Health and Neuroscience, Maastricht University. As a neurobiologist and health scientist, she is involved in epidemiological studies into the determinants and risk factors of cognitive ageing and dementia. She completed degrees in biomedical sciences at VU University Amsterdam, and mental health at Maastricht University. Her PhD project, which she finished in 2011, focused on nutritional and genetic determinants of cognitive ageing.'),
	(14, 'nl', 'The Netherlands', 'images/staff/olga.png', 'Olga Schiepers is werkzaam als postdoc onderzoeker bij de School for Mental Health and Neuroscience, Universiteit Maastricht. Als neurobioloog en gezondheidswetenschapper is zij betrokken bij epidemiologische studies naar de determinanten en risicofactoren van cognitieve veroudering en dementie. Ze is afgestudeerd in biomedische wetenschappen aan de Vrije Universiteit Amsterdam en geestelijke gezondheidskunde aan de Universiteit Maastricht. In 2011 rondde zij haar promotieonderzoek af, waarin zij zich richtte op de rol van voeding en genetische factoren in cognitieve veroudering.');
/*!40000 ALTER TABLE `support_experts` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_faq
CREATE TABLE IF NOT EXISTS `support_faq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(10) NOT NULL DEFAULT 'en',
  `question` text NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lang` (`lang`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_faq: ~2 rows (approximately)
/*!40000 ALTER TABLE `support_faq` DISABLE KEYS */;
INSERT INTO `support_faq` (`id`, `lang`, `question`, `answer`) VALUES
	(1, 'en', 'what is this site', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pellentesque lacus rutrum lobortis sagittis. Donec cursus urna in mauris gravida congue. Quisque convallis vel tortor in elementum. Mauris eget interdum est. Pellentesque fringilla vulputate nunc eu imperdiet. Nam vel posuere ante. Aenean accumsan volutpat pellentesque. Phasellus in turpis ligula. Etiam sed orci aliquam, consequat purus et, vestibulum neque. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam scelerisque mollis interdum. Aenean eget dignissim urna. Praesent nunc sem, gravida vel laoreet et, auctor nec nulla. Etiam lobortis ornare nulla ut luctus.</p>\r\n\r\n<p>Ut quis nibh ut augue mattis accumsan in vitae orci. Pellentesque vel enim ac tortor dictum ornare eget at ante. Aliquam at eleifend tellus, in tempus ipsum. Vestibulum condimentum, mauris eu posuere semper, est lectus consequat diam, ac tincidunt augue magna sit amet nulla. Donec congue enim varius neque scelerisque, in blandit libero ultrices. Praesent tristique, enim sit amet placerat suscipit, urna sem elementum justo, ut sollicitudin augue est ac felis. Curabitur tincidunt erat diam, a viverra nunc interdum in. Sed et dolor cursus, blandit ante quis, tincidunt nunc. Integer vel mi aliquet, commodo augue non, pretium est.</p>\r\n\r\n<p>Cras posuere ultrices augue, vitae congue urna accumsan eu. Duis lacus enim, faucibus eu rhoncus eu, luctus non velit. Aenean gravida auctor ultricies. Mauris interdum ligula a auctor blandit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque et eros a libero egestas mattis a at nunc. Curabitur sem quam, malesuada sed arcu a, euismod ullamcorper arcu. Nam tincidunt imperdiet urna, sed sodales arcu pulvinar in. Phasellus at feugiat massa, ultricies eleifend tortor. Fusce at malesuada lacus, posuere venenatis diam. Donec bibendum urna nec eros consectetur, non tincidunt purus venenatis. Ut id tellus lectus.</p>'),
	(2, 'en', 'do romain looking good?', 'Yes');
/*!40000 ALTER TABLE `support_faq` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_goals
CREATE TABLE IF NOT EXISTS `support_goals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(10) NOT NULL DEFAULT 'en',
  `id_riskfactor` int(11) DEFAULT NULL,
  `goal_name` varchar(255) DEFAULT NULL,
  `goal_nb` int(11) DEFAULT NULL,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`lang`),
  KEY `riskfactor_FK` (`id_riskfactor`,`lang`),
  CONSTRAINT `riskfactor_FK` FOREIGN KEY (`id_riskfactor`, `lang`) REFERENCES `support_riskfactors` (`id`, `lang`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_goals: ~27 rows (approximately)
/*!40000 ALTER TABLE `support_goals` DISABLE KEYS */;
INSERT INTO `support_goals` (`id`, `lang`, `id_riskfactor`, `goal_name`, `goal_nb`, `description`, `image_url`) VALUES
	(1, 'en', 4, 'Get walking', 1, '<ul>\r\n	<li>Aim to go for a 30-minute walk each day or a 60-minute walk four times a week.</li>\r\n	<li>Use a pedometer and aim to take 10,000 steps a day. Start slowly and build up to the 10,000. Week one:&nbsp; Aim for 5,000 steps per day. Week two: Aim for 7,000 steps. Week three: Aim to hit 10,000 steps per day. You can buy a pedometer in most chemists and sports supply shops at a reasonable price.</li>\r\n	<li>(A pedometer is a device, usually portable, that counts each step a person takes by detecting the movement of the person&rsquo;s hips.)</li>\r\n	<li>Only use the car when absolutely necessary. Walk to the shops and to visit friends and family.</li>\r\n	<li>Take the stairs instead of the lift.</li>\r\n</ul>', 'images/goal-walking.jpg'),
	(2, 'en', 4, 'Work out', 2, '<ul>\r\n	<li>Join an exercise class and work out twice a week. &nbsp;</li>\r\n	<li>Get on your bike and cycle to work, to the shops or to visit family or friends.</li>\r\n</ul>', 'images/goal-exercise.jpg'),
	(3, 'en', 4, 'Do it yourself', 3, '<ul>\r\n	<li>Join a local running or walking club, or start your own.</li>\r\n	<li>Buy or rent a fitness DVD and exercise at home.</li>\r\n</ul>', 'images/goal-doityourself.jpg'),
	(4, 'en', 4, 'Try something new', 4, '<ul>\r\n	<li>Try swimming. If you don&rsquo;t know how to swim, check out your local pool for information on adult swimming lessons.&nbsp;</li>\r\n	<li>Take a dance class &ndash; it&rsquo;s a great way to meet people and get fit.</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(5, 'en', 4, 'Set your own Goal!', 5, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(6, 'en', 9, 'Goal 1', 1, '<ul>\r\n	<li>Include one portion of beans in your daily lunch or your main evening meal, three times per week</li>\r\n	<li>Eat two pieces of fruit a day and three portions of vegetables (= 3 heaped tablespoons)</li>\r\n</ul>', 'images/riskfactors/diet/goal.jpg'),
	(7, 'en', 9, 'Goal 2', 2, '<ul>\r\n	<li>Eat less salt and sugar. Processed foods have a lot of salt and sugar added, so this will be easier to do if you prepare and cook your own meals</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Choose food only from the Mediterranean Diet</li>\r\n	<li>Use olive oil and limit butter, margarine and cream to one serving per day</li>\r\n	<li>Eat three servings of nuts per week (1 serving = a handful)</li>\r\n</ul>', 'images/riskfactors/diet/goal.jpg'),
	(8, 'en', 9, 'Goal 3', 3, '<ul>\r\n	<li>Eat less red meat and replace it with oily fish or white meat twice a week</li>\r\n	<li>Replace white bread, pasta and rice with whole-grain bread, pasta and rice</li>\r\n	<li>Eat a tomato-based sauce at least twice a week</li>\r\n</ul>', 'images/riskfactors/diet/goal.jpg'),
	(9, 'en', 9, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(10, 'en', 8, 'Goal 1', 1, '<ul>\r\n	<li>Learn to play an instrument or learn a new dance</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Visit your local community centre and offer your services as a volunteer</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Take an evening adult education class</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(11, 'en', 8, 'Goal 2', 2, '<ul>\r\n	<li>Do a crossword puzzle every day</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Aim to read one book per month or read the newspaper every day</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(12, 'en', 8, 'Goal 3', 3, '<ul>\r\n	<li>Aim to get together with family or friends at least once a week</li>\r\n	<li>Get your friends together and have each of you host a games night each week</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(13, 'en', 8, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(14, 'en', 6, 'Goal 1', 1, '<ul>\r\n	<li>Keep your drinking below the recommended limits</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>&nbsp;Drink alcohol only when eating a meal</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(15, 'en', 6, 'Goal 2', 2, '<ul>\r\n	<li>Avoid binge drinking. Take longer to finish a drink and avoid pressure from others to &lsquo;keep up&rsquo;</li>\r\n</ul>\r\n\r\n<p>&nbsp;and/or</p>\r\n\r\n<ul>\r\n	<li>Instead of going to the pub to socialise, choose other options that don&rsquo;t involve alcohol, such as movies, shows and outdoor activities</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(16, 'en', 6, 'Goal 3', 3, '<ul>\r\n	<li>Take up a new hobby or activity that gives you a new focus</li>\r\n</ul>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Become a volunteer in one of your local community groups</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(17, 'en', 6, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(18, 'en', 7, 'Goal 1', 1, '<ul>\r\n	<li>Use a pedometer and aim to take 10,000 steps a day. Start slowly and build up to the 10,000. Week one: Aim for 5,000 steps per day. Week two: Aim for 7,000 steps. Week three: Aim to hit 10,000 steps per day. You can buy a pedometer in most chemists and sports supply shops at a reasonable price.</li>\r\n	<li>(A pedometer is a device, usually portable, that counts each step a person takes by detecting the movement of the person&rsquo;s hips)</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(19, 'en', 7, 'Goal 2', 2, '<ul>\r\n	<li>Eat more fruit and vegetables. Make sure you have at least five portions per day. A portion of vegetables is three heaped tablespoons and a portion of fruit is a regular size apple, a banana, or two small plums or mandarin oranges.&nbsp;</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(20, 'en', 7, 'Goal 3', 3, '<ul>\r\n	<li>Go for a 20-minute walk four times a week. Start at a pace that is comfortable for you. Take a few weeks to build up to a faster pace &ndash; enough to get your heart beating faster than normal.</li>\r\n</ul>\r\n', 'images/goal-somethingnew.jpg'),
	(21, 'en', 7, 'Goal 4', 4, '<ul>\r\n	<li>Replace butter or margarine with &nbsp;monounsaturated oils, for example olive oil or a spread based on olive oil.</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(22, 'en', 7, 'Goal 5', 5, '<ul>\r\n	<li>Replace white bread, rice and pasta with whole-grain versions. Cut down on your serving sizes. Try using a smaller plate or bowl.</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(23, 'en', 7, 'Set your own Goal!', 6, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(24, 'en', 1, 'Goal 1', 1, '<ul>\r\n	<li>Quit or cut down on smoking</li>\r\n	<li>See our Smoking page here</li>\r\n	<li>Get more exercise</li>\r\n	<li>Aim to be physically active every day for at least 20 minutes. See our Physical Activity page here.</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(25, 'en', 1, 'Goal 2', 2, '<ul>\r\n	<li>If you drink alcohol on a regular/daily basis, aim to cut down</li>\r\n	<li>Avoid binge drinking</li>\r\n	<li>See our Alcohol page here</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(26, 'en', 1, 'Goal 3', 3, '<ul>\r\n	<li>Lose weight if needed</li>\r\n	<li>Aim for 1lb per week in order to maintain weight loss</li>\r\n	<li>Cut out/down on salt</li>\r\n	<li>See our Diet and Obesity pages here</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(27, 'en', 1, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg'),
	(28, 'en', 5, 'Goal 1', 1, '<ul>\r\n	<li>Quit Smoking!</li>\r\n</ul>\r\n\r\n<p>Your local chemist will have information on the best help available</p>\r\n\r\n<ul>\r\n	<li>Talk to your GP</li>\r\n	<li>Ask your family and friends for support and to join you in quitting!</li>\r\n	<li>Contact a quit smoking programme</li>\r\n</ul>\r\n', 'images/goal-somethingnew.jpg'),
	(29, 'en', 5, 'Goal 2', 2, '\r\n<ul>\r\n	<li>Cut down!</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(30, 'en', 10, 'Goal 1', 1, '<ul>\r\n	<li>Cut out butter and replace it with olive oil</li>\r\n	<li>Eat two pieces of fruit and three portions of vegetables a day</li>\r\n</ul>\r\n\r\n<p>See our Diet page <a href="infos-riskfactor.html?riskfactor=9">here</a></p>', 'images/goal-somethingnew.jpg'),
	(31, 'en', 10, 'Goal 2', 2, '<ul>\r\n	<li>Quit smoking or cut down</li>\r\n</ul>\r\n\r\n<p>See <a href="infos-riskfactor.html?riskfactor=5">here </a>for more information</p>\r\n\r\n<p>and/or</p>\r\n\r\n<ul>\r\n	<li>Cut down on alcohol. Stay below the recommended alcohol limit</li>\r\n</ul>\r\n\r\n<p>See our Alcohol page <a href="infos-riskfactor.html?riskfactor=6">here</a></p>', 'images/goal-somethingnew.jpg'),
	(32, 'en', 10, 'Goal 3', 3, '<ul>\r\n	<li>Go for a fast walk or bicycle ride for 20 minutes four times a week</li>\r\n</ul>\r\n\r\n<p>See our Physical Activity page <a href="infos-riskfactor.html?riskfactor=4">here</a></p>', 'images/goal-somethingnew.jpg'),
	(33, 'en', 10, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>\r\n<p>If you need to lose weight, visit our <a href="infos-riskfactor.html?riskfactor=4">Physical Activity</a> and <a href="infos-riskfactor.html?riskfactor=9">Diet </a>pages</p>', 'images/goal-ownprogram.jpg'),
	(34, 'en', 2, 'Goal 1', 1, '<ul>\r\n	<li>Get outdoors and walk, cycle or just spend time in the garden. Aim for 30 minutes a day</li>\r\n	<li>Eat a healthy, balanced diet. See our Diet page here</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(35, 'en', 2, 'Goal 2', 2, '<ul>\r\n	<li>Talk to your family and friends</li>\r\n	<li>Spend one evening a week on a social activity</li>\r\n</ul>\r\n\r\n<p>Sometimes we need extra support when we feel low or depressed</p>', 'images/goal-somethingnew.jpg'),
	(36, 'en', 2, 'Goal 3', 3, '<ul>\r\n	<li>Talk to your GP and ask about counselling services that are available</li>\r\n	<li>If you don&rsquo;t wish to talk to your GP, most counselling services take self-referrals. See our link below to contact a service</li>\r\n</ul>', 'images/goal-somethingnew.jpg'),
	(37, 'en', 2, 'Set your own Goal!', 4, '<textarea class="form-control" rows="5"></textarea>', 'images/goal-ownprogram.jpg');
/*!40000 ALTER TABLE `support_goals` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_goals_users
CREATE TABLE IF NOT EXISTS `support_goals_users` (
  `id_goal` int(11) NOT NULL,
  `id_user` varchar(7) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` text,
  PRIMARY KEY (`id_goal`,`id_user`,`timestamp`),
  KEY `user_FK_idx` (`id_user`),
  CONSTRAINT `goal_FK` FOREIGN KEY (`id_goal`) REFERENCES `support_goals` (`id`),
  CONSTRAINT `user_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_goals_users: ~1 rows (approximately)
/*!40000 ALTER TABLE `support_goals_users` DISABLE KEYS */;
INSERT INTO `support_goals_users` (`id_goal`, `id_user`, `timestamp`, `comment`) VALUES
	(4, '1101002', '2014-07-14 14:08:59', 'bidule');
/*!40000 ALTER TABLE `support_goals_users` ENABLE KEYS */;


-- Dumping structure for table inmindd.support_riskfactors
CREATE TABLE IF NOT EXISTS `support_riskfactors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(10) NOT NULL DEFAULT 'en',
  `name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `desc_keep` text,
  `desc_improv` text,
  `sources` text,
  PRIMARY KEY (`id`,`lang`),
  KEY `lang` (`lang`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.support_riskfactors: ~13 rows (approximately)
/*!40000 ALTER TABLE `support_riskfactors` DISABLE KEYS */;
INSERT INTO `support_riskfactors` (`id`, `lang`, `name`, `image_url`, `desc_keep`, `desc_improv`, `sources`) VALUES
	(1, 'en', 'Blood Pressure', 'images/libra/blood_pressure_amber.png', '<h1>High Blood Pressure</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us your blood pressure is within the normal range. Keep this up!</p>\r\n\r\n<p>Below you will find information to help you maintain your current blood pressure levels</p>\r\n\r\n<h3>What is high blood pressure?</h3>\r\n\r\n<ul>\r\n	<li>You have <strong>high blood pressure</strong> (medically known as hypertension) if check-ups on separate occasions show your blood pressure to be <strong>140/90mmHg or higher</strong>.</li>\r\n	<li>\r\n	<p>A blood pressure reading below <strong>130/80mmHg</strong> is considered <strong>normal.</strong></p>\r\n	</li>\r\n</ul>\r\n\r\n<h3>Who is at risk?</h3>\r\n\r\n<p>You are more likely to have high blood pressure as you get older. High blood pressure often has no clear cause, but your risk is higher if you:</p>\r\n\r\n<ul>\r\n	<li>are overweight</li>\r\n	<li>have a relative with high blood pressure</li>\r\n	<li>don&#39;t eat many fruits and vegetables</li>\r\n	<li>don&#39;t take enough exercise</li>\r\n	<li>drink a lot of alcohol</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>You can take steps to prevent high blood pressure by:</p>\r\n\r\n<ul>\r\n	<li>losing weight if you need to</li>\r\n	<li>exercising regularly</li>\r\n	<li>eating a healthy diet</li>\r\n	<li>cutting back if you drink a lot of alcohol</li>\r\n	<li>stopping smoking</li>\r\n</ul>', '<h1>High Blood Pressure</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us your blood pressure is higher than the recommended limits.</p>\r\n\r\n<p>Below you will find information to help you lower your blood pressure to a healthy level.</p>\r\n\r\n<p>Your doctor may have recommended medication to help lower your blood pressure. However, it is still important for you to be physically active, eat a healthy and balanced diet, limit your drinking and not smoke.</p>\r\n\r\n<h3>What is high blood pressure?</h3>\r\n\r\n<ul>\r\n	<li>You have <strong>high blood pressure</strong> (medically known as hypertension) if check-ups on separate occasions show your blood pressure to be <strong>140/90mmHg or higher</strong>.</li>\r\n	<li>\r\n	<p>A blood pressure reading below <strong>130/80mmHg</strong> is considered <strong>normal.</strong></p>\r\n	</li>\r\n</ul>\r\n\r\n<h3>Who is at risk?</h3>\r\n\r\n<p>You are more likely to have high blood pressure as you get older. High blood pressure often has no clear cause, but your risk is higher if you:</p>\r\n\r\n<ul>\r\n	<li>are overweight</li>\r\n	<li>have a relative with high blood pressure</li>\r\n	<li>don&#39;t eat many fruits and vegetables</li>\r\n	<li>don&#39;t take enough exercise</li>\r\n	<li>drink a lot of alcohol</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>You can take steps to prevent high blood pressure by:</p>\r\n\r\n<ul>\r\n	<li>losing weight if you need to</li>\r\n	<li>exercising regularly</li>\r\n	<li>eating a healthy diet</li>\r\n	<li>cutting back if you drink a lot of alcohol</li>\r\n	<li>stopping smoking</li>\r\n</ul>', '<h3>Further Supports</h3>\r\n\r\n<p><a href="http://www.Irishheart.ie">www.Irishheart.ie</a></p>'),
	(2, 'en', 'Mood', 'images/libra/mood_amber.png', '<h1>Mood</h1>\r\n\r\n<h2>Keep this up!</h2>\r\n\r\n<p>Your profile tells us that your general mood is good. Keep this up!</p>\r\n\r\n<p>Feeling low can have an impact on our brain health.</p>\r\n\r\n<p>Below you will find information to help you maintain you current overall mood level.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/mood/img1.png" /></p>\r\n\r\n<p>People with low mood often have more trouble concentrating and performing normal mental activities.</p>\r\n\r\n<p>Low or depressed mood can be treated by eating a healthy diet, taking exercise, being more social and talking to a therapist. All these things will help improve your cognitive activity and in turn your cognitive function.</p>\r\n\r\n<p>People who feel depressed or anxious often have trouble sleeping. Poor sleep is known to reduce our cognitive function.</p>\r\n\r\n<ul>\r\n	<li>Cognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension.</li>\r\n</ul>\r\n\r\n<h3>Know your mood</h3>\r\n\r\n<p>People who are depressed may:</p>\r\n\r\n<ul>\r\n	<li>feel sad or hopeless</li>\r\n	<li>lose interest or pleasure in life</li>\r\n	<li>feel guilty or have low self-worth</li>\r\n	<li>find their sleep pattern or appetite is disturbed</li>\r\n	<li>feel tired</li>\r\n	<li>have trouble concentrating</li>\r\n</ul>\r\n\r\n<p>Speak to your GP/health professional</p>\r\n\r\n<p>Contact support services</p>\r\n\r\n<p>Exercise and get outdoors. Visit our Physical Activity page <a href="infos-riskfactor.html?riskfactor=4">here</a></p>\r\n\r\n<p><img alt="" src="images/riskfactors/mood/img2.png" /></p>\r\n\r\n<p>Eat a balanced diet. Visit our Diet page <a href="infos-riskfactor.html?riskfactor=9">here</a></p>\r\n\r\n<p>Avoid alcohol</p>\r\n\r\n<p>Get enough rest and sleep</p>\r\n\r\n<p>Talk to family and friends</p>\r\n\r\n<p>Socialise more. See information here</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Getting enough quality sleep is good for your mood.</p>\r\n\r\n<p>Here are some tips to help you get a good night&rsquo;s sleep:</p>\r\n\r\n<ul>\r\n	<li>Try to go to bed at the same time every night and get up at the same time every morning, even at the weekends!</li>\r\n	<li>Avoid watching TV or using a laptop, tablet or mobile phone in bed.</li>\r\n	<li>Don&rsquo;t go to bed hungry or on a full stomach.</li>\r\n	<li>Make sure your bedroom is cool, dark and quiet.</li>\r\n	<li>Limit daytime naps.</li>\r\n	<li>Be physically active each day.</li>\r\n	<li>Get help managing your stress.</li>\r\n	<li>Don&rsquo;t lie awake worrying about not sleeping. Get up, go to another room and read or watch something boring on the TV. When you start to feel tired, return to bed</li>\r\n</ul>\r\n', '<h1>Mood</h1>\r\n\r\n<h2>Room for improvement</h2>\r\n\r\n<p>Your profile tells us that your general mood could be improved.</p>\r\n\r\n<p>Feeling low can have an impact on our brain health.</p>\r\n\r\n<p>Below you will find information to help you improve your overall mood.</p>\r\n\r\n<p>You will also find your personalised plan.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/mood/img1.png" /></p>\r\n\r\n<p>People with low mood often have more trouble concentrating and performing normal mental activities.</p>\r\n\r\n<p>Low or depressed mood can be treated by eating a healthy diet, taking exercise, being more social and talking to a therapist. All these things will help improve your cognitive activity and in turn your cognitive function.</p>\r\n\r\n<p>People who feel depressed or anxious often have trouble sleeping. Poor sleep is known to reduce our cognitive function.</p>\r\n\r\n<ul>\r\n	<li>Cognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension.</li>\r\n</ul>\r\n\r\n<h3>Know your mood</h3>\r\n\r\n<p>People who are depressed may:</p>\r\n\r\n<ul>\r\n	<li>feel sad or hopeless</li>\r\n	<li>lose interest or pleasure in life</li>\r\n	<li>feel guilty or have low self-worth</li>\r\n	<li>find their sleep pattern or appetite is disturbed</li>\r\n	<li>feel tired</li>\r\n	<li>have trouble concentrating</li>\r\n</ul>\r\n\r\n<p>Speak to your GP/health professional</p>\r\n\r\n<p>Contact support services</p>\r\n\r\n<p>Exercise and get outdoors. Visit our Physical Activity page <a href="infos-riskfactor.html?riskfactor=4">here</a></p>\r\n\r\n<p><img alt="" src="images/riskfactors/mood/img2.png" /></p>\r\n\r\n<p>Eat a balanced diet. Visit our Diet page <a href="infos-riskfactor.html?riskfactor=9">here</a></p>\r\n\r\n<p>Avoid alcohol</p>\r\n\r\n<p>Get enough rest and sleep</p>\r\n\r\n<p>Talk to family and friends</p>\r\n\r\n<p>Socialise more. See information here</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Getting enough quality sleep is good for your mood.</p>\r\n\r\n<p>Here are some tips to help you get a good night&rsquo;s sleep:</p>\r\n\r\n<ul>\r\n	<li>Try to go to bed at the same time every night and get up at the same time every morning, even at the weekends!</li>\r\n	<li>Avoid watching TV or using a laptop, tablet or mobile phone in bed.</li>\r\n	<li>Don&rsquo;t go to bed hungry or on a full stomach.</li>\r\n	<li>Make sure your bedroom is cool, dark and quiet.</li>\r\n	<li>Limit daytime naps.</li>\r\n	<li>Be physically active each day.</li>\r\n	<li>Get help managing your stress.</li>\r\n	<li>Don&rsquo;t lie awake worrying about not sleeping. Get up, go to another room and read or watch something boring on the TV. When you start to feel tired, return to bed</li>\r\n</ul>\r\n', ''),
	(3, 'en', 'Diabetes', 'images/libra/diabetes-amber.png', '<h1>Diabetes</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you do not have diabetes. This is good news for your long term brain health!&nbsp; Below you will find information on lifestyle choices that can help prevent diabetes in the future.</p>\r\n\r\n<h3>Who is at risk?</h3>\r\n\r\n<p><strong>You are more at risk of getting Type 2 diabetes if you are:</strong></p>\r\n\r\n<ul>\r\n	<li>Over 40 years of age</li>\r\n	<li>Have a parent or brother/sister with diabetes</li>\r\n	<li>Had&nbsp;diabetes during a pregnancy</li>\r\n	<li>Are overweight</li>\r\n	<li>Do not take 30 minutes of exercise daily</li>\r\n	<li>Have high blood pressure</li>\r\n	<li>Have high cholesterol levels</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img1.png" /></p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<ul>\r\n	<li>Eat regularly</li>\r\n	<li>Watch your serving size</li>\r\n	<li>Follow a healthy eating plan. One good plan is the Mediterranean Diet, which is low in refined sugars and fat. For more information, visit our Diet page <a href="infos-riskfactor.html?riskfactor=9">here</a><br />\r\n	<img alt="" src="images/riskfactors/diabetes/img2.png" /></li>\r\n	<li>Choose low-fat options when eating meat, poultry, dairy products and spread</li>\r\n	<li>Eat a wide variety of fresh fruit and vegetables</li>\r\n	<li>Choose wholegrain bread, pasta and cereals</li>\r\n	<li>Keep foods high in fat and sugar to a minimum</li>\r\n	<li>Get active by taking 30 minutes of physical exercise a day. For more information, visit our Physical Activity page <a href="infos-riskfactor.html?riskfactor=4">here</a><br />\r\n	<img alt="" src="images/riskfactors/diabetes/img3.png" /></li>\r\n</ul>', '<h1>Diabetes</h1>\r\n\r\n<h2 class="h-color">Remember to manage well</h2>\r\n\r\n<p>Your profile tells us that you have diabetes.Besides taking medication, you can manage diabetes through diet and exercise. Below you will find information to help you.</p>\r\n\r\n<p>If you manage your diabetes well, you can protect your brain health and your overall health.</p>\r\n\r\n<p><strong>You are more at risk of getting Type 2 diabetes if you are:</strong></p>\r\n\r\n<ul>\r\n	<li>Over 40 years of age</li>\r\n	<li>Have a parent or brother/sister with diabetes</li>\r\n	<li>Had&nbsp;diabetes during a pregnancy</li>\r\n	<li>Are overweight</li>\r\n	<li>Do not take 30 minutes of exercise daily</li>\r\n	<li>Have high blood pressure</li>\r\n	<li>Have high cholesterol levels</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img1.png" /></p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<ul>\r\n	<li>Eat regularly</li>\r\n	<li>Watch your serving size</li>\r\n	<li>Follow a healthy eating plan. One good plan is the Mediterranean Diet, which is low in refined sugars and fat. For more information, visit our Diet page <a href="infos-riskfactor.html?riskfactor=9">here</a><br />\r\n	<img alt="" src="images/riskfactors/diabetes/img2.png" /></li>\r\n	<li>Choose low-fat options when eating meat, poultry, dairy products and spread</li>\r\n	<li>Eat a wide variety of fresh fruit and vegetables</li>\r\n	<li>Choose wholegrain bread, pasta and cereals</li>\r\n	<li>Keep foods high in fat and sugar to a minimum</li>\r\n	<li>Get active by taking 30 minutes of physical exercise a day. For more information, visit our Physical Activity page <a href="infos-riskfactor.html?riskfactor=4">here</a><br />\r\n	<img alt="" src="images/riskfactors/diabetes/img3.png" /></li>\r\n</ul>', '<h3>Other Supports</h3>\r\n\r\n<ul>\r\n	<li><a href="http://www.diabetes.ie/">www.diabetes.ie</a></li>\r\n</ul>\r\n\r\n<p>Site provides information on attending a diabetic clinic, self-management, diet and exercise advice</p>'),
	(4, 'en', 'Physical Exercise', 'images/libra/physical_exercise_amber.png', '<h1>Physical Activity</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you are doing enough physical activity to help protect your brain health. Well done, keep this up!</p>\r\n\r\n<p>Below you will find information on the importance of being physically active. You will also find tips to help you maintain your current levels of activity, you may also find new ways to continue being physically active.</p>\r\n\r\n<h3>Did you know?</h3>\r\n\r\n<strong>Being physically active is good for your brain health!</strong><br />\r\n	Being physically active is one of the most important steps you can take to improve your overall health and wellbeing.<br />\r\n	Researchers have found a link between physical activity and brain health. They believe that exercise may help us preserve cognitive function and activity and in turn protect us against dementia.<br />\r\n	People who take regular exercise lower their risk of dementia by up to 30%.<br />\r\n	Regular physical exercise helps the blood flow to the brain.<br />\r\n	It also helps lower the risk of stroke, heart attack and diabetes. These are all risk factors for dementia.<br />\r\n	Being physically active can have many different health benefits.<br />\r\n	As well as reducing our risk of dementia, it can improve our mood and our overall level of fitness. It can also keep our hearts healthy.&nbsp;<br />\r\n\r\n<p>Being active can also:&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Lower your risk of heart disease&nbsp;</li>\r\n	<li>Maintain and/or improve your cholesterol level, your blood sugar levels, and your blood pressure.</li>\r\n</ul>\r\n\r\n<h2>What should you aim for?</h2>\r\n\r\n<ul>\r\n	<li>Healthy adults aged 18&ndash;64 should spend at least 150 minutes a week doing some type of moderate physical activity.</li>\r\n	<li>Another option is to spend at least 75 minutes a week doing some type of vigorous activity or a third option would be a combination of moderate and vigorous activity.</li>\r\n	<li>One way to get your recommended 150 minutes a week is to do 30 minutes of physical exercise five days of the week.</li>\r\n</ul>\r\n\r\n<p>Moderate physical activities take a little effort, but you should be able to talk while doing them.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>walking fast&nbsp;</li>\r\n	<li>water aerobics</li>\r\n	<li>riding a bike on level ground or with a few hills</li>\r\n	<li>playing tennis for fun</li>\r\n	<li>pushing a lawn mower</li>\r\n</ul>\r\n\r\n<p>Vigorous physical activities require a little more effort and make you breathe harder and faster.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>running&nbsp;</li>\r\n	<li>&nbsp;jogging</li>\r\n	<li>&nbsp;aerobics</li>\r\n	<li>&nbsp;fast cycling</li>\r\n	<li>&nbsp;any tasks that require carrying, lifting or digging</li>\r\n</ul>\r\n\r\n<p>Be physically active throughout the day!</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Be active while travelling:</p>\r\n\r\n<ul>\r\n	<li>Get off the bus a stop earlier.</li>\r\n	<li>Go for a walk at lunchtime.</li>\r\n	<li>Leave the car at home and walk to the shops.&nbsp;</li>\r\n	<li>Take the stairs rather than the lift.</li>\r\n	<li>If you sit at a desk for most of the day, make sure you take a break every 30 minutes and stretch your legs.</li>\r\n	<li>Remember, gardening, washing the windows and washing the car all count as physical activity.</li>\r\n	<li>Do tasks around the house that involve lifting, carrying or digging.</li>\r\n	<li>Walk the dog more often.</li>\r\n	<li>Push a pram while walking. If you know a new parent, offer to take the baby for a walk!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Have fun!</p>\r\n\r\n<ul>\r\n	<li>If running is not for you, you might like to try an aerobics class or some exercise classes. Another fun option is a dance class.</li>\r\n	<li>Taking up yoga is another way to improve your physical health. It also has many benefits for your mental health.</li>\r\n	<li>Remember, small changes to your daily routine can make a big difference.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Move around!</p>\r\n\r\n<ul>\r\n	<li>Get active and sit less.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n	<li>Do 30 minutes of physical activity on five days of the week.</li>\r\n	<li>Go for a bicycle ride.</li>\r\n	<li>Invite friends to join you in a dance or exercise class.</li>\r\n	<li>Go for a quick jog or brisk walk for 15 minutes before breakfast every day.</li>\r\n	<li>Remember to choose something you enjoy. You&rsquo;ll be much more likely to keep it up then.</li>\r\n	<li>You could also check out local community noticeboards to see if there are any running groups in your area. Running groups have become quite common and offer beginners the chance to start at an easy pace and gradually increase. There are many fun 5k runs/walks around the country every weekend. Joining a running club is also a great way to meet new people.</li>\r\n</ul>', '<h1>Physical Activity</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that you could take more exercise than you now do.<br />\r\nMaking small changes now could make a big difference to your brain health in later years.<br />\r\nBelow you will find information to get you started or to help you take more exercise than you now do.<br />\r\nClick on My Goals to see your personalised plan.</p>\r\n\r\n<h3>Did you know?</h3>\r\n\r\n	<strong>Being physically active is good for your brain health!</strong><br />\r\n	Being physically active is one of the most important steps you can take to improve your overall health and wellbeing.<br />\r\n	Researchers have found a link between physical activity and brain health. They believe that exercise may help us preserve cognitive function and activity and in turn protect us against dementia.<br />\r\n	People who take regular exercise lower their risk of dementia by up to 30%.<br />\r\n	Regular physical exercise helps the blood flow to the brain.<br />\r\n	It also helps lower the risk of stroke, heart attack and diabetes. These are all risk factors for dementia.<br />\r\n	Being physically active can have many different health benefits.<br />\r\n	As well as reducing our risk of dementia, it can improve our mood and our overall level of fitness. It can also keep our hearts healthy.&nbsp;<br />\r\n\r\n<p>Being active can also:&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Lower your risk of heart disease&nbsp;</li>\r\n	<li>Maintain and/or improve your cholesterol level, your blood sugar levels, and your blood pressure.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What should you aim for?</h3>\r\n\r\n<ul>\r\n	<li>Healthy adults aged 18&ndash;64 should spend at least 150 minutes a week doing some type of moderate physical activity.</li>\r\n	<li>Another option is to spend at least 75 minutes a week doing some type of vigorous activity or a third option would be a combination of moderate and vigorous activity.</li>\r\n	<li>One way to get your recommended 150 minutes a week is to do 30 minutes of physical exercise five days of the week.</li>\r\n</ul>\r\n\r\n<p>Moderate physical activities take a little effort, but you should be able to talk while doing them.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>walking fast</li>\r\n	<li>water aerobics</li>\r\n	<li>riding a bike on level ground or with a few hills</li>\r\n	<li>playing tennis for fun</li>\r\n	<li>pushing a lawn mower</li>\r\n</ul>\r\n\r\n<p>Vigorous physical activities require a little more effort and make you breathe harder and faster.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>running</li>\r\n	<li>&nbsp;jogging</li>\r\n	<li>&nbsp;aerobics</li>\r\n	<li>&nbsp;fast cycling</li>\r\n	<li>&nbsp;any tasks that require carrying, lifting or digging</li>\r\n</ul>\r\n\r\n<p>Be physically active throughout the day!</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Be active while travelling:</h3>\r\n\r\n<ul>\r\n	<li>Get off the bus a stop earlier.</li>\r\n	<li>Go for a walk at lunchtime.</li>\r\n	<li>Leave the car at home and walk to the shops.&nbsp;</li>\r\n	<li>Take the stairs rather than the lift.</li>\r\n	<li>If you sit at a desk for most of the day, make sure you take a break every 30 minutes and stretch your legs.</li>\r\n	<li>Remember, gardening, washing the windows and washing the car all count as physical activity.</li>\r\n	<li>Do tasks around the house that involve lifting, carrying or digging.</li>\r\n	<li>Walk the dog more often.</li>\r\n	<li>Push a pram while walking. If you know a new parent, offer to take the baby for a walk!</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/bike.jpg" alt="" /></p>\r\n\r\n<h3>Have fun!</h3>\r\n\r\n<ul>\r\n	<li>If running is not for you, you might like to try an aerobics class or some exercise classes. Another fun option is a dance class.</li>\r\n	<li>Taking up yoga is another way to improve your physical health. It also has many benefits for your mental health.</li>\r\n	<li>Remember, small changes to your daily routine can make a big difference.</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/yoga.jpg" alt="" /></p>\r\n\r\n<h3>Move around!</h3>\r\n\r\n<ul>\r\n	<li>Get active and sit less.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n	<li>Do 30 minutes of physical activity on five days of the week.</li>\r\n	<li>Go for a bicycle ride.</li>\r\n	<li>Invite friends to join you in a dance or exercise class.</li>\r\n	<li>Go for a quick jog or brisk walk for 15 minutes before breakfast every day.</li>\r\n	<li>Remember to choose something you enjoy. You&rsquo;ll be much more likely to keep it up then.</li>\r\n	<li>You could also check out local community noticeboards to see if there are any running groups in your area. Running groups have become quite common and offer beginners the chance to start at an easy pace and gradually increase. There are many fun 5k runs/walks around the country every weekend. Joining a running club is also a great way to meet new people.</li>\r\n	<li>If you are not physically active now, you will benefit by starting. Even a small amount of exercise at first is better than doing nothing at all.</li>\r\n	<li>You can start slowly and increase the amount you do.</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/couple.jpg" alt="" /></p>', '<h3>Further Supports</h3>\r\n\r\n<p><a href="http://www.getirelandactive.ie/">www.getirelandactive.ie</a></p>\r\n\r\n<ul>\r\n	<li>This site offers information and support on physical activity. It also provides information on local and national running/walking groups</li>\r\n</ul>\r\n\r\n<p><a href="http://www.irishheart.ie/">www.irishheart.ie</a></p>\r\n\r\n<ul>\r\n	<li>Irish Heart provides information on how to keep your heart healthy through diet and exercise changes. Looking after your heart health is important as a healthy heart has a positive impact on your brain health</li>\r\n</ul>\r\n\r\n<p>&nbsp;<a href="http://www.getirelandwalking.ie/">www.getirelandwalking.ie</a></p>\r\n\r\n<ul>\r\n	<li>Offers tips and advice on setting up walking groups and links to existing groups in your locality&nbsp;</li>\r\n</ul>'),
	(4, 'nl', 'Fisykal Exercise', 'images/libra/physical_exercise_amber.png', '<h1>Physical Activity</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you are doing enough physical activity to help protect your brain health. Well done, keep this up!</p>\r\n\r\n<p>Below you will find information on the importance of being physically active. You will also find tips to help you maintain your current levels of activity, you may also find new ways to continue being physically active.</p>\r\n\r\n<h3>Did you know?</h3>\r\n\r\n<strong>Being physically active is good for your brain health!</strong><br />\r\n	Being physically active is one of the most important steps you can take to improve your overall health and wellbeing.<br />\r\n	Researchers have found a link between physical activity and brain health. They believe that exercise may help us preserve cognitive function and activity and in turn protect us against dementia.<br />\r\n	People who take regular exercise lower their risk of dementia by up to 30%.<br />\r\n	Regular physical exercise helps the blood flow to the brain.<br />\r\n	It also helps lower the risk of stroke, heart attack and diabetes. These are all risk factors for dementia.<br />\r\n	Being physically active can have many different health benefits.<br />\r\n	As well as reducing our risk of dementia, it can improve our mood and our overall level of fitness. It can also keep our hearts healthy.&nbsp;<br />\r\n\r\n<p>Being active can also:&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Lower your risk of heart disease&nbsp;</li>\r\n	<li>Maintain and/or improve your cholesterol level, your blood sugar levels, and your blood pressure.</li>\r\n</ul>\r\n\r\n<h2>What should you aim for?</h2>\r\n\r\n<ul>\r\n	<li>Healthy adults aged 18&ndash;64 should spend at least 150 minutes a week doing some type of moderate physical activity.</li>\r\n	<li>Another option is to spend at least 75 minutes a week doing some type of vigorous activity or a third option would be a combination of moderate and vigorous activity.</li>\r\n	<li>One way to get your recommended 150 minutes a week is to do 30 minutes of physical exercise five days of the week.</li>\r\n</ul>\r\n\r\n<p>Moderate physical activities take a little effort, but you should be able to talk while doing them.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>walking fast&nbsp;</li>\r\n	<li>water aerobics</li>\r\n	<li>riding a bike on level ground or with a few hills</li>\r\n	<li>playing tennis for fun</li>\r\n	<li>pushing a lawn mower</li>\r\n</ul>\r\n\r\n<p>Vigorous physical activities require a little more effort and make you breathe harder and faster.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>running&nbsp;</li>\r\n	<li>&nbsp;jogging</li>\r\n	<li>&nbsp;aerobics</li>\r\n	<li>&nbsp;fast cycling</li>\r\n	<li>&nbsp;any tasks that require carrying, lifting or digging</li>\r\n</ul>\r\n\r\n<p>Be physically active throughout the day!</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Be active while travelling:</p>\r\n\r\n<ul>\r\n	<li>Get off the bus a stop earlier.</li>\r\n	<li>Go for a walk at lunchtime.</li>\r\n	<li>Leave the car at home and walk to the shops.&nbsp;</li>\r\n	<li>Take the stairs rather than the lift.</li>\r\n	<li>If you sit at a desk for most of the day, make sure you take a break every 30 minutes and stretch your legs.</li>\r\n	<li>Remember, gardening, washing the windows and washing the car all count as physical activity.</li>\r\n	<li>Do tasks around the house that involve lifting, carrying or digging.</li>\r\n	<li>Walk the dog more often.</li>\r\n	<li>Push a pram while walking. If you know a new parent, offer to take the baby for a walk!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Have fun!</p>\r\n\r\n<ul>\r\n	<li>If running is not for you, you might like to try an aerobics class or some exercise classes. Another fun option is a dance class.</li>\r\n	<li>Taking up yoga is another way to improve your physical health. It also has many benefits for your mental health.</li>\r\n	<li>Remember, small changes to your daily routine can make a big difference.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Move around!</p>\r\n\r\n<ul>\r\n	<li>Get active and sit less.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n	<li>Do 30 minutes of physical activity on five days of the week.</li>\r\n	<li>Go for a bicycle ride.</li>\r\n	<li>Invite friends to join you in a dance or exercise class.</li>\r\n	<li>Go for a quick jog or brisk walk for 15 minutes before breakfast every day.</li>\r\n	<li>Remember to choose something you enjoy. You&rsquo;ll be much more likely to keep it up then.</li>\r\n	<li>You could also check out local community noticeboards to see if there are any running groups in your area. Running groups have become quite common and offer beginners the chance to start at an easy pace and gradually increase. There are many fun 5k runs/walks around the country every weekend. Joining a running club is also a great way to meet new people.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Further Supports</p>\r\n\r\n<p><a href="http://www.getirelandactive.ie/">www.getirelandactive.ie</a></p>\r\n\r\n<ul>\r\n	<li>This site offers information and support on physical activity. It also provides information on local and national running/walking groups</li>\r\n</ul>\r\n\r\n<p><a href="http://www.irishheart.ie/">www.irishheart.ie</a></p>\r\n\r\n<ul>\r\n	<li>Irish Heart provides information on how to keep your heart healthy through diet and exercise changes. Looking after your heart health is important as a healthy heart has a positive impact on your brain health</li>\r\n</ul>\r\n\r\n<p>&nbsp;<a href="http://www.getirelandwalking.ie/">www.getirelandwalking.ie</a></p>\r\n\r\n<ul>\r\n	<li>Offers tips and advice on setting up walking groups and links to existing groups in your locality</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>', '<h1>Physical Activity</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that you could take more exercise than you now do.<br />\r\nMaking small changes now could make a big difference to your brain health in later years.<br />\r\nBelow you will find information to get you started or to help you take more exercise than you now do.<br />\r\nClick on My Goals to see your personalised plan.</p>\r\n\r\n<h3>Did you know?</h3>\r\n\r\n	<strong>Being physically active is good for your brain health!</strong><br />\r\n	Being physically active is one of the most important steps you can take to improve your overall health and wellbeing.<br />\r\n	Researchers have found a link between physical activity and brain health. They believe that exercise may help us preserve cognitive function and activity and in turn protect us against dementia.<br />\r\n	People who take regular exercise lower their risk of dementia by up to 30%.<br />\r\n	Regular physical exercise helps the blood flow to the brain.<br />\r\n	It also helps lower the risk of stroke, heart attack and diabetes. These are all risk factors for dementia.<br />\r\n	Being physically active can have many different health benefits.<br />\r\n	As well as reducing our risk of dementia, it can improve our mood and our overall level of fitness. It can also keep our hearts healthy.&nbsp;<br />\r\n\r\n<p>Being active can also:&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Lower your risk of heart disease&nbsp;</li>\r\n	<li>Maintain and/or improve your cholesterol level, your blood sugar levels, and your blood pressure.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What should you aim for?</h3>\r\n\r\n<ul>\r\n	<li>Healthy adults aged 18&ndash;64 should spend at least 150 minutes a week doing some type of moderate physical activity.</li>\r\n	<li>Another option is to spend at least 75 minutes a week doing some type of vigorous activity or a third option would be a combination of moderate and vigorous activity.</li>\r\n	<li>One way to get your recommended 150 minutes a week is to do 30 minutes of physical exercise five days of the week.</li>\r\n</ul>\r\n\r\n<p>Moderate physical activities take a little effort, but you should be able to talk while doing them.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>walking fast</li>\r\n	<li>water aerobics</li>\r\n	<li>riding a bike on level ground or with a few hills</li>\r\n	<li>playing tennis for fun</li>\r\n	<li>pushing a lawn mower</li>\r\n</ul>\r\n\r\n<p>Vigorous physical activities require a little more effort and make you breathe harder and faster.</p>\r\n\r\n<p>They include:</p>\r\n\r\n<ul>\r\n	<li>running</li>\r\n	<li>&nbsp;jogging</li>\r\n	<li>&nbsp;aerobics</li>\r\n	<li>&nbsp;fast cycling</li>\r\n	<li>&nbsp;any tasks that require carrying, lifting or digging</li>\r\n</ul>\r\n\r\n<p>Be physically active throughout the day!</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Be active while travelling:</h3>\r\n\r\n<ul>\r\n	<li>Get off the bus a stop earlier.</li>\r\n	<li>Go for a walk at lunchtime.</li>\r\n	<li>Leave the car at home and walk to the shops.&nbsp;</li>\r\n	<li>Take the stairs rather than the lift.</li>\r\n	<li>If you sit at a desk for most of the day, make sure you take a break every 30 minutes and stretch your legs.</li>\r\n	<li>Remember, gardening, washing the windows and washing the car all count as physical activity.</li>\r\n	<li>Do tasks around the house that involve lifting, carrying or digging.</li>\r\n	<li>Walk the dog more often.</li>\r\n	<li>Push a pram while walking. If you know a new parent, offer to take the baby for a walk!</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/bike.jpg" alt="" /></p>\r\n\r\n<h3>Have fun!</h3>\r\n\r\n<ul>\r\n	<li>If running is not for you, you might like to try an aerobics class or some exercise classes. Another fun option is a dance class.</li>\r\n	<li>Taking up yoga is another way to improve your physical health. It also has many benefits for your mental health.</li>\r\n	<li>Remember, small changes to your daily routine can make a big difference.</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/yoga.jpg" alt="" /></p>\r\n\r\n<h3>Move around!</h3>\r\n\r\n<ul>\r\n	<li>Get active and sit less.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n	<li>Do 30 minutes of physical activity on five days of the week.</li>\r\n	<li>Go for a bicycle ride.</li>\r\n	<li>Invite friends to join you in a dance or exercise class.</li>\r\n	<li>Go for a quick jog or brisk walk for 15 minutes before breakfast every day.</li>\r\n	<li>Remember to choose something you enjoy. You&rsquo;ll be much more likely to keep it up then.</li>\r\n	<li>You could also check out local community noticeboards to see if there are any running groups in your area. Running groups have become quite common and offer beginners the chance to start at an easy pace and gradually increase. There are many fun 5k runs/walks around the country every weekend. Joining a running club is also a great way to meet new people.</li>\r\n	<li>If you are not physically active now, you will benefit by starting. Even a small amount of exercise at first is better than doing nothing at all.</li>\r\n	<li>You can start slowly and increase the amount you do.</li>\r\n</ul>\r\n\r\n<p><img src="images/riskfactors/physical/couple.jpg" alt="" /></p>\r\n\r\n<h3>Further Supports</h3>\r\n\r\n<p><a href="http://www.getirelandactive.ie/">www.getirelandactive.ie</a></p>\r\n\r\n<ul>\r\n	<li>This site offers information and support on physical activity. It also provides information on local and national running/walking groups</li>\r\n</ul>\r\n\r\n<p><a href="http://www.irishheart.ie/">www.irishheart.ie</a></p>\r\n\r\n<ul>\r\n	<li>Irish Heart provides information on how to keep your heart healthy through diet and exercise changes. Looking after your heart health is important as a healthy heart has a positive impact on your brain health</li>\r\n</ul>\r\n\r\n<p>&nbsp;<a href="http://www.getirelandwalking.ie/">www.getirelandwalking.ie</a></p>\r\n\r\n<ul>\r\n	<li>Offers tips and advice on setting up walking groups and links to existing groups in your locality&nbsp;</li>\r\n</ul>', ''),
	(5, 'en', 'Smoking', 'images/libra/smoking_amber.png', '<h1>Smoking</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you do not smoke. Keep this up!</p>\r\n\r\n<p>Smoking increases blood pressure and puts you at risk of heart disease. It can also affect your brain health. Evidence tells us that smoking in mid-life increases the risk of developing dementia in later years.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/smoking/img.png" /></p>', '<h1>Smoking</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us you are a current smoker.</p>\r\n\r\n<p>Smoking increases blood pressure and puts you at risk of heart disease. It can also affect your brain health. Evidence tells us that smoking in mid-life increases the risk of developing dementia in later years.</p>\r\n\r\n<p>Making a change will improve your overall health and your brain health.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/smoking/img.png" /></p>\r\n\r\n<p>You may find it hard to quit smoking, but remember that many people have stopped with the right help</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>If you can&rsquo;t quit smoking altogether, then try cutting down. </strong></p>\r\n\r\n<p><strong>Start by smoking one less cigarette a day. That is seven fewer cigarettes per week. The next week, cut two cigarettes per day.</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Many people feel that smoking helps them cope with stress. </strong></p>\r\n\r\n<p><strong>Look for other ways to manage stress. See our </strong><a href="infos-riskfactor.html?riskfactor=2"><strong>Mood</strong></a><strong><a href="infos-riskfactor.html?riskfactor=2"> page</a> for support and advice.</strong></p>', ''),
	(6, 'en', 'Drinking', 'images/libra/drinking_amber.png', '<h1>Alcohol</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that your alcohol levels are at or below the recommended levels for safe drinking. Keep this up!</p>\r\n\r\n<p>Below you will find information and guidelines to help you stick to your current levels of &nbsp;safe, healthy drinking</p>\r\n\r\n<ul>\r\n	<li>in mid-life increases the risk of dementia later in life. Binge drinking (more than five half-pints of beer or a bottle of wine on one occasion) is associated with a higher risk of dementia. Heavy drinking can also damage your physical health and lead to heart disease, obesity, and some cancers.</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/alcool/chart.png" /></p>\r\n\r\n<ul>\r\n	<li>In Ireland, a standard drink has about 10&nbsp;grams of pure alcohol in it. In the UK, a standard drink &ndash; also called a unit of alcohol &ndash; has about 8&nbsp;grams of pure alcohol.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Remember</strong>, not all drinks measures are the same. What you get in a pub and what you pour for yourself could be very different.</p>\r\n\r\n<p>However, the overall amount of alcohol consumed by many people in Ireland puts them at risk of developing alcohol-related problems.</p>\r\n\r\n<h3>These problems may be:</h3>\r\n\r\n<p>Difficulty with memory</p>\r\n\r\n<p>Physical &ndash; such as heart disease</p>\r\n\r\n<p>Psychological &ndash; such as depression</p>\r\n\r\n<p>Social &ndash; such as acts of violence</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<p>Cut down on alcohol if you are drinking more than the recommended limits</p>\r\n\r\n<p>Avoid binge drinking</p>\r\n\r\n<p>Take up social activities that do not include alcohol, such as going to the cinema, the theatre, or to a concert.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Know your measures!</h3>\r\n\r\n<p>If you are drinking more than the recommended limits to cope with stress, look for other ways to manage stress. You could take a yoga class or practise mindfulness or simply get outdoors. See the Mood page for more options.</p>\r\n\r\n<p>If you need help with your drinking, speak to your GP about where to get treatment locally.</p>\r\n\r\n<p>Treatment may include:</p>\r\n\r\n<p>Counselling</p>\r\n\r\n<p>Self-help groups, where people talk about their drinking problems in a supportive environment</p>\r\n\r\n<p>Medication, which can help to reduce cravings&nbsp;&nbsp;</p>', '<h1>Alcohol</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that you drink more alcohol than the recommended limits. Making small changes to your drinking habits can have a positive effect on your brain health and your overall physical health.</p>\r\n\r\n<p>Below you will find information and guidelines for safe, healthy drinking.</p>\r\n\r\n<p>You will also find your personalised plan. This gives you a number of goals to choose from to help you drink less alcohol or stop drinking altogether.</p>\r\n\r\n<ul>\r\n	<li>in mid-life increases the risk of dementia later in life. Binge drinking (more than five half-pints of beer or a bottle of wine on one occasion) is associated with a higher risk of dementia. Heavy drinking can also damage your physical health and lead to heart disease, obesity, and some cancers.</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/alcool/chart.png" /></p>\r\n\r\n<ul>\r\n	<li>In Ireland, a standard drink has about 10&nbsp;grams of pure alcohol in it. In the UK, a standard drink &ndash; also called a unit of alcohol &ndash; has about 8&nbsp;grams of pure alcohol.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Remember</strong>, not all drinks measures are the same. What you get in a pub and what you pour for yourself could be very different.</p>\r\n\r\n<p>However, the overall amount of alcohol consumed by many people in Ireland puts them at risk of developing alcohol-related problems.</p>\r\n\r\n<h3>These problems may be:</h3>\r\n\r\n<p>Difficulty with memory</p>\r\n\r\n<p>Physical &ndash; such as heart disease</p>\r\n\r\n<p>Psychological &ndash; such as depression</p>\r\n\r\n<p>Social &ndash; such as acts of violence</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<p>Cut down on alcohol if you are drinking more than the recommended limits</p>\r\n\r\n<p>Avoid binge drinking</p>\r\n\r\n<p>Take up social activities that do not include alcohol, such as going to the cinema, the theatre, or to a concert.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Know your measures!</h3>\r\n\r\n<p>If you are drinking more than the recommended limits to cope with stress, look for other ways to manage stress. You could take a yoga class or practise mindfulness or simply get outdoors. See the Mood page for more options.</p>\r\n\r\n<p>If you need help with your drinking, speak to your GP about where to get treatment locally.</p>\r\n\r\n<p>Treatment may include:</p>\r\n\r\n<p>Counselling</p>\r\n\r\n<p>Self-help groups, where people talk about their drinking problems in a supportive environment</p>\r\n\r\n<p>Medication, which can help to reduce cravings&nbsp;&nbsp;</p>\r\n', '<h3>Further Supports:</h3>\r\n\r\n<ul>\r\n	<li><a href="http://www.yourdrinking.ie/">www.yourdrinking.ie</a></li>\r\n</ul>\r\n\r\n<p>Advice and information on safe drinking practices</p>\r\n\r\n<ul>\r\n	<li><a href="http://www.alcoholicsanonymous.ie/">http://</a><a href="http://www.alcoholicsanonymous.ie/">www.alcoholicsanonymous.ie</a></li>\r\n</ul>\r\n\r\n<p>Offers information on where to access local groups who offer support to those who are experiencing problems due to their alcohol intake</p>'),
	(7, 'en', 'Obesity', 'images/libra/obesity_amber.png', '<h1>Obesity</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<ul>\r\n	<li>Your profile tells us that your BMI is less than 30. &nbsp;This is important as a healthy weight is good for your overall brain health. Keep this up![link to What is BMI and What is obesity]</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What we know</h3>\r\n\r\n<ul>\r\n	<li>Obesity in middle age could increase the risk of dementia later in life.</li>\r\n	<li>Studies on <strong>cognitive function </strong>[Link to What is cognitive function] and body mass index (BMI [Link to What is BMI) found that obese people have more trouble on memory and reasoning tests.</li>\r\n	<li>High blood pressure is often associated with obesity and could increase the risk of dementia later in life.</li>\r\n	<li>Cognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension. [this will appear as a pop up]</li>\r\n</ul>\r\n\r\n<h3>A quick guide to obesity</h3>\r\n\r\n<ul>\r\n	<li>A person who is obese has so much excess body fat that his or her health may suffer. Obesity is a medical condition.</li>\r\n	<li>Body mass index (BMI) is a formula used to check whether a person is at a healthy weight for their height. A person is considered obese if they have a BMI of 30 or more.</li>\r\n	<li>Obesity has many causes and is not just about eating too much food.</li>\r\n	<li>Many of us are less physically active than our parents or grandparents were. Instead of walking everywhere, we travel in cars or on public transport. Many of us also have jobs that require us to sit for most of the day.</li>\r\n	<li>Lowering your BMI to a healthy level is not only good for your physical health but also improves your brain health.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>A calorie is a unit of energy. It describes how much energy your body gets from eating or drinking. Most food and drinks contain calories. If you eat or drink more calories than you need, the leftover calories are converted to fat. If you only eat and drink the recommended daily number of calories, and if you also take enough exercise, you will stay within a healthy weight range. Being active is important because physical activity burns calories.&nbsp; [this will appear as a pop up to explain table on calories]</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/diet/goal.jpg" /></p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<ul>\r\n	<li>To lose weight, you need to reduce the number of calories you eat and drink every day and increase the amount of exercise you get.</li>\r\n	<li>If you are not physically active, you should ask your GP about the type of exercise you should do and how much, depending on how fit you are.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/obesity/truc.png" /></p>\r\n\r\n<h3>Take more exercise!</h3>\r\n\r\n<ul>\r\n	<li>Build up slowly. Choose physical activities that you enjoy. You&rsquo;ll be more likely to keep doing them then.</li>\r\n	<li>Start with activities that you can fit into your everyday life, like walking, gardening or taking the stairs instead of the lift</li>\r\n</ul>\r\n\r\n<h3>Be realistic</h3>\r\n\r\n<ul>\r\n	<li>Set realistic weight loss goals.</li>\r\n	<li>Keep a food diary. This is a good way to keep track of how many calories you are eating.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Join a local slimming club if you think this may help you.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>With support, we can make simple changes that help us to feel better physically and mentally and to maintain good brain health.</li>\r\n</ul>\r\n\r\n<ul>\r\n	<li>If you choose food from the Mediterranean Diet, eat more fruit and vegetables every day, and use olive oil instead of butter or margarine, your diet and your brain health will improve.</li>\r\n</ul>', '<h1>Obesity</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<ul>\r\n	<li>Your profile tells us that your BMI is &hellip;over 30&hellip;.. [link to What is BMI and What is obesity] There is room to improve your diet and your level of physical activity. Just a few small changes can lower your BMI, improve your brain health, and reduce your risk of dementia later in life.</li>\r\n	<li>BMI stands for body mass index. It is a formula used to check whether a person is underweight, overweight or at a healthy weight. The formula divides your weight by the square of your height to get a BMI score. Anyone with a BMI score of between 18.5 to 25 is considered a healthy weight, while 25 to 30 is overweight. A score of more than 30 is described as obese. Click here to calculate your BMI [this will appear as a pop up]</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>What we know</h3>\r\n\r\n<ul>\r\n	<li>Obesity in middle age could increase the risk of dementia later in life.</li>\r\n	<li>Studies on <strong>cognitive function </strong>[Link to What is cognitive function] and body mass index (BMI [Link to What is BMI) found that obese people have more trouble on memory and reasoning tests.</li>\r\n	<li>High blood pressure is often associated with obesity and could increase the risk of dementia later in life.</li>\r\n	<li>Cognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension. [this will appear as a pop up]</li>\r\n</ul>\r\n\r\n<h3>A quick guide to obesity</h3>\r\n\r\n<ul>\r\n	<li>A person who is obese has so much excess body fat that his or her health may suffer. Obesity is a medical condition.</li>\r\n	<li>Body mass index (BMI) is a formula used to check whether a person is at a healthy weight for their height. A person is considered obese if they have a BMI of 30 or more.</li>\r\n	<li>Obesity has many causes and is not just about eating too much food.</li>\r\n	<li>Many of us are less physically active than our parents or grandparents were. Instead of walking everywhere, we travel in cars or on public transport. Many of us also have jobs that require us to sit for most of the day.</li>\r\n	<li>Lowering your BMI to a healthy level is not only good for your physical health but also improves your brain health.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>A calorie is a unit of energy. It describes how much energy your body gets from eating or drinking. Most food and drinks contain calories. If you eat or drink more calories than you need, the leftover calories are converted to fat. If you only eat and drink the recommended daily number of calories, and if you also take enough exercise, you will stay within a healthy weight range. Being active is important because physical activity burns calories.&nbsp; [this will appear as a pop up to explain table on calories]</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/diet/goal.jpg" /></p>\r\n\r\n<h3>What can I do?</h3>\r\n\r\n<ul>\r\n	<li>To lose weight, you need to reduce the number of calories you eat and drink every day and increase the amount of exercise you get.</li>\r\n	<li>If you are not physically active, you should ask your GP about the type of exercise you should do and how much, depending on how fit you are.</li>\r\n	<li>You can start by spending less time sitting at your computer or in front of the television.</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/obesity/truc.png" /></p>\r\n\r\n<h3>Take more exercise!</h3>\r\n\r\n<ul>\r\n	<li>Build up slowly. Choose physical activities that you enjoy. You&rsquo;ll be more likely to keep doing them then.</li>\r\n	<li>Start with activities that you can fit into your everyday life, like walking, gardening or taking the stairs instead of the lift</li>\r\n</ul>\r\n\r\n<h3>Be realistic</h3>\r\n\r\n<ul>\r\n	<li>Set realistic weight loss goals.</li>\r\n	<li>Keep a food diary. This is a good way to keep track of how many calories you are eating.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Join a local slimming club if you think this may help you.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>With support, we can make simple changes that help us to feel better physically and mentally and to maintain good brain health.</li>\r\n</ul>\r\n\r\n<h3>How does my plan work?</h3>\r\n\r\n<ul>\r\n	<li>The plan is meant to help you lose weight. A weight loss of 5%-10% will be good for your heart and your brain health.</li>\r\n	<li>Below are three goals that can help you lose weight by changing your diet and by exercising.</li>\r\n	<li>Remember, if you are not enjoying the changes to your diet or exercise habits, you will find it harder to keep going.&nbsp;</li>\r\n	<li>&nbsp;Choose one of the goals below. If you find it hard to fit these changes into your lifestyle, then come back and choose another goal!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>Choosing one of the goals above will help you lose weight and improve your BMI. It may take some time to get your BMI below 30. But remember that making simple changes to your diet and taking more exercise may help you to improve your overall brain health score. The plan is meant to help you make positive changes to your diet and exercise habits.</li>\r\n	<li>Choosing food from the Mediterranean Diet is a good way to improve your diet and, in turn, your brain health. Your brain will also benefit if you eat more fruit and vegetables every day and use olive oil instead of butter or margarine.</li>\r\n	<li>If you make simple changes to your diet, take more exercise, and choose one of the goals above, you will improve your overall brain health score.</li>\r\n	<li>If you choose food from the Mediterranean Diet, eat more fruit and vegetables every day, and use olive oil instead of butter or margarine, your diet and your brain health will improve.</li>\r\n</ul>', '<h3>Other Supports</h3>\r\n\r\n<p><a href="http://www.hse.ie/phew">www.hse.ie/phew</a></p>\r\n\r\n<ul>\r\n	<li>This is a 6 week programme for healthy eating and weight management designed for overweight individuals</li>\r\n</ul>\r\n\r\n<p><a href="http://www.getirelandactive.ie">www.getirelandactive.ie</a></p>\r\n\r\n<ul>\r\n	<li>This website highlights the importance of physical activity to the health of Irish people, provides information, recommendations and support for people of all ages and abilities to get active</li>\r\n</ul>'),
	(8, 'en', 'Cognitive Activity', 'images/libra/cognitive_activity_amber.png', '<h1>Cognitive Activity</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you are cognitively active. Keep this up!<br />\r\nWhile many of us know how to keep physically active, we may not think about keeping our brains active, or have time for it.</p>\r\n\r\n<p>Below you will find information about activities that can help you maintain your current level of cognitive activity in order to keep your brain healthy</p>\r\n\r\n<p>As we age, it is important to remain cognitively active to maintain our cognitive function.<br />\r\nLearning new things and doing activities that challenge you is an excellent way to keep your brain healthy</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cognitive/brain.png" /></p>\r\n\r\n<p>Activities that stimulate you mentally, physically and socially will keep your brain healthy and help prevent dementia.<br />\r\nFor a healthy brain, you need to look after your brain, body and heart. Brain health is especially important once you reach middle age. That is when changes start to occur.<br />\r\nResearch suggests that people who have few friends nearby and who spend a lot of time alone have a higher risk of dementia.<br />\r\nAlso people with little formal&nbsp; education and people in mid-life who do not exercise their brains much run a higher risk of developing dementia later in life.<br />\r\nCognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cognitive/games.png" /></p>\r\n\r\n<h3>What can you do?</h3>\r\n\r\n<p>Train your brain with crossword puzzles or online brain-training exercises.</p>\r\n\r\n<p>Join your local library. This is a great alternative to buying books, and the staff can recommend interesting books on all sorts of topics.</p>\r\n\r\n<p>Consider volunteering. This is a great way to meet new people while doing something that will benefit your community.</p>\r\n\r\n<p>Learn a new language. Many local libraries offer free language classes. You can also borrow language tapes from the library to take home.</p>\r\n\r\n<p>Join or start a book club with friends and neighbours.</p>\r\n\r\n<p>If you like music, learn to play an instrument or join a local choir or music group. Post an ad at your local college or music school; music students may be willing to give you lessons for a relatively low fee.</p>\r\n\r\n<p>If you love to dance, check out local classes in your area. You will find everything from ballroom and salsa to hip-hop.</p>\r\n\r\n<p>Go to concerts. Live music need not be expensive. There are many places that have free music events throughout the year.</p>\r\n\r\n<ul>\r\n	<li>Check out adult education classes. They offer everything from sewing to Greek philosophy!</li>\r\n	<li>Get out in the garden more. Many communities now have allotments where people can grow their own vegetables.</li>\r\n	<li>Join a local amateur dramatic society. This is great fun, but learning lines is also good for your memory!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Activities that are good for brain health do not need to be expensive or boring.</p>\r\n\r\n<p>Anything that stimulates you and that you enjoy is good for your brain!</p>', '<h1>Cognitive Activity</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that there is room to improve your social life and exercise your brain more.<br />\r\nWhile many of us know how to keep physically active, we may not think about keeping our brains active, or have time for it.</p>\r\n\r\n<p>Below you will find information about activities that can help you keep your brain healthy.<br />\r\nYou will also find your personalised plan.</p>\r\n\r\n<p>As we age, it is important to remain cognitively active to maintain our cognitive function.<br />\r\nLearning new things and doing activities that challenge you is an excellent way to keep your brain healthy</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cognitive/brain.png" /></p>\r\n\r\n<p>Activities that stimulate you mentally, physically and socially will keep your brain healthy and help prevent dementia.<br />\r\nFor a healthy brain, you need to look after your brain, body and heart. Brain health is especially important once you reach middle age. That is when changes start to occur.<br />\r\nResearch suggests that people who have few friends nearby and who spend a lot of time alone have a higher risk of dementia.<br />\r\nAlso people with little formal&nbsp; education and people in mid-life who do not exercise their brains much run a higher risk of developing dementia later in life.<br />\r\nCognitive function refers to memory, the ability to learn new information, process thoughts, speech and reading comprehension.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cognitive/games.png" /></p>\r\n\r\n<h3>What can you do?</h3>\r\n\r\n<p>Train your brain with crossword puzzles or online brain-training exercises.</p>\r\n\r\n<p>Join your local library. This is a great alternative to buying books, and the staff can recommend interesting books on all sorts of topics.</p>\r\n\r\n<p>Consider volunteering. This is a great way to meet new people while doing something that will benefit your community.</p>\r\n\r\n<p>Learn a new language. Many local libraries offer free language classes. You can also borrow language tapes from the library to take home.</p>\r\n\r\n<p>Join or start a book club with friends and neighbours.</p>\r\n\r\n<p>If you like music, learn to play an instrument or join a local choir or music group. Post an ad at your local college or music school; music students may be willing to give you lessons for a relatively low fee.</p>\r\n\r\n<p>If you love to dance, check out local classes in your area. You will find everything from ballroom and salsa to hip-hop.</p>\r\n\r\n<p>Go to concerts. Live music need not be expensive. There are many places that have free music events throughout the year.</p>\r\n\r\n<ul>\r\n	<li>Check out adult education classes. They offer everything from sewing to Greek philosophy!</li>\r\n	<li>Get out in the garden more. Many communities now have allotments where people can grow their own vegetables.</li>\r\n	<li>Join a local amateur dramatic society. This is great fun, but learning lines is also good for your memory!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Activities that are good for brain health do not need to be expensive or boring.</p>\r\n\r\n<p>Anything that stimulates you and that you enjoy is good for your brain!</p>', '<h3>Further Supports</h3>\r\n\r\n<p><a href="http://www.citizensinformation.ie/en/"><strong>http://www.citizensinformation.ie/en/</strong></a></p>\r\n\r\n<ul>\r\n	<li><strong>Provides information on community services and access to those services</strong></li>\r\n</ul>\r\n\r\n<p><a href="http://www.volunteer.ie/"><strong>http://www.volunteer.ie/</strong></a></p>\r\n\r\n<ul>\r\n	<li><strong>If you are thinking about volunteering this site will give you information on groups and organisations who take on volunteers.</strong></li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Adult Education Courses</strong></p>\r\n\r\n<p><a href="http://www.courses.ie/"><strong>http://www.courses.ie/</strong></a></p>\r\n\r\n<p><a href="http://www.aontas.com/"><strong>http://www.aontas.com/</strong></a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Outdoor Activities</strong></p>\r\n\r\n<p><a href="http://www.allotments.ie/"><strong>http://www.allotments.ie/</strong></a></p>\r\n\r\n<p><a href="http://www.dublincity.ie/YourCouncil/LocalAreaServices/SouthCentralArea/Pages/AllotmentsandCommunityGardens.aspx"><strong>http://www.dublincity.ie/YourCouncil/LocalAreaServices/SouthCentralArea/Pages/AllotmentsandCommunityGardens.aspx</strong></a></p>\r\n\r\n<p>These websites offer the user information on securing an allotment in your local community</p>'),
	(9, 'en', 'Diet', 'images/libra/diet_amber.png', '<h1>Diet</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that your current diet is healthy. Keep this up!<br />\r\nBelow you will find information on the importance of a healthy diet to your brain health. You may find new ways of incorporating healthy foods into your everyday diet!</p>\r\n\r\n<h3>A healthy diet is good for our overall health but it is also good for our brain health</h3>\r\n\r\n<p>Researchers have found that diet and physical activity have a positive effect on our brain health.</p>\r\n\r\n<p>Our eating habits may also play an important role in protecting us against dementia.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diet/pyramid.png" /></p>\r\n\r\n<h3>The Mediterranean Diet is thought to protect against dementia.</h3>\r\n\r\n<p>The Mediterranean Diet has lots of fish, vegetables, pulses (beans, also known as legumes), fruit, cereals and olive oil, and only small amounts of dairy, meat and butter or margarine.</p>\r\n\r\n<ul>\r\n	<li>To eat a healthy and balanced diet, you need to combine several different types of foods - from each of the main food groups - in the right amounts. That way your body gets all the nutrients it needs while you maintain a healthy weight.&nbsp; This means you should eat:</li>\r\n	<li>Whole-grain breads, pasta and rice if available</li>\r\n	<li>Plenty of fruit and vegetables</li>\r\n	<li>Some milk, cheese and yoghurt</li>\r\n	<li>Some meat, fish, eggs, beans and other non-dairy sources of protein, and</li>\r\n	<li>Just a small amount of foods and drinks high in fat and/or sugar</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li><strong>Eat more vegetables and fruit. </strong>Eat fruit and vegetables at every meal and for snacks as well. Carrots, celery, bananas and apples are easy to have on hand and make satisfying snacks. Fruit salads are a great way to eat a wide variety of healthy fruit.</li>\r\n	<li><strong>Switch to whole-grain bread. </strong>Whole grains contain very few unhealthy fats. Try using whole-grain rice and pasta. Replace sugary breakfast cereals with porridge and add in blueberries or other fruit. If you need more sweetness, use honey instead of sugar.</li>\r\n	<li><strong>Eat nuts. </strong>Try almonds, cashews, pistachios and walnuts. Try natural peanut butter. Nuts can be added to any meal but are also a great snack when you&rsquo;re out and about. Don&rsquo;t overdo it because nuts can be high in calories. We recommend a handful of unsalted nuts 3 times per week. .</li>\r\n	<li><strong>Use olive oil </strong>as a healthy replacement for butter or margarine. Drizzle it on vegetables and bread. Make your own salad dressing by using a little olive oil, red wine or balsamic vinegar and crushed garlic. Add a squeeze of lemon juice if you wish. Olive oil, garlic, leeks and onions are a healthy alternative to shop-bought pasta sauces.<br />\r\n	<img alt="" src="images/riskfactors/diet/oil.png" /></li>\r\n	<li><strong>Eat fish </strong>at least twice a week. Salmon, trout and tuna are healthy choices. Grilling, baking or steaming fish is not only healthy but also takes very little time and effort. If you&rsquo;re on a budget, choose tinned sardines, tuna or mackerel. Eat it on whole-grain bread for a healthy lunch or evening snack.</li>\r\n	<li><strong>Go low fat</strong>. Cut down on high-fat dairy products. Switch to skimmed milk, fat-free yogurt and low-fat cheese. Try frozen yogurt or natural yogurt as a healthy alternative to ice cream. Add in any fruit you wish for that extra sweetness!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>A calorie is a unit of energy. It describes how much energy your body gets from eating or drinking. Most food and drinks contain calories. If you eat or drink more calories than you need, the leftover calories are converted to fat. If you only eat and drink the recommended number of daily calories, you will stay within a healthy weight range. Being active is also important because physical activity burns calories. [This will appear as a pop up on the webpage]</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h2 class="h-color">What can I do?</h2>\r\n\r\n<ul>\r\n	<li>Watch the number of calories you consume and maintain a healthy weight.</li>\r\n	<li>Limit the amount of fat in your diet. Use less animal fat like butter and margarine (known as saturated fats) and more vegetable fat like olive oil (known as unsaturated fats). Try to eliminate fast food, fried food and greasy snacks, which contain unhealthy trans-fatty acids.</li>\r\n	<li>Eat more fruits and vegetables, beans, whole grains and nuts.</li>\r\n	<li>Eat less sugar.</li>\r\n	<li>Limit the amount of salt you consume from all sources.</li>\r\n	<li>Fruit and vegetables are important parts of a healthy diet. Eating enough fruit and vegetables every day helps you avoid serious diseases, such as cardiovascular diseases and certain cancers.<br />\r\n	<img alt="" src="images/riskfactors/diet/vegies.png" /></li>\r\n</ul>\r\n\r\n<h3>Healthy Options</h3>\r\n\r\n<p>Eat more fish every week. Have oily fish such as salmon, mackerel or sardines twice a week.</p>\r\n\r\n<p>Switch to olive oil. Instead of frying your food, choose grilling, baking or steaming.</p>\r\n\r\n<p>Use whole-grain pasta or rice.</p>\r\n\r\n<p>Add pulses (or legumes) to your everyday diet, for example peas, kidney beans or lentils.</p>\r\n\r\n<p>Snack on fruit, nuts or vegetables.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Try not to skip meals, as you are more likely to eat larger portions when you do eat.<br />\r\nCut out/down on coffee/tea and sugary drinks and drink more water.</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>If your BMI is above the recommended limits for a healthy weight (over 25), you should reduce your calorie intake. You can do this by following the advice above. Remember to limit your portion size as well, and not to exceed the recommended number of calories.</p>\r\n\r\n<p>BMI is a formula used to check whether a person is underweight, overweight or at a healthy weight. The formula divides your weight by the square of your height to get a BMI score. Anyone with a BMI score of between 18.5 to 25 is considered healthy, while 25 to 30 is overweight. A score of more than 30 is described as obese. Click here to calculate your BMI.</p>', '<h1>Diet</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that your current diet could be improved.<br />\r\nMaking some small changes to your diet will help you maintain your brain health.</p>\r\n\r\n<p>Below you will find information to help you make healthy eating part of your everyday routine.<br />\r\nYou will also find your personalised plan. This gives you a number of goals to choose from to help you develop healthy eating habits!</p>\r\n\r\n<h3>A healthy diet is good for our overall health but it is also good for our brain health</h3>\r\n\r\n<p>Researchers have found that diet and physical activity have a positive effect on our brain health.</p>\r\n\r\n<p>Our eating habits may also play an important role in protecting us against dementia.</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diet/pyramid.png" /></p>\r\n\r\n<h3>The Mediterranean Diet is thought to protect against dementia.</h3>\r\n\r\n<p>The Mediterranean Diet has lots of fish, vegetables, pulses (beans, also known as legumes), fruit, cereals and olive oil, and only small amounts of dairy, meat and butter or margarine.</p>\r\n\r\n<ul>\r\n	<li>To eat a healthy and balanced diet, you need to combine several different types of foods - from each of the main food groups - in the right amounts. That way your body gets all the nutrients it needs while you maintain a healthy weight.&nbsp; This means you should eat:</li>\r\n	<li>Whole-grain breads, pasta and rice if available</li>\r\n	<li>Plenty of fruit and vegetables</li>\r\n	<li>Some milk, cheese and yoghurt</li>\r\n	<li>Some meat, fish, eggs, beans and other non-dairy sources of protein, and</li>\r\n	<li>Just a small amount of foods and drinks high in fat and/or sugar</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li><strong>Eat more vegetables and fruit. </strong>Eat fruit and vegetables at every meal and for snacks as well. Carrots, celery, bananas and apples are easy to have on hand and make satisfying snacks. Fruit salads are a great way to eat a wide variety of healthy fruit.</li>\r\n	<li><strong>Switch to whole-grain bread. </strong>Whole grains contain very few unhealthy fats. Try using whole-grain rice and pasta. Replace sugary breakfast cereals with porridge and add in blueberries or other fruit. If you need more sweetness, use honey instead of sugar.</li>\r\n	<li><strong>Eat nuts. </strong>Try almonds, cashews, pistachios and walnuts. Try natural peanut butter. Nuts can be added to any meal but are also a great snack when you&rsquo;re out and about. Don&rsquo;t overdo it because nuts can be high in calories. We recommend a handful of unsalted nuts 3 times per week. .</li>\r\n	<li><strong>Use olive oil </strong>as a healthy replacement for butter or margarine. Drizzle it on vegetables and bread. Make your own salad dressing by using a little olive oil, red wine or balsamic vinegar and crushed garlic. Add a squeeze of lemon juice if you wish. Olive oil, garlic, leeks and onions are a healthy alternative to shop-bought pasta sauces.<br />\r\n	<img alt="" src="images/riskfactors/diet/oil.png" /></li>\r\n	<li><strong>Eat fish </strong>at least twice a week. Salmon, trout and tuna are healthy choices. Grilling, baking or steaming fish is not only healthy but also takes very little time and effort. If you&rsquo;re on a budget, choose tinned sardines, tuna or mackerel. Eat it on whole-grain bread for a healthy lunch or evening snack.</li>\r\n	<li><strong>Go low fat</strong>. Cut down on high-fat dairy products. Switch to skimmed milk, fat-free yogurt and low-fat cheese. Try frozen yogurt or natural yogurt as a healthy alternative to ice cream. Add in any fruit you wish for that extra sweetness!</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>A calorie is a unit of energy. It describes how much energy your body gets from eating or drinking. Most food and drinks contain calories. If you eat or drink more calories than you need, the leftover calories are converted to fat. If you only eat and drink the recommended number of daily calories, you will stay within a healthy weight range. Being active is also important because physical activity burns calories. [This will appear as a pop up on the webpage]</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h2 class="h-color">What can I do?</h2>\r\n\r\n<ul>\r\n	<li>Watch the number of calories you consume and maintain a healthy weight.</li>\r\n	<li>Limit the amount of fat in your diet. Use less animal fat like butter and margarine (known as saturated fats) and more vegetable fat like olive oil (known as unsaturated fats). Try to eliminate fast food, fried food and greasy snacks, which contain unhealthy trans-fatty acids.</li>\r\n	<li>Eat more fruits and vegetables, beans, whole grains and nuts.</li>\r\n	<li>Eat less sugar.</li>\r\n	<li>Limit the amount of salt you consume from all sources.</li>\r\n	<li>Fruit and vegetables are important parts of a healthy diet. Eating enough fruit and vegetables every day helps you avoid serious diseases, such as cardiovascular diseases and certain cancers.<br />\r\n	<img alt="" src="images/riskfactors/diet/vegies.png" /></li>\r\n</ul>\r\n\r\n<h3>Healthy Options</h3>\r\n\r\n<p>Eat more fish every week. Have oily fish such as salmon, mackerel or sardines twice a week.</p>\r\n\r\n<p>Switch to olive oil. Instead of frying your food, choose grilling, baking or steaming.</p>\r\n\r\n<p>Use whole-grain pasta or rice.</p>\r\n\r\n<p>Add pulses (or legumes) to your everyday diet, for example peas, kidney beans or lentils.</p>\r\n\r\n<p>Snack on fruit, nuts or vegetables.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Try not to skip meals, as you are more likely to eat larger portions when you do eat.<br />\r\nCut out/down on coffee/tea and sugary drinks and drink more water.</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>If your BMI is above the recommended limits for a healthy weight (over 25), you should reduce your calorie intake. You can do this by following the advice above. Remember to limit your portion size as well, and not to exceed the recommended number of calories.</p>\r\n\r\n<p>BMI is a formula used to check whether a person is underweight, overweight or at a healthy weight. The formula divides your weight by the square of your height to get a BMI score. Anyone with a BMI score of between 18.5 to 25 is considered healthy, while 25 to 30 is overweight. A score of more than 30 is described as obese. Click here to calculate your BMI.</p>', '<h3>Further Supports</h3>\r\n\r\n<p><a href="http://www.dohc.ie/publications/pdf/YourGuide_HealthyEating_FoodPyramid.pdf?direct=1">http://</a><a href="http://www.dohc.ie/publications/pdf/YourGuide_HealthyEating_FoodPyramid.pdf?direct=1">www.dohc.ie/publications/pdf/YourGuide_HealthyEating_FoodPyramid.pdf?direct=1</a></p>\r\n\r\n<ul>\r\n	<li>Information on healthy diets and ideas and plans for healthy cooking</li>\r\n</ul>\r\n\r\n<p><a href="http://www.safefood.eu/">www.safefood.eu</a></p>\r\n\r\n<ul>\r\n	<li>Facts and information on safe foods, healthy diets recipes and tips on eating well and weight loss</li>\r\n</ul>'),
	(10, 'en', 'Cholesterol', 'images/libra/cholesteral_amber.png', '<h1>Cholesterol</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you do not have high cholesterol.<br />\r\nKeep this up!<br />\r\nEvidence tells us that a healthy cholesterol level can improve your long term brain health<br />\r\nBelow you will find information on maintaining your cholesterol at a healthy level</p>\r\n\r\n<h3>What causes high cholesterol?</h3>\r\n\r\n<p>Eating a diet that is high in saturated fats</p>\r\n\r\n<p>Lack of physical exercise</p>\r\n\r\n<p>Smoking</p>\r\n\r\n<p>High alcohol intake</p>\r\n\r\n<p>Liver or kidney disease</p>\r\n\r\n<h3>Know your fats!</h3>\r\n\r\n<p><strong>Unsaturated fats</strong></p>\r\n\r\n<p>Small amounts of unsaturated fat in your diet can be healthy. This type of fat can help reduce cholesterol levels.</p>\r\n\r\n<p>The traditional Mediterranean <a href="infos-riskfactor.html?riskfactor=9">Diet</a> includes plenty of olive oil and only small amounts of animal fat. It is good for your heart and blood circulation (your cardiovascular health).</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cholesterol/img1.png" /></p>\r\n\r\n<p><strong>Foods high in unsaturated fats include</strong></p>\r\n\r\n<p>oily fish</p>\r\n\r\n<p>avocados</p>\r\n\r\n<p>nuts and seeds</p>\r\n\r\n<p>sunflower, rapeseed and olive oil</p>\r\n\r\n<p>vegetable oils</p>\r\n\r\n<h3>Foods to avoid!</h3>\r\n\r\n<p>Avoid foods containing saturated fats or eat them only on rare occasions.</p>\r\n\r\n<p>These foods include:</p>\r\n\r\n<p>fatty cuts of meat and meat products, such as sausages and pies</p>\r\n\r\n<p>butter and lard</p>\r\n\r\n<h3>Foods to limit!</h3>\r\n\r\n<p><img alt="" src="images/riskfactors/cholesterol/img2.png" /></p>\r\n\r\n<p>cream, soured cream, cr&egrave;me fraiche and ice cream</p>\r\n\r\n<p>cheese, particularly hard cheese</p>\r\n\r\n<p>cakes and biscuits</p>\r\n\r\n<p>chocolate</p>\r\n\r\n<p>coconut oil, coconut cream and palm oil</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>The average man should have no more than 30 grams of saturated fat a day.</p>\r\n\r\n<p>The average woman should have no more than 20 grams of saturated fat a day.</p>\r\n\r\n<p>To get an idea how much saturated fat you are consuming, take a look at the food labels of the foods you are eating.</p>\r\n', '<h1>Cholesterol</h1>\r\n\r\n<h2 class="h-color">Room for improvement</h2>\r\n\r\n<p>Your profile tells us that you have high cholesterol. Evidence tells us that a healthy cholesterol level can improve your long term brain health</p>\r\n\r\n<h3>What causes high cholesterol?</h3>\r\n\r\n<p>Eating a diet that is high in saturated fats</p>\r\n\r\n<p>Lack of physical exercise</p>\r\n\r\n<p>Smoking</p>\r\n\r\n<p>High alcohol intake</p>\r\n\r\n<p>Liver or kidney disease</p>\r\n\r\n<h3>Know your fats!</h3>\r\n\r\n<p><strong>Unsaturated fats</strong></p>\r\n\r\n<p>Small amounts of unsaturated fat in your diet can be healthy. This type of fat can help reduce cholesterol levels.</p>\r\n\r\n<p>The traditional Mediterranean <a href="infos-riskfactor.html?riskfactor=9">Diet</a> includes plenty of olive oil and only small amounts of animal fat. It is good for your heart and blood circulation (your cardiovascular health).</p>\r\n\r\n<p><img alt="" src="images/riskfactors/cholesterol/img1.png" /></p>\r\n\r\n<p><strong>Foods high in unsaturated fats include</strong></p>\r\n\r\n<p>oily fish</p>\r\n\r\n<p>avocados</p>\r\n\r\n<p>nuts and seeds</p>\r\n\r\n<p>sunflower, rapeseed and olive oil</p>\r\n\r\n<p>vegetable oils</p>\r\n\r\n<h3>Foods to avoid!</h3>\r\n\r\n<p>Avoid foods containing saturated fats or eat them only on rare occasions.</p>\r\n\r\n<p>These foods include:</p>\r\n\r\n<p>fatty cuts of meat and meat products, such as sausages and pies</p>\r\n\r\n<p>butter and lard</p>\r\n\r\n<h3>Foods to limit!</h3>\r\n\r\n<p><img alt="" src="images/riskfactors/cholesterol/img2.png" /></p>\r\n\r\n<p>cream, soured cream, cr&egrave;me fraiche and ice cream</p>\r\n\r\n<p>cheese, particularly hard cheese</p>\r\n\r\n<p>cakes and biscuits</p>\r\n\r\n<p>chocolate</p>\r\n\r\n<p>coconut oil, coconut cream and palm oil</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>The average man should have no more than 30 grams of saturated fat a day.</p>\r\n\r\n<p>The average woman should have no more than 20 grams of saturated fat a day.</p>\r\n\r\n<p>To get an idea how much saturated fat you are consuming, take a look at the food labels of the foods you are eating.</p>\r\n', ''),
	(11, 'en', 'Heart Disease', 'images/libra/heart_disease_amber.png', '<h1>Heart Disease</h1>\r\n\r\n<p><img alt="" src="images/riskfactors/heart/heart1.png" /></p>\r\n\r\n<p>Your profile tells us that you do not suffer from heart disease.</p>\r\n\r\n<p>Keeping your heart healthy has other health benefits. For example, it can help protect your brain health.&nbsp;</p>\r\n\r\n<h3>How can I prevent heart disease?</h3>\r\n\r\n<ul>\r\n	<li>Maintain a healthy weight</li>\r\n	<li>Be physically active, always consult your GP before making any changes to your diet or exercise habits (click <a href="infos-riskfactor.html?riskfactor=4">here</a>)</li>\r\n	<li>Eat a healthy, balanced diet (click <a href="infos-riskfactor.html?riskfactor=9">here</a>)</li>\r\n	<li>Quit smoking (click <a href="infos-riskfactor.html?riskfactor=5">here</a>)</li>\r\n	<li>Reduce stress (click <a href="infos-riskfactor.html?riskfactor=4">here</a>)</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/heart/heart2.png" /></p>\r\n', '<h1>Heart Disease</h1>\r\n\r\n<h2 class="h-color">Remember to manage!</h2>\r\n\r\n<p><img alt="" src="images/riskfactors/heart/heart1.png" /></p>\r\n\r\n<p>Your profile tells us that you suffer from heart disease.</p>\r\n\r\n<p>Besides taking medication, you can manage your condition through diet and exercise</p>\r\n\r\n<p>Keeping your heart healthy has other health benefits. For example, it can help protect your brain health.&nbsp;</p>\r\n\r\n<h3>How can I prevent heart disease?</h3>\r\n\r\n<ul>\r\n	<li>Maintain a healthy weight</li>\r\n	<li>Be physically active, always consult your GP before making any changes to your diet or exercise habits (click <a href="infos-riskfactor.html?riskfactor=4">here</a>)</li>\r\n	<li>Eat a healthy, balanced diet (click <a href="infos-riskfactor.html?riskfactor=9">here</a>)</li>\r\n	<li>Quit smoking (click <a href="infos-riskfactor.html?riskfactor=5">here</a>)</li>\r\n	<li>Reduce stress (click <a href="infos-riskfactor.html?riskfactor=4">here</a>)</li>\r\n</ul>\r\n\r\n<p><img alt="" src="images/riskfactors/heart/heart2.png" /></p>\r\n', ''),
	(12, 'en', 'Chronic Kidney Disease', 'images/libra/kidney_disease_amber.png', '<h1>Chronic Kidney Disease</h1>\r\n\r\n<h2 class="h-color">Keep this up!</h2>\r\n\r\n<p>Your profile tells us that you do not have Chronic Kidney Disease. In many cases Kidney disease cannot be prevented but there are steps you can take to reduce your risk of developing it.</p>\r\n\r\n<p>Below you will find information on further supports</p>\r\n\r\n<p>Evidence tells us that there is an increased risk of dementia in those with chronic kidney disease (CKD).</p>\r\n\r\n<ul>\r\n	<li>Chronic Kidney Disease describes the gradual loss of kidney function. Your kidney filters wastes and excess fluids from your blood, which are then excreted in your urine</li>\r\n</ul>\r\n\r\n<p>Some causes of CKD are:</p>\r\n\r\n<ul>\r\n	<li>high blood pressure</li>\r\n	<li>Type 1 or 2 Diabetes</li>\r\n	<li>Recurrent kidney infection</li>\r\n	<li>Too much potassium can build up when the kidneys are no longer functioning well</li>\r\n	<li>Potassium if found in many foods.</li>\r\n</ul>\r\n\r\n<h3>What Can I do?</h3>\r\n\r\n<p>Stick to the recommended alcohol consumption limits (See our alcohol page <a href="infos-riskfactor.html?riskfactor=6">here</a>)</p>\r\n\r\n<p>Eat a healthy balanced diet that includes plenty of fresh fruit and vegetable and fish (see our diet page <a href="infos-riskfactor.html?riskfactor=9">here</a>)</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img2.png" /></p>\r\n\r\n<p>Take regular exercise (see our exercise page <a href="infos-riskfactor.html?riskfactor=4">here</a>)</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img3.png" /></p>\r\n\r\n<p>Quit smoking (see our smoking page <a href="infos-riskfactor.html?riskfactor=5">here</a>)</p>', '<h1>Chronic Kidney Disease</h1>\r\n\r\n<h2 class="h-color">Remember to manage well</h2>\r\n\r\n<p>Your profile tells us that you have Chronic Kidney Disease. Your doctor will be assisting you in managing this. In many cases Kidney disease cannot be prevented but there are steps you can take to reduce your risk of developing it.</p>\r\n\r\n<p>Below you will find information on further supports</p>\r\n\r\n<p>Evidence tells us that there is an increased risk of dementia in those with chronic kidney disease (CKD).</p>\r\n\r\n<ul>\r\n	<li>Chronic Kidney Disease describes the gradual loss of kidney function. Your kidney filters wastes and excess fluids from your blood, which are then excreted in your urine</li>\r\n</ul>\r\n\r\n<p>Some causes of CKD are:</p>\r\n\r\n<ul>\r\n	<li>high blood pressure</li>\r\n	<li>Type 1 or 2 Diabetes</li>\r\n	<li>Recurrent kidney infection</li>\r\n	<li>Too much potassium can build up when the kidneys are no longer functioning well</li>\r\n	<li>Potassium if found in many foods.</li>\r\n</ul>\r\n\r\n<h3>What Can I do?</h3>\r\n\r\n<p>Stick to the recommended alcohol consumption limits (See our alcohol page <a href="infos-riskfactor.html?riskfactor=6">here</a>)</p>\r\n\r\n<p>Eat a healthy balanced diet that includes plenty of fresh fruit and vegetable and fish (see our diet page <a href="infos-riskfactor.html?riskfactor=9">here</a>)</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img2.png" /></p>\r\n\r\n<p>Take regular exercise (see our exercise page <a href="infos-riskfactor.html?riskfactor=4">here</a>)</p>\r\n\r\n<p><img alt="" src="images/riskfactors/diabetes/img3.png" /></p>\r\n\r\n<p>Quit smoking (see our smoking page <a href="infos-riskfactor.html?riskfactor=5">here</a>)</p>', '');
/*!40000 ALTER TABLE `support_riskfactors` ENABLE KEYS */;


-- Dumping structure for table inmindd.user
CREATE TABLE IF NOT EXISTS `user` (
  `userId` varchar(7) NOT NULL,
  `passwordhash` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `scored` tinyint(4) DEFAULT NULL,
  `randomised_group` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.user: ~56 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `passwordhash`, `timestamp`, `scored`, `randomised_group`) VALUES
	('101', '0023bc0fd9afe67d0263ccf9ee3a5f7721739de2', '2014-07-08 11:22:07', NULL, NULL),
	('1101002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-11 13:50:53', NULL, NULL),
	('1101002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 14:08:24', NULL, NULL),
	('1101003', '816e2125284adec1ce16e4d2dcf64703493f5d8b', '2014-06-12 16:47:08', NULL, NULL),
	('1101003', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-07 12:28:48', NULL, NULL),
	('1101030', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-10 14:39:12', NULL, NULL),
	('1101099', 'bed4eb698c6eeea7f1ddf5397d480d3f2c0fb938', '2014-07-03 16:46:21', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:24:45', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:25:26', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:25:47', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:26:43', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:28:18', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:29:25', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:30:13', NULL, NULL),
	('1101120', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-14 09:32:40', NULL, NULL),
	('1102007', 'b3279ee0c866b96c38a7321679bb7b7c63ce68e2', '2014-06-24 15:30:20', NULL, NULL),
	('1102008', 'e03bf48b900cc76bbad5e62a81c799b60010bc01', '2014-06-30 12:44:59', NULL, NULL),
	('1102009', 'ad87109bfff0765f4dd8cf4943b04d16a4070fea', '2014-06-30 15:27:18', NULL, NULL),
	('1104008', '67b5fa48f92ce8525701f324d6dfed859c20b64f', '2014-07-11 14:54:27', NULL, NULL),
	('11123', 'e9899b0a1d7657bc51d2024504acdc9464aff384', '2014-07-14 09:10:03', NULL, NULL),
	('1112345', '6420ed4d831b436d1e92d25605d18297296374e3', '2014-07-11 14:57:18', NULL, NULL),
	('11124', 'd01236df001621bc89cfc46535bf947fa703c56c', '2014-07-14 10:07:45', NULL, NULL),
	('1120192', 'bec818f97f38a66c375a13575e9c551f036941e2', '2014-07-11 15:19:13', NULL, NULL),
	('1120192', 'bec818f97f38a66c375a13575e9c551f036941e2', '2014-07-11 15:20:19', NULL, NULL),
	('1124681', '91fb64276c08bb21aded26660f7d81ba92ceea7c', '2014-07-11 16:09:55', NULL, NULL),
	('1156789', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:07:44', NULL, NULL),
	('1156789', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:07:50', NULL, NULL),
	('1156789', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:07:52', NULL, NULL),
	('1156789', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:07:53', NULL, NULL),
	('116742', '13982b89b924ed97d986fdab9a69093088782b89', '2014-07-11 15:22:26', NULL, NULL),
	('1175432', '4358f93135878b72a20b7b00954f8038f9f7095f', '2014-07-11 16:22:57', NULL, NULL),
	('1175432', '4358f93135878b72a20b7b00954f8038f9f7095f', '2014-07-11 16:28:43', NULL, NULL),
	('1198765', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:09:02', NULL, NULL),
	('1198765', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:09:05', NULL, NULL),
	('1234', '6420ed4d831b436d1e92d25605d18297296374e3', '2014-06-24 14:47:04', NULL, NULL),
	('2201001', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:39:28', NULL, NULL),
	('2201002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:41:59', NULL, NULL),
	('2201002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 11:47:59', NULL, NULL),
	('2201002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-07 15:10:09', NULL, NULL),
	('2201003', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:42:44', NULL, NULL),
	('2201004', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:43:23', NULL, NULL),
	('2201004', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:45:04', NULL, NULL),
	('2201005', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 09:49:40', NULL, NULL),
	('2201006', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 10:00:57', NULL, NULL),
	('2201010', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-06-16 10:05:46', NULL, NULL),
	('2202007', 'b3279ee0c866b96c38a7321679bb7b7c63ce68e2', '2014-07-11 14:37:18', NULL, NULL),
	('2212345', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:03:29', NULL, NULL),
	('2212345', 'a1b39dd41fb439c6eeb61bbe84136c182cea04fc', '2014-07-11 16:03:32', NULL, NULL),
	('3301002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-07 15:09:47', NULL, NULL),
	('3301002', 'cb70df9914581044b41e7b952ffebccff19f23c7', '2014-07-07 15:53:50', NULL, NULL),
	('3355555', '2600ca14f0d6d3c6ecabef4424e8b847ce358384', '2014-07-11 15:30:47', NULL, NULL),
	('36', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', '2014-07-08 11:21:43', NULL, NULL),
	('36', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', '2014-07-08 11:21:49', NULL, NULL),
	('36', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', '2014-07-08 11:21:50', NULL, NULL),
	('36', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', '2014-07-08 11:21:51', NULL, NULL),
	('52', '67d9674c8455f6c240f73ebadbe2795bb187109a', '2014-07-09 13:25:20', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_commentmeta
CREATE TABLE IF NOT EXISTS `wp_commentmeta` (
  `meta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`meta_id`),
  KEY `comment_id` (`comment_id`),
  KEY `meta_key` (`meta_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_commentmeta: ~0 rows (approximately)
/*!40000 ALTER TABLE `wp_commentmeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `wp_commentmeta` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_comments
CREATE TABLE IF NOT EXISTS `wp_comments` (
  `comment_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_post_ID` bigint(20) unsigned NOT NULL DEFAULT '0',
  `comment_author` tinytext NOT NULL,
  `comment_author_email` varchar(100) NOT NULL DEFAULT '',
  `comment_author_url` varchar(200) NOT NULL DEFAULT '',
  `comment_author_IP` varchar(100) NOT NULL DEFAULT '',
  `comment_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_date_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_content` text NOT NULL,
  `comment_karma` int(11) NOT NULL DEFAULT '0',
  `comment_approved` varchar(20) NOT NULL DEFAULT '1',
  `comment_agent` varchar(255) NOT NULL DEFAULT '',
  `comment_type` varchar(20) NOT NULL DEFAULT '',
  `comment_parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_ID`),
  KEY `comment_post_ID` (`comment_post_ID`),
  KEY `comment_approved_date_gmt` (`comment_approved`,`comment_date_gmt`),
  KEY `comment_date_gmt` (`comment_date_gmt`),
  KEY `comment_parent` (`comment_parent`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_comments: ~1 rows (approximately)
/*!40000 ALTER TABLE `wp_comments` DISABLE KEYS */;
INSERT INTO `wp_comments` (`comment_ID`, `comment_post_ID`, `comment_author`, `comment_author_email`, `comment_author_url`, `comment_author_IP`, `comment_date`, `comment_date_gmt`, `comment_content`, `comment_karma`, `comment_approved`, `comment_agent`, `comment_type`, `comment_parent`, `user_id`) VALUES
	(1, 1, 'Mr WordPress', '', 'https://wordpress.org/', '', '2014-07-04 15:16:17', '2014-07-04 15:16:17', 'Hi, this is a comment.\r\nTo delete a comment, just log in and view the post&#039;s comments. There you will have the option to edit or delete them.', 0, '1', '', '', 0, 0);
/*!40000 ALTER TABLE `wp_comments` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_links
CREATE TABLE IF NOT EXISTS `wp_links` (
  `link_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `link_url` varchar(255) NOT NULL DEFAULT '',
  `link_name` varchar(255) NOT NULL DEFAULT '',
  `link_image` varchar(255) NOT NULL DEFAULT '',
  `link_target` varchar(25) NOT NULL DEFAULT '',
  `link_description` varchar(255) NOT NULL DEFAULT '',
  `link_visible` varchar(20) NOT NULL DEFAULT 'Y',
  `link_owner` bigint(20) unsigned NOT NULL DEFAULT '1',
  `link_rating` int(11) NOT NULL DEFAULT '0',
  `link_updated` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `link_rel` varchar(255) NOT NULL DEFAULT '',
  `link_notes` mediumtext NOT NULL,
  `link_rss` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`link_id`),
  KEY `link_visible` (`link_visible`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_links: ~0 rows (approximately)
/*!40000 ALTER TABLE `wp_links` DISABLE KEYS */;
/*!40000 ALTER TABLE `wp_links` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_options
CREATE TABLE IF NOT EXISTS `wp_options` (
  `option_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `option_name` varchar(64) NOT NULL DEFAULT '',
  `option_value` longtext NOT NULL,
  `autoload` varchar(20) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_options: ~111 rows (approximately)
/*!40000 ALTER TABLE `wp_options` DISABLE KEYS */;
INSERT INTO `wp_options` (`option_id`, `option_name`, `option_value`, `autoload`) VALUES
	(1, 'siteurl', 'https://inminddblog.appspot.com', 'yes'),
	(2, 'blogname', 'Inmindd Blog', 'yes'),
	(3, 'blogdescription', 'Just another WordPress site', 'yes'),
	(4, 'users_can_register', '0', 'yes'),
	(5, 'admin_email', 'romain@romainbeuque.fr', 'yes'),
	(6, 'start_of_week', '1', 'yes'),
	(7, 'use_balanceTags', '0', 'yes'),
	(8, 'use_smilies', '1', 'yes'),
	(9, 'require_name_email', '1', 'yes'),
	(10, 'comments_notify', '1', 'yes'),
	(11, 'posts_per_rss', '10', 'yes'),
	(12, 'rss_use_excerpt', '0', 'yes'),
	(13, 'mailserver_url', 'mail.example.com', 'yes'),
	(14, 'mailserver_login', 'login@example.com', 'yes'),
	(15, 'mailserver_pass', 'password', 'yes'),
	(16, 'mailserver_port', '110', 'yes'),
	(17, 'default_category', '1', 'yes'),
	(18, 'default_comment_status', 'open', 'yes'),
	(19, 'default_ping_status', 'open', 'yes'),
	(20, 'default_pingback_flag', '0', 'yes'),
	(21, 'posts_per_page', '10', 'yes'),
	(22, 'date_format', 'F j, Y', 'yes'),
	(23, 'time_format', 'g:i a', 'yes'),
	(24, 'links_updated_date_format', 'F j, Y g:i a', 'yes'),
	(25, 'comment_moderation', '0', 'yes'),
	(26, 'moderation_notify', '1', 'yes'),
	(27, 'permalink_structure', '', 'yes'),
	(28, 'gzipcompression', '0', 'yes'),
	(29, 'hack_file', '0', 'yes'),
	(30, 'blog_charset', 'UTF-8', 'yes'),
	(31, 'moderation_keys', '', 'no'),
	(32, 'active_plugins', 'a:2:{i:0;s:13:"appengine.php";i:1;s:12:"batcache.php";}', 'yes'),
	(33, 'home', 'https://inminddblog.appspot.com', 'yes'),
	(34, 'category_base', '', 'yes'),
	(35, 'ping_sites', 'http://rpc.pingomatic.com/', 'yes'),
	(36, 'advanced_edit', '0', 'yes'),
	(37, 'comment_max_links', '2', 'yes'),
	(38, 'gmt_offset', '0', 'yes'),
	(39, 'default_email_category', '1', 'yes'),
	(40, 'recently_edited', '', 'no'),
	(41, 'template', 'twentyten', 'yes'),
	(42, 'stylesheet', 'twentyten', 'yes'),
	(43, 'comment_whitelist', '1', 'yes'),
	(44, 'blacklist_keys', '', 'no'),
	(45, 'comment_registration', '0', 'yes'),
	(46, 'html_type', 'text/html', 'yes'),
	(47, 'use_trackback', '0', 'yes'),
	(48, 'default_role', 'subscriber', 'yes'),
	(49, 'db_version', '27916', 'yes'),
	(50, 'uploads_use_yearmonth_folders', '1', 'yes'),
	(51, 'upload_path', '', 'yes'),
	(52, 'blog_public', '0', 'yes'),
	(53, 'default_link_category', '2', 'yes'),
	(54, 'show_on_front', 'posts', 'yes'),
	(55, 'tag_base', '', 'yes'),
	(56, 'show_avatars', '1', 'yes'),
	(57, 'avatar_rating', 'G', 'yes'),
	(58, 'upload_url_path', '', 'yes'),
	(59, 'thumbnail_size_w', '150', 'yes'),
	(60, 'thumbnail_size_h', '150', 'yes'),
	(61, 'thumbnail_crop', '1', 'yes'),
	(62, 'medium_size_w', '300', 'yes'),
	(63, 'medium_size_h', '300', 'yes'),
	(64, 'avatar_default', 'mystery', 'yes'),
	(65, 'large_size_w', '1024', 'yes'),
	(66, 'large_size_h', '1024', 'yes'),
	(67, 'image_default_link_type', 'file', 'yes'),
	(68, 'image_default_size', '', 'yes'),
	(69, 'image_default_align', '', 'yes'),
	(70, 'close_comments_for_old_posts', '0', 'yes'),
	(71, 'close_comments_days_old', '14', 'yes'),
	(72, 'thread_comments', '1', 'yes'),
	(73, 'thread_comments_depth', '5', 'yes'),
	(74, 'page_comments', '0', 'yes'),
	(75, 'comments_per_page', '50', 'yes'),
	(76, 'default_comments_page', 'newest', 'yes'),
	(77, 'comment_order', 'asc', 'yes'),
	(78, 'sticky_posts', 'a:0:{}', 'yes'),
	(79, 'widget_categories', 'a:2:{i:2;a:4:{s:5:"title";s:0:"";s:5:"count";i:0;s:12:"hierarchical";i:0;s:8:"dropdown";i:0;}s:12:"_multiwidget";i:1;}', 'yes'),
	(80, 'widget_text', 'a:0:{}', 'yes'),
	(81, 'widget_rss', 'a:0:{}', 'yes'),
	(82, 'uninstall_plugins', 'a:0:{}', 'no'),
	(83, 'timezone_string', '', 'yes'),
	(84, 'page_for_posts', '0', 'yes'),
	(85, 'page_on_front', '0', 'yes'),
	(86, 'default_post_format', '0', 'yes'),
	(87, 'link_manager_enabled', '0', 'yes'),
	(88, 'initial_db_version', '27916', 'yes'),
	(89, 'wp_user_roles', 'a:5:{s:13:"administrator";a:2:{s:4:"name";s:13:"Administrator";s:12:"capabilities";a:62:{s:13:"switch_themes";b:1;s:11:"edit_themes";b:1;s:16:"activate_plugins";b:1;s:12:"edit_plugins";b:1;s:10:"edit_users";b:1;s:10:"edit_files";b:1;s:14:"manage_options";b:1;s:17:"moderate_comments";b:1;s:17:"manage_categories";b:1;s:12:"manage_links";b:1;s:12:"upload_files";b:1;s:6:"import";b:1;s:15:"unfiltered_html";b:1;s:10:"edit_posts";b:1;s:17:"edit_others_posts";b:1;s:20:"edit_published_posts";b:1;s:13:"publish_posts";b:1;s:10:"edit_pages";b:1;s:4:"read";b:1;s:8:"level_10";b:1;s:7:"level_9";b:1;s:7:"level_8";b:1;s:7:"level_7";b:1;s:7:"level_6";b:1;s:7:"level_5";b:1;s:7:"level_4";b:1;s:7:"level_3";b:1;s:7:"level_2";b:1;s:7:"level_1";b:1;s:7:"level_0";b:1;s:17:"edit_others_pages";b:1;s:20:"edit_published_pages";b:1;s:13:"publish_pages";b:1;s:12:"delete_pages";b:1;s:19:"delete_others_pages";b:1;s:22:"delete_published_pages";b:1;s:12:"delete_posts";b:1;s:19:"delete_others_posts";b:1;s:22:"delete_published_posts";b:1;s:20:"delete_private_posts";b:1;s:18:"edit_private_posts";b:1;s:18:"read_private_posts";b:1;s:20:"delete_private_pages";b:1;s:18:"edit_private_pages";b:1;s:18:"read_private_pages";b:1;s:12:"delete_users";b:1;s:12:"create_users";b:1;s:17:"unfiltered_upload";b:1;s:14:"edit_dashboard";b:1;s:14:"update_plugins";b:1;s:14:"delete_plugins";b:1;s:15:"install_plugins";b:1;s:13:"update_themes";b:1;s:14:"install_themes";b:1;s:11:"update_core";b:1;s:10:"list_users";b:1;s:12:"remove_users";b:1;s:9:"add_users";b:1;s:13:"promote_users";b:1;s:18:"edit_theme_options";b:1;s:13:"delete_themes";b:1;s:6:"export";b:1;}}s:6:"editor";a:2:{s:4:"name";s:6:"Editor";s:12:"capabilities";a:34:{s:17:"moderate_comments";b:1;s:17:"manage_categories";b:1;s:12:"manage_links";b:1;s:12:"upload_files";b:1;s:15:"unfiltered_html";b:1;s:10:"edit_posts";b:1;s:17:"edit_others_posts";b:1;s:20:"edit_published_posts";b:1;s:13:"publish_posts";b:1;s:10:"edit_pages";b:1;s:4:"read";b:1;s:7:"level_7";b:1;s:7:"level_6";b:1;s:7:"level_5";b:1;s:7:"level_4";b:1;s:7:"level_3";b:1;s:7:"level_2";b:1;s:7:"level_1";b:1;s:7:"level_0";b:1;s:17:"edit_others_pages";b:1;s:20:"edit_published_pages";b:1;s:13:"publish_pages";b:1;s:12:"delete_pages";b:1;s:19:"delete_others_pages";b:1;s:22:"delete_published_pages";b:1;s:12:"delete_posts";b:1;s:19:"delete_others_posts";b:1;s:22:"delete_published_posts";b:1;s:20:"delete_private_posts";b:1;s:18:"edit_private_posts";b:1;s:18:"read_private_posts";b:1;s:20:"delete_private_pages";b:1;s:18:"edit_private_pages";b:1;s:18:"read_private_pages";b:1;}}s:6:"author";a:2:{s:4:"name";s:6:"Author";s:12:"capabilities";a:10:{s:12:"upload_files";b:1;s:10:"edit_posts";b:1;s:20:"edit_published_posts";b:1;s:13:"publish_posts";b:1;s:4:"read";b:1;s:7:"level_2";b:1;s:7:"level_1";b:1;s:7:"level_0";b:1;s:12:"delete_posts";b:1;s:22:"delete_published_posts";b:1;}}s:11:"contributor";a:2:{s:4:"name";s:11:"Contributor";s:12:"capabilities";a:5:{s:10:"edit_posts";b:1;s:4:"read";b:1;s:7:"level_1";b:1;s:7:"level_0";b:1;s:12:"delete_posts";b:1;}}s:10:"subscriber";a:2:{s:4:"name";s:10:"Subscriber";s:12:"capabilities";a:2:{s:4:"read";b:1;s:7:"level_0";b:1;}}}', 'yes'),
	(90, 'widget_search', 'a:2:{i:2;a:1:{s:5:"title";s:0:"";}s:12:"_multiwidget";i:1;}', 'yes'),
	(91, 'widget_recent-posts', 'a:2:{i:2;a:2:{s:5:"title";s:0:"";s:6:"number";i:5;}s:12:"_multiwidget";i:1;}', 'yes'),
	(92, 'widget_recent-comments', 'a:2:{i:2;a:2:{s:5:"title";s:0:"";s:6:"number";i:5;}s:12:"_multiwidget";i:1;}', 'yes'),
	(93, 'widget_archives', 'a:2:{i:2;a:3:{s:5:"title";s:0:"";s:5:"count";i:0;s:8:"dropdown";i:0;}s:12:"_multiwidget";i:1;}', 'yes'),
	(94, 'widget_meta', 'a:2:{i:2;a:1:{s:5:"title";s:0:"";}s:12:"_multiwidget";i:1;}', 'yes'),
	(95, 'sidebars_widgets', 'a:8:{s:19:"wp_inactive_widgets";a:0:{}s:19:"primary-widget-area";a:6:{i:0;s:8:"search-2";i:1;s:14:"recent-posts-2";i:2;s:17:"recent-comments-2";i:3;s:10:"archives-2";i:4;s:12:"categories-2";i:5;s:6:"meta-2";}s:21:"secondary-widget-area";a:0:{}s:24:"first-footer-widget-area";a:0:{}s:25:"second-footer-widget-area";N;s:24:"third-footer-widget-area";N;s:25:"fourth-footer-widget-area";N;s:13:"array_version";i:3;}', 'yes'),
	(96, 'cron', 'a:5:{i:1405523782;a:3:{s:16:"wp_version_check";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:10:"twicedaily";s:4:"args";a:0:{}s:8:"interval";i:43200;}}s:17:"wp_update_plugins";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:10:"twicedaily";s:4:"args";a:0:{}s:8:"interval";i:43200;}}s:16:"wp_update_themes";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:10:"twicedaily";s:4:"args";a:0:{}s:8:"interval";i:43200;}}}i:1405523811;a:1:{s:19:"wp_scheduled_delete";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:5:"daily";s:4:"args";a:0:{}s:8:"interval";i:86400;}}}i:1405523818;a:1:{s:30:"wp_scheduled_auto_draft_delete";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:5:"daily";s:4:"args";a:0:{}s:8:"interval";i:86400;}}}i:1405539600;a:1:{s:20:"wp_maybe_auto_update";a:1:{s:32:"40cd750bba9870f18aada2478b24840a";a:3:{s:8:"schedule";s:10:"twicedaily";s:4:"args";a:0:{}s:8:"interval";i:43200;}}}s:7:"version";i:2;}', 'yes'),
	(97, 'secure_auth_key', 'ekpS-qx0D^6W]?}o,4}X@p q{>QH[@_-AjlE6wo%yxrEqn.{DC@IC(w}A7eVo@ZV', 'yes'),
	(98, 'secure_auth_salt', '&u9c~u1),~Vnk2z[?[(C$CX^;e^_jS3K1z7HScorpLkb-!QWVW|>jDA.// 9!;xJ', 'yes'),
	(99, 'logged_in_key', 's-M KTGB!T{.z{^Z=d8sZQ!euDFVh%M<xd5UM-.>*F::43Uc&Xn14q!`~Ts^/Dcm', 'yes'),
	(100, 'logged_in_salt', 'FHfqbARjn:hG_lF5Q8~7I(J&>6Lwv dTgbHUJ<}Xm0$:d]]S}CwAjh3(&EJbwBLC', 'yes'),
	(101, 'nonce_key', 'Du$@s{$Q~Nd2|)jsr[{wB[-Rpt{tYT^:#.K:Q0t`f?~zYBr]v1/lX>`p9z t5w,r', 'yes'),
	(102, 'nonce_salt', 'I0u9d9p}dG0~[whI2)Kk_x8sE#hPB;{3=wp~Q33mlD([Fnd`Gc}~;uoGU]#1H(*X', 'yes'),
	(103, 'can_compress_scripts', '0', 'yes'),
	(104, 'theme_mods_twentyfourteen', 'a:1:{s:16:"sidebars_widgets";a:2:{s:4:"time";i:1404487172;s:4:"data";a:4:{s:19:"wp_inactive_widgets";a:0:{}s:9:"sidebar-1";a:6:{i:0;s:8:"search-2";i:1;s:14:"recent-posts-2";i:2;s:17:"recent-comments-2";i:3;s:10:"archives-2";i:4;s:12:"categories-2";i:5;s:6:"meta-2";}s:9:"sidebar-2";a:0:{}s:9:"sidebar-3";a:0:{}}}}', 'yes'),
	(105, 'current_theme', 'Twenty Ten', 'yes'),
	(106, 'theme_mods_twentyten', 'a:1:{i:0;b:0;}', 'yes'),
	(107, 'theme_switched', '', 'yes'),
	(108, 'recently_activated', 'a:0:{}', 'yes'),
	(110, 'appengine_uploads_bucket', 'inminddblog.appspot.com', 'yes'),
	(111, 'appengine_email_enable', '1', 'yes'),
	(112, 'appengine_email', '', 'yes'),
	(113, 'auth_key', '{v##8fbIwU=Y+*!o7t0S&y&UgGP*%_9*i5l>qz5%:,8K//;A>C0~49@,7xA`S>@8', 'yes'),
	(114, 'auth_salt', 'I(~m80g8<[Fvs0u*v7f!Yn6y)ErfC&[^i1q@^Bg=12`iWGQz.}=j8J/Ba[v6mg/7', 'yes');
/*!40000 ALTER TABLE `wp_options` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_postmeta
CREATE TABLE IF NOT EXISTS `wp_postmeta` (
  `meta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`meta_id`),
  KEY `post_id` (`post_id`),
  KEY `meta_key` (`meta_key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_postmeta: ~4 rows (approximately)
/*!40000 ALTER TABLE `wp_postmeta` DISABLE KEYS */;
INSERT INTO `wp_postmeta` (`meta_id`, `post_id`, `meta_key`, `meta_value`) VALUES
	(1, 2, '_wp_page_template', 'default'),
	(2, 4, '_wp_trash_meta_status', 'auto-draft'),
	(3, 4, '_wp_trash_meta_time', '1404487032'),
	(4, 1, '_edit_lock', '1404486993:1');
/*!40000 ALTER TABLE `wp_postmeta` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_posts
CREATE TABLE IF NOT EXISTS `wp_posts` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0',
  `post_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_date_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_content` longtext NOT NULL,
  `post_title` text NOT NULL,
  `post_excerpt` text NOT NULL,
  `post_status` varchar(20) NOT NULL DEFAULT 'publish',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open',
  `ping_status` varchar(20) NOT NULL DEFAULT 'open',
  `post_password` varchar(20) NOT NULL DEFAULT '',
  `post_name` varchar(200) NOT NULL DEFAULT '',
  `to_ping` text NOT NULL,
  `pinged` text NOT NULL,
  `post_modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_modified_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_content_filtered` longtext NOT NULL,
  `post_parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `guid` varchar(255) NOT NULL DEFAULT '',
  `menu_order` int(11) NOT NULL DEFAULT '0',
  `post_type` varchar(20) NOT NULL DEFAULT 'post',
  `post_mime_type` varchar(100) NOT NULL DEFAULT '',
  `comment_count` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `post_name` (`post_name`),
  KEY `type_status_date` (`post_type`,`post_status`,`post_date`,`ID`),
  KEY `post_parent` (`post_parent`),
  KEY `post_author` (`post_author`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_posts: ~4 rows (approximately)
/*!40000 ALTER TABLE `wp_posts` DISABLE KEYS */;
INSERT INTO `wp_posts` (`ID`, `post_author`, `post_date`, `post_date_gmt`, `post_content`, `post_title`, `post_excerpt`, `post_status`, `comment_status`, `ping_status`, `post_password`, `post_name`, `to_ping`, `pinged`, `post_modified`, `post_modified_gmt`, `post_content_filtered`, `post_parent`, `guid`, `menu_order`, `post_type`, `post_mime_type`, `comment_count`) VALUES
	(1, 1, '2014-07-04 15:16:17', '2014-07-04 15:16:17', 'Welcome to WordPress. This is your first post. Edit or delete it, then start blogging!', 'Hello world!', '', 'publish', 'open', 'open', '', 'hello-world', '', '', '2014-07-04 15:16:17', '2014-07-04 15:16:17', '', 0, 'https://inminddblog.appspot.com/?p=1', 0, 'post', '', 1),
	(2, 1, '2014-07-04 15:16:17', '2014-07-04 15:16:17', 'This is an example page. It\'s different from a blog post because it will stay in one place and will show up in your site navigation (in most themes). Most people start with an About page that introduces them to potential site visitors. It might say something like this:\r\n\r\n<blockquote>Hi there! I\'m a bike messenger by day, aspiring actor by night, and this is my blog. I live in Los Angeles, have a great dog named Jack, and I like pi&#241;a coladas. (And gettin\' caught in the rain.)</blockquote>\r\n\r\n...or something like this:\r\n\r\n<blockquote>The XYZ Doohickey Company was founded in 1971, and has been providing quality doohickeys to the public ever since. Located in Gotham City, XYZ employs over 2,000 people and does all kinds of awesome things for the Gotham community.</blockquote>\r\n\r\nAs a new WordPress user, you should go to <a href="https://inminddblog.appspot.com/wp-admin/">your dashboard</a> to delete this page and create new pages for your content. Have fun!', 'Sample Page', '', 'publish', 'open', 'open', '', 'sample-page', '', '', '2014-07-04 15:16:17', '2014-07-04 15:16:17', '', 0, 'https://inminddblog.appspot.com/?page_id=2', 0, 'page', '', 0),
	(4, 1, '2014-07-04 15:16:58', '2014-07-04 15:16:58', '', 'Auto Draft', '', 'trash', 'open', 'open', '', 'auto-draft', '', '', '2014-07-04 15:17:12', '2014-07-04 15:17:12', '', 0, 'https://inminddblog.appspot.com/?p=4', 0, 'post', '', 0),
	(5, 1, '2014-07-04 15:17:12', '2014-07-04 15:17:12', '', 'Auto Draft', '', 'inherit', 'open', 'open', '', '4-revision-v1', '', '', '2014-07-04 15:17:12', '2014-07-04 15:17:12', '', 4, 'https://inminddblog.appspot.com/?p=5', 0, 'revision', '', 0),
	(6, 1, '2014-07-16 11:19:30', '0000-00-00 00:00:00', '', 'Auto Draft', '', 'auto-draft', 'open', 'open', '', '', '', '', '2014-07-16 11:19:30', '0000-00-00 00:00:00', '', 0, 'http://127.0.0.1:8080/?p=6', 0, 'post', '', 0);
/*!40000 ALTER TABLE `wp_posts` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_terms
CREATE TABLE IF NOT EXISTS `wp_terms` (
  `term_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `slug` varchar(200) NOT NULL DEFAULT '',
  `term_group` bigint(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`term_id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_terms: ~1 rows (approximately)
/*!40000 ALTER TABLE `wp_terms` DISABLE KEYS */;
INSERT INTO `wp_terms` (`term_id`, `name`, `slug`, `term_group`) VALUES
	(1, 'Uncategorized', 'uncategorized', 0);
/*!40000 ALTER TABLE `wp_terms` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_term_relationships
CREATE TABLE IF NOT EXISTS `wp_term_relationships` (
  `object_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_taxonomy_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`term_taxonomy_id`),
  KEY `term_taxonomy_id` (`term_taxonomy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_term_relationships: ~2 rows (approximately)
/*!40000 ALTER TABLE `wp_term_relationships` DISABLE KEYS */;
INSERT INTO `wp_term_relationships` (`object_id`, `term_taxonomy_id`, `term_order`) VALUES
	(1, 1, 0),
	(4, 1, 0);
/*!40000 ALTER TABLE `wp_term_relationships` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_term_taxonomy
CREATE TABLE IF NOT EXISTS `wp_term_taxonomy` (
  `term_taxonomy_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `term_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `taxonomy` varchar(32) NOT NULL DEFAULT '',
  `description` longtext NOT NULL,
  `parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `count` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`term_taxonomy_id`),
  UNIQUE KEY `term_id_taxonomy` (`term_id`,`taxonomy`),
  KEY `taxonomy` (`taxonomy`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_term_taxonomy: ~1 rows (approximately)
/*!40000 ALTER TABLE `wp_term_taxonomy` DISABLE KEYS */;
INSERT INTO `wp_term_taxonomy` (`term_taxonomy_id`, `term_id`, `taxonomy`, `description`, `parent`, `count`) VALUES
	(1, 1, 'category', '', 0, 1);
/*!40000 ALTER TABLE `wp_term_taxonomy` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_usermeta
CREATE TABLE IF NOT EXISTS `wp_usermeta` (
  `umeta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`umeta_id`),
  KEY `user_id` (`user_id`),
  KEY `meta_key` (`meta_key`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_usermeta: ~14 rows (approximately)
/*!40000 ALTER TABLE `wp_usermeta` DISABLE KEYS */;
INSERT INTO `wp_usermeta` (`umeta_id`, `user_id`, `meta_key`, `meta_value`) VALUES
	(1, 1, 'first_name', ''),
	(2, 1, 'last_name', ''),
	(3, 1, 'nickname', 'romain'),
	(4, 1, 'description', ''),
	(5, 1, 'rich_editing', 'true'),
	(6, 1, 'comment_shortcuts', 'false'),
	(7, 1, 'admin_color', 'fresh'),
	(8, 1, 'use_ssl', '0'),
	(9, 1, 'show_admin_bar_front', 'true'),
	(10, 1, 'wp_capabilities', 'a:1:{s:13:"administrator";b:1;}'),
	(11, 1, 'wp_user_level', '10'),
	(12, 1, 'dismissed_wp_pointers', 'wp350_media,wp360_revisions,wp360_locks,wp390_widgets'),
	(13, 1, 'show_welcome_panel', '1'),
	(14, 1, 'wp_dashboard_quick_press_last_post_id', '6'),
	(15, 1, 'wp_user-settings', 'editor=html'),
	(16, 1, 'wp_user-settings-time', '1405509563');
/*!40000 ALTER TABLE `wp_usermeta` ENABLE KEYS */;


-- Dumping structure for table inmindd.wp_users
CREATE TABLE IF NOT EXISTS `wp_users` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_login` varchar(60) NOT NULL DEFAULT '',
  `user_pass` varchar(64) NOT NULL DEFAULT '',
  `user_nicename` varchar(50) NOT NULL DEFAULT '',
  `user_email` varchar(100) NOT NULL DEFAULT '',
  `user_url` varchar(100) NOT NULL DEFAULT '',
  `user_registered` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_activation_key` varchar(60) NOT NULL DEFAULT '',
  `user_status` int(11) NOT NULL DEFAULT '0',
  `display_name` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `user_login_key` (`user_login`),
  KEY `user_nicename` (`user_nicename`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table inmindd.wp_users: ~1 rows (approximately)
/*!40000 ALTER TABLE `wp_users` DISABLE KEYS */;
INSERT INTO `wp_users` (`ID`, `user_login`, `user_pass`, `user_nicename`, `user_email`, `user_url`, `user_registered`, `user_activation_key`, `user_status`, `display_name`) VALUES
	(1, 'romain', '$P$B/O39lcim7gpp2InellVi6u.Rqzk5I.', 'romain', 'romain@romainbeuque.fr', '', '2014-07-04 15:16:16', '', 0, 'romain');
/*!40000 ALTER TABLE `wp_users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
