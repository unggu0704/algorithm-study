-- 코드를 입력하세요
SELECT Round(avg(DAILY_FEE)) as AVERAGE_FEE
FROM car_rental_company_Car
where car_type = "SUV";