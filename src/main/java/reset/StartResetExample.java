package reset;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StartResetExample {

    public static void main(String[] args) throws InterruptedException {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(StartResetExampleWorker.TASK_QUEUE)
                .build();

        // Create Workflow
        MyState initalState = new MyState(UUID.randomUUID(), OffsetDateTime.now().plusHours(1));

        ScheduleWorkflow workflow = client.newWorkflowStub(
                ScheduleWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(initalState.getId().toString())
                        .setTaskQueue(StartResetExampleWorker.TASK_QUEUE)
                        .build()
        );

        WorkflowClient.start(workflow::start, initalState);

        Thread.sleep(2000);

        // Reschedule/Update Workflow
        ScheduleWorkflow existingWorkflow = client.newWorkflowStub(ScheduleWorkflow.class, initalState.getId().toString());

        existingWorkflow.reschedule(OffsetDateTime.now().plusHours(2));

    }
}
