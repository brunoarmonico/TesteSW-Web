-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`Livro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`Livro` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`Livro` (
  `isbn` VARCHAR(10) NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `autor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`Usuario` (
  `ra` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ra`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Emprestimo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteca`.`Emprestimo` ;

CREATE TABLE IF NOT EXISTS `biblioteca`.`Emprestimo` (
  `emprestimoNumero` INT NOT NULL,
  `dataEmprestimo` DATE NOT NULL,
  `dataDevolucao` DATE NOT NULL,
  `Livro_isbn` VARCHAR(10) NOT NULL,
  `Usuario_ra` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`emprestimoNumero`, `Livro_isbn`, `Usuario_ra`),
  INDEX `fk_Emprestimo_Livro_idx` (`Livro_isbn` ASC),
  INDEX `fk_Emprestimo_Usuario1_idx` (`Usuario_ra` ASC),
  CONSTRAINT `fk_Emprestimo_Livro`
    FOREIGN KEY (`Livro_isbn`)
    REFERENCES `biblioteca`.`Livro` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emprestimo_Usuario1`
    FOREIGN KEY (`Usuario_ra`)
    REFERENCES `biblioteca`.`Usuario` (`ra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
