package org.attention;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VideoController {

    @PostMapping("/analyze")
    public String analyze(
            @RequestBody Map<String,String> data
    ) {

        try {

            String url =
                    data.get("url");

            String query =
                    data.get("query");


            VideoCrawler crawler =
                    new VideoCrawler();

            crawler.downloadVideo(url);


            TranscriptReader reader =
                    new TranscriptReader();

            String transcript =
                    reader.loadTranscript();


            AIAnalyzer analyzer =
                    new AIAnalyzer();

            String timestamps =
                    analyzer.analyzeTranscript(
                            transcript,
                            query
                    );


            String[] parts =
                    timestamps.split("-");

            String start =
                    "00:" + parts[0];

            int startSec =
                    convertToSeconds(start);

            int endSec =
                    convertToSeconds(
                            "00:"+parts[1]
                    );


            int duration =
                    endSec - startSec;


            VideoTrimmer trimmer =
                    new VideoTrimmer();

            trimmer.trimVideo(
                    "downloaded_video.mp4",
                    start,
                    secondsToTime(duration)
            );


            return
                    "Relevant segment found: "
                            + timestamps
                            + "<br> trimmed_output.mp4 created";


        }

        catch(Exception e){
            e.printStackTrace();
            return "Error running analysis";
        }

    }


    private int convertToSeconds(
            String time
    ){

        String[] p=time.split(":");

        return
                Integer.parseInt(p[0])*3600+
                        Integer.parseInt(p[1])*60+
                        Integer.parseInt(p[2]);
    }


    private String secondsToTime(
            int total
    ){

        int m=total/60;
        int s=total%60;

        return String.format(
                "00:%02d:%02d",
                m,
                s
        );
    }

}