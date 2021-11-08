package reset;

import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.OffsetDateTime;

public class ScheduleWorkflowImpl implements ScheduleWorkflow {
    private Logger log = Workflow.getLogger(ScheduleWorkflowImpl.class);

    private MyState state;
    private UpdatableTimer startTimer = new UpdatableTimer();

    @Override
    public void start(MyState initialState) {
        state = initialState;

        log.info("Starting a new workflow");
        log.info("Scheduled to start at " + state.getStartTime());

        startTimer.sleepUntil(state.getStartTime().toInstant().toEpochMilli());

        log.info("Done");
    }

    @Override
    public void reschedule(OffsetDateTime newStartTime) {
        log.info("Reschedule to start at " + newStartTime);

        state.setStartTime(newStartTime);

        startTimer.updateWakeUpTime(state.getStartTime().toInstant().toEpochMilli());
    }
}
