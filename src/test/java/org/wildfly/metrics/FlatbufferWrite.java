package org.wildfly.metrics;

import com.google.flatbuffers.FlatBufferBuilder;
import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class FlatbufferWrite extends JapexDriverBase {


    private byte[] bytes;
    private int numSamples;

    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);
        numSamples = testCase.getIntParam("batch.size");
    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);

        long start = System.currentTimeMillis();
        FlatBufferBuilder fbb = new FlatBufferBuilder(64);
        int[] samples = new int[numSamples];

        for (int i=0;i<numSamples;i++) {
            int name = fbb.createString("jvm-heap-size");
            int sample = Metric.createMetric(fbb, name, System.currentTimeMillis(), 1024 * 64);
            samples[i] = sample;
        }

        int vec = MetricBatch.createMetricsVector(fbb, samples);

        MetricBatch.startMetricBatch(fbb);
        MetricBatch.addMetrics(fbb,vec);
        int batch = MetricBatch.endMetricBatch(fbb);
        fbb.finish(batch);

        bytes = fbb.sizedByteArray();

        setLongParam(Constants.RESULT_VALUE, bytes.length);
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);
    }

    /*@Override
    public void finish() {
        super.finish();

        try {
            String fileName = System.getProperty("java.io.tmpdir") + "metrics-" + numSamples;
            Files.write(Paths.get(fileName), bytes);
            System.out.println(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
