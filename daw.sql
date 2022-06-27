/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50530
 Source Host           : localhost:3306
 Source Schema         : daw

 Target Server Type    : MySQL
 Target Server Version : 50530
 File Encoding         : 65001

 Date: 26/06/2022 17:27:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_areas
-- ----------------------------
DROP TABLE IF EXISTS `tb_areas`;
CREATE TABLE `tb_areas`  (
  `id_area` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_area`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_areas
-- ----------------------------
INSERT INTO `tb_areas` VALUES (4, 'Area de Sistemas');
INSERT INTO `tb_areas` VALUES (7, 'RR.HH');

-- ----------------------------
-- Table structure for tb_cargos
-- ----------------------------
DROP TABLE IF EXISTS `tb_cargos`;
CREATE TABLE `tb_cargos`  (
  `id_cargo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sueldo` double NULL DEFAULT NULL,
  `id_area` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cargo`) USING BTREE,
  UNIQUE INDEX `UK1x6lj1x0ma1yw41g99yreosxp`(`id_cargo`, `id_area`) USING BTREE,
  UNIQUE INDEX `UKny0k79kvgeuw6to2ub3odeod3`(`nombre`, `id_area`) USING BTREE,
  INDEX `FK62kscn4xukybp44jxyqo3p4im`(`id_area`) USING BTREE,
  CONSTRAINT `FK62kscn4xukybp44jxyqo3p4im` FOREIGN KEY (`id_area`) REFERENCES `tb_areas` (`id_area`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_cargos
-- ----------------------------
INSERT INTO `tb_cargos` VALUES (3, 'Asistente de RR.HH', 1500, 7);
INSERT INTO `tb_cargos` VALUES (4, 'Jefe de RR.HH', 3000, 7);
INSERT INTO `tb_cargos` VALUES (6, 'Programador de Sistemas', 2500, 4);

-- ----------------------------
-- Table structure for tb_contrato
-- ----------------------------
DROP TABLE IF EXISTS `tb_contrato`;
CREATE TABLE `tb_contrato`  (
  `id_contrato` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_fin` date NULL DEFAULT NULL,
  `fecha_inicio` date NULL DEFAULT NULL,
  `fecha_registro` date NULL DEFAULT NULL,
  `id_empleado` int(11) NULL DEFAULT NULL,
  `id_empresa` int(11) NULL DEFAULT NULL,
  `id_tipo_contrato` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_contrato`) USING BTREE,
  INDEX `FKaqrmmy41krilgytun8542qmhq`(`id_empleado`) USING BTREE,
  INDEX `FKsjh95yymovcooypngv7r8umo6`(`id_empresa`) USING BTREE,
  INDEX `FKcwcfkhw6sle89yaq6aurgnhjv`(`id_tipo_contrato`) USING BTREE,
  CONSTRAINT `FKaqrmmy41krilgytun8542qmhq` FOREIGN KEY (`id_empleado`) REFERENCES `tb_empleados` (`id_empleado`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKcwcfkhw6sle89yaq6aurgnhjv` FOREIGN KEY (`id_tipo_contrato`) REFERENCES `tb_tipo_contratos` (`id_tipo_contrato`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsjh95yymovcooypngv7r8umo6` FOREIGN KEY (`id_empresa`) REFERENCES `tb_empresas` (`id_empresa`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_contrato
-- ----------------------------

-- ----------------------------
-- Table structure for tb_empleado_vaciones
-- ----------------------------
DROP TABLE IF EXISTS `tb_empleado_vaciones`;
CREATE TABLE `tb_empleado_vaciones`  (
  `id_vacacion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFin` date NULL DEFAULT NULL,
  `fechaInicio` date NULL DEFAULT NULL,
  `id_empleado` int(11) NOT NULL,
  `fecha_fin` date NULL DEFAULT NULL,
  `fecha_inicio` date NULL DEFAULT NULL,
  PRIMARY KEY (`id_vacacion`) USING BTREE,
  UNIQUE INDEX `UKnvk0vfc8hekvynrobm6e93hlo`(`id_vacacion`, `id_empleado`) USING BTREE,
  INDEX `FKsml3pw53bx4klul1go74bsdb1`(`id_empleado`) USING BTREE,
  CONSTRAINT `FKsml3pw53bx4klul1go74bsdb1` FOREIGN KEY (`id_empleado`) REFERENCES `tb_empleados` (`id_empleado`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_empleado_vaciones
-- ----------------------------
INSERT INTO `tb_empleado_vaciones` VALUES (2, '2022-09-01', NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for tb_empleados
-- ----------------------------
DROP TABLE IF EXISTS `tb_empleados`;
CREATE TABLE `tb_empleados`  (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `ape_materno` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ape_paterno` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `celular` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `direccion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `estado_civil` enum('S','C','V','D') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `fecha_ingreso` date NULL DEFAULT NULL,
  `fecha_nacimiento` date NULL DEFAULT NULL,
  `hijos` int(1) NULL DEFAULT NULL,
  `nombres` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nro_documento` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sexo` enum('M','F') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `id_cargo` int(11) NULL DEFAULT NULL,
  `id_sistema_pension` int(11) NULL DEFAULT NULL,
  `id_tipo_documento` int(11) NULL DEFAULT NULL,
  `id_tipo_horario` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_empleado`) USING BTREE,
  INDEX `FK9hdmb7tiuct3psga84cdolx4c`(`id_cargo`) USING BTREE,
  INDEX `FKkauf5i7jb418hoo0ne8yw2261`(`id_sistema_pension`) USING BTREE,
  INDEX `FKb0n54xpdtptm5rjxxejfesmu`(`id_tipo_documento`) USING BTREE,
  INDEX `FKb25mlq3isoc3bsgsal684566r`(`id_tipo_horario`) USING BTREE,
  CONSTRAINT `FK9hdmb7tiuct3psga84cdolx4c` FOREIGN KEY (`id_cargo`) REFERENCES `tb_cargos` (`id_cargo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKb0n54xpdtptm5rjxxejfesmu` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tb_tipo_documentos` (`id_tipo_documento`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKb25mlq3isoc3bsgsal684566r` FOREIGN KEY (`id_tipo_horario`) REFERENCES `tb_tipo_horarios` (`id_tipo_horario`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkauf5i7jb418hoo0ne8yw2261` FOREIGN KEY (`id_sistema_pension`) REFERENCES `tb_sistema_pensiones` (`id_sistema_pension`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_empleados
-- ----------------------------
INSERT INTO `tb_empleados` VALUES (1, 'Rojas', 'Pasaca', '977568791', 'Mz G lote 2 Asociacion Universo', 'orlandopasaca@gmail.com', 'S', '2019-11-01', '1997-02-14', 0, 'Orlando', '71939783', 'M', 6, 1, 1, 1);
INSERT INTO `tb_empleados` VALUES (3, 'Rojas', 'Pasaca', '977568791', 'Mz G Lote 2 asociacion universo', 'orlandopasaca@gmail.com', 'S', '2022-06-01', '2001-09-11', NULL, 'Luis', '71939781', 'M', 4, 2, 1, 2);

-- ----------------------------
-- Table structure for tb_empresas
-- ----------------------------
DROP TABLE IF EXISTS `tb_empresas`;
CREATE TABLE `tb_empresas`  (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `razon_social` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ruc` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_empresa`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_empresas
-- ----------------------------

-- ----------------------------
-- Table structure for tb_marcacion
-- ----------------------------
DROP TABLE IF EXISTS `tb_marcacion`;
CREATE TABLE `tb_marcacion`  (
  `id_marcacion` int(11) NOT NULL AUTO_INCREMENT,
  `ctrl_tardanza` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `fecha` date NULL DEFAULT NULL,
  `hora_ingreso` time NULL DEFAULT NULL,
  `hora_salida` time NULL DEFAULT NULL,
  `observacion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `id_empleado` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_marcacion`) USING BTREE,
  INDEX `FKfrwo2tshd7ampd0psgxy53y1u`(`id_empleado`) USING BTREE,
  CONSTRAINT `FKfrwo2tshd7ampd0psgxy53y1u` FOREIGN KEY (`id_empleado`) REFERENCES `tb_empleados` (`id_empleado`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_marcacion
-- ----------------------------
INSERT INTO `tb_marcacion` VALUES (1, '*', '2022-05-30', '15:43:17', '15:43:21', 'PRUEBA', 1);
INSERT INTO `tb_marcacion` VALUES (2, '*', '2022-05-30', '15:43:19', '15:43:21', 'PRUEBA2', 1);
INSERT INTO `tb_marcacion` VALUES (3, '*', '2022-05-30', '15:43:18', '15:43:21', 'PRUEBA3', 1);

-- ----------------------------
-- Table structure for tb_sistema_pensiones
-- ----------------------------
DROP TABLE IF EXISTS `tb_sistema_pensiones`;
CREATE TABLE `tb_sistema_pensiones`  (
  `id_sistema_pension` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `porcentaje_descuento` double NULL DEFAULT NULL,
  PRIMARY KEY (`id_sistema_pension`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sistema_pensiones
-- ----------------------------
INSERT INTO `tb_sistema_pensiones` VALUES (1, 'AFP', 9);
INSERT INTO `tb_sistema_pensiones` VALUES (2, 'ONP', 10);

-- ----------------------------
-- Table structure for tb_tipo_contratos
-- ----------------------------
DROP TABLE IF EXISTS `tb_tipo_contratos`;
CREATE TABLE `tb_tipo_contratos`  (
  `id_tipo_contrato` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_contrato`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_tipo_contratos
-- ----------------------------

-- ----------------------------
-- Table structure for tb_tipo_documentos
-- ----------------------------
DROP TABLE IF EXISTS `tb_tipo_documentos`;
CREATE TABLE `tb_tipo_documentos`  (
  `id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `length` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_tipo_documentos
-- ----------------------------
INSERT INTO `tb_tipo_documentos` VALUES (1, 'DNI', 8);
INSERT INTO `tb_tipo_documentos` VALUES (2, 'RUC', 11);

-- ----------------------------
-- Table structure for tb_tipo_horarios
-- ----------------------------
DROP TABLE IF EXISTS `tb_tipo_horarios`;
CREATE TABLE `tb_tipo_horarios`  (
  `id_tipo_horario` int(11) NOT NULL AUTO_INCREMENT,
  `hora_ingreso` time NULL DEFAULT NULL,
  `hora_salida` time NULL DEFAULT NULL,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_horario`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_tipo_horarios
-- ----------------------------
INSERT INTO `tb_tipo_horarios` VALUES (1, '09:00:00', '18:00:00', 'FULL TIME');
INSERT INTO `tb_tipo_horarios` VALUES (2, '09:00:00', '13:00:00', 'PART TIME MAÑANA');
INSERT INTO `tb_tipo_horarios` VALUES (3, '14:00:00', '18:00:00', 'PART TIME TARDE');

-- ----------------------------
-- Table structure for tb_usuarios
-- ----------------------------
DROP TABLE IF EXISTS `tb_usuarios`;
CREATE TABLE `tb_usuarios`  (
  `id_usuario` int(11) NOT NULL,
  `clave` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `foto` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`) USING BTREE,
  CONSTRAINT `FK3piwia9v8hqj3mm2dkj1vgrb7` FOREIGN KEY (`id_usuario`) REFERENCES `tb_empleados` (`id_empleado`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_usuarios
-- ----------------------------
INSERT INTO `tb_usuarios` VALUES (1, '$2a$10$HhsWwtEvcIUWBkaRw/lNf.7YufJ3Ej20Ko9Iw8opXpqxmmWrqgbAq', 'orlando', 'https://res.cloudinary.com/hiddensoft/image/upload/v1654375301/1.jpg');
INSERT INTO `tb_usuarios` VALUES (3, '$2a$10$.B7CitwNeRxMaDpyTbBNFOI.RbYkyBVeDL6CZ3HM9C35grM7Z021.', 'luis', 'https://res.cloudinary.com/hiddensoft/image/upload/v1655200204/3.png');

SET FOREIGN_KEY_CHECKS = 1;
