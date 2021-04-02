import java.util.{Calendar, Date}

import org.json4s.native.Json

object TA_Work1 {

  def processDebtPlanAndPayments(debts_lst: List[Debts], paymentplans_lst: List[PaymentPlans], payments_lst: List[Payments]): List[Debts] = {

    val cal = Calendar.getInstance

    //Iterating through all Debt objects
    // for calculating "is_in_payment_plan" & remaining_amount
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
      //println("checking debt object after setting isInPaymentPlan:" + debt)

    }

    return debts_lst
  }

  def main(args: Array[String]): Unit = {

// Data fetched from http service json processing with net.liftweb.json package
    val debts_lst = Debts.getDebts()
    println("debts list objects:" + debts_lst)

    val paymentplans_lst = PaymentPlans.getPaymentPlans()
    println("payment plans list objects:" + paymentplans_lst)

    val payments_lst = Payments.getPayments()
    println("payment list objects:" + payments_lst)
    val debts_lst_after_processing = processDebtPlanAndPayments(debts_lst, paymentplans_lst, payments_lst)

    println("debt list after making all relevant computations::")
    println(debts_lst_after_processing)
//    for (debt <- debts_lst_after_processing) {
//      println(debt)
//    }

    //printing the result in requisite format
    print("#### Resultant Debt objects ###")
    printDebtsToJson(debts_lst_after_processing)

  } //main method

  //helper method to print the List of Debts objects to json
  def printDebtsToJson(debtslst: List[Debts]): Unit = {

    for (t <- debtslst) {
      val debtsMap = Map("id" -> t.id, "amount" -> t.amount,
        "isInPaymentPlan" -> t.isInPaymentPlan,
        "remaining_amount" -> t.remaining_amount,
        "next_payment_due_date" -> t.next_payment_due_date)
      println(Json(org.json4s.DefaultFormats).write(debtsMap))
    }


  }
} //object TA_Work1







