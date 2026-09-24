package ai.agent;

import org.junit.jupiter.api.Test;

import base.BaseTest;

class TestAnalysisAgentTest extends BaseTest {

//     @Test
//     void testAgent() {
//         TestAnalysisAgent agent = new TestAnalysisAgent();

//         String analysis = agent.getTestResult();

        
//         System.out.println("========== LLM ANALYSIS ==========");
//         System.out.println(analysis);
//     }





    @Test
    void testAgent1() {

        TestAnalysisAgent agent =
                new TestAnalysisAgent();

        String answer = agent.analyze(
                "I want to know the latest ClockTest result. " +
                "Use the available tool and explain the result."
        );

        System.out.println("========== FINAL ANSWER ==========");
        System.out.println(answer);
    }


    }




    