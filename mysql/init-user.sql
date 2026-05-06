CREATE DATABASE IF NOT EXISTS mydatabase;
USE mydatabase;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_username` (`username`),
  UNIQUE KEY `uk_user_email` (`email`)
);

ALTER TABLE `user`
  MODIFY `id` INT NOT NULL AUTO_INCREMENT;

-- Usuario 1: admin
INSERT INTO `user` (`email`, `password`, `username`)
SELECT 'admin@local', 'password', 'admin'
FROM DUAL
WHERE NOT EXISTS (
  SELECT 1 FROM `user` WHERE `username` = 'admin'
);

-- Usuario 2: veterinario
INSERT INTO `user` (`email`, `password`, `username`)
SELECT 'veterinario@local', 'password', 'veterinario'
FROM DUAL
WHERE NOT EXISTS (
  SELECT 1 FROM `user` WHERE `username` = 'veterinario'
);

-- Usuario 3: asistente
INSERT INTO `user` (`email`, `password`, `username`)
SELECT 'asistente@local', 'password', 'asistente'
FROM DUAL
WHERE NOT EXISTS (
  SELECT 1 FROM `user` WHERE `username` = 'asistente'
);