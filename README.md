# CZ2001 Project 2
In the first part of this project, we used Multi-Source Breadth First Search to find the nearest hospital, by adding all the hospitals into the queue in the very first iteration. Then, we do a simple breadth first search, and mark nodes as visited in the meantime.

In the second part, we used a modified version of the abovementioned algorithm to return the top k hospitals. We used a queue to store the current node and the previous path it took, a nested hash table to model a partial minimum distance spanning tree, and a hash table to store the nearest k hospitals.

We also did an analysis of time and space complexity for both algorithms, as well as empirical analysis. The results could be found in our report, which is in the root folder of this repository.
