package com.simxdeveloper.kamusdicoding.data.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.simxdeveloper.kamusdicoding.data.helper.Const;

/**
 * User: simx Date: 23/05/18 18:52
 */
@Entity (tableName = Const.TABLE_WORD_ENG_INDO)
public class WordsEngIndo implements android.os.Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = Const.ROW_WORD)
  private String word;

  @ColumnInfo(name = Const.ROW_DESC)
  private String desc;


  public WordsEngIndo (String word, String desc) {
    this.word = word;
    this.desc = desc;
  }

  @Override
  public String toString () {
    return "WordsEngIndo{" +
        "id=" + id +
        ", word='" + word + '\'' +
        ", desc='" + desc + '\'' +
        '}';
  }

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public String getWord () {
    return word;
  }

  public void setWord (String word) {
    this.word = word;
  }

  public String getDesc () {
    return desc;
  }

  public void setDesc (String desc) {
    this.desc = desc;
  }

  @Override
  public int describeContents () {
    return 0;
  }

  @Override
  public void writeToParcel (Parcel dest, int flags) {
    dest.writeInt (this.id);
    dest.writeString (this.word);
    dest.writeString (this.desc);
  }

  protected WordsEngIndo (Parcel in) {
    this.id = in.readInt ();
    this.word = in.readString ();
    this.desc = in.readString ();
  }

  public static final Creator<WordsEngIndo> CREATOR = new Creator<WordsEngIndo> () {
    @Override
    public WordsEngIndo createFromParcel (Parcel source) {
      return new WordsEngIndo (source);
    }

    @Override
    public WordsEngIndo[] newArray (int size) {
      return new WordsEngIndo[size];
    }
  };
}
