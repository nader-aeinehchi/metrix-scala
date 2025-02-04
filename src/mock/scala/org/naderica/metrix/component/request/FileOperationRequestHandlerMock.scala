package org.naderica.metrix.component.request

import org.naderica.metrix.component.request.FileOperationErrorMock.*
import org.naderica.metrix.component.request.FileOperationRequestMock.*
import org.naderica.metrix.component.request.FileOperationResponseMock.*

enum FileOperationRequestHandlerMock(
    request: FileOperationRequestMock,
    response: FileOperationResponseMock | FileOperationErrorMock
) extends RequestHandler[
      FileOperationRequestMock,
      FileOperationResponseMock,
      FileOperationErrorMock
    ] {

  override def handleRequest(
      request: FileOperationRequestMock
  ): FileOperationResponseMock | FileOperationErrorMock = request match {
    case this.request => this.response
    case _            => IllegalOperationError

  }

  case ReadRequestHandler
      extends FileOperationRequestHandlerMock(ReadRequest, CreatedResponse)
  case WriteRequestHandler
      extends FileOperationRequestHandlerMock(WriteRequest, ExecutedResponse)
  case IllegalRequestHandler
      extends FileOperationRequestHandlerMock(
        IllegalRequest,
        IllegalOperationError
      )
}
