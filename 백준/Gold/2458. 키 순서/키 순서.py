from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
reverse_graph = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    reverse_graph[b].append(a)

def bfs(start, graph):
    visited = [False] * (N+1)
    queue = deque([start])
    visited[start] = True
    count = 0

    while queue:
        now = queue.popleft()
        for nxt in graph[now]:
            if not visited[nxt]:
                visited[nxt] = True
                queue.append(nxt)
                count += 1
    return count

result = 0
for i in range(1, N+1):
    taller = bfs(i, graph)
    shorter = bfs(i, reverse_graph)
    if taller + shorter == N - 1:
        result += 1

print(result)
