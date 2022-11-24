-- MySQL Script generated by MySQL Workbench
-- Thu Nov 24 13:00:49 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema prueba_cuentas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema prueba_cuentas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prueba_cuentas` DEFAULT CHARACTER SET utf8 ;
USE `prueba_cuentas` ;

-- -----------------------------------------------------
-- Table `prueba_cuentas`.`genero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`genero` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`genero` (
  `pk_genero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`pk_genero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`persona` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`persona` (
  `pk_persona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `identificacion` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `fk_genero` INT NOT NULL,
  PRIMARY KEY (`pk_persona`),
  INDEX `fk_TPERSONA_CGENERO1_idx` (`fk_genero` ASC) VISIBLE,
  CONSTRAINT `fk_TPERSONA_CGENERO1`
    FOREIGN KEY (`fk_genero`)
    REFERENCES `prueba_cuentas`.`genero` (`pk_genero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`cliente` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`cliente` (
  `pk_cliente` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` VARCHAR(45) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL,
  `fk_persona` INT NOT NULL,
  PRIMARY KEY (`pk_cliente`),
  INDEX `fk_TCLIENTE_TPERSONA_idx` (`fk_persona` ASC) VISIBLE,
  CONSTRAINT `fk_TCLIENTE_TPERSONA`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `prueba_cuentas`.`persona` (`pk_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`tipo_cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`tipo_cuenta` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`tipo_cuenta` (
  `pk_tipo_cuenta` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_tipo_cuenta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`cuenta` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`cuenta` (
  `pk_cuenta` INT NOT NULL AUTO_INCREMENT,
  `numero_cuenta` VARCHAR(45) NOT NULL,
  `saldo_inicial` DOUBLE NOT NULL,
  `fk_cliente` INT NOT NULL,
  `fk_tipo_cuenta` INT NOT NULL,
  `estado` TINYINT NOT NULL,
  PRIMARY KEY (`pk_cuenta`),
  INDEX `fk_TCUENTA_TCLIENTE1_idx` (`fk_cliente` ASC) VISIBLE,
  INDEX `fk_TCUENTA_CTIPOCUENTA1_idx` (`fk_tipo_cuenta` ASC) VISIBLE,
  CONSTRAINT `fk_TCUENTA_TCLIENTE1`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `prueba_cuentas`.`cliente` (`pk_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TCUENTA_CTIPOCUENTA1`
    FOREIGN KEY (`fk_tipo_cuenta`)
    REFERENCES `prueba_cuentas`.`tipo_cuenta` (`pk_tipo_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`tipo_movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`tipo_movimiento` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`tipo_movimiento` (
  `pk_tipo_movimiento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_tipo_movimiento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba_cuentas`.`tmovimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba_cuentas`.`tmovimiento` ;

CREATE TABLE IF NOT EXISTS `prueba_cuentas`.`tmovimiento` (
  `pk_movimiento` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NOT NULL,
  `valor` DOUBLE NOT NULL,
  `saldo` DOUBLE NOT NULL,
  `fk_cuenta` INT NOT NULL,
  `fk_tipo_movimiento` INT NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pk_movimiento`),
  INDEX `fk_TMOVIMIENTO_TCUENTA1_idx` (`fk_cuenta` ASC) VISIBLE,
  INDEX `fk_TMOVIMIENTO_TTIPO_MOVIMIENTO1_idx` (`fk_tipo_movimiento` ASC) VISIBLE,
  CONSTRAINT `fk_TMOVIMIENTO_TCUENTA1`
    FOREIGN KEY (`fk_cuenta`)
    REFERENCES `prueba_cuentas`.`cuenta` (`pk_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TMOVIMIENTO_TTIPO_MOVIMIENTO1`
    FOREIGN KEY (`fk_tipo_movimiento`)
    REFERENCES `prueba_cuentas`.`tipo_movimiento` (`pk_tipo_movimiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;