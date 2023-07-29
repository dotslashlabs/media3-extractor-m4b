package com.dotslashlabs.media3.extractor.m4b.metadata;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.UnstableApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UnstableApi
public class ChapterListFrame implements Metadata.Entry {

  public final int version;

  public final long flags;

  public final long chapterEntries;

  public final int chapterCount;

  public final List<ChapterMetadata> chapters;


  public ChapterListFrame(int version, long flags, long chapterEntries, List<ChapterMetadata> chapters) {
    this.version = version;
    this.flags = flags;
    this.chapterEntries = chapterEntries;

    if (chapters == null) {
      this.chapterCount = 0;
      this.chapters = new ArrayList<>();
    } else {
      this.chapterCount = chapters.size();
      this.chapters = chapters;
    }
  }

  private ChapterListFrame(Parcel in) {
    version = in.readInt();
    flags = in.readLong();
    chapterEntries = in.readLong();
    chapterCount = in.readInt();
    chapters = new ArrayList<>(chapterCount);

    for (int i = 0; i < chapterCount; i++) {
      chapters.add(new ChapterMetadata(in.readLong(), in.readString()));
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int parcelFlags) {
    dest.writeInt(version);
    dest.writeLong(flags);
    dest.writeLong(chapterEntries);
    dest.writeInt(chapters.size());

    for (ChapterMetadata chapter : chapters) {
      dest.writeLong(chapter.startTime);
      dest.writeString(chapter.chapterTitle);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChapterListFrame that = (ChapterListFrame) o;
    return version == that.version && flags == that.flags && chapterCount == that.chapterCount && Objects.equals(chapters, that.chapters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, flags, chapterCount, chapters);
  }

  @NonNull
  @Override
  public String toString() {
    return "ChapterListFrame{" +
            "version=" + version +
            ", flags=" + flags +
            ", chapterEntries=" + chapterEntries +
            ", chapterCount=" + chapterCount +
            ", chapters=" + chapters +
            '}';
  }

  public static final Parcelable.Creator<ChapterListFrame> CREATOR =
          new Parcelable.Creator<ChapterListFrame>() {

            @Override
            public ChapterListFrame createFromParcel(Parcel in) {
              return new ChapterListFrame(in);
            }

            @Override
            public ChapterListFrame[] newArray(int size) {
              return new ChapterListFrame[size];
            }
          };
}
