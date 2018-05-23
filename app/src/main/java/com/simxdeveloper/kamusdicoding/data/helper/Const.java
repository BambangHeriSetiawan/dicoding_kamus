package com.simxdeveloper.kamusdicoding.data.helper;

/**
 * User: simx Date: 23/05/18 23:41
 */
public class Const {

  /**
   * Database name
   */

  public static final String DATABASE_NAME = "db_kamus";
  public static final int DATABASE_VERSION = 1;

  /**
   * Table name
   */
  public static final String TABLE_WORD_ENG_INDO = "word_eng_indo";
  public static final String TABLE_WORD_INDO_ENG = "word_indo_eng";

  /**
   * Row name
   */
  public static final String ROW_ID = "id";
  public static final String ROW_WORD = "word";
  public static final String ROW_DESC = "desc";

  /**
   * Query
   */

  public final static String queryGetAll(String table_name){
    return String.format ("SELECT * FROM " + table_name);
  }

  public final static String queryGetAllBy(String table_name, String query){
    return String.format ("SELECT * FROM " + table_name +" LIKE :" + query);
  }






}
