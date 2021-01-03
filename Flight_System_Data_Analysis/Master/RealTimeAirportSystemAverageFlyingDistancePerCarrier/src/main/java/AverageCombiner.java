import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageCombiner extends Reducer<Text, AverageCountTuple, Text, AverageCountTuple> {

    private AverageCountTuple tuple = new AverageCountTuple();

    @Override
    protected void reduce(Text key, Iterable<AverageCountTuple> values, Context context) throws IOException, InterruptedException {

        int totalFlight=0;
        int totalDist=0;
        int totalAirTime =0;

        for(AverageCountTuple avg: values){
            totalFlight += avg.getFlightCount();
            totalDist += avg.getDistCount();
            totalAirTime += avg.getAirTime();
        }

        tuple.setAirTime(totalAirTime);
        tuple.setDistCount(totalDist);
        tuple.setFlightCount(totalFlight);

        context.write(key,tuple);
    }
}
