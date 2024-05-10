-- 코드를 입력하세요
SELECT date_format(DATETIME, '%H') as HOUR, count(ANIMAL_ID) as COUNT
from ANIMAL_OUTS
WHERE date_format(DATETIME, '%H') > 8 and date_format(DATETIME, '%H') < 20
GROUP BY HOUR
ORDER BY HOUR ASC