/**
 * Run a full solar system orrery in Scala.
 *
 * Ken Guyton
 * Thu 2013-01-31 06:42:50 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import orrery._
import run_orrery_package._

/** 
 * A full orrery with nine bodies orbiting the sun.
 */
object FullOrrery extends OrreryOps {
  override var planets: List[Planet] = List(
    new Planet("Mercury", 0.39, 0.0),
    new Planet("Venus", 0.72, 0.0),
    new Planet("Earth", 1.0, 0.0),
    new Planet("Mars", 1.52, 0.0),
    new Planet("Jupiter", 5.2, 0.0),
    new Planet("Saturn", 9.5, 0.0),
    new Planet("Uranus", 19.2, 0.0),
    new Planet("Neptune", 30.1, 0.0),
    new Planet("Pluto", 39.5, 0.0)
    )
}


/*
 * Run the orrery class with a step size (years) for count times.
 * The count and step size can be specified on the command line.
 */
object RunFullOrrery extends RunOrrery {
  /*
   * Print the intro.
   */
  override def printIntro  = {
    println("RunFullOrrery using the orrery package.")
    println("Stepping the orrery " + step_count +
            " times after the initial position.")
    println("With a time step increment of " + delta_t + " year(s).\n")
  }

  /*
   * Print the initial position of the orrery.  Then step through each
   * delta_t for step_count times and print out the positions.
   */
  override def runAndPrintTheOrrery  = {
    val the_orrery = FullOrrery

    println(the_orrery)
    for (i <- 1 to step_count) {
      the_orrery.step(delta_t)
      println(the_orrery)
    }
  }
}