package com.dotslashlabs.media3.extractor.m4b.metadata;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ChapterMetadata implements Parcelable {
  public final long startTime;
  public final String chapterTitle;

  public ChapterMetadata(long startTime, String chapterTitle) {
    this.startTime = startTime;
    this.chapterTitle = chapterTitle;
  }

  protected ChapterMetadata(Parcel in) {
    startTime = in.readLong();
    chapterTitle = in.readString();
  }

  public long getStartTime() {
    return startTime;
  }

  public long getStartTimeMs() {
    return startTime / 10000L;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChapterMetadata that = (ChapterMetadata) o;
    return startTime == that.startTime && Objects.equals(chapterTitle, that.chapterTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, chapterTitle);
  }

  @NonNull
  @Override
  public String toString() {
    return "ChapterMetadata{" +
            "startTime=" + startTime +
            ", chapterTitle='" + chapterTitle + '\'' +
            '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeLong(startTime);
    dest.writeString(chapterTitle);
  }

  public static final Creator<ChapterMetadata> CREATOR = new Creator<ChapterMetadata>() {
    @Override
    public ChapterMetadata createFromParcel(Parcel in) {
      return new ChapterMetadata(in);
    }

    @Override
    public ChapterMetadata[] newArray(int size) {
      return new ChapterMetadata[size];
    }
  };
}
