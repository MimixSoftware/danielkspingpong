package com.danielkspingpong.danielkspingpong;

import java.util.ArrayList;
import java.util.List;

/**
 * A memory stress test class, for Stage 3.
 */
public class MemoryStressTest
{
    /**
     * A main function.
     */
    public static void main(String[] args)
    {
        MemoryStressTest test = new MemoryStressTest();
        test.runHeapExperiment();
        //test.runStackExperiment();
    }

    /**
     * An experiment for heap size. Objects keep being created until MemoryOverflow occurs.
     */
    public void runHeapExperiment()
    {
        long startTime = System.currentTimeMillis();
        int objectCount = 0;
        try
        {
            List<Object> objects = new ArrayList<>();
            while (true)
            {
                objects.add(new long[100000]);
                objectCount++;
            }
        }
        catch (OutOfMemoryError e)
        {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Heap Experiment Results:");
            System.out.println("Objects created: " + objectCount);
            System.out.println("Time taken (ms): " + duration);
        }
    }

    /**
     * An experiment for stack size. Infinite recursion occurs and the max depth is measured.
     */
    public void runStackExperiment()
    {
        long startTime = System.currentTimeMillis();
        try
        {
            recursiveMethod(0);
        }
        catch (StackOverflowError e)
        {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Stack Experiment Results:");
            System.out.println("Depth of recursion: " + maxDepth);
            System.out.println("Time taken (ms): " + duration);
        }
    }

    /**
     * The recursive method that will be called as part of the stack experiment.
     */
    int maxDepth = 0;
    public void recursiveMethod(int depth)
    {
        maxDepth = Math.max(maxDepth, depth);
        recursiveMethod(depth + 1);
    }
}
