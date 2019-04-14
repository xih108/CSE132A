select r.day, count(distinct b.bname) as number
from reservation r,boat b
where r.bname = b.bname and b.color = "red" 
group by r.day 
union 
select w.day, 0 as number
from weekday w
where w.day not in (select day from reservation)
      or not exists (select *  from reservation x, boat y where w.day = x.day and x.bname = y.bname and y.color = "red");

