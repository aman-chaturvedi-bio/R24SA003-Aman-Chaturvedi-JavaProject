# R24SA003 — Aman Chaturvedi
## Java Programming Mini Project — Bank Account Management System

**University:** REVA University  
**Program:** B.Sc. (BSTCs)  
**Semester:** V  
**Subject:** Java Programming  
**SRN:** R24SA003  
**Student:** Aman Chaturvedi  
**Project:** Bank Account Management System

## 1. Project Overview

This is a console-based Bank Account Management System implemented in Java. It models customers, savings accounts, current accounts and transactions using object-oriented programming. The application supports customer registration, account creation, deposits, withdrawals, transfers, transaction history, interest/service-charge calculation and system statistics.

The implementation follows the assignment requirement for a package-organized Java project with at least two custom packages. The source is organized into `model`, `service`, `util` and `app` packages.

## 2. Project Structure

```text
R24SA003_Aman_Chaturvedi_JavaProject/
├── src/
│   └── com/reva/bank/
│       ├── app/
│       │   └── Main.java
│       ├── model/
│       │   ├── Account.java
│       │   ├── AccountType.java
│       │   ├── CurrentAccount.java
│       │   ├── Customer.java
│       │   ├── Person.java
│       │   ├── SavingsAccount.java
│       │   ├── Transaction.java
│       │   ├── TransactionId.java
│       │   └── TransactionType.java
│       ├── service/
│       │   ├── BankService.java
│       │   └── Payable.java
│       └── util/
│           └── InputUtil.java
├── docs/
│   ├── README.md
│   └── sample_output.txt
└── bin/
```

## 3. Features Implemented

- Customer registration
- Savings and current account creation
- Account listing
- Deposit and withdrawal operations
- Inter-account money transfer
- Transaction history
- Interest/service-charge calculation
- System statistics
- Input validation
- Formatted console output
- Object-oriented inheritance and polymorphism

## 4. Mandatory Feature Traceability

| Assignment Requirement | Implementation | Location |
|---|---|---|
| 1. 3–4 classes with encapsulation | Private fields with public getters/setters | `Customer.java`, `Account.java`, `Transaction.java` |
| 2. Data types, constants, variable scope | `int`, `double`, `String`, `boolean`, `final` constants, instance/static/local variables | `Account.java`, `Main.java`, `Customer.java` |
| 3. Operators and precedence | Arithmetic expressions in payable calculation and balance/statistics calculations | `BankService.java`, `Main.java` |
| 4. Type conversion/casting | `int roundedBalance = (int) totalBalance` | `Main.java` |
| 5. Enum | `AccountType`, `TransactionType` | `AccountType.java`, `TransactionType.java` |
| 6. Control flow and jump statements | `if/else`, `switch`, loops, `break`, `continue`, `return` | `Main.java`, `BankService.java` |
| 7. Array of objects | `Account[]` returned through `getAccountArray()` | `BankService.java` |
| 8. Console I/O and formatting | `Scanner`, `System.out`, `printf`, `String.format` | `Main.java`, `InputUtil.java`, model classes |
| 9. Constructor overloading | Default and parameterized constructors | `Person.java`, `Customer.java`, `Account.java`, `SavingsAccount.java`, `CurrentAccount.java` |
| 10. Method overloading | `deposit(double)` and `deposit(int)` | `Account.java` |
| 11. Static fields/methods | Customer/account counters and transaction counter | `Customer.java`, `Account.java`, `TransactionId.java` |
| 12. `this` reference | Constructor assignments and constructor chaining | `Person.java`, `Customer.java`, `Account.java` |
| 13. String methods | `trim()`, `equalsIgnoreCase()`, `isBlank()`, `isEmpty()` | `BankService.java`, `Main.java` |
| 14. Base class + 2 subclasses | `Account` → `SavingsAccount`, `CurrentAccount`; `Person` → `Customer` | `Account.java`, `SavingsAccount.java`, `CurrentAccount.java`, `Person.java`, `Customer.java` |
| 15. `super` keyword | Parent constructor and parent method access | `Customer.java`, `SavingsAccount.java` |
| 16. Method overriding + dynamic binding | Overridden account-type and interest methods accessed through `Account` references | `SavingsAccount.java`, `CurrentAccount.java`, `Main.java` |
| 17. Abstract class + abstract method | `Account` and `Person` contain abstract methods | `Account.java`, `Person.java` |
| 18. Interface | `Payable` implemented by `BankService` and accessed through `Payable` reference | `Payable.java`, `BankService.java`, `Main.java` |
| 19. Object method overriding | `toString()` and `equals()` overridden | `Customer.java`, `Transaction.java`, `Account.java` |
| 20. Final method/class | Final `getAccountSummary()` and final `TransactionId` utility class | `Account.java`, `TransactionId.java` |
| 21. Custom packages | `com.reva.bank.app`, `.model`, `.service`, `.util` | `src/` package hierarchy |

## 5. Important Code Locations

- Encapsulation and account abstraction: `src/com/reva/bank/model/Account.java`
- Inheritance: `src/com/reva/bank/model/SavingsAccount.java` and `CurrentAccount.java`
- Customer model: `src/com/reva/bank/model/Customer.java`
- Service layer: `src/com/reva/bank/service/BankService.java`
- Interface: `src/com/reva/bank/service/Payable.java`
- Input handling: `src/com/reva/bank/util/InputUtil.java`
- Application entry point: `src/com/reva/bank/app/Main.java`

## 6. Compilation and Execution

From the project root:

```text
javac -d bin $(find src -name "*.java")
java -cp bin com.reva.bank.app.Main
```

On Windows Command Prompt, compile all source files using the IDE or PowerShell equivalent and run:

```text
java -cp bin com.reva.bank.app.Main
```

The project was compiled successfully with standard `javac` and executed successfully using the `Main` class.

## 7. Sample End-to-End Run

A captured run is included in `docs/sample_output.txt`. The demonstrated flow includes:

1. Viewing seeded accounts.
2. Depositing money.
3. Withdrawing money.
4. Transferring money between accounts.
5. Viewing transaction history.
6. Viewing system statistics.
7. Exiting the application.

## 8. UML Class Structure

```text
                    <<abstract>>
                      Person
                         |
                      Customer

                    <<abstract>>
                      Account
                    /         \
                   /           \
        SavingsAccount       CurrentAccount

Payable <................. BankService

Account --> Customer
Account --> Transaction
Account --> AccountType
Transaction --> TransactionType
```

## 9. Frontend Decision

The assignment explicitly specifies a **console-based real-world application**, so the submitted implementation uses a polished console interface rather than adding a separate GUI. This keeps the project aligned with the stated submission requirement while still providing a clear user-facing menu and formatted output.

## 10. Submission Checklist

- [x] Complete Java source code
- [x] Custom package structure
- [x] Mandatory Unit-I and Unit-II OOP features
- [x] README/traceability report
- [x] Sample console output
- [x] UML class structure
- [x] Compiles using standard Java tools
- [x] Runs without compilation errors
- [x] No unnecessary code comments; only the single final-class rationale required by the assignment is retained
