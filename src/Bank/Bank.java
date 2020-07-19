package Bank;
import java.util.*;


public class Bank {
    
    
    
            int interArrivalArray[] = {0,1,2,3,4,5};
            int serviceTimeiceTimeArray[] = {1,2,3,4};
            double interArrivalArrayProb[] = {0.09,0.17,0.27,0.2,0.15,0.12};
            double serviceTimeiceTimeArrayProb[] = {0.2,0.4,0.28,0.12};
            ArrayList<Customer> DriveInTeller = new ArrayList<>();
            ArrayList<Customer> InsideBankTeller = new ArrayList<>();
            ArrayList<Double> interArrivalArrayCumlProb = new ArrayList<Double>();
            ArrayList<Double> ServiceTimeCumlProb = new ArrayList<Double>();
            ArrayList<Customer> customers = new ArrayList<Customer>();
            
    
    public Bank() {
        
       
            interArrivalArrayCumlProb.add(interArrivalArrayProb[0]);
        
            

            for(int i =1;i<6;i++)
            {
                interArrivalArrayCumlProb.add(interArrivalArrayCumlProb.get(i-1)+interArrivalArrayProb[i]);
            }

            ServiceTimeCumlProb.add(serviceTimeiceTimeArrayProb[0]);
            for(int i =1;i<4;i++)
            {
                ServiceTimeCumlProb.add(ServiceTimeCumlProb.get(i-1)+serviceTimeiceTimeArrayProb[i]);
            }

            for(int i =0;i<10;i++)
            {
                int randArrival = (int)(Math.random()*100+1);
                int randService = (int)(Math.random()*100+1);
                int interArr=-1,serviceTime=-1;

                if(randArrival>= 1 && randArrival <=9)
                {
                    interArr = interArrivalArray[0];
                }
                else if(randArrival>=10&&randArrival<=26)
                {
                    interArr = interArrivalArray[1];
                }
                else if(randArrival>=27&&randArrival<=53)
                {
                    interArr = interArrivalArray[2];
                }
                else if(randArrival>=54&&randArrival<=73)
                {
                    interArr = interArrivalArray[3];
                }
                else if(randArrival>=74&&randArrival<=88)
                {
                    interArr = interArrivalArray[4];
                }
                else if(randArrival>=89&&randArrival<=100)
                {
                    interArr = interArrivalArray[5];
                }

                if(randService>=1&& randService<=20)
                {
                    serviceTime = serviceTimeiceTimeArray[0];
                }
                else if(randService>=21&&randService<=60)
                {
                    serviceTime = serviceTimeiceTimeArray[1];
                }
                else if(randService>=60&&randService<=88)
                {
                    serviceTime = serviceTimeiceTimeArray[2];
                }
                else if(randService>=89&&randService<=100)
                {
                    serviceTime = serviceTimeiceTimeArray[3];
                }

                int arrivalTime;
                int timeServiceBegins;
                int timeServiceEnds;
                int timeInQueue;
                int idleTime;
                int QueueLocation;
                // id, intArr,Arrtime,TimeServBeg,ServTime,TimeServEnd,TimeInQueue,IdleTime,QueueLocation
                if(i==0)
                {
                    interArr = 0;
                    arrivalTime = 0;
                    timeServiceBegins = 0;
                    timeServiceEnds = serviceTime;
                    timeInQueue = 0;
                    idleTime = 0;
                    QueueLocation =0;
                }
                else if(i==1)
                {
                    arrivalTime = customers.get(i-1).getArrivalTime() + interArr;
                    //TimeServiceBegins
                    timeServiceBegins = arrivalTime > DriveInTeller.get(i-1).getTimeServiceEnds()? arrivalTime : DriveInTeller.get(i-1).getTimeServiceEnds();
                    QueueLocation = 0;
                    //TimeServiceEnds
                    timeServiceEnds = timeServiceBegins + serviceTime;
                    timeInQueue = timeServiceBegins - arrivalTime;
                    idleTime = timeServiceBegins - DriveInTeller.get(i-1).getTimeServiceEnds();
                }
                else
                {
                    //ArrivalTime
                    arrivalTime = customers.get(i-1).getArrivalTime() + interArr;
                    //TimeServiceBegins
                    if(arrivalTime < DriveInTeller.get(DriveInTeller.size()-2).getTimeServiceEnds())
                    {
                        if(InsideBankTeller.isEmpty())
                            timeServiceBegins = arrivalTime;
                        else
                            timeServiceBegins= arrivalTime > InsideBankTeller.get(InsideBankTeller.size()-1).getTimeServiceEnds()? arrivalTime : InsideBankTeller.get(InsideBankTeller.size()-1).getTimeServiceEnds();
                        QueueLocation = 1;
                    }
                    else
                    {
                        timeServiceBegins= arrivalTime > DriveInTeller.get(DriveInTeller.size()-1).getTimeServiceEnds()? arrivalTime : DriveInTeller.get(DriveInTeller.size()-1).getTimeServiceEnds();
                        QueueLocation = 0;
                    }
                    

                    //TimeServiceEnds
                    timeServiceEnds = timeServiceBegins + serviceTime;
                    timeInQueue = timeServiceBegins - arrivalTime;
                    if(QueueLocation == 0)
                    {
                        idleTime = timeServiceBegins - DriveInTeller.get(DriveInTeller.size()-1).getTimeServiceEnds();
                    }
                    else
                    {
                        if(InsideBankTeller.isEmpty())
                            idleTime = timeServiceBegins;
                        else
                            idleTime = timeServiceBegins - InsideBankTeller.get(InsideBankTeller.size()-1).getTimeServiceEnds();
                    }

                }
                Customer x = new Customer(i,interArr,arrivalTime,timeServiceBegins,serviceTime,timeServiceEnds,timeInQueue,idleTime,QueueLocation);
                customers.add(x);
                if(QueueLocation==0)
                {
                    DriveInTeller.add(x);
                    
                }
                    
                else if(QueueLocation==1)
                {
                    InsideBankTeller.add(x);
                    
                }
                    

                timeInQueue=0;
                idleTime=0;
            
        }
    }

    public int[] getInterArrivalArray() {
        return interArrivalArray;
    }

    public int[] getServiceTimeiceTimeArray() {
        return serviceTimeiceTimeArray;
    }

    public double[] getInterArrivalArrayProb() {
        return interArrivalArrayProb;
    }

    public double[] getServiceTimeiceTimeArrayProb() {
        return serviceTimeiceTimeArrayProb;
    }

    public ArrayList<Customer> getDriveInTeller() {
        return DriveInTeller;
    }

    public ArrayList<Customer> getInsideBankTeller() {
        return InsideBankTeller;
    }

    public ArrayList<Double> getInterArrivalArrayCumlProb() {
        return interArrivalArrayCumlProb;
    }

    public ArrayList<Double> getServiceTimeCumlProb() {
        return ServiceTimeCumlProb;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    
}
