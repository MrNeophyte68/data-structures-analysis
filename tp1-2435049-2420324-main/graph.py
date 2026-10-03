"""
Graphs the results of the benchmarks for TP1
"""

import os
import sys
import csv
import matplotlib.pyplot as plt

REPORT_PATH = 'build/reports/jmh/results.txt'

def entry(row):
    return int(row['Param: size']), float(row['Score'].translate({ord(','): '.'}))

def plot(vector_entries, linked_entries, title):
    fig, ax = plt.subplots()

    ax.plot([x for x, _ in vector_entries], [y for _, y in vector_entries], 'o-', label='Vector')
    ax.plot([x for x, _ in linked_entries], [y for _, y in linked_entries], 'o-', label='Linked List')

    ax.legend()
    ax.set_title(title)
    ax.set_xlabel('List size')
    ax.set_ylabel('Time (ms)')

    plt.show()

if __name__ == "__main__":
    if not os.path.isfile(REPORT_PATH):
        print(f'No results found at {os.path.join(os.getcwd(), REPORT_PATH)}', file=sys.stderr)
        print('Make sure the benchmarks were executed properly', file=sys.stderr)
        exit(1)


    is_push = lambda r: 'push' in r['Benchmark'].lower()
    is_pop = lambda r: 'pop' in r['Benchmark'].lower()
    is_enqueue = lambda r: 'enqueue' in r['Benchmark'].lower()
    is_dequeue = lambda r: 'dequeue' in r['Benchmark'].lower()
    is_vector = lambda r: r['Param: type'] == 'vector'
    is_linked = lambda r: r['Param: type'] == 'linkedlist'

    with open(REPORT_PATH, 'r') as f:
        rows = [row for row in csv.DictReader(f)]

        stack_push_vector = [entry(r) for r in rows if is_push(r) and is_vector(r)]
        stack_push_linked = [entry(r) for r in rows if is_push(r) and is_linked(r)]
        stack_pop_vector = [entry(r) for r in rows if is_pop(r) and is_vector(r)]
        stack_pop_linked = [entry(r) for r in rows if is_pop(r) and is_linked(r)]
        queue_enqueue_vector = [entry(r) for r in rows if is_enqueue(r) and is_vector(r)]
        queue_enqueue_linked = [entry(r) for r in rows if is_enqueue(r) and is_linked(r)]
        queue_dequeue_vector = [entry(r) for r in rows if is_dequeue(r) and is_vector(r)]
        queue_dequeue_linked = [entry(r) for r in rows if is_dequeue(r) and is_linked(r)]

        plot(stack_push_vector, stack_push_linked, 'Stack Push Benchmark')
        plot(stack_pop_vector, stack_pop_linked, 'Stack Pop Benchmark')
        plot(queue_enqueue_vector, queue_enqueue_linked, 'Queue Enqueue Benchmark')
        plot(queue_dequeue_vector, queue_dequeue_linked, 'Queue Dequeue Benchmark')

