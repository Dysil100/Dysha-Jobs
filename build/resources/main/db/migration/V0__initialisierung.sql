DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS confirmation_token;
DROP TABLE IF EXISTS archiv;
DROP TABLE IF EXISTS dyshajob;
DROP TABLE IF EXISTS dyshaworker;
DROP TABLE IF EXISTS workerjobrelation;
DROP TABLE IF EXISTS dysha_file;
CREATE ROLE dyshajobs WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    NOREPLICATION
    PASSWORD 'iamgroot';

