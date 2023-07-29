package com.dotslashlabs.media3.extractor.m4b;

import androidx.media3.common.C;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import com.dotslashlabs.media3.extractor.m4b.metadata.ChapterListFrame;
import com.dotslashlabs.media3.extractor.m4b.metadata.ChapterMetadata;

import java.util.ArrayList;
import java.util.List;

@UnstableApi
public class M4bExtractorOutput implements ExtractorOutput {

  private final List<ChapterListFrame> chapterListFrame;

  private long durationUs = C.TIME_UNSET;

  public M4bExtractorOutput() {
    chapterListFrame = new ArrayList<>();
  }

  @Override
  public TrackOutput track(int id, int type) {
    return null;
  }

  @Override
  public void endTracks() {

  }

  @Override
  public void seekMap(SeekMap seekMap) {
    durationUs = seekMap.getDurationUs();
  }

  public void addChapterListMetadata(Metadata metadata) {
    for (int i = 0; i < metadata.length(); i++) {
      Metadata.Entry entry = metadata.get(i);

      if (entry instanceof ChapterListFrame) {
        chapterListFrame.add((ChapterListFrame) entry);
      }
    }
  }

  public List<ChapterMetadata> getChapterMetadataList() {
    List<ChapterMetadata> chapters = new ArrayList<>();

    for (ChapterListFrame chapterListFrame : chapterListFrame) {
      chapters.addAll(chapterListFrame.chapters);
    }

    return chapters;
  }

  public long getDurationUs() {
    return durationUs;
  }
}
