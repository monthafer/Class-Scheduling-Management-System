-- Database structure for Class Scheduling Management System (CSMS)

SET FOREIGN_KEY_CHECKS = 0;

-- --------------------------------------------------------
-- Table structure for table `admin`
--
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(50) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `fbAcc` varchar(50) DEFAULT NULL,
  `bio` varchar(200) DEFAULT NULL,
  `job` varchar(50) NOT NULL,
  `job_location` varchar(50) DEFAULT NULL,
  `job_info` varchar(200) DEFAULT NULL,
  `img_avatar` longblob DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `teacher`
--
CREATE TABLE IF NOT EXISTS `teacher` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(50) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `facebook` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `consult_hours` varchar(50) DEFAULT NULL,
  `consult_days` varchar(50) DEFAULT NULL,
  `tea_desc` varchar(200) NOT NULL,
  `img_avatar` longblob DEFAULT NULL,
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `sgs`
--
CREATE TABLE IF NOT EXISTS `sgs` (
  `sgs_id` int(11) NOT NULL,
  `strand` varchar(30) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `section` varchar(30) DEFAULT NULL,
  `teacher_adviser` int(11) DEFAULT NULL,
  PRIMARY KEY (`sgs_id`),
  KEY `teacher_adviser` (`teacher_adviser`),
  CONSTRAINT `sgs_ibfk_1` FOREIGN KEY (`teacher_adviser`) REFERENCES `teacher` (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `student`
--
CREATE TABLE IF NOT EXISTS `student` (
  `stud_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(50) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `facebook` varchar(50) DEFAULT NULL,
  `student_desc` varchar(200) NOT NULL,
  `img_avatar` longblob DEFAULT NULL,
  `sgs_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stud_id`),
  KEY `sgs_id` (`sgs_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`sgs_id`) REFERENCES `sgs` (`sgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `location`
--
CREATE TABLE IF NOT EXISTS `location` (
  `loc_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep` varchar(30) DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  `room_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `subject`
--
CREATE TABLE IF NOT EXISTS `subject` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `sem` int(11) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  `day1` varchar(30) DEFAULT NULL,
  `day2` varchar(30) DEFAULT NULL,
  `hour1` int(11) DEFAULT NULL,
  `min1` int(11) DEFAULT NULL,
  `hour2` int(11) DEFAULT NULL,
  `min2` int(11) DEFAULT NULL,
  `loc_id` int(11) DEFAULT NULL,
  `sgs_id` int(11) DEFAULT NULL,
  `tea_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sub_id`),
  KEY `tea_id` (`tea_id`),
  KEY `sgs_id` (`sgs_id`),
  KEY `loc_id` (`loc_id`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `subject_ibfk_2` FOREIGN KEY (`sgs_id`) REFERENCES `sgs` (`sgs_id`),
  CONSTRAINT `subject_ibfk_3` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `agenda`
--
CREATE TABLE IF NOT EXISTS `agenda` (
  `agenda_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text DEFAULT NULL,
  `date` date NOT NULL,
  `sgs_id` int(11) DEFAULT NULL,
  `repeatable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`agenda_id`),
  KEY `sgs_id` (`sgs_id`),
  CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`sgs_id`) REFERENCES `sgs` (`sgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `announce`
--
CREATE TABLE IF NOT EXISTS `announce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `tea_id` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `timecreated` date NOT NULL,
  `description` varchar(1000) NOT NULL,
  `sgs` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sgsid` (`sgs`),
  KEY `iid` (`tea_id`),
  KEY `iiid` (`admin_id`),
  CONSTRAINT `iid` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `iiid` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `sgsid` FOREIGN KEY (`sgs`) REFERENCES `sgs` (`sgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `comment`
--
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `announce` int(11) NOT NULL,
  `stud_id` int(11) DEFAULT NULL,
  `tea_id` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `comment` varchar(1000) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `stud_id` (`stud_id`),
  KEY `tea_id` (`tea_id`),
  KEY `admin_id` (`admin_id`),
  KEY `announce` (`announce`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`stud_id`) REFERENCES `student` (`stud_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `comment_ibfk_4` FOREIGN KEY (`announce`) REFERENCES `announce` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `message`
--
CREATE TABLE IF NOT EXISTS `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `send_stud` int(11) DEFAULT NULL,
  `send_tea` int(11) DEFAULT NULL,
  `send_admin` int(11) DEFAULT NULL,
  `rec_stud` int(11) DEFAULT NULL,
  `rec_tea` int(11) DEFAULT NULL,
  `rec_admin` int(11) DEFAULT NULL,
  `message` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`message_id`),
  KEY `message_ibfk_1` (`send_admin`),
  KEY `rec_stud` (`rec_stud`),
  KEY `send_stud` (`send_stud`),
  KEY `rec_tea` (`rec_tea`),
  KEY `send_tea` (`send_tea`),
  KEY `rec_admin` (`rec_admin`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`send_admin`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`rec_stud`) REFERENCES `student` (`stud_id`),
  CONSTRAINT `message_ibfk_3` FOREIGN KEY (`send_stud`) REFERENCES `student` (`stud_id`),
  CONSTRAINT `message_ibfk_4` FOREIGN KEY (`rec_tea`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `message_ibfk_5` FOREIGN KEY (`send_tea`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `message_ibfk_6` FOREIGN KEY (`rec_admin`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
