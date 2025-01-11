package exercise.logger.app.master.entity;

public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

    @Override
    public String toString() {
        return name();
    }
}