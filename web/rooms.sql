-- phpMyAdmin SQL Dump
-- version 4.4.15.1
-- http://www.phpmyadmin.net
--
-- Počítač: wm110.wedos.net:3306
-- Vytvořeno: Sob 08. dub 2017, 01:47
-- Verze serveru: 10.0.21-MariaDB
-- Verze PHP: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `d126367_sara`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `id` mediumint(255) unsigned NOT NULL,
  `name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `top` mediumint(255) NOT NULL,
  `left` mediumint(255) NOT NULL,
  `width` mediumint(255) NOT NULL,
  `height` mediumint(255) NOT NULL,
  `color` varchar(255) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `top`, `left`, `width`, `height`, `color`) VALUES
(1, 'kitchen', 310, 0, 400, 250, 'green'),
(2, 'bedroom3', 0, 0, 400, 300, 'blue'),
(3, 'room2', 0, 410, 100, 400, ''),
(4, 'bedroom', 410, 410, 500, 150, 'yellow'),
(5, 'bedroom2', 0, 520, 390, 400, 'red');

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` mediumint(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
