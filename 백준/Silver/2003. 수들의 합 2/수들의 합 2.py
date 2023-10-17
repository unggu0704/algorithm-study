N, M = map(int, input().split())
arrs = list(map(int, input().split()))

left, right = 0, 1
n = 0


while (right<=N and left<=right):
    sum_arrs = arrs[left:right]
    total = sum(sum_arrs)

    if total == M:
        n += 1
        right += 1

    elif total < M:
        right += 1

    else:
        left += 1


print(n)