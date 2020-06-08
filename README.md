# almost-trans-check

`edu.mines.jtk.util.Almost` `Comparable.compareTo` contract validation.  

# Requirements

 - JVM 1.8
 - SBT 1.x

# Running

```console
sbt test
```

# Results

```console
+ TransitivitySpec
  + Test `edu.mines.jtk.util.Almost::cmp` symmetry (anticommutation)
  + Test `edu.mines.jtk.util.Almost::cmp` transitivity
  + Test `edu.mines.jtk.util.Almost::cmp` 'zero case'
Ran 3 tests in 1 h 33 m 34 s: 3 succeeded, 0 ignored, 0 failed
```
