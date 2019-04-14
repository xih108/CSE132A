select DISTINCT b.bname, b.color
from boat b, reservation r
where r.day = "Wednesday" and r.bname = b.bname;


