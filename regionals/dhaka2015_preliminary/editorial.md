# ACM-ICPC 2015 Asia Regional Dhaka (Preliminary Contest)

### A - Back to the Past
**Tags** ad-hoc

**Solution** Do as asked. [Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/BackToThePast.java)

**Complexity** `O(1)`

---
### A5_2 - Search the Khoj
**Tags** brute force

**Solution** Print phone numbers that mismatch with the number Jalil can recall in at most 1 digit.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/SearchTheKhoj.java)

**Complexity** `O(N * M)` where `M` is the length of the strings. In this case `M = 11`.

---
### D - XOR Subset
**Tags** combinatorics

**Solution** By [Ahmed Amer](http://codeforces.com/profile/Amerisma). For each element from 1 to N, we have to decide whether it will be in set X or set Y or not in any of the sets, so we have 3 possibilities for each element. So, in total we have 3^N ways to make sets X and Y. Each of these ways has a symmetric way in which (X1 = Y2 and Y1 = X2). We are interested in the way for which XOR(X) <= XOR(Y). So for each pair of ways, we will pick one of them. Only one way is symmetric to itself when X = {} and Y = {}. So the answer is (3^N-1) / 2 + 1 = (3^N+1)/2. [Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/XORSubset.java)


**Complexity** `O(log N)`

---
### incomp.E - Emoticons
**Tags** greedy

**Solution** Consider `^_^` as `[_]`. So `^` can be either matched/unmatched `[` or matched `]`. It's not optimal to have unmatched `]` because it will never be matched if considered. Let's loop from left to right trying to make use of all symbols we have in a way similar to bracket matching. As we go further, we will count four things

1. `ans`: complete emoticons `^_^` so far
2. `first`: unmatched `[`
3. `pair`: count of `[_` that need a `]` to be added to `ans`
4. `unmatch_`: unmatched `_` 

Our decision for each symbol will be as follows:

1. If the current symbol is `^`. We will first try to consider it as `]` because it will increase the result. If we failed, we will consider it as unmatched `[` (increase `first`). We have to cases to make it `]`.
  - If we have `pair` > 0, we can take one of them and make a complete emoticon. 
  - If we have some complete emoticons and `unmatch_` > 0. Then, this situation exists as a subsequence: `^_^_^`. We are considering it equivalent to `[_]_^`. Instead of making it `[_]_[`, it will better if we utilized the unmatched `_` by swapping the unmatched `[` and make it `[_[_]` (2nd and 3rd `^` are swapped). So, when we proceed now, `pair` will increase instead of `first` which is obviously advantageous.
2. If the current symbol is `_`. If `first` > 0, then make them a pair. Otherwise, we may add it to `unmatch_` because it may be used in the swapping mentioned above. However, we will always keep `unmatch_ <= ans` to avoid wrong calculation with the case `_[_]^`.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/Emoticons.java)

**Complexity** `O(N)`

---
### T - Brain Fry
**Tags** graphs (SSSP on weighted graphs, DAGs), Probablities, DP

**Solution** First of all, we need to find the shortest path from all restaurants to the university. This can be done by running Dijkstra's aglorithm from the source only once, so now we have all distances needed. Also, let's count for each node its neighbours. All adjacent nodes of the univeristy are its neighbours, but neighbours of restaurants are restaurants only (university is not included). The strategy described is obviously finite because time keeps increasing. The state of the judges can be described by two parameters (current location, elapsed time). Let's denoted our answer for each state by {prob, exp}.

1. If the current location is the university, check if allowed time has finished or there are no neighbours. In such case, our answer is {0.0, current time} meaning we can never get brain fry and we are expected to arrive now because simply we are at the university. If this is not the case, we will pick a neighbour at random (probability to pick it `p = 1 / #neighbours`) and we will go there with the time of the road leading there. Now, we have a new state that has some solution {x, y}. From the current state, this solution will be {x/p, y/p}. {prob, exp} of the current state is the sum of solutions of all possible neighbour picks.
2. If the current location is a restaurant, then there are two disjoint cases and the solution for the whole state is the sum of solutions of both cases:
  - We will find brain fry (with probability P[location]) and we will eat it directly even if time elapsed is greater than allowed time as we are hungry :D. In such case, the solution will be {P[location], current time + time to get back to university from current location}
  - We will not find brain fry (with probablity 1 - P[location]). Now, we have three disjoint subcases and the solution of the current case is the sum of solutions of individual cases multiplied by 1 - P[location]. First, we will have a look at the elapsed time. if allowed time has finished, we have to go back to the univeristy. and the solution will be {0, current time + time to get back to university from current location}. Second case, we have time but we do not have neighbours. We will go back to the university and start a search from there. Otherwise (we have time and neighbours), we will pick one of them at random (with probability `p = 1 / #neighbours`) and we will go there with the time of the road leading there. Now, we have a new state that has some solution {x, y}. From the current subcase, this solution will be {x/p, y/p}. {prob, exp} of the current subcase is the sum of solutions of all possible neighbour picks.

[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/BrainFry.java)

**Complexity** `O(T * M)`

---
### G - Geek Power Inc.
**Tags** greedy (sortings)

**Observation**. If you take a power source with some rating _x_, it will be better to take all powers sources with rating >= _x_.
**Solution** Sort power groups and loop from largest to smallest adding to your count all encountered power sources and each iteration, maximize your final answer with the running sum multplied by current (minimum) power rating
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/GeekPowerInc.java)

**Complexity** `O(N log N)`

---
### H - Marbles in Jars
**Tags** combinatorics, DP or sortings

**Observation** If we can take `y` marbles from one jar, then we can take `x < y` from this jar as well. So the number of jars from which we can take `x` marbles equal to the number of jars from which we can take `x + 1` marbles plus the number of jars with exactly `x` marbles.

**Solution 1** Count for each value `x` with a suffix sum: from how many jars we can take the value `x`. Obviously, we can't take the same value `x` from two jars because we will not be able to differentiate between them. So for each value `x`, it will either be consider in the weighing process or it will not be considered. If it will be considered, then the number of jars we can take `x` from is equal to the perviously counted value minus the number of jars we have already taken value `y > x` from. Use the counting principle and a simple knapsack function.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/MarblesInJars1.java)

**Complexity** `O(N * M)` where M is the maximum valu e in a jar.

**Solution 2** Sort all jars and loop on them from smallest one to largest one and multiply the result with the `marbles[i] - i`. This is true because for a jar `i` (0-based indexing) it will have `marbles[i]` values but we can't use `i` of them because they have already been used. [Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015_preliminary/MarblesInJars2.java)

**Complexity** `O(N log N)`

---
### External Links
- [Problem set on UVa](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=866): problems 13025-13033
- [Official results and problem set](https://icpc.baylor.edu/regionals/finder/dhaka-preliminary-2015)
