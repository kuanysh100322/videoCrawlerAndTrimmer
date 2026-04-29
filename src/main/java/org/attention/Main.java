package org.attention;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "AI-Powered Targeted Video Crawler and Trimmer"
        );


        // Input
        System.out.print(
                "Enter YouTube URL: "
        );
        String youtubeUrl =
                scanner.nextLine();


        System.out.print(
                "What part should AI extract? "
        );
        String userQuery =
                scanner.nextLine();



        // Download video + subtitles
        VideoCrawler crawler =
                new VideoCrawler();

        crawler.downloadVideo(
                youtubeUrl
        );



        // Load transcript from subtitles
        TranscriptReader reader =
                new TranscriptReader();

        String transcript =
                reader.loadTranscript();



        // AI finds timestamps
        AIAnalyzer analyzer =
                new AIAnalyzer();

        String timestamps =
                analyzer.analyzeTranscript(
                        transcript,
                        userQuery
                );


        System.out.println(
                "Relevant segment found: "
                        + timestamps
        );



        // Parse timestamps and trim automatically
        try {

            String[] parts =
                    timestamps.trim().split("-");


            String start =
                    "00:" + parts[0];

            String end =
                    "00:" + parts[1];


            int startSeconds =
                    convertToSeconds(
                            start
                    );

            int endSeconds =
                    convertToSeconds(
                            end
                    );

            int duration =
                    endSeconds
                            -
                            startSeconds;



            VideoTrimmer trimmer =
                    new VideoTrimmer();

            trimmer.trimVideo(
                    "downloaded_video.mp4",
                    start,
                    secondsToTime(
                            duration
                    )
            );


        }

        catch(Exception e){

            System.out.println(
                    "Could not parse AI timestamps."
            );

            e.printStackTrace();
        }


        scanner.close();

    }



    private static int convertToSeconds(
            String time
    ){

        String[] parts =
                time.split(":");


        int hours =
                Integer.parseInt(
                        parts[0]
                );

        int minutes =
                Integer.parseInt(
                        parts[1]
                );

        int seconds =
                Integer.parseInt(
                        parts[2]
                );


        return
                hours*3600
                        +
                        minutes*60
                        +
                        seconds;
    }



    private static String secondsToTime(
            int totalSeconds
    ){

        int minutes =
                totalSeconds / 60;

        int seconds =
                totalSeconds % 60;


        return String.format(
                "00:%02d:%02d",
                minutes,
                seconds
        );
    }

}