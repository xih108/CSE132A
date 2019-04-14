select distinct r.day
from reservation r 
where not exists
	 (select * 
	  from reservation x
 	  where x.day = r.day and 
	  not exists (
		  select * from boat n
	      where n.bname = x.bname and n.color = "red"));

