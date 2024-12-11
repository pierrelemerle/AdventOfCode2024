from collections import Counter

def simulate_blinks(stones, blinks):
    # Count occurrences of each stone
    counts = Counter(stones)
    
    for _ in range(blinks):
        new_counts = Counter()
        for stone, count in counts.items():
            if stone == 0:
                new_counts[1] += count
            elif len(str(stone)) % 2 == 0:  # Even number of digits
                num_str = str(stone)
                mid = len(num_str) // 2
                left, right = int(num_str[:mid]), int(num_str[mid:])
                new_counts[left] += count
                new_counts[right] += count
            else:
                new_counts[stone * 2024] += count
        counts = new_counts

    # Total number of stones
    return sum(counts.values())

# My own input
initial_stones = [0,27,5409930,828979,4471,3,68524,170]  
# Part 2 
blinks = 75
print("le nombre de pierre apres",blinks,"clignement est", simulate_blinks(initial_stones, blinks))