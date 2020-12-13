class Node:
    def __init__(self, i):
        self.i = i
        self.size = 1
        self.max = i
        self.root = self

def root(node):
    r = node
    while r.root != r:
        r = r.root
    # perform path compression
    while node.root != node:
        node, node.root = node.root, r
    return r

def union(n1, n2):
    n1 = root(n1)
    n2 = root(n2)
    if n1.size < n2.size:
        n1, n2 = n2, n1
    n2.root = n1
    n1.size += n2.size
    n1.max = max(n1.max, n2.max)

def Sfind(uf, i):
    return root(uf[i]).max

def Sdelete(uf, i):
    union(uf[i], uf[i+1])

N = 100
S = dict((i, Node(i)) for i in range(1, N))

# for i in S:
#     print(S[i]);

Sdelete(S, 10)
Sdelete(S, 12)
Sdelete(S, 11)

for i in [10, 12, 13, 20]:
    print(i, Sfind(S, i));