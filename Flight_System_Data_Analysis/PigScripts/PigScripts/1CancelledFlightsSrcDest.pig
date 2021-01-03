--/FlightDirectory/712352293_T_ONTIME_REPORTING.csv
--/PigOutputDirectory/1.CancelledFlights

--Load the data
RAW_DATA = LOAD '/home/aniketh/Desktop/PigScripts/712352293_T_ONTIME_REPORTING.csv' USING PigStorage(',') AS 
	(year:int,month:int,dayofmonth:int,dayofweek: int, 
	opuniquecarrier:chararray,tailnum:chararray,opcarrierflightnum:int, 
	origin:chararray,destination:chararray,crsdeptime:int,deptime:int, 
	depdelay:int,taxiout:int,taxiin:int,crsarrtime:int,arrtime:int, 
	arrdelay:int,cancelled:int,cancellationcode:chararray,diverted:chararray,
	crselapsedtime:int,actualelapsedtime:int,airtime:int,distance:int,carrierdelay:int,weatherdelay:int,
	nasdelay:int,securitydelay:int,lateaircraftdelay:int);
--Selecting cancelled source-destination cancelled data 
CANCELLEDFLIGHTS = FOREACH RAW_DATA GENERATE origin AS s, destination AS d,cancelled as c;
FILTERCANCELELDSRCDEST =FILTER CANCELLEDFLIGHTS BY c==1;  
--Storing the results is HDFS
STORE FILTERCANCELELDSRCDEST INTO 'home/aniketh/Desktop/PigScripts/PigOutputFiles/1CancelledFlightsSrcDest' USING PigStorage(',');

	
