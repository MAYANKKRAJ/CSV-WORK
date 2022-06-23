# Java Sync Csv Assignment 
# Clone the Project, and follow the below Steps for execution
# Make changes in Your Prpoerties file for DB configuration 
#    • Cahnge MysQL Credentials in properties file for your local Mysql  
#    • You have to make DB manually in your Mysql,You can change DB name otherwise leave it as same ie: "csvrecords"
	
  ![PropertiesFile](https://user-images.githubusercontent.com/44177120/171572972-2c809ab5-4c7d-46af-a627-8fec5c3022b6.PNG)
  

# Place the CSV file at some location and configure the file path in Properties File.
#    • above imgage for reference
      file_path= C:\\Users\\CODER\\Downloads\\Assignment Sheet.csv 

#  • Make handler i.e readCsvFile() with  (This will read Csv and populate the Csv data in Mysql table). And make the business logic as well in Service class.
         localhost:8080/v1/csv/read-csv
	
![GetCtrl](https://user-images.githubusercontent.com/44177120/171576926-e41fa9cd-5826-41de-9e02-64db32da29f0.PNG)
		
		   define the business logic for same handler in Service
		
![ReadCsvService](https://user-images.githubusercontent.com/44177120/171578228-1056a377-ea6f-4f25-a305-1afb7e3a8792.PNG)

# now run the application and wait for output 
#      • It will auto trigger the localhost:8080/v1/csv/read-csv (because of Schedular) and it will read CSV and upadte the same data in Database.
			
		//Initially CSV has only 20 records
![Excel1](https://user-images.githubusercontent.com/44177120/171579691-40ff7209-d812-48a7-ac3a-c13406ef69e7.PNG)

// DB has also Same number of Records
![db_initialRecords](https://user-images.githubusercontent.com/44177120/171580061-ff337388-ba1d-4992-ae1f-c0eafa23a038.PNG)


# Now Insert Records to CSV and check if Csv has any new records or not, if found then update the DB as Same
     localhost:8080/v1/csv//add-records-to-csv
![updatedPostman](https://user-images.githubusercontent.com/44177120/171581207-61c8af8e-e715-4583-9370-74f29a30240a.PNG)

     New rords inserted to Excel see last row no. of Excel sheet
![Excel2](https://user-images.githubusercontent.com/44177120/171581635-e6be3c5d-4026-4314-9e3a-6e77dd127bc2.PNG)

    DB also get Updated....
![updatedDb](https://user-images.githubusercontent.com/44177120/171581770-d78ed2b5-cd2a-4bae-be8e-dd5359e6fcaf.PNG)

  GETTING PROPER LOGGER AND SCHEDULAR INFO IN CONSOLE.....
![Console](https://user-images.githubusercontent.com/44177120/171582490-f943e23f-906f-4675-b126-9216cab2e95f.PNG)
		

# If you manuaaly want to hit the endpoint for reading and populating csv in DB then : 
         Comment the Task Assigned to schedular
![cmntTask](https://user-images.githubusercontent.com/44177120/171584148-7bb214b4-a3f3-41df-8537-1753f5cc7f0e.PNG)

       hit the end point on postman .....   localhost:8080/v1/csv/read-csv
![withoutSchdlr](https://user-images.githubusercontent.com/44177120/171584284-0508024c-c10a-4179-99e0-421de7e56938.PNG)
	
	Now you can check DB has got updated with CSV file.
	
# If you don't want to comment the schedular task then you can call the schedular after end point is triggerd manually
   Below code will work if you will assign the task in run() by implementing Runnable interface.
![Runnable](https://user-images.githubusercontent.com/44177120/171585170-f07167a7-574b-4930-9f75-0ddb12428180.PNG)


# Delete Csv Record from java end using postman 
    For now only Csv record is deleting, to delete in DB we need to work with Some primary key in csv and DB.
      localhost:8080/v1/csv/remove/2               here /2 is row number
	
![Del](https://user-images.githubusercontent.com/44177120/171586302-5b0d3352-6175-40de-a7a7-435452b7dd6e.PNG)



  

 


# NOTES: -
# Once we will start the project it will read the CSV and and populate the csv records in database itself because of Schedular.
# Implementing Runnable Interface and @Override run() method can be another approach for task scheduling, assign the Schedule time in controller's Handler method         inside scheduledAtFixedRate(task, initailadDelay, ScheuduledDelay, Time.Minutes); using this we can prevent auto triggering the task at load on Startup.

# For now only CSv records Deleting We can Manage all the Deletion, Updation and Insertion Task if we will get and Primary Key on Csv fILE.
