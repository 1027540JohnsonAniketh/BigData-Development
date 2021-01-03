import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DelayedMapperCount extends Mapper<LongWritable, Text, NullWritable, IntWritable> {

    Text word = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String [] values = value.toString().split(",");
        //ByPassing First Line
        if(values[0].equals("Year"))return;
        try {
            int delay = Integer.parseInt(values[16]);

            if(delay>=15)
                context.write(NullWritable.get(), one);
        } catch(Exception e){
            return;
        }

    }
}