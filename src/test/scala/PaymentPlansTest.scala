import collection.mutable.Stack
import org.scalatest.flatspec.AnyFlatSpec

class PaymentPlansTest extends AnyFlatSpec {

  "PaymentPlans Is Not An empty List" should "Have size Greater Than 0" in {
    assert(PaymentPlans.getPaymentPlans().size > 0)
  }

  it should "Payment Plans List throw an IndexOutOfBoundsException when trying to access any element outside of the Size" in {
    val sizeOfPaymentPlansList = PaymentPlans.getPaymentPlans().size
    assertThrows[IndexOutOfBoundsException] {
      PaymentPlans.getPaymentPlans()(sizeOfPaymentPlansList+1)
    }
  }
}
