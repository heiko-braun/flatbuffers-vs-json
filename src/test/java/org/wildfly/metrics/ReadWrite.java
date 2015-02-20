package org.wildfly.metrics;

import com.google.flatbuffers.FlatBufferBuilder;

import java.nio.ByteBuffer;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class ReadWrite {

    public static void main(String[] args) {
        FlatBufferBuilder fbb = new FlatBufferBuilder(64);

        // -- encoding

        // Everything else (other tables, strings, vectors)
        // MUST be created before the start of the table they are referenced in.

        // single metric start
        int name = fbb.createString("jvm-heap-size");
        int firstMetric = Metric.createMetric(fbb, name, System.currentTimeMillis(), 1024 * 64);
        // single metric end


        // single metric start
        int name2 = fbb.createString("jvm-non-heap-size");
        int secondMetric = Metric.createMetric(fbb, name2, System.currentTimeMillis(), 1024 * 128);
        // single metric end


        // batch start
        int vec = MetricBatch.createMetricsVector(fbb, new int[]{firstMetric, secondMetric});

        MetricBatch.startMetricBatch(fbb);
        MetricBatch.addMetrics(fbb,vec);
        int batch = MetricBatch.endMetricBatch(fbb);
        // batch end

        fbb.finish(batch);

        byte[] bytes = fbb.sizedByteArray();
        System.out.println(bytes.length + " bytes");

        // -- decoding
        ByteBuffer input = ByteBuffer.wrap(bytes);
        MetricBatch batchInput = MetricBatch.getRootAsMetricBatch(input);

        for(int i=0; i <batchInput.metricsLength();i++)
        {
            Metric metric = batchInput.metrics(i);
            System.out.println("--");
            System.out.println("name:"  +metric.name());
            System.out.println("ts:"    +metric.timestamp());
            System.out.println("val:"   +metric.value());
        }

    }
}
