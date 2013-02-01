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
  override def theOrrery: OrreryOps = {
    FullOrrery
  }
}