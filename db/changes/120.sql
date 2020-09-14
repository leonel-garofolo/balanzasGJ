CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tara_id` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_reports_tara_idx` (`tara_id`),
  CONSTRAINT `FK_reports_tara` FOREIGN KEY (`tara_id`) REFERENCES `taras` (`idtaras`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


