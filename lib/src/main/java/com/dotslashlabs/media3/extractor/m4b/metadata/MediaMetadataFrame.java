package com.dotslashlabs.media3.extractor.m4b.metadata;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.media3.common.C;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.UnstableApi;

import java.util.Objects;

@UnstableApi
public class MediaMetadataFrame implements Metadata.Entry {

  public final long durationUs;

  public final int fileType;


  public MediaMetadataFrame(long durationUs, int fileType) {
    if (durationUs > 0) {
      this.durationUs = durationUs;
    } else {
      this.durationUs = C.TIME_UNSET;
    }

    this.fileType = fileType;
  }

  private MediaMetadataFrame(Parcel in) {
    durationUs = in.readLong();
    fileType = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeLong(durationUs);
    dest.writeInt(fileType);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MediaMetadataFrame that = (MediaMetadataFrame) o;
    return durationUs == that.durationUs && fileType == that.fileType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(durationUs, fileType);
  }

  @Override
  public String toString() {
    return "TrackFrame{" +
            "durationUs=" + durationUs +
            ", fileType=" + fileType +
            '}';
  }

  public static final Parcelable.Creator<MediaMetadataFrame> CREATOR =
          new Parcelable.Creator<MediaMetadataFrame>() {

            @Override
            public MediaMetadataFrame createFromParcel(Parcel in) {
              return new MediaMetadataFrame(in);
            }

            @Override
            public MediaMetadataFrame[] newArray(int size) {
              return new MediaMetadataFrame[size];
            }
          };
}
