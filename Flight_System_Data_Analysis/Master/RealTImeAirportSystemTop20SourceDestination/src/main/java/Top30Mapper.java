import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

public class Top30Mapper extends Mapper<Object, Text, NullWritable, Text> {
    private TreeMap<Integer,Text> countMap = new TreeMap<>();
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] val= value.toString().split("\t");

        if(val.length==2){
            String pair = val[0];
            int count = Integer.parseInt(val[1]);
            countMap.put(count,new Text(value));
        }

        if(countMap.size()>20)
            countMap.remove(countMap.firstKey());
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for(Text t: countMap.values())
            context.write(NullWritable.get(),t);
    }
}
