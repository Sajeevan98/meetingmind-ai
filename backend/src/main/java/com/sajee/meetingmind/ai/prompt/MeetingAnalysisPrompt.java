package com.sajee.meetingmind.ai.prompt;

public final class MeetingAnalysisPrompt {

    private MeetingAnalysisPrompt() {
    }

    public static String build(String meetingContent) {

        return """
                You are an AI meeting assistant.
                Analyze the meeting notes below.
                Return ONLY valid JSON.
                Do not include markdown.
                Do not include explanation.
                Do not wrap the JSON inside ```.
                Return exactly this structure:
                {
                  "summary": "...",
                  "actionItems": [
                    {
                      "assignee": "...",
                      "task": "...",
                      "deadline": "..."
                    }
                  ],
                  "decisions": [
                    "..."
                  ]
                }
                Meeting Notes:
                
                %s
                """.formatted(meetingContent);
    }
}