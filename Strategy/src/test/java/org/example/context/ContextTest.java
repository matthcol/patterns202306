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
}