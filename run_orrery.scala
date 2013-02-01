/**
 * A solar system orrery in Scala.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import orrery._


package run_orrery_package {
  /*
   * Run the orrery class with a step size (years) for count times.
   * The count and step size can be specified on the command line.
   */
  class RunOrrery {
    /**
     * Parse the args to get the step_count and delta_t time step.
     */
    var step_count = 5
    var delta_t = 0.5
  
    def parseArgs(args: Array[String]) = {
      if (args.length == 1 && args(0) == "--help") {
        println("scala RunOrrery [[delta_t] count]")
        System.exit(0)
      }
  
      if (args.length == 1) {
        step_count = args(0).toInt
        println("Set step_count to " + step_count)
      }
      else if (args.length == 2) {
        delta_t = args(0).toDouble
        step_count = args(1).toInt
      }
    }
  
    /**
      * Print the intro.
      */
    def printIntro  = {
      println("RunOrrery using the orrery package.")
      println("Stepping the orrery " + step_count +
              " times after the initial position.")
      println("With a time step increment of " + delta_t + " year(s).\n")
    }

    /**
      * Use a particular type of orrery.
      */
    def theOrrery: OrreryOps = {
      SmallOrrery
    }
  
    /*
     * Print the initial position of the orrery.  Then step through each
     * delta_t for step_count times and print out the positions.
     */
    def runAndPrintTheOrrery(the_orrery: OrreryOps)  = {
      println(the_orrery)
      for (i <- 1 to step_count) {
        the_orrery.step(delta_t)
        println(the_orrery)
      }
    }

    def main(args: Array[String]) {
      parseArgs(args)
      printIntro
      runAndPrintTheOrrery(theOrrery)
    }
  }
}