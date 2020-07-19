/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.ArrayList;
import Bank.Customer;

/**
 *
 * @author Ahmed
 */
public class Table extends javax.swing.JFrame {

    /**
     * Creates new form Table
     */
    public Table() {
        initComponents();
        int interArrivalArray[] = {0,1,2,3,4,5};
        int serviceTimeiceTimeArray[] = {1,2,3,4};
        double interArrivalArrayProb[] = {0.09,0.17,0.27,0.2,0.15,0.12};
        double serviceTimeiceTimeArrayProb[] = {0.2,0.4,0.28,0.12};
        ArrayList<Customer> DriveInTeller = new ArrayList<>();
        ArrayList<Customer> InsideBankTeller = new ArrayList<>();
        ArrayList<Double> interArrivalArrayCumlProb = new ArrayList<Double>();
        ArrayList<Double> ServiceTimeCumlProb = new ArrayList<Double>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
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
                //timeServiceBegins = arrivalTime > DriveInTeller.get(DriveInTeller.size()-1).getTimeServiceEnds()? arrivalTime : DriveInTeller.get(DriveInTeller.size()-1).getTimeServiceEnds();
                //QueueLocation = 0;
                
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
                DriveInTeller.add(x);
            else if(QueueLocation==1)
                InsideBankTeller.add(x);
            
            timeInQueue=0;
            idleTime=0;
        }
        int countDrive=0,countInside=0;
        for(int i =0;i<10;i++)
        {
            
            if(customers.get(i).getQueueLocation()==0)
            {
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getCustomerID()+1, i, 0);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getInterArrivalTime(), i, 1);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getArrivalTime(), i,2);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getTimeServiceBegins(), i, 3);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getServiceTime(), i, 4);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getTimeServiceEnds(), i, 5);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getTimeInQueue(), i, 6);
                jTable1.getModel().setValueAt(DriveInTeller.get(countDrive).getIdleTime(), i, 7);
                jTable1.getModel().setValueAt("Drive in teller", i, 8);
                countDrive = countDrive+1;
            }
            else
            {
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getCustomerID()+1, i, 0);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getInterArrivalTime(), i, 1);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getArrivalTime(), i,2);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getTimeServiceBegins(), i, 3);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getServiceTime(), i, 4);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getTimeServiceEnds(), i, 5);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getTimeInQueue(), i, 6);
                jTable1.getModel().setValueAt(InsideBankTeller.get(countInside).getIdleTime(), i, 7);
                jTable1.getModel().setValueAt("Inside teller", i, 8);
                countInside = countInside+1;
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Interarrival time", "Arrival Time", "Time Service Begins", "Service Time", "Time service Ends", "Time In Queue", "Idle Time", "Which Queue"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Table().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
