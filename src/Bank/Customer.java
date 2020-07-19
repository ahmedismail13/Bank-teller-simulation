package Bank;

public class Customer {
    int customerID;
    int interArrivalTime;
    int arrivalTime;
    int timeServiceBegins;
    int serviceTime;
    int timeServiceEnds;
    int timeInQueue;
    int idleTime;
    int QueueLocation;

    public Customer(int customerID, int interArrivalTime, int arrivalTime, int timeServiceBegins, int serviceTime, int timeServiceEnds, int timeInQueue, int idleTime, int QueueLocation) {
        this.customerID = customerID;
        this.interArrivalTime = interArrivalTime;
        this.arrivalTime = arrivalTime;
        this.timeServiceBegins = timeServiceBegins;
        this.serviceTime = serviceTime;
        this.timeServiceEnds = timeServiceEnds;
        this.timeInQueue = timeInQueue;
        this.idleTime = idleTime;
        this.QueueLocation = QueueLocation;
    }

    public int getQueueLocation() {
        return QueueLocation;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getInterArrivalTime() {
        return interArrivalTime;
    }

    public void setInterArrivalTime(int interArrivalTime) {
        this.interArrivalTime = interArrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTimeServiceBegins() {
        return timeServiceBegins;
    }

    public void setTimeServiceBegins(int timeServiceBegins) {
        this.timeServiceBegins = timeServiceBegins;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getTimeServiceEnds() {
        return timeServiceEnds;
    }

    public void setTimeServiceEnds(int timeServiceEnds) {
        this.timeServiceEnds = timeServiceEnds;
    }

    public int getTimeInQueue() {
        return timeInQueue;
    }

    public void setTimeInQueue(int timeInQueue) {
        this.timeInQueue = timeInQueue;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    @Override
    public String toString() {
        return customerID + " " + interArrivalTime + " " + arrivalTime + " " + timeServiceBegins + " " + serviceTime + " " + timeServiceEnds + " " + timeInQueue + " " + idleTime + " " + QueueLocation;
    }
    
    
}
