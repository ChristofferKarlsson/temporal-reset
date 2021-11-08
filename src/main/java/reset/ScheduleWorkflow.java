package reset;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.time.OffsetDateTime;

@WorkflowInterface
public interface ScheduleWorkflow {

    @WorkflowMethod
    void start(MyState initialState);

    @SignalMethod
    void reschedule(OffsetDateTime newStartTime);

}
