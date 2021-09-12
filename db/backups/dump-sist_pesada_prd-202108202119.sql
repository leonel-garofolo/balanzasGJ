-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: sist_pesada_prd
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ata`
--

DROP TABLE IF EXISTS `ata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ata` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ata`
--

LOCK TABLES `ata` WRITE;
/*!40000 ALTER TABLE `ata` DISABLE KEYS */;
/*!40000 ALTER TABLE `ata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (4,'WE','20-31631073-8',NULL,NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunicaciones`
--

DROP TABLE IF EXISTS `comunicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunicaciones` (
  `idcomunicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idindicadores` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcomunicaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunicaciones`
--

LOCK TABLES `comunicaciones` WRITE;
/*!40000 ALTER TABLE `comunicaciones` DISABLE KEYS */;
INSERT INTO `comunicaciones` VALUES (1,'INDICADOR 1',10);
/*!40000 ALTER TABLE `comunicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejes`
--

DROP TABLE IF EXISTS `ejes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejes` (
  `idEjes` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nroEje` int(11) DEFAULT NULL,
  `peso_entrada` decimal(9,2) DEFAULT NULL,
  `peso_salida` decimal(9,2) DEFAULT NULL,
  `idTaras` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEjes`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejes`
--

LOCK TABLES `ejes` WRITE;
/*!40000 ALTER TABLE `ejes` DISABLE KEYS */;
INSERT INTO `ejes` VALUES (1,1,0.00,NULL,31),(2,1,12500.00,2000.00,32),(3,2,12500.00,3000.00,32),(4,3,25000.00,4000.00,32),(5,4,4500.00,5000.00,32),(6,1,15000.00,NULL,33),(7,2,25000.00,NULL,33),(8,3,25000.00,NULL,33),(9,1,10000.00,9.90,34),(10,2,15000.00,20.00,34),(11,3,35000.00,20.00,34),(12,1,10.00,0.10,35),(13,2,20.00,9.10,35),(14,3,20.10,19.90,35),(15,1,1000.00,20000.00,36),(16,2,2000.00,0.00,36),(17,1,15400.00,NULL,38),(18,1,16700.00,16720.00,41),(19,2,16700.00,16720.00,41),(20,3,16700.00,16720.00,41),(21,1,123.00,0.00,42),(22,2,222.00,13.00,42),(23,3,2223.00,1311.00,42);
/*!40000 ALTER TABLE `ejes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importadores_exportadores`
--

DROP TABLE IF EXISTS `importadores_exportadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `importadores_exportadores` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importadores_exportadores`
--

LOCK TABLES `importadores_exportadores` WRITE;
/*!40000 ALTER TABLE `importadores_exportadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `importadores_exportadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicadores`
--

DROP TABLE IF EXISTS `indicadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicadores` (
  `idindicadores` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `posicion_caracter_control` int(11) DEFAULT NULL,
  `longitud_caracter_control` int(11) DEFAULT NULL,
  `caracter_control` varchar(5) DEFAULT NULL,
  `posicion_inicio_dato` int(11) DEFAULT NULL,
  `longitud_dato` int(11) DEFAULT NULL,
  `puerto` int(11) DEFAULT NULL,
  `velocidad` int(11) DEFAULT NULL,
  `bits_de_datos` int(1) DEFAULT NULL,
  `paridad` varchar(1) DEFAULT NULL,
  `control_de_flujo` varchar(45) DEFAULT NULL,
  `bits_parada` varchar(5) DEFAULT NULL,
  `is_eje` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idindicadores`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicadores`
--

LOCK TABLES `indicadores` WRITE;
/*!40000 ALTER TABLE `indicadores` DISABLE KEYS */;
INSERT INTO `indicadores` VALUES (10,'QSAND',1,1,'',3,7,6,9600,8,'n','0 - comNone','1','');
/*!40000 ALTER TABLE `indicadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciones` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros_globales`
--

DROP TABLE IF EXISTS `parametros_globales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametros_globales` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `valueByte` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros_globales`
--

LOCK TABLES `parametros_globales` WRITE;
/*!40000 ALTER TABLE `parametros_globales` DISABLE KEYS */;
INSERT INTO `parametros_globales` VALUES ('ACTIVAR_DEBUG','false',NULL),('EMPRESA_AUTOMATICO','',NULL),('EMPRESA_BACKUP','C:\\SistemaDePesaje\\bk',NULL),('EMPRESA_DENOMINACION_BAL','Denomina1',NULL),('EMPRESA_DIR','BAIGORRIA 624',NULL),('EMPRESA_DIR_BAL','RUTA 20 KM 234',NULL),('EMPRESA_EMAIL','JLEONANGELI@CJBALANZAS.COM',NULL),('EMPRESA_EMAIL_BAL','JLEONANGELI@CJBALANZAS.COM',NULL),('EMPRESA_IMG','','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0·\0\0\0½\0\0\0ñõìù\0\0€\0IDATxÚì}|\\Õ±þJ«U/î6½<zI€ÐB½ƒ+¦„^mbcÀÔ@ ô¡šPŒ�ÁÆE¶%Ëê}¥]mï}WíûÏÌ¹wµ’åBÉÿ½¼çÕo~»Ú½{÷Þs¾3óÍœ9sØþØþø_ú0lo‚í�íàÞþØþØîí�í�íàÞþØþØîí�í�íàÞþØþØîí�íàþxôöö¢¿¿Ð{üÿÐ÷¶å³í�íàþû`Ðöõõ‰è�žžžAÿë~�¿ÖAŸ)ÛÛÁýßèÄm9~;°·ƒûÄc¨VÖÁ«kæá4{&x‡ÓÜÛÛÁýß¦¥uê1ØÃz8N®;]zÌptåç”í�ÿàÞV0d›�ÛÝÝ�¡Î¡õc™ooMsgX×âúû©TjÐïý;dûã9¸.Í7,Öp8ŸÏ‡®®.´µµ‰X­Vx½^D\"‘AçÐ®¿÷CÛ�‘í–áÿ0¸‡ÂÐƒ·¢¢ÿüç?ñôÓOcÎœ9¸ùæ›1}útLž<_|1~ÿûßã´ÓN9ûì³å½+¯¼3fÌÀm·Ý†Ç{¯¿þ:–,Y‚ææfþ¶\0\\·›“7x·ƒû¿ÜCé�Þà:7¦OÑ‡n¤zâèîMÑÿ}\"St”ÇãÁ·ß~‹§žz\n·Ür3.ºèwÜ1Øu×‰(++�Á`@V–99Ùòš%;[½Ç¯M&ãÀ{†l‘lƒãÆŒÅ¯>§žz*¦L™‚{ï½o¼ñÊËË�F‡„ûÒ×ÔÛÛM×ÛKï¥�HÄÐÛŸ’ûâçH2Œfs>\\ôª6‚ˆ’}©´ð÷þH__�\\\'¿Öô™ˆ´û–¥Ù®¹µG2™Ü„3¨ÜPniD6Ó	E5Ôq‡|ðîºë.\\vÙe8ôÐCQRR’¯.`mæ{÷Üsyäa‘§žz>Š¼=47Üp#¦N�ŽO<cÆŒI‡ËÎ;O�ßš:uªh÷O?ýN§{\0ä©n\rì)Ž‚dwL„ �)Ó¯Ä‡üÏ¿üw4ÁL@ïI¤÷c%¨[’Í}7óú ÐÄvpÿÈp]\"‘Ð´x�\0ƒ¯»;‰X,–qÐÐÐD |çž{öÝw_äåå¥˜››+@fŽQŠßüæ$¢#7 ?ýô“xá…¿Ë¹EKÉÀéM?ó{¬‘™�WVVâ“O>Á£�.ÀW\\†½÷Þs�Öçç²²2|ð¯èó+ipÌÅ_|·ÓÅæ\'}_Édñx4Ýùk*1jüHè/œOwÛ-Ú¼›^%z“rï?E¶ulö½	‘á€ß¿E�¾�–l–+ëÿ3 ‡6X(ÂâÅKpë­·áè£�Å¨Q¬Q³5Ja\Z¤±KGáÒË/Â«¯¿„\r×Ãëw¥©Ó‚D*Šž¾$\"± üK„åónêPÖ¬:êÏÐ¸v§~ü>®¿á\Z¹Ngòóóéu–\\K^^ößÿ\0\\pÞù¸ûÎ»ðÑ¢³³Sœê~V­Y‰ßÿálèûE¹øë#1C(D4Aª?‰n¦c[|›¶|[¾Ïî­§Ãí¤KoOZ!ü§€ùÿ;¸3ãÎLIôÿôñx<Ýp¬É-^|ñE\\zéå8üð#5@+1\Z�¤A³E[ëÚôÌ3OÇ»ï¿…öŽ–4 ¹câÉˆ€Zï¸h<”~Ÿßããìü^8MÊ`Åˆ§ü¤¹„ò·ß}^|Æ�›AYrE\Z_/.,Áž»ï…³Î8SèË-·Ü‚›nº	ÇsŒ\0›%7ßˆ¿Üþg¢\"DWúˆuGèwCr�[’­�7AçÙ’${¢[”8I¢—yß`�³\"`€P”íàVs?NÞÓ+¼õ•—^ÆI\'œHNÝø´“Çà)**Q \"�©´¦A(È¬Y3ÐØTKÜµ[:\'óÃñÊÿR~Íïñkã{äÿ@Ô\'ÀŠ�“H œ\n!ÜMÀî\r#’\n ÷Ê3w8ÝÜÙŠÇ_€½öÚƒ@�£q{#\r8SÚÕ¯Ù˜•£^g\\on.½g4àÖÛnððõðo‡AÄè:~ŠD¶Aø·6\'±žP\ZàºðàS\Z¼;­Á,0\rÓåÿ:çf�Í“-Cgù½•+¾Ç´«§bôÈQŠ<S¾f@s(¬­:è\0<ó·\'àöØ…¯û\".é \"ÈPÂ‡pÒ/ïE»ƒòÌ`\rÄ<é÷ø˜dTÞs„»êõ#Òã‡/æD0éA¼ŸÀžôÂ°É±ýZ4ç�÷ÞÄ‘G>ˆóçd›Ò×ËÀæ×:ÐssóÅÚð±FS6núó\r¢Myàñ5ò`âóÿ;…ï]Í¿©Kú=\ZÔ‘¢I½!xqÑè1¹g¸ðpMAé,e;¸;“Ü@z´Äf³áùçŸÇá‡ý:­ñ\nò\nå5F×Ü\nÔ\n §þî|»|©˜SÑZqÕ1þ¸®��€éƒ\'â”g~�Ås#�ðŠèŸgïO¹D\\Ñ.8#Vø’NúŽî°•¾ã¦Áà‡7ì”ßc>þù—‹pö9g	×µsƒW×Ø$&c®ˆhuíÚ\nòpÕÔÉˆ’¶fÀù£N‘@ÌµE	ÆÝ[ÜµEá«¿æûÉy?åA ›Ú¦G]ÀÞ�Çº£\ZUI\rÄí{ÿ�{¸$¤ÌÉ~(n=pì¢E‹ˆW_ŠÑ£G#7Ë”æ­™1g“)Ox¶h=cÎ9÷÷X]þ½hQ·Ï.€c-Ì�Äè�:¸vyÎþlKâIÚáŒYáŠwQG;EÜ±.x¢Vê|‡ˆÕÕ&ÚÎr#LT§ªº\'ž|œD@t\'—éJN¶¢\'¹9yéAÊ÷\"\Z>\'[¸;kkOÈ¦]_¯m‹ÂÇlI<‘®-ÊÖÎï‰Ó5$i�‡R,þ4¥á�êÑ£:=›\0üòdÑO÷ÐD¥Ì÷35µ»~øá‡qØa‡¥AaÌ\0´®õ˜Ï*Ç-Gœ±S÷4hüš©B¬\' ZÏ&\r³¥µ®#l‘ç$±NØ#f8¢p\'¬\"NzíwÒy­ðÐC)7iú.ù=OÈ!\\yõú•8æÄ£`ÊÍB^ži ÎnPC…ùEijÅƒ”9÷Î;K¤3hË à¢ßø)Â÷¼%ÙúýóÀ¶	ÀÅj%ÝBÍàâ°Sªœ#[’ßÃ\0gŠÒ¿Ù˜ø¶ËÿppëÓÐ|ã™é¦™ÉHüÙí·ßŽâââ´£eÐÌ9Z7ë¹9¦4ðØG{6ÔV’“­Ç\Z��ÁZ©ËÛ&Z–AèŒX�,üZÿßêØ²°»Bm°…Ûà,üÚFï9è3k ¶ ™Àm!+AZ=lƒÝošóÙ’OpÈaÊufç(ÇQ€¬\rV¾§LpŸþûÓÄ/pÕ5òµóoüÙêýmõþ©Í¢iCV¬ÉÙ¢±EdNÎ×Ë$ŽÜHè´[�<3þ¿ÜÃqë¡™wœûqÝu×)À\nÍ0\nÀ9nl2æhœ5[žÓÎcNŽ9îh|»r™hlg�AÕ)ZÚ›°8ìBÖ¼H)ƒ’Ÿu�nƒ0¸»Â-°GÛDø5‹-Ò*¿Á@g ZýmÒù¶`,î61áoýó5üòWû	Ea\03…RtŠùö\0¸ü\'�z‚|ÇÒ _w¸õ\'É6ÝÛDo³L¥ èÐ\0ÀÅùe€wk!V¹Lüô%€ÿpîÌg=2Â ·Ûí˜9sfZë–.y¦Ü4¸™†H,;ß„ýØK—/`»ÃŠO³ÙäNà±[áŒ›Ó�ÄÿiÝ­u®dÌ,Cßãß1{Õ !m×h€²¦ÄxùÍ¿cÇIcÏÒ§îsB˜Æl÷‘Ç.€±útêÚ­¡æ-JæµüÙêùé\Zx\0³dZ>�ï³Ó\ZJxT*Hƒ<¥ÅÆ‡Nßÿ¯¥%zx/3Á‰3óÒüYÓÚ`îx¦!YÀgÍ—W�‹^y^€móvI˜Îâkêïq ÉQ….�Eëüá:ÍlBg Qž·$ÃwzcZÌ¾Z9®ÍS›y›»>~˜hQ«§÷=tF¥½3g3e0³VÏ5àà#~IVÇ…N¿\ZŒ–I°á\'ÉÖÀ»UpÓuØ2\0Îƒ×E\0wÀu‡T�Úè �§‚pr6{ÂƒR\0~ÀÿhÉP�ÙétbáÂ…éˆÇp¢^Í8j!³¢|œáyðÝð„Ý¢µí!åüXƒÊÌÚˆ:¸»;Ð¬‡•ÁMÂ@gÉ|ÝÉ\0ßŠl\nøMd��c-hrnD\r_¯í>¾¿‰ÀÝ&\0_·q9âÈÊQ1m!!K”cLƒ{ÿCö#Zã$pkÀþÀ½ÕûÓÚbsÂ>…p]ø~œrq|Ã]é°%ƒœç\0˜‹³s¬œÌÔ&\Z|ÛAþ@KôUæ*y()©¢EEEƒâÔ:˜uîmÈäØ2µžƒÝ÷Ü\r–6xC·7JšÎË VŽžæv?iÔpãf;•Ahöo½ó‡j{¥\r4·-Ò,¿e�hÇÓo·yë´�D\ZØÛ{ UÎ7Þ{	¦|âÜ&EOd`kÓïý^ìI>ƒ]´~g°…®‘³~‹b\r5lQ¶e\0oñþMîæAàæû±3õ\"a€ó½\rpR©Ä&\0ÏLµý�7‡…$ÞÙ«âžÝÉ|úñ\"Œ\Z5J¸g6›êl%€f\'’¹v¾P’ü<É-0à¥7ŸA°×	¯M\0ÊôƒÅ``±¦©�/@Z•´§Ó‹8}¬�t v†êEÒ`¡ïðç|œÍGè¯‡- Þ*ÔK£-ô>®¤AÀÀ\\Þh£cˆ³“·zàŠ©ÈÌœÝ‡ðïl£Ü«)§@œË�vÚÑ„_~·Ã[/÷`ñk\"×Ú Ö¨#X—¾fkúšµßÖ®Ñ¢NÒ®–ïñõXý\"r}¡Zú¬íÑJ´FkÑÊ\n�áö …èZ»ÒütÌæx¾-¤ÄmOGftªÂ“?*\\¨48S”Tw}äh‚S+DÊ	ýi³ìëHœ\0Ï0òó€ÿ§Ó}¦J»–úÚì¶ÛéÔÔ4°³ƒÛ`0	¸9Z\"Z�´ÝE—ž‹»Þdš‰xº­šùl\07;‹ÂY\Zá íçð·jÚ¦Uœ=ýÖìJ4‡Íp—¦™<@ä½Fõ�Ð\0eP¿£>³õãšør¨�”Ò~�¾&qÆ>ÿnÊvC.Y+¦$Y&äó%v¿Ãã„³2¸Ð]aõ]�J…Èùºüúï«ûøíŽp+Ìaø5j 3etŠÈý‡ÉºD6 #ºæh�ÛAN0_·|‡ŽÙœÏ¡ÿ¦#Ò–·pq->¯ÏxêŽf2Aw*ŠþTýd¹‘êQàîÕ€Ý7\0nM-j²%`ÿO7I2žÂÌ™×¤óAòósÕ4uöP¾­ÀÍ¢S”ýÜêÊI#¸PbíÒÐlºÔ¶`{:¢w¶â¼JÄ¬Íé(ˆP¢ÖI�‰%m‚éXG¨I„µ”D8‚V¢\"üY¦hQ9Æ¬Âq!ÍZøZe¦ïÆ;oçÒ�MtËh­Íà5f¤ðTŽÏ«ó4©�Æ¢Eo2}‡L\rª_³~}�ºix¹~ºiå.>&B\Z?ZM\0ßH¥Nã÷�\"ÒŽ\Z¸7u¬ªtÈ‘ÎéÔ�M\rà*\\¨rWà	r0Qô&cðþî™ìI/d�=;ÿFÊbø¡üz¸5Žúû\'Ÿ,\"@¢  hXRåb�›ß/,.À=÷ß�nDÉÌk`Ñ@ÌN›Ñ6Ô)b~™VhŽP\ZÔ™ÀNTwS\ZØ€\0·mpg†	3Á�4ú\0Ðœ1ž¾çkkw·¢¶½#w)šÛ˜“«Å»Uî9ƒ›#?b”ëÒ¾¯®Û,†ï•_s4F®Qdp“£ë&êæ¤Áït¤Á-×FÚJtÄ\ZÞHßiÐ¬•YD·ep7·>¸t’	p™â�¹%WG\0\nÀSÉúHƒ÷j+�ÄÑ”µGSõšB¼ªg�½�Y‰†ŸlýB9…•W•~øái­½9`‹¨Ày²f×ÝwA]ËF‰eÛˆû»íhu×H\\™5·Š’˜UçÓç¬!»|ÍŠcÛ‡HF$B�\n„õëÊ°\0bòCÍƒcåÃ…	™z²2|,‘C•fÏÓ�ko¾VxwN®IâÜLËJÊ\n%1IÀMÎ±„2íJ´û³•ˆì0Úýf¡P]züž-Z¸frv=Äù]ÔÏ¦*ÍÌ”¥#¬4sW¨F¨S7‡¿]ÎÅçQt­nÀRö\0¸›4po\np\'9™ƒîG\"ŠÒÝ“‰Î\rWàÖÍ^�£ô»xmøÎ?\\»ÿ pon%:çŒ<øàƒéø®þ+,,\04	OI+1¥%§ ×Þ4Éþ°h‹¿YœÇ©Ð´Îg>Í#;ƒ~å@²–�Ö!’òÊ”Íx˜‰ ô„Ð�óé\"€d�íi\"+CþBÊ‰Vg+¾Y³ŒîÍ(aA	¸‹GI&¢…ïƒ´+”ïE·U6ƒ”…51ÏÊn3ifŸ·îpwÐyÌ4pø3ö5ØWp¿çcÜÞv¸îŽHCFÛn�{ÛÙ·Ñ\0îµ‹¸˜–EäL»Ò\0�ÄEÑãà<Ù“êOÈò:^$\"Zœƒp¦,CÍ¡àV¢9ŸÃÉ¿ÜÉd7š›[%ÃOùé4útvVn UÎgÑíº÷Î¨o­R¹\">ìN_Ú½54AfÛÒ@Ð�G»„ÒÌÃ€{@³n	°C�©Å»[�_iÄV%Äí*ÑA×Óâ&óÏ&;îD‹­§žyŠho¶Nî²1¥\0ÖÊü¦œÛœ8ƒ”éÅPpó=ëŽ²™€Éâó(Ðò1Üf:o[¤mQuÓ.79¬\Zxj?g@ñýN¢DÑÆÍÎj¦­Y`Àéà6ê7;ñoWLe`r¨�\'{8LÈ	nœÄ¹óÝÐV÷0Map‹d€;äÛîn%ÿpë\0O$R˜7ïa5•ž7�¦ª–ƒeÃ˜Mïmîlä³`ÊÉÆŒ®”„$î<7u\n‡¼Ü_îÔ«Æf�i\Z[çÙª‘[DSu0ðÒàÎÐÔÁÖºbN¿<ZÓƒ@×PJZ5€·hÒ¿3Üí4 í1‹¶ÍK`JØa&îýÆ»¯iIU\nÜ#Æ–Áñ¸¬ê|œ´¥Å˜uÎ­œ)	\'X1hYÛê¡¡àö°]>EmøúØ­Q~V‘�ì´€;¨\0ÜiA{¬iXpë<›¯MUê\0WNü\0Eap³8\"*;“§ì¹?ÃI7Q0ŸÌ.ÇúÔŠ\'^?*àæ*¢½û±µ…=™àîÿ¹Á­;Œ™%ô)v~ni6cìØñ›Ì8ªP`¶hmæØù¦b·hl£JðßkÏI¨¨[…vG=<QæpmŠç…•0È9jÂÑšä?HHªM\0ÈqgI‹ã¾v:–g/ÙAòv[ÑéW|Ýµ\n X#ºxÂ®ˆÆycšFççóºÃ\n\0�¤…=q›\Z!rÔbô:n@¶S»’¤]I;êš›ŸYXÃK¦Q¬6kvßk¢\0›§åKFHíÂS9$×R!Aw¼#m¥8L…õÚ…ÓÚ¼­§ÇwÒ tÄZ%ÞIÀìH4#¶É1œÒNàg`w$é5�³CRy-ètÖ#L÷âð6\n099Ì\Z#Z”h4y“)ºEá6×�^³§A’½dÐ´ ¹>�’6Ë93án�JIºJy$ƒ%Ñ—2¬½»S	¦ZÕ34ánè¢E}Uµ}šŸÿ×+&MÒûQš[O[ÕM›#$÷Þ7¦Ü|	{q±}¥¸ž—%ñl%Ù•G’C]Zš‹³Î:�¤Cu\"iž±ø©ãxö‘ží¤;}\rhnT=JÂï±¸ãínGÒ&ZJ4±žsAZ†¿ÃÚ¤Ëo¥sÙáMzaö1Ÿï�ã¹ƒnî(i3×GŸ	Èy\n:JZØGÊÓæp;�‡\'˜XƒvE:DlQ5`º´TYþn§£3®™‚üÅ¹KG—HídÂ=°’ólák6Ê}Ç%€sþJz…y-Ý4ËDOy�Ê¡Œ²æ% j”„TOŸC&kÜw‡€/Ðí‚ÙY§€VÉa­þF¿\n!/<ÀÚ]�òš�x4ý;Ñ-ÎZ™,bGšÓ	ôZv¤Ç+©ÉÒØ^øÐEˆøw´G-zîçbDÝ)Fñ d»ÍUåÍ¬«¨£§Rÿ$pgfûeþ8_/Û}�}%ò¡¢êƒÌJ*î�£>ËÊp«åcªpÎÈ2#žyj™,¿$Ý«èG“t\0›5šxéÔ>\Z\0ÜÐfw“Z%“°jf²!MI¬Œ6™¨	ôZ@f	+ò\"ƒNÑ† “Ž·Ñ1.8IrÇv’–cÍÍÀäÁb÷“óå#+B×\"-l÷µÉt¹5h§ßp�Æ¦sEÈ\"ÇnT¿ÇNU¬C€Í³š™ôàpròV|¿DÚD¦ãMÒ¢œ¶ëE‹‡DÂ#ÑW¬Mw“hf™œ¢÷�t>�ÉEˆ›8pœÊJ×ÖI·=j–5 ¾vÖª	²LëˆËÛâ]¤á­2˜�Ôf*û¯U;êl]XÛJnùQaE%¼à�5³™W¶mîF\rÔíÊúi´%@\n†44æÞî¨‹”…›úË‡p_ŒXmé\ZOÖp,ýƒ\'mz{”V×µsæƒ#rŒÄTO÷À¼a?~:¸3ãÙ™y$ï½÷žšiÌ&ê‘­VÍð„\rÏ6êàÎÒÀm2æ«…	ÔÑ9tÌãòà°WÃÊ�ìS#ž1à,*žÝI&—s¨�¤5%NË)§!¥©ThN5²JOm\"-RM`®&m]/ÓÅ¼°Àágëà£óqÒRikÅwu^Êßg+$Zã¥Žr¸jˆË’së%ÓífÓîA³‡AM¾qÉ6ÒTL˜6ñƒ›ç²°¯À bŽìeÎìlÃÁ¿ÜŸ´wFŒ*CC�9ê§Áá”#<?:\0nþ>O¹;Ã\nÜ�ö*º®N	÷IÄ„CƒäÀµÅê$ç�CÂÕµ	\'G¤Eh;µ�>^eãE“£Q\rà�²jbÑˆj™m¤ü–!î[ K¦óí4€$ÄhÑhµ_\'Ï;éÚ8Åæ³�Æ’R\nRßáM‘\'PG¸VL�—8‡\nÁeçzâ„Ðn‘¾Þ¤Æ£û Rðáå©>™øùY4w¦9Ð)É9çœ£fsU|[K’|-q(KòHtg’¨»0ß€3Ï8‚Îæ–º!Rz¡/\"ÞµrB¼²=…¨˜6Gˆ4§›£nE¼Ê:]hvÕËÒ0ÎÜë\nÕQ£ÇÖI£\'úÕ*ø85š/A„\ZÎF”€ó+:Hcw&Wçü‰*¨|ØTÒŠ(i¶~úý8i]Ÿ»\'3iÖÞ¬\r]q‹ÒZÌƒãf•ÿB0ƒ‹é\róö\0i¼ ÿÂO‰Å*YJ€²\"Ø“ °`%�³©—…Å§Y“[utâõô:B#ÑëD4é’Dœ3w\"H\'€·�œEÚ�§ÕS/\røù|,Aˆ~+q_¦_ì“°ULô{ÑÝB—|#º $ª­z�\"Ö«„éƒ”ZþÞ€Ü·…Ú¥•øù$L-ÎJØ=µbéøwûù\n{cäPF¥½}¤‰=‰ID\0ž¤?^lÝKíÚÛ&€%	àQô$#®„\0¾;£kKB/­\'uÉÈùÔA‰÷ˆôüTpgr�ÌâîÕÕÕÄ›K	ÔÄ·³TdD¢Z	kpÉÝf\'Ò�\' ç)#×)5à‰\'nÇâÅ/aÆìk0eÚT\\9m\n¦Îº\ZÓ®¹\ZSf^‰«¯¹\n×Ü8w=t¾Y½œÌ£—´€Ÿ4OTX :Ó¶ªU$¬=ˆZ¼‰ÚlpõÍ;˜uÝtÎ«0óÚÙ˜<sn{à~Ò®âÚ.´°)�wˆsÈàöÅð’Vóßï²¬Ã=wÏÂ´—`êì«qÝí· ÎF!èíí%§‰­\nS(v`Å‘�4Ë?_‡›š—�Ÿ¬LSSfMŸLŽµA²$/¾ü2L½îZ¬ª®¢AÓÏ�ïÃ•è�òŒ¢‡€Î³�ËW‚Ù×_N÷2YîeÆŸ®Ã¼§Ÿ&g6\ngw/qçv¸SdÙè{Ñ¤YgCíJÜtÓ5¸jÆL¿q6¾­Z!–ÊÙmA]g9n»ó\Z\\7ó*\\{ÕÌ g–é3¯Q}0•î{*®ºv*&S?Ì¸y–U|7)YQ¦f]4 ZÈOrûªÉÚÕ£±y¥ºÎÙWbúìi˜uÃu¸öÖ[PÛÑA£—S^xŒ\'\'[õCwkp¢(}ì|n>[ô!î¿ï.Ü@ç¸þúëqÃŸnÂ¼ù�âË¯¿…ÕîP³~Oöáçq(õèŽåüùóµÈHžšFÏRyËÙZº§žòÉaÀœì<9ƒ;‡ÞŸ4±+V¼‹)W�LÇ1�ÉVÂ3y¦,-/C¥‰æ“¶;ô˜ÿÂŒ›®Ç×«–#ØšÐN gNÉKÌ8ÃÎ%M%sª…+Pƒënºlà<|^¢H…cÆâ³ßÀÝDiU·³»K¸2kJ�¿>¢4•å‹°óŽ*/Æ@÷S¶Óx,¯Þ€6Ÿ›4 Qœ�œ¬G¨Û&Z[Q\"¦ÌKá‰(p{ˆR}ôÁ«Q’+tLÚ‹ï“ü“Y·ÜL¿ëºe“ˆC‡XÖüŽ�:‡Í]‹[ïœ¡Ýƒži˜ƒ‰ûˆ\'_}‡hMŠªMq~o-¢ôû^¢1K?#µÈ}oî3Ãž²‘•jBEÓw(.¡v¥ÏŠ2Û$ÿG£&Ü—YÒ?û(tß\r.ÎHì„•ü /ÑÇ:Qÿxá!¢^†´r3Ð}æaÎÂ\'È7IP›pI#‡É\"pì»›,rè9ÂµÉÑ$~þÑû¯aê”K°Ç®;¥}3=¤œ�[€~y.¸t\nêš;È²€¬êÏ\0îÌ0 kn‡Ã!UQÕ²°Bj˜¼4ç6æj¥V×C•7p&èóC5ßã¤“÷��aÌ+„© X¹žÿœ­Ü¨ÀIrÚ¹Àwå¤½°Fœ\"<£ÉÎ#ÂoB Þ€•ë>Â®{”’“kH\'0r¬EÅ¸ò†k ÔÑœBîHu¢ÅUEÞ}+¢‰V|µø~Ù»2M&õ}cY–V¬‡%ÌT‚~;¤ÖSr\\ž£LGØ‘epÛÂõÄï$êâpÖã†ë¦ˆµ2éùÝ¼\0šÓ_÷ÙÕ*\Z#Óæµ@Yø:S$Ž¥Û6à”ßŽüÂÀQ[rJpÀQ¿E£3&Z”S˜Â„è:\"žz|õÁËÈã¹“¹Å…¸mÞÝ°%»$¶]Û¾\n£FØ¥éÕú©Y¿Å’	ÑÈ{<@œ�#<vr„¨q-éõÀé\\OR‰ÓNýòò¸y™ �\'ôò\npÈ1\'Pû’c™L\"Ô—‚Ÿx¸TöJpMÇ°\0¤Éß|õo8p¿]U[±ÕÏL¶cÅÉJ�pfÈÎÇ—KVµú™À=´ŒÃÆ�Ó³�9é†ÉÖ´°Æ­9ä\'�ª&rä3j°¢b¦]q|íÿÂyÇ•ÑqÔÆ\"ÆLÀ¥·Ü„;¾�>vŸsf^|FÐçãòJ…ÖòJpþõ7£%\Z’Ù;îLvd¸,Ïäy]�HÚ+ñéKsQÈ¼Ÿ5		¯ŽÉ‘°äHLÚï$T9ÃhNt¢9¾\n�q›ÐO¸Ñ`º}ë°~ñkGÀ.æ{É.Eé{biU:È´šÙ‰“UA*.Þ!\ZCç2½pqA âØž®jôÐ€«Þ¸;ï:\nÙyFq®Kó‹å:ì\\�Û/|±\\h…-F£‹¸8q}_¸œÍ÷ô4 ½­g}&PGç–�®«(wÞ}}5jûhãòÄµc¡zxkñÅ\'Wà¦ãLe;áºyO¡.†§×\r«eÞ}ã!<ððM¸óÉ{ðÈßžÅÜGïÃƒÏÂSÏ\\�GæÍÆé\'�âœ‘Ô§#L£�U:o}¹íá ÚC\Zä6q€]‰Zô9—¢bù( v6¨4·I0A÷;b_|RNƒ—€háéù>’äïô“¯)gCíÚü-öÞmær=ÆÑtŽÑ¸ô²+1Á}xò©1ï¡;pQœS~{}^„oVV€· ¹ÅM	?”’°#ùî»ï¦g\"·î<cŽ7inÖe#²ñÌc7ÁÛöþpt1Gœ=gò\'íƒwW|KL,ŽÞ~z¹4CãFÌ¼è\"1¡\\�Ê�_Š²}‰õ]*Vb€qt€(‚Ÿœ™ §œ f]x2rùšH“L:pWwâá( ë-È‹¢qàÙ�¾‚~´¥ª`ŽZ…3{	¤aÿ:$=ëP±ô\rŒ\'­_ÈµT²JÜK6Ô°#náœœ	Hàn	n€½Û,ŽG2¼ž&Ä|õ�6{ó�…dž•6Ë#`_EÆ×•—_B\Zq4N»êrTCh\ryá g‹Ïç\n”“o±Ž´ù:²nkpîñ\nÜEÔ†9Åe(?Z\0”g…Žº5ä6‘%ñ­‰“Åð9Vã»Å¯¨ÁÀŠ£d\"nzìy4Ðµs3™hBwÁ‚œsjƒ`œ§Æ¹º©T‚Þ\Züö¸#Iƒ�2 `S›õ»³QÑÙ!àn\r¸Èzu‰•ê öbp?=6LyJÛ”•à²K/ �Å‹¾É\ZçOÄŒ»ŸAù‡vºVYÊ$9òÝQmz3¹ï¼ü€ê/\ZÙ9;á¿Ž;�f‡ä£ôŒ£%¬«ª\'çüMXÝA„8§	øùÀ­/úå2\rúläÖÀ�›­ejà7¾\0×~Wó\'8ã×¹tS4º\' `—}ñÊ—_’6pŠƒtnDÊgÆÚ¥ÿB	‡�L-ò�·ë>ø¢ªŽ¸i\0¡i¬87r�ð_yð\r«>ÇÞc\nÅ¼ŠM˜|ãÕxþõÇabzÁþAîŽ8å²Yh!SØœjîíˆwÂO\\9è[‹„{-*¿y;ä1/Íp—Œß_WÖ\n¸Ûãàæ<˜¶P%i¤fÉ­æ°^ Ð@>RÜ�ßã¼s�Uæ�É½ö?«–¯À¤±c¤ž !§cö?kZšÐLÜÛõH\\?¯%N<¶›¬’}\rÎ8rŒãv$°å—�Çåäló`É7•’ì‰¿/]J%\"ùÕ‘@-Ñ’Õøêã§¸³ËÜ{ãÆG^D+Ã3·>o%‚ä“0•k$kŽøárÔ°7àŽ›§ Pvœ( Z7–ü”]ðÆçŸË\0´$Hs‡éþ%’\'×*l[„ß=Q¬#÷ïáÇƒ/¾ø%¦l	Œã°Ó/OE%Q¨ŽD\0¾ž.$Hâ‘.ô‘ÅBh9¦žw(Š�<ÃM–Â0.|Òô/á(Ž¨xŽ�s²U¿ZÒìé£¡‰ŸWsófI‡rHº°Î7™Ô½÷Ù‰`-iå�púa&ê4¢9ãQ4ñ\0üý“Odª›¹¯ÏQA\Z°õë—cÇŠ–ŽDÞî¿À·-]¨éì$�œ©5r‚8g¤Në¼ñÔ|¢2Yd-òa\Z;/~öÖ5.AY©Æ-sÇaôÞ¿Æ:§-ÝN´ñ,!9–dÐ·1wÅÒ·DsçKi†\"iàn#€´Åœ¢íyV“W©tÆª©Ãy:¼U\"%ÑP5\r�Õè¨ù;ŒSÎ˜aÔ8\\<u:<n;.:û·\nx94\0Çî�ùÿxvj^Ö¾N$›`!jäKÕQ[/ÃŽÙè~ŠcP4r}û9ŠF«ú(¹9;ãˆó\'Óõ¤ÐEmðW#êYŽ½?�L¼A²aÔ!˜ý× =�\'aF ¸Q\"’ž\'kçíDÈC×íoDùò�°ÛeÔ\'9bþ\rÙÅøã”©èJEÑ´S[¹ÑuÊ½ÛCeÖ,ûF©Ð†ü\\Üõ×ûa·7ã×îŽB©V\nCÙžxsÉZ4†|ðô{ìîD$jA_ÁÓ»SÏ9�¬$;�tláDœ~É�ð1€õõ9¼|­Ÿµ|\\rR8-%Jmû©àÎœúä…¿uuu’Öš^Á¾­œ›ÀmÌÏÁñ\'üŠˆ6÷™‡ç€ÈDg�FÙnâÝo–É…ÇWG¡q2óŸð†€AiîBŒÿÕÑhO�\0‡ÝgpÙ5°ù7ÀiY�‹O;evrÇ`§ƒCµ»5’ÓÏ>�h	u‚‰´CÙnxòƒÏÐÔÍ4ƒœÒ¯ô®G8P‰i¶u_¿�ÑÄ¹„–¸wØ\r_“µ`p·ÐñíD™:´il{¼E€Í¼›\'^‚ÞrD:¿Æ/Þ‰b¦$¤Ác\'à~7é•gÄH“¶H:o~sþt¤úÑšô¢��P2÷î—¥X§}).<Iiî±¹{¡dÌ>¨ê¨Á¹—¯\"SYÄ‹Gî‰w¿.\'pûÈ’5 ì^‚_<¡ú%gFÿ®›ÿ2iÍ�Ì¸ü•ðÅÚ$6ÞìD˜ü”€£®Ž\r¸â¼ÓQÀ\nn?cö8èWXV])ÏÒçG£ŸsÅ-âôú¢5°{VàÙg?§k!`FŒÀç+–H|ÿÞÛ¯FiŽª(fÈ‡+þ|?Úqtuûè·-\'¬ˆ“¯„ÐJÜ;ûtë)ùdÙÆí‡{¾LýÒ\'Ú;ÞÝ£’­ú•æf8ò{ÑÞþŸnýÁ|û‹/¾(…F³5pÅ7Ég¦Â\\rÎFŠ4�­þwžh	2�cwÁ;K¾– ª×…iÇµ+¾Â™¿9QøG[e¸û¹WÑï&oÝ*Ùw�ìÎÐzxB¨«ü»È€É-ÜçL½¤ÏÐâ[‰¿½p³D@²²ÈéÉß	GŸ{%iî>¡œƒÁyÏÁ@•€»|ñëES(º÷â\rµhM„ÜmQmò‡4-\'e±ƒÅ�n¥A™m@Ì²—ýn?2-,Bá~¡Ân‘©ôÚò÷1q„Ö6ÄÃGì~$–5[Ñ\' ªf:íñ&¢Jàr.ÁùÇî&à.1ì†’Ñ¿@£³ó<\nËÊ\Z\ZwÆ)çÏ„3ž\"à6�U\\Že‹æ«~Éß�~ÿP\\ûÐ‹¤}ƒä8S»«…’pè‘gw#Ž\rˆ»êñé[ÏcÂˆRrÄ‹¨¿Š‘•?\nw=òºz¨&Ú–²ÊÄ—¬åûL6Âaû\'´Q\nj\'Òº“>u]mðýùîë0šî¿@Â‹ùDMŽBM€”C< ³¼¡nÂQ;y…•Xõù?0ªPaE\"fÔæ9#vÆùS®Ã½óŸ‚ÙH¯°ŒÅ“É|©ŸîÌ/ñ–óæÍKg\0n+¸eÕ\r}–_R€[n�‰$iÜÅâl‘³HÎÒÉœ‡«o˜†i3.ÆÌi—áäã�“èB>q·]wÙ÷Ì{•?ê£Q4GÆ³Ä8‡º.Ï÷xrÞuâÊ3ŒÄèÇ3ï‰æX±+Ö¼‚1%|M¹Ôq;¡t·ƒñ];Q“˜›ÌºM&Aþ*Ä¼X«iî|á­àn‰‡äwÜZn…#èA»Ï)“C]äLv‡h|ÿ\nö,c­Eß/…“§]O×ì…›ÀåjûÎ:vG±FyÔ.#öÁý/¼ƒºXíI�p÷&\Z`®ät´}Š‹�ß»E(0ì‚âÑÒ@íD»¿¼ìX‰C›Œ;!·t|¸lµdAF#+ñÙ?ïÑÀ½iî£pãÃ/ÁÖ@\'ùmD	%iÊ[�hÊ‚ˆm\r:ë–á´ã#%’M–¶9yãpÈq§�f÷ >DŽ$Ñˆ:ŽLuwIf¢ËSƒd¤Ë?C\Z—ûš(Œi,fÜþ i[œD�:ZáèƒŠPš«…KÆã¹EKÐLÖßÚ&Ÿ‚k„»ÐK\Z<B×=íŠ3QdÒƒ\Zfh ™ÊvÄ^�Ûæ>†Ê&3ô t*Æü\'‚;³’¯¸á½òµ·-Z\"JZ¦dT)þ:ïâ7­BKÎ>²@ˆo>Ÿ¨KAžpT.=ÆüXJ“Vgm²ÇÄ½°�´¶�.§‘<ü\ZÎ;‰5IŒ9˜¨‡¹íœFZnï?“5÷8kÛ<èLqèª�ËpÂ{Kr¨©`Ç}qÏß_CcÄ+9\ZÌŸ½Þ*DÜX¿ôŒÍ3(žVáø]ñUåFwZsëà„`&\'Û\rJ.Šß²ï>~£h[ž•4ŒØ	>þJ4³],Ã2<?ïJŒÕ5\r´CÎ¸„\\âÝQ²2tÖdlñõ°[ã¼#wÇ.dùJ“�?ê¨¶™¥dÃòÊw�SÊ¹˜Úv,Î¹êZrøì4À¾Ã’/ @&av ¥qnxèo0\'Üh¦ÁÕÑm–˜7OÕ‡ˆ\'üxì¡d°åÇÎ6’9r7<óöûpôGaî±¡�…%Õ&mí	4\"\Z¬CÄ¹÷ß|qeoŽFVÁD|°t-œ©º|Up¥úËìß\n“˜uÞhœ{ííh\'t¶\'¢ä\'ùˆV¨].xÊ¾±~5®Ÿq>F˜ŠkÕr\rYÌý‰Jí€Ó.˜‚·?þINO&¸®È÷pk$y¿\Z~¸\\.wÜq)­\nÀâ\\Ê¥i¸MChIñÈ|üÉë¹+%Zòûÿ*Ô´#›oÒÞ¦å€õÌB®–šK¿‘C7›‹I{€«ÿrVÛ¬ÔHäµ\'[àuÂ¨ÅúU¯at>ódÓ\\8ùV˜½pôaáè€«óï½Y~/‡œØœÒ	8üô?Å	Ix�Ë¤ù¨Ó‚t\\ùÒ·QÆ1y®;’]\0Ó¨	XZMœ;A}ÐŠVrÆ¬’»M¼ÛÃEƒ¸®7qZi·–ïpùo�Qšu+Þó`¬rÇQCZÐF×ó®Dýª7PJÀÏ—É’2áÍŸ×5‰ÄÑ\Zë@g\rÖà÷ð8¾ÃGíEàæI—	Ès\0*»:áînC=SŸ™\'‘Á”�´-[—ÚÕh£ó/þr¡€ÕHí`,Ý7Î}Öþ\06êhuJN�ÃS…¸w\rÊ¿ûí9Bú)7w””3.œk\"‰j¯�Ñ6´M²�\"±újIÁ5#âª‚£~	ŽÙ§�ÑH	î}ÐÉhpÄe×Cœ>@¾ÇgoÏÅ®~Ë %Íž¿ËAXmõ÷îˆºèõ�ï†_6áŠ!Lôçù…wbú§-ä{àš/pïWŠ‰û‚Ï—®�Œ@Þtè$N&n·\nn}v’Ÿ9R²óÎ;§9·]ÿà9n¾^ò!bd¶Ü-Ÿ\n¸y„rNJÞØpÅõ×ã�Gçbþ£÷âþ9·ã¶»ÿ‚ßýîT9�D8‡ÅTŒ?=¼\0õa;š©¡ýþ.¸º*1ÿž+QÂù9œ+>¯¼»DâÙÌâÝ}½n¬^úv((¦ßäë)DÙÄ=ðEm�Õ%–Ã½QßF¬ÿö=¡7%9\\z¢%vÇçëÈ±\nûI³ÚÅ¡”´W_Ñ‡Ô\n1;\Z!m^¹üLÈU³…¥8íŠkÑ))bäý§ìäoÐ\0êX�OW¾D\'�•MÂ�^C3é�rÆ\Z£•p÷UÁnYŠsÝ\r	ÜùÒ¨;Œ\rqVžÙ\\�¥k_Â>äÇÉâŽÀùšAÔ£~¼\0Å&Î+¤�ó§ûç‘¿à$êÓ†N‹\r2}©CÒµ_trY[	xÆN<_–o¤FG�—´=�›Ã~±à>W%bŽ\n¬þäyì’£Ò/Œ¦Qøó� ¨xHrÁ~pÝZ�ýwI}Ç™£ã�5jo,|ï_0§Ü<ñFÙ‚·àâ­TR	·LÍXêWáµ§ÿŠÛ¯½\Z£ÈQ-ÌÍƒ)·H|3vÂ�<éLt¸9G|øJ»[·¾ê†óikkkÓ!@}g±m·Î¹Çí4kÊ¿Îílúg‘Oš6Ù&ùè�ðÎ‹îçL<ç¾Iž÷·Ëÿ…ËÎ?¥&ÍL�Fé^¿ÀçÖÁšòÂï0£«q\rNþ5ñh)!L¡tÎ¹äj\\1s®š5³n˜‚«®º\0Wñ®dyy\\µßT6·>ý4q]rr¸�»§\Z_\rÖ-{—h€A9JÔy¥¤ÿµf=Ñ¿˜w·¬«ô«ôTžúwÐ÷ü48ž˜{F“%\Z•—%ßÝç¨ÓpáMwãüY×`úõWã–&ãzº–C÷Ý›\0›«r:\nÆc¯c/$ÍMàŽ\rˆm„³¯\ZVó\\rì/0‰®5�À]:ñ¬·¹…YÃå°‡¿Ç]“/fœó8ŠöœˆÕÍßãŸ?#Q�|ržó‹&à/ó š×aÂ�Jw�$šEñÙkwaüNÙZŠç¼ŒÄóž\"ÎDKØ�z;Úbf™¶·GH{ÛÖ’v­BØº÷Ìº@¨—¤GääáäÓÏÃ”knÆ4Þ´vöTLŸy)®\'¿éà]wÃHã(ÂÄX:nüöÒéèìM�ÕµÃ–¢ëèãŒÍº9ƒ‘\0ÞÓC�ÐÇÓò!Ä\\-Xôæó8ï·Ç‰r+bE`,šòÕúxñÁ�¹ÌlÅŠC–‘m�–ä²ÒàÞa—QS»J4·îPš¤¼Ã2¹»âÝ/¿F¨/‚NW5<q®¦ZIœ¬\r«—(4¡”C�äàÇNÂK‹>GsÀ‰PWÖ|ù.Ææj wT>�r£Ièçº¤KKÔA‰„)I‹xæÙätúÑèj\"í¡âëk¾~c´ó™r\n1bÇ]±x}¥€»ÑoQ‘‘ ªŠ*!»ðòâ	ä–•8éð]…\ZåS§›ŠÇÈd�!·„,N®Ê»0ªó²Ö.Í+S)o$Œã‰�Ê›	Ø>´t·¸ÖÀÞõ\r.<voŒ•¶‡‚]ÇJÇÙ;a‹l€Å·MËÞÂNeY*äXš�îû>ýüM9A6)�ü±øóCóD7ÅÚ¥gZ\Z—â7‡�V�ücQ);îTÔZéü‘Ú\"¡_¼àXdøÉqw¬EœK°5,ÃŠ1N¸´–ƒ’¥Õ¢Éa¿Ie‡æÕ\\A19œ&ÃH¡<£÷<\0«Ì-hçý~¢\\š-âF$…7•l?ÞI-\'-Þï#rí…µj9ŽÞwwi·lòd)îá=´õÞªæGqnÖÞ|‚/¿ü2=3¹­àÎÔÜî–ÖJDÈqëªû§Ð£4HJvÜoýk±,ksTÂ¯ƒ7ÕiùæÚ%Ø©T9\ZY¦Rd�Þ�¼ü:º„-5xè–iØ<™%3‰3äfÉ¬™dö1ÈósdEL	gùq£Kb·Ûølãz4{ÛÕbûz¬^ü6v(ÌRüÜX€¼ãñME•šÌðYÈirÉ²«NÉi&ÿ!´6Ë2|÷Å?0†x^¶6õM|Ý`äì¸\\‹ó2æ²µD*m å±É-Û\rSï|-É89píh	­ƒÝ±<jgŒá2¹“`û+¬´zÐÎ‹µ4¨*Ðç^‹k\'Ÿ©’¼è·\'ì½3Þþç›2yÆdy…;âÖy¸ëˆ+sRÏ!ümÁ-Bã²ÉÂ˜Š|¥£ñìkï #È³�4úÚe&¶ÉÕ*uV¬žzD81Í[��ßyBú¢Xbêûâ|	á—$^™T»òûÔÿydÅòsG#§l,zþïtäƒôøá÷4Ëô:‡-ü±„‰jÄ¥XO½D·mðà’SŽÇ.t�FŽÌÏŸóÒûhìØÛôG9”:ïf€óöÑƒ‹ìdý`p[»êÜ¬¹Ï=¦D¶aþ›7vW¢%Kà þë\'NÙáãrÁÕpúk±ì‹×0©D7™ÚâwÃ«‹‘Óã‡­f)ŽØs´pÜ¢âÈÛqÌž3sžysçýóæÞŠ‡æ?€{ç=ˆÛïÿ3î¿÷\ZüúÀÔÄpÌ8\\÷ðC°ÆR!@Ô¤|É»_ ~//§eã&âÛ\rÕ2˜êì­jµwÐ/u¾…¸¯#°W9úËTù¬‘Ë&à¼)³ðà£�àÁù÷â¾9·bÞcspÿ¼ûpïœ»1ïá»qáE§a¤ÞF…c1éðSPí‹¢–W”÷\rèZ„ó�Ÿ Ü6ß´;#ÆÆ`L­n÷¬…;X‹¤g�ðü½\'•©Òu¹Ù¸öO7¢¨€œIÃh²qçcO£)Ä«rZ%±ªqíÇ8x�Q(ËÓbåYy8ý�—ÀŠJÁL^+Ê	b¼š‡ËQ¸9:e_\r¦K.:EyLÛ¨ïÊJqÑôéxàÁ¹X0w.š{æ<ö æ<y?þúø½X0ÿ.œ}â‰Ã3Ÿ9E#qÂ¹ -#eÑ‹€»R¿¬Xù=xÉ/OÚØƒ!Y­ƒ~ÒÞ}N�yÆÑûíCmkRÉUùcðÑŠõè\ZR]øGƒ›#&o½õV\ZØ:ïÞpó\nx÷ø�w€ÛÓ&´„57sn™ÏÉÅÈ]÷Á{_-‘¥I)„àíî”ÀïËáôVàeà�–ÛqÏ}QÑÑ,u«>}ã³yÊxïcÏ€™Ô€·�7*²!0ËnÀ¼ö:ØçE²§÷Þv…€°ÐX(Sã{œt,ªíÍ°ùê%qhå—oJ(°(K9Ã;LÚ_¬\\ww‚´v€\'Ÿ,l“ãäëo%�_Gf~~{è>jÊ>{òÇî��¾YIþ¹KýÄ%éZR}jK¼ýŸ‚_|MN™Q9šH#�Û¯,^-K7§¼~„³��ñroäÈ�9Ã	âý\\¨Nrh]¶oõTâ¶Ù¡HK�´û~Á0•1–îŽ?Ï[ˆNò8þµ­ÂíWŸ!mYDÎ¨1·;MÚ+××ÃOJ®¼§×%yî.Ó�\nÉÚG¾Wo¨�íK°ÃÎZifNež´/VÖ¶¨Z‘A?º¹´1<äô¶Ó3µ²\rÿ|uÆšTPÀHýW¶Ó>øf#9åq vÃW(&‹sÂ	\'à�¹�¡ª�s�Eè”>xœu˜ß_Hûç¢${”Ø]÷þj¬VÑ¿YÎýƒh	ƒû…^HOàdæ–l�së)¯n SÀÝYý.N9XÕ²Î*Ì“ÉŽ3.¹So˜�¦œƒ+f_ˆKg^€c	ØÄÑZþqqaæ>ñÌA;êœµäØœ.f[’«ŠwÄ”»E™«Z\'«©—ð’ÇOfÖÑ!5;í«°èŸ�pó¬yãÆàËõßª¹dº—}ú²hnæÎ¹4\0²FâìË&ãòë®Á×OÅå³.ÅÕ×\\‚«¦_ˆ™·]Šµ•‹ðÅ{ÏÊ5ÊÔõì~È)¨ïòÁîµÀF˜×CÙ±QVŠ·Ó r„«PÝôŽ!�[š£¸¡t\'\\yÇ|´&S¨\"K`sŠsŽ+pgöDÞ¤ßby—-Q¢CQ:‡§NçrkÐ¸zv•—Î®ËÊOý±9à»áÆy�£5Ø…®®5°T~€ýK¥à\"�|ìø	ûáÊ7âò31å†«pñÌ?âÒkÎÇäÙ—ãŠWaòÔ+°`á}°¸Öâ…×îCÑHE9Lùe8à´`ö%á²ûàï²Àãh‘ÕDœ-Ù(‡7¼­5Ÿa§bFrúƒ¤bŒÂÂ—¿B‹hªý£ËŒÚâ„Ò˜1ûZ\\{ÓtÌ¸†0pÙéU’G8\"‡Ù¸Lt_�=ù¢hø€än¿†­AÑ‹ŠGÈÉxê©§2vAÈVE-�™«D4º\"%rE83L_¨°ó„RxÜuðû6Àlþ\nÇ?V–©¹~µ¶�ç�låRç—íDüñÁ;PÕ^w¯+ê¿Ä{OBn‘Ê/[€¯×}ƒjG=Ìq\ZƒÍhâ•ÞdZ½ÕRçÄK\0^[þÚo4\nTˆQ£pí_n–ÕÚ\\^øËe¡ D­VÏÖ´azS*­tEN®rš8µó�7_ÁŸnœ­®ƒÏYbÄôÛ®%ÓkEc M&fZ#MdijÑîßk¨\nVÿz˜=«ñçÏQœ÷Ý$“»Û~‡£É¦Ö„6’CyÂi{È¢‹¢Â|”•L€ÅB£­md-š“«Ðá­%3^‡Ë\nÜsÿU²øš}YùCý“SRŒ;þz�”zã’ïò,\nò\r¢åGdôÛÖä—‡Œ–ŽVœýÇßK~ºrD�øË3ÈQu¡Þß!Q$žØ2»6Jô(H ÷Wï0¯ÃÎý�rîMFKÊpÄ‰§Ãì�Ái«Ç53.R~¶a *p–¶HAÂ¶ùy\Z1zg¢šOÀêŽžìÁË;l¸y2ƒû¹çžn‘AÀÎ(x™¸¾³®w�æ¦ïáõU¢µý+Üxóƒ–¨¥—8™4�s¼xü|ÔÑ˜yÓÍxêåPÛÙ\0ŸmÔxïó\nr´<âìB=þ¬o«PÅsâ�oâbî>ÞLZ™³vv–cÖŒs˜N`ÊÉÆaÇÿÚ�jËŒª†•ØcŸqé{ÊÉQ³�œ‰§oË§GbÊÈQ|û�—qâIÇ¨Ì8ž´]ˆw¿ú�8¥Í�v´†Ú¥P¼w\r¬A¢e�\rèð¬Ã[¯ÏAI±6Ë›]ŠÂQ»`Ñ²¥BÉ\Z»¾Å¹!%3¤ˆÑÃlñÐ÷ä�5¢)º\ZíîZÉ‹á”ÝÚ†Å8÷Ü£dÚ?Ë Ò\ZŒÅExÿË�ÐâR¥(¾øöM©“gÐ¨œq+¢)œƒýKÔ4VcÇ‰;ˆÊŸ�7ŸT|sÊ‹¢í!U‘—ìÙ<p9\'Á¹àÏ<÷×Œe9(\Z7�úÒ�$QŸÊuKðøc÷ãŠKÏÅ„	£2ê¹gKDë CÇ�¾o¿÷	\\ÞH\ZØ½?4+p(¸õ´±X¯¿þzçÖ´·¾/k8p›%Ñ–2M ÍýÝŠáÖÀîX‰ïW½Š{|\0Î›ƒþzîÿë�x`Áxðz~˜ä‘ûñä?žÆÇ_�»…—,ÎåýhšÝ°¡ã[<ô90ßƒùO<€7>yE\näXIk7%i5 %P‹vg N4;¨¼VrýúOñÐœÙDqÅçáéWþ&5:¸~J›­¯¼¹óÜFŽß]xpî˜3÷n,xä!åœ>p;}j>î�{;<<—,Á÷xíõqßÜ{Èa¼O½ô7ê¸F˜]èY¥Ã«ÏeÞ@Š®Åê#jF÷àt¬ÃdòçÌ™ƒ¹óŸÆÜÇžEM›*€cv–ã+\ZÀ/¸<‚ž{V/Ú]�r_MáµRiŠ‹õtÚ7ÀlÄÒ%oÓùÀ|º®{œƒ§_üZœf´ºU	çój<òØíxú±ðÔüûÄÙÝ’Ìy˜ûån¼ñÞ+hî¨Ç#OÎ“÷øù‰gEm ƒÜ.´„;$þÞâ®‘ìH¾7Ý£‹î×ío@SÛzúÞ]xâ¹Ç1÷qr².D³Í�}¿¯‡|¡¸­­Õøð£·ñÌßžÀ“O?�Ç>�ç_z‹—.§>‹ §_4Ž$Õ-ùÑš›ÎàÖ£%u¶³3@=Ü\ZÀsrrÓkóÆíP‚O>{>^¢ä¯D V#¹\\Ö—wéåÂ.¼è ÜCŽL�ƒœ/üIŸDPìa\'i©v)So\'“¬B}Ÿwø\r÷¸e7^AÎuDZýõh\"ÍÁ•N¹’©™€`&:`×ÊêøVË*øè5‡¶\\q|=~)PÃõ>8³Ïâ®Fä,†[¡órY…hÒ�hÂ_Ä!e¢½>ô J\rœDOo\\ª™òîk\\p NŒ0Ò‡\'€;ANi6OÂ%YŒ\\¢�ï3�°ÀãŽµ£¿7 á¬09XQ…‘ÌÛ\0†»-ä2yÐÛí—\n4Q2Ç!r(ÙÉsõØ$…K_p­ìµCª×�T·“ú, Ûˆ÷J�­‡ÚÌ\nkÔ\"ÀëôÔ�“gC2êD2ä’bÿ[.œÄ‹!˜²qÙ�¹rÜ_ñþ€Ð®:o#ZBª†b£³RjÇ˜Ýë%G¾“o—«š¾ß†`Ê)µƒÝä¤R»YüÔç4ðC¤T¢1ºöž ÄKx¿y®÷ÊKz0P£$ïA\"Ù‡nÞ´A“´†r8pó†>ñxË–-KÓµ†Ò˜	/j\0È]÷èq%xûýHIÝNGñ2r°\\ÍR“[J©”Rj¤Ù˜Kò ÇX›l\rh¶S:díb;ñçvßz¢\ZåhsÕ¨ýedë�F´yªeƒ&6ÿm¤9:ÈJ˜ÕhõÐ€ðV×­„…^w�säU§Ë«µºêè\\UQ:ŽÇ„ÍŽ\rRí‰3þ,®&©kÈ•¬N=÷D\\1ë\"L�q.¿òb\\6å)K1yúd\\LÿOž5W_7MžY¸\\Å•3/\'¹WÍ¼Œä\\9ý\"L»ú<Ìš5ÓgNÃ•WÏÄä×àÒ©SpÕ5“1eÚ’!9íªK1kÚtÌœ>ÓgÌ&göj\\J¿}ÉµçâÒi—Ów.Ã4rþ¦Òù¦óñôÿ•W_†ó.¹\0“¯™ŠçÞy-¤Ø‘•òtjs[¼.U9`KÂÇsÛ6Û«¤­ùÿVgµ¯j\n4£•>o¢AÓÆý¬“½v8šcõWI[vzªdËÎ(äªa\\ÅËá‚D]RZ9îB d•|w.‰Ó`ïï‘êR¬�ã‰”\09‘ìMWšÒ«fþ@p÷6ïX•HÄPQ±.\rn}à�úÛÃ\\qT:*U„g_\\(ety’�£\ZŒ\Z…¨C»‡x¨O‰Ù[%ËÇtúž�¼´‹ßkqW Í»�¡\núŸÌ3\r”Vw•Pþª´0°;ƒiÐlTñaÒxþ\n4;WÓ¹êd÷.™Ìße>lñU Ñº‚hP�toöÔî¬óÏta]Ã\nŒËŸ€\'N˜“\Zü�Ü\"Ó`\"Ó9ÓW÷kÿç›´‰¦,-—™\'É²ÕäJšók‹>L†œô¾–ªì‚æodoâØ”™�;\"#\'ŽÁDk\Z=\rbÑ:ˆ\"t¸è¹«ž¸0Q	å¶—xç•´3×>áRvídáš½j§·fjÿVïFÙ¨Šç\'ØR¶y+$i�×bá~ÖËCËÄÓm¤Ü‚dâÝ^±ŒÝ}Qwª\'Žï¯ÃZ»¿oPX•0¥öÚÙ%Ù&p³æfp\'“q46Ö§iI\ZÜÌ­|€¢Èž09Fµ³\0Ç¢Ëò‰Kß+µ¸¥ð%Ý(ƒR5Æ¬™@ÇÒNN\'—Ë™IãðÆ¦¼Ñ)sÖNÖÆtkn.©ÀÇ	 5Pó´=7(×É\Z›@Ë\0o°¯–Fçó3µamÖB†wkv­—�ºÂÔI:‡—þlÔÎQ-�ÉuöªÚWã•�žÃS¯-À­÷ßLšù\nìû«}P4:_9ÂÙ*W›Ïge\rÙe\"k°dkÎŸþ–)\'£î‹ª†+ÀæMí=.ŸÁõDà¹j)[N‘G�pf�¿“®iá³ã…·žÃßßþ¯û’ü�Ñ®�¼A-ƒ‹¬�ÕÑDíY³Eáþá6nqm@#ÑAnc.>ÄmÎï±ðqü>?³µlñTŠÅäÈP3)\"þŒ�w%ÚUŸ“ÖçÊ¶¬(ü¼ß<WÍ\"ßÄ±!ÚÍ‹–½RµŠéIŒ÷ØéïŠÂ+Á¥cw÷üp÷\r7sHú7K[[Ë p´Œ¿€wFGš²PDšûOwÜ(…h˜bpAy¹ù\0›èB‹»\\\0ÎÒâæç\rRS„·aaíÚæÙ(ï³6æãêíëÄr£rG°°¶`€·º*ä™ÿo&î×ä\\\'[ep£7’Ùl&Á†�Sn|d�Ž5ô�õb=ø™…-Šêè�ªÞž¶!WUåš|u�5øhñ?ñøs�âüKÿ€‘cJÒ»·q˜�ãþ*b4¸ê­Ì	dé–Æ�®E\nÔ–†4HÙ2_�—›-)²‘k¾¦­ó8ê7ÇàÉçâ‹eŸ¢²v¢}^x‰ß{SªhW³­uVH´†¯¿Ý[\'åˆYƒ·r[nA¤­´vÓßk\"%À\0æÏ˜B6è¹ýØò±öfJØ¢÷Éú¶øMl•AÑ$–—q):¦)\\ÿ�ä˜Ó©-Uáú€ìÆK„øw_�`Píû×›~þÁàæÉ^&$ÝdXØ¡4›Û´Ê²¦á‡€{“ýptmÄ¦rDfÞ4‹nÌ\"[D7ÚjE{¶�†lö˜ý¤\0u‚O‰˜63ñèÁ¢\0ÎÒL¼�…UK�KÒá�vÏÀg|ß¤	ÿŸù]ý¼J”†×é\r )¶�ym!îÉÒFüµÝËYsrØÐ°óÎÅaG,÷ÎU¶²´eréº‰YyZ¥®¼Á{ejõôv†&)\"*\Z\\«+áOjÏ�v�€ko»ß¬Y\n™tÞi™}öUxñÇzK3?Ëµ×È=°¢e!÷´yÑÛgs¢·¯Y³œ¢¹µßáÁÄŽ=¿Ö7¼ÕwmP%š[Õ.Å{Ís]owLß_žÀÍ\nvóÛoosñùMÁÍã”DâÞVk\'8`ÿÁÔdÀ­;”9…¹8ó‚³¤ e«»…8oƒÒØ\Z¸ù™AÍÏ¬€W=<ÀIš½Õ\"­šˆCéÞTøýVíX–&Ÿ’âÔºèš?-|\r$-i©\nÃæ½ÉS—Þ.¤¡‹ÎOÎ&ç2[]-xåõçpÞùgiõÈ³Ôk`®ÎÅ3£Ã�;sU“´kž,Ö�Š]zŒ]ãÕE¤(ybê­¥ò-m°Ð5¸\Z„¾5;‰\Z„èZ©-•TŠ&°É}dÞÓæe[ÁÝžV™m\\‹�âåÂ÷91Ø”±Íb‹Ôôæý;veÐö¶ì	)p÷Å¸û’?Ü}àæ½Kú’rRÖÚÑXÉTv{.»ì’A+q29·2¿ƒKt¥câp2«ûº¿�o\" ðƒ@M�Í4ep£oV˜\"°4y$ó}Ž|è¢¿×’ql£WÉ&¿Côgàwªäzø\ZEø5Q˜F\Z,�îZ:k%vLë,¤åÝªt²ÅÙŒ\rÕ«qù¤)Šî£iïáÁ­+\rÎJäÿy7f¦!¬µyòèú?_ƒNG›Ô(d0×[7°yu_{½½\\,a‹w\rÉZ­MîKˆ¼_õ“¤Í­DWª}ª2¤ZER˜\nù4p·ìÜ0,¸½n®Yòow‚÷¤ÿà�óçÿuÐþí™ÎäPp³¦2JþÂ@‘ËF\nGmrOö6 ÑU.Ànó)mÒä\Z\067Ô€T\rˆ«:-™\r©Î<}¨èŸeß0¨£4`ë2¸Ó\0×¯¡VÎÃÇ2¯dsßhß@÷GË^#³�Ì�;â\0•‹a2¦Á«¶SÉ•AŸŽ,im–¥qm�ÆèPŠŒ’œõÇSQÓ´®@\'êm«ÄÑkegÛß$ƒ¬ž´v“{-µky¸«�»É·–dÍf•Ç¶J«´·r,õ¾j ?\'-.vu}\rR¸Þšn®-ž	nÞ™˜ÁÍûuriåáÁýƒ6YíÛÜ=\Z×apó¢z¦&�€ÿú×¢(#õuSpggtNŽ8�›ÃSo~úªìÀ ªw¬Ð(G¥’œ—Št#ñk~o°li �¬dÃ iÚFÑ�×µq&`•TËû’&÷šY;pÎ�ékÔM¸pU¶ŽJ9ç|¿óñ«ØqÒèt\\=„Ê Óù8Y[\0;[ËcHO(™‹e«¿gÐì kô¬Ö]êQÝU©Ààk_%ÒìZK4…îÑQ#Â×ÛàYƒïÊ­¶Ó ¥2¬Tl\"ìpf\n·c«DSê•æÖ7ÄÕ¶(”íF\"ú¶Ûq*ã© l÷—wšs÷Úgu+à¼i¥ÌlePuŠ‹”-	†¼¨««I›MeftDk\rÖ>àæD›ü&Üx×l©ùÁ<˜ÁÍÚ›� ¿®w­\"¢™ê�UšT¶ÑÏ¹©¬Óž+%LÖè¨VâT�¯­Ñµ’\0ó½<79WKÔ…cë`þ¾Þ¡Lêlkä=Ýg¨µ”ËÌçµž–¦ú¶*ÒVF=/Ç¨-†Î•<sØéŠ–osò\'È¬lC\'ieý¦óÔt}‹j[¹€»ÞU#\\—9v��®ÙAšÝAÒFÊÂV#Ò@×Þà\\…:÷Št›oN6U.ƒ…£N�4¸Òâ-ßDpˆ•5¼¤hàV»¶mÜú^–<û;\0î�-¶0¸õðßPp3%a\r\nû`±tààƒÚ\"¸9te”–)\rn.qÌ‰ô9ÅY8é¬c$~Í7Ì\rÐà$á$úÐ\rv¹&Úÿi@WmÐ¢¾§€¬B‚ºxu€§Á]¥�›?_­€í^N�ù=	¼|\0àÔyuöU¨µ­$3¼V:¸Žî§–m�SÑŽ\\,^õ)iÝlRmu¿€;G\\íÕ™OÀÎWµU4`§—ÉÑsÁè\"ü�UÞ„©�4q‹}\rièåä°­�5�ÀiÀ5ŽJ¹_¦#|­Ít�Í6²F¶4Ùëäzø¾ë=ßoaÐWlÆr–zÏZ%DÄ\ZxV\rX~ÖB‹ï\nnY®\'à¶ÈüS“Í�›\'súúñãÁ=Ô™dP³æÖAÎàž:õ*�’hZ\'{Ëà–ðWvN:ÃlÏƒwF‹ƒh��ù¶ƒ[ÅO«ÒôDuØÚ!²^¸\\=™ºzv(]•ƒ9æY?HÔw2ÌdZÖj4CÓPD3ÒâRñ\\Ñ>ê Ï\nê,–•ô½5òý\0tí:W�Kâ+WNŸ1Àù\\�¶�øå‘û¨zß¹jß i¯MÀ]8ÜYz\n0}gÔÎcPßQ\'Nk½µ\\&™j‹¤|¬µè³:gµj¹.ºGnëu§0�4óýq[\n-ÙŠfÞ\nøk<t�D�Xê©mäœ%ÜV:¸ù:˜®I-s}OÞ8W7ónÜÁ¸{›ÀÝ»UpgY,é?)¤ú“²L´\'‚Hw¡dÁD\0].þñêóRž)3æLVäHÑËì´pÒT¶V”>;g W{ìÄ|[þ9ê-•²bÎÚ{£e¹h›z759™Õ¤\r™\Z˜µb�‹L*iQîÔZÒ?EÔ¹~¼4Úi\0ÚÖ)JââëY�\Z÷·\"üšƒlU[9þtÛl5{ÉÛ§dek	ú™s›JšsSÛyÂ¡¦:LØÏ¼n7ýu­s%iðïòýñ9·$Š¶•«Á¢nýs9Gz�WÊ$’UÛ8WçÞvm/y7oñˆyNMñjªR=¤lûsîmQÝÃ‚›7ÂÔÁÍÂÀöÇ|r®/±rí\nŒ[ª8¤)##PãÛjÂ\"#ç[ÛdTÀMÇ—ŒÍÅÓ/>‚V2¡Õ–Õ$+•Y×€[Õõ�t7J��M|yZjˆp§Õ:	8®å¨!ÍðS„ç§ƒ›gIÙwÜ¬Ñø›ìÕäX¾®r¾\rzE­¡‘’M�mÐx9çšœù9BI\ZlŠß³%Ë¼–LPnëõs[nI¶~¡”š÷¹\\—Sõ!ƒ›7ˆepëQ“ÿ6póü>š5v0éC8‚7äF§ÝŒs/<G[Ò¯¸w¶æõs K›:ÖÁ­\'Nñ`0eÁXdÀESÎ‘l@vÆj¬«o¥†Øh[Žêÿ×Þy€IVVy:N\0f@X]#‚kBÅˆ¸*küˆae‘ \n’XLë§.¸º¬¢¨,¢(abO÷ô„žéœsÎ¹««:‡éîéÜÓ“Î÷þÏî{oU‡™f˜©zžótwÕLÕ­{ï¹ç=±?Ÿ¥AÜþëÅ-­^Ø�\râ¶&/>­àFú­†ÇT�cRpãw¾°½¥l–ìLÙ.“¢TeßéŒ·)4Ü:ùU>?þùCÔØ]ÃfàÆ9²A\\éw9øñ½ya÷„<þ×¬¿„Íÿ�F9÷t€Z{êð8ç\\˜¡¡}lšlMØlò bc£­lÁ“�Bx¸„²Ï£×É|ˆ·\\y1µvÕñ¦­¦GnÆ\0vm4^Kí€€\\ìö5àü“Ož\0h0—á®,8¥ÒØWÆ ÉE‡ãÉ5p×\rŠïÓ[À›ÓªöRúþOî—™€k¤Mãv¥F,÷šsbé·þ5ÕùËÙµ†÷åó%ÎŸ§Sõýû‹l­�êús¥ÊkƒcÄÝ¶®¯˜�§Ü<›P˜&(ï‡LÌ�Òðx?5uÔÑúÖQLlÛ�NË™ÃÍ=Þ\0¸†Ývi¡|ì•kiÛ®�TÝ]@þ\\†»º/—¡®êÍ¢ZqK¯ðVõçQM_�œ¬šÞlªíW¢.î©’úžÖJXtX˜5™T7”ÍRÓŸ#¾Ÿ¸¸bóWÙVBúø{Ù,‰P®@·æ^îØsbèOŸä((4`UW.ÞJ�¿çwYòû8`óñð1å²\0p˜˜Ø;ÕöñÁ®ž—nï¬m†û°nüE?7ÒñáÝ-të7¹Ò3íÂáØ˜uw¤šQ‰´W¤€²w)›kVÑí÷ÜJêÙ+RÝ›O•=ÙÔ4RDe=éT5�MÕBV\n€x•:ÙX\0€»¦/‹á@§TÄ]·ZSuÕŠc`÷eóë�bÃYÝQF¯|Ãù2O[� ‹ðÁ\\±+7¿gGÓ3[ŸövCRÈd\r©AÒÇƒÏÔòb|?ýþ‰„:Ï€íý¿U½9|—ÁyðÂ}ò7”À�–V€\ZÙYÓó“5²µ0nzdzˆº‡}””‘HkÏŠUÞ�Ó…J‡—W™ N$·ˆÒ¹Ê*Œü–ËßLE­™œYÑ•#áe ³îòþLª\0Üîl—æ®\\¡Ø0œˆH\ršÇ³²\'ƒ*{ÓX{×àýÅb…Voì­¤ÄŒí<]mÝ9ñkíÚµÁp¯r$ÜÿF\rÝåì)©ìÊàÅ\njî¥ŽÅ W]ØÙr¡ãüˆ»0´wMO¾nÎ<•ps5„\0œëŽÑ<ÍPïˆ_D¿Ø`î§ÚÖ\nºöŸ?D1«�ˆ«¼Ì†Aœ(çßAƒ­]¿†þó�ÿW|ñb^Ù-ceTäOa°«†²¨rP^.N@¬?q²ªDÕ½™BÒy!¬DäE8qÀò.á.pWYp7VPy[>ýä±ó‚¾è²7J×žÚ£,·vª�øêõ±Ÿ¼…jü%lÃVödRU_º9Ž*q>´Hè³-È>Ÿ‹È’‹Ûú7ÞÏÄk8NîR¶¹5Ü}“�/Ü2ËÄ�¹´Sålv$²ŒM2Ø8\0¥ö�ù)1)Ajb˜&1‘fr°Ú$VIáD¡(9Jò¶\\B¥-ÂÖ(§ü–dªÊg¸üInv:^Öp2¥vd¸ÓY*ú2•òÞŒÉRï_Ñ�ÁR;œ\'Ì©T†»Vl&Ëi¼®î.¢Šö\"z÷GÞÅ{��\\wmH¸m°¡ãeZ³a5íÉI¤j!ßæ+ºÓÙ*íNc3nYÇ\ZR²œóÅDœ$8>)âZõHÓ¤¶«€áF—YLðPÙ	)Ã&B9ÈV‚†\nöÐ‘ù“÷œ2K ¹9Â‡ìÁñ.tTúü\rÿGÚÒpõ­Ž6)ž^¸ñ·ÖàpBV_I�=ý(Û¥ÐÞÐv¸XvY_\Z•ö¦\nIç	©Uv§\n\r./ìb²¼Kýÿ¥fTq …Á.íNw©½Ë»Ò©Ä\'.´€1­4‰ÖœCëÎ[Kwï.iÆEFºÏ‚áŽrÁ{nŒ€{Uu°&,ëJw´”ÿJÏŸq:Ÿ}p�Ê»Òn\\Sl¬kù\\¥ƒ†C€ÛÖÜ\'n;NL…àm¸‘0>#´7¼$HEDbËÐD7§sâVâè ÌüT:ÿÕd˜8Bîh%j¼\'Øà<R[hý·]}e•\'‹•-n·�6MªÅ¦Ú»¤\'…¥¸;™¥D\\T¾°âVtí3¯/$ra,,x¿•]‘8\\ÔâÀ>¾°…�Il¦”„YåË§Ÿ>þ0»?/{Ç¥ôýÿûkñGÜ»³¨ÂŸÏ›´\"ÿ^*íI²`J9±ãW\nc!Yþ®4óùÁÇ�ªîlY|]5ÜÈAÜ¶·Ä©Æ‘pÃb8)ps%Î‘C7Æg•æFBàÆ�è|\0l,1šúŽ{¿)oŸgE›ÀŽÝãÄÜhp\\À5èñ·Šîýáœw]Ü–Á`àÄi¨‹z’ƒà.ëÚË¢Ÿ?Yp“üÎ=¼H*Ò¨0°‡Š„ð1\n³¤¼3‡\nš²è=½‚V­[Ew~÷Nºë»w[æ[ÄpG-wygÛ±ü9Ý»åµ¿}lË9þbq\\‰,v2/vhtÜÅÊ;³8\r¸e-¥,\\ðÂ�|n(RiO¯î£äîÃÊ,à°¹a–à\0�r‰,®>±Û�<´_\0ÞNéùûèMo}£IÒ9\'^Àu³Á”!jM½îM¯¢-{þÆÑ¼’ŽTÖ\Zò¢íp;—ˆ¿!\Zî¥4÷É†ÇWÖ‡;@šTü„-›×’Äp?¹é7±~Ã½7c/Ý÷àwåâ��2MŒ¼Z[Ã½JÃ-áÞ•³�ßSÃ]Ú³gÑï¹œï_Ò“¶¨ÜêÚ°(À¡Ùy¡û3ØËƒIè/Ãî@dè4p;Å\ncl\nŸt¸¡µÙ49<!v±CòÖ1ÛGC3]7º±™r @¿ûßÇ8‰L¶Ø5QJ39ayNÎ×PQU&hcû©÷QY‹¸…ueQ±o¯|\0#á–âÀ-.n`·yþTII_2v\'I­�c‚ÛqY ƒªºòéCŸy7o$ßýá«¨¹³™î}à>ã:ÕßöBpïÌŽ£2_6kÃBÿN*îq¾¿þìãÿ+[Ü±u]ŠqZ`¦	³ÔŸÊp#Ë}M�îŒ^ˆìôÀ­k(÷ìá™“÷¼X=ZsÃ,Á°æÆNwRåâ\n907H}û}ôÝ‡¾cšÃ@{GªÖ:ç$Â77ŸWyÍgÿC4=ôŸwQMW.´%	\r˜ÎÐØà©WâßÅb?*¤@,²<qù��b�°›ŸËkßÃvñ¶<F«ÎAcÌHzüO¿¥šæ\Zºãž;]\rzBimÜ‘²?IÌ†hJÌÚF%í™|W+èL¤¢îDs~B�\'ïß¡eßÊD™bZ\nÅ90\"öEúÎ†ÕV€i¯ð˜ `EÃ�öœîzP¶vxIàF>-´÷ôÜ˜øÐýÜt�mn6\0‡Ý�öb˜æ…¾¥Õùô™Ï]gàŽR•ßæ¢©\ní�X¥Z³©tØ7¼õôä–ÿb¿i~ÇnaÓîbh æ\"\n­)ò%š×N–xáñ\nà.èÚÅ’×¹ƒ*Óîœ¦ÝtÅÇÞÌ]¡ÞøŽ×Qau•T—³díYk„Û±·Ü±î™[yOïL¾ovíàócŸ#[ðü’Ç/6í+|¶|^žo§86%âú´íwád†u²Ü‘j¬qI¸á~^9Üžš´cªØì¨jëxðè‡à‘8…Æ†¦	�÷lãæ,ý3-<ñªw¬‰2\néíW\\¨Šbe¿�è(\'¬,óM4Ü²V€ø…¼óªKiOÚVª÷SAý>*ëÀ´ì¢œö�¢î]”Üº™2º(Sü\rÉ:=� $ž²ÄÏ¬®íJ¶Qf`+eù7ÉlaÉëˆ§\\Ÿ”¼Î!ÛYrýRòÄ{eú¶‰§lÿÊíÚM9B²�Ìž­”î‹£\"q—I©¦Bk.•·ÑcO>JBk¯:kýÇßC�Ý”]±“¾uÿM²7á‰‘ðFFxêQ£œ@X„ÌÅ‰9/Š¶gn¢ÜÖ½n¶o›8Ž­”+Ôb’ãK8©’k	·\rw~ënÞCµ¦ªÍd;ù†\ZiXÜý\'Afèðx7w›Bû:\r78Ü·äèB=KŽ¾8pó†RÁ�¦áÆJìŸm6T+ÛRƒÓÔ½¿‰~û¿�Ò…—\\À\rÜyÓíxPV™”X§Ê[¯º)	mwÍ¿¼‡6íú35ôQFMÛày¾Ý”Õ¾�’›7Qñpeø´âöœ!´˜@hKVg<åÉ€-9�qR:¶à9�nÉõ\'0Ô™â½ÓÛã)£=‘²|lqËÍô%Qº\0,ïß.€köws6Å%o¢xãÞD¾ÿ_ÞJ¹5Â|h›¿æ4ºùî¯ÊB�Õ1nWÁÂpGŸIqnhÈ¬Ž­¼8—‚/[,Þ“)9¬‚¥#QÜÁv1ÜE)b¯�É›IÀ�nWpH`Ÿ¦á›îç=�†Ì½$p#Ï6÷üÛÖ6Üh‚Ø7ÓÎî¶¥¦|44é£ÖîjúÑO¿G¯yÃ¹³FÙ˜¦C¬²·£×ò@¥UÆ{²J5�”›¨÷~ì�ôÔæ_SiGÛàiõÛî¢þ$ÚU÷¥w\n-Üžà’ì6·ä´ï`1+ÉÏAð~¶à‚iM\rInŸ!4vfÇNJkM¤ô¶],i­;üŒVñÿZ“¨Ô—K»sãéÊ¿�ýÚëþqýmÇãTÙ‘NÅMbÓ×˜Bßüî�Ü±!á66w¸sZ’øÖŸÙ¾E,ìMAÇÿR‹v\r7ÎynÛN!0Möqø]zJd#L¸\0ûÆ²�ÚD��n@°ö¢ÂMÀ­³á-™<4ê˜&â¶¸gýÒ_)v¿(ÝG›¬ö�Z–Òºlzä×ÓEoy�tÆ¸ûàIY-E­¥5ke�½˜u±&¹êíï¿”~÷Ü/8Ÿ¶lZãVÖÂù½Bƒú·\ZXƒD@Zv¹$]hG(ÃÈv–ôö8õ{CœÙ&´•O˜Fþ=,Íñâö»—r\Z“h_Éúø—>,g3®_EÿvÏç©®\'—2«·Q…/•Ò+âéŽ‡¾¡ÆØEÈ™�‘î.].W îmé/PnóÖŠmÂ,óm<åp‡Ú<Û\\ƒ]7ªªÐÓp£�º¶µaš¼$p“n;õ6n¶Ý\r¸±¡d¯‰ÀÝ?ícóu~µþ*kÉ¥ŸÀßòî7YP×8%hî˜è³e¦¨5òV\r¯Jl¤„ÉF—¿†þëæ0.ÂÛé�ÂfÐ¥4<\'@Ž³d»%¶O´d—KR…]IfEzÇ.y¦2q`Öd¶oc˜r:¶±=žÝ¾•Òš^ äúgy?�Û�DÛÓŸ¥�~ñ}¾l-üÑëßGûJÅ�¦ag\næ4Š»Du}óþ¯¸£¢×µ [.Üé­›N¸;âÜ¢Aç=«uÃ]ØžLícU7j(gºnfg	¸)?©pÛv·6MxS9Õ+sLä�‘ÖÞ8ø¾™6ÎÓ.lÉ>…*:òè×Oÿ‚.½â�pÎi^³šVÇž­Ì)rÆ·,Q‹¯k\rþÊK6Ðƒÿy\'Åç<C%�É”ÛO…;Øn†\r\r”oÕ,ÛXôÉÖZX»ÃÒÎ	îTŸ£Å5Ø¸C\0\"Àœˆ¶®°qÛ7Š;ÈF*w�²žx*nL§ßþõQúÀ\'¯”ýº…\\ûù÷P|öszNoˆcÿojÍf*hÙK·Ü÷eÙÏ;FeMz\n„m·©îíiÇ÷R6óÉ‚;«5žá†YŸ¿o²–ü“M\\CÉž’	ŸŒr»{ÍçgØÞf¸Ù\r¨á>Ä†û8á&÷1“c¢á†i‚‘p´³Ý-àÆf\0\Z›4ìªÎ‰z\nÌ4PUO{3½FÜÆÒè�/ü]õáw˜†í²žPš\'kVŸ­úâÅ¸´¸î‹~\'‘çˆ\rÚ\'.§G~ÿ}*hÜCÅÂþÌép°±Ü\".öfJómbIïÜÊÎ4ßþ=UH\Zþ’*$E,Št\0ÌÇ’Ñ!\nw‡¬ÖÍÂÆ`‹Å’Ó´‰òZ¶ˆÍí±pÅ¿«~Ž~ôó{é²w½ŽŒÚ°Š®þø;è©í�sI\\zC<G.÷Ö>G™ÍqÌ]tÛ÷¾Ê‹Uq\"—\rwŒnÜI²…Í}Òá=A¸a¾îüŽ$ÎÅ÷O×óäsC	jÓÊž	ö~ÙSX	º?w0ÜnRW÷!³©”pPÁœA©¹Ç¤�>o˜#½ÓÍÔ=×D­•Ô8VL…BËf6î¢¢ö4Öä/ìzŠnúÎ—éµo:_šV\r¦ÝCouìzÕé4V5ÒTBÈ+/<›¾xÓ\'è·y„ò{à\ZÄÆNhba2hÉšnÀ7p§ú7³¤I`@Ò}Ï*yŽ²|ÏÙ( &HûViÞ4o§¼fq‘\ZÅbÚGu]9”+ë#¿{ˆ>ö…wÓšóV�}Ý?H[’Ÿ¡Ê@e6%ò­9½Ehý®DÞ`¥\nÛ›á^­ÚaDD‡líw´nl(5Ü§§Y’ àÞÉ×¿n¸H(»&êšna¸±GÜh>�S˜$ìÇSrtÎ˜$î¥Zð,#åÕ\r¸»*G›&8ØG~4<ÑÅ�ñá!�ÇMÊ³uä›­¢ÖérNYMm_¸y\'ç[À%”_›LOüõô±Ï¿—ÎÙàL@=¦¼è±<Ü-|c£Îv¼)jSªj1/{ûék|†~ŸðJ®ÛF…])TÚ\'L¡ÎÝ¼éÃ	Nm�3>jhätØÏþmF«çž <-Ì›gÅÿß\"€|�²›7Q~›\0ºe•¶¥Q¥/�ª:\niOVýÇ/LŸ¾þôºK^kÆ\n®;ÕüŸ£=¹[ùß\"€“#lxø™ÓÚ¶Ê»<*b�|óþ¯1¬²>r�ž%‘n¸£$Üñéù½ó`:	ó+ëÅ°¹½p®@Òš·ð{ævî¤Ì–ÞÓ È¤e´‚áîži•}KT¡\"\'KM÷+à(+PYÚ8Ëð§á>^ÀW-å	.9›µàóÀ`¸1^Â†»m¦ŒêFsXÓd·¸\ZÅºi\'•úÒ¨¤5™sž¥‡v/]ñîKLÈžÛŒEHs%ŠµxŒì¡ås1„J­…¼øÊ×ÐG®ÿ\0ÝòÀ×è×{”’Š·SiG—€Á,*õ§s~¢eð»vHÉ»ùaÊäPŠ„M^(4N¡ØtV±®\'ŸJ›³hKÒ³\\Eó•[¾JïýÈéU¯­œ]s)–®üÀ¥ôË?ü„’ó·‹�Ç‹7½6Ž-p)¦wl±àVf‰‚;jpgµ�pc_¢÷7¸KAkC¹@kçuì¥Ú¡BjÃ¡µ{§Û¸òöö€ÐÜCj¢‚+xcÁ�È¤„ûè\nà>¶ÜŽi‚UdÃ�ƒÁAî¡I?wÉG¶àîš©#ÿL5uÌ”¸ó8‡·hD­à*TaÙ\\që®>ÿ;úÎƒ_§÷\\óOtÖyÖÀUeªÀ¾vÍ9&›PŽÿS?Ær´úœ(ºômÑÇ?{-Ýv×�<ÏòÏü7ýeÓ´9ñiÚ™ö<e%Pqm\n•5§³¤–î äâxJÌÞHÏ%>Éû‚Çžú=ðÿî¦ÏçéªkÞE^}®3F-Â‹Þ|)}î†ëé™íOPCw	•µe\n<‘íj,dDR“[^û€­yF«Ðd\r{ÜÿjzF-ØiêÌƒ›ãðXÁ¿Ý!=Q¥ÝéÔ| Rl&ë\rÜâ\0l8!†TÈ]zIö¸a%À,yñà>¶8Ü0îyS©\\‚ØÕâ °™Rî@tË7Ú{º–o™(å¨l¨ÄE�‹\'­ao²*:áMIá^\\òŸéG�ÞKŸøÂ5ôº‹/�­b#œ\n{±¹äyå*ïÅkW¯³Z¸­2ÅµèD{ÑÅ¯§·¾íRzçÿDï}ßô‘�¾—>ýÙ¦ëoø}ù«Ÿ¦¾ò)úÔõŸ§�~æ“tõµ¢·½ûJzóï KÞþf:÷Õ_»$!ç½áúôW¯cïÏ¾üD®0)lIw„½<ag·ÆS¾?‘½8ií›ÜâÂ· ëwÓ­÷}}Q¸£T¯E¸îÜÆÓnl²\0µöm·ï”&™P^ØHvNÕñFÁ>tš‚9 WÞ0ÜRkëžÜºÛ‚´¹™aO/:Üî`í=büÝpë +³{RjðÀT=ù¦ªÅÆ²ˆóxƒ%ì2Ž`uÆSI¥Öod·^q§°Çý)l®ì-ÜJÿõÇ‡é_¿õEºâýÿD^µÎô&4®Dz­úYGRlôj§G¡š5¡.E„îÃ\'+Ìáz\\+E˜>±ëÔ¼y§ý|×0}¾pËuôØ_B5Û©(�…ï¥}µ[¹hw¨´¦Mì–Ì6½6I²;vˆ…¼SÀ�äÀ)AŽX\nîUgÜˆö¶Æs,�£’Ba5—²­\r¸Ápl¸§z9[ÃíhíYvAK°�¸�CyŒBö„]nhoY™3Jûg¹+>FK³S~BÎ‹ädôÉfj«¡¶ñ\n™ÀïÛ-nÏÒ>KiÝH©m›øâtïä<ˆ”úÍbã™ «¸»³© q/=÷8ýÇcÿNÿúíëéšO¾—þñ’óåüÅèU.Ÿ¹6Mà?^³nµ“\'íw2… ÇEöYÁ0ª5<‹œg@F:gÍ±tÉo ëoý4ÝÿÈ�ôôÎÿ¡”ª-r3\'hzÓBCÇ³O<«3�öÕ¿@©b#\n/Fzë”Û½]|¿,¸Å&³>™nûîMîˆã„;át„»=Þ¤3àÚqDÒ·�J»2�­�Md—j]¬+ÝQÀþí¹ae’Œ\Z¸6š±j­}Ráv[ÑJÀ=zp˜§xaü&Ã¢‰8r¸=-JøÅí¨ã@-UäóíŠÝD>™­—Ú±™ÝtûÚ¥_º w7Ûª©ÂdI­ÛÊ¹ÀH•¬ðgSNÍ^ŠÏxž~óÌ/è¡ŸßGwüûmtÃ­Ÿ§«ÿù*ºäò7ÒÙ¯Z÷lzÕö×�²ž·GŠçÎ{u4½ù�¯¡kþåJúÚ­Ÿ¥{~x=òÄé/ñ�SrYW¾TæÈqû-ìÙÅ�£=\rÏˆcw¢Þìy�¿=¿w»0Gž—ÿV¹ÙB²<(u)îèÐpGYpG�!pÛ¹:™Mñ¬µak×ö’ºÑnØý§´6ÀF|Ä÷ì‘I£µ·ÖÚGéøÁ^&ÜGƒ†ÜÐÞ·f˜†¦Äærzˆ!ïÞ3ÖÉ ØûÆ¨õ@9’Y‹¥¶n¡œ¤¦ÆI_s÷6ö?kÈ9#O@„\rh®¸�#8Ohq®¯RmW1÷Ù[�@ÏŠ�âŸ¶þžýÃÏèáÇ~Hüì>úÎ¾M·ï6úÜ×?K_ºùKB¾L_ºéºá_¡¯Üüuºñö›é›÷ÜAßyà^úÙ¯n§\'þò#úÛ¶_PÜ¾\'(»rUûR©ÚŸF•¨xiOâ ô¦mìZÄâƒ6FÊ-(‚B{Ì¹½ñ”ÒþœØDþ�²»6‰;Ó³{FÛV¾Ug5%1Ü·÷æ‚{õ†Ónì1xÕ¾‹áFþº‚¡?y÷\\7\\Ò³p´9¸rGðFxÂpU\r7À~qà^\"Ê£!ÇÈ.`P…Ñ”~vnÒ•%ˆÛ�Î5Ñ¦	¯ZažtNÔr¿f€šY/l³ÆTäÛÃö)BÉ¸•CÓAR;6² ÑFí—æ\0MGœ\n¥Ç³9 K¬JzöQy*ÿ„m\'¹n“°‡7RRõó,xŽ!m‘¹pQö%±ä÷ìæ<ñœ@\"¿/ÞŸƒß‘<•Ö¶Íd\nâw<‡×L˜[åS°ðßq¶Ïê�9)Yâ3s\ZvIo	Ú>GE:Ãœ–ò–ÀôŠhŠK}žcœ7ƒ”·N-8Qq³xŽÔúã(³s›Ë;É±Ò]qGãsÚ\"Q •ÁöOÊ±ÙÐØh0�»ùàx�ÐØ½BcÒØ´ÐØÓûifVl\"ç&èàü:<CGÄF0á²ÜPû‹\n7fä\0ðñƒC!©l¸‘ó�/‹	ZÈ·@á4rA»¼½ÃN•)œ2ŒžÞ±IA½Å„Ï‚[nbÜi­Vš+rŸÙKÃ.ªD—p¤Ïs±ƒóÁ—	·�¨å‚{ë™·¥•ù\\û¶J���¯Ã©	òß£0ÿAhmmZ1â|RN+Öp÷Ãe<Ñ+4vŸn€=wpòÔÂ-?Ìpcàª†{b~ØÑÞS½A›Jþ‚Bs¦\Z…yRÃ“P\r�`\n\0wßæ¶š“	 !\Zn+÷Ã\0ŽÛ¢é°ÐÌ\0W—<áw™¡o.¤Lê‘ßìR‰Gø7�¯aÿ½<¸·Ÿ4¸Wœ¦êÜ¾­!á–ßm;{Ip~u’\n£F‹\\IRNo\r÷\07Uå–i3cnÔê¢¤#k4Ø/9ÜÜÏDÀ­\'œ¡®r)í\r¸»¦›Ø5Ø~ ‚»uìcÓ$³u›¹âä!<.5ø67Ø�êDwÄ¹·FhÜõ	×·LüŽçðý¼}1½U+2é\'Î°þÝI\nú{…;XÑà:È¬Ë`“$ÛJm…«Ý·:&«8I\nps¨]Á\roÚð¤£µ\'gFhjö€Kk£f×ÖÚÇ3PuÅpëâaÖÞ‡gpìru\'*­½àpØÃïÍü©Ü?YÇ‘Ð¨Ñ<w´VÕ\0qD¯Ã	‚¸ÄÊÚƒàÿÀ½ÈÑ1©÷9}!õë^€½ðz5·þ÷6ÜökpÇÁÍ¥l*�XÃ-Kò¸aŽ`Ã�&JH”ëœ®a“p#hcÜØDNõ­\r°ÑÉ`ƒ#ðÄƒTÜ˜}z<é­\'·¹»2žGg‹].àÖ}Mž7;S]R{3à-F{c\0(ú:#ç#«9‘W>„µ@»„6-kq€î\\ŸxNÂ¿mÙÊ0sˆ/�×kÐÙ¿nÁ©Ÿ[ìe{¼p[€¸á>máŽóÀí>Ç¶­íÒÚ­qì×F5~ÝþÖÚˆHÂC­Ý;Õ¡æLv±ÖÞ?%µ6‚Ósã²\'ŽÛ«µ�7wû„àvƒ~Dö4mæØñÀá«äd*8üÝZ{sìœ•í\rí�ie˜™Â%ZÂþÆN~p„oe9WœS	c®|õ‰·ó–½9Ì¶�¬5÷BáÞÿ\nú�aI¸%wýžÓî¸eÃÍf\\‡ÚãÀÎw#Ù×1—Ú\'*l­µu‚”-M­µ\'”ÖF73€¬µWöqÃMv_5«rþÈ”¬¯TáxÝæ__ªo2À€÷˜°|³2O\Z¸ôÚ5v(|E>\0G]\"\0gUåº}›Ô‚ŸÞº%(;\rë9ª<L?³~¿kY®yrbpo?ÃáŽÒØìeR9BHCéÌL™C\"ƒ6|�yz‚�µöÀd¯c’L�\Z“DÃ<ÛýÀ­5·†‚áó§�aThïaþ2R{KÀyã6…Ì°éVËâd ‡u™?‹òš÷É(^«Ì…FàA®—­âLY™£ÁCgªiY2ñbA\\p\'º\0×·~Ø¥Ùu»éönTefÑË‡;ZÂ½5ùYNÐâ ´jÇ+®Àa Û¶±\rí9oºª�÷EØ#‰…ZàÛÃ�v1Š°c¢†zçÄæq¶En$Ùý×a´¶ìé.µöøÌ›$€f	œØÃÁ\ZXìS7\"–ÓÇ[8¾¾Œ¶7>Õi\0‡\rŽ�y8­�ö\r¹M{)»y›*Ü£ÄrÕ9½D$Üðçúâ–î«q’›Òð1©>Rv)I”@hm‡.TBsëÁ“Êè¨ã†{[Ês|—ãÔaœ‡ÎM/BS�xÓ·…7Œ®>/Ò´ã&;ˆ´É¼|Y™ÊS`^²O{ZVÚp‚gÿXk#b\r ð\0¶m’p�¤ûD\n€OšY¢ÇhB¸ù\0\np´žÅ—À—Á—Òù&pižh…j&š¸˜Ó®�:ŠÛ.o\n;÷±äà‹²•anØ]¡ K^¼%:2­Xüò3àSÇ‚td§ô³«×\n|{¹Jþ[|CÁáôjY&Ü0KPIŽÖeÜ+°…«^V\"HÏÕ®TÝ„HïKxavê–m{8ÄŽEŠÞpç¶(å»/²þŒëO\\[”�áz³o[\\ÿ °çu÷Ö9Øn¸Wþ8!¸õŒx{MTÆ Vâ,;âKÀ¶’æÉ ¯Þþ)Ç<a{L	\Zú@‹c„4Æö¡rŠPX\nM¡!ÁEÈ÷ïpIA qÉbKÁ™·Baü	!àÞ­àÞ¹ÜË¯ÄÜ¨¡Ä¹A“I\r·>?\'*6ÜÁç1Ñú^I|MPÑ„@ÈòÏÔòµã}”Êüã|íéne’ö²‚ã�‚‰)eŠ8ýHrä;T®ö‰äo¯n¸rÙ˜�¥÷äœÔÞ©\r¸M¶ šf\n;¬%i“ÒEˆ.ûÐâèiÑ6ZÍ�‰ �°¹FêtA�\'šä‚.T‹¯òRÁ\r0rê÷®îb_*kn˜%ÁíM3XT¬sá(yÜ€&ö@ÐØìéËåšHx¼ºg\Z•“@n u1´6®·ÖÚà@Ã\r­­áÆ¼Ó… >epK9là¶ÇÁOœ_FØÞ³c42µŸ³ñEíígû»oÚ/KÓÆ›YôLÁ/ç>ˆ`ÂŠ[¡Éé†Yw9Í·ºŒ—œ¸“<pïfSk¥p£X¡¤3Í÷	}ç�óq\0³ËlÙÍpãû\0ìŠÞj)çðº´±›ŒÆöš#¸[#×Ÿg—\nE§gÜ¸L’Eà¶c,§nÛßfÌÈ¼ÑÞøÁq©½gdÎ÷À´£½Ùÿ=íg°¹%„Xý}3-œ=Ø8$GÑ¡5Âº\Zn)Üµ‹¥ °3$ÜÇÝ¦}µW hÛ\0‘ï‡¶¾û”È¿ù�Ýl¯¢ÅAnÃ¾Á��±‰]§Ú,ÉïÞ¶âÍ¶Rà…èß¥dŸwT\Zw¥ñ,ÐÆÑR[ÚÙ�ÆŸÍ>m·6Ip½1N}üà(ƒ­Ç€ÈÑ{³l’,÷I·¹Ý·íA‘…Äù`•phoÎù«xPhoî€ÑÜÀ­£˜¾‰*“��.¡‰¹n¤^Ò+¥¨[�yœý´ƒD-–TAäûíåœu){YÐ¿?¡ùò\Z“éÛÊ|n9\"ûøàÞ‘¹YÞÕÄg¡µraïö�\rå�ûpŸîÝr!\ZIâE‰…Š9 �Ú—=ÓÀ`#\n‰<ý¾i¹‡â´k˜Ø}427Ä`CÁéáMÚ$y	á^áã¨8¸#GéèaD.�ÐáÃ‡éÐ¡Câ‹Ì:\Z\\Ø[¸=éù•:±ªO%Vqþ‰\Z¼É°ÃMx �Ú†+¹\0cDp;F.864E“¥ÈŸìÒ>z²�¾À\ZvÙ >´hOXºÐ~\'ƒÎÓü{�ècàç;„lN“ý¹£4ÐÑ®!«¶¬²Ç†ÄÈvjh>�É\n˜3£\'+`Va÷>–‚.÷±aò�ó»‚Þš|€ã*w?LEƒÀÜAŽ=O(ç9¿9‰Ïûþ*¹/:ÐÂƒš$ÈNeåŠ+äkOØ§�’C€=1?FS‡&Ù=,ÁFÜÝq§‡Ò”r2+‡û˜8ç|KÀšã•Š/Æ¾o¡½�Ð|·x»�›sÜp¢ã`	ÏVÇ¸7œp¸¡Ð+PÃ-/àÂÚÈÜ~¡eåpïd)\n„†Âóa„Í]Ò’Nß†Y²¸#<pGmˆ0pÃ\\“3qv2ÐhÚP“l¸½#>Šù¹]âçn3ò…ÚPÈ»Ç¼KÄ! hØu+®O×˜ÔÒ\0ºk¬ÃÜ…±ŸÒæˆÖÚ“óÒ‘2Ø¨ì:³àÖ€ëÒ4¸¶¿ñå°z¹„ê1ˆöÇèYÀÑÃ�ÛpÃƒ\"oàúËÖ‘\nª(0€Cƒp\\3®ÛÝ6O\\·ëî`YjæËRpuïbÑŽl13|ð7ŽÙ‚›^.ÜÑnž‰ãË’{±©Ü^Mí=n\r¸YtØzH�ü6w	f[ŠsŠ|{œoì`&rN�ªƒÔ‘G	·�7�ØKAcÃ‘›H©¹7šë8m\ZŽªbß3\rîcV¿±4mûÛn¬\08JÓ4ÜÜb\\š(€[G1Ñ\"\'Y\'Z!—ÁÑà)Ž*A—\Zs¯{¡)^˜�Æ²äœÆ%æT2Ü»-¸S”ì“š‘§›%Á­5wðLœ…àŽ\n÷nØ!ghZw[ôyÃOlÚµ9‚q×µ}pHà\0\0ßIDATyìµB8{áñ[æË¸Ž@ºÁ6¶ö¼œH¦á6ýGÌ^îƒÛ.I“í æ]½N¼€Ûp;Ò!M¶*÷ pxR0)+ØáØlêÉ½\Zp£=»“W<ÍkEp+ÀƒàVÝ´¢€[Of¸£\"•ÍÍs(K;³¹ºÞ�¢ÞÝÓÃ:î@°˜Í9¦ŒiÀÕ`c¸-rEpž}ãul\Z\"›¶6Ìi~HW_ÿD�‰_\0nìýÆÞ–ÝZ�¶hÞBß3Ò,�h¸e;ˆYþ¢ÜÌÇžÎ`\rŽ‚‰ÂÓÑÐÚVÙà²±f³’F“Iˆ.ý5}¬ÅÊ›œ‘Î)F“»€öì)+²Ú³‡Å,šj‰œÑÈŸ/î2¥­îˆ%áVƒ Ü1çÅðH’r>Ã�\rtqo’Y€�¿v=›q�ŽI‡ü˜ ˜W?THM#%œkoˆÜóÔ«ì¾vYÉ×©›ÁFôö5\0ÇOn®#®+ƒM7ýþ4ÜÜ@žN2Í\'Û,ñš&º¡�îk÷øÆ	±!ß¯ìpÛ‹\"o1�ËZÌ:a‡KÈqaÐÄGŽaNS³ÈíÙå©ÁÒ›Ì}³¥,1~©Ùò}{ùýä{§óq@ältë3Dè\'x\\�Q²í[4Æ¥ðOGÜ¦‰‚;Zjî=y	T( rñ}¡±Kû÷…þ~FœãÑçG�ÊWödp(�¡ 4p^eÕz‹ñŽp±É8J&}•4Sƒ*!Jj°�`O™P-ˆeÿoâcÇŽ�Ùp³‡PÁ-›úÌ›ßèM¡Û £zÇ®¿„ =š’÷*¿©œ4Ûb\n�‘.‹V¸ àbÀŽ$}xÆ¸/è)!-–W&â–éMcXp|,½üœ–Š€8¦ö,ºëÁ[äÄ¶ˆHŠA÷ZÜÑ¡´·‚;)\'Uvr¤\Z»|ÐY¼¶†¼úûëc‚÷‚sSÙ•AÕ=YTÓ›MÍÃœö€�ÈêÓ`ëˆ£´¯Ñ(‚\"ß~“å‡è³\r¶œ9mµCÓ½G¤œ¹p/ð’+Ø5<JµcC —U<ýA½µ™‚�:vìniä‚ã–‘RÞlÂuS^\rìHl’´æÒ&€`ðúÓR—6\Z[Ã»”f/íÑö~šRÖ“ÁšSÂ�AUÝ™Â,É¢»ÿý›²íæn²æŽ²šñ¬rÁÍ›NÕCV÷ì‹¤�Êà5êM—K|v…ø.•jqÙšÚ�XX�LŽàÁ‚28oÆhÑçUçcË~#~£±‡\'$ØÓ#y†8¡õIÕ\nmNµBÓrø”€}ÒáÆCw¬Ò\Z\\w‹ÅÉ@ä\n\ZÜ\r¸œØ€`ÏLQþpî^?«-Í¬q Á±	j.£†�bªí-š)‡!/ï’Z r)™J£	éÏbø\0¢cÊ¤›¿¥^LÒÀrAe+‘Únñ98Ìµ?^¸uïÂèõQw¥_hî®¹púÓ�yÁýÕ÷âïÛ%EC�^å8/8?ð„ ‡£Ü46;¢Î«v÷õ[¦ˆ®�ØÈøÔ)¬\Zlø²¥W$Øg*ÜËßë)\r²™½óä‘ÿ=;�ªŒF“Û…Æ\0œsÁ\'ä­Qj%“2ìË�•È\rg\rßj1Ú†å�Yòà�Ñ]@ÜhÒé…VŽ×LWÃ{È÷Ë±$KÁ-¡Üå­ÙApë!®™8¸£$ÜûòwQU ˆ*»sÅûfÉÏV`×ˆ¿aj@p—@#Q5Î\nBZ‡*80†;žÙ¨[éÇÝlcËhc—jnj�måeC1!úlgùq\n+ò˜\"G<Ž�ip]ÜîI\r:\\>¹\0à:\\ß­\Zýø•øŒÈ¶­Æ/®‹°áäu}R“ã#”Ì\\ÀÐ±™²A·Á_è9¯ØpËÅ“k-¢,õš4*Úrèž‡n7pKoÉ2àF±‚0KR\n“¸O\"¾Ì­Ú�c;k°±ˆ ø7øÞÍeuÇH-G\ZåÞ¥Å‹\0hˆV\Z:âˆsÎG[¦®Bck°Ùá¿ƒÆâ´>brŽBÁÍ€/cvû)‡{±/pÌö}[pÛ€;\Z|’«4t&Ï©bc\rx—DP©³¶æÑ·WØ�¸�Hìi­årã`™r~wL	¨„&—¿/%€×wo¾Y8x`C©lÏ¥{¿ÿ-ÙHŸ5w´3or¸#¢,¸ó÷P] Tw¡x?nÔ°©Å]«e¨”ss0oÝ†š« p®Æ¥ÿÚu7d¯ˆß6Œ®ÔfX£ôÇÁá ¨Cõ¿�ÛØÈ™	7Yr,DKdå&T%j6ä6àZƒë€�StÜ%3§:ÙDé\Zo—‰=J4è¸°ÐäícÚ}XÎ·h\r:`Ð`h8\0ârEÂíhmøßYÜ¨3ÄOôi±áŽ	‚;Ò\Z�m�ÌVY�±Â,Ñp×vðqÖ\r¸�P7–P›°©;ÇjÕPÓ&G[ëä4ì_´³8wC¿±³u`ççŸ{ÅµÐ`ã:éÚY]&æ”Š-l‚�}ºÃ}<5ov[6Î?f·ˆ˜qA®[$#á\n\Z\'Y\rz]€÷Nà¸½rh%Öæ\\á£q6©4Í:v}Ádèð²À{\08\0‰W–‚»j ›s�f5/\ZnhWÜ\07BÝ÷ƒo»à^nããÖ“…Õ¼Ÿ´¼=TßUFõ=EÔÐ—/Ž=Ÿ�Q[\0Ý�ÈâX•XÐ2º›Zö‹i±‚1ŽùaR�Uä‘{g«P:<W:0Eƒkaƒ­kgáòµKÅ–´«ÿ^áöv®Ò…Æ\\‹©Š�5à²ÙÏ8‡reÈ~˜Ã»óâäÛm#de½®®ï4%lÝ¦g‹\n\05«bÖ†œƒA£eÔ¼¿„!€\ZÚåÂ�¦ôhJS;�Ï\r×‘Á\nn, Ü\\µ¸y:„ÐÜkÖGSFÁ^jê‹²¯Dhh˜…Ô>R&€® ÀD-uOÕË�[\r747ïE”+!tm4@OwËƒ™Nf“¹Øœ\"!Cér6$®…\röács,ÇµY<æ†™¤§9Ü+5k¬ÉÇŽª|”ƒœÿ+3\nUEýÜ¥Å÷óæfH÷Mô¸¤—7žÎæSo˜ Ñ]žµ¡‚M\nñ�Öñ¦«mXhõ�\njìƒ†Ð÷–\ZÁß^iì-fØ\Z…ÀÜ©�R;TÌ‚¾,¨ì¯ëÏûËéî¿­Æ“D˜ñØZ°¹”£$ÊhîØ³¢)·4ƒ:‡Z¨}°�:G›Èw ‘¡•Ù”:¢«Dm¸ÙüÀ†qÒï(ƒ¾iÀ`‘E½öèAaŠ\Z‘ÇÃS4ƒÎGÒÁ#‡iþè!Â¼\\9JìµŽ�×ßiwpÂÕAö�ê”Y¤OJ->ªrÃee}ÿd/K0Ü“šd®è\r(ûÊ›Œ@ÓñFT€Žj|€iª¢–ÁJ†¾©¿œÁ‡\0xÀ\riÏ¤¦¯ˆªúXX‹‹\r à®?¸e”RÂ�_žEÝ£>ò�ÈÜj¿î­li/Ø2t®L�©€Kú(Ãäç™.†zÿ,4¶0Eæ‡ØÈ™92­2ûæìCˆ[upîãü°\'\'Å2…L3œt@®µ¸NÞ@û\r¹Úlj�Š¶3¥·Àw2m‘­\nØmÍ®¡ðý€0kúKô¥~Ã@©ÑÞÐÚ,øÆ>±(º*éÞ‡îTsyŽî‚Š\\N5e`§:Ì„0—Œw¸à6™|ÆéaDNýlŸêa\Z?<* ãhm�²:ì�IYµ­‹0ÜÇ\rº;à£ŸµlqîM(´8ê3Gä0St€p$;&ŠËí¥\0×>_í/·ÅiÔlBÓþ�n©¡Î‘:,\0¸[!cÕ,Ø¼â¹ñz{#}ï‡w«)lÇa–»{õÙ1TX™§à–{§Ù¤ÏõÝ4Ô¶]\r˜h“{Ýï²­‘­	¨gŽN²èó­Áf7Ÿ\'•èè1:­§î�‰WG\ràö ©y�Y¨ÓgáMZ|L@Î€«ŽôºÚ¹é¦œ6Ò›â„ Mzïs®×¹BE\n/xh¦ôæµÑ¤�Â|ð·Ò?¾WG.·#Úæ^»~\rW˜Iqº¹‘þ>ŽøÝP»Š	úMÞ56è:÷Ú¤©‘ù!ŽÌº²úì”Uíî\rÃ½\\¸M^¸ÓZËxRÐÙ*”Oüð¸ËTä¸€ZpQ5äºg¸é](@ðÚá¶póô)i˜6pžÓ‹>Ôã-¦Š‹¡KË´NÕ•æÃýÃûŒÍ\r÷ràFˆ³Î]Ge5E<4	“âôBµƒZ¶é�ïì-ýÐ^¨±ŸÑÙ|ÎXj-ó&.!ýØ§—ÙqfÁm»‰¬H—îKÈ>qËeÈÍ7Ka{\\l:aªèM§×}¨/¸v#\"â©Ý‡ŽÑ-h?a¿®½/Z\0¶ióÚÞ£Ý\'äë�¡ºÿ÷š˜Áp»×¯î³×¯¥Òªbž+í=0©ïF]Æsìè~³¸5Ôœ–*îtpëéë<\Zã_T»™²PÄñ˜»õÙK>ÿû�Ûò}j!¥½íÞ„\Zt=ÙÁØâªË,Ü†è•a{VtH†’�h\'>`ñB¬ÅûœîA%¨âï³½JËk^¶Ò÷¾�3à9%.°CÃ\r?÷º³WSIe§�B{³�Ü�{PUÄh³CB=Â‚ ŒÎÑ�ÜýØgÍÕRŽpÅŒmônú��dîåÇ€Ž†ˆ^ñŽÅùºG¡­Éq•…Ò/®!×¢7žèûÝ Ïö²�W3 nÝ�Ëâ•�qŸd.rb¿•Ü¥\'çö�‰×G;é¶;¿!4±ÞPF†(1‹r�Æ–=L$ÜÐÜ\0p#¸28Þå2Á`’�ª.OXÜXäú<ÌÎOº›â˜†ïGƒ¢ŒËŠ,†á>^¸8‰6Üvtó¨“€ewšÕ-%lmÎ¹ãsã&gùÜ(hn€oã0[B™.ú§-²@Vµèïš;`D>�¢ŠžQp1\0ð÷ûè›ß¹Y&C)ÍT;©:NÙK¿¯�¦šÆj\Z›\Z¡¡}l7ó\"Åw˜•ß…Û—Í0]žt3@mRS­fï2/$8|¾Td1÷‰n(([³á¶%(+èº½„Öb\ZtÈØü~ž_�[¹-ÒN0âöÂô³Û‘k	19€g¾ô¸Do`um(—Ï\rwÒíwÜbªßav¸¡vd•%°¹W¯�¥î^?�Nî—ÅHdÂ]IÁÑ=ùÕÕPã<  Æ©©.°�,ì°æ>¹~ïÅÔ½Ý¸ÜL}P°ëê{y�ØqÑ¥fŸ6žm£{M-ˆˆÚ‚4PYrÕË3Ì¥sG×„¢?¯î[o¿ÉÀµlnIÎ[OS3ã,Øc h€s>æ§Œh ¥é!�6Ä#GŒ„2E–ãý8<$gÜ‹�P÷ëŽùâ˜-‡\\&/è:(¤½Zœ\ZÏQcÊØ³†M¡Å¥*Ñ@öfÑ¯�£èþ\0}íÆTÌ`¸½•ï:y\nšûÂ‹ÞÀ&ØÌÜ$¡ƒ<4I7r·=ºx€Ýx!Í\n™Žü÷øXuÆƒ·G{(¬¶-y¼ tnù†(œò›Û¢‹\'´`H‘-€2) �DùÕìpÐ�Ï\r² Vth¤‡>õÙë\\š{áŽS–‹PÀ}õûÞÃw¤éÙ	†MFáí€™áÛì°¡V!7=ôb�íxÙÃ½l“{™®Dos ©¹œâ\ZòPÅ^	¼-üQ%‚´?ú³HÙÏÿfx´—Þÿ¡÷(w„Ëæ†pé™�ÈH�¸¿ôåë9b¨íÉöâuÄkO/>º„„á>)p/òc¡wóúVkÛ—6èÆ6?æD>u®²]8±ô³ócJÆYŒR˜:>;{d\\˜>£üo\0÷åW¾•á´€[m‹†:*Ê‰PÞy×\\¨­\r»ZöfÇ�>â�ÛÓ¶ìØñÁ-#Å8P(¡|Ilò…\Zô×°<x�G«eü­×�ùùyš��åÿ�ÿ7==mÞ?ñ\ZÞƒ{‘«	¸Zôde-ðÉŒÊãûé¢‹_/‹b…æŽÔ�G¸6�¶IC±±±ôÈ#�˜c°¿ÓR›Ä—óãïnû133ãö¹(°¯†ÀjÐõ‚ÐðãÿÛ\0ñ¿Wë ’W\0<O˜q>19J¯8½„\ZmŒ#-wŸÇÑpGGGÓêÕ«)--Íô?Ççêc÷Þ™ÂPÿ=m(—ŠY]?´æõÐÚ™›y\n §¦¦\\Ïkè�;Áá ±]“ð-ãŽŸ}\\U:¦Dkj˜ ZlÈñúYg�E£££f1Ûß)¬­_Æpk-ì5O4¼Z[kMhCî}Ø¦�³Ž„”PQÕêêJKSk±òK<Ú Cs¿ÿýïú>úxÂP¿Œá¶A°¥5´ø]?RSSé/ùýìg?£„„ªªªr¬ÿ-4¾\r½|}q¸u 	²o_’;òˆæ<jÓ¨MÀl­ý§?ýÉ|ï]\'öËn½ñÒ�¹¹9þ™••EwÝu½ë]ï¢×¾öµ~~ðƒ¤\'žx‚�€k!xíÜåØüÚdÀ{<õÔSüØ$ÚG\r7ÀÆkÚ�\\|ñÅ444d>Wƒ¼˜i~¼Œln\rƒ­­›ššèª«®rÁ°´&=çœsèÆo4›N{�hÈ–7<,wß}·Ù$zCìúyãþ€c#ùàƒ†ü^ú»„5÷ËnÛã¡Ÿ˜˜ [o½ÕÉºS`ÛPéç~ùË_ºÞP-Wkkè°(\0÷Ç?þq×BÒŸgÛÛöë6l æææ ½ÃBŸ~¼L7”¶Çž‡õë×Ë¤¤¨(ã­Ðn7\rž{å+_IÝÝÝµÞÀÙfÁáÃÚTq‚%GŽ32?˜ÎÎ¤sÏ}…Ì\'Að&*†E›#\Zt[“ÿä\'?	ƒ†{é\r¥6)ð³¶¶Öhh{Cgkn\r\Z(?üpH“@ûÍÁž†<ø³åÏÊÊj«³T$­Y³Îälëã€`	Ø¿ò•¯ÐÈÈHî0ÜKÃm»û\Z\Z\ZÌ­Íš5¬%±­±˜¶ƒ/¿ür\Z0�Ûmˆßm[ÞŽŒÚ‹àŽ;î`ˆ×­[ç²«½‹ríµ×RKKKØ¦Ã½øC›\"ÜÍJ™(ÃÃÃ.@\rÀmo…öfàùçŸ>¤-ï½+xýãx½­­�ÍÛ+â�Jb‘á9lr\n\nŒI†;÷²<&\Zòññq³¹Ó\n½±ÔZ[›$\Zôk®¹ÆÜðÀÑÞhÚ¯Ù¦lóG}Ô,\Zýþøé]HßøÆ7Ø=©ŠwÓ~„ái–hÈ\r€Û¾};­]»Ö\0�m-Øðº’™™/ ·?ÃëQ)++£/¼Ðõ¾¶ûÏ½úÕ¯¦‡zˆ5¼^0Zk‡óEÂp/	¶7K~ë[n¹Å˜f;[Oƒþ…/|�ýÞ\Z@¼—öƒk[Ü6K|>}ìcs½‡^LZc_}õÕ´{÷nã}Áûé÷ÄÏ0Üa¸—|hM«sIðhmm¥Ûn»ÍØÃÚ÷¬·}Ð:°ó×¿þÕ¼\'´6Âê±ÏÏÏ™ß!­­ÍtóÍ7qˆ}õêKSËó©O}‚~ó›ÿ¡ŠŠ\nW–¡ýÞún†;÷’v··HÀ ´½yófúä\'?éÜ6tPrÑEÑïÿ{\Z¹¹Äã…^`�­MýžpóAûÿùÏ¦úúú Ä§Óµ[jî3no\Z,494èã�?N×]w½þõ¯7.:ýÓ6+^õªWÑõ×_ÏÑË\'Ÿ|’aúé§é§?ý)o\nñÿõ¦‹çþûï§gŸ}–ï¡\\‡a¸Ãp¯Èö•Ô¯í[ý@€\'99™~õ«_Ñí·ßNŸùÌgè�ï|\']pÁ.û\\Ëyç�G¯xÅ+è²Ë.cÌHÄº÷Þ{éw¿ûÛÓxÏ¸ì�Ì†;÷‹\n·\ZíÖ³ó6ð€?Ÿ¼¼<Ú¹s\'mÜ¸‘55 …–ì±Çè�ü#kîçž{ŽÓe÷ìÙC•••ìn´aÖŸ­ïË6wîãÅ‹·ŽÒkCë¢;Êö�/ôYúýìYû3mó$wî“j“ÛÀj0m³Å[®æýÛâ„ZúÿØ^‘0Üa¸OÚÃš]?éÝŒÚef¶™á½„‚Ò¹ísÃ†û%³Ëm ½ee^ˆm-íý¹ØïaÍ†;ü?Âp‡áGîð#wø~„á?Â�ÓôñÿB«0ÄRíÞ©\0\0\0\0IEND®B`‚'),('EMPRESA_ING_MANUAL','456',NULL),('EMPRESA_LOC','SANTA FE',NULL),('EMPRESA_LOC_BAL','MEDANOS',NULL),('EMPRESA_NOMBRE','CJBALANZAS.COM',NULL),('EMPRESA_NOMBRE_BAL','QSAND',NULL),('EMPRESA_PROV','SANTA FE',NULL),('EMPRESA_PROV_BAL','ENTRE RIOS',NULL),('EMPRESA_RESTORE','C:\\Users\\Jorge\\Pictures\\CJJL.jpg',NULL),('EMPRESA_TEL','3413270242',NULL),('EMPRESA_TEL_BAL','3455 335566',NULL),('EMPRESA_TRANSACCION','17',NULL),('EXPORT_PATH','C:\\SistemaDePesaje\\csv',NULL),('NUM_BALANZAS','1',NULL),('PASS_WINDOWS','',NULL),('REMITO_PAGE_FORMAT','A4',NULL),('TICKET_ETIQUETADORA','REMITO',NULL),('USER_WINDOWS','',NULL),('VALIDACION_ENTRADA','1,7,8,9,10',NULL),('VALIDACION_SALIDA','',NULL);
/*!40000 ALTER TABLE `parametros_globales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patentes`
--

DROP TABLE IF EXISTS `patentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patentes` (
  `codigo` varchar(45) NOT NULL,
  `tara` double NOT NULL,
  `date_update` datetime NOT NULL,
  `dias` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patentes`
--

LOCK TABLES `patentes` WRITE;
/*!40000 ALTER TABLE `patentes` DISABLE KEYS */;
INSERT INTO `patentes` VALUES ('AAA111',0,'2021-03-31 12:58:58',0,NULL),('ASD234',0,'2021-06-30 13:23:04',0,NULL),('ASD444',0,'2021-04-09 15:51:29',0,NULL),('KKK333',0,'2021-06-16 11:23:40',0,NULL),('MMM234',0,'2021-06-18 15:12:25',0,NULL),('MNB234',0,'2021-06-16 11:22:52',0,NULL),('MNK111',0,'2021-06-30 13:24:29',0,NULL),('NNN111',0,'2021-06-28 17:37:09',0,NULL),('OOO111',0,'2021-07-02 18:32:19',0,NULL),('UTY222',0,'2021-07-03 09:13:42',0,NULL),('UYI111',0,'2021-06-18 17:55:53',0,NULL),('YYY222',0,'2021-07-01 22:09:41',0,NULL);
/*!40000 ALTER TABLE `patentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedencias`
--

DROP TABLE IF EXISTS `procedencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedencias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedencias`
--

LOCK TABLES `procedencias` WRITE;
/*!40000 ALTER TABLE `procedencias` DISABLE KEYS */;
INSERT INTO `procedencias` VALUES (2,'POIO');
/*!40000 ALTER TABLE `procedencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (5,'HH',NULL,NULL,NULL),(6,'PRDO2',NULL,NULL,NULL),(7,'QER',NULL,NULL,NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remito_field`
--

DROP TABLE IF EXISTS `remito_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remito_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dato` varchar(100) NOT NULL,
  `pos_x` varchar(10) DEFAULT NULL,
  `pos_y` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remito_field`
--

LOCK TABLES `remito_field` WRITE;
/*!40000 ALTER TABLE `remito_field` DISABLE KEYS */;
INSERT INTO `remito_field` VALUES (1,'Acoplado','1.0','2.0'),(2,'Conductor','1.0','3.0'),(3,'Cuit','1.0','4.0'),(4,'DenominaciÃ³n','1.0','5.0'),(5,'Domicilio','1.0','6.0'),(6,'Localidad','1.0','7.0'),(7,'Peso Entrada','1.0','8.0'),(8,'Peso Neto','1.0','9.0'),(9,'Peso Salida','1.0','10.0'),(10,'Provincia','1.0','11.0'),(11,'Denominación','1.0','5.0');
/*!40000 ALTER TABLE `remito_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tara_id` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_reports_tara_idx` (`tara_id`),
  CONSTRAINT `FK_reports_tara` FOREIGN KEY (`tara_id`) REFERENCES `taras` (`idtaras`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,30,1),(2,32,2),(3,33,1),(4,34,2),(5,35,3),(6,36,1),(7,39,2),(8,40,2),(9,41,5),(10,37,1);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taras`
--

DROP TABLE IF EXISTS `taras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taras` (
  `idtaras` int(11) NOT NULL AUTO_INCREMENT,
  `transaccion` varchar(45) DEFAULT NULL,
  `fecha_entrada` datetime DEFAULT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `balanza` varchar(45) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_transporte` int(11) DEFAULT NULL,
  `id_procedencia` int(11) DEFAULT NULL,
  `id_imp_exp` int(11) DEFAULT NULL,
  `modalidad` varchar(45) DEFAULT NULL,
  `comprobante_nun1` varchar(45) DEFAULT NULL,
  `modoTara` varchar(45) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `conductor` varchar(255) DEFAULT NULL,
  `tipo_doc` varchar(3) DEFAULT NULL,
  `num_doc` varchar(45) DEFAULT NULL,
  `patente` varchar(45) DEFAULT NULL,
  `patente_aceptado` varchar(45) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `observacion_aduana` varchar(255) DEFAULT NULL,
  `contenedor_num` varchar(45) DEFAULT NULL,
  `peso_entrada` decimal(9,3) DEFAULT NULL,
  `peso_salida` decimal(9,3) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modoChasis` varchar(45) DEFAULT NULL,
  `id_ata` int(11) DEFAULT NULL,
  `contenedor` varchar(45) DEFAULT NULL,
  `manifiesto` varchar(45) DEFAULT NULL,
  `mercaderia` varchar(45) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtaras`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taras`
--

LOCK TABLES `taras` WRITE;
/*!40000 ALTER TABLE `taras` DISABLE KEYS */;
INSERT INTO `taras` VALUES (28,'1','2021-03-31 12:59:25','2021-04-09 15:49:09','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','AAA111',NULL,'',NULL,NULL,1256.000,34.000,'2021-03-31 15:59:25','COMPLETO',NULL,NULL,NULL,NULL,NULL),(29,'2','2021-04-09 15:51:31','2021-04-09 15:51:36','ING. MANUAL',6,4,4,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','ASD444','','',NULL,NULL,9568.000,4200.000,'2021-04-09 18:51:31','COMPLETO',NULL,NULL,NULL,'',''),(30,'3','2021-05-17 10:08:38',NULL,'ING. MANUAL',NULL,NULL,NULL,NULL,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','AAA111','','',NULL,NULL,123.000,NULL,'2021-05-17 13:08:39','COMPLETO',NULL,NULL,NULL,NULL,''),(31,'4','2021-06-16 11:23:10',NULL,'ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','MNB234','','',NULL,NULL,0.000,NULL,'2021-06-16 14:23:10','POR EJE',NULL,NULL,NULL,NULL,''),(32,'5','2021-06-16 11:24:32','2021-06-16 11:25:04','ING. MANUAL',6,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','KKK333','','',NULL,NULL,54500.000,14000.000,'2021-06-16 14:24:32','POR EJE',NULL,NULL,NULL,NULL,''),(33,'6','2021-06-18 15:12:51',NULL,'ING. MANUAL',6,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','MMM234','','',NULL,NULL,65000.000,NULL,'2021-06-18 18:12:51','POR EJE',NULL,NULL,NULL,NULL,''),(34,'7','2021-06-18 17:56:12','2021-06-20 22:59:42','QSAND',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','UYI111','','',NULL,NULL,60000.000,49.900,'2021-06-18 20:56:13','POR EJE',NULL,NULL,NULL,NULL,''),(35,'8','2021-06-20 22:59:14','2021-07-12 19:39:15','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','KKK333','','',NULL,NULL,50.100,0.000,'2021-06-21 01:59:14','POR EJE',NULL,NULL,NULL,'',''),(36,'9','2021-06-28 17:37:50','2021-06-28 17:38:00','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','NNN111','','',NULL,NULL,3000.000,20000.000,'2021-06-28 20:37:51','POR EJE',NULL,NULL,NULL,NULL,''),(37,'10','2021-06-30 13:23:30','2021-07-12 19:35:03','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','ASD234','','',NULL,NULL,0.000,0.000,'2021-06-30 16:23:31','POR EJE',NULL,NULL,NULL,NULL,''),(38,'11','2021-06-30 13:24:44',NULL,'ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','MNK111','','',NULL,NULL,15400.000,NULL,'2021-06-30 16:24:44','POR EJE',NULL,NULL,NULL,NULL,''),(39,'12','2021-07-01 22:09:59','2021-07-01 22:12:40','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','YYY222','','',NULL,NULL,10000.000,900.000,'2021-07-02 01:09:59','COMPLETO',NULL,NULL,NULL,NULL,''),(40,'13','2021-07-02 18:32:26','2021-07-02 18:32:32','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','OOO111','','',NULL,NULL,10000.000,50000.000,'2021-07-02 21:32:26','COMPLETO',NULL,NULL,NULL,NULL,''),(41,'14','2021-07-03 09:14:04','2021-07-14 20:03:07','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','UTY222','','',NULL,NULL,50100.000,0.000,'2021-07-03 12:14:05','POR EJE',NULL,NULL,NULL,NULL,''),(42,'15','2021-07-14 20:05:13','2021-07-14 20:06:45','ING. MANUAL',5,4,3,2,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'1231','2131','','',NULL,NULL,2568.000,1324.000,'2021-07-14 18:05:14','POR EJE',NULL,NULL,NULL,'',''),(43,'16','2021-07-14 20:07:43','2021-07-14 23:29:08','ING. MANUAL',5,4,4,2,NULL,'ADUANA','COMPRO','NORMAL','','',NULL,'1231','21','ACOPLADO','1','2','3',213.000,222.000,'2021-07-14 18:07:43','COMPLETO',NULL,'','','',''),(44,'17','2021-07-14 23:30:59','2021-07-14 23:31:02','ING. MANUAL',5,4,4,2,NULL,'ESTANDAR','','NORMAL',NULL,'LEONEL CONDUCTOR',NULL,'31631073','JDZ778','22ACO','',NULL,NULL,222.000,13333.000,'2021-07-14 21:30:59','COMPLETO',NULL,NULL,NULL,'','');
/*!40000 ALTER TABLE `taras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportes`
--

DROP TABLE IF EXISTS `transportes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportes`
--

LOCK TABLES `transportes` WRITE;
/*!40000 ALTER TABLE `transportes` DISABLE KEYS */;
INSERT INTO `transportes` VALUES (3,'Ã‘LO',NULL,NULL,NULL),(4,'TRANS',NULL,NULL,NULL);
/*!40000 ALTER TABLE `transportes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','123456',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versionado`
--

DROP TABLE IF EXISTS `versionado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versionado` (
  `idversionado` int(11) NOT NULL AUTO_INCREMENT,
  `numero_sql` int(11) NOT NULL,
  `update_sql` datetime DEFAULT NULL,
  PRIMARY KEY (`idversionado`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versionado`
--

LOCK TABLES `versionado` WRITE;
/*!40000 ALTER TABLE `versionado` DISABLE KEYS */;
INSERT INTO `versionado` VALUES (1,1,'2021-03-31 14:35:28'),(2,2,'2021-03-31 14:35:28'),(3,3,'2021-03-31 14:35:28'),(4,4,'2021-07-12 17:17:38'),(5,5,'2021-07-12 17:17:38'),(6,6,'2021-07-12 17:17:39'),(7,5,'2021-07-15 20:39:17');
/*!40000 ALTER TABLE `versionado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sist_pesada_prd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20 21:19:10
