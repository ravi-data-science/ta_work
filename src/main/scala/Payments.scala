import java.text.SimpleDateFormat
import java.util.Date

import net.liftweb.json.{DefaultFormats, parse}

case class Payments(payment_plan_id: Long, amount: Double, date: String) {
  var dateStrToRealDate: Date = {

    val format = new SimpleDateFormat("yyyy-MM-dd")
    format.parse(date)
  }

}

object Payments {
  def getPayments(): List[Payments] = {


    println("in Payments")
    val payments_response = requests.get("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments")
    println(payments_response.statusCode)
    // 200
    println(payments_response.headers("content-type"))
    // Buffer("application/json; charset=utf-8")

    println(payments_response.text)

    implicit val formats = DefaultFormats
    // json is a JValue instance
    val payments_json = parse(payments_response.text)
    //val debt_elements = (debts_json \\ "emailAccount").children
    val payments_elements = (payments_json).children
    //println(debt_elements)
    val payments_lst = for (acct <- payments_elements)
      yield acct.extract[Payments]


    //println(debts_lst)
    return payments_lst

  }

}
