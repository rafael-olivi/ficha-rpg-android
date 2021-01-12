package mds.rpg.database;

/**
 * Created by rboli on 17/12/2016.
 */

public class ScriptSQL {

    public static String getCreateFicha() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE FICHA ( ");
        sqlBuilder.append("id   INTEGER      PRIMARY KEY AUTOINCREMENT ");
        sqlBuilder.append("NOT NULL, ");
        sqlBuilder.append("NOME VARCHAR (15) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String getCreateRegistro() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE REGISTRO ( ");
        sqlBuilder.append("id        INTEGER      PRIMARY KEY AUTOINCREMENT ");
        sqlBuilder.append("NOT NULL, ");
        sqlBuilder.append("id_Ficha  INTEGER      REFERENCES FICHA (id) ");
        sqlBuilder.append("NOT NULL, ");
        sqlBuilder.append("NOME      VARCHAR (15), ");
        sqlBuilder.append("RAÇA      VARCHAR (1), ");
        sqlBuilder.append("CLASSE    VARCHAR (1), ");
        sqlBuilder.append("EXP_TOTAL VARCHAR (10), ");
        sqlBuilder.append("NIVEL     VARCHAR (3), ");
        sqlBuilder.append("FORCA     VARCHAR (2), ");
        sqlBuilder.append("DESTREZA     VARCHAR (2), ");
        sqlBuilder.append("CONSTITUICAO     VARCHAR (2), ");
        sqlBuilder.append("INTELIGENCIA     VARCHAR (2), ");
        sqlBuilder.append("SABEDORIA     VARCHAR (2), ");
        sqlBuilder.append("CARISMA     VARCHAR (2), ");
        sqlBuilder.append("MOD_FORCA     VARCHAR (2), ");
        sqlBuilder.append("MOD_DESTREZA  VARCHAR (2), ");
        sqlBuilder.append("MOD_CONSTITUICAO     VARCHAR (2), ");
        sqlBuilder.append("MOD_INTELIGENCIA     VARCHAR (2), ");
        sqlBuilder.append("MOD_SABEDORIA     VARCHAR (2), ");
        sqlBuilder.append("MOD_CARISMA     VARCHAR (2), ");
        sqlBuilder.append("PV     VARCHAR (2), ");
        sqlBuilder.append("PV_ATUAL     VARCHAR (2) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

}