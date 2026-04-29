package org.attention;

import java.io.IOException;

public class VideoCrawler {

    public void downloadVideo(String url){

        try {

            ProcessBuilder pb =
                    new ProcessBuilder(
                            "python",
                            "-m",
                            "yt_dlp",

                            "--write-auto-sub",
                            "--sub-lang","en",

                            "-f","b[ext=mp4]",
                            "-o",
                            "downloaded_video.%(ext)s",

                            url
                    );

            pb.inheritIO();

            Process process =
                    pb.start();

            process.waitFor();

            System.out.println(
                    "Video and subtitles retrieved."
            );

        }

        catch(Exception e){
            e.printStackTrace();
        }

    }
}