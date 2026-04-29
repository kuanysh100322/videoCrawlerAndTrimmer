package org.attention;

import java.io.IOException;

public class VideoTrimmer {

    public void trimVideo(
            String inputFile,
            String start,
            String duration
    ) {

        try {

            ProcessBuilder pb =
                    new ProcessBuilder(
                            "ffmpeg",
                            "-y",
                            "-loglevel",
                            "error",
                            "-ss",
                            start,
                            "-i",
                            inputFile,
                            "-t",
                            duration,
                            "trimmed_output.mp4"
                    );

            pb.inheritIO();

            Process process =
                    pb.start();

            int exitCode =
                    process.waitFor();

            if(exitCode == 0){
                System.out.println(
                        "Trimmed video created successfully."
                );
            }

        }

        catch(IOException |
              InterruptedException e){

            e.printStackTrace();
        }

    }
}