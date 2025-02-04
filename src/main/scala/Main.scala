import upickle.default.*

class JsonTest {
  val jsonString =
    """{"name": "Peter", "age": 13, "pets": ["Toolkitty", "Scaniel"]}"""
  val json: ujson.Value = ujson.read(jsonString)
  println(json("name").str)
  println(json)

  json("name") = "Alex"
  json("nickname") = "Pete"
  json("pets").arr.remove(1)

// Write it back to a String
  val result: String = ujson.write(json)
  println(result)

}

@main def hello(): Unit =
  println("Hello world!")
  println(msg)

  val jsonTest = JsonTest()

def msg = "I was compiled by Scala 3. :)"
