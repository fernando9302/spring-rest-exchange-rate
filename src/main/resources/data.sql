insert into currency (id, code, description) values('90a81075-3bc7-4687-8bc9-a04730ebb8b8', 'USD', 'US Dollar');
insert into currency (id, code, description) values('01adb917-7cf0-4b69-ae1b-08de679961d4', 'PEN', 'Soles');
insert into currency (id, code, description) values('e590af92-c6d3-4bfc-aa22-1a6aff7ab5d9', 'PIK', 'Piks');
insert into currency (id, code, description) values('921d958f-ebd0-4aed-88a8-5708960e3f89', 'EUR', 'Euros');

insert into exchange_rate (id, currency_id_from, currency_id_to, date, amount) values('4e3678d5-8671-4840-b339-85278d95c031', '01adb917-7cf0-4b69-ae1b-08de679961d4', '90a81075-3bc7-4687-8bc9-a04730ebb8b8', '2023-08-27', 3.71);
insert into exchange_rate (id, currency_id_from, currency_id_to, date, amount) values('f378a280-6a76-48f8-bcee-6179921ce261', '90a81075-3bc7-4687-8bc9-a04730ebb8b8', '01adb917-7cf0-4b69-ae1b-08de679961d4','2023-08-27', 0.271);