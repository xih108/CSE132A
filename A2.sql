select distinct a.sname as sname1, b.sname as sname2
from reservation a,reservation b
where a.sname < b.sname and a.day = b.day;
