import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DelayYearReducer extends Reducer<Text, DelayCountTuple, Text, DelayCountTuple> {
    private DelayCountTuple result= new DelayCountTuple();
    @Override
    protected void reduce(Text key, Iterable<DelayCountTuple> values, Context context) throws IOException, InterruptedException {
        int total=0;
        int delayedTotal=0;
        for(DelayCountTuple d: values){
            total += d.getFlightCount();
            delayedTotal +=d.getDelayedFlightCount();
        }
        double percent = ((double)delayedTotal/total)*100;
        result.setDelayedFlightCount(delayedTotal);
        result.setFlightCount(total);
        result.setDelayPercent(percent);
        context.write(key,result);
    }
}
