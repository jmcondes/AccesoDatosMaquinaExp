-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-10-2015 a las 20:41:32
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `maquinaexp`
--

CREATE SCHEMA `maquinaexp` ;

USE `maquinaexp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `tipo_admin` varchar(45) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id_admin`, `usuario`, `password`, `tipo_admin`) VALUES
(1, 'jose', 'admin', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `depositos`
--

CREATE TABLE IF NOT EXISTS `depositos` (
  `id_moneda` int(11) NOT NULL AUTO_INCREMENT,
  `valor` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id_moneda`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `depositos`
--

INSERT INTO `depositos` (`id_moneda`, `valor`, `cantidad`) VALUES
(1, 5, 9),
(2, 10, 17),
(3, 20, 22),
(4, 50, 26),
(5, 100, 34),
(6, 200, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispensadores`
--

CREATE TABLE IF NOT EXISTS `dispensadores` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `producto` varchar(45) NOT NULL,
  `precio` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `dispensadores`
--

INSERT INTO `dispensadores` (`id_producto`, `producto`, `precio`, `cantidad`) VALUES
(1, 'Coca Cola', 85, 15),
(2, 'Kas Naranja', 75, 12),
(3, 'Red Bull', 70, 3),
(4, 'San Miguel', 80, 6),
(5, 'Kit-Kat', 100, 4),
(6, 'Kinder Bueno', 95, 0),
(7, ' Patatas Lays', 100, 4),
(8, 'Agua Fontvella', 65, 5),
(9, 'Frutos Secos', 85, 20),
(10, 'Agua Solan ', 65, 12),
(11, 'Patatas Matutano', 75, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;