import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

    Set<String> set = new HashSet<>();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        set.clear();
        StringBuilder sb = new StringBuilder("");
        for(Text v: values){
            set.add(v.toString());
        }
        for(String v: set){
            sb.append(v);
            sb.append(" ");
        }
        context.write(key, new Text(sb.toString()));
    }

}