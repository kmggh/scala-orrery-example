/**
 * First attempt at a solar system orrery in Scala.
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
 */ 
class Planet(namein: String, rin: Double, posin: Double) {
  var name: String = namein
  var r: Double = rin
  var pos: Double = posin

  val two_pi = math.Pi * 2.0

  /** 
   * Compute the period of the planet's orbit.
   * The units of the period are years.
   */
  def period(): Double = {
    pow(r, 1.5)
  }

  /** 
   * Advance the planet in it's orbit an angle corresponding to time
   * delta-t.
   */
  def step(delta_t: Double) = {
    val delta_theta = delta_t * two_pi / period
    pos += delta_theta

    // Normalize the angle.
    while (pos >= two_pi) {
      pos -= two_pi
    }
  }

  /**
   * Convert the position, which is in radians, to degrees.
   */
  def pos_in_degrees = pos * 180 / math.Pi

  override def toString(): String =
    name + " " + r +
    " period " + "%.2f".format(period) +
    " at " + "%.2f".format(pos_in_degrees)
}


/** 
 * An orrery holds a collection of planets.
 */
class Orrery() {
  val planets: List[Planet] = List(
    new Planet("Earth", 1.0, 0.0),
    new Planet("Jupiter", 5.0, 0.0),
    new Planet("Neptune", 30.1, 0.0)
    )

  /**
   * Advance all planets in the orrery with the time step.
   */
  def step(delta_t: Double) = {
    planets map {_.step(delta_t)}
  }

  override def toString(): String = {
    // Fold technique is a bit obscure:
    // ("" /:planets)(_ + _.toString() + "\n")
    var outstr = ""
    planets foreach (outstr += _.toString + "\n")
    outstr
  }
}


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


object TestOrrery {
  def main(args: Array[String]) {
    val orrery = new Orrery()
    println(orrery)
    orrery.step(0.5)
    println(orrery)
  }
}


object RunOrrery {
  def main(args: Array[String]) {
    var delta_t = 0.5
    var step_count = 5
    val orrery = new Orrery()

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

    println("Stepping the orrery " + step_count + " times")
    println("With a time step increment of " + delta_t + " year(s).\n")

    for (i <- 1 to step_count) {
      orrery.step(delta_t)
      println(orrery)
    }
  }
}