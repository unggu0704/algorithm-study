select grade.score1 as SCORE, HR_EMPLOYEES.EMP_NO, EMP_NAME, POSITION, EMAIL
from HR_EMPLOYEES
join (
    select EMP_NO, SUM(SCORE) as score1
    from HR_GRADE
    GROUP BY EMP_NO
    ORDER BY SUM(SCORE) DESC 
    limit 1
) as grade
on HR_EMPLOYEES.EMP_NO = grade.EMP_NO;
