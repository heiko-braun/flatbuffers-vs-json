package org.wildfly.metrics;

import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class FlatbufferRead extends JapexDriverBase {


    private byte[] data;

    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);
        int numSamples = testCase.getIntParam("batch.size");
        Path path = Paths.get("data/fb/metrics-"+numSamples);
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);

        long start = System.currentTimeMillis();

        ByteBuffer input = ByteBuffer.wrap(data);
        MetricBatch batchInput = MetricBatch.getRootAsMetricBatch(input);

        for(int i=0; i <batchInput.metricsLength();i++)
        {
            Metric metric = batchInput.metrics(i);
            String name = metric.name();
            long timestamp = metric.timestamp();
            long value = metric.value();

        }

        setLongParam(Constants.RESULT_VALUE, data.length);
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);
    }
}
