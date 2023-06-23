/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 24-Jun-2023 01:05:35 				*/
/*  DBMS       : SQLite 								*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS 'CodeReviews'
;

DROP TABLE IF EXISTS 'DiscussionThread'
;

DROP TABLE IF EXISTS 'ReviewDiscussions'
;

DROP TABLE IF EXISTS 'RevisionsToReviews'
;

DROP TABLE IF EXISTS 'ScmConfigurations'
;

DROP TABLE IF EXISTS 'ScmUserAliases'
;

DROP TABLE IF EXISTS 'StarredProjects'
;

DROP TABLE IF EXISTS 'SystemUsers'
;

/* Create Tables with Primary and Foreign Keys, Check and Unique Constraints */

CREATE TABLE 'CodeReviews'
(
	'reviewData' TEXT NOT NULL,
	'state' INTEGER NOT NULL,
	'projectId' TEXT NOT NULL,
	'reviewId' TEXT NOT NULL,
	CONSTRAINT 'FK_CodeReviews_ScmConfigurations' FOREIGN KEY ('projectId') REFERENCES 'ScmConfigurations' ('projectId') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'DiscussionThread'
(
	'uuid' TEXT NOT NULL PRIMARY KEY,
	'threadData' TEXT NOT NULL
)
;

CREATE TABLE 'ReviewDiscussions'
(
	'projectId' TEXT NOT NULL,
	'reviewId' TEXT NOT NULL,
	'threadUuid' TEXT NOT NULL,
	'created' NUMERIC NOT NULL,
	CONSTRAINT 'FK_ReviewDiscussions_DiscussionThread' FOREIGN KEY ('threadUuid') REFERENCES 'DiscussionThread' ('uuid') ON DELETE No Action ON UPDATE No Action
)
;

-- Needs to be reworked.
CREATE TABLE 'RevisionsToReviews'
(
	'projectId' TEXT NOT NULL,
	'reviewId' TEXT NOT NULL,
	'revisionId' TEXT NOT NULL
)
;

CREATE TABLE 'ScmConfigurations'
(
	'projectId' TEXT NOT NULL PRIMARY KEY,
	'scmConfigData' TEXT NOT NULL
)
;

CREATE TABLE 'ScmUserAliases'
(
	'userUuid' TEXT NOT NULL,
	'aliasName' TEXT NOT NULL,
	CONSTRAINT 'FK_ScmUserAliases_SystemUsers' FOREIGN KEY ('userUuid') REFERENCES 'SystemUsers' ('uuid') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'StarredProjects'
(
	'userUuid' TEXT NOT NULL,
	'projectId' TEXT NOT NULL,
	'whenStarred' NUMERIC NOT NULL,
	CONSTRAINT 'FK_StarredProjects_ScmConfigurations' FOREIGN KEY ('projectId') REFERENCES 'ScmConfigurations' ('projectId') ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT 'FK_StarredProjects_SystemUsers' FOREIGN KEY ('userUuid') REFERENCES 'SystemUsers' ('uuid') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'SystemUsers'
(
	'uuid' TEXT NOT NULL PRIMARY KEY,
	'userLoginName' TEXT NOT NULL,
	'userDisplayName' TEXT NOT NULL,
	'userEmail' TEXT NOT NULL,
	'avatarLocation' TEXT NOT NULL,
	'isBanned' INTEGER NULL
)
;

/* Create Indexes and Triggers */

CREATE INDEX 'IXFK_CodeReviews_ScmConfigurations'
 ON 'CodeReviews' ('projectId' ASC)
;

CREATE INDEX 'IXFK_ReviewDiscussions_DiscussionThread'
 ON 'ReviewDiscussions' ('threadUuid' ASC)
;

CREATE INDEX 'IXFK_ScmUserAliases_SystemUsers'
 ON 'ScmUserAliases' ('userUuid' ASC)
;

CREATE INDEX 'IXFK_StarredProjects_ScmConfigurations'
 ON 'StarredProjects' ('projectId' ASC)
;

CREATE INDEX 'IXFK_StarredProjects_SystemUsers'
 ON 'StarredProjects' ('userUuid' ASC)
;
