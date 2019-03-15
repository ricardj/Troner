-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: May 20, 2018 at 11:14 AM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dpo_troner`
--
DROP DATABASE IF EXISTS `dpo_troner`;
CREATE DATABASE IF NOT EXISTS `dpo_troner` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dpo_troner`;

-- --------------------------------------------------------

--
-- Table structure for table `juga`
--

DROP TABLE IF EXISTS `juga`;
CREATE TABLE `juga` (
  `id_partida` bigint(11) NOT NULL,
  `correu` varchar(200) DEFAULT NULL,
  `nom` varchar(10) NOT NULL,
  `data_partida` date DEFAULT NULL,
  `puntuacio` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `juga`
--

INSERT INTO `juga` (`id_partida`, `correu`, `nom`, `data_partida`, `puntuacio`) VALUES
(5, 'morty@salleurl.edu', 'morty', '2018-05-15', -30),
(6, 'morty@salleurl.edu', 'morty', '2018-05-15', -10),
(7, 'a@.com', 'Stalin', '2018-05-15', -30),
(8, 'a@.com', 'Stalin', '2018-05-15', -30),
(9, 'a@.com', 'Stalin', '2018-05-15', 0),
(10, 'a@.com', 'Stalin', '2018-05-15', -10),
(11, 'morty@salleurl.edu', 'morty', '2018-05-15', -50),
(11, 'pepe@pepe.pepe', 'pepe', '2018-05-15', -40),
(11, 'prova@gmail.com', 'Prova', '2018-05-15', 50),
(12, 'morty@salleurl.edu', 'morty', '2018-05-15', -30),
(13, 'morty@salleurl.edu', 'morty', '2018-05-15', 20),
(14, 'morty@salleurl.edu', 'morty', '2018-05-15', 0),
(15, 'morty@salleurl.edu', 'morty', '2018-05-16', 0),
(15, 'pepe@pepe.pepe', 'pepe', '2018-05-16', -40),
(15, 'a@.com', 'Stalin', '2018-05-16', 10),
(16, 'a@.com', 'Stalin', '2018-05-16', -10),
(17, 'morty@salleurl.edu', 'morty', '2018-05-16', 0),
(17, 'pepe@pepe.pepe', 'pepe', '2018-05-16', 20),
(17, 'a@.com', 'Stalin', '2018-05-16', -40),
(18, 'a@.com', 'Stalin', '2018-05-16', -30),
(19, 'morty@salleurl.edu', 'morty', '2018-05-16', -30),
(20, 'morty@salleurl.edu', 'morty', '2018-05-16', 20),
(20, 'pepe@pepe.pepe', 'pepe', '2018-05-16', -50),
(20, 'a@.com', 'Stalin', '2018-05-16', -30),
(21, 'morty@salleurl.edu', 'morty', '2018-05-16', -30),
(21, 'pepe@pepe.pepe', 'pepe', '2018-05-16', -40),
(21, 'a@.com', 'Stalin', '2018-05-16', 50),
(22, 'morty@salleurl.edu', 'morty', '2018-05-16', -20),
(22, 'pepe@pepe.pepe', 'pepe', '2018-05-16', 60),
(22, 'a@.com', 'Stalin', '2018-05-16', -50),
(23, 'morty@salleurl.edu', 'morty', '2018-05-17', 30),
(24, 'morty@salleurl.edu', 'morty', '2018-05-17', 20),
(25, 'morty@salleurl.edu', 'morty', '2018-05-17', -50),
(25, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -20),
(25, 'a@.com', 'Stalin', '2018-05-17', 10),
(26, 'morty@salleurl.edu', 'morty', '2018-05-17', -40),
(26, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -20),
(26, 'a@.com', 'Stalin', '2018-05-17', -40),
(27, 'morty@salleurl.edu', 'morty', '2018-05-17', -10),
(27, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -60),
(27, 'a@.com', 'Stalin', '2018-05-17', 0),
(28, 'morty@salleurl.edu', 'morty', '2018-05-17', -40),
(28, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -20),
(28, 'a@.com', 'Stalin', '2018-05-17', 80),
(29, 'morty@salleurl.edu', 'morty', '2018-05-17', 20),
(29, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -50),
(29, 'a@.com', 'Stalin', '2018-05-17', 80),
(30, 'morty@salleurl.edu', 'morty', '2018-05-17', 30),
(31, 'morty@salleurl.edu', 'morty', '2018-05-17', -50),
(31, 'pepe@pepe.pepe', 'pepe', '2018-05-17', -60),
(31, 'a@.com', 'Stalin', '2018-05-17', 0),
(32, 'morty@salleurl.edu', 'morty', '2018-05-17', 30),
(34, 'morty@salleurl.edu', 'morty', '2018-05-18', 30),
(34, 'pepe@pepe.pepe', 'pepe', '2018-05-18', -30),
(35, 'morty@salleurl.edu', 'morty', '2018-05-18', 60),
(35, 'pepe@pepe.pepe', 'pepe', '2018-05-18', 30),
(35, 'a@.com', 'Stalin', '2018-05-18', -50),
(36, 'morty@salleurl.edu', 'morty', '2018-05-18', 90),
(36, 'pepe@pepe.pepe', 'pepe', '2018-05-18', -30),
(36, 'a@.com', 'Stalin', '2018-05-18', -50),
(37, 'pepe@pepe.pepe', 'pepe', '2018-05-18', -30),
(37, 'a@.com', 'Stalin', '2018-05-18', 30),
(38, '12@123.1', 'pepe', '2018-05-18', -10),
(38, 'a@.com', 'Stalin', '2018-05-18', 10),
(39, 'morty@salleurl.edu', 'morty', '2018-05-18', -50),
(39, '12@123.1', 'pepe', '2018-05-18', 30),
(39, 'a@.com', 'Stalin', '2018-05-18', -30),
(39, 'troner@salleurl.edu', 'troner', '2018-05-18', 10),
(40, '12@123.1', 'pepe', '2018-05-18', -10),
(40, 'a@.com', 'Stalin', '2018-05-18', 10),
(41, 'morty@salleurl.edu', 'morty', '2018-05-18', -10),
(41, 'a@.com', 'Stalin', '2018-05-18', 10),
(42, 'morty@salleurl.edu', 'morty', '2018-05-18', -10),
(42, 'a@.com', 'Stalin', '2018-05-18', 10),
(43, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(43, 'a@.com', 'Stalin', '2018-05-18', -10),
(44, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(44, 'a@.com', 'Stalin', '2018-05-18', 0),
(45, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(45, 'a@.com', 'Stalin', '2018-05-18', 0),
(46, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(46, 'a@.com', 'Stalin', '2018-05-18', 0),
(47, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(47, 'a@.com', 'Stalin', '2018-05-18', -35),
(48, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(48, 'a@.com', 'Stalin', '2018-05-18', -35),
(49, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(49, 'a@.com', 'Stalin', '2018-05-18', -35),
(50, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(50, 'a@.com', 'Stalin', '2018-05-18', -10),
(51, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(51, 'a@.com', 'Stalin', '2018-05-18', 0),
(52, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(52, 'a@.com', 'Stalin', '2018-05-18', -35),
(53, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(53, 'a@.com', 'Stalin', '2018-05-18', -35),
(54, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(54, 'a@.com', 'Stalin', '2018-05-18', 10),
(55, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(55, 'a@.com', 'Stalin', '2018-05-18', -35),
(56, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(56, 'a@.com', 'Stalin', '2018-05-18', -35),
(57, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(57, 'a@.com', 'Stalin', '2018-05-18', 0),
(58, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(58, 'a@.com', 'Stalin', '2018-05-18', -35),
(59, 'morty@salleurl.edu', 'morty', '2018-05-18', -40),
(59, '1@1.1', 'rickinillo', '2018-05-18', -40),
(59, 'a@.com', 'Stalin', '2018-05-18', -50),
(59, 'troner@salleurl.edu', 'troner', '2018-05-18', -40),
(60, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(60, '12@123.1', 'pepe', '2018-05-18', -40),
(60, '1@1.1', 'rickinillo', '2018-05-18', 10),
(60, 'a@.com', 'Stalin', '2018-05-18', -20),
(61, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(61, '1@1.1', 'rickinillo', '2018-05-18', -10),
(62, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(62, '1@1.1', 'rickinillo', '2018-05-18', -35),
(63, 'morty@salleurl.edu', 'morty', '2018-05-18', -20),
(63, '12@123.1', 'pepe', '2018-05-18', -10),
(63, '1@1.1', 'rickinillo', '2018-05-18', 0),
(63, 'a@.com', 'Stalin', '2018-05-18', -40),
(64, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(64, '12@123.1', 'pepe', '2018-05-18', -20),
(64, '1@1.1', 'rickinillo', '2018-05-18', 20),
(64, 'a@.com', 'Stalin', '2018-05-18', -40),
(65, 'morty@salleurl.edu', 'morty', '2018-05-18', -40),
(65, '12@123.1', 'pepe', '2018-05-18', -10),
(65, '1@1.1', 'rickinillo', '2018-05-18', 20),
(65, 'a@.com', 'Stalin', '2018-05-18', 10),
(66, 'morty@salleurl.edu', 'morty', '2018-05-18', 0),
(66, '12@123.1', 'pepe', '2018-05-18', -40),
(66, '1@1.1', 'rickinillo', '2018-05-18', 10),
(66, 'a@.com', 'Stalin', '2018-05-18', -20),
(67, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(67, '12@123.1', 'pepe', '2018-05-18', -20),
(67, '1@1.1', 'rickinillo', '2018-05-18', -10),
(67, 'a@.com', 'Stalin', '2018-05-18', -40),
(68, 'morty@salleurl.edu', 'morty', '2018-05-18', -40),
(68, '12@123.1', 'pepe', '2018-05-18', -10),
(68, '1@1.1', 'rickinillo', '2018-05-18', -20),
(68, 'a@.com', 'Stalin', '2018-05-18', 0),
(69, 'morty@salleurl.edu', 'morty', '2018-05-18', -20),
(69, '12@123.1', 'pepe', '2018-05-18', 0),
(69, '1@1.1', 'rickinillo', '2018-05-18', -40),
(69, 'a@.com', 'Stalin', '2018-05-18', 10),
(70, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(70, '1@1.1', 'rickinillo', '2018-05-18', 10),
(71, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(71, '1@1.1', 'rickinillo', '2018-05-18', -35),
(72, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(72, '1@1.1', 'rickinillo', '2018-05-18', -35),
(73, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(73, '1@1.1', 'rickinillo', '2018-05-18', 10),
(74, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(74, '12@123.1', 'pepe', '2018-05-18', 0),
(74, '1@1.1', 'rickinillo', '2018-05-18', -10),
(74, 'a@.com', 'Stalin', '2018-05-18', -40),
(75, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(75, '12@123.1', 'pepe', '2018-05-18', -10),
(75, '1@1.1', 'rickinillo', '2018-05-18', -30),
(75, 'a@.com', 'Stalin', '2018-05-18', -60),
(76, 'morty@salleurl.edu', 'morty', '2018-05-18', 10),
(76, '12@123.1', 'pepe', '2018-05-18', -60),
(76, '1@1.1', 'rickinillo', '2018-05-18', 50),
(76, 'a@.com', 'Stalin', '2018-05-18', -60),
(77, 'morty@salleurl.edu', 'morty', '2018-05-18', -50),
(77, '12@123.1', 'pepe', '2018-05-18', 20),
(77, '1@1.1', 'rickinillo', '2018-05-18', -30),
(77, 'a@.com', 'Stalin', '2018-05-18', -50),
(78, 'morty@salleurl.edu', 'morty', '2018-05-18', -35),
(78, '1@1.1', 'rickinillo', '2018-05-18', 10),
(79, 'morty@salleurl.edu', 'morty', '2018-05-18', -10),
(79, '1@1.1', 'rickinillo', '2018-05-18', -35),
(80, 'morty@salleurl.edu', 'morty', '2018-05-18', -50),
(80, '12@123.1', 'pepe', '2018-05-18', -30),
(80, '1@1.1', 'rickinillo', '2018-05-18', -50),
(80, 'a@.com', 'Stalin', '2018-05-18', -100),
(81, 'morty@salleurl.edu', 'morty', '2018-05-18', -10),
(81, '12@123.1', 'pepe', '2018-05-18', -40),
(81, '1@1.1', 'rickinillo', '2018-05-18', 0),
(81, 'a@.com', 'Stalin', '2018-05-18', 10),
(82, 'morty@salleurl.edu', 'morty', '2018-05-18', -40),
(82, '12@123.1', 'pepe', '2018-05-18', -20),
(82, '1@1.1', 'rickinillo', '2018-05-18', -10),
(82, 'a@.com', 'Stalin', '2018-05-18', 0),
(83, 'morty@salleurl.edu', 'morty', '2018-05-18', -20),
(83, '12@123.1', 'pepe', '2018-05-18', -40),
(83, '1@1.1', 'rickinillo', '2018-05-18', 0),
(83, 'a@.com', 'Stalin', '2018-05-18', -40),
(84, '12@123.1', 'pepe', '2018-05-18', 0),
(84, 'a@.com', 'Stalin', '2018-05-18', -35),
(85, '12@123.1', 'pepe', '2018-05-18', 10),
(85, 'a@.com', 'Stalin', '2018-05-18', -35),
(86, '12@123.1', 'pepe', '2018-05-18', 0),
(86, 'a@.com', 'Stalin', '2018-05-18', -35),
(87, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 20),
(87, '12@123.1', 'pepe', '2018-05-19', -35),
(88, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 30),
(88, 'morty@salleurl.edu', 'morty', '2018-05-19', -30),
(89, 'kalima@salleurl.edu', 'kalima', '2018-05-19', -10),
(89, '12@123.1', 'pepe', '2018-05-19', -35),
(90, 'kalima@salleurl.edu', 'kalima', '2018-05-19', -10),
(90, '12@123.1', 'pepe', '2018-05-19', -35),
(91, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 30),
(91, '12@123.1', 'pepe', '2018-05-19', -30),
(92, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 30),
(92, '12@123.1', 'pepe', '2018-05-19', -30),
(93, 'johnson@salleurl.edu', 'johnson', '2018-05-19', 30),
(93, '12@123.1', 'pepe', '2018-05-19', -30),
(94, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 30),
(94, '12@123.1', 'pepe', '2018-05-19', -30),
(95, 'johnson@salleurl.edu', 'johnson', '2018-05-19', -40),
(95, 'kalima@salleurl.edu', 'kalima', '2018-05-19', 120),
(95, '12@123.1', 'pepe', '2018-05-19', -40),
(95, 'a@.com', 'Stalin', '2018-05-19', -40),
(96, 'jorge@hotmail.com', 'Jorge', '2018-05-19', 30),
(96, 'a@.com', 'Stalin', '2018-05-19', -30),
(97, 'jorge@hotmail.com', 'Jorge', '2018-05-19', -10),
(97, 'a@.com', 'Stalin', '2018-05-19', 10),
(98, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 50),
(98, 'morty@salleurl.edu', 'morty', '2018-05-20', -60),
(98, '12@123.1', 'pepe', '2018-05-20', -50),
(98, 'a@.com', 'Stalin', '2018-05-20', -60),
(99, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 40),
(99, 'morty@salleurl.edu', 'morty', '2018-05-20', -60),
(99, '12@123.1', 'pepe', '2018-05-20', -60),
(99, 'a@.com', 'Stalin', '2018-05-20', -30),
(100, 'kalima@salleurl.edu', 'kalima', '2018-05-20', -40),
(100, 'morty@salleurl.edu', 'morty', '2018-05-20', -40),
(100, '12@123.1', 'pepe', '2018-05-20', 0),
(100, 'a@.com', 'Stalin', '2018-05-20', -40),
(101, 'kalima@salleurl.edu', 'kalima', '2018-05-20', -40),
(101, 'morty@salleurl.edu', 'morty', '2018-05-20', -40),
(101, '12@123.1', 'pepe', '2018-05-20', 10),
(101, 'a@.com', 'Stalin', '2018-05-20', -40),
(102, 'kalima@salleurl.edu', 'kalima', '2018-05-20', -30),
(102, 'morty@salleurl.edu', 'morty', '2018-05-20', 40),
(102, '12@123.1', 'pepe', '2018-05-20', 30),
(102, 'a@.com', 'Stalin', '2018-05-20', -40),
(103, 'kalima@salleurl.edu', 'kalima', '2018-05-20', -20),
(103, 'morty@salleurl.edu', 'morty', '2018-05-20', 10),
(103, '12@123.1', 'pepe', '2018-05-20', -40),
(103, 'a@.com', 'Stalin', '2018-05-20', 30),
(104, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 30),
(104, '12@123.1', 'pepe', '2018-05-20', -30),
(105, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 10),
(105, '12@123.1', 'pepe', '2018-05-20', -35),
(106, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 10),
(106, '12@123.1', 'pepe', '2018-05-20', -35),
(107, 'kalima@salleurl.edu', 'kalima', '2018-05-20', 30),
(107, '12@123.1', 'pepe', '2018-05-20', -35),
(108, 'kalima@salleurl.edu', 'kalima', '2018-05-20', -40),
(108, 'morty@salleurl.edu', 'morty', '2018-05-20', -40),
(108, '12@123.1', 'pepe', '2018-05-20', -10),
(109, 'kortana@salleurl.edu', 'kortana', '2018-05-20', 10),
(109, 'morty@salleurl.edu', 'morty', '2018-05-20', -10),
(110, 'fermin@salleurl.edu', 'fermin', '2018-05-20', 10),
(110, 'kortana@salleurl.edu', 'kortana', '2018-05-20', -10),
(111, 'kortana@salleurl.edu', 'kortana', '2018-05-20', -10),
(112, 'fermin@salleurl.edu', 'fermin', '2018-05-20', -10),
(112, 'kortana@salleurl.edu', 'kortana', '2018-05-20', 10),
(112, '12@123.1', 'pepe', '2018-05-20', -40),
(113, 'kortana@salleurl.edu', 'kortana', '2018-05-20', 0),
(113, 'a@.com', 'Stalin', '2018-05-20', -35),
(114, 'kortana@salleurl.edu', 'kortana', '2018-05-20', -20),
(114, 'a@.com', 'Stalin', '2018-05-20', 20),
(115, 'primer@salleurl.edu', 'primer', '2018-05-20', 10),
(115, 'a@.com', 'Stalin', '2018-05-20', -10),
(116, 'a@.com', 'Stalin', '2018-05-20', -30),
(116, 'werewolf@salleurl.edu', 'werewolf', '2018-05-20', 30);

-- --------------------------------------------------------

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
CREATE TABLE `partida` (
  `id_partida` bigint(20) NOT NULL,
  `mode_joc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `partida`
--

INSERT INTO `partida` (`id_partida`, `mode_joc`) VALUES
(2, 1),
(3, 2),
(4, 3),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 2),
(12, 1),
(13, 1),
(14, 1),
(15, 3),
(16, 1),
(17, 3),
(18, 1),
(19, 1),
(20, 2),
(21, 2),
(22, 2),
(23, 1),
(24, 1),
(25, 2),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(30, 1),
(31, 3),
(32, 1),
(33, 1),
(34, 1),
(35, 2),
(36, 3),
(37, 1),
(38, 1),
(39, 2),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 1),
(47, 1),
(48, 1),
(49, 1),
(50, 1),
(51, 1),
(52, 1),
(53, 1),
(54, 1),
(55, 1),
(56, 1),
(57, 1),
(58, 1),
(59, 2),
(60, 2),
(61, 1),
(62, 1),
(63, 2),
(64, 2),
(65, 2),
(66, 2),
(67, 2),
(68, 2),
(69, 2),
(70, 1),
(71, 1),
(72, 1),
(73, 1),
(74, 2),
(75, 3),
(76, 3),
(77, 3),
(78, 1),
(79, 1),
(80, 3),
(81, 2),
(82, 2),
(83, 2),
(84, 1),
(85, 1),
(86, 1),
(87, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(92, 1),
(93, 1),
(94, 1),
(95, 3),
(96, 1),
(97, 1),
(98, 3),
(99, 3),
(100, 2),
(101, 2),
(102, 2),
(103, 2),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(108, 2),
(109, 1),
(110, 1),
(111, 1),
(112, 2),
(113, 1),
(114, 1),
(115, 1),
(116, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuari`
--

DROP TABLE IF EXISTS `usuari`;
CREATE TABLE `usuari` (
  `nom` varchar(10) NOT NULL,
  `contrasenya` varchar(10) NOT NULL,
  `data_inici` date NOT NULL,
  `data_ultim_acces` date NOT NULL,
  `correu` varchar(200) NOT NULL,
  `t_amunt` char(1) NOT NULL DEFAULT 'w',
  `t_avall` char(1) NOT NULL DEFAULT 's',
  `t_esquerra` char(1) NOT NULL DEFAULT 'a',
  `t_dreta` char(1) DEFAULT 'd',
  `t_turbo` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuari`
--

INSERT INTO `usuari` (`nom`, `contrasenya`, `data_inici`, `data_ultim_acces`, `correu`, `t_amunt`, `t_avall`, `t_esquerra`, `t_dreta`, `t_turbo`) VALUES
('fermin', 'fermin', '2018-05-20', '2018-05-20', 'fermin@salleurl.edu', 'w', 's', 'a', 'd', ''),
('fraser', 'fraser', '2018-05-20', '2018-05-20', 'fraser@salleurl.edu', 'w', 's', 'a', 'd', ''),
('Irene', '123456', '2018-05-19', '2018-05-19', 'irene@salleurl.edu', 'w', 's', 'a', 'd', ''),
('johnson', 'johnson', '2018-05-19', '2018-05-19', 'johnson@salleurl.edu', 'w', 's', 'a', 'd', ''),
('Jorge', 'jorge123,', '2018-05-19', '2018-05-19', 'jorge@hotmail.com', 'w', 's', 'a', 'd', ''),
('kalima', 'kalima', '2018-05-19', '2018-05-20', 'kalima@salleurl.edu', 'w', 's', 'a', 'd', ''),
('kortana', 'kortana', '2018-05-20', '2018-05-20', 'kortana@salleurl.edu', 'w', 's', 'a', 'd', ''),
('morty', '123456', '2018-05-15', '2018-05-20', 'morty@salleurl.edu', 'w', 's', 'a', 'd', ''),
('PEPE', '123456', '2018-05-18', '2018-05-20', '12@123.1', 'w', 's', 'a', 'd', ''),
('pepe', '123456', '2018-05-02', '2018-05-20', 'pepe@pepe.pepe', 'w', 's', 'a', 'd', ''),
('primer', 'primer', '2018-05-20', '2018-05-20', 'primer@salleurl.edu', 'w', 's', 'a', 'd', ''),
('Prova', '654321', '2018-04-07', '2018-04-07', 'prova@gmail.com', 'w', 's', 'a', 'd', ''),
('rickinillo', '123456', '2018-05-18', '2018-05-20', '1@1.1', 'w', 's', 'a', 'd', ''),
('Stalin', 'Stalin', '2018-05-15', '2018-05-20', 'a@.com', 'w', 's', 'a', 'd', ''),
('troner', '123456', '2018-05-18', '2018-05-20', 'troner@salleurl.edu', 'w', 's', 'a', 'd', ''),
('werewolf', 'werewolf', '2018-05-20', '2018-05-20', 'werewolf@salleurl.edu', 'w', 's', 'a', 'd', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `juga`
--
ALTER TABLE `juga`
  ADD PRIMARY KEY (`id_partida`,`nom`),
  ADD KEY `nom` (`nom`);

--
-- Indexes for table `partida`
--
ALTER TABLE `partida`
  ADD PRIMARY KEY (`id_partida`),
  ADD UNIQUE KEY `id_partida` (`id_partida`);

--
-- Indexes for table `usuari`
--
ALTER TABLE `usuari`
  ADD PRIMARY KEY (`nom`,`correu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `partida`
--
ALTER TABLE `partida`
  MODIFY `id_partida` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `juga`
--
ALTER TABLE `juga`
  ADD CONSTRAINT `juga_ibfk_1` FOREIGN KEY (`id_partida`) REFERENCES `partida` (`id_partida`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `juga_ibfk_2` FOREIGN KEY (`nom`) REFERENCES `usuari` (`nom`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
