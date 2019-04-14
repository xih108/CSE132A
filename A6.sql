select w.day 
from weekday w
where 
(select count(*) 
from reservation 
where day = w.day) = (select max(num) from 
                        ( select day,count(*) as num 
                          from reservation group by day)
                        ) 
or (select count(*) from reservation) = 0;