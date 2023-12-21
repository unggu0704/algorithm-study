-- 코드를 입력하세요
SELECT pt_name, pt_no, gend_Cd, age, IFNULL(tlno,'NONE')
from patient
where gend_cd = "W" and AGE <= 12
order by age desc, pt_name asc;