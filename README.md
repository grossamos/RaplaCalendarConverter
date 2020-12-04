# Ultra Rapla Calendar Converter

## Description
This Application's goal is to be able to parse the information found in Rapla calendar and make it usable in a CalDav environment.
It essentially consists out of two parts, a web scraper and a port for CalDav. The Parser can be used independently, however the CalDav port ist dependent on the previous component.
## Parser
- The Parser can be used independently of the rest of the application
- For an example of how to use it see "WebScraperExample" in ``com.amosgross.cloud.raplacalendarconverter.examples``
- Basic Usage:
    - initialize the Scraper:
    ```java
    LocalDate localDate = LocalDate.of(2020, 11, 30);
    Scraper scraper = new Scraper(UrlBuilder.getUrl("20D", localDate), localDate);
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