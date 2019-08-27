/**
 * 电梯设计
 * 
	需求: 以面向对象的方式设计一个电梯, 包含一些必要的类和函数.

	分析: 我的设计分为三个类, 一个是电梯本身的类, 包含一些移动电梯的函数和电梯状态. 另外还有一个update函数会被不停调用, 更新电梯状态. 还有一个请求类, 采用singleton模式, 用于接收用户请求, 和电梯提供给电梯下一个目标楼层, 和删除请求. 还有一个类似user类, 可以使得用户通过这个类提供的接口发出请求.

	电梯调度算法采用经典的电梯扫描算法, 在一个方向上完成所有的请求, 然后再改变方向完成另外一个方向的所有请求.

 */


/* 
------------------------ 
Elevator System 
------------------------ 

Use Cases : 

1. Passanger/User wants to go to the different floor. 
2. He request the floor number in the elevator system 
3. Elevator picks the person 
4. Elevator delivers the person to the floor. 

----------------- 
What if elevator is running ? 

-> If it is going to the same direction, it will pick the person on its way 
-> If it is open state, it will wait to get it running state 
-> If elevator is in halt/maintainance state, it will not respond 
-> If it is waiting state, it will start moving. 

------------------ Alternate usecases---- 
-> Elevator has a maximum number of floor. 
-> A user can request for call, alarm, stop, keep door open/close such commands 
-> Elevator has preferrences like door will keep open for 5 seconds for loading or unloading. 

------------------ 
Let's find out the classes, attribute and datastructure by doing language analysis 
--------------------------------------------------------------------------------- 
1. Passanger 
-> srcFloor 
-> destinationFloor 
*issueRequest(int dest) 
*issueAlarm() 
*issueStop() 

2. Elevator 
-> state 
-> direction 
-> speed 
-> targetted Floors 
*openDoor() 
*moveUp() 
*moveDown() 
*stop() 
*startAlarm() 

3. State (Enum) 
-> Running, Open, Idle, Stopped, Alarmed 

4. Floor 
-> number 
-> isServiced 

-------------------------------------------------------------------------------------- 
Command Pattern ( How Elevator will listen to request ) 
-------------------------------------------------------------------------------------- 
*/ 
 

class Elevator { 
	State currState; 
	int directon; 
	int speed; 
	Floor[] targettedFloors; 

	void openDoor() { 
	//Implementation of open door 
	} 

	void closeDoor() { 
	//Implementation of closing door 
	} 
} 

interface Request { 
	boolean execute(Elevator e); 
} 

class DoorOpenRequest implements Request{ 
	public boolean execute(Elevator e) 
	{ 
		e.openDoor(); 
	} 
} 

class CloseDoorRequest implements Request{ 
	public boolean execute(Elevator e) 
	{ 
		e.closecloseDoor(); 
	} 
} 

--- If passanger wants to issue request 

Command odCommand = new OpenDoorCommand(Elevator.getInstance()); 
odCommand.execute();