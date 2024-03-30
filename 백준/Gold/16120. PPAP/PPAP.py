import sys
input = sys.stdin.readline

ppap = []
s = input().rstrip()
result = 'NP'

for i in s:
  ppap.append(i)
  if len(ppap) >= 4 and ppap[-4:] == ['P','P','A','P']: # 기본 문자열 
    for _ in range(3):
      ppap.pop() # 스택에서 삭제 
    
if len(ppap) == 1 and ppap[0] == 'P':
  result = 'PPAP'
print(result)