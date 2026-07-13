CREATE TABLE IF NOT EXISTS meetings (

    id BIGSERIAL PRIMARY KEY,

    uuid UUID NOT NULL UNIQUE,

    title VARCHAR(255) NOT NULL,

    status VARCHAR(50),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL

);

CREATE TABLE IF NOT EXISTS meeting_attachments (

    id BIGSERIAL PRIMARY KEY,

    meeting_id BIGINT NOT NULL,

    original_file_name VARCHAR(255) NOT NULL,

    stored_file_name VARCHAR(255) NOT NULL,

    file_path VARCHAR(500) NOT NULL,

    file_type VARCHAR(255) NOT NULL,

    file_size BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_meeting_attachments_meeting
        FOREIGN KEY (meeting_id)
        REFERENCES meetings(id)
        ON DELETE CASCADE
        -- It is belong to the meeting. Database should automatically delete them.
        -- This matches JPA: orphanRemoval = true, cascade = CascadeType.ALL
);

CREATE INDEX idx_meetings_uuid
ON meetings(uuid);

CREATE INDEX idx_meeting_attachments_meeting_id
ON meeting_attachments(meeting_id);
