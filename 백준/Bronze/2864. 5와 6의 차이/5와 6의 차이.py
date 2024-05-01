def min(x):
    result = ""
    for i in x:
        if i == "6":
            result += "5"
        else:
            result += i
    return result

def max(x):
    result = ""
    for i in x:
        if i == "5":
            result += "6"
        else:
            result += i
    return result
A, B = input().split()

minNum = int(min(A)) + int(min(B))
maxNum = int(max(A)) + int(max(B))

print(minNum, maxNum)