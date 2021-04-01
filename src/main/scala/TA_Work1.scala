import java.text.SimpleDateFormat
import java.util.Date

import net.liftweb.json.DefaultFormats
import net.liftweb.json._
import java.util.Calendar
import java.time.LocalDateTime
import scala.collection.mutable._
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import net.liftweb.json.JsonAST
import net.liftweb.json.JsonDSL._
//import net.liftweb.json.Printer.{compact,pretty}

object TA_Work1 {

  def main(args: Array[String]): Unit = {

    val debts_lst = Debts.getDebts()
    println("debts list objects:" + debts_lst)

    val paymentplans_lst = PaymentPlans.getPaymentPlans()
    println("payment plans list objects:" + paymentplans_lst)

    val payments_lst = Payments.getPayments()
    println("payment list objects:" + payments_lst)


    val cal = Calendar.getInstance

//Iterating through all Debt objects
    // for calculating "is_in_payment_plan"
    for (debt <- debts_lst) {

      //Iterating through all PaymentPlans objects
      for (pplan <- paymentplans_lst) {
        //println(pplan.debt_id)

        if (pplan.debt_id == debt.id && pplan.amount_to_pay != 0 && pplan.installment_amount != 0) {

          debt.isInPaymentPlan = true

          //Iterating through all Payments objects
          for (pments <- payments_lst) {

            //println("In payments loop ##pments.payment_plan_id:"+pments.payment_plan_id)
            //println("In payments loop ##pplan.id:"+pplan.id)
            if (pments.payment_plan_id == pplan.id) {
              debt.remaining_amount = pplan.amount_to_pay - pments.amount

              cal.setTime(new Date(pments.dateStrToRealDate.getTime))
              //println("payments loop ##pments.dateStrToRealDate:"+pments.dateStrToRealDate)
              if (pplan.installment_frequency.equals("WEEKLY")) {
                cal.add(Calendar.DATE, 7)
                //println("payments loop after setting next date weekly ##pments.dateStrToRealDate:"+pments.dateStrToRealDate)
                debt.next_payment_due_date = pments.dateStrToRealDate

              } else if (pplan.installment_frequency.equals("BI_WEEKLY")) {
                cal.add(Calendar.DATE, 14)
                //println("payments loop after setting next bi weekly ##pments.dateStrToRealDate:"+pments.dateStrToRealDate)
                debt.next_payment_due_date = pments.dateStrToRealDate
              }
            }
          }
        }

      }
      println("checking debt object after setting isInPaymentPlan:" + debt)

    }
    println("debt list after making all relevant computations::"+debts_lst)
    implicit val formats = DefaultFormats
    val debtsLstJson = write(debts_lst)
    println("debtsLstJson::"+debtsLstJson.toString)
    //println(compact(JsonAST.render(json)))

  }//main method
}//object TA_Work1







