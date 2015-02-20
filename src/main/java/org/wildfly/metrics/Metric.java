// automatically generated, do not modify

package org.wildfly.metrics;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class Metric extends Table {
  public static Metric getRootAsMetric(ByteBuffer _bb) { return getRootAsMetric(_bb, new Metric()); }
  public static Metric getRootAsMetric(ByteBuffer _bb, Metric obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Metric __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public long timestamp() { int o = __offset(6); return o != 0 ? bb.getLong(o + bb_pos) : 0; }
  public long value() { int o = __offset(8); return o != 0 ? bb.getLong(o + bb_pos) : 0; }

  public static int createMetric(FlatBufferBuilder builder,
                                 int name,
                                 long timestamp,
                                 long value) {
    builder.startObject(3);
    Metric.addValue(builder, value);
    Metric.addTimestamp(builder, timestamp);
    Metric.addName(builder, name);
    return Metric.endMetric(builder);
  }

  public static void startMetric(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addTimestamp(FlatBufferBuilder builder, long timestamp) { builder.addLong(1, timestamp, 0); }
  public static void addValue(FlatBufferBuilder builder, long value) { builder.addLong(2, value, 0); }
  public static int endMetric(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

