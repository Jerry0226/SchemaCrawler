CREATE TABLE model_parameter (
    param_name TEXT COLLATE NOCASE PRIMARY KEY,
    description TEXT,
    value NOT NULL
);
create view if not exists scenario_month (month) as
WITH RECURSIVE -- iterate months in scenario. This 'with section' is pretty ridiculous, but it works.
  scen_months(start_month, month_index, this_month) as (
     SELECT 
        (select strftime('%Y-%m-01', year || '-' || month || '-' || '01')),
        0,
        (select strftime('%Y%m01', year || '-' || month || '-' || '01'))
        from (select substr(value, 1, 4) as year, substr(value, 5, 2) as month
              from (select value from model_parameter where param_name = 'start_date' limit 1))
     UNION ALL
     SELECT start_month, month_index+1, (select strftime('%Y%m01', year || '-' || month || '-' || '01')
                                         from (select substr(value, 1, 4) as year, substr(value, 6, 2) as month
                                               from (select date(start_month, printf('+%i months', month_index + 1)) as value)))
     FROM scen_months
     LIMIT (select 12 * (end_year - start_year) + end_month - start_month -- Number of months in sim
            from
            ( (select substr(value, 1, 4) as start_year, substr(value, 5, 2) as start_month from model_parameter where param_name = 'start_date'),
              (select substr(value, 1, 4) as end_year, substr(value, 5, 2) as end_month from model_parameter where param_name = 'end_date') ))
)
SELECT this_month
FROM scen_months ;
