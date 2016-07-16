# ACM-ICPC 2015 Asia Dhaka Regional Contest

### A - Automatic Cheater Detection
**Tags** ad-hoc, greedy(sortings)

**Observation** correct non-leaked questions and incorrect leaked questions are useless, so you can discard them.

**Solution 1** sort questions based on difficulty and leaked questions come first in case of a tie. Then, loop on questions. 
If the current question is not leaked (and incorrect), add it to some variable `x`. If it is leaked (and correct) at `x` to the result.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/AutomaticCheaterDetection.java)

**Solution 2** instead of sorting, count questions of every difficulty (as `d <= 10`) and loop on the difficulty array.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/AutomaticCheaterDetection2.java)

**Complexity** `O(T * Q)` or `O(T * Q log Q)`

---
### B - Counting Weekend Days
**Tags** ad-hoc, data structures(maps, sets)

**Solution** make a set for months with 31 days and map every day to an index in order. Loop starting from the current day
for `x` times where `x` is the number of days in the input month. Use modulo to circulate and count. Don't forget to handle `FEB` case.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/CountingWeekendDays.java)

**Complexity** `O(T)`

---
### C - Toll Management
**Tags** graphs(mst), trees(LCA)

**Observation** *A* for non-special edges is inf and *B* for special edges is inf. If there is no non-special edges
(the given graph is a tree), *A* will also be inf.

**Solution** To find *A* for special edges and *B* for non-special edges, we can reduce the problem to the following:
- For every non-special edge e (u, v, w), find the edge e' with maximum weight on the path between u and v. This will give
us the lower bound w' for the current edge weight (B = w - w') because if we decreased w to be less than w`, then we can
remove e' from the mst and we will get to separate components that e can connect with smaller weight.

- For every special edge e (u, v, w), find the edge e' (u', v', w') with minimum weight such that e is on the path between
u' and v'. This will give us the upper bound w' for the current edge weight (A = w' - w) because if we increased w to be
greater than w', then we can remove e from the mst and we will get to separate components that e' can connect with samller weight.

To do these tasks, we can use the LCA algorithm which is similar to the sparse table. Given two nodes (u, v), we need to find
minimum weight on the path (u -> v). Since we find the LCA by jumping up powers of two, we can store in a sparse table (built on the tree)
the minimum weight of lengths that are powers of two starting from any node u and going upwards. Similar idea will be done to get the second
task, but this time we will be updating the sparse table not querying it. At the end of the day, we will propagate any updated values
residing in lengths > 1 to smallers lengths and consequently every node will have the correct value. Note that edge weights are
represented in the tree on the node considered as a child with respect to each edge.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/TollManagement.java)

**Complexity** `O(T * M * log N)`

---
### D - Owllen
**Tags** greedy, strings(LCP)

**Observation** if some character doesn't exist, you can make a string using it only and the LCP will be 0. If all characters
exist at least once, then LCP cannot be 0. If some character exists exactly once, you can make a string using it only and LCP will be 1.

**Solution** check which character has the smallest frequency and print it.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/Owllen.java)

**Complexity** `O(T * N)`

---
### E - Sum of MSLCM
**Tags** number theory(modified sieve), DP (cumulative sum)

**P.S.** TLE in such kind of problems can be avoided because the worst-case scenario can be easily generated.

**Solution**  preprocess using a modified sieve to count the sum of divisors and compute cumulative sum at the same time.
Each test can be answered now in `O(1)`.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/SumOfMSLCM.java)

**Complexity** `O(N * log N * log log N)`

---
### F - Unique Party
**Tags** brute force, DP (static RSQ), data structures(segment trees, RMQ)

**Note** below solution is still getting TLE, but this is the best one I found so far (thanks to [badry](http://codeforces.com/profile/Badry))

**Observation** The families will always gather at the median floor as it will minimize the distance (larger median in case of even count)

**Solution**  For every height h, we need to find a subrectangle with maximum area whose median is >= h. Let every floor >= h take the value 1 and every floor < h take the value -1. Now, we need to find a max-area subrectangle with non-negative
sum. We will do something similar to 2D max range sum that runs in `O(n^3)`. Brute force on two columns c1 (left column)
and c2 (right column) and let's find r1 and r2 such that subrectangle with opposite corners (c1, r1) and (c2, r2) has non-negative
sum and maximum area. Make a cumulative array for rows `prefixR` using RSQ obtained from cumulative-column sum and build a segment tree on this array.
Now, for every r2, we need to find r1 < r2 such that `prefixR[r2] - prefixR[r1-1] >= 0`. This is done with RMQ on the segment tree.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/UniqueParty.java)

**Complexity** `O(T * Q * C^2 * R * log R)`

---
### H - Design New Capital

**Tags** Counting + Polynomial multiplication (Fast Fourier Transform)

**Solution** [By Khalid Jamal](http://codeforces.com/profile/Safrout). Well, we need the point (0, 0) to be the centre of the universe to satisfy the optimum property described in the problem statement. Let's divide the universe then into 4 quaderants.
```
        |        
  (-,+) |  (+,+) 
    0   |    1   
--------|--------
        |        
  (-,-) |  (+,-) 
    3   |    2   
        |      
```
Let's now count the number of points in each region of those 4.
The first observation is, for (0, 0) to be optimum, this condition should hold cnt[0] + cnt[3] = cnt[1] + cnt[2].
And similarily we can divide this equation into 2 equations:<br>
- cnt[1] = cnt[3]
- cnt[0] = cnt[2]<br>
and this is kind of intuitive because if region 3 has 1 point more than region 1 then the optimal point would be somewhere between (0, 0) and the nearst point to it, but not (0, 0) itself.
 
So, now we can solve the problem for 2 Quaderants. In how many ways you can pick 2x points (x from one quaderant and x from the other one) from quaderants 1 and 3?
The answer is nCr(cnt[1], x) * nCr(cnt[3], x). Iterate over all possible 2x to get that.
We also can do that for the other 2 Quaderants.
 
Now we want to solve our original problem, to get the number of ways to choose i points from all the quaderants.
Now we will construct 2 polynomials poly1 and poly2<br>
P1 = 1 + nCr(cnt[1], 1) * nCr(cnt[3], 1) * X^2 + nCr(cnt[1], 2) * nCr(cnt[3], 2) * X^4 + ....... (Till the 1st time you hit x > cnt[1]  or cnt[3]).<br>
P2 = 1 + nCr(cnt[0], 1) * nCr(cnt[2], 1) * X^2 + nCr(cnt[0], 2) * nCr(cnt[2], 2) * X^4 + ....... (Till the 1st time you hit x > cnt[0]  or cnt[2]).
 
Now, think about it a bit, if you will multiply the 2 polynomials P1 and P2, the the coefficients of the powers of X from X^1 till X^n are the answer to the problem.
 
Now comes another problem, The naive way take `O(n^2)` to multiply to polynomials.
The good thing is that we can use the Fast Fourier algorithm to multiply 2 polynomials in `O(n*logn)`.
 
But here comes another problem, the FFT algorithm relies on complex numbers and doubles, however the problem needs some modular arithmetic and we all know "modular arithmetic can't be done with doubles"
Here will need a special version of the FFT called the Number Theoretic Transform (or NTT), it is basically a FFT but with out complex numbers and doubles.
 
To do so, we need to find for our given mod which is 7340033 some thing which is called an 2nth primitive root of unity (read the convolution theorem if you want to know about this).
 
Thank God this prime number is the same prime that e-maxx (and most of the people) use it for their NTT codes so you can just blindly take the e-maxx NTT code (but if the mod will change you will need to change some constants related to the convolution theorem).
 
I think yes the judges were lazy to get another prime and another 2nth primitive root of unity :D

[Source Code](http://ideone.com/xT9HEN)
 

---
### I - Numbered Cards
**Tags** DP (knapsack, bitmasks), combinatorics

**Observation** Required subset size <= 9

**Solution** Compute for every subset of the digits {0, 1,...9} how many numbers <= N that contain digits only in this subset.
This can be using DP with bitmask and moving with an index on the number digits. Now, let's count required subsets for each possible size.
This can be done using knapsack and bitmasks. You will decide for every subset element which digits to take and we have already calculated valid numbers using these digits, so we will mark these digits as used, go an solve the same problem for remaining elements. Multiplying the count of valid numbers for every element will yield the required result for this subset size.
[Source Code](https://github.com/AhmadElsagheer/UVa-Solutions/blob/master/regionals/dhaka2015/NumberedCards.java)

**Complexity** `O(4^B * B)` where B is the base (here B = 10)

---
If anyone can get codes for remaining problems, that will be great!

### External Links
- [Problem set on Live Archive](https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=684)
- [Problem set on UVa](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=868)
- [Official results and problem set](https://icpc.baylor.edu/regionals/finder/dhaka-2015)
- [Problem set analysis](https://www.scribd.com/doc/291072260)
