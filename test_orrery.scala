/**
 * A solar system orrery in Scala.
 *
 * Ken Guyton
 * Sat 2013-01-19 13:38:54 -0500
 * 
 * Copyright (C) 2013 by Ken Guyton.  All Rights Reserved.
 */

import orrery._ 

/**
 * Run a small orrery for one step.
 */
object TestOrrery {
  def main(args: Array[String]) {
    println("Running TestOrrery using the orrery package.\n\n")

    val orrery = SmallOrrery
    println(orrery)
    orrery.step(0.5)
    println(orrery)
  }
}


