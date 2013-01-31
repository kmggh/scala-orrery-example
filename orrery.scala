/**
 * A solar system orrery in Scala.
 * 
 * Several approaches to the task of traversing a list of planets are
 * provided below as illustrations. Each approach is represented as a
 * trait.
 * 
 * The trait OrreryOps can be changed to use a particular trait.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

package orrery {
  /**
  * A string object to accumulate planet string representations,
  * used by the MapOntoPlanets trait below.
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
   * Map a string accumulator function over the planets.
   */
  trait MapOntoPlanets {
    var planets: List[Planet]
  
    def toPlanetString(): String = {
      var str_accumulator = new OrreryString
      planets map str_accumulator.append
      str_accumulator.toString
    }
  }

  /**
   * Map a simple accumulating function onto the planets.
   */
   trait SimpleMapOntoPlanets {
     var planets: List[Planet]

     /**
      * Use map to make a list of strings, then join them.
      */
     def toPlanetString: String = {
       var planet_strings = planets map (_.toString)
       planet_strings.mkString("", "\n", "\n")
     }
   }


  /**
   * Fold a string accumulating operation over the planets.
   */
  trait FoldPlanets {
    var planets: List[Planet]
  
    def toPlanetString(): String = {
      ("" /:planets)(_ + _.toString() + "\n")
    }
  }


  /**
   * Use foreach to accumulate the planet strings.
   */
  trait ForEachPlanet {
    var planets: List[Planet]
  
    def toPlanetString(): String = {
      var outstr = ""
      planets foreach (outstr += _.toString + "\n")
      outstr
    }
  }


  /** 
   * Operations for an orrery.
   */
  trait OrreryOps extends SimpleMapOntoPlanets {
    /**
     * Advance all planets in the orrery with the time step (measured in 
     * years.
     */
    def step(delta_t: Double) = {
      planets map {_.step(delta_t)}
    }

    override def toString: String = {
      toPlanetString
    }
  }
  
  
  /** 
   * A small orrery with three planets.
   */
  object SmallOrrery extends OrreryOps {
    override var planets: List[Planet] = List(
      new Planet("Earth", 1.0, 0.0),
      new Planet("Jupiter", 5.0, 0.0),
      new Planet("Neptune", 30.1, 0.0)
      )
  }

}
  