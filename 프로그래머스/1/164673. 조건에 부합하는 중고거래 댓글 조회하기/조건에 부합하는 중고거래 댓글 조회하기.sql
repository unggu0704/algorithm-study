-- 코드를 입력하세요
SELECT b.TITLE, b.BOARD_ID, r.REPLY_ID, r.WRITER_ID, r.CONTENTS, date_format(r.CREATED_DATE, '%Y-%m-%d') as CREATED_DATE
from USED_GOODS_BOARD as b join USED_GOODS_REPLY as r on b.board_id = r.board_id
where b.created_Date between '2022-10-01' and '2022-10-31'
order by r.created_Date asc, b.title asc;