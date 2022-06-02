# GreenDeck Java Sync Csv Assignment (Appraoches I have Follwed)
# Project Setup (using Spring initializr)
Step1: Springboot project setup/configuration
Step2: Place the Csv File at speific location and copy its path and configure the path in Properties file.
Step3: Make an Entity/Model and make the respective getters & Setters and Constructor as well for future requirement for initializing value.
Step4: Make Dao and implement JpaRepository
Step5: Make a  Service Class i.e "CsvService.java" with @Service annotations for writing the business logic here
Step6: Make a RestController i.e "CsvController.java" class with required Handler method.

# For reading Csv and insertingn in DB
Step6: Make handler i.e readCsvFile() with end points localhost:8080/v1/csv/read-csv (This will read Csv and populate the Csv data in Mysql table).
Step7: Write business logic in Service class inside readCsvFile() for handling the Csv parsing and insertion request.

# For checking if Csv has any new records or not, if found then update the DB as Same
Step8: Make handler i.e addRowToCsv() with end points localhost:8080/v1/csv//add-records-to-csv (This will If any new records inserted in Csv, if found then it will update the same record in DB as well).
Step9: Make the addRowToCsv() in CsvService class to fulfill the updation request if new records found.

# For checking Deleting the CSV records only .
Step10: Make a removeRowFromCsv() in CsvController to handle the end point  localhost:8080/v1/csv/remove/{row} which will delete the CSV record of particular id.
Step11: Make a removeRecodsFromCSV() in CsvService to provide the business logic for deleting csv records. 

# Schedular Steps :  
Step12:  Make the schedular class i.e "ScheduledTask.java" and write scheduleFixedDelayTask() method for assigning the Task need to be scheduled. Make sure to annotate            the method with @Scheduled(fixedDelay = 3*1000) 
Step14:  Assign the task in scheduleFixedDelayTask() to 
Step13:  annotate CsvController class with @EnableScheduling so that Schedular can be exceute on respective endpoints.
# If you want to schedule it in every 2min then change the fixedDelay value as @Scheduled(fixedDelay = 2*60*1000)


# Configure the Properties file for Mysql Connectivity and and Hibernate 

# Now Test the endpoints on postman
 # 1 Read Csv And and insert records in DB as well Initially CSV has 20 records only (for testing)

![Excel1](https://user-images.githubusercontent.com/44177120/171566126-341de9d2-7318-45d4-9049-385fe16a6f88.PNG)

 DATABASE ALSO HAVE SAME NUMBER OF RECORDS 
 ![db_initialRecords](https://user-images.githubusercontent.com/44177120/171567590-810dd7d4-bd0e-4d83-bb94-ac6633b2e7ff.PNG)

# 2 for Inserting new Recods in CSV while service keeps lloking for new Records

![updatedPostman](https://user-images.githubusercontent.com/44177120/171566427-b657bb5f-7e5d-44ee-bb84-29948ee8aa57.PNG)
   NEW RECORDS INSERTED TO CSV NOW 
![Excel2](https://user-images.githubusercontent.com/44177120/171566641-5c0d896b-b8ac-4bc0-a566-e65f11f01a4b.PNG)
DATABASE ALSO GET UPDATED NOW
![updatedDb](https://user-images.githubusercontent.com/44177120/171566929-4456f4c1-1577-490e-9132-795d62d04669.PNG)

CONSOLE IS GETTING PROPER LOGS OF SCHEDULAR
![Console](https://user-images.githubusercontent.com/44177120/171567084-07f52ce0-1243-494a-8054-ea522da2784b.PNG)

# NOTES: -
# Once we will start the project it will read the CSV and and populate the csv records in database itself.
# Once we will execute the project the @GetMapping("/read-csv") will be triggered itself because we have used @EnableScheduling annotations.
# Implementing Runnable Interface and @Override run() method can be another approach for task scheduling, assign the Schedule time in controller's Handler method         inside scheduledAtFixedRate(task, initailadDelay, ScheuduledDelay, Time.Minutes); using this we can prevent auto triggering the task at load on Startup.

# For now only CSv records Deleting We can Manage all the Deletion, Updation and Insertion Task if we will get and Primary Key on Csv fILE.
