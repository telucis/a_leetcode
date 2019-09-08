### Requirements

* Functional
    - Users should be able to upload/download/view photos
    - User can perform searches based on photo/video titles
    - User can follow other users
    - The system should be able to generate and display a user's timeline consisting of top photos from all they follows
* Non-functional
    - Our service needs to be highly available
    - The acceptable latency of system is 200ms for timeline generation
    - Consistency can take a hit(in the interest of availablility), if user doesn't see a photo for a while, it should be fine
    - The system should be highly reliable, any photo/video uploaded should not be lost 
* Not in scope
    - Adding tags to photos
    - searching photos on tags
    - commenting on photos
    - tagging users to photos
    - who to follow, suggestions, etc

### Some Design Considerations

read-heavy

1. Efficient management of storage should be a 
