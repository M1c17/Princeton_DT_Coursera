class SuccessorUF(object):
    def __init__(self, n):
        self.parents = []
        for i in range(0, n):
            self.parents.append(i)

    def root(self, p):
        while self.parents[p] != p:
            p = self.parents[p]
        return p

    def union(self, p, q):
        root_p = self.root(p)
        root_q = self.root(q)
        self.parents[root_p] = root_q

    def remove(self, p):
        """
        :param (int) p: 
        :return: 
        """
        if p == len(self.parents) - 1:
            self.parents.pop(p)
            return
        self.union(p, p + 1)

    def successor(self, p):
        if p > len(self.parents) - 1 or self.root(p) != p:
            return 'DELETED_OR_NOT_EXISTED_EVER'  # Deleted Element
        if p == len(self.parents) - 1:
            return 'LAST_ELEMENT'
        return self.root(p + 1)