#!/usr/bin/python

import numpy as np

FILENAME = "challenge.txt"
NROWS = 45
NCOLS = 45

TARGET = "WHISPERING"

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
    ["UP", -1, 0], ["DOWN", 1, 0], ["RIGHT", 0, 1], ["LEFT", 0, -1],
    ["DR", 1, 1], ["DL", 1, -1], ["UL", -1, -1], ["UR",-1, 1]]

dirs = {
    "UP": (-1,0),
    "DOWN": (1,0),
    "RIGHT": (0,1),
    "LEFT": (0,-1),
}

def find_word_path_aux(grid, w, r, c):
    if grid[r][c] != w[0]:
        return (False, None)

    possible_dir = {}

    for dir, i, j in directions:
        rd, cd = r+i, c+j
        possible_dir[dir] = []

        possible_dir[dir].append((r,c))

        for k in range(1, len(w)):
            if 0<=rd<NROWS and 0<=cd<NCOLS:
                if w[k] == grid[rd][cd]:
                    possible_dir[dir].append((rd, cd))
                    rd, cd = rd+i, cd+j
                else:
                    break

    for dir in possible_dir:
        path = possible_dir[dir]

        if len(path) == 0:
            continue

        if len(path) == len(w):
            return (True, path)

        possible_angles = []
        if dir == "UP" or dir == "DOWN":
            possible_angles.append((0, -1))
            possible_angles.append((0, 1))
        elif dir == "RIGHT" or dir == "LEFT":
            possible_angles.append((1, 0))
            possible_angles.append((-1, 0))
        
        if len(possible_angles) == 0:
            continue

        for idx, (rCURRENT, cCURRENT) in enumerate(path):
            new_path = []
            new_path.extend(path[:idx:])
            new_path.append((rCURRENT,cCURRENT))
    
            for ai, aj in possible_angles:

                rd = rCURRENT + ai
                cd = cCURRENT + aj

                # if w==TARGET and len(path) == 4:
                #     print(new_path)

                if 0<=rd<NROWS and 0<=cd<NCOLS:
                    # if rd == 24 and cd == 37:
                    #     print("HELLOU")

                    # if len(path) == 4:
                    #     print(f"w[idx+1]: {w[idx+1]}")
                    #     print(f"({rd},{cd})\t: {grid[rd][cd]}")
                    #     print((ai, aj))

                    if w[idx+1] == grid[rd][cd]:
                        new_path.append((rd, cd))
                        flag = True

                        rd, cd = rd+ai, cd+aj
                        for k in range(idx+2, len(w)):
                            if 0<=rd<NROWS and 0<=cd<NCOLS and w[k] == grid[rd][cd]:
                                new_path.append((rd, cd))
                                rd, cd = rd+ai, cd+aj
                            else:
                                flag = False
                                break
                        
                        if flag:
                            return (True, new_path)
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
    print(f"({'%02s' % i},{'%02s' % j}): {word} - PATH: {path}")

def prettyprint_grid():
    print()
    
    print(f"     {' '.join('%02s' % idx for idx,v in enumerate(grid[0]))}")
    for idx, row in enumerate(grid):
        print(f"{'%03s' % idx} [{' '.join('%02s' % i for i in row)}]")
    print()


def compute_password():
    password = ""
    for idx, row in enumerate(grid):
        password += ''.join('%s' % i for i in row if i != MARKER)
    
    return password.strip()

if __name__ == "__main__":

    parse_file()

    prettyprint_grid()

    not_found = []
    for word in words:
        ret, path = find_word_path(grid, word)
        if ret:
            mark_path_in_grid(grid, path)
            prettyprint_word_found(word, path)
        else:
            not_found.append(word)

    if len(not_found) > 0:
        print()
        print("!!! MISSING WORDS !!!")
        for w in not_found:
            print(w)
    
    prettyprint_grid()
    
    print(f"PASSWORD: {compute_password()}")