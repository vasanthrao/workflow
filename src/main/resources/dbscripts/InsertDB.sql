-- Insert agencies script
-- insert into agency (agency_id, agency_name) values (1,'ALEAP'),(2,'WeHub'),(3,'TIHCL'),(4,'Commissionarate of Industries'),(5,'GT');
insert into agency (agency_id, agency_name) values (1,'ALEAP'),(2,'WeHub'),(3,'TIHCL');

-- Insert program outcome tables
insert into program_outcome_tables (outcome_table_id, outcome_table_display_name, outcome_table_name) values
(1, 'ONDC Registration', 'ONDCRegistration'),
(2, 'ONDC Transaction', 'ONDCTransaction'),
(3, 'Udyam Registration', 'UdyamRegistration'),
(4, 'CGTMSE Transaction', 'CGTMSETransaction');

-- Insert district and mandal script
insert into district(district_id,district_name) values (1,'Adilabad'),(2,'Hyderabad');
insert into mandal(mandal_id,mandal_name,district) values(1,'Adilabad(Rural)',1),(2,'Adilabad(urbarn)',1);

-- Insert Activity & Sub Activity table script
insert into activity(activity_id,activity_name) values(1,'Sample Activity1');
insert into sub_activity(sub_activity_id,sub_activity_name) values(1,'Sample Sub Activity1');
insert into activity(activity_id,activity_name) values(2,'Sample Activity2');
insert into sub_activity(sub_activity_id,sub_activity_name) values(2,'Sample Sub Activity2');
insert into activity_sub_activity(activity_id,sub_activity_id) values(1,1);
insert into activity_sub_activity(activity_id,sub_activity_id) values(2,2);