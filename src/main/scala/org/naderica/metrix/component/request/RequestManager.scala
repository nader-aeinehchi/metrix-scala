package org.naderica.metrix.component.request
import scala.collection.mutable.ListBuffer

/** Iterates through a set of <code>RequestHandler</code>s that each handle the
  * request. Upon handling the request, a response is received. *
  *
  * <code>terminationStrategy</code> decides whether to terminate the iteration
  * when some condition (true) is met. For instance, if a response is an error,
  * the mange may decide to stop the iteration (and calling the remaining
  * <code>RequestHandler</code>s ) and return the already received responses.
  *
  * @param handlers
  * @param terminationStrategy
  */
class RequestManager[Q, L, R](
    val handlers: Set[RequestHandler[Q, L, R]],
    val terminationStrategy: (x: (L | R)) => Boolean = (x: (L | R)) => false
) {

  def handleRequest(request: Q): List[L | R] = {
    val responsez: ListBuffer[L | R] =
      ListBuffer.empty[L | R]

    var flag = true
    for (handler <- handlers if flag) do {
      val response = handler.handleRequest(request)

      responsez += response
      flag = !terminationStrategy(response)
    }

    return responsez.toList
  }
}

// trait RequestHandler[Q, P] {
//   def handleRequest(request: Q): P | ErrorResponse
// }

// class RequestManager[Q, P](
//     val handlers: Set[RequestHandler[Q, P]],
//     val errorStrategy: ErrorStrategy
// ) {

//   def handleRequest(request: Q): List[P | ErrorResponse] = {
//     val responsez: ListBuffer[P | ErrorResponse] =
//       ListBuffer.empty[P | ErrorResponse]

//     var flag = true
//     for (handler <- handlers if flag) do {
//       val response = handler.handleRequest(request)
//       responsez += response
//       // Terminate if condition is met then make the flag false
//       flag = !(_toBreak(response))
//     }

//     def _toBreak(response: P | ErrorResponse): Boolean = {
//       response.isInstanceOf[
//         ErrorResponse
//       ] & (errorStrategy == ErrorStrategy.StopOnError)
//     }

//     return responsez.toList
//   }

// }
