import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class DelayDayOfWeekMapper extends Mapper<Object, Text, Text, DelayCountTuple> {

    private String [] days ={"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private DelayCountTuple tuple = new DelayCountTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))
            return;
        if(tokens[3].equals("DAY_OF_WEEK"))
            return;
        int dayOfWeek=Integer.parseInt(tokens[3]);
        if(dayOfWeek>8)
            return;
        String day = days[dayOfWeek];//DayOfWeek


        try {
            int delay = Integer.parseInt(tokens[16]);


            if (delay > 15) {
                tuple.setDelayedFlightCount(1);
            } else {
                tuple.setDelayedFlightCount(0);
            }
        }catch (Exception e){
            tuple.setDelayedFlightCount(0);
        }

        tuple.setFlightCount(1);

        context.write(new Text(day),tuple);
    }
}
