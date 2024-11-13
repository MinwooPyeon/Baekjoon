price = 0
count = 0
X=int(input())
N=int(input())
while count!=N:
    a,b = map(int, input().split())
    price += a*b
    count +=1
if X==price:
    print("Yes")
else:
    print("No")