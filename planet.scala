/**
 * A solar system orrery in Scala.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import math._

package orrery {


  /**
   * A planet has a semi-major axis r, a name and a position.  
   * The units of r are astronomical units.
   * The units of pos are degrees.
   */ 
  class Planet(namein: String, rin: Double, posin: Double) {
    var name: String = namein
    var r: Double = rin
    var pos: Double = posin
  
    /** 
     * Compute the period of the planet's orbit.
     * The units of the period are years.
     * This is where all of the astronomy happens since the period is computed
     * using Kepler's Law.
     */
    def period(): Double = {
      pow(r, 1.5)
    }
  
    /** 
     * Advance the planet in it's orbit an angle corresponding to time
     * delta-t (measured in years).
     */
    def step(delta_t: Double) = {
      val delta_theta = 360.0 / period * delta_t
      pos += delta_theta
  
      // Normalize the angle.
      while (pos >= 360.0) {
        pos -= 360.0
      }
    }
  
    override def toString(): String =
      name + " " + r +
      " period " + "%.2f".format(period) +
      " at " + "%.2f".format(pos)
  }
}
  