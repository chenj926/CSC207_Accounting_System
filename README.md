# CSC207_Accounting_System
## Project Description
The accounting system will allow users to manage financial records, generate informal financial reports, and ensure compliance with accounting standards. Each user can create a personal account and open multiple financial accounts. Joint accounts allow multiple users to record and plan cash flows in a synchronized account. Inflow and outflow of cash can be manually recorded in accounts with corresponding annotations. The system can generate both real time and monthly informal financial records for user interpretation. Correspondingly, automated financial planning will plan the budget provided by the user periodically, notifications will be sent regarding the status of current budget usage. Import and export features allows users to import their past records, and obtain generated pdf or csv files from their current records.

This project implements Clean Architecture and SOLID design principles to achieve segregation.

## Current Progress
Currently, the basic functions including, sign up, login, logout and transactions are implemented and integrated in to a user interface. Both periodic and one time transactions are put into use. The periodic transaction function can be recorded but cannot be updated automatically yet, we plan to utilize an external API to achieve automatic beckground update. We plan to focus on implementing the shared account feature in the next phase.

## Executing the Software
To access the user interface, navigate to src/main/app/Main.java and run the main block. Once executed, a user interface will pop up. 

Please use the signup button to create an account and login. Users will then be able to record transactions using the transacion button.

The recorded data for the user account available once logged in, the transaction records can be found in the csv files located in src/main/data.

Once the desired transactions are recorded, the logout button is available in the home page for logging out.
