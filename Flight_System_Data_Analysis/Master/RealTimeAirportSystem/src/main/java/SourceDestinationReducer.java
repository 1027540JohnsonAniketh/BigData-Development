import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SourceDestinationReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int sum=0;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {


        for(IntWritable v: values){
            sum += v.get();
        }
        context.write(key, new IntWritable(sum));

    }

}