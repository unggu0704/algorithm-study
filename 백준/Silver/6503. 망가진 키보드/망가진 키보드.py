import sys
input=sys.stdin.readline

def func(m):
    str=input().rstrip()
    d=dict()
    s=e=0
    cnt=0

    while e<len(str):
        if len(d)<m: 
            d[str[e]]=d.get(str[e],0)+1
            e+=1
        else:
            if str[e] in d: 
                d[str[e]]+=1 
                e+=1
            else:
                d[str[s]]-=1 
                if d[str[s]]==0:
                    del d[str[s]]
                s+=1
        if len(d)<=m:
            cnt=max(cnt,e-s)
    return cnt
           
while True:
    
    M=int(input())
    if M==0:
        break
    print(func(M))