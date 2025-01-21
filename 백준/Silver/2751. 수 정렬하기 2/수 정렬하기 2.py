import sys

n = int(input())
num_list = []

for i in range(n):
    num_list.append(int(sys.stdin.readline()))

num_list.sort()

for num in num_list:
    print(num)