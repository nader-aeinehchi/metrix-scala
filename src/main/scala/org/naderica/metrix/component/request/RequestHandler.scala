package org.naderica.metrix.component.request

/** Handles a request of type <code>Q</code> and returns a response of type
  * either <code>L</code> (Left) or <code>R</code> (Rigth).
  *
  * Left or Right represent the outcome of a process. For example Left may be
  * the response upon successful handling of a request whilst Right may be an
  * error. Another example may be left or right nodes in a decision tree.
  */
trait RequestHandler[Q, L, R] {
  def handleRequest(request: Q): L | R
}
