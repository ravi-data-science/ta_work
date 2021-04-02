# ta_work

How to run the program?

1) 'TA_Work1.scala' - In Master branch, Control program/ scala file having the main method (which calls the processDebtPlanAndPayments and prits results)
2) Run the main method in 'TA_Work1.scala' to print the results in json lines format
Example - 

{"id":1,"remaining_amount":75.0,"isInPaymentPlan":true,"next_payment_due_date":"2020-08-08T07:00:00Z","amount":100.0}
{"id":2,"remaining_amount":607.6700000000001,"isInPaymentPlan":true,"next_payment_due_date":"2020-08-08T07:00:00Z","amount":4920.34}
{"id":3,"remaining_amount":3082.585,"isInPaymentPlan":true,"next_payment_due_date":"2020-08-15T07:00:00Z","amount":12938.0}
{"id":4,"remaining_amount":9238.02,"isInPaymentPlan":false,"next_payment_due_date":null,"amount":9238.02}
3) Time spent - 
a) 2.5 hours for coding
b) 30 mins for documenting
4) 
Description of process - 
a) Case classes defined 
- Debts (getDebts method provides the list of debts reading from http service, parsed by net.liftweb.json package)
- PaymentPlans (getPaymentPlans method provides the list of PaymentPlans reading from http service, parsed by net.liftweb.json package)
- Payments (getPayments method provides the list of Payments reading from http service, parsed by net.liftweb.json package)


b) TA_Work1.scala - Processing object having main method which calls the method 'processDebtPlanAndPayments' 
c) Test case classes - TA_Work1Test.scala, DebtsTest.scala, PaymentPlansTest.scala, PaymentsTest.scala 

5) what you think you would have done differently given more time.
- More inline documentation
- Validation of test cases, Would have provided more test coverage , edge cases
- consider the edge cases to think about mutability vs immutability of data/ variables
- "io.circe" is also good package for json processing for scala projects. Need to review different standard packages for specific type of processing
- Need to review java calendar (better to use joda) for dates operations and test
- when billions of debts, paymentplans, payments are flowing into system what would be architecture / system parallel processing using tech like Spark processing
, ingesting data into a db, Integration of spark job/ program with analytical db like Apache Druid so that near real time analytical queries can be done etc
- would like to collaborate and do things and learn
6) Any design decisions or assumptions you made.
At the heart of system is Debt, payment plans, payments, payment dates and amounts rules thereof and requirements (accordingly logic) need to be reviewed.
