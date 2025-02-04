trait Merger[T]{
  self: T =>

  def merge(other:T):T = {
    return self   
  }
}


case class MessageHeader(headerName: String) extends Merger[MessageHeader]
case class MessageBody(val bodyName: String) extends Merger[MessageBody]
case class Message[H,B](header:H, body:B)

case class BaseRequestHeader(headerName:String, title:String, id:String, inResponseToId: String) extends Merger[BaseRequestHeader]
case class BaseRequestBody(bodyName:String) extends Merger[BaseRequestBody]
// case class BaseRequest[H,B](header:H, body:B) extends Message[H,B](header, body)

class LmRequestBody(bodyName: String, text:String, expectedText:String, expectedReferences:List[String]) extends MessageBody(bodyName)
// class LmRequest(header:BaseRequestHeader, body: LmRequestBody) extends BaseRequest[BaseRequestHeader, LmRequestBody](header, body)







// enum Default[T]{
//     case default extends T()
// }



/**
  * Motivation:
    1. Add specific header and body to each layer/use-case
    2. Provide default value for header or body or the class in each layer/use-case
    3. A default value must be marked as default
  */