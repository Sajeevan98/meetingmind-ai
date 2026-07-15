CREATE TABLE IF NOT EXISTS meeting_analyses (

    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    meeting_id BIGINT NOT NULL,

    summary TEXT NOT NULL,

    action_items JSONB NOT NULL,

    decisions JSONB NOT NULL,

    raw_response TEXT,

    model VARCHAR(50) NOT NULL,

    analysis_version INTEGER NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_meeting_analyses_meeting
        FOREIGN KEY (meeting_id)
        REFERENCES meetings(id)
        ON DELETE CASCADE

);

CREATE INDEX idx_meeting_analyses_uuid
ON meeting_analyses(uuid);

CREATE INDEX idx_meeting_analyses_meeting_id
ON meeting_analyses(meeting_id);

-- for getting "latest analysis"
CREATE INDEX idx_meeting_analyses_meeting_version
ON meeting_analyses(meeting_id, analysis_version DESC);