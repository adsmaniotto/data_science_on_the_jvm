object ZillowAPIRequest extends App {

  class ZillowAPIRequest(id: String, state: String) {
    val query = s"zws-id=$id&state=$state"
    val url = s"http://www.zillow.com/webservice/GetRegionChildren.htm?$query"

    def hitAPI(): String = {
      scala.io.Source.fromURL(url).mkString
    }
  }

  val request = new ZillowAPIRequest("X1-ZWz1h0z8a322ob_634pb", "IL")
  val result = request.hitAPI()
  println(result)
}
