-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-05-2016 a las 03:19:35
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel_inter`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huespedes`
--

CREATE TABLE `huespedes` (
  `codigo` smallint(6) NOT NULL,
  `cedula` varchar(15) DEFAULT NULL,
  `ciu_origen` varchar(45) DEFAULT NULL,
  `categoria` varchar(25) DEFAULT NULL,
  `estadia` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `huespedes`
--

INSERT INTO `huespedes` (`codigo`, `cedula`, `ciu_origen`, `categoria`, `estadia`) VALUES
(1, '1144062871', 'Cali', 'Premium', 7),
(2, '12345', 'Cali', 'Basica', 7),
(3, '98765', 'Bogota', 'Basica', 8),
(4, '546372', 'Medellin', 'Basica', 10),
(5, '123456789', 'Medellin', 'Economica', 3),
(6, '6667775432', 'Bogota', 'Basica', 7),
(7, '123987', 'Medellin', 'Premium', 2),
(8, '654765', 'Cali', 'Basica', 20),
(9, '987564', 'Bogota', 'Basica', 3),
(10, '123321123', 'Medellin', 'Economica', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `huespedes`
--
ALTER TABLE `huespedes`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `huespedes`
--
ALTER TABLE `huespedes`
  MODIFY `codigo` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
