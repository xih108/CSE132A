delete from reservation where sname in (select s.sname from sailor s
		where s.sname = sname and s.rating < (select min(rating) from boat));


delete from sailor 
where sname in (select s.sname from sailor s
                where s.rating < (select min(rating) from boat));


