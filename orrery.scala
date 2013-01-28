/**
 * A solar system orrery in Scala.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

package orrery {

  /** 
   * An orrery holds a collection of planets.
   *
   * This class defines the methods.  The planets value should be overridden
   * with a specific list of planets.
   */
  class Orrery {
    val planets: List[Planet] = List()
  
    /**
     * Advance all planets in the orrery with the time step (measured in 
     * years.
     */
    def step(delta_t: Double) = {
      planets map {_.step(delta_t)}
    }
  
    override def toString(): String = {
      var outstr = ""
      planets foreach (outstr += _.toString + "\n")
      outstr
    }
  }
  
  
  /** 
   * A small orrery with three planets.
   */
  object SmallOrrery extends Orrery {
    override val planets: List[Planet] = List(
      new Planet("Earth", 1.0, 0.0),
      new Planet("Jupiter", 5.0, 0.0),
      new Planet("Neptune", 30.1, 0.0)
      )
  }
}
  