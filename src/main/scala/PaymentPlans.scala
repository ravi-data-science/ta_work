import java.text.SimpleDateFormat
import java.util.Date

import net.liftweb.json.{DefaultFormats, parse}

// case class definition for single PaymentPlan
case class PaymentPlans(amount_to_pay: Double, debt_id: Long, id: Long, installment_amount: Double, installment_frequency: String, start_date: String) {


  var startDateStrToRealDate: Date = {

    val format = new SimpleDateFormat("yyyy-MM-dd")
    format.parse(start_date)
  }
}

// getPaymentPlans gives the list of paymentplans (source of data) reading from a http service , used net.liftweb.json for parsing
object PaymentPlans {
  def getPaymentPlans(): List[PaymentPlans] = {


    println("in PaymentPlans")
    val payment_plans_response = requests.get("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans")
    println(payment_plans_response.statusCode)
    // 200
    println(payment_plans_response.headers("content-type"))
    // Buffer("application/json; charset=utf-8")

    println(payment_plans_response.text)

    implicit val formats = DefaultFormats
    // json is a JValue instance
    val payment_plans_json = parse(payment_plans_response.text)
    //val debt_elements = (debts_json \\ "emailAccount").children


    val payment_plan_elements = (payment_plans_json).children
    //println(payment_plan_elements)
    val payment_plan_lst = for (acct <- payment_plan_elements) yield
      acct.extract[PaymentPlans]


    //println(payment_plan_lst)
    return payment_plan_lst

  }


}