package org.wildfly.metrics;

import com.google.flatbuffers.FlatBufferBuilder;
import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class FlatbufferWrite extends JapexDriverBase {


    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);

    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);

        int numSamples = testCase.getIntParam("batch.size");

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

        byte[] bytes = fbb.sizedByteArray();

        setLongParam(Constants.RESULT_VALUE, bytes.length);
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);
    }
}
