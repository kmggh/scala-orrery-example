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

    println("RunOrrery using the orrery package.")

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
