# ACM-ICPC 2015 Southwestern Europe Regional Contest 

### A - promotions
**Tags** graphs (DAGs, dfs)

**Observation** For X promotions, an empolyee will definitely be promoted if the number of empolyees other than his successors is less than X and will have no possibility to be promoted if the number of his predecessors is greater than or equal to X.

**Solution** Run a dfs from each node to count its successors and to add this node to the predecessors of the its successors then use the counted values to compute the answer.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/swerc2015/Promotions.java)

**Complexity** `O(P * E)`

---
### C - Canvas Painting
**Tags** greedy

**Discussion** If we look at the first step, all canvasses will be painted. After that we will have two parts where will do the painting again on each of them unless a part has only one canvass. Drawing all possibilities as trees, we will find that they make full binary trees where the value each internal node is the sum of its children values. The cost of the tree (and thus of the painting strategy) is the sum of the values of all internal nodes. We need to minimize this cost. This is similar to Huffman tree and there is a well-known greedy strategy to find the minimum cost.

**Solution** Think about the problem backwards. The very last step has two canvasses where each one will be painted to a different color. Before painting, they have the same color but different from all other canvasses colors. So, we can consider them before painting as one canvass. Therefore, if we can consider from the very beginning painting only N - 1 canvasses and afterwards make the last paint, the best solution will be when the two canvasses we choose for merging has minimum size sum. Doing that recursively will yield the optimal solution. To compute minimum cost, find it from the bottom of Huffman tree. Make a set with all canvasses. Remove the smallest two canvasses, add their sum to the result and to set. Repeat till the set has only one element.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/swerc2015/CanvasPainting.java)

**Complexity** `O(N log N)`

---
### External Links
- [Problem set on Live Archive](https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=701)
- [Problem set on UVa](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=866): problems 13015-13024
- [Official results and problem set](https://icpc.baylor.edu/regionals/finder/swerc-2015)
- [Problem set analysis](http://swerc.up.pt/2015/reports/SWERC2015_presentation.pdf)
