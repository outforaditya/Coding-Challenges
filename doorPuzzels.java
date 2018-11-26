# You have 1000 doors in a row that are all initially closed. you make 100 passes by the doors starting with the first door every time. 
# Wthe first time through you visit every door and toggle the door (if the door is closed, you open it, if its open, you close it). 
# the second time you only visit every 2nd door (door #2, #4, #6). the third time, every 3rd door (door #3, #6, #9), ec, 
# until you only visit the 1000th door.
# What state are the doors in after the last pass? Which are open which are closed?

class doorPuzzles {
   
  public static void main (String[] args){
	   
	   String doors[] = new String[1000];
	   int i,j,Count=0,DoorNumber=0;
	   // initaly all doors are Closed
	   
	     for(i=0;i<1000;i++){
		   
		   doors[i]= "Close";
	   }
		  
         // iteartion from door number 1 to 1000
		   
        for(i=1;i<1000;i++){
			
			for(j=i; j<1000; j=j+i)
			{
				if(doors[j]== "Close")
					doors[j]= "Open";
				else
					doors[j] = "Close";
			}
		}

           for(i=0;i<1000;i++){
			   if(doors[i]== "Open"){
				   DoorNumber = i++;
				   System.out.println("DoorNumber " + DoorNumber + " will be Open");
				   Count++;
			   }
		   }
		   System.out.println("after 1000 iteration " + Count + " doors will be Open");
   			   
   }
   

}
