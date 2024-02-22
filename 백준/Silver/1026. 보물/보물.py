num =  int(input())
A =  [int(x) for x in input().split()]
B =  [int(x) for x in input().split()]
temp_B = B
A =sorted(A)
 
result= 0

for i in range(num) : 
    result += (A[i] * temp_B[temp_B.index(max(temp_B))])
    temp_B.remove(max(temp_B))
 
print(result)
