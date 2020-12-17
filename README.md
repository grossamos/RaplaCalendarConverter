# Ultra Rapla Calendar Converter

## Description
This Application's goal is to be able to parse the information found in Rapla calendar and make it usable in a CalDav environment.
It essentially consists out of two parts, a web scraper and a port for CalDav. The Parser can be used independently, however the CalDav port ist dependent on the previous component.

## CalDav Application
- To use the Application as a whole, two things need to be given, an object of the CalDavManager class, and an implementation of the CalDavCredentials interface
- CalDavCredentials: simply implemnet ways, to output the url of your CalDav instance (nextcloud), and it's login credentials (password, username)
    ```java
      public class MyCreds implements CalDavCredentials{
  
          @Override
          public String getUrl() {
              return "some_url";
          }
  
          @Override
          public String getServerUserName() {
              return "some_username";
          }
  
          @Override
          public String getServerPassword() {
              return "some_password";
          }
      }
  ```
- The CalDavManager just has to be initialized:
  ```java
  CalDavManager calDavManager = new CalDavManager(new CalDavClient(new MyCreds()));
  ```
- For exact details on how to use it, please look at the example in ``com.amosgross.cloud.raplacalendarconverter.examples``

## Parser
- The Parser can be used independently of the rest of the application
- For an example of how to use it see "WebScraperExample" in ``com.amosgross.cloud.raplacalendarconverter.examples``
- Basic Usage:
    - initialize the Scraper:
    ```java
    LocalDate localDate = LocalDate.of(2020, 11, 30);
    Scraper scraper = new Scraper(UrlBuilder.getUrl("20D", localDate));
    ```
    - Get an ArrayList of LectureDays (a class containing all Lecture objects) call:
    ```java
    ArrayList<LectureDay> lectureDays = scraper.getLectureDaysFromPage();
    ```
    - To get the Lecture objects from a LectureDay object, call 
    ```java
    lectureDay.getLectures()
    ```
- The commonly used Lecture class has the following fields you can use:
    - Time ~ Array with [0] being the start time and [1] being the end time
    - Title ~ Sting
    - Lecturer ~ String in last name, first name format