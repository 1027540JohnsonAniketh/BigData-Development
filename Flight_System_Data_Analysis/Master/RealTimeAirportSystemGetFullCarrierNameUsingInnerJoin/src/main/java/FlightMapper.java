import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, Text, Text> {



    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text word = new Text();
        IntWritable one = new IntWritable(1);

        String line = value.toString();
        String[] tokens= line.split("\t");
        //Bypass the firstline
        if(tokens[0].equals("OP_UNIQUE_CARRIER"))return;
        String newKey = tokens[0];
        word.set(newKey);
        //Flag the value for reducer
        String outValue= "B"+tokens[1];
        context.write(word,new Text(outValue));
    }

}
