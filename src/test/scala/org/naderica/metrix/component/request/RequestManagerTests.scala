package org.naderica.metrix.component.request
import munit.FunSuite

import org.naderica.metrix.component.request.FileOperationRequestMock.*
import org.naderica.metrix.component.request.FileOperationResponseMock.*
import org.naderica.metrix.component.request.FileOperationErrorMock.*
import org.naderica.metrix.component.request.FileOperationRequestHandlerMock.*

class RequestManagerTests extends FunSuite {
  val handlers = Set[RequestHandler[
    FileOperationRequestMock,
    FileOperationResponseMock,
    FileOperationErrorMock
  ]](
    ReadRequestHandler,
    WriteRequestHandler,
    IllegalRequestHandler
  )

  val requestManager = RequestManager[
    FileOperationRequestMock,
    FileOperationResponseMock,
    FileOperationErrorMock
  ](handlers = handlers)

  val terminationStrategy =
    (x: FileOperationResponseMock | FileOperationErrorMock) => {
      x match {
        case x: FileOperationErrorMock => true
        case _                         => false
      }
    }

  val requestManagerWithTerminationStrategy = RequestManager[
    FileOperationRequestMock,
    FileOperationResponseMock,
    FileOperationErrorMock
  ](handlers = handlers, terminationStrategy = terminationStrategy)

  test("RequestManager should return three responses") {

    val responses =
      requestManager.handleRequest(ReadRequest)
    print("responses:" + responses)

    assertEquals(responses.length, 3)
    assert(responses.contains(CreatedResponse))
    assert(responses.contains(IllegalOperationError))

  }

  test("RequestManager should drop error responses") {

    val responses =
      requestManagerWithTerminationStrategy.handleRequest(ReadRequest)
    print("responses:" + responses)

    assertEquals(responses.length, 2)
    assert(responses.contains(CreatedResponse))
    assert(responses.contains(IllegalOperationError))

  }
}
