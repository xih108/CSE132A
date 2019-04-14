select distinct r.day
from reservation r 
where (select count(*) 
       from reservation x 
       where r.day = x.day and 
       (select count(*) from boat b 
       where x.bname = b.bname and b.color ="red") = 0
)= 0; 