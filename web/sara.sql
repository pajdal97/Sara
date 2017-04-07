-- phpMyAdmin SQL Dump
-- version 4.4.15.1
-- http://www.phpmyadmin.net
--
-- Počítač: wm110.wedos.net:3306
-- Vytvořeno: Sob 08. dub 2017, 01:49
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
-- Struktura tabulky `objects`
--

CREATE TABLE IF NOT EXISTS `objects` (
  `id` bigint(255) unsigned NOT NULL,
  `type` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `data` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `timestamp` bigint(255) NOT NULL,
  `name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `room` mediumint(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `objects`
--

INSERT INTO `objects` (`id`, `type`, `data`, `timestamp`, `name`, `room`) VALUES
(1, 'light', 'off', 1491572234, 'Door to house', 3),
(2, 'light', 'on', 1491572234, 'Door to house', 1),
(3, 'door', 'close', 1491572234, 'Door to house', 1),
(4, 'door', 'close', 1491572234, 'Door to house', 1),
(5, 'door', 'close', 1491572234, 'Door to house', 1);

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

-- --------------------------------------------------------

--
-- Struktura tabulky `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `id` mediumint(255) unsigned NOT NULL,
  `user_id` mediumint(255) NOT NULL,
  `ssid` mediumint(255) NOT NULL,
  `timestamp` bigint(255) NOT NULL,
  `origin_ssid` mediumint(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` mediumint(255) unsigned NOT NULL,
  `username` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `ssid` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `timestamp` bigint(255) NOT NULL,
  `group` mediumint(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `users`
--

INSERT INTO `users` (`id`, `username`, `first_name`, `last_name`, `ssid`, `token`, `timestamp`, `group`) VALUES
(1, 'admin', 'Vladimir', 'Palousek', '', 'easy_token', 0, 100);

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `objects`
--
ALTER TABLE `objects`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `objects`
--
ALTER TABLE `objects`
  MODIFY `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pro tabulku `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` mediumint(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pro tabulku `sessions`
--
ALTER TABLE `sessions`
  MODIFY `id` mediumint(255) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pro tabulku `users`
--
ALTER TABLE `users`
  MODIFY `id` mediumint(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
