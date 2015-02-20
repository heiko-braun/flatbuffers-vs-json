// automatically generated, do not modify

package org.wildfly.metrics;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class MetricBatch extends Table {
  public static MetricBatch getRootAsMetricBatch(ByteBuffer _bb) { return getRootAsMetricBatch(_bb, new MetricBatch()); }
  public static MetricBatch getRootAsMetricBatch(ByteBuffer _bb, MetricBatch obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public MetricBatch __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public Metric metrics(int j) { return metrics(new Metric(), j); }
  public Metric metrics(Metric obj, int j) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int metricsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createMetricBatch(FlatBufferBuilder builder,
      int metrics) {
    builder.startObject(1);
    MetricBatch.addMetrics(builder, metrics);
    return MetricBatch.endMetricBatch(builder);
  }

  public static void startMetricBatch(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addMetrics(FlatBufferBuilder builder, int metricsOffset) { builder.addOffset(0, metricsOffset, 0); }
  public static int createMetricsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startMetricsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endMetricBatch(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishMetricBatchBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

