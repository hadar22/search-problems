# SOLVING PROBLEMS THROUGH SEARCH
## Tile Puzzle (colored N*M tile puzzle)
![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/15-puzzle-loyd.svg/220px-15-puzzle-loyd.svg.png)

Implement a search engine that supports multiple search algorithms to solve the game.<br/>
### Input:
Text file: <br/>
line 1 = The desired algorithm: BFS, DFID, A*, IDA* or DFBnB <br/>
line 2 = Whether to print runtime <br/>
line 3 = Whether to print to the screen the open list at each stage of the search algorithm run <br/>
line 4 = Board size <br/>
line 5 = List of black blocks <br/>
line 6 = List of red blocks<br/>
The rest of the lines = Initial arrangement of the board

Example of input: 
(https://github.com/hadar22/search-problems/blob/master/input.txt)

### Output:
line 1 = The moves - the block that moves and the direction that moves (R,L,D,U) <br/>
line 2 = The number of vertices created<br/>
line 3 = The cost of the blocks we moved to reach a solution<br/>
line 4 = Running time in seconds - the time it took for the algorithm to exhaust the solution<br/>

If there is no solution - "no path"

Example of output:
(https://github.com/hadar22/search-problems/blob/master/output.txt)

