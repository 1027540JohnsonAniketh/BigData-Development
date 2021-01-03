--Load the data
RAW_DATA = LOAD '/home/aniketh/Desktop/PigScripts/712352293_T_ONTIME_REPORTING.csv' USING PigStorage(',') AS 
	(year:int,month:int,dayofmonth:int,dayofweek: int, 
	opuniquecarrier:chararray,tailnum:chararray,opcarrierflightnum:int, 
	origin:chararray,destination:chararray,crsdeptime:int,deptime:int, 
	depdelay:int,taxiout:int,taxiin:int,crsarrtime:int,arrtime:int, 
	arrdelay:int,cancelled:int,cancellationcode:chararray,diverted:chararray,
	crselapsedtime:int,actualelapsedtime:int,airtime:int,distance:int,carrierdelay:int,weatherdelay:int,
	nasdelay:int,securitydelay:int,lateaircraftdelay:int);
--Selecting airline frequency 
DELAYS = FOREACH RAW_DATA GENERATE year as y,carrierdelay as cdelay,weatherdelay as wdelay,nasdelay as ndelay
			,securitydelay as sdelay,lateaircraftdelay as ladelay;
			
GROUPBYCLAUSE = GROUP DELAYS BY y;

DELAYOUTPUT = FOREACH GROUPBYCLAUSE { TEMP = FILTER DELAYS BY (cdelay==1 OR wdelay==1) ;
                                    GENERATE flatten(group),COUNT(TEMP) AS DELAYCOUNT;
                                    };
                                    
STORE DELAYOUTPUT INTO 'home/aniketh/Desktop/PigScripts/PigOutputFiles/5AIRLINEDIFFERENTDELAYSOUTPUT' USING PigStorage(',');
