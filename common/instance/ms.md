### Calendar design

* User schema
    - Id    int64   PK
    - Name  string
* Data schema
    - Data  Timestamp   PK
    - UserId    int64   PK
    - MeetingId Array<int64>
* Metting schema
    - Id    int64   PK
    - AttendId  Array<int64>
    - Start Timestamp
    - End   Timestamp


