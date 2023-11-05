
-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
-- Table: public.hero

-- DROP TABLE IF EXISTS public.hero;

CREATE TABLE IF NOT EXISTS public.hero
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age integer NOT NULL,
    power VARCHAR(255) NOT NULL,
    weakness VARCHAR (255) NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.hero
    OWNER to postgres;

    -- Table: public.squad

-- DROP TABLE IF EXISTS public.squad;

CREATE TABLE IF NOT EXISTS public.squad
(
    id SERIAL PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    size integer NOT NULL,
    cause VARCHAR (255) NOT NULL

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.squad
    OWNER to postgres;