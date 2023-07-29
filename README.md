# media3-extractor-m4b

Provides m4b file format extractor including support for extracting chapter metadata.

Adapted from MP4 container support in [androidx.media3:media3-extractor:1.1.0](https://github.com/androidx/media/tree/5328d6464acb077a7e8cba61b8cac1973c4943d7/libraries/extractor/src/main/java/androidx/media3/extractor/mp4) from the [androidx.media3](https://developer.android.com/reference/androidx/media3/extractor/package-summary) family of modules.


## Installation

```kotlin
// build.gradle.kts

dependencies {
    implementation("androidx.media3:media3-extractor:1.1.0")
    implementation("com.dotslashlabs:media3-extractor-m4b:1.0.3")
}

```

## Usage

```kotlin
import android.content.Context
import android.net.Uri
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.MetadataRetriever
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.dotslashlabs.media3.extractor.m4b.M4bExtractor
import com.dotslashlabs.media3.extractor.m4b.metadata.M4bMetadata


fun retrieveMetadata(context: Context, sourceUri: Uri) {
    val extractors = M4bExtractor.createDefaultExtractors()

    val results = MetadataRetriever.retrieveMetadata(
        DefaultMediaSourceFactory(context) { extractors },
        MediaItem.fromUri(sourceUri),
    ).get()

    val mediaMetadata = (0 until results.length).mapNotNull(results::get)
        .flatMap { trackGroup ->
            (0 until trackGroup.length).mapNotNull(trackGroup::getFormat)
        }.fold(M4bMetadata.Builder()) { builder, format ->
            builder.populateFromFormat(format)
        }

    val metadata = mediaMetadata.build()
    val durationUs = metadata.durationUs ?: C.TIME_UNSET
    val chapters = metadata.chapters ?: emptyList()
}

```

