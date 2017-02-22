package org.change.v2.analysis.processingmodels.instructions

import org.change.v2.analysis.memory.State
import org.change.v2.analysis.processingmodels.{LocationId, Instruction}

/**
 * Author: Radu Stoenescu
 * Don't be a stranger,  symnetic.7.radustoe@spamgourmet.com
 */
case class Forward(place: LocationId) extends Instruction {
  /**
   *
   * A state processing block produces a set of new states based on a previous one.
   *
   * @param s
   * @return
   */
  override def apply(s: State, v: Boolean): (List[State], List[State]) = {
//    if (place.contains("Vlan"))
      println(s.history.mkString(" ") + place)
    (List((if (v) s.addInstructionToHistory(this) else s).forwardTo(place)), Nil)
  }
}
