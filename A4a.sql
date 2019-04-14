select distinct r.day
from reservation r 
where r.day not in 
    (select x.day 
    from reservation x
    where x.day = r.day and x.bname 
    not in (select b.bname from boat b where x.bname = b.bname and b.color = "red")); 
