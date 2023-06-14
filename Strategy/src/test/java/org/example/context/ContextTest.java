package org.example.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void useOperationSmallData(){
        var context = new Context<String>("Toulouse", "Pau", "Marseille", "Lyon", "Paris");
        context.operation();
    }

    @Test
    void useOperationBigData(){
        var context = new Context<String>(
                "Toulouse", "Pau", "Marseille", "Lyon", "Paris",
                "Bayonne", "Biarritz", "Bordeaux", "La Rochelle", "Rennes", "Lille");
        context.operation();
    }

    @Test
    void useOperationBigDataNumeric(){
        var context = new Context<Integer>(12, 33, 44, 14, 65, 6,
                14, 67, 91, 234, 438, 901);
        context.operation();
    }
}