select day, avg(rating) as averating 
from (select y.day,h.rating 
      from reservation y,sailor h 
      where y.sname = h.sname 
      group by y.day, y.sname) 
group by day;
