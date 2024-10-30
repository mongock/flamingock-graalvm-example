package io.flamingock.changes;

import com.mongodb.client.MongoDatabase;
import io.flamingock.graalvm.FlamingockGraalVM;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@FlamingockGraalVM
@ChangeUnit(id = "create-collection", order = "1", transactional = false)
public class ACreateCollection {

    @Execution
    public void execution(MongoDatabase mongoDatabase) {
        mongoDatabase.createCollection("clientCollection");
    }

    @RollbackExecution
    public void rollBack() {
    }
}
