package atc

import edu.mines.jtk.util.Almost
import zio.test.Assertion._
import zio.test._

/**
 * Check if `Almost.cmp` adheres to `Comparable.compareTo` contract.
 */
object AlmostCmpComparableSpec extends DefaultRunnableSpec {

  @inline
  def cmp(f1: Float, f2: Float) = Almost.FLOAT.cmp(f1, f2)

  def spec = suite("TransitivitySpec")(

    /**
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     */
    testM("Test `edu.mines.jtk.util.Almost::cmp` symmetry (anticommutation)") {
      checkN(100_000_000)(Gen.anyFloat, Gen.anyFloat) { (x, y) =>
        val xy = cmp(x, y)
        val yx = cmp(y, x)
        assert(xy)(equalTo(-yx))
      }
    },

    /**
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     */
    testM("Test `edu.mines.jtk.util.Almost::cmp` transitivity") {
      checkN(100_000_000)(Gen.anyFloat, Gen.anyFloat, Gen.anyFloat) { (x, y, z) =>
        val xy = cmp(x, y)
        val yz = cmp(y, z)
        val xz = cmp(x, z)
        assert(xy)(equalTo(yz)) ==> assert(xy)(equalTo(xz))
      }
    },

    /**
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     */
    testM("Test `edu.mines.jtk.util.Almost::cmp` 'zero case'") {
      checkN(100_000_000)(Gen.anyFloat, Gen.anyFloat, Gen.anyFloat) { (x, y, z) =>
        val xy = cmp(x, y)
        val yz = cmp(y, z)
        val xz = cmp(x, z)
        assert(xy)(equalTo(0)) ==> assert(xz)(equalTo(yz))
      }
    }
  )

}

