#!/usr/bin/python

import numpy as np

FILENAME = "challenge.txt"
NROWS = 45
NCOLS = 45

GRID_LINE = "Grid:"
WORDS_LINE = "Words:"
MARKER = "#"

index_cache = {}
grid = np.empty([NROWS,NCOLS], dtype=str)
words = []

def parse_file():
    
    with open(FILENAME) as file:
        lines = [line.strip() for line in file.readlines() if len(line.strip()) > 0]

    reading_grid = 0
    reading_word = 0 
    
    r, c = 0, 0
    for line in lines:
        if line == GRID_LINE:
            reading_grid = 1
            reading_word = 0

        elif line == WORDS_LINE:
            reading_grid = 0
            reading_word = 1

        elif reading_word:
            words.append(line)

        elif reading_grid:
            c = 0
            for x in line.split(' '):
                grid.itemset((r,c), x)
                index_cache.setdefault(f"{x}", []).append((r,c))
                c += 1
            r += 1

directions = [
    [-1, 0], [1, 0], [0, 1], [0, -1],
    [1, 1], [1, -1], [-1, -1], [-1, 1]]

def find_word_path_aux(grid, w, r, c):
    if grid[r][c] != w[0]:
        return (False, None)
    
    for i, j in directions:
        rd, cd = r+i, c+j
        flag = True
        
        path = []
        path.append((r,c))

        for k in range(1, len(w)):
            if 0<=rd<NROWS and 0<=cd<NCOLS and w[k] == grid[rd][cd]:
                path.append((rd, cd))

                rd += i
                cd += j
            else:
                flag = (False, None)
                break
        if flag:
            return (True,path)

    return (False, None)

def mark_path_in_grid(grid, path):
    for (i,j) in path:
        grid[i][j] = MARKER

def find_word_path(grid, w):
    for r in range(NROWS):
        for c in range(NCOLS):
            ret, path = find_word_path_aux(grid, w, r, c)
            if ret:
                return (True, path)
    return (False, None)

def prettyprint_word_found(word, path):
    (i,j) = path[0]
    print(f"({'%02s' % i},{'%02s' % j}): {word}")

def prettyprint_grid():
    print()
    
    print(f"     {' '.join('%02s' % idx for idx,v in enumerate(grid[0]))}")
    for idx, row in enumerate(grid):
        print(f"{'%03s' % idx} [{' '.join('%02s' % i for i in row)}]")
    print()

if __name__ == "__main__":
    
    parse_file()

    prettyprint_grid()
    for word in words:
        ret, path = find_word_path(grid, word)
        if ret:
            mark_path_in_grid(grid, path)
            prettyprint_word_found(word, path)

    prettyprint_grid()