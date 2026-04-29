AI-Powered Targeted Video Crawler and Trimmer

Overview

This project retrieves a video from a user-provided URL, analyzes its transcript using AI, identifies a relevant segment based on a natural language query, and automatically trims that segment into a separate video.

Example input:
A YouTube URL and a query such as:
“Extract the section discussing GPU benchmarks.”

Example output:
Relevant timestamps and a trimmed output video.

Architecture

The system follows this pipeline:

User Input
VideoCrawler
TranscriptReader
AIAnalyzer
VideoTrimmer
Trimmed Output

Components

VideoCrawler:
Uses yt-dlp to download the video and retrieve subtitles.

TranscriptReader:
Loads the subtitle transcript for analysis.

AIAnalyzer:
Uses the OpenAI API to analyze transcript content and return relevant timestamps.

VideoTrimmer:
Uses FFmpeg to trim only the relevant segment.

Technologies Used

Java
Spring Boot
HTML and JavaScript
OpenAI API
yt-dlp
FFmpeg

Running the Project

Install yt-dlp:

pip install yt-dlp

Set environment variable:

OPENAI_API_KEY=your_api_key

Run the application:

mvn spring-boot:run

Open in browser:

http://localhost:8080

Example Console Output

Extracting URL...
Downloading subtitles...
Video and subtitles retrieved.
Relevant segment found: 01:20-02:10
Trimmed video created successfully.

AI Usage

AI is used for semantic transcript analysis.

Instead of simple keyword matching, the model interprets the meaning of the user request and identifies relevant timestamps dynamically.

Future Improvements

Support automatic transcript generation with Whisper.
Support other video platforms.
Support extraction of multiple relevant segments.