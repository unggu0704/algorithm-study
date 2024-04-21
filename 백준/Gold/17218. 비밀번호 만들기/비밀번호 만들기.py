input1 = input()
input2 = input()

lena = len(input1)
lenb = len(input2)

dp = [[0] * (lenb + 1) for _ in range(lena + 1)]
for i in range(lena):
    for j in range(lenb):
        if input1[i] == input2[j]:
            dp[i + 1][j + 1] = dp[i][j] + 1 
        else:
            dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j])

res = ''

while dp[lena][lenb] != 0: 
    if dp[lena][lenb] == dp[lena - 1][lenb]:
        lena -= 1 
    elif dp[lena][lenb] == dp[lena][lenb - 1]:
        lenb -= 1 
    else:
        res += input1[lena - 1]
        lena -= 1 
        lenb -= 1 

print(res[::-1])