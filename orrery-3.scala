/**
 * Second attempt at a solar system orrery in Scala.
 *
 * This one is maybe a bit more idiomatic since it creates an Orrery
 * class and then an immutable SmallOrrery object with the planets.
 * It adds yet a third method of looping over the list of planets
 * with a for comprehension.
 * 
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import math._


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


/**
* A string object to accumulate planet string representations.
*/
object OrreryString {
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
    var str_accumulator = OrreryString
    planets map str_accumulator.append
    str_accumulator.toString
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


/* 
 * Manually test the Planet class.
 */
object TestPlanets {
  def main(args: Array[String]) {
    val earth = new Planet("Earth", 1.0, 0.0)
    val jupiter = new Planet("Jupiter", 5.0, 0.0)

    def print_planets = {
      println
      println(earth)
      println(jupiter)
    }

    def step_planets(step: Double) = {
      earth.step(step)
      jupiter.step(step)
    }

    print_planets
    step_planets(0.5)
    print_planets
  }
}


/* 
 * Manually test the Orrery class.
 */
object TestOrrery {
  def main(args: Array[String]) {
    val orrery = SmallOrrery
    println(orrery)
    orrery.step(0.5)
    println(orrery)
  }
}


/*
 * Run the orrery class with a step size (years) for count times.
 * The count and step size can be specified on the command line.
 */
object RunOrrery {
  def main(args: Array[String]) {
    var delta_t = 0.5
    var step_count = 5
    val orrery = SmallOrrery

    if (args.length == 1 && args(0) == "--help") {
      println("scala RunOrrery [[delta_t] count]")
      System.exit(0)
    }

    if (args.length == 1) {
      delta_t = 0.5
      step_count = args(0).toInt
      println("Set step_count to " + step_count)
    }
    else if (args.length == 2) {
      delta_t = args(0).toDouble
      step_count = args(1).toInt
    }

    println("Stepping the orrery " + step_count +
            " times after the initial position.")
    println("With a time step increment of " + delta_t + " year(s).\n")

    println(orrery)
    for (i <- 1 to step_count) {
      orrery.step(delta_t)
      println(orrery)
    }
  }
}
