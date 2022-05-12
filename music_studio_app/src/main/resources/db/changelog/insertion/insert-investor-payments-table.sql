INSERT INTO investor_payments (investor_id, investor_bill_id, amount)
VALUES ((SELECT investors.id
         FROM investors
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Bill')),
        (SELECT investor_bills.id
         FROM investor_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 100000),
       ((SELECT investors.id
         FROM investors
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon')),
        (SELECT investor_bills.id
         FROM investor_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 1)), 35000);