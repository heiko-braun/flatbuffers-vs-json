package org.wildfly.metrics;

import com.sun.japex.Constants;
import com.sun.japex.JapexDriverBase;
import com.sun.japex.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Heiko Braun
 * @since 20/02/15
 */
public class JsonRead extends JapexDriverBase {


    private static final String METRICS = "metrics";
    private int numSamples;
    private String data;

    @Override
    public void prepare(TestCase testCase) {
        super.prepare(testCase);
        numSamples = testCase.getIntParam("batch.size");
        Path path = Paths.get("data/js/metrics-" + numSamples);
        try {
            data = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run(TestCase testCase) {
        super.run(testCase);


        long start = System.currentTimeMillis();
        JSONObject obj = (JSONObject)JSONValue.parse(data);
        JSONArray array = (JSONArray)obj.get(METRICS);
        for(int i=0; i<array.size(); i++)
        {
            JSONObject item = (JSONObject)array.get(i);
            String name = (String)item.get("name");
            long ts = (Long)item.get("ts");
            long value = (Long)item.get("value");
        }

        setLongParam(Constants.RESULT_VALUE, data.length());
        setLongParam(Constants.RESULT_TIME, System.currentTimeMillis()-start);


    }
}
