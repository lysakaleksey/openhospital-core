#version 7-4-2006
#version 27-8-2006
#version 29-8-2006  EXAM,LABORATORY
#version 17-9-2006  ADMISSION, MENU , MENUITEM
#version 17-11-2006 OPD (new) SURGERY (deleted), DISEASE, EXAM, LABORATORY modified
#	DISEASE	(added DIS_OPD_INCLUDE, DIS_IPD_INCLUDE)
#	EXAM	(EXA_DESC from 50 to 100)
#	LABORATORY (added LAB_AGE, LAB_SEX, LAB_MATERIAL, LAB_EXAM_DATE, LAB_PAT_INOUT)
#version 17-6-2008
#	OPD (added OPD_REFERRAL_FROM, OPD_REFERRAL_TO, OPD_PAT_ID, OPD_PAT_FNAME, OPD_PAT_SNAME, OPD_PAT_NEXT_KIN, OPD_PAT_ADDR, OPD_PAT_CITY)
#version 10-12-2012
#	IMPORTANT MYSQL SETTINGS:
#   If you are using InnoDB tables, you should set this variable to 1 on all platforms 
#   to force names to be converted to lowercase.
#   lower_case_table_names = 1   


drop database if exists oh;
create database oh CHARACTER SET utf8;

use oh;


--
-- creazione TABELLE
--
CREATE TABLE ADMISSION  (
	ADM_ID  int NOT NULL auto_increment,
	ADM_IN  int NOT NULL default '0',
	ADM_TYPE  char(1) NOT NULL default 'N',
	ADM_WRD_ID_A  char(1) NOT NULL default '',
	ADM_YPROG  int NOT NULL default '0',
	ADM_PAT_ID  int NOT NULL default '0',
	ADM_DATE_ADM datetime NOT NULL,
	ADM_ADMT_ID_A_ADM  varchar(10) NOT NULL default '',
	ADM_FHU  varchar(50) default NULL,
	ADM_IN_DIS_ID_A  varchar(10) default NULL,
	ADM_OUT_DIS_ID_A  varchar(10) default NULL,
	ADM_OPE_ID_A  varchar(10) default NULL,
	ADM_DATE_OP datetime NULL ,			
	ADM_RESOP  varchar(10) default NULL,
	ADM_DATE_DIS  datetime default NULL,
	ADM_DIST_ID_A  varchar(10) default NULL,
	ADM_NOTE  text NULL,
	ADM_TRANS float NULL default 0,	
	ADM_PRG_DATE_VIS  datetime default NULL,
	ADM_PRG_PTT_ID_A  varchar(10) default NULL,
	ADM_PRG_DATE_DEL  datetime default NULL,
	ADM_PRG_DLT_ID_A  char(1) default NULL,
	ADM_PRG_DRT_ID_A  char(1) default NULL,
	ADM_PRG_WEIGHT  float default NULL,
	ADM_PRG_DATE_CTRL1  datetime default NULL,
	ADM_PRG_DATE_CTRL2  datetime default NULL,
	ADM_PRG_DATE_ABORT  datetime default NULL,
	ADM_LOCK  int NOT NULL default '0',
	ADM_DELETED  char(1) NOT NULL default 'N',
	PRIMARY KEY  ( ADM_ID )
) ENGINE=MyISAM;

CREATE TABLE ADMISSIONTYPE (
	ADMT_ID_A varchar (10)  NOT NULL ,
	ADMT_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( ADMT_ID_A )
) ENGINE=MyISAM;

CREATE TABLE DISCHARGETYPE (
	DIST_ID_A varchar (10)  NOT NULL ,
	DIST_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( DIST_ID_A )
) ENGINE=MyISAM;


CREATE TABLE DELIVERYRESULTTYPE (
	DRT_ID_A char (1)  NOT NULL ,
	DRT_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( DRT_ID_A )
) ENGINE=MyISAM;


CREATE TABLE DELIVERYTYPE (
	DLT_ID_A char (1)  NOT NULL ,
	DLT_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( DLT_ID_A )
) ENGINE=MyISAM;

CREATE TABLE DISEASE (
	DIS_ID_A varchar (10)  NOT NULL ,
	DIS_DESC varchar (100)  NOT NULL ,
	DIS_DCL_ID_A char (2)  NOT NULL ,
	DIS_LOCK int NOT NULL default 0,
	DIS_OPD_INCLUDE int(11) NOT NULL  default 0, 
	DIS_IPD_INCLUDE int(11) NOT NULL  default 0,
	PRIMARY KEY ( DIS_ID_A )
) ENGINE=MyISAM;

CREATE TABLE DISEASETYPE (
	DCL_ID_A char (2)  NOT NULL ,
	DCL_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( DCL_ID_A )
) ENGINE=MyISAM;

CREATE TABLE EXAM (
	EXA_ID_A varchar (10)  NOT NULL ,
	EXA_DESC varchar (100)  NOT NULL ,
	EXA_EXC_ID_A char (2)  NOT NULL ,
	EXA_PROC int NOT NULL,					
	EXA_DEFAULT varchar(50) ,				
	EXA_LOCK int NOT NULL default 0,
	PRIMARY KEY ( EXA_ID_A )
) ENGINE=MyISAM;

CREATE TABLE EXAMROW (
	EXR_ID int NOT NULL AUTO_INCREMENT,
	EXR_EXA_ID_A varchar (10)  NOT NULL ,
	EXR_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( EXR_ID )
) ENGINE=MyISAM;

CREATE TABLE EXAMTYPE (
	EXC_ID_A char (2)  NOT NULL ,
	EXC_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( EXC_ID_A )
) ENGINE=MyISAM;


CREATE TABLE HOSPITAL (
	HOS_ID_A varchar (10)  NOT NULL ,
	HOS_NAME varchar (255)  NOT NULL ,
	HOS_ADDR varchar (255)  NOT NULL ,
	HOS_CITY varchar (255)  NOT NULL ,
	HOS_TELE varchar (50)  NULL ,
	HOS_FAX varchar (50)  NULL ,
	HOS_EMAIL varchar (50)  NULL ,
	HOS_LOCK int NOT NULL default 0,
	PRIMARY KEY ( HOS_ID_A )
) ENGINE=MyISAM;

CREATE TABLE HELP(
	HL_ID int NOT NULL AUTO_INCREMENT,
	HL_MASK int NOT NULL,
	HL_FIELD int NOT NULL,
	HL_LANG char(2),
	HL_MSG varchar(255),
	PRIMARY KEY (HL_ID)
) ENGINE=MyISAM;


CREATE TABLE LABORATORY (
	LAB_ID int NOT NULL AUTO_INCREMENT ,
	LAB_EXA_ID_A varchar (10)  NOT NULL ,
	LAB_DATE datetime NOT NULL  , 
	LAB_RES varchar (50)  NOT NULL ,
	LAB_NOTE varchar (255) NULL ,
	LAB_PAT_ID int NULL,					
	LAB_PAT_NAME varchar(100) NULL ,		
	LAB_CROSS1 int NULL ,				
	LAB_CROSS2 int NULL ,
	LAB_CROSS3 int NULL ,
	LAB_CROSS4 int NULL ,
	LAB_CROSS5 int NULL ,
	LAB_CROSS6 int NULL ,
	LAB_CROSS7 int NULL ,
	LAB_CROSS8 int NULL ,
	LAB_CROSS9 int NULL ,
	LAB_CROSS10 int NULL ,
	LAB_CROSS11 int NULL ,
	LAB_CROSS12 int NULL ,
	LAB_CROSS13 int NULL ,	
	LAB_LOCK int NOT NULL default 0,		
	LAB_AGE int(11) NULL, 
	LAB_SEX char(1) NULL,
	LAB_MATERIAL varchar(25) NULL,
	LAB_EXAM_DATE date NULL,
	LAB_PAT_INOUT char(1) NULL,
	PRIMARY KEY ( LAB_ID )
) ENGINE=MyISAM;


CREATE TABLE LABORATORYROW (					
	LABR_ID int NOT NULL AUTO_INCREMENT ,
	LABR_LAB_ID int  NOT NULL ,
	LABR_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( LABR_ID )
) ENGINE=MyISAM;


CREATE TABLE LOG (
	LOG_ID int NOT NULL AUTO_INCREMENT ,
	LOG_TYPE int  NOT NULL ,
	LOG_CLASS varchar (100)  NULL ,
	LOG_TYME datetime NOT NULL,
	LOG_MESS varchar (255) NULL , 
	PRIMARY KEY ( LOG_ID )
) ENGINE=MyISAM;

CREATE TABLE MALNUTRITIONCONTROL (
	MLN_ID int NOT NULL AUTO_INCREMENT ,
	MLN_DATE_SUPP datetime NOT NULL ,		
	MNL_DATE_CONF datetime NULL ,
	MLN_ADM_ID int NOT NULL ,
	MLN_HEIGHT float NOT NULL ,
	MLN_WEIGHT float NOT NULL ,
	MLN_LOCK int NOT NULL default 0,
	PRIMARY KEY ( MLN_ID )
) ENGINE=MyISAM;


CREATE TABLE MEDICALDSR (
	MDSR_ID int NOT NULL AUTO_INCREMENT ,
	MDSR_MDSRT_ID_A char (1)  NOT NULL ,
	MDSR_DESC varchar (100)  NOT NULL ,
	MDSR_MIN_STOCK_QTI float NOT NULL default 0,
	MDSR_INI_STOCK_QTI float NOT NULL default 0,
	MDSR_IN_QTI float  NOT NULL default 0,
	MDSR_OUT_QTI float  NOT NULL default 0,
	MDSR_LOCK int NOT NULL default 0,
	UNIQUE INDEX ( MDSR_MDSRT_ID_A,MDSR_DESC) ,
	PRIMARY KEY (MDSR_ID )
) ENGINE=MyISAM;

CREATE TABLE MEDICALDSRLOT(
	LT_ID_A varchar(50) NOT NULL,
	LT_PREP_DATE datetime NOT NULL ,
	LT_DUE_DATE datetime NOT NULL ,
	LT_LOCK int NOT NULL default 0,
	PRIMARY KEY ( LT_ID_A )
) ENGINE=MyISAM;

CREATE TABLE MEDICALDSRSTOCKMOV (
	MMV_ID int NOT NULL AUTO_INCREMENT ,
	MMV_MDSR_ID int  NOT NULL ,
	MMV_WRD_ID_A char(1) NULL ,
	MMV_MMVT_ID_A varchar (10) NOT NULL ,
	MMV_LT_ID_A varchar (50)   NULL ,
	MMV_DATE datetime NOT NULL ,
	MMV_QTY float NOT NULL default 0,
	MMV_FROM varchar(30) NULL default 'JMS' ,
	MMV_LOCK int NOT NULL default 0,
	PRIMARY KEY ( MMV_ID )
) ENGINE=MyISAM;

CREATE TABLE MEDICALDSRSTOCKMOVTYPE (
	MMVT_ID_A varchar(10) NOT NULL ,
	MMVT_DESC varchar (50)  NOT NULL ,
	MMVT_TYPE char (2)  NOT NULL ,
	PRIMARY KEY ( MMVT_ID_A )
) ENGINE=MyISAM;

CREATE TABLE MEDICALDSRTYPE(
	MDSRT_ID_A char(1) NOT NULL,
	MDSRT_DESC varchar(30),
	PRIMARY KEY (MDSRT_ID_A)
) ENGINE=MyISAM;


CREATE TABLE OPD ( 
	OPD_ID        	  int(11) AUTO_INCREMENT NOT NULL,
	OPD_DATE      	  datetime NOT NULL,
	OPD_NEW_PAT   	  char(1) NOT NULL DEFAULT 'N',
	OPD_DATE_VIS  	  date NOT NULL,
	OPD_PROG_YEAR 	  int(11) NOT NULL,
	OPD_SEX       	  char(1) NOT NULL,
	OPD_AGE       	  int(11) NOT NULL DEFAULT 0,
	OPD_DIS_ID_A  	  varchar(10) NULL,
	OPD_DIS_ID_A_2	  varchar(10) NULL,	
	OPD_DIS_ID_A_3	  varchar(10) NULL,
	OPD_LOCK      	  int(11) NOT NULL DEFAULT '0',
	PRIMARY KEY(OPD_ID)
) ENGINE=MyISAM;

CREATE TABLE OPERATION (
	OPE_ID_A varchar (10)  NOT NULL ,
	OPE_OCL_ID_A char (2)  NOT NULL ,
	OPE_DESC varchar (50)  NOT NULL ,
	OPE_STAT int NOT NULL default 0,			
	OPE_LOCK int NOT NULL default 0,
	PRIMARY KEY ( OPE_ID_A )
) ENGINE=MyISAM;

CREATE TABLE OPERATIONTYPE (
	OCL_ID_A char (2)  NOT NULL ,
	OCL_DESC varchar (50)  NOT NULL ,
	OCL_TYPE varchar (20) NOT NULL default 'MAJOR', 
	PRIMARY KEY ( OCL_ID_A )
) ENGINE=MyISAM;


CREATE TABLE PATIENT (
	PAT_ID int NOT NULL AUTO_INCREMENT ,
	PAT_FNAME varchar(50) NOT NULL,
	PAT_SNAME varchar(50) NOT NULL,
	PAT_NAME varchar(100) NULL,
	PAT_AGE int NOT NULL ,
	PAT_SEX char (1)  NOT NULL ,
	PAT_ADDR varchar (50)  NULL ,
	PAT_CITY varchar (50)  NOT NULL ,
	PAT_NEXT_KIN varchar (50)  NULL ,
	PAT_TELE varchar (50)  NULL ,
	PAT_MOTH char (1)  NULL ,
	PAT_FATH char (1)  NULL ,
	PAT_LEDU char (1)  NULL ,
	PAT_ESTA char (1)  NULL ,
	PAT_PTOGE char (1)  NULL ,
	PAT_NOTE text NULL,
	PAT_DELETED char(1) NOT NULL default 'N',
	PAT_LOCK int NOT NULL default 0,
	PRIMARY KEY ( PAT_ID )
) ENGINE=MyISAM;

CREATE TABLE PATIENTVACCINE (
	PAV_ID int NOT NULL AUTO_INCREMENT ,
	PAV_YPROG int NOT NULL ,
	PAV_DATE datetime NOT NULL ,
	PAV_PAT_ID int NOT NULL ,
	PAV_VAC_ID_A varchar (10)  NOT NULL ,
	PAV_LOCK int NOT NULL default 0,
	PRIMARY KEY ( PAV_ID )
) ENGINE=MyISAM;


CREATE TABLE PREGNANTTREATMENTTYPE (
	PTT_ID_A varchar (10)  NOT NULL ,
	PTT_DESC varchar (50)  NOT NULL ,
	PRIMARY KEY ( PTT_ID_A )
) ENGINE=MyISAM;


CREATE TABLE  USER (
	US_ID_A varchar(50) NOT NULL DEFAULT '' ,
	US_UG_ID_A varchar(50) NOT NULL DEFAULT '' ,
	US_PASSWD varchar(50) NOT NULL DEFAULT '' ,
	US_DESC varchar(128) ,
	PRIMARY KEY (US_ID_A)
) ENGINE=MyISAM;

CREATE TABLE  USERGROUP (
	UG_ID_A varchar(50) NOT NULL DEFAULT '' ,
	UG_DESC varchar(128) ,
	PRIMARY KEY (UG_ID_A)
) ENGINE=MyISAM;


CREATE TABLE VACCINE (
	VAC_ID_A varchar (10)  NOT NULL ,
	VAC_DESC varchar (50)  NOT NULL ,
	VAC_PATI char (1)  NOT NULL ,
	VAC_LOCK int NOT NULL default 0,
	PRIMARY KEY ( VAC_ID_A )
) ENGINE=MyISAM;


CREATE TABLE VERSION (
	VER_MAJOR int  NOT NULL ,
	VER_MINOR int NOT  NULL ,
	VER_SOURCE LONGBLOB  NULL, 
	VER_DATE datetime NOT NULL,
	VER_CURRENT char (1) default 'N' NOT NULL,
	PRIMARY KEY ( VER_MAJOR,VER_MINOR )
) ENGINE=MyISAM;


CREATE TABLE WARD (
	WRD_ID_A char (1)  NOT NULL ,
	WRD_NAME varchar (50)  NOT NULL ,
	WRD_TELE varchar (50)  NULL ,
	WRD_FAX varchar (50)  NULL ,
	WRD_EMAIL varchar (50)  NULL ,
	WRD_NBEDS int NOT NULL ,
	WRD_NQUA_NURS int NOT NULL ,
	WRD_NDOC int NOT NULL ,
	WRD_LOCK int NOT NULL default 0,
	PRIMARY KEY ( WRD_ID_A )
) ENGINE=MyISAM;

-- MENU area

CREATE TABLE GROUPMENU (
  	GM_ID int NOT NULL AUTO_INCREMENT ,
  	GM_UG_ID_A varchar(50) NOT NULL default '',
  	GM_MNI_ID_A varchar(50) NOT NULL default '',
  	GM_ACTIVE char(1) NOT NULL default '',
  	PRIMARY KEY  (GM_ID)
) ENGINE=MyISAM;

CREATE TABLE MENUITEM (
  	MNI_ID_A varchar(50) NOT NULL default '',
  	MNI_BTN_LABEL varchar(50) NOT NULL default '',
  	MNI_LABEL varchar(50) NOT NULL default '',
  	MNI_TOOLTIP varchar(100) default NULL,
  	MNI_SHORTCUT char(1) default NULL,
  	MNI_SUBMENU varchar(50) NOT NULL default '',
  	MNI_CLASS varchar(100) NOT NULL default '',
  	MNI_IS_SUBMENU char(1) NOT NULL default 'N',
  	MNI_POSITION int(10) unsigned NOT NULL default '0',
  	PRIMARY KEY  (MNI_ID_A)
) ENGINE=MyISAM;

CREATE TABLE BSUNIT
(
    BSU_ID_A               varchar(10)          not null,
    BSU_DESC               varchar(50)          not null,
    BSU_CREATED_BY         varchar(50)          null,
    BSU_CREATED_DATE       datetime             null,
    BSU_LAST_MODIFIED_BY   varchar(50)          null,
    BSU_LAST_MODIFIED_DATE datetime             null,
    BSU_ACTIVE             tinyint(1) default 1 not null,
    PRIMARY KEY (BSU_ID_A)
) ENGINE = MyISAM;

CREATE TABLE TEMPUNIT
(
    TPU_ID_A               varchar(2)           not null,
    TPU_DESC               varchar(50)          not null,
    TPU_CREATED_BY         varchar(50)          null,
    TPU_CREATED_DATE       datetime             null,
    TPU_LAST_MODIFIED_BY   varchar(50)          null,
    TPU_LAST_MODIFIED_DATE datetime             null,
    TPU_ACTIVE             tinyint(1) default 1 not null,
    PRIMARY KEY (TPU_ID_A)
) ENGINE = MyISAM;