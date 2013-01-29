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
  * A string object to accumulate planet string representations.
  */
  class OrreryString {
    var string_accumulator = new StringBuilder()
  
    def append(planet: Planet) = {
      string_accumulator.append(planet.toString)
      string_accumulator.append("\n")
    }
  
    override def toString: String = {
      string_accumulator.toString
    }
  }
  
  /**
   * An orrery that maps a string accumulator function over the planets.
   */
  class MapOntoPlanetOrrery {
    val planets: List[Planet] = List()
  
    override def toString(): String = {
      var str_accumulator = new OrreryString
      planets map str_accumulator.append
      str_accumulator.toString
    }
  }


  /**
  * An orrery that uses the fold operation to implement toString.
  */
  class FoldPlanetOrrery {
    val planets: List[Planet] = List()
  
    override def toString(): String = {
      ("" /:planets)(_ + _.toString() + "\n")
    }
  }


  /**
   * An orrery that uses the foreach method of traversing the planet list.
   */
  class ForEachPlanetOrrery {
    val planets: List[Planet] = List()
  
    override def toString(): String = {
      var outstr = ""
      planets foreach (outstr += _.toString + "\n")
      outstr
    }
  }


  /** 
   * An orrery holds a collection of planets.
   *
   * This class defines the methods.  The planets value should be overridden
   * with a specific list of planets.
   */
  class Orrery extends ForEachPlanetOrrery {
    /**
     * Advance all planets in the orrery with the time step (measured in 
     * years.
     */
    def step(delta_t: Double) = {
      planets map {_.step(delta_t)}
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
  