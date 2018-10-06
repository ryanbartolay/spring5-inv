insert into currency (code, name, decimal_places, rate, symbol, base_currency) values ('CNY', 'Chinese Yuan', 2, 1, '¥', false);
insert into currency (code, name, decimal_places, rate, symbol, base_currency) values ('PHP', 'Philippine Peso', 2, 1, '₱', false);
insert into currency (code, name, decimal_places, rate, symbol, base_currency) values ('QAR', 'Qatari Rial', 2, 1, '﷼', true);
insert into currency (code, name, decimal_places, rate, symbol, base_currency) values ('USD', 'US Dollar', 2, 1, '$', false);

insert into settings (key, name, value) values ('BASE_CURRENCY','Base Currency', 'QAR');
