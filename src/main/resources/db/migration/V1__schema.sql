-- Create table for ApplicationUser
CREATE TABLE application_user
(
    id                      UUID PRIMARY KEY,
    name                    VARCHAR(255),
    lastname                VARCHAR(255),
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
    account_non_expired     BOOLEAN             NOT NULL,
    account_non_locked      BOOLEAN             NOT NULL,
    credentials_non_expired BOOLEAN             NOT NULL,
    enabled                 BOOLEAN             NOT NULL,
    login_attempts          INT,
    roles                   VARCHAR(255)
);

CREATE TABLE holiday
(
    id                  UUID PRIMARY KEY,
    type                VARCHAR (255) NOT NULL,
    status              VARCHAR (255) NOT NULL,
    request_time        timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    start_time          timestamp NOT NULL,
    end_time            timestamp NOT NULL,
    action_time         timestamp,
    last_update         timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notes               VARCHAR (1024) NOT NULL,
    application_user_id UUID,
    action_user_id UUID,
    FOREIGN KEY (application_user_id) REFERENCES application_user (id),
    FOREIGN KEY (action_user_id) REFERENCES application_user (id)
);

-- Create table for AuditEvent
CREATE TABLE audit_event
(
    id      UUID PRIMARY KEY,
    date    timestamp NOT NULL,
    action  VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    object  VARCHAR(255) NOT NULL,
    path    VARCHAR(255) NOT NULL
);

-- Create table for roles (Element Collection for AppUser)
CREATE TABLE application_user_roles
(
    user_id     UUID,
    roles       VARCHAR(255),
    FOREIGN KEY (application_user_id) REFERENCES application_user (id)
);
