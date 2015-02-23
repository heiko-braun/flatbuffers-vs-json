package org.wildfly.metrics;

import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class JsonWrite extends JapexDriverBase {


    private int numSamples;
    private byte[] bytes;

    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);
        numSamples = testCase.getIntParam("batch.size");
    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);


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

        bytes = batch.toJSONString().getBytes();

        setLongParam(Constants.RESULT_VALUE, bytes.length);
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);


    }

    /*@Override
    public void finish() {
        super.finish();                             /*@Override
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
