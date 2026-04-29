package org.attention;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TranscriptReader {

    public String loadTranscript(){

        try {

            return Files.readString(
                    Paths.get(
                            "downloaded_video.en.vtt"
                    )
            );

        }

        catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }

}