
-- Insert Activity & Sub Activity table script
insert into activity(activity_id,activity_name,agency_id) values(1,'Aleap ESDP Training ',1);
insert into sub_activity(sub_activity_id,sub_activity_name) values(1,'ESDP Training');
insert into sub_activity(sub_activity_id,sub_activity_name) values(2,'Awareness program');
insert into activity_sub_activity(activity_id,sub_activity_id)values(1,1),(1,2);
