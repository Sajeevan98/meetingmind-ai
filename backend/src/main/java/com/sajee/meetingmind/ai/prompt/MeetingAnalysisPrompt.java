package com.sajee.meetingmind.ai.prompt;

public final class MeetingAnalysisPrompt {

    private MeetingAnalysisPrompt() {
    }

    public static String build(String meetingContent) {

        return """
                You are an expert AI meeting assistant.
                
                Analyze the following meeting notes.
                
                Return the response using exactly this format.
                
                SUMMARY:
                <summary>
                
                ACTION ITEMS:
                <action items>
                
                DECISIONS:
                <decisions>
                
                MEETING NOTES:
                %s
                """.formatted(meetingContent);
    }
}