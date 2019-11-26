-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `academia` DEFAULT CHARACTER SET utf8 ;
USE `academia` ;

-- -----------------------------------------------------
-- Table `atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atividade` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `instrutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instrutor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rg` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  `nascimento` DATE NULL DEFAULT NULL,
  `titulacao` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `turma` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `horario` VARCHAR(50) NOT NULL,
  `duracao` FLOAT NOT NULL,
  `datainicio` DATE NOT NULL,
  `datafim` DATE NOT NULL,
  `instrutor_id` INT(11) NOT NULL,
  `atividade_id` INT(11) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_turma_instrutor1_idx` (`instrutor_id` ASC),
  INDEX `fk_turma_atividade1_idx` (`atividade_id` ASC),
  CONSTRAINT `fk_turma_atividade1`
    FOREIGN KEY (`atividade_id`)
    REFERENCES `atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_instrutor1`
    FOREIGN KEY (`instrutor_id`)
    REFERENCES `instrutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aluno` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NOT NULL,
  `endereco` VARCHAR(200) NULL DEFAULT NULL,
  `telefone` VARCHAR(20) NULL DEFAULT NULL,
  `nascimento` DATE NULL DEFAULT NULL,
  `altura` FLOAT NULL DEFAULT NULL,
  `peso` FLOAT NULL DEFAULT NULL,
  `turma_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aluno_turma1_idx` (`turma_id` ASC),
  CONSTRAINT `fk_aluno_turma1`
    FOREIGN KEY (`turma_id`)
    REFERENCES `turma` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chamada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chamada` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `matricula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matricula` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `turma_id` INT(11) NOT NULL,
  `aluno_id` INT(11) NOT NULL,
  `data_matricula` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  INDEX `fk_matricula_turma1_idx` (`turma_id` ASC),
  INDEX `fk_matricula_aluno1_idx` (`aluno_id` ASC),
  CONSTRAINT `fk_matricula_aluno1`
    FOREIGN KEY (`aluno_id`)
    REFERENCES `aluno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matricula_turma1`
    FOREIGN KEY (`turma_id`)
    REFERENCES `turma` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `item_chamada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `item_chamada` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `presente` TINYINT(1) NOT NULL DEFAULT 0,
  `id_matricula` INT(11) NOT NULL,
  `id_chamada` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `item_chamada_matricula_id_fk` (`id_matricula` ASC),
  INDEX `item_chamada_chamada_id_fk` (`id_chamada` ASC),
  CONSTRAINT `item_chamada_chamada_id_fk`
    FOREIGN KEY (`id_chamada`)
    REFERENCES `chamada` (`id`),
  CONSTRAINT `item_chamada_matricula_id_fk`
    FOREIGN KEY (`id_matricula`)
    REFERENCES `matricula` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telefone` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(20) NOT NULL,
  `tipo` VARCHAR(50) NULL DEFAULT NULL,
  `instrutor_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefone_instrutor_idx` (`instrutor_id` ASC),
  CONSTRAINT `fk_telefone_instrutor`
    FOREIGN KEY (`instrutor_id`)
    REFERENCES `instrutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;