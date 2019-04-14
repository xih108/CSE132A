select distinct s.sname, s.rating as srating, b.bname, b.rating as brating, r.day
from sailor s, boat b, reservation r
where s.sname = r.sname and b.bname = r.bname and s.rating < b.rating;