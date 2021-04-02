import TA_Work1.processDebtPlanAndPayments

import collection.mutable.Stack
import org.scalatest.flatspec.AnyFlatSpec

// case class signatures
//Debts(id: Long, amount: Double)
//PaymentPlans(amount_to_pay: Double, debt_id: Long, id: Long, installment_amount: Double, installment_frequency: String, start_date: String)
//Payments(payment_plan_id: Long, amount: Double, date: String)

class TA_Work1Test extends AnyFlatSpec {

  "Debt having a payment plan" should "have the flag isInPaymentPlan set to true" in {

    val debts_lst: List[Debts] = TA_Work1.processDebtPlanAndPayments(List(Debts(0,100)),List(PaymentPlans(100,0,0,100,"WEEKLY","2020-09-28")),List(Payments(0,100,"2020-09-29")))

    assert(debts_lst(0).isInPaymentPlan==true)
  }

  "Debt Not having a payment plan" should "have the flag isInPaymentPlan set to false" in {

    val debts_lst: List[Debts] = TA_Work1.processDebtPlanAndPayments(List(Debts(0,100)),List(PaymentPlans(100,1,0,100,"WEEKLY","2020-09-28")),List(Payments(0,100,"2020-09-29")))

    assert(debts_lst(0).isInPaymentPlan==false)
  }

  "Debt remaining_amount " should " be zero when amount_to_pay in paymentplan is zero" in {

    val debts_lst: List[Debts] = TA_Work1.processDebtPlanAndPayments(List(Debts(0,0)),List(PaymentPlans(0,0,0,0,"WEEKLY","2020-09-28")),List(Payments(0,0,"2020-09-29")))

    assert(debts_lst(0).remaining_amount==0)
  }

  "Debt next_payment_due_date is null " should " if no payment plan" in {

    //single debit object with ID 0 doesn't have a payment plan
    val debts_lst: List[Debts] = TA_Work1.processDebtPlanAndPayments(List(Debts(0,0)),List(PaymentPlans(0,1,0,0,"WEEKLY","2020-09-28")),List(Payments(0,0,"2020-09-29")))

    assert(debts_lst(0).next_payment_due_date==null)
  }

  "Debt next_payment_due_date is null " should " if debt has been paid off" in {

    //single debit object with ID 0 have a payment plan but 'amount_to_pay' is zero
    val debts_lst: List[Debts] = TA_Work1.processDebtPlanAndPayments(List(Debts(0,0)),List(PaymentPlans(0,0,0,0,"WEEKLY","2020-09-28")),List(Payments(0,0,"2020-09-29")))

    assert(debts_lst(0).next_payment_due_date==null)
  }
}