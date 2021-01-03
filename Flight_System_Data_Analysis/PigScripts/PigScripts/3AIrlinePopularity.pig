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
AIRLINESFREQUENCY = FOREACH RAW_DATA GENERATE month as m,opuniquecarrier AS c;
GROUPBYAIRLINES = GROUP AIRLINESFREQUENCY BY (m,c);
POPULARITYOUTPUT = FOREACH GROUPBYAIRLINES GENERATE FLATTEN(group),(COUNT(AIRLINESFREQUENCY)) AS AIRLINEPOPULRITY;
--Storing the results is HDFS
STORE POPULARITYOUTPUT INTO 'home/aniketh/Desktop/PigScripts/PigOutputFiles/3AIRLINEPOPULARITY' USING PigStorage(',');

	
