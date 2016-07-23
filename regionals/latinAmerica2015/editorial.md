# ACM-ICPC 2015 Latin America Regional Contest

### A - At most twice
**Tags** greedy

**Solution** Loop on digits starting from the most significant digits while counting frequncy of each digit. If a digit is encountered more than twice, we will change the number. Let this digit be *d*. We need to find largest digit *d'* < *d* that has occurred before less than twice. If not found (all digits from 0 till *d-1* occurred twice), go one digit back and do the same and eventually you will find it because the signifcant digit can be replaced with 0. If found, put it in the current position and now we have a number smaller than the upper limit. Starting from next position, replace every digit with the largest digit possible, that is, the digit that has already occurred less than twice.
[Source Code]()

**Complexity** `O(log U)`

---
### B - Blood groups
**Tags** bipartite matching

**Solution** for each query, we need to find if there is some way to take an antigen from every parent (or not to take any antigen if this parent has O) so that we get all query antigens and nothing else. build a bipartite graph where the left set represents parents and the right set represents required antigens and run any MCBM algorithm. If the result is B (query antigens), then the answer is "Yes: because we can get all antigens from B parents. For each remaining N - B parents, we will either take nothing from this parent (if this parent has O) or take a duplicate of the B antigens (if this parents doesn't have O). O blood group is a special case.
[Source Code]()

**Complexity** `O(Q * N^3)`

---
### D - D as in Daedalus
**Tags** ad-hoc, implementation

**Solution** for every round, try all possible values for *C* and pick the one that will maximize the score, then add the difference between old and new score to the result.
[Source Code]()

**Complexity** `O(M * N)`

---
### E - Exposing corruption
**Tags** graphs (bipartite coloring, connected components), DP

**Observation** the given rivalries will make the two parties the two sets of a bipartite graph. For each connected component in this bipartite graph, no two subsets (one from each set) that are in the same connected component can be in the same party. In other words, for each connected component, its left set must be in a different party from its right set. If a switch is to be performed for one member, all members included in its connected component must switch, as well. This is true because if a member will switch, his rivalries must switch too, their rivalries must switch, the rivalries of the rivalries of his rivalries must switch and so on ending up with the whole left and right sets involved in the switching operation.

**Solution** Find three things about each connected component
1. The number of DSP members (this value can be 0)
2. The number of PPP members (this value can be 0)
3. The cost of making a switch (which will be the sum of all individual prices)

Let's solve the problem of maximizing DSP members. For each component, we have two options. Either keep it as it with no cost. In such case, we will add DSP members of this component to the result. Or make a switch (if switch cost of this component is affordable within the current budget). In such case, we will add PPP members of this component to the result. We will maximize between both options at each step. Solution for maximizing PPP members can be done in a similar way. You can solve each problem (max for DSP and max for PPP) separately or merge both in one DP function.
[Source Code]()

**Complexity** `O((D + P) * B)`

---
### F - Fence the vegetables fail
**Tags** geometry(line sweep), data structures(dynamic RSQ), sortings

**Observation** a point is inside a rectilinear polygon if you draw a horizontal line passing through this point and it intersects odd number of vertical polygon sides in both directions and the same for drawing a vertical line (intersection with endpoints should be handled carefully, check point 1 in the second sample test case).

**Solution** We will make a line sweep in horizontal/vertical directions. Make segments for every vertical fence side and also for plants where plant segments will have equal y coordinates, so a segment can be represented by *(x, y1, y2)*. At the same time, collect all encountered y values. After that, sort all segments according to x coodinates and map y values with values from 1 to n (number of distinct y values) to reduce the space. We will keep a data structre (segment/fenwick tree) to tell us how many fence sides encountered so far that pass through some y value. Now, we will sweep on the segments from left to right using the sorted array. If the current segment is a fence side, increment the values in the DS from *y1 + 1* to *y2*. The +1 is for handling intersection with endpoints as mentioned above. If the current segment is plant, query for the number fence sides at this plant y value. So, we need a DS that supports point query and range update. Fenwick trees will be the easiest for this task.
[Source Code]()

**Complexity** `O((V + P) log (V + P))`

---
### G - Galactic taxes
**Tags** graphs(SSSP on weighted graphs), ternary search

**Solution** since every edge is a monotonic function, every path from the source to the destination is a monotonic function as well because it is the sum of some monotonic functions. These functions are linear functions. If you draw them in the XY plane, take the minimum y value for each x value, you will get a new function which is the minimum of all these functions (you might have seen this graph before if you read about DP optimization with convex hull). This function is a unimodal function so we can apply ternary search to find its peak. This function is the represents the shortest path for each *t* value from source to destination and its peak is the maximum value for the shortest path at some time.
[Source Code]()

**Complexity** `O(M * log M * log (T / EPS)` where `T` is the valid time range. In this problem `T = 24 * 60`

---
### H - Height map
**Tags** graphs(grids, connected components), implementation

**Solution** It's obvious that external sides and bottom faces always exist as one face each, so initially we have 5 faces. For top faces, we have a number of faces equal to the number of connected components of the grid described by height map because every two adjacent columns (cells in the height map) with the same height will be part of the same face. What remains is finding internal side faces. For every column, count the number of adjacent columns with smaller heights and add this count to the result. Some faces will be counted more than once, so for every column, if it has an adjacent visited column, count the number of adjacent columns in the complmentary directions (N/S <=> E/W) with heights smaller than both columns heights and subtract that from the result.
[Source Code]()

**Complexity** `O(R * C)`

---
### I - Identifying tea
**Tags** ad-hoc

**Solution** [Source Code]()

**Complexity** `O(1)`

Remaining problems will be added later when I finish upsolving.

### External Links
- [Problem set on Live Archive](https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=702)
- [Problem set on UVa](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=866): problems 13004-13014
- [Official results and problem set](https://icpc.baylor.edu/regionals/finder/mexico-central-america-2015)
- [Problem set analysis](https://chococontest.wordpress.com/2015/11/23/solucionario-regional-south-america-2015/)
