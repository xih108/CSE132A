update reservation
set day = 0 
where day = "Monday";

update reservation 
set day = "Monday"
where day = "Wednesday";

update reservation
set day = "Wednesday"
where day = 0;
