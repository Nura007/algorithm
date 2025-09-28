# Algorithm Project

This project is a Java Maven project that implements and analyzes classical algorithms:

- Merge Sort
- Quick Sort
- Deterministic Select (Median of Medians)
- Closest Pair of Points

It includes:
- Implementations with metric tracking
- Complexity and architecture notes
- Benchmarks with CSV export
- JUnit 5 tests

---

# 1. Architecture Notes

## 1.1 Quick Sort
- **Recursion depth** → Controlled by always recursing into the smaller partition, while the larger partition is processed iteratively. This guarantees recursion depth of O(log n).
- **Pivot selection** → A randomized pivot ensures expected O(n log n) complexity, making the worst case O(n²) extremely unlikely.
- **Limit** → This design prevents stack overflow on very large or already sorted inputs.

## 1.2 Closest Pair of Points
- **Memory management** → O(n) memory is used for the strip list at each recursion level.
- **Complexity** → O(n log n) due to:
    1. Initial sorting by X once.
    2. Sorting by Y inside recursion and linear scan of strip.

## 1.3 Deterministic Select
- **Memory management** → Mostly in-place, only extra array of medians (size n/5).
- **Recursion control** → Deterministic pivot guarantees balance (between 30%–70%), avoiding bad partitions. Complexity O(n).

---

# 2. Recurrence Analysis

## 2.1 Merge Sort
- Recurrence: `T(n) = 2T(n/2) + Θ(n)`
- By Master Theorem → O(n log n)

## 2.2 Quick Sort
- Average recurrence: `E(T(n)) ≈ 2E(T(n/2)) + Θ(n)`
- On average same as Merge Sort → Θ(n log n)

## 2.3 Closest Pair of Points
- Recurrence: `T(n) = 2T(n/2) + Θ(n)`
- By Master Theorem → O(n log n)

## 2.4 Deterministic Select
- Recurrence: `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)`
- By Akra-Bazzi → O(n)

---

# 3. Constant-Factor Effects

## 3.1 Cache Locality
- **Merge Sort** → Poor locality due to buffer usage.
- **Quick Sort** → Good locality since partitioning is sequential.

## 3.2 Overhead Costs
- **Deterministic Select** → High overhead (groups of 5, multiple insertion sorts).
- **Closest Pair** → Floating point operations, ArrayList allocations, and Comparator overhead.

## 3.3 Garbage Collection
- **Closest Pair** → Heavy GC load due to frequent object allocation.
- **Merge Sort / Quick Sort / Select** → Mostly primitive arrays, minimal GC impact.

---

# 4. Summary

## Merge Sort
- Theoretical: Θ(n log n)
- Practical: Average performance, overhead from buffer copying
- Alignment: ✅ Matches theory

## Quick Sort
- Theoretical: Θ(n log n)
- Practical: High performance, good cache locality
- Alignment: ✅ Matches theory

## Closest Pair
- Theoretical: Θ(n log n)
- Practical: Slower due to GC and floating point overhead
- Alignment: ✅ Matches growth order

## Deterministic Select
- Theoretical: Θ(n)
- Practical: High constant factor, often slower than Quick Sort
- Alignment: ✅ Matches complexity, but slower in practice

---


---

## 5. Benchmark Graphs

### Time vs Input Size
![Time vs Size](images/time_vs_size.png)

### Comparisons vs Input Size
![Comparisons vs Size](images/comparisons_vs_size.png)

### Max Recursion Depth vs Input Size
![Max Depth vs Size](images/depth_vs_size.png)

### All Metrics Combined
![All Metrics](images/all_metrics.png)


---

## 6. How to Run

### Build & Compile
```bash
mvn clean compile

## Build
```bash
mvn test
