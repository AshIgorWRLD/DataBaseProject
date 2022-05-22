INSERT INTO bills_of_investors (investor_payment_id, investor_bill_id)
VALUES ((SELECT investor_payments.id
         FROM investor_payments
                  INNER JOIN investors ON (investors.id = investor_payments.investor_id)
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon' AND investor_payments.amount = 35000)),
        (SELECT investor_bills.id
         FROM investor_bills
         WHERE investor_bills.month_to_work = 1)),
       ((SELECT investor_payments.id
         FROM investor_payments
                  INNER JOIN investors ON (investors.id = investor_payments.investor_id)
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Bill' AND investor_payments.amount = 100000)),
        (SELECT investor_bills.id
         FROM investor_bills
         WHERE investor_bills.month_to_work = 3));