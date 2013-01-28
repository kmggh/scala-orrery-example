/**
 * A solar system orrery in Scala.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import orrery._

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
