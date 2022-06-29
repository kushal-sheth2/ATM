# ATM
This project implements all the major functionalities of ATM like Withdraw, Deposit, Transfer money as well as shows transaction history. 

In this project we have 5 different classes named as ATM, User, Account, Transaction, Bank. 

ATM class: In this class I have included the main function, this class implements kind of front end. Using this class we call different methods based on user's requirements. 

User class: This class stores user(rather account holder)'s details as first name, last name, pin of particular user, uuid(unique user ID), list of acconts that user have, etc. 

Account : This class stores all the account details as account holder name, balance, transactions performed on this account, etc.

Transaction: This class stores details about particular transaction like who performed, when performed, from which account to which accout transaction performed, etc. 

Bank: This class stores bank name, users, accounts. We can say that Bank class is used for backend side(bank side). 
