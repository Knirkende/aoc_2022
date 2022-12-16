dataset = []
s = 0

with open("inp.txt", "r") as inp:
    while True:
        try:
            dataset.append((eval(inp.readline()), eval(inp.readline())))
            inp.readline()
        except:
            break

def check_equality(term1, term2):
    if isinstance(term1, int) and isinstance(term2, int):
        if term1 < term2:
            return 1
        elif term1 == term2:
            return 2
        else:
            return 3
    if isinstance(term1, list) and isinstance(term2, list):
        i = 0
        while i < len(term1) and i < len(term2):
            r = check_equality(term1[i], term2[i])
            if r == 1:
                return 1
            elif r == 2:
                i += 1
                continue
            elif r == 3:
                return 3
            i += 1
        if i == len(term1) and i < len(term2):
                return 1
        elif i == len(term2) and i < len(term1):
                return 3
        else:
            return 2
    elif isinstance(term1, list):
        return check_equality(term1, [term2])
    elif isinstance(term2, list):
        return check_equality([term1], term2)

for idx, pair in enumerate(dataset):

    if check_equality(*pair) == 1:
        s += (idx + 1)

print(s)