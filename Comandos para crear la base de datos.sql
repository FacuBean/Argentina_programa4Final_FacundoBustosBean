Nombre de Schema: validador_correlativas

CREATE TABLE `alumnos` (
  `nombre` varchar(50) DEFAULT NULL,
  `legajo` int NOT NULL,
  `materias_aprobadas` json DEFAULT NULL,
  PRIMARY KEY (`legajo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `materias` (
  `nombre_materia` varchar(50) NOT NULL,
  `correlativas` json DEFAULT NULL,
  PRIMARY KEY (`nombre_materia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci