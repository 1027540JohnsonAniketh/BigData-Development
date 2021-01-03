import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CancelledMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text word = new Text();
    IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String [] values = value.toString().split(",");
        if(values[0].equals("Year"))return;
        try {
            String can = values[17];
            String year = values[0];

            if(can.equals("1"))
                context.write(new Text(year), one);
        } catch(Exception e){
            return;
        }
    }
}