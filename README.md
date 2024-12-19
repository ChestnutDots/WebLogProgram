# WebLogProgram
 Analyze access to website from log entries.

Access to websites are read in from an example text file using LogEntry. They are parsed to
extract the contents using WebLogParser. This allows access to IP addresses, access time and date
and status code.

By using LogAnalyzer, the LogEntries can be analyzed by:

- quantifying unique IP addresses,
- printing all log entries,
- printing log entries with a status code that is higher than a threshold,
- creating a list of unique IP addresses on a chosen day,
- counting IP addresses within a range of a min and max of status code,
- creating a map of how many visits per IP address occured,
- creating a list of IPs that had the most visits on that day,
- mapping the IPs that visited the website on that particular day.

All methods can be tested with example text files provided, using the Tester object.
