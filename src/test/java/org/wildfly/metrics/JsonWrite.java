package org.wildfly.metrics;

import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class JsonWrite extends JapexDriverBase {


    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);


    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);

        int numSamples = testCase.getIntParam("batch.size");
        long start = System.currentTimeMillis();
        JSONObject batch = new JSONObject();

        JSONArray list = new JSONArray();
        for (int i=0;i<numSamples;i++) {
            JSONObject obj = new JSONObject();
            obj.put("name", "jvm-heap-size");
            obj.put("ts", System.currentTimeMillis());
            obj.put("value", 1024*64);
            list.add(obj);
        }

        batch.put("metrics", list);

        byte[] bytes = batch.toJSONString().getBytes();

        setLongParam(Constants.RESULT_VALUE, bytes.length);
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);


    }
}
