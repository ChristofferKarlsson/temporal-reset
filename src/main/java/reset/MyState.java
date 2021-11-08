package reset;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MyState {
    private UUID id;
    private OffsetDateTime startTime;

    public MyState() {
    }

    public MyState(UUID id, OffsetDateTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }
}
