import java.util.Date

import net.liftweb.json.{DefaultFormats, parse}

case class Debts(id: Long, amount: Double) {
  var isInPaymentPlan: Boolean = false
  var remaining_amount = amount;
  var next_payment_due_date: Date = null;

  override def toString = "id:" + id + "\t" + "amount:" + "\t" + amount + "\t" + "isInPaymentPlan:" + isInPaymentPlan+"\t"+"remaining_amount:"+remaining_amount+"\t"+"next_payment_due_date:"+next_payment_due_date
}

object Debts {

  def getDebts(): List[Debts] = {


    println("in Debts")
    val debts_response = requests.get("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts")
    println(debts_response.statusCode)
    // 200
    println(debts_response.headers("content-type"))
    // Buffer("application/json; charset=utf-8")

    println(debts_response.text)

    implicit val formats = DefaultFormats
    // json is a JValue instance
    val debts_json = parse(debts_response.text)
    //val debt_elements = (debts_json \\ "emailAccount").children
    val debt_elements = (debts_json).children
    //println(debt_elements)
    val debts_lst = for (acct <- debt_elements)
      yield acct.extract[Debts]


    //println(debts_lst)
    return debts_lst

  }

}
