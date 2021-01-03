import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class UniqueCarrierCountMapper  extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text word = new Text();
        IntWritable one = new IntWritable(1);

        String line = value.toString();
        String[] tokens= line.split(",");

        if(tokens[0].equals("Year"))
            return;

        String carrier = tokens[4];
        word.set(carrier);
        context.write(word,one);
    }


}
