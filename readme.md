Sure, here is an example README.md file for your project:

# Shop Payment System

This project simulates a payment system for a shop, where customers can make payments either online or in-store with e-money using their bank account. The system calculates bonuses based on the amount paid and the payment location, and also applies transaction fees for small payments.

## Installation

- Run `mvn clean install` command at the root of the project to build the project and install dependencies.

## Usage

- Ensure that the project is built successfully
- Run the project by executing the `java -jar <filename>.jar` command
- Use the following endpoints to make payments and get account information:
    - `/api/payment/{location}/{amount}` - Make a payment with specified location and amount
    - `/api/bankAccountOfEMoney` - Get the bonus amount of the account
    - `/api/money` - Get the e-money amount of the account


