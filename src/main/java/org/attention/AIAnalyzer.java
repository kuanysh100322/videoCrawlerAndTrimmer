package org.attention;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class AIAnalyzer {

    public String analyzeTranscript(
            String transcript,
            String userQuery
    ) {

        String apiKey =
                System.getenv(
                        "OPENAI_API_KEY"
                );

        if(apiKey == null || apiKey.isBlank()){
            return "API key not found";
        }

        try {

            OkHttpClient client =
                    new OkHttpClient();

            JSONObject body =
                    new JSONObject();

            body.put(
                    "model",
                    "gpt-4o-mini"
            );

            JSONArray messages =
                    new JSONArray();

            messages.put(
                    new JSONObject()
                            .put(
                                    "role",
                                    "system"
                            )
                            .put(
                                    "content",
                                    """
                                    You identify relevant
                                    segments in video transcripts
                                    and return timestamps only.
                                    """
                            )
            );

            messages.put(
                    new JSONObject()
                            .put(
                                    "role",
                                    "user"
                            )
                            .put(
                                    "content",
                                    """
                                    Find timestamps relevant to:
                                    """
                                            + userQuery
                                            +
                                            """

                                            Return ONLY one range like:
                                            01:20-02:10

                                            Transcript:
                                            """
                                            + transcript
                            )
            );

            body.put(
                    "messages",
                    messages
            );


            Request request =
                    new Request.Builder()
                            .url(
                                    "https://api.openai.com/v1/chat/completions"
                            )
                            .addHeader(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )
                            .addHeader(
                                    "Content-Type",
                                    "application/json"
                            )
                            .post(
                                    RequestBody.create(
                                            body.toString(),
                                            MediaType.parse(
                                                    "application/json"
                                            )
                                    )
                            )
                            .build();


            Response response =
                    client.newCall(request)
                            .execute();

            String result =
                    response.body().string();

            JSONObject json =
                    new JSONObject(result);

            return json
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        }

        catch(Exception e){
            e.printStackTrace();
        }

        return "No segment found";
    }
}