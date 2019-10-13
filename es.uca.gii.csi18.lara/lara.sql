-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 30-11-2018 a las 11:00:49
-- Versión del servidor: 5.7.23
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `lara`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

DROP TABLE IF EXISTS `autor`;
CREATE TABLE IF NOT EXISTS `autor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`Id`, `Nombre`) VALUES
(1, 'Lara Croft'),
(2, 'Richard Croft'),
(3, 'Indiana Jones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reliquia`
--

DROP TABLE IF EXISTS `reliquia`;
CREATE TABLE IF NOT EXISTS `reliquia` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Autor` int(11) NOT NULL,
  `Peso` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Autor` (`Id_Autor`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `reliquia`
--

INSERT INTO `reliquia` (`Id`, `Id_Autor`, `Peso`, `Nombre`) VALUES
(1, 1, 20, 'Update'),
(3, 3, 30, 'Create'),
(4, 3, 30, 'Create'),
(5, 3, 1, 'pruebadesplegable'),
(6, 3, 4, 'prueba2'),
(7, 1, 30, 'Create'),
(8, 1, 50, 'Create'),
(15, 3, 2, 'pruebaA'),
(16, 1, 20, 'Update');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reliquia`
--
ALTER TABLE `reliquia`
  ADD CONSTRAINT `reliquia_ibfk_1` FOREIGN KEY (`Id_Autor`) REFERENCES `autor` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
