class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        n = len(points)

        def cal_line(i, j):
            x1, y1 = points[i]
            x2, y2 = points[j]
            k = (y1-y2)/(x1-x2) if x1 != x2 else 1e9
            b = (x2*y1-x1*y2) / (x1-x2) if x1 != x2 else x1
            return (k, b)

        edges = defaultdict(lambda: defaultdict(int))
        lines = defaultdict(set)
        for i in range(n-1):
            for j in range(i+1, n):
                k, b = cal_line(i, j)
                edges[k][b] += 1
                lines[(k,b)].add(i)
                lines[(k,b)].add(j)
        if len(edges) == 1:
            return 0
        
        ans = 0
        for k, bs in edges.items():
            bs = list(bs.values())
            m = len(bs)
            for i in range(m-1):
                n1 = bs[i]
                for j in range(i+1, m):
                    n2 = bs[j]
                    ans += n1 * n2

        def count_parallelograms(points):
            n = len(points)
            # 哈希表记录每个中点（存储2倍值避免浮点误差）的出现次数
            midpoint_count = defaultdict(int)
            
            # 遍历所有点对 (i, j) 其中 i < j
            for i in range(n):
                x1, y1 = points[i]
                for j in range(i + 1, n):
                    x2, y2 = points[j]
                    # 计算中点*2（整数存储避免除法）
                    mx = x1 + x2
                    my = y1 + y2
                    midpoint_count[(mx, my)] += 1
            
            # 统计平行四边形总数
            total = 0
            for count in midpoint_count.values():
                if count >= 2:
                    # 组合公式 C(count, 2) = count*(count-1)//2
                    total += count * (count - 1) // 2
            return total

        dup = count_parallelograms(points)
        for line in lines.values():
            _points = [points[i] for i in line]
            if len(_points) >= 4:
                dup -= count_parallelograms(_points)
        return ans - dup