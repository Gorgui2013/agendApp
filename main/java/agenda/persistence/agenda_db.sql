-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 10, 2022 at 12:04 PM
-- Server version: 5.7.28
-- PHP Version: 7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agenda_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`idCategory`, `nom`) VALUES
(1, 'Cours'),
(2, 'TP'),
(3, 'Rencontre'),
(4, 'Match'),
(5, 'TD'),
(6, 'Réunion'),
(7, 'Rendez-vous médical'),
(8, 'Anniversaire'),
(17, 'TPE');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `dateHeureDebut` varchar(50) NOT NULL,
  `dateHeureFin` varchar(50) NOT NULL,
  `lieu` varchar(50) NOT NULL,
  `category` int(11) NOT NULL,
  `priorite` int(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`idEvent`, `nom`, `dateHeureDebut`, `dateHeureFin`, `lieu`, `category`, `priorite`, `status`) VALUES
(1, 'test event', '2022-02-09T23:34', '2022-02-09T23:39', 'Ugb', 7, 2, 0),
(2, 'test event', '2022-02-24T00:36', '2022-02-24T04:40', 'L04', 1, 3, 0),
(3, 'test 009', '2022-02-10T12:01', '2022-02-10T15:01', 'KHayraty', 4, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `event_users`
--

DROP TABLE IF EXISTS `event_users`;
CREATE TABLE IF NOT EXISTS `event_users` (
  `idEventUser` int(11) NOT NULL AUTO_INCREMENT,
  `event` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`idEventUser`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_users`
--

INSERT INTO `event_users` (`idEventUser`, `event`, `user`) VALUES
(30, 2, 1),
(35, 1, 15),
(39, 2, 15),
(40, 3, 1),
(41, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `nomUtilisateur` varchar(50) NOT NULL,
  `motDePasse` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `nomUtilisateur`, `motDePasse`) VALUES
(1, 'fallou', 'Diop', 'fallou', 'RmFsbG91MTIz'),
(2, 'modou', 'Diop', 'modou', 'TW9kb3UxMjM='),
(15, 'Fatou', 'NDIAYE', 'fatou', 'RmF0b3UxMjM=');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
