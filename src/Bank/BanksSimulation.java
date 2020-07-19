/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.ArrayList;


public class BanksSimulation {
    
        
        
        public static void main(String args[])
        {
            ArrayList<Double> driveInServTime = new ArrayList<Double>();
            ArrayList<Double> insideServTime = new ArrayList<>();
            ArrayList<Double> driveInWaitTime = new ArrayList<>();
            ArrayList<Double> insideWaitTime = new ArrayList<>();
            ArrayList<Integer> maxInsideLength = new ArrayList<>();
            ArrayList<Double> probCustWaitInside = new ArrayList<>();
            ArrayList<Integer> probIdelInside = new ArrayList<>();
            ArrayList<Customer> tempDriveInTeller = new ArrayList<>();
            ArrayList<Customer> tempInsideBankTeller = new ArrayList<>();
            int n=30;
            for(int i =0;i<n;i++)
            {
                Bank b= new Bank();
                tempDriveInTeller = b.getDriveInTeller();
                tempInsideBankTeller= b.getInsideBankTeller();
                for(int j=0;j<10;j++)
                {
                    int countWaitInside=0; double insideWaitProb; int totalIdleTime=0;
                    double avgDriveServTime=0.0,avgInsideServTime=0.0;
                    double avgDriveWaitTime=0.0,avgInsideWaitTime=0.0;
                    for(int z=0;z<tempDriveInTeller.size();z++)
                    {
                        avgDriveServTime += tempDriveInTeller.get(z).getServiceTime();
                        avgDriveWaitTime += tempDriveInTeller.get(z).getTimeInQueue();
                    }
                    avgDriveWaitTime/=tempDriveInTeller.size();
                    avgDriveServTime/=tempDriveInTeller.size();
                    if(tempInsideBankTeller.size()==0)
                    {
                        avgInsideServTime =0;
                        avgInsideWaitTime=0;
                                
                    }
                    else
                    {
                        for(int z=0;z<tempInsideBankTeller.size();z++)
                        {
                            avgInsideServTime += tempInsideBankTeller.get(z).getServiceTime();
                            avgInsideWaitTime += tempInsideBankTeller.get(z).getTimeInQueue();
                            if(tempInsideBankTeller.get(z).getTimeInQueue()>0)
                                countWaitInside++;
                            totalIdleTime+=tempInsideBankTeller.get(z).getIdleTime();
                        }
                        avgInsideWaitTime/=tempInsideBankTeller.size();
                        avgInsideServTime/=tempInsideBankTeller.size();
                    }
                    
                    if(tempInsideBankTeller.size()>0)
                        insideWaitProb = countWaitInside/tempInsideBankTeller.size();
                    else
                        insideWaitProb =0;

                    driveInServTime.add(avgDriveServTime);
                    insideServTime.add(avgInsideServTime);
                    driveInWaitTime.add(avgDriveWaitTime);
                    insideWaitTime.add(avgInsideWaitTime);
                    maxInsideLength.add(tempInsideBankTeller.size());
                    probCustWaitInside.add(insideWaitProb);
                    probIdelInside.add(totalIdleTime);
                }
            }
            int countWaitInside=0;int totalIdleTime=0;
            double avgDriveServTime=0,avgInsideServTime=0; int maxInsNum=-99;
            double avgDriveWaitTime=0,avgInsideWaitTime=0;
            for(int i=0;i<n;i++)
            {
                avgDriveServTime+=driveInServTime.get(i);
                avgDriveWaitTime+=driveInWaitTime.get(i);
                avgInsideServTime+=insideServTime.get(i);
                avgInsideWaitTime+=insideWaitTime.get(i);
                countWaitInside+=probCustWaitInside.get(i);
                totalIdleTime+=probIdelInside.get(i);
                if(maxInsideLength.get(i)>maxInsNum)
                    maxInsNum = maxInsideLength.get(i);
            }
            avgDriveServTime/=n;
            avgDriveWaitTime/=n;
            avgInsideServTime/=n;
            avgInsideWaitTime/=n;
            countWaitInside/=n;
            totalIdleTime/=n;
            System.out.println("avgDriveServTime="+avgDriveServTime);
            System.out.println("avgDriveWaitTime="+avgDriveWaitTime);
            System.out.println("avgInsideServTime="+avgInsideServTime);
            System.out.println("avgInsideWaitTime="+avgInsideWaitTime);
            System.out.println("countWaitInside="+countWaitInside);
            System.out.println("totalIdleTime="+totalIdleTime);
            System.out.println("maxInsNum="+maxInsNum);
            tempDriveInTeller.clear();
            tempInsideBankTeller.clear();
        }
                    
}
