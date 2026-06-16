-- Database seed data for CSMS

SET FOREIGN_KEY_CHECKS = 0;

-- --------------------------------------------------------
-- Dumping data for table `admin`
--
INSERT INTO `admin` (`admin_id`, `first_name`, `last_name`, `username`, `email`, `password`, `contact_no`, `fbAcc`, `bio`, `job`, `job_location`, `job_info`, `img_avatar`) VALUES
(2, 'Admin', 'User', 'admin', 'admin@csms.edu', 'password123', '09000000000', 'AdminUserFB', 'System administrator account for CSMS maintenance.', 'Administrator', 'MSU-MSHS', 'I perform maintenance in this system.', NULL);

-- --------------------------------------------------------
-- Dumping data for table `teacher`
--
INSERT INTO `teacher` (`tea_id`, `first_name`, `last_name`, `username`, `email`, `password`, `contact_no`, `facebook`, `address`, `consult_hours`, `consult_days`, `tea_desc`, `img_avatar`) VALUES
(9, 'Teacher', 'One', 'teacher', 'teacher@csms.edu', 'password123', '09000000001', 'TeacherOneFB', 'MSU-MSHS Faculty', '9:00 AM - 12:00 PM', 'Mon/Wed/Fri', 'Information Technology Department Coordinator', NULL),
(10, 'Teacher', 'Two', 'adviser', 'adviser@csms.edu', 'password123', '09000000002', 'TeacherTwoFB', 'MSU-MSHS Faculty', '1:00 PM - 4:00 PM', 'Tue/Thu', 'Advisory Class Supervisor', NULL);

-- --------------------------------------------------------
-- Dumping data for table `sgs`
--
INSERT INTO `sgs` (`sgs_id`, `strand`, `grade`, `section`, `teacher_adviser`) VALUES
(0, 'STEM', 12, '1', NULL),
(1, 'STEM', 12, '2', NULL),
(2, 'STEM', 12, '3', NULL),
(3, 'STEM', 12, '4', NULL),
(4, 'STEM', 12, '5', NULL),
(5, 'HUMSS', 12, '1', NULL),
(6, 'HUMSS', 12, '2', NULL),
(7, 'ABM', 12, '1', NULL),
(8, 'ABM', 12, '2', NULL),
(9, 'ABM', 12, '3', NULL),
(10, 'ABM', 12, '4', NULL),
(11, 'TVL-ICT', 12, '1', NULL),
(12, 'TVL-ICT', 12, '2', 10),
(13, 'TVL-HE', 12, '1', NULL),
(14, 'TVL-AFA', 12, '1', NULL),
(15, 'TVL-AFA', 12, '2', NULL),
(16, 'SPORTS', 12, '1', NULL),
(17, 'SPORTS', 12, '2', NULL),
(18, 'STEM', 11, '1', NULL),
(19, 'STEM', 11, '2', NULL),
(20, 'STEM', 11, '3', NULL),
(21, 'STEM', 11, '4', NULL),
(22, 'STEM', 11, '5', NULL),
(23, 'STEM', 11, '6', NULL),
(24, 'HUMSS', 11, '1', NULL),
(25, 'HUMSS', 11, '2', NULL),
(26, 'TVL-ICT', 11, '1', 9),
(27, 'TVL-ICT', 11, '2', NULL),
(28, 'TVL-HE', 11, '1', NULL),
(29, 'SPORTS', 11, '1', NULL),
(30, 'ABM', 11, '1', NULL),
(31, 'ABM', 11, '2', NULL),
(32, 'ABM', 11, '3', NULL),
(34, 'TestStrand', 12, '1', NULL),
(35, 'Industrial Arts', 12, '1', NULL);

-- --------------------------------------------------------
-- Dumping data for table `student`
--
INSERT INTO `student` (`stud_id`, `first_name`, `last_name`, `username`, `email`, `password`, `contact_no`, `address`, `facebook`, `student_desc`, `img_avatar`, `sgs_id`) VALUES
(1, 'Student', 'User', 'student', 'student@csms.edu', 'password123', '09000000003', 'MSU Campus, Marawi City', 'StudentFB', 'Grade 12 TVL-ICT student account.', NULL, 11);

-- --------------------------------------------------------
-- Dumping data for table `location`
--
INSERT INTO `location` (`loc_id`, `dep`, `room`, `room_desc`) VALUES
(1, 'PUC', 8, 'Computer Room'),
(2, 'PUC', 7, 'Reading Room');

-- --------------------------------------------------------
-- Dumping data for table `subject`
--
INSERT INTO `subject` (`sub_id`, `name`, `sem`, `desc`, `day1`, `day2`, `hour1`, `min1`, `hour2`, `min2`, `loc_id`, `sgs_id`, `tea_id`) VALUES
(1, 'Empowerment Technologies', 1, 'ICT and modern applications', 'Monday', 'Wednesday', 8, 30, 10, 0, 1, 11, 9),
(2, 'Practical Research 2', 1, 'Quantitative Research methodologies', 'Tuesday', 'Thursday', 13, 0, 14, 30, 2, 11, 10);

-- --------------------------------------------------------
-- Dumping data for table `announce`
--
INSERT INTO `announce` (`id`, `title`, `tea_id`, `admin_id`, `timecreated`, `description`, `sgs`) VALUES
(1, 'Welcome to CSMS!', NULL, 2, '2026-06-16', 'Welcome back to classes! Please make sure to check your schedules regularly.', 11);

-- --------------------------------------------------------
-- Dumping data for table `comment`
--
INSERT INTO `comment` (`id`, `announce`, `stud_id`, `tea_id`, `admin_id`, `comment`, `timestamp`) VALUES
(1, 1, 1, NULL, NULL, 'Thank you! Excited for the semester.', '2026-06-16 07:00:00');

-- --------------------------------------------------------
-- Dumping data for table `message`
--
INSERT INTO `message` (`message_id`, `send_stud`, `send_tea`, `send_admin`, `rec_stud`, `rec_tea`, `rec_admin`, `message`, `time`) VALUES
(1, 1, NULL, NULL, NULL, NULL, 2, 'Hello Admin! Can you help me check my class enrollment status?', '2026-06-16 07:01:00'),
(2, NULL, NULL, 2, 1, NULL, NULL, 'Hi Student! Your enrollment has been verified and registered in TVL-ICT 12-1.', '2026-06-16 07:02:00'),
(3, 1, NULL, NULL, NULL, 9, NULL, 'Hello Sir! Do we have programming homework due this week?', '2026-06-16 07:03:00'),
(4, NULL, 9, NULL, 1, NULL, NULL, 'Hello! Yes, please check the timetable. Exercises 3 and 4 are due by Friday.', '2026-06-16 07:04:00');

SET FOREIGN_KEY_CHECKS = 1;
