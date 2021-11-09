-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema trade_off
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `trade_off` ;

-- -----------------------------------------------------
-- Schema trade_off
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trade_off` DEFAULT CHARACTER SET utf8 ;
USE `trade_off` ;

-- -----------------------------------------------------
-- Table `trade_off`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trade_off`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_type` INT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `state` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `zipcode` INT NULL,
  `password` VARCHAR(255) NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trade_off`.`clothes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trade_off`.`clothes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` INT NULL,
  `name` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `price` INT NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clothes_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_clothes_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trade_off`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trade_off`.`current_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trade_off`.`current_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  `user_id` INT NOT NULL,
  `cloth_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_current_orders_users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_current_orders_clothes1_idx` (`cloth_id` ASC) VISIBLE,
  CONSTRAINT `fk_current_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trade_off`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_current_orders_clothes1`
    FOREIGN KEY (`cloth_id`)
    REFERENCES `trade_off`.`clothes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trade_off`.`past_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trade_off`.`past_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `updated_at` DATETIME NULL DEFAULT NOW(),
  `current_order_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_past_orders_current_orders1_idx` (`current_order_id` ASC) VISIBLE,
  INDEX `fk_past_orders_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_past_orders_current_orders1`
    FOREIGN KEY (`current_order_id`)
    REFERENCES `trade_off`.`current_orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_past_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trade_off`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
